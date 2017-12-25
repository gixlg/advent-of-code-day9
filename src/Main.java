import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		String characterStream = readFile("input.txt");

		Stream stream = new Stream(characterStream);
		stream.getScore();
		stream.printAmmountOfRemovedGarbage();
	}

	public static String readFile(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
