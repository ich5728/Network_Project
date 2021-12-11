package NP_lecture.server;

import java.net.Socket;
import java.util.ArrayList;


public class myserver {
    public static ArrayList<Socket> clients = new ArrayList<Socket>(5);//TCP

    public static void main(String[] args) {

        int serverPort = 2323;    // default port

        if (args.length == 1)
            serverPort = Integer.parseInt(args[0]);

        try {
            // instantiates a datagram socket for both sending
            // and receiving data
            System.out.println("Server ready.");

            myServerDatagramSocket mySocket = new myServerDatagramSocket(serverPort); //UDP

            DatagramMessages forID = mySocket.receiveMessageAndSender();
            DatagramMessages forIP = mySocket.receiveMessageAndSender();

            int count = clients.size();     //접속자 넘버링 확인, 굳이 필요는 없음
            String UserID = forID.getMessage().trim();      //혼자해서 중첩값 때문에 바꿔둠, 아이디를 PK로 설정
            String UserIP = forIP.getMessage().trim();  //IP주소값 저장, 갱신
            String UserPWD = "1234";       //비밀번호는 직접 입력하는식으로 변경해야함
            System.out.println("User Connect     ID: " + UserID + "     IP: " + UserIP + "     PWD : " + UserPWD);

//          USERDATA db = new USERDATA(count, UserID, UserIP, UserPWD);     // connect database 유저 정보가 있으면 안하고, 없으면 하고
            String CI = forID.checkID(UserID, UserIP, UserPWD);
            if(CI == "No")
                mySocket.close();
            // -> TCP
        } // end try
        catch (Exception e) {
            e.printStackTrace();
        } // end catch
    } //end main
} // end class


