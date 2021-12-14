package NP_lecture.server;

import java.io.*;
import java.net.*;

public class myserverthread2 extends Thread {
    static final String endMessage = ".";
    private Socket sock;

    //생성자
    public myserverthread2(Socket sock) {
        this.sock = sock;
    }

    // 배열리스트에서 클라이언트 소켓을 제거, 접속 후 나갈때 사용
    public void remove(Socket socket) {
        for (Socket s : myserver2.clients) {
            if (socket == s) {
                myserver2.clients.remove(socket);
                break;
            }
        }
    }

    public void run() {      //스레드가 할일을 기술

        InputStream fromClient = null;
        OutputStream toClient = null;

        try {
            System.out.println(sock + ": 연결됨");

            fromClient = sock.getInputStream();

            byte[] buf = new byte[10];// byte 배열에 stream형태로 1024바이트만큼
            int count;// 몇 byte짜린지
            while ((count = fromClient.read(buf)) != -1) {
                for (Socket s : myserver2.clients) {
                    if (sock != s) {
                        toClient = s.getOutputStream(); //소켓에 대한 출력 스트림 반환
                        toClient.write(buf, 0, count); //
                        toClient.flush(); //스트림 속에 데이터를 남기지 않고 모두 전송

                    }
                }
                //ApiExamTranslateNmt.main(buf); //여기서 출력
                System.out.write(buf, 0, count); //메시지 출력
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