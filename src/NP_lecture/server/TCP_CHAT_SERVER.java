
package NP_lecture.server;

import java.io.*;
        import java.net.*;
        import java.util.*;

//TCP
public class TCP_CHAT_SERVER {
    public static ArrayList<Socket> clients = new ArrayList<Socket>(5);
    //server에서 ArrayList로 받아온 clients들의 serverSocket값들 저장
    //max 5

    public static void main(String[] args) throws IOException {
        int serverPort = 2323; // default port
        if(args.length == 1)
            serverPort = Integer.parseInt(args[0]);
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println(serverSocket + "서버 생성");
            // server - client 간 connect, creating connection socket

            while (true) {
                Socket client = serverSocket.accept();
                clients.add(client);
                myServer_Thread myserver = new myServer_Thread(client);
                myserver.start();
//              Thread theThread = new Thread(new myServer_Thread(server_socket));
//               theThread.start();       //스레드를 스타트 run() method call
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }//end catch
    }//end main
}