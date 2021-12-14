package NP_lecture.server;


import java.io.IOException;

public class NP_PROJECT_MAIN_CLASS {
    public static void main(String[] args) {
        myserver UDP_IDEN = new myserver();
        String a = UDP_IDEN.main(args);
        System.out.println("hello" + " " +a);
        TCP_CHAT_SERVER TCP_CHAT = new TCP_CHAT_SERVER();
        TCP_API_SERVER TCP_API = new TCP_API_SERVER();
        switch (a){
            case "1":
                try {
                    TCP_CHAT.main(args);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case "2":
                try {
                    TCP_API.main(args);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
