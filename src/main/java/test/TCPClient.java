package test;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class TCPClient{

    String host = "localhost";
    int port = 6780;
    static Socket socket = null;
    int count = 0;
    ExecutorService workerpool = Executors.newFixedThreadPool(5);

    public static void main(String args[]) throws Exception {
        //Character aByte = 0x10;
        TCPClient client = new TCPClient();
//        do{
//                client.connect();
//
//        }while(socket==null);
       client.connect();

//        String message = "<bbbcbcbcbccbcb\"http://schemasbdhbdhbdhbdlope/\">"
//                + "<bdhshcccvbhbcdbb></soapenv:Envelope>" + aByte;
//        client.sendToServer(message);
//        client.recieveFromServer();
//        client.sendToServer(message);
//        client.recieveFromServer();
//        client.close();
    }

    public void connect(){
        Socket socket=null;
        String del="split";
        do{
            try {
                if(socket==null) {
                    socket = new Socket(host, port);
                    //socket.setSoTimeout(100*1000);
                    System.out.println("Connected");
                }
                DataInputStream input = new DataInputStream(socket.getInputStream());
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    int next = input.read();
                    //System.out.println("next char " + next);
                    while(next > -1) {
                        bos.write(next);
                        next = input.read();
                        if(input.available() <= 0) {
                            //System.out.println("next char " + input.available());
                            if(next > -1) {
                                bos.write(next);
                            }
                            String[] segments = bos.toString().split(del);
                            for(String s : segments) {
                                //System.out.println("next segment " + s);
                                workerpool.execute(new TCPWorker(s));
//                                Thread out=new Thread(new TCPWorker(s));
//                                out.start();
                                //System.out.println("after thread>>>" );
                            }
                            //System.out.println("......out of for loop........ " + next);
                            bos = new ByteArrayOutputStream();
                            //System.out.println("......out of for loop........>> " + bos);
                            break;
//                            next = input.read();
//                            System.out.println("......next char........ " + next);
                        }
                    }
                } catch (IOException e) {

                } finally {
                    try {
                        bos.close();
                    } catch (IOException e) {

                    }
                }
                socket=null;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }while (socket==null);

    }
    TCPClient() throws Exception {
//        socket = new Socket(host, port);
//        System.out.println("Connected");
    }

//    void sendToServer(String msg) throws Exception {
//        //create output stream attached to socket
//        System.out.println("send " + msg);
//        PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
//        //send msg to server
//        outToServer.print(msg);
//        outToServer.flush();
//    }

//    void recieveFromServer() throws Exception {
//
//        char delimiter = 0x10;
//        InputStream inFromServer = socket.getInputStream();
//        //read from server
//        int next = inFromServer.read();
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        while (next > -1) {
//            if (delimiter != next) {
//                bos.write(next);
//            }
//            next = inFromServer.read();
//            if (delimiter == next) {
//                System.out.println(new String(bos.toByteArray()));
//                count++;
//                if (count == 1 || count == 2) {
//                    break;
//                }
//                bos = new ByteArrayOutputStream();
//            }
//        }
//
//        if (count == 2) {
//            close();
//        }
//    }

//    void close() throws IOException {
//        socket.close();
//    }

//    public void run() {
//        Socket socket=null;
//        String del="split";
//        while(socket==null){
//            try {
//                if(socket==null) {
//                socket = new Socket(host, port);
//                socket.setSoTimeout(100*1000);
//                System.out.println("Connected");
//            }
//                DataInputStream input = new DataInputStream(socket.getInputStream());
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                try {
//                    int next = input.read();
//                    while(next > -1) {
//                        bos.write(next);
//                        next = input.read();
//                        if(input.available() <= 0) {
//                            if(next > -1) {
//                                bos.write(next);
//                            }
//                            String[] segments = bos.toString().split(del);
//                            for(String s : segments) {
//                                Thread out=new Thread(new TCPWorker(s));
//                                out.start();
//                            }
//                            bos = new ByteArrayOutputStream();
//                            next = input.read();
//                        }
//                    }
//                } catch (IOException e) {
//
//                } finally {
//                    try {
//                        bos.close();
//                    } catch (IOException e) {
//
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            socket=null;
//        }
//    }
}


