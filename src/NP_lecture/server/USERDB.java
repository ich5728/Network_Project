package NP_lecture.server;

import java.sql.*;

public class USERDB {

    private Connection con = null;
    private Statement stat = null;
    private ResultSet rs = null;
    static final String server = "127.0.0.1"; // MySQL 서버 주소
    static final String database = "user_db"; // MySQL DATABASE 이름
    static final String user_name = "root"; //  MySQL 서버 아이디
    static final String password = "1234"; // MySQL 서버 비밀번호

    public USERDB(int count, String UserName, String UserIP, String UserPWD) throws SQLException {

        // 1.드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 2.연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }
        stat = con.createStatement();


        // 3.해제
        try {
            if (con != null) {
                stat.close();
                con.close();
            }
        } catch (SQLException e) {
        }
    }

    public void tableInsert(int count, String userid, String userip, String userpwd) throws SQLException {

        Table tn = new Table(count, userid, userip, userpwd);

        String query = "INSERT user_db " + "VALUE (" + count + ",'" + tn.ID + "','" + tn.IP + "','" + tn.PWD + "');";
        System.out.println(query);
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }
        stat = con.createStatement();
        stat.executeUpdate(query);
    }

    public void tableUpdate(int count, String userid, String userip, String userpwd) throws SQLException {
        Table tn = new Table(count, userid, userip, userpwd);
        String query = "UPDATE user_db " + "SET IP = '" + tn.IP + "' WHERE ID = '" + tn.ID + "';";
        System.out.println(query);
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }
        stat = con.createStatement();
        stat.executeUpdate(query);
    }

    public class Table {    //make values to database
        int count;
        String ID;
        String IP;
        String PWD;

        public Table(int count, String ID, String IP, String PWD) {
            super();
            this.count = count;
            this.ID = ID;
            this.IP = IP;
            this.PWD = PWD;
        }

        // for print user data
        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getIP() {
            return IP;
        }

        public void setIP(String IP) {
            this.IP = IP;
        }

        public String getPWD() {
            return PWD;
        }

        public void setPWD(String PWD) {
            this.PWD = PWD;
        }
    }
}
