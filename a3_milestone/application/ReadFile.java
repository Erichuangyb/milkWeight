/**
 * This file contains the ReadFile class to run the program
 */
package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.stage.Stage;
/**
 * This class creates objects to read file
 * @author Yibin Huang
 *
 */
public class ReadFile {

	/**
	 * Scan a file and add to dataStorage
	 * @param file
	 * @param dataStorgae
	 * @throws IOException
	 */
	public ReadFile(File file, DataStorage dataStorgae) throws IOException {
		try {
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				//split the line scanned into array
				String[] fileInfo = line.split(",");
				if (fileInfo[2].equals("weight"))
					continue;
				if (dataStorgae.contains(fileInfo[1])) {
					dataStorgae.get(fileInfo[1]).add(Integer.parseInt(fileInfo[2]), fileInfo[0]);
				}
				else {
					Farm newFarm = new Farm();
					newFarm.add(Integer.parseInt(fileInfo[2]), fileInfo[0]);
					dataStorgae.insert(fileInfo[1], newFarm);
				}
				// check if the file uploaded has errors
				if (!fileInfo[1].contains("Farm ")) {
					throw new IllegalArgumentException();
				}
				for (char ch : fileInfo[2].toCharArray()) {
					if (Character.isLetter(ch)) {
						throw new IllegalArgumentException();
					}
				}
			}
		} catch (IllegalArgumentException e2) {
			throw new IllegalArgumentException();
		} catch (Exception e) {
			System.out.println("Invalid File input");
		}

	}
}
