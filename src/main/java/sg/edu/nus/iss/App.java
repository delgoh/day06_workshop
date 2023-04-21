package sg.edu.nus.iss;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnknownHostException, IOException
    {
        System.out.println( "Hello World!" );
        CookieClientHandler cch = new CookieClientHandler(new Socket("localhost", 3000));
        Thread cookieThread1 = new Thread(cch);
        cookieThread1.start();
    }
}
