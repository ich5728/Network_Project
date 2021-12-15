package NP_lecture.server;

import java.io.*;
import java.net.*;

public class myServer_Thead_API extends Thread {
    static final String endMessage = ".";
    private Socket sock;

    public myServer_Thead_API(Socket sock) {
        this.sock = sock;
    }

    public void remove(Socket socket) {
        for (Socket s : myserver.clients) {
            if (socket == s) {
                myserver.clients.remove(socket);
                break;
            }
        }
    }

    public void run() {
        InputStream fromClient = null;
        OutputStream toClient = null;
        try {
            System.out.println(sock + ": Connected");
            fromClient = sock.getInputStream();
            byte[] buf = new byte[30];
            int count;
            while ((count = fromClient.read(buf)) != -1) {
                for (Socket s : TCP_API_SERVER.clients2) {
                    if (sock != s) {
                        toClient = s.getOutputStream();
                        if (buf[0] == 38) {
                            byte[] text = api.main(buf);
                            int count2 = api.main(buf).length;
                            toClient.write(text, 0, count2);
                        } else {
                            toClient.write(buf, 0, count);
                        }
                        toClient.flush();
                    }
                }
                api.main(buf);
            }
        } catch (IOException e) {
            System.out.println("Exception caught in thread : " + e);
        } finally {
            try {
                if (sock != null) {
                    sock.close();
                    remove(sock);
                    fromClient = null;
                    toClient = null;
                }
            } catch (IOException e) {
            }
        }
    }
}
