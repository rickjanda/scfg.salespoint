package scfg.salespoint;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AcceptanceTest {

	private Thread socketListenerThread;
	private SocketListener socketListener;
	private PrintStream printStream;
	
	@Before
	public void setup() throws IOException {
		startListener();
		printStream = startBillingSystem();
	}
	
	@After
	public void teardown() {
		printStream.close();
		stopListener();
	}

	@Test
	public void test() throws Exception {
		printStream.println("123");
		
		Thread.sleep(1000);
		
		assertEquals("1.00 CHF", socketListener.getLastMessage());
	}

	private void startListener() {
		socketListener = new SocketListener();
		socketListenerThread = new Thread(socketListener);
		socketListenerThread.start();
	}

	private PrintStream startBillingSystem() throws IOException {
		ProcessBuilder builder = new ProcessBuilder("java", "scfg.salespoint.Main");
		builder.redirectErrorStream(true);
		File file = new File("target/classes");
		builder.directory(file);
		Process process = builder.start();
		
		OutputStream outputStream = process.getOutputStream();
		final PrintStream printStream = new PrintStream(outputStream, true);
		return printStream;
	}

	private void stopListener() {
		socketListener.stop();
		socketListenerThread.interrupt();
	}

}
