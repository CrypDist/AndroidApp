package Client.Util;

/**
 * Created by ouygu on 5/14/2017.
 */

import org.apache.log4j.Level;
import android.os.Environment;
import de.mindpipe.android.logging.log4j.LogConfigurator;
import org.apache.log4j.Logger;

import java.io.File;

public class ALogger {
    public static org.apache.log4j.Logger getLogger(Class clazz) {
        final LogConfigurator logConfigurator = new LogConfigurator();
        logConfigurator.setFileName(Environment.getExternalStorageDirectory().toString() + File.separator + "log.txt");
        logConfigurator.setRootLevel(Level.ALL);
        logConfigurator.setLevel("org.apache", Level.ALL);
        logConfigurator.setUseFileAppender(true);
        logConfigurator.setFilePattern("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
        logConfigurator.setMaxFileSize(1024 * 1024 * 10);
        logConfigurator.setImmediateFlush(true);
        logConfigurator.configure();
        Logger log = Logger.getLogger(clazz);
        return log;
    }
}
