import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {

    public HTTPServer() {}

    public static void main(String[] args) throws IOException {

        // IP Addresses will be discussed in detail in lecture 4
        String IPAddressString = "127.0.0.1";
        InetAddress host = InetAddress.getByName(IPAddressString);

        // Port numbers will be discussed in detail in lecture 5
        int port = 8080;

        // The server side is slightly more complex
        // First we have to create a ServerSocket
        System.out.println("Opening the server socket on port " + port);
        ServerSocket serverSocket = new ServerSocket(port);

        // The ServerSocket listens and then creates as Socket object
        // for each incoming connection
        System.out.println("Server waiting for client...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected!");

        // Like files, we use readers and writers for convenience
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Writer writer = new OutputStreamWriter(clientSocket.getOutputStream());

        // We can read what the client has said
        // Parse the HTTP request
        String request = reader.readLine();
        String parts[] = request.split(" ");
        String method = parts[0];

        if (method.equals("GET")) {
            String path = parts[1];

            System.out.println("GETting the file " + path);

            // Read the headers
            do {
                String header = reader.readLine();
                if (header.equals("")) break;
                // Could split the headers using ":" and save them in a map
            } while (true);

            if (path.equals("/") || path.equals("/index.html")) {
                String htmlString = "<html>\r\n<head>\r\n<title>Hello World</title>\r\n<body>\r\n<h1>HELLO WORLD!</h1>\r\n</body>\r\n</html>\r\n";
                String simpleHTML  = """
                        <!DOCTYPE html>\r
                        <html lang="en">\r
                        <head>\r
                          <meta charset="UTF-8">\r
                          <meta name="viewport" content="width=device-width, initial-scale=1.0">\r
                          <title>CSS3 Loading Animation</title>\r
                          <style>\r
                            @import "compass/css3";\r
                            $speed: 2.5s;\r
                            * {\r
                              margin: 0px;\r
                              padding: 0px;\r
                              border: 0px;\r
                            }\r
                            html, body {\r
                              min-height: 100%;\r
                            }\r
                            body {\r
                              background: radial-gradient(#eee,#444);\r
                            }\r
                            .loader {\r
                              position: absolute;\r
                              top: 0px;\r
                              bottom: 0px;\r
                              left: 0px;\r
                              right: 0px;\r
                              margin: auto;\r
                              width: 175px;\r
                              height: 100px;\r
                            }\r
                            .loader span {\r
                              display: block;\r
                              background: #ccc;\r
                              width: 7px;\r
                              height: 10%;\r
                              border-radius: 14px;\r
                              margin-right: 5px;\r
                              float: left;\r
                              margin-top: 25%;\r
                            }\r
                            .loader span:last-child {\r
                              margin-right: 0px;\r
                            }\r
                            .loader span:nth-child(1) {\r
                              animation: load 2.5s 1.4s infinite linear;\r
                            }\r
                            .loader span:nth-child(2) {\r
                              animation: load 2.5s 1.2s infinite linear;\r
                            }\r
                            .loader span:nth-child(3) {\r
                              animation: load 2.5s 1s infinite linear;\r
                            }\r
                            .loader span:nth-child(4) {\r
                              animation: load 2.5s 0.8s infinite linear;\r
                            }\r
                            .loader span:nth-child(5) {\r
                              animation: load 2.5s 0.6s infinite linear;\r
                            }\r
                            .loader span:nth-child(6) {\r
                              animation: load 2.5s 0.4s infinite linear;\r
                            }\r
                            .loader span:nth-child(7) {\r
                              animation: load 2.5s 0.2s infinite linear;\r
                            }\r
                            .loader span:nth-child(8) {\r
                              animation: load 2.5s 0s infinite linear;\r
                            }\r
                            .loader span:nth-child(9) {\r
                              animation: load 2.5s 0.2s infinite linear;\r
                            }\r
                            .loader span:nth-child(10) {\r
                              animation: load 2.5s 0.4s infinite linear;\r
                            }\r
                            .loader span:nth-child(11) {\r
                              animation: load 2.5s 0.6s infinite linear;\r
                            }\r
                            .loader span:nth-child(12) {\r
                              animation: load 2.5s 0.8s infinite linear;\r
                            }\r
                            .loader span:nth-child(13) {\r
                              animation: load 2.5s 1s infinite linear;\r
                            }\r
                            .loader span:nth-child(14) {\r
                              animation: load 2.5s 1.2s infinite linear;\r
                            }\r
                            .loader span:nth-child(15) {\r
                              animation: load 2.5s 1.4s infinite linear;\r
                            }\r
                            @keyframes load {\r
                              0% {\r
                                background: #ccc;\r
                                margin-top: 25%;\r
                                height: 10%;\r
                              }\r
                              50% {\r
                                background: #444;\r
                                height: 100%;\r
                                margin-top: 0%;\r
                              }\r
                              100% {\r
                                background: #ccc;\r
                                height: 10%;\r
                                margin-top: 25%;\r
                              }\r
                            }\r
                          </style>\r
                        </head>\r
                        <body>\r
                          <div class="loader">\r
                            <span></span>\r
                            <span></span>\r
                            <span></span>\r
                            <span></span>\r
                            <span></span>\r
                            <span></span>\r
                            <span></span>\r
                            <span></span>\r
                            <span></span>\r
                            <span></span>\r
                            <span></span>\r
                            <span></span>\r
                            <span></span>\r
                            <span></span>\r
                            <span></span>\r
                          </div>\r
                        </body>\r
                        </html>\r
                        """;
                writer.write("HTTP/1.1 200 OK\r\n");
                writer.write("Content-Type: text/html\r\n");
                writer.write("Content-Length: " + simpleHTML.length() + "\r\n");
                writer.write("\r\n");
                writer.write(simpleHTML);

                writer.flush();

            } else {
                String errorPage = "<html>\r\n<head>\r\n<title>Not Found</title>\r\n<body>\r\n<h1>File not found :-(</h1>\r\n</body>\r\n</html>\r\n";

                writer.write("HTTP/1.1 404 Not Found\r\n");
                writer.write("Content-Type: text/html\r\n");
                writer.write("Content-Length: " + errorPage.length() + "\r\n");
                writer.write("\r\n");
                writer.write(errorPage);

                writer.flush();
            }
        }  // Could implement other methods here

        clientSocket.close();
    }
}
