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
      Socket socket = serversocket.accept();
      handleRequest(socket);
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

      }
    } catch (IOException e) {
      System.out.println("Failed to handle request: " + e.getMessage());
    }
  }
}
