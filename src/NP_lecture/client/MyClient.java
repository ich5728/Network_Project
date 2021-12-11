package NP_lecture.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClient {
    static final String endMessage = ".";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Socket sock = null;

        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        try {
            System.out.println("Input your IP Address : ");
            String hostName = br.readLine();
            if (hostName.length() == 0) // if user did not enter a name
                hostName = "localhost";  //   use the default host name

            System.out.println("Input your Port Number : ");
            String portNum = br.readLine();
            if (portNum.length() == 0)
                portNum = "2323";          // default port number
            System.out.println("Input your ID : ");
            sc.nextLine();
            System.out.println("Input your PassWord : ");


            System.out.println("Connect.");

            InetAddress address = InetAddress.getLocalHost();
            String UserName = address.getHostName(); //Com Name
            String UserAddr = address.getHostAddress(); //IP
            String UserName_form = "[" + UserName + "] >>";
            System.out.println(UserName);
            System.out.println(UserAddr);
            MyClientHelper helper = new MyClientHelper(hostName, portNum, UserName_form);
            helper.ServerGetIPID(UserName);
            helper.ServerGetIPID(UserAddr);

            boolean done = false;
            String message, echo;

            int portnum = Integer.parseInt(portNum);
            sock = new Socket(hostName, portnum);
            System.out.println(sock + ": Connect");

            OutputStream toServer = sock.getOutputStream();

            ServerHandler chandler = new ServerHandler(sock);
            chandler.start();
            byte[] buf = new byte[10];
            int count = System.in.read(buf);

            while (!done) {
                System.out.println("Enter a line to receive an echo back from the server, " + "or a single peroid to quit.");
                message = br.readLine();

                if ((message.trim()).equals(endMessage)) {
                    done = true;
                    helper.done();
                    sock.close();
                } else {
                    echo = helper.getEcho(message);
                    //
                    System.out.println(echo);
                    toServer.write(buf, 0, count);
                    toServer.flush();
                }
            } // end while
        } // end try
        catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // end catch
    }// end main
}// end class

