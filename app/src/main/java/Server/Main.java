package Server;

/**
 * Created by ouygu on 5/13/2017.
 */

import java.io.IOException;

public class Main {

    public static void main (String[] args) {
        try {
            Thread t = new Server();
            t.start();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
