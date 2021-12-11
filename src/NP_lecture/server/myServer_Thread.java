package NP_lecture.server;

import java.io.*;
import java.net.*;

public class myServer_Thread extends Thread {
    static final String endMessage = ".";
    private Socket sock;

    public myServer_Thread(Socket sock) {
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

            byte[] buf = new byte[10];// byte �迭�� stream���·� 1024����Ʈ��ŭ
            int count;// �� byte¥����
            while ((count = fromClient.read(buf)) != -1) {
                for (Socket s : myserver.clients) {
                    if (sock != s) {
                        toClient = s.getOutputStream(); //���Ͽ� ���� ��� ��Ʈ�� ��ȯ
                        toClient.write(buf, 0, count); //
                        toClient.flush(); //��Ʈ�� �ӿ� �����͸� ������ �ʰ� ��� ����

                    }
                }
                //ApiExamTranslateNmt.main(buf); //���⼭ ���
                System.out.write(buf, 0, count); //�޽��� ���


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
