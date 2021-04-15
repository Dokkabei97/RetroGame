package TeamProject;

import java.net.*;
import java.io.*;
import java.util.*;

public class ChatHandler extends Thread {

	private Socket sock;
	private Vector<ChatHandler> userV;
	public String userId;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean isStop = false;
	
	public ChatHandler() {
		
	}

	public ChatHandler(Socket sock, Vector<ChatHandler> userV) {
		this.sock = sock;
		this.userV = userV;
		try {
			in = new ObjectInputStream(this.sock.getInputStream());
			out = new ObjectOutputStream(this.sock.getOutputStream());
		} catch (IOException e) {
			System.out.println("ChatHandler()에서 예외: " + e);
		}
	}

	@Override
	public void run() {
		
		try {
			String str = in.readUTF();
			System.out.println(str);
			String[] tokens = str.split("\\|");
			if (tokens == null)
				return;
			int protocol = Integer.parseInt(tokens[0]);
			System.out.println("protocol= " + protocol);
			if (protocol == 100) {
				isStop = false;
				this.userId = tokens[1];

				for (ChatHandler userChat : userV) {
					String info = "100|" + userChat.userId;
					sendMessageTo(info);
				}
				userV.add(this);
				String msg = "100|" + userId;
				sendMessageAll(msg);

				while (!isStop) {
					String cMsg = in.readUTF();
					process(cMsg);
				}
			}
		} catch (IOException e) {
			System.out.println("ChatHandler run()에서 예외: " + e);
		}
	}

	private void process(String cMsg) {
		System.out.println(cMsg);
		String[] tks = cMsg.split("\\|");
		switch (tks[0]) {
		case "200":
			String message = tks[1];
			sendMessageAll("200|" + this.userId + "|" + message);
			break;
		case "900":
			sendMessageAll("900|" + this.userId);
			userV.remove(this);
			closeAll();
			break;
		}
	}

	private synchronized void sendMessageAll(String msg) {
		for (ChatHandler userChat : userV) {
			try {
				userChat.out.writeUTF(msg);
				userChat.out.flush();
			} catch (IOException e) {
				System.out.println("sendMessageAll()에서 예외: " + e);
				userV.remove(userChat);
				break;
			}
		}

	}

	private synchronized void sendMessageTo(String msg) throws IOException {
		out.writeUTF(msg);
		out.flush();
	}

	private void closeAll() {
		isStop = true;
		try {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
			if (sock != null) {
				sock.close();
				sock = null;
			}
		} catch (IOException e) {
			System.out.println("closeAll()에서 예외: " + e);
		}
	}
}
