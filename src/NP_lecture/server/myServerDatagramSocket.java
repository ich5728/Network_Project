package NP_lecture.server;

import java.net.*;
import java.io.*;

/**
 * A subclass of DatagramSocket which contains
 * methods for sending and receiving messages
 * @author M. L. Liu
 */

public class myServerDatagramSocket extends DatagramSocket {
    static final int MAX_LEN = 100;
    myServerDatagramSocket(int portNo) throws SocketException{
        super(portNo);
    }

    public void sendMessage(InetAddress receiverHost,int receiverPort,String message)throws IOException {
        byte[ ] sendBuffer = message.getBytes( );
        DatagramPacket datagram = new DatagramPacket(sendBuffer, sendBuffer.length, receiverHost, receiverPort);
        this.send(datagram);
    } // end sendMessage

    public String receiveMessage( ) throws IOException {
        byte[ ] receiveBuffer = new byte[MAX_LEN];
        DatagramPacket datagram = new DatagramPacket(receiveBuffer, MAX_LEN);
        this.receive(datagram);
        String message = new String(receiveBuffer);
        return message;
    } //end receiveMessage


    //DatagramPacket ���� method
    public DatagramMessages receiveMessageAndSender( ) throws IOException {
        byte[ ] receiveBuffer = new byte[MAX_LEN];
        DatagramPacket datagram = new DatagramPacket(receiveBuffer, MAX_LEN);
        this.receive(datagram);
        // create a DatagramMessage object, to contain message
        //   received and sender's address
        DatagramMessages returnVal = new DatagramMessages( );
        returnVal.putVal(new String(receiveBuffer),datagram.getAddress( ),datagram.getPort( ));

        return returnVal;
    } //end receiveMessage
} //end class
