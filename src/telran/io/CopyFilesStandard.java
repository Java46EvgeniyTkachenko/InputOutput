package telran.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Application for copying files based on static method copy of class Files
 * files may be very big (several Gbytes ) 
 * args[0] - source file path 
 * args[1] - destination file path 
 * args[2] - if exists "overwritten" then destination may
 * be overwritten 
 * otherwise may not be Output one of the following: 
 * Files have been copied (<amount of bytes> <time of copying>) 
 * Source file doesn't exist
 * Destination can not be overwritten
 *
 */
public class CopyFilesStandard {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		LocalDateTime timeStart = LocalDateTime.now();		
		CopyFileArguments cfa = new CopyFileArguments();
		String copyFileArguments[];
		File fileSource = null, fileDestination;
		
		try {

			copyFileArguments = cfa.getArguments(args);
			fileSource = new File(copyFileArguments[0]);
			Path destinationFile = Path.of(copyFileArguments[1]);
			Path sourceFile = Path.of(copyFileArguments[0]);
			OutputStream os = new FileOutputStream(destinationFile.toFile());
			Files.copy(sourceFile, os);
			long bytesFale = fileSource.length();
			LocalDateTime timeFinish = LocalDateTime.now();
			long timeCopy = ChronoUnit.MILLIS.between(timeStart, timeFinish);
			System.out.printf("Copied %d bytes for %d milliseconds", bytesFale, timeCopy);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
