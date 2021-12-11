package NP_lecture.client;

import java.net.*;
import java.io.*;

public class MyClientDatagramSocket extends DatagramSocket {
    static final int MAX_LEN = 100;

    MyClientDatagramSocket( ) throws SocketException{
        super( );
    }

    MyClientDatagramSocket(int portNo) throws SocketException{
        super(portNo);
    }

    public void sendMessage(InetAddress receiverHost,int receiverPort,String message) throws IOException {
        byte[ ] sendBuffer = message.getBytes( ) ;
        DatagramPacket datagram = new DatagramPacket(sendBuffer, sendBuffer.length, receiverHost, receiverPort);
        this.send(datagram); // �ش� IP, Port�� buffer�� �ִ� sendBuffer ���̸�ŭ�� ���� ����
    } // end sendMessage


    public String receiveMessage()throws IOException {
        byte[ ] receiveBuffer = new byte[MAX_LEN];
        DatagramPacket datagram = new DatagramPacket(receiveBuffer, MAX_LEN);
        this.receive(datagram);
        String message = new String(receiveBuffer);
        return message;
    } //end receiveMessage


    public void sendIPID(InetAddress receiverHost,int receiverPort,String userIPID) throws IOException {
        byte[ ] sendIPIDBuffer = userIPID.getBytes( );
        DatagramPacket datagram = new DatagramPacket(sendIPIDBuffer, sendIPIDBuffer.length, receiverHost, receiverPort);
        this.send(datagram); // �ش� IP, Port�� buffer�� �ִ� sendBuffer ���̸�ŭ�� ���� ����

    } // end sendMessage




} //end class
