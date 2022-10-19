package telran.io;

import java.io.File;
import java.util.NoSuchElementException;

public class CopyFileArguments {

	public String[] getArguments(String[] args) throws Exception {
		try {
			if (args.length < 1) {
				throw new Exception("Source and destination path file doesn't exist!");
			}

			File file;
			file = new File(args[0]);
			if (!file.exists()) {
				throw new Exception("Source file doesn't exist!");
			}

			if (args.length < 2) {
				throw new Exception("Path destination file doesn't exist!");
			}
			file = new File(args[1]);
			if (!file.getParentFile().exists()) {

				throw new Exception("Destination " + file.getParentFile() + " has non-existed directory in the path");
			}

			if (file.exists() && !args[2].toLowerCase().equals("overwritten")) {
				throw new Exception("Destination " + file + " cannot be overwritten");
			}

			if (args.length > 3) {
				throw new Exception("Please input arguments [source file path, destination file path, overwritten]");
			}

			return args;

		} catch (NoSuchElementException e) {
			throw new Exception("Uknown error!");
		}
	}

}
