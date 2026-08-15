package myapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
  public static void main(String[] args) {
    final int port = 8080;
    try (ServerSocket serversocket = new ServerSocket(port);) {
      while (true) {

        Socket socket = serversocket.accept();
        handleRequest(socket);
      }
    } catch (IOException e) {
      System.out.println("Failed to socket: " + e.getMessage());
    }
  }

  private static void handleRequest(Socket socket) {

    try (InputStream inputStream = socket.getInputStream()) {
      OutputStream outputStream = socket.getOutputStream();
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      String line = bufferedReader.readLine();
      String[] parts = line.split(" ");
      String requestMethod = parts[0];
      String path = parts[1];
      if ("GET".equalsIgnoreCase(requestMethod) && "/message".equalsIgnoreCase(path)) {
        writeResponse(outputStream);
      }
    } catch (IOException e) {
      System.out.println("Failed to handle request: " + e.getMessage());
    } finally {
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static void writeResponse(OutputStream outputStream) throws IOException {
    String message = "test message yipee";
    String httpResponse = """
        HTTP/1.1 200 OK
        Content-Type: text/plain
        Content-length: """ + message.length() + "\n\n" + message;
    outputStream.write(httpResponse.getBytes());
    outputStream.flush();
    outputStream.close();
  }
}
