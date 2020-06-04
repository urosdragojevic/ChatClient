import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Client {

    private static final String IP_ADDRESS = "127.0.0.1";
    //    private static final int PORT = 9090;
    private static String name;
    private static int port;

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Client.port = port;
    }

    public static void setName(String name) {
        Client.name = name;
    }

    public static String getName() {
        return name;
    }

    public static void main(String[] args) throws IOException {


        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        String request;

        System.out.print("Enter your name: ");
        setName(keyboard.readLine());
//        out.println(getName());

        System.out.print("Enter port: ");
        setPort(Integer.parseInt(keyboard.readLine()));
//        out.println(port);

        new ChatServerHandler(port);

//        ChatServer.createSocket(port);

        Socket socket = new Socket(IP_ADDRESS, port);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        new ServerConnection(socket, getName());
        System.out.println("Created ServerConnection.");

        while (true) {
            request = keyboard.readLine();
            if (request.equals("quit")) break;
            out.println(request);
        }
        out.close();
        socket.close();
        //System.exit(0);
    }

}

