package NP_lecture.server;

import NP_lecture.client.MyClient;

import java.net.*;
import java.sql.*;
import java.util.Scanner;

public class DatagramMessages {
    private String message;
    private InetAddress senderAddress;
    private int senderPort;

    private String UserName;
    private String UserIP;
    private String UserPWD;

    private Connection conn;
    public static Statement stat;
    public static ResultSet rs;
    public static int cols;


    Scanner sc = new Scanner(System.in);

    public void putVal(String message, InetAddress addr, int port) {
        this.message = message;
        this.senderAddress = addr;
        this.senderPort = port;
    }


    public String getMessage() {
        return this.message;
    }

    public InetAddress getAddress() {
        return this.senderAddress;
    }


    public int getPort() {
        return this.senderPort;
    }


    public String checkID(String UserName, String UserIP, String userPWD) {

        //input data to database
        int count = 0;
        this.UserName = UserName;
        this.UserIP = UserIP;
        String server = "127.0.0.1"; // MySQL 서버 주소
        String database = "user_db"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "1234"; // MySQL 서버 비밀번호

        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("connect DB");
            stat = conn.createStatement();
            System.out.println("Statement 객체 생성");
            rs = stat.executeQuery("Select ID, IP, PWD from user_db");

            while (rs.next()) {
                if ((rs.getString(1).equals(UserName)) == true) {       //아이디가 같다면
                    if ((rs.getString(2).equals(UserIP)) == true) {             //ip가 같다면
                        System.out.println("Correct ID, IP ");
                        return "check done\n";
                    }
                } else {
                    System.out.println("Wrong ID, IP");
                    USERDATA NUSER = new USERDATA(count, UserName, UserIP, UserPWD);
//                    System.out.println("유저 정보를 추가? Y/N");
//                    String a = sc.nextLine();
//                    switch (a) {
//                        case "Y":
//                            USERDATA db = new USERDATA();
//                            break;
//                        case "N":
//                            return "No";
//                    }
                }
            }
            rs.close();
            stat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return UserName;
    }
} // end class