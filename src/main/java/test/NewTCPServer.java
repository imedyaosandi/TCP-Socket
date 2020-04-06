package test;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class NewTCPServer implements Runnable
{
    //Socket connectionSocket;
    //Character aByte = 0x10;
    int port=6780;
    boolean start=false;
    ServerSocket mysocket;

    Scanner scn=new Scanner(System.in);

//    String message3= "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"
//            + "<soapenv:Header/><soapenv:Body>message1</soapenv:Body></soapenv:Envelope>" + aByte + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">"
//            + "<soapenv:Header/><soapenv:Body>message2</soapenv:Body></soapenv:Envelope>" + aByte + "<gdhgdhgdhd>hddjdjdhd</hdhdhddhjd>"+ aByte;


    public NewTCPServer(int port){
        try{
            this.port=port;
            System.out.println("Server started.....  " );
            this.mysocket = new ServerSocket(port);
            this.start=true;
        }catch(Exception e){e.printStackTrace();}
    }

    public void run(){
        Socket socket=null;
        do{
            try {
                socket=mysocket.accept();
                System.out.println("Client accepted server.....  " );
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("writing to client ");
            System.out.println("input your message to client ");
            String out=scn.nextLine();
            DataOutputStream outToClient = null;
            if (out.equals("exit")) {
                System.out.println("closing the server socket ");
                try {
                    socket.close();
                    outToClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{

                try {
                    outToClient = new DataOutputStream(socket.getOutputStream());
                    outToClient.writeUTF(out);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }



        }while(start);
//        try{
//            System.out.println("writing to client ");
//            System.out.println(Thread.currentThread().getName()
//                    + ", executing run() method!");
//            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
//            outToClient.writeBytes(message3);
//            //outToClient.writeBytes(message1);
//
//                connectionSocket.close();
//                outToClient.close();
//                System.out.println(Thread.currentThread().getName()
//                        + ", got closed");
//
//        }catch(Exception e){e.printStackTrace();}
    }

    public static void main(String argv[]) throws Exception
    {
        NewTCPServer server=new NewTCPServer(6780);
        Thread serverThread=new Thread(server);
        serverThread.start();
//        System.out.println("Threaded Server is Running  " );
//        ServerSocket mysocket = new ServerSocket(6780);
//        while(true)
//        {
//            Socket sock = mysocket.accept();
//            NewTCPServer server=new NewTCPServer(sock);
//
//
//
//        }
    }
}