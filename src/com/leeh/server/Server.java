package com.leeh.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket server = null;
	private boolean isRunning;

	public Server() throws IOException {
		this(6393);
	}

	public Server(int port) throws IOException {
		server = new ServerSocket(port);
	}

	public void start() throws IOException {
		this.isRunning = true;
		this.receive();
	}

	private void receive() throws IOException {
		int i = 0;
		while (isRunning) {
			Socket client = server.accept();
			System.out.println(i + "============================");
			Dispatch dispatch = new Dispatch(client);
			new Thread(dispatch).start();
			System.out.println(i++ + "============================");
		}
	}

	public void stop() {
		this.isRunning = false;
	}

	public static void main(String[] args) {

		try {
			Server s = new Server();
			s.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
