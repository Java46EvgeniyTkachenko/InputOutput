package telran.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.io.*;
class FileTests {
	private static final String FILL_SYMBOL = " ";
	private static final  int N_SYMBOLS_PER_LEVEL = 2;
	File file;
	@BeforeEach
	void setUp() {
		file = new File("dir1/dir2");
		file.delete();
	}

	@Test
	void newDirectoryTest() {
		
		assertFalse(file.exists());
		file.mkdirs();
		assertTrue(file.exists());
	}
	@Test
	void printDirectoryContent() {
	
	//	printDirectory("..", 1);
	//	printDirectory("..", 2);
		printDirectory("..", 3);
	//	printDirectory("..", -1);
	}

	/**
	 * 
	 * @param pathName - name of path to initial directory
	 * @param level - level of sub-directories printing
	 * example, level = 1 printing only first level of the initial directory content
	 *          level = 2 content + sub-directories content
	 *          ''''''''
	 *          level = -1 printing all levels
	 */
	private void printDirectory(String pathName, int level) {
		/*
		 * <dir> type=dir 
		 * 		<dir> type=dir 
		 * 			<file> type=file 
		 * 			<dir> type=dir ...
		 */
		if (level < 0) {
			level = 10;
		}
		File path = new File(pathName);
		File[] list = path.listFiles();

		if (list.length == 0) {
			return;
		}

		for (int i = 0; i < list.length; i++) {
			if (list[i].isFile()) {				
				String tempPath = "<file> type = " + list[i].getName();
				System.out.printf("%s%s\n", FILL_SYMBOL.repeat((10 - level) * N_SYMBOLS_PER_LEVEL), tempPath);
			} else if (list[i].isDirectory()) {
				String tempPath = "<dir> type = " + list[i].getName();
				System.out.printf("%s%s\n", FILL_SYMBOL.repeat((10 - level) * N_SYMBOLS_PER_LEVEL), tempPath);				
				if (level > 1) {
					printDirectory(list[i].getPath(), level - 1);

				}
			}
		}
	}
}
