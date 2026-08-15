package myapp;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * SimpleHttpServer
 */
public class SimpleHttpServer {
  public static void main(String[] args) {
    final int port = 8000;
    try (ServerSocket serversocket = new ServerSocket(port);) {
      serversocket.accept();
    } catch (IOException e) {
      // TODO: handle exception
    }
  }
}
