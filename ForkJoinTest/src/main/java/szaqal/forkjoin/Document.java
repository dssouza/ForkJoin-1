package szaqal.forkjoin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author malczyk.pawel@gmail.com
 * 
 */
public class Document {

	private final List<String> lines;

	List<String> getLines() {
		return this.lines;
	}

	Document(List<String> lines) {
		this.lines = lines;
	}

	static Document fromFile() throws IOException {
		List<String> lines = new LinkedList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(
				new File("/home/malczyk/Development/ForkJoin/ForkJoin/ForkJoinTest/src/main/java/szaqal/forkjoin/firstnames_female.txt")))) {
			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
		}
		return new Document(lines);
	}
}
