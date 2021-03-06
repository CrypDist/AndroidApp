package Client.Util;

/**
 * Created by ouygu on 5/13/2017.
 */

public class Config {

    public static String USER_NAME = "Client1";
    public static String USER_PASS = "Pass1";

    public static int MESSAGE_OUTGOING = 200;
    public static int MESSAGE_OUTGOING_RESPONSE = 201;
    public static int MESSAGE_MAX_TRIALS = 4;
    public static int MESSAGE_ACK = 900;
    public static int MESSAGE_REQUEST_KEYSET = 301;
    public static int MESSAGE_REQUEST_BLOCK = 302;
    public static int MESSAGE_TIMEOUT = 2500;

    public static int MESSAGE_RESPONSE_INVALIDKEY = 401;
    public static int MESSAGE_RESPONSE_INVALIDHASH = 402;
    public static int MESSAGE_RESPONSE_VALID = 403;

    public static int MESSAGE_SERVER_TEST = 999;

    public static int UPLOAD_EXPIRATION_TIME = 100000;
    public static int BLOCKCHAIN_BATCH_TIMEOUT = 10000;
    public static int BLOCKCHAIN_BATCH_PERIOD = 8000;
    public static int TRANSACTION_VALIDATION_TIMEOUT = 5000;
    public static int BLOCK_CREATION_TIMEOUT = 300000;

    public static String KEY_SPLITTER = "////";

    public static String DB_TABLE_NAME="blockchain";

    public static String SERVER_ADDRESS = "46.101.245.232";
    public static int SERVER_PORT = 4141;
    public static int SERVER_TIMEOUT = 5000;

    public static int CLIENT_HEARTBEAT_PORT = 4141;
    public static int CLIENT_SERVER_PORT = 4142;
    public static String CLIENT_MESSAGE_SPLITTER = "////";
    public static String CLIENT_MESSAGE_PEERSIZE = "X";

    public static int HEARTBEAT_FLAG_CLIENT = 101;
    public static int HEARTBEAT_FLAG_SERVER = 100;
    public static int HEARTBEAT_ACK = 102;
    public static int HEARTBEAT_PERIOD = 5000;
    public static int HEARTBEAT_TIMEOUT = 10000;
    public static int HEARTBEAT_MAX_TRIALS = 3;

    public static int FLAG_BROADCAST_TRANSACTION = 1;
    public static int FLAG_BROADCAST_HASH = 2;
    public static int FLAG_BLOCKCHAIN_INVALID = 4;

    public static String UPLOAD_BUCKETNAME = "crypdist-trial-bucket-mfs";



    public static String PRIVATE_KEY = "";

}

