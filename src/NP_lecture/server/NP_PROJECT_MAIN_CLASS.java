package NP_lecture.server;


import java.io.IOException;

public class NP_PROJECT_MAIN_CLASS {
    public static void main(String[] args) {
        myserver UDP_IDEN = new myserver();
        String a = UDP_IDEN.main(args);
        System.out.println("hello" + " " +a);
        myserver2 TCP_CHAT = new myserver2();
        switch (a){
            case "1":
                try {
                    TCP_CHAT.main(args);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case "2":
        }
    }
}
