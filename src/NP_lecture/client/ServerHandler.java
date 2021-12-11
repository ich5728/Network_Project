package NP_lecture.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerHandler extends Thread {
    Socket sock = null;

    public ServerHandler(Socket sock) {
        this.sock = sock;
    }

    public void run() {
        InputStream fromServer = null;
        try {       //���� �д� �ٽ� ���
            fromServer = sock.getInputStream();

            byte[] buf = new byte[10];
            int count;

            while ((count = fromServer.read(buf)) != -1)
                //ApiExamTranslateNmt.main(buf); //���⼭ ���
                System.out.write(buf, 0, count);        //ȭ�鿡 ����


        } catch (IOException e) {
            System.out.println("��������(" + e + ")");
        } finally {
            try {
                if (fromServer != null)
                    fromServer.close();
                if (sock != null)
                    sock.close();
            } catch (IOException e) {
            }
        }
    }
}