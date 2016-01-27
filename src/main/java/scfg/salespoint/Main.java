package scfg.salespoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) throws IOException {
		new Main();
	}

	Main() throws UnknownHostException, IOException {
		try (Socket kkSocket = new Socket("localhost", 6543);
				PrintWriter out = new PrintWriter(kkSocket.getOutputStream(),
						true)) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			String s;
			while ((s = in.readLine()) != null && s.length() != 0) {
				out.println("1.00 CHF");
			}
			// An empty line or Ctrl-Z terminates the program
		}
	}

}
