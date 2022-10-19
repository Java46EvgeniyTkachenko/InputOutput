package telran.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Application for copying files based on FileInputStream and FileOutputStream
 * files may be very big (several Gbytes ) 
 * args[0] - source file path
 * args[1] - destination file path
 * args[2] - if exists "overwritten" then destination may be overwritten otherwise may not be
 * Output one of the following:
 * Files have been copied (<amount of bytes> <time of copying>)
 * Source file doesn't exist
 * Destination can not be overwritten
 *
 */
public class CopyFilesInputOutputStreams {

	public static void main(String[] args) {
		LocalDateTime timeStart = LocalDateTime.now();
		CopyFileArguments cfa = new CopyFileArguments();
		String copyFileArguments[];
		InputStream input = null;
		OutputStream output = null;

		try {
			copyFileArguments = cfa.getArguments(args);
			File fileSource = new File(copyFileArguments[0]);
			File fileDestination = new File(copyFileArguments[1]);
			input = new FileInputStream(fileSource);
			output = new FileOutputStream(fileDestination);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}

			LocalDateTime timeFinish = LocalDateTime.now();
			long bytesFale = fileSource.length();

			long timeCopy = ChronoUnit.MILLIS.between(timeStart, timeFinish);
			System.out.printf("Copied %d bytes for %d milliseconds", bytesFale, timeCopy);

			input.close();
			output.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
