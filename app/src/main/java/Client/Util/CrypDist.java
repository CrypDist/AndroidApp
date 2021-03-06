package Client.Util;

/**
 * Created by ouygu on 5/13/2017.
 */


import Client.Blockchain.BlockchainManager;
//import GUI.ScreenManager;
import Client.P2P.Client;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;
import android.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CrypDist {

    private static transient  Logger log = Logger.getLogger("CrypDist");
    private byte[] sessionKey;
    private boolean authenticated;
    private boolean active;
    //private ScreenManager screenManager;
    // Flag 1 = Transaction data
    // Flag 2 = Hash
    // Flag 3 = Valid transaction message
    // Flag 4 = Validate my blockchain (taken from blockchainManager)

    private static BlockchainManager blockchainManager;
    private static Client client;

    /*public CrypDist(ScreenManager screenManager)
    {
        this.screenManager = screenManager;
        if(!Decryption.initialization())
            log.warn("Decryption service cannot be created.");
        client = new Client(this);
        blockchainManager = new BlockchainManager(this, sessionKey);
        updateBlockchain();
        Thread t = new Thread(client);
        t.start();
    }*/

    public CrypDist()
    {
        if(!Decryption.initialization())
            log.warn("Decryption service cannot be created.");
        client = new Client(this);
        blockchainManager = new BlockchainManager(this, sessionKey);
        updateBlockchain();
        Thread t = new Thread(client);

        if(isAuthenticated() && isActive())
            log.error("CrypDist is started successfully, authenticated and active.");
        else if(isAuthenticated() && !isActive())
            log.error("CrypDist is started successfully, authenticated and passive.");
        else if(!isAuthenticated() && isActive())
            log.error("CrypDist is started successfully, NOT authenticated and active.");
        else
            log.error("CrypDist is started successfully, NOT authenticated and passive.");

        t.start();
    }

    public BlockchainManager getBlockchainManager()
    {
        return blockchainManager;
    }

    public String updateByClient(String arg) {
        if (blockchainManager == null || blockchainManager.getBlockchain() == null){
            return "";
        }
        Gson gson = new Gson();

        String lastHash = blockchainManager.getBlockchain().getLastBlock();

        String strToBeSplitted = arg;
        String[] elems = strToBeSplitted.split(Config.CLIENT_MESSAGE_SPLITTER);
        String ip = elems[0];
        String str = elems[1];

        if(ip.equals(Config.CLIENT_MESSAGE_PEERSIZE)) {
            log.debug("Pair size is now " + str);
            blockchainManager.setNumOfPairs(Integer.parseInt(str));
            return "";
        }

        JsonObject obj2 = gson.fromJson(str, JsonObject.class);

        int flagValue = obj2.get("flag").getAsInt();

        if(flagValue == Config.MESSAGE_REQUEST_KEYSET) {
            return blockchainManager.getKeySet();
        }

        if(flagValue == Config.MESSAGE_REQUEST_BLOCK) {
            log.debug("BLOCK REQUESTED.");
            JsonElement data = obj2.get("data");
            String dataStr = data.getAsString();

            Object block = blockchainManager.getBlock(dataStr);
            if(block == null) {
                return "";
            } else {
                return gson.toJson(blockchainManager.getBlock(dataStr));
            }

        }
        byte[] dummy = new byte[1];

        String hashValue = obj2.get("lastHash").getAsString();
        byte[] key = Base64.decode( gson.fromJson(obj2.get("key").getAsString(),dummy.getClass()), Base64.DEFAULT);
        String[] credentials = Decryption.decryptGet(key);
        String messageIp;
        if(credentials == null){
            log.warn("The incoming message includes false key");
            messageIp = "";
        }
        else {
            messageIp = credentials[0];
        }



        JsonObject toReturn = new JsonObject();
        toReturn.addProperty("key", Base64.encodeToString(sessionKey, Base64.DEFAULT));

        if(ip.equals(messageIp)) {
            if (blockchainManager.validateHash(hashValue)) {

                if (flagValue == Config.FLAG_BROADCAST_TRANSACTION) {

                    JsonElement data = obj2.get("data");
                    String dataStr = data.getAsString();

                    toReturn.addProperty("response", Config.MESSAGE_RESPONSE_VALID);
                    toReturn.addProperty("transaction", dataStr);
                    toReturn.addProperty("lastHash", hashValue);

                    blockchainManager.addTransaction(dataStr);

                } else if (flagValue == Config.FLAG_BROADCAST_HASH) {
                    toReturn.addProperty("response", Config.MESSAGE_RESPONSE_VALID);

                    JsonElement data = obj2.get("data");
                    JsonElement time = obj2.get("timeStamp");
                    JsonElement blockId = obj2.get("blockId");
                    blockchainManager.receiveHash(data.getAsString(), time.getAsLong(), blockId.getAsString());
                }

            } else {
                toReturn.addProperty("response", Config.MESSAGE_RESPONSE_INVALIDHASH);
                log.warn("Incoming message has invalid hash.");
            }
        }
        else  {
            toReturn.addProperty("response", Config.MESSAGE_RESPONSE_INVALIDKEY);
        }
        return gson.toJson(toReturn);
    }

    public String updateByBlockchain(Object arg) {
        log.debug("BE NOTIFIED");
        Gson gson = new Gson();
        String lastHash = blockchainManager.getBlockchain().getLastBlock();

        JsonObject obj = (JsonObject) arg;
        obj.addProperty("lastHash", lastHash);
        byte [] keyStr = Base64.encode(sessionKey, Base64.DEFAULT);
        obj.addProperty("key", gson.toJson(keyStr));

        int flag = obj.get("flag").getAsInt();

        if(flag == Config.FLAG_BROADCAST_TRANSACTION) {

            log.debug("TRANSACTION IS BEING SENT");
            HashMap<String,String> results = client.broadCastMessageResponseWithIp(obj.toString());

            log.debug("RESULT SIZE IS " + results);
            int totalValidResponses = 0;
            int totalValidations = 0;
            int totalInvalidKeysResponses = 0;
            int totalInvalidHashResponses = 0;
            String transaction = null;

            for(Map.Entry<String,String> entry : results.entrySet()) {

                JsonObject result = gson.fromJson(entry.getValue(), JsonObject.class);
                byte[] key = Base64.decode(result.get("key").getAsString(), Base64.DEFAULT);
                String[] credentials = Decryption.decryptGet(key);
                if (credentials == null) {
                    continue;
                }
                String messageIp = credentials[0];
                String username = credentials[1];


                if (messageIp.equals(entry.getKey()) && username.length() > 2) {
                    int response = result.get("response").getAsInt();

                    if (response == Config.MESSAGE_RESPONSE_INVALIDKEY) {
                        totalInvalidKeysResponses++;
                    }
                    if (response == Config.MESSAGE_RESPONSE_VALID) {

                        if(transaction != null && !transaction.equals(result.get("transaction").getAsString()))
                            log.warn("WRONG RESPONSE");

                        transaction = result.get("transaction").getAsString();
                        totalValidations++;
                    }
                    if (response == Config.MESSAGE_RESPONSE_INVALIDHASH)
                        totalInvalidHashResponses++;

                    totalValidResponses++;
                }
            }

            log.debug(totalValidations + " vs  " + totalValidResponses);
            log.debug("Invalid:" + totalInvalidHashResponses + " vs  " + totalValidResponses);

            if(totalValidations >= totalValidResponses/2+1)
                blockchainManager.markValid(transaction);
            else if(totalInvalidHashResponses >= totalValidResponses/2 +1)
            {
                updateBlockchain();
                blockchainManager.addTransaction(obj.get("data").getAsString());
                updateByBlockchain(arg);
            }
        }
        else if (flag == Config.FLAG_BROADCAST_HASH) {
            log.debug("HASH BROADCAST IS IN PROCESS");
            client.broadCastMessage(obj.toString());
        }
        else if (flag == Config.FLAG_BLOCKCHAIN_INVALID) {
            log.debug("HASH UPDATE IS IN PROGRESS");
            updateBlockchain();
        }
        else
            log.warn("Invalid flag");

        return "";
    }

    public void updateBlockchain()
    {
        synchronized (this) {
            blockchainManager.setUpdating(true);
            // UPDATE BLOCKCHAIN
            log.info("Blockchain update procedure is started!");
            ArrayList<String> keySet = client.receiveKeySet();
            if (keySet.size() == 0) {
                blockchainManager.setUpdating(false);
                return;
            }
            Set<String> purifiedList = blockchainManager.getPurifiedList(keySet);

            for (String str: purifiedList)
            {
                log.debug("KEY IN PURIFIED LIST: " + str);
            }

            for (String str:blockchainManager.getBlockchain().getKeySet())
            {
                log.debug("KEY IN BLOCKCHAIN: " + str);
            }

            Set<String> neededBlocks = blockchainManager.getNeededBlocks(purifiedList);

            blockchainManager.removeInvalidBlocks(new ArrayList<>(purifiedList));


            if (neededBlocks.size() == 1){
                log.trace(neededBlocks.iterator().next());
            }
            else if (neededBlocks.size() == 0) {
                blockchainManager.setUpdating(false);
                return;
            }

            HashMap<String, String> blocks = client.receiveBlocks(neededBlocks);

            for (String str : blocks.values())
                log.debug("BLOCK " + str);


            blockchainManager.addNewBlocks(blocks);
            blockchainManager.setUpdating(false);
        }
    }

    public void setSessionKey(byte[] sessionKey) {
        this.sessionKey = sessionKey;
    }

    public byte[] getSessionKey() {
        return sessionKey;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
