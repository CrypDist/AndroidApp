package Client;

/**
 * Created by ouygu on 5/13/2017.
 */

import Client.Blockchain.BlockchainManager;
import Client.P2P.Peer;
import Client.Util.ALogger;
import Client.Util.Config;
import Client.Util.CrypDist;
import Client.Util.CustomAppender;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.apache.log4j.Level;
import android.os.Environment;
import de.mindpipe.android.logging.log4j.LogConfigurator;

public class Property {
    public Property() {


        //PropertyConfigurator.configure(Property.class.getClassLoader().getResourceAsStream("log4j_custom.properties"));
        //Logger l1 = Logger.getLogger("P2P");

        Logger logger = ALogger.getLogger(Peer.class);

        Appender a = new CustomAppender();
        logger.addAppender(a);

        logger = ALogger.getLogger(BlockchainManager.class);
        logger.addAppender(a);

        /*logger = ALogger.getLogger("DbManager");
        logger.addAppender(a);*/

        logger = ALogger.getLogger(CrypDist.class);
        logger.addAppender(a);

        try {
            Config.PRIVATE_KEY = new String((IOUtils.toByteArray(Property.class.getClassLoader().getResourceAsStream("private.pem"))) );

        } catch (Exception e) {

        }
    }
}

