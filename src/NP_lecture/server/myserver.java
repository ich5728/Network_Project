package NP_lecture.server;

import java.net.Socket;
import java.util.ArrayList;

public class myserver {
    public static ArrayList<Socket> clients = new ArrayList<Socket>(5);//TCP

    public static String main(String[] args) {

        int serverPort = 2323;    // default port

        if (args.length == 1)
            serverPort = Integer.parseInt(args[0]);

        try {
            // instantiates a datagram socket for both sending
            // and receiving data
            myServerDatagramSocket mySocket = new myServerDatagramSocket(serverPort); //UDP

            System.out.println("Server ready.");
            DatagramMessages forID = mySocket.receiveMessageAndSender();
            DatagramMessages forIP = mySocket.receiveMessageAndSender();
            DatagramMessages forPWD = mySocket.receiveMessageAndSender();
            DatagramMessages forTcp = mySocket.receiveMessageAndSender();

            String UserID = forID.getMessage().trim();
            String UserIP = forIP.getMessage().trim();  //IP주소값 저장, 갱신
            String UserPWD = forPWD.getMessage().trim();       //비밀번호는 직접 입력하는식으로 변경해야함
            System.out.println("User Connect  ID: " + UserID + "   IP: " + UserIP + "   PWD : " + UserPWD);
// connect database 유저 정보가 있으면 안하고, 없으면 정보를 입력 하고 정보가 다르다면 자동 갱신
            forID.checkID(UserID, UserIP, UserPWD);
            String selSer = forTcp.getMessage().trim();
           return selSer;
            // -> TCP
        } // end try
        catch (Exception e) {
            e.printStackTrace();
        } // end catch
        return null;
    } //end main

} // end class
