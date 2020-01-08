import java.io.*;

public class Save {

	private static PrintWriter fileOut;

	// create save file
	public void createOutputFile(String fileName) {
		try {
			fileOut = new PrintWriter(new BufferedWriter(new FileWriter(
					fileName)));
		} catch (IOException e) {
			System.out.println("Save " + fileName + " cannot be created");
		}
	}

	// adds a new line of info
	public void addInfo(String text) {
		fileOut.println(text);
	}

	// closes file
	public void closeOutputFile() {
		fileOut.close();
	}

	private BufferedReader fileIn;

	// opens save file
	public void openInputFile(String fileName) {
		try {
			fileIn = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Save " + fileName + " cannot be created");
		}
	}

	// reads new line
	public String getInfo() throws IOException {
		try {
			return fileIn.readLine();
		} catch (IOException e) {
		}

		return null;
	}

	// closes file
	public void closeInputFile() throws IOException {
		fileIn.close();
	}

}
