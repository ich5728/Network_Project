package NP_lecture.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class TCP_CHAT_CLIENT {
    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        Socket sock = null;
        try {
            // IP addr
            System.out.println("Server Host IP\n");
            String hostName = br.readLine();
            if (hostName.length() == 0) // if user did not enter a name
                hostName = "localhost";  //   use the default host name

            // Port Num
            System.out.println("Server host Port\n");
            String portNum = br.readLine();
            if (portNum.length() == 0)
                portNum = "2323";          // default port number
            int portNUM = Integer.parseInt(portNum); // cast String to Int

            sock = new Socket(hostName, portNUM);

            // Connect
            System.out.println(sock + ": 연결됨");
            System.out.println("Start Chat");

            OutputStream toServer = sock.getOutputStream();

            //서버에서 보내오는 값을 받기위한 스레드
            ServerHandler chandler = new ServerHandler(sock);
            chandler.start();

            byte[] buf = new byte[1024];
            int count;

            while ((count = System.in.read(buf)) != -1) {
                toServer.write(buf, 0, count);
                toServer.flush();
            }
        } catch (IOException e) {
            System.out.println("연결종료 (" + e + ")");
        } finally {
            try {
                if (sock != null)
                    sock.close();
            } catch (IOException e) {

            }
        }
    }
}