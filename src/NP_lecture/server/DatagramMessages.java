package NP_lecture.server;

import java.net.*;
import java.sql.*;

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

    public int count;

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

    public void Add_User(int count, String UserName, String UserIP, String UserPWD) throws SQLException {
        this.count = count;
        USERDB db = new USERDB(count, UserName, UserIP, UserPWD);
        db.tableInsert(count, UserName, UserIP, UserPWD);
        System.out.println("create info");
    }


    public void checkID(String UserName, String UserIP, String UserPWD) throws SQLException {

        //input data to database
        int count = 0;
        this.UserName = UserName;
        this.UserIP = UserIP;
        this.UserPWD = UserPWD;
        String server = "127.0.0.1"; // MySQL 서버 주소
        String database = "user_db"; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "1234"; // MySQL 서버 비밀번호
        USERDB db = new USERDB(count, UserName, UserIP, UserPWD);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("connect DB");
            stat = conn.createStatement();
            System.out.println("Statement 객체 생성");
            rs = stat.executeQuery("Select ID, IP, PWD from user_db");
            while (true) {// 아이디값이 데이터에 존재할 때(테이블 값이 있을 때
                if (rs.next()) {
                    if ((rs.getString(1).equals(UserName)) == true) {       //아이디가 같다면
                        if ((rs.getString(2).equals(UserIP)) == true) {             //ip가 같다면
                            System.out.println("Correct ID, IP");
                            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
                            break;
                        } else if ((rs.getString(2).equals(UserIP)) != true) {
                            System.out.println("Wrong IP");
                            db.tableUpdate(count, UserName, UserIP, UserPWD);
                            System.out.println("Update User Info!");
                            break;
                        }
                    }
                } else {
                    System.out.println("Not In DATABASE");
                    Add_User(count, UserName, UserIP, UserPWD);
                    break;
                }

            }
            rs.close();
            stat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} // end class
