
package NP_lecture.server;

import java.io.*;
import java.net.*;
import java.util.*;

//TCP
public class TCP_API_SERVER {
    public static ArrayList<Socket> clients2 = new ArrayList<Socket>(5);

    public static void main(String[] args) throws IOException {
        int serverPort = 2323; // default port
        if (args.length == 1)
            serverPort = Integer.parseInt(args[0]);
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println(serverSocket + "서버 생성");
            // server - client 간 connect, creating connection socket
            while (true) {
                Socket client = serverSocket.accept();
                clients2.add(client);
                myServer_Thead_API myserver2 = new myServer_Thead_API(client);
                myserver2.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }//end catch
    }//end main
}