import java.io.*;
import java.net.*;
//0
public class Client {
    //initialising socket and input & output  stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    //constructor to put ip address annd port
    public Client(String address, int port) {
        //establishing a connection
        try {
            socket = new Socket(address, port);
            System.out.println("connected");

            //take input from client socket
            input = new DataInputStream(socket.getInputStream()
                    //new BufferedInputStream(socket.getInputStream())
            );

            //create a output stream
            output = new DataOutputStream(socket.getOutputStream());

            //store the out put
            BufferedReader out = new BufferedReader(new InputStreamReader(System.in));

            //variable to read the message from input
            String msgin = "";
            String msgout = "";

            //keep reading until stop is input
            while (!msgout.equals("stop")) {
                try {
                    //sending message
                    msgout = out.readLine();
                    output.writeUTF(msgout);
                    output.flush();

                    //recieving message
                    msgin = input.readUTF();
                    System.out.println(msgin);

                    //sending message
                    msgout = out.readLine();
                    output.writeUTF(msgout);
                    output.flush();


                } catch (IOException i) {
                    System.out.println(i);
                }
            }

            //close the connection

            input.close();
            out.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
        public static void main(String[] args) {
        Client client = new Client("127.0.0.2",5000);
    }
}

/*//start server and waits for a connection
        try {
            server = new ServerSocket((port));
            System.out.println("Server started");

            System.out.println("waiting for client?....");

            socket = server.accept();
            System.out.println("Client accepted");

            //take input from client socket
            input = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream())
            );



            //read mesage from client until stop is sent
            while (!msgin.equals("stop")){
                try {
                    msgin = input.readUTF();
                    System.out.println(msgin);
                }
                catch (IOException i){
                    System.out.println(i);
                }

            }
            System.out.println("closing connection");

            //close connection
            socket.close();
            input.close();
        }
        catch (IOException i){
            System.out.println(i);
        }*/
