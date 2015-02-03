
package edu.afeka.document;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Document {

	private String current_word;
	private Scanner scanner;

	public void open(String path) throws FileNotFoundException {

		scanner = new Scanner(new File(path));
		scanner.useDelimiter("(\\s|[,.:;?\"])+");
	}

	public void close() {

		scanner.close(); 
	}

	public String get_word() { return current_word; }
	public boolean has_more_words() { return scanner.hasNext(); }
	public void next_word() { current_word = scanner.next(); }
}
