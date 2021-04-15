package TeamProject;

import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer extends Thread {

	private ServerSocket server;
	private final int port = 9999;
	Vector<ChatHandler> userV = new Vector<>();

	public ChatServer() {
		try {
			server = new ServerSocket(port);
			System.out.println("ChatServer Starting");
			System.out.println("Port: " + port + " waiting");
		} catch (IOException e) {
			System.out.println("ChatServer()에서 예외: " + e);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket sock = server.accept();
				System.out.println(sock.getInetAddress() + " Connect");
				ChatHandler chat = new ChatHandler(sock, userV);
				chat.start();
			} catch (IOException e) {
				System.out.println("ChatSever run()에세 예외: " + e);
			}
		}
	}

	public static void main(String[] args) {
		new ChatServer().start();
	}
}
