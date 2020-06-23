import org.apache.commons.lang3.ObjectUtils;

import java.io.*;
import java.net.*;

public class Server {
    //initialising socket and input & output  stream
    private Socket socket1 = null;
    private Socket socket2 = null;
    private ServerSocket server = null;
    private DataInputStream input1 = null;
    private DataInputStream input2 = null;
    private DataOutputStream output1 = null;
    private DataOutputStream output2 = null;

    //constructor with post
    public Server( int port){

        //start server and waits for a connection
        try {
            server = new ServerSocket((port));
            System.out.println("Server started");

            System.out.println("waiting for client?....");

            socket1 = server.accept();
            System.out.println("Client 1 accepted :" + socket1);

            socket2 = server.accept();
            System.out.println("Client 2 accepted :" + socket2);

            //take input from client1 socket
            input1 = new DataInputStream(socket1.getInputStream()//data input stream
                    //new BufferedInputStream(socket1.getInputStream())
            );

            //take input from client2 socket
            input2 = new DataInputStream(socket2.getInputStream()//data input stream
                   // new BufferedInputStream(socket2.getInputStream())
            );

            //create a output stream
            output1 = new DataOutputStream(socket1.getOutputStream());
            output2 = new DataOutputStream(socket2.getOutputStream());

            //store the out put in out
            BufferedReader out = new BufferedReader(new InputStreamReader(System.in));

            String msgin1 = "";
            String msgin2 = "";

            //read mesage from client until stop is sent
            while (!(msgin1.equals("stop") )){
                try {
                    //recieving message
                    msgin1 = input1.readUTF();
                    if (msgin1!="") {
                        System.out.println(msgin1 + "==> from client1");
                        output2.writeUTF(msgin1);
                        output2.flush();
                    }



                        //recieving message
                    msgin2 = input2.readUTF();
                    if (msgin2!="") {
                        System.out.println(msgin2 + "==>from client2");
                        output1.writeUTF(msgin2);
                        output1.flush();
                    }

                }
                catch (IOException i){
                    System.out.println(i);
                }

            }
            System.out.println("closing connection");

            //close connection
            socket1.close();
            socket2.close();
            input1.close();
            input2.close();
        }
        catch (IOException i){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
    }


}
/*
* //establishing a connection
        try {
            socket = new Socket(address , port);
            System.out.println("connected");

            //take input from terminal
            input = new DataInputStream(System.in);

            //sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException e) {
            System.out.println(e);
        }
        //variable to read the message from input
        String msgin = "";
        String msgout = "";

        //keep reading until stop is input
        while(!msgout.equals("stop")){
            try {
                msgout = input.readLine();
                out.writeUTF(msgout);
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        //close the connection
        try {
            input.close();
            out.close();
            socket.close();
        }
        catch (IOException i) {
            System.out.println(i);
        }*/
