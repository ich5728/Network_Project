package NP_lecture.client;

import java.net.*;
import java.io.*;

public class MyClientHelper {
    private MyClientDatagramSocket mySocket;
    private InetAddress serverHost;
    private int serverPort;
    private String UserName_form;


    MyClientHelper(String hostName, String portNum, String UserName_form) throws SocketException, UnknownHostException {
        this.serverHost = InetAddress.getByName(hostName);
        this.serverPort = Integer.parseInt(portNum);
        // instantiates a datagram socket for both sending
        // and receiving data
        this.mySocket = new MyClientDatagramSocket();
        this.UserName_form = UserName_form;
    }

    public String getEcho( String message) throws SocketException, IOException {
        String echo = "";
        mySocket.sendMessage(serverHost, serverPort, message);
        // �ش� Host, Port���� message�� ���ִ� method
        echo = UserName_form + mySocket.receiveMessage();

        return echo;
    } //end getEcho

    public void ServerGetIPID(String userInfo) throws IOException {
        mySocket.sendIPID(serverHost, serverPort, userInfo);
    }

    public void done( ) throws SocketException {
        mySocket.close( );
    }  //end done

} //end class
