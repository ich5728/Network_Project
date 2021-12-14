package NP_lecture.client;


import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClient {

    public static void main(String[] args) {
        byte[] buf = new byte[1024];
        Socket sock = null;
        Scanner sc = new Scanner(System.in);
        int count = 0;
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        DatagramPacket DP = new DatagramPacket(buf, buf.length);


        try {
            System.out.println("Input your IP Address : ");
            String hostName = br.readLine();
            if (hostName.length() == 0) // if user did not enter a name
                hostName = "localhost";  //   use the default host name
            System.out.println("Input your Port Number : ");
            String portNum = br.readLine();
            if (portNum.length() == 0) {
                portNum = "2323";
            } else {
                System.out.println("Defferent Sock Number");
                sock.close();
            }    // default port number
            System.out.println("Input your ID : ");
            String ID = br.readLine();
            if (ID.length() == 0)
                ID = InetAddress.getLocalHost().getHostName();
            System.out.println("Input your PassWord : ");
            String PWD = br.readLine();
            if (PWD.length() == 0)
                PWD = "1234";


            InetAddress address = InetAddress.getLocalHost();
            String UserAddr = address.getHostAddress(); //IP
            String UserName_form = "[" + ID + "] >>";
            System.out.println("Check Your Info");
            System.out.println("Your Info : ID : " + ID + ", IP : " + UserAddr + ", PWD : " + PWD);
            MyClientHelper helper = new MyClientHelper(hostName, portNum, UserName_form, ID, PWD);

// 여기에다가 정보 출력, 값 입력받는 거
//            System.out.println("Not Info in DATABASE");
            System.out.println("Create Your Info?   Y/N");
            String Check = sc.nextLine();
            switch (Check) {
                case "Y":
                    helper.ServerGetIPID(ID);
                    helper.ServerGetIPID(UserAddr);
                    helper.ServerGetIPID(PWD);
                    System.out.println("Create your Info");
                    System.out.println("Welcome Server");
                    break;
                case "N":
                    helper.ServerGetIPID(ID);
                    helper.ServerGetIPID(UserAddr);
                    helper.ServerGetIPID(PWD);
                    System.out.println("Close Server");
            }

//
            System.out.println("Select Connect Server : " + "\t" + "1(일반 채팅) / 2(번역 채팅, 한국어 -> 영어)");
            String selectSer = br.readLine();
            switch (selectSer) {
                case "1":
                    helper.ServerGetNum(selectSer);
                    TCP_CHAT_CLIENT tcp_chat = new TCP_CHAT_CLIENT();
                    tcp_chat.main(args);
                case "2":
                    helper.ServerGetNum(selectSer);
                    TCP_API_CHAT tcp_chat2 = new TCP_API_CHAT();
                    tcp_chat2.main(args);

            }
        } // end try
        catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // end catch
    }// end main
}// end class

