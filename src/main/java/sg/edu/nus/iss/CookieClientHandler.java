package sg.edu.nus.iss;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CookieClientHandler implements Runnable {

    private final Socket socket;

    public CookieClientHandler(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        Console cons = System.console();
        String keyboardInput = "";

        try (InputStream is = socket.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);
            try (OutputStream os = socket.getOutputStream()) {
                BufferedOutputStream bos = new BufferedOutputStream(os);
                DataOutputStream dos = new DataOutputStream(bos);

                String msgReceived = "";
                System.out.println("Get a cookie!");
                while (!keyboardInput.equals("close")) {
                    keyboardInput = cons.readLine("Command: ");
                    dos.writeUTF(keyboardInput);
                    dos.flush();

                    msgReceived = dis.readUTF();
                    System.out.println(msgReceived);
                }

                dos.close();
                bos.close();
                os.close();
            } catch (EOFException e) {
                e.printStackTrace();
                dis.close();
                bis.close();
                is.close();
            }
            
            dis.close();
            bis.close();
            is.close();
        } catch (EOFException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
