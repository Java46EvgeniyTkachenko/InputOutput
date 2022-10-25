package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class LoggerTests {

	
Handler handler;
Logger logger;
BufferedReader reader;
@BeforeEach
void setUp() throws FileNotFoundException {
	File file = new File("loggerFile.txt");
	if (file.exists()) {
		file.delete();
	}
	handler = new SimpleStreamHandler(new PrintStream(file));
	logger = new Logger(handler, "test-logger");
	reader = new BufferedReader(new FileReader(file));
}
	
	private void message() {
		logger.error("error text");
		logger.warn("warn text");
		logger.info("info text");
		logger.debug("debug text");
		logger.trace("trace text");
	}
	@Test
	void errorTest() {
		logger.setLevel(Level.ERROR);
		message();
		String [] levels = {Level.ERROR.toString()};
		runTestContent(levels);
		
	}
	@Test
	void warnTest() {
		logger.setLevel(Level.WARN);
		message();
		String [] levels = {Level.ERROR.toString(), Level.WARN.toString()};
		runTestContent(levels);
		
	}
	@Test
	void infoTest() {
		logger.setLevel(Level.INFO);
		message();
		String [] levels = {Level.ERROR.toString(), Level.WARN.toString(),
				Level.INFO.toString()};
		runTestContent(levels);
		
	}
	@Test
	void debugTest() {
		logger.setLevel(Level.DEBUG);
		message();
		String [] levels = {Level.ERROR.toString(), Level.WARN.toString(),
				Level.INFO.toString(), Level.DEBUG.toString()};
		runTestContent(levels);
		
	}
	@Test
	void traceTest() {
		logger.setLevel(Level.TRACE);
		message();
		String [] levels = {Level.ERROR.toString(), Level.WARN.toString(),
				Level.INFO.toString(), Level.DEBUG.toString(), Level.TRACE.toString()};
		runTestContent(levels);
		
	}
	@Test
	void consoleTest() {
		handler = new SimpleStreamHandler(System.out);
		logger = new Logger(handler, "logger-console-test");
		
		System.out.println("***********************Mesage logger to console******************");
		
		message();
	}

	private void runTestContent(String[] levels) {
		List<String> records =  reader.lines().collect(Collectors.toCollection(ArrayList::new));
		assertEquals(levels.length, records.size());
		for(int i = 0; i < levels.length; i++) {
			assertTrue(records.get(i).contains(levels[i]));
		}
		
	}

}
