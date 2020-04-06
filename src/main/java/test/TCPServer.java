package test;

import java.io.*;
import java.net.*;

class TCPServer {
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    Character aByte = 0x10;
    int count =0;
    String message= "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"
            + "<soapenv:Header/><soapenv:Body/></soapenv:Envelope>" + aByte;

    public TCPServer(int port) throws IOException {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started" + server);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Waiting for a client ...");

        try {
            socket = server.accept();
            System.out.println("Client accepted " + socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(count<5){
            System.out.println("writing to client " + count);
            DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
            outToClient.writeBytes(message);
            count++;
        }else{
            System.out.println("Closing the connection..... ");
            socket.close();

        }

    }

    public static void main(String argv[]) throws Exception {

        TCPServer server = new TCPServer(6780);

    }
}
