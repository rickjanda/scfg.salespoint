package scfg.salespoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener implements Runnable {

	private String lastMessage;
	private boolean stop;

	@Override
	public void run() {
		String clientSentence;
		try (ServerSocket welcomeSocket = new ServerSocket(6543)){
			while (!stop) {
				Socket connectionSocket = welcomeSocket.accept();
				BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(connectionSocket.getInputStream()));
				clientSentence = inFromClient.readLine();
				System.out.println("Received: " + clientSentence);
				lastMessage = clientSentence;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getLastMessage() {
		return lastMessage;
	}
	
	public void stop () {
		stop = true;
	}
}
