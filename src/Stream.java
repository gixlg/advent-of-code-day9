import java.util.ArrayList;

public class Stream {

	private String characterStream;

	public Stream(String characterStream) {
		this.characterStream = characterStream;

	}

	public void getScore() {
		ArrayList<String> groups = new ArrayList<String>();
		groups.add(characterStream);
		int lv = 1;
		int score = 0;

		do {
			ArrayList<String> newGroups = new ArrayList<String>();
			for (String element : groups) {
				newGroups.addAll(getGroups(element));
			}

			for (String newElement : newGroups) {
				System.out.println(newElement);
			}

			groups = new ArrayList<String>();
			for (String newElement : newGroups) {
				if (newElement.charAt(0) != '{'
						|| newElement.charAt(newElement.length() - 1) != '}')
					throw new RuntimeException("No right start or end symbol");
				int point = lv;
				System.out.println("point: " + point);
				score += point;
				groups.add(newElement.substring(1, newElement.length() - 1));
			}

			lv++;
		} while (groups.size() != 0);
		System.out.println("Score: " + score);
	}

	private boolean insideComment = false;
	private boolean ignoreNext = false;
	private int countOfRemovedGarbage = 0;

	private boolean isToIgnore(char c) {

		if (ignoreNext) {
			ignoreNext = false;
			return true;
		}

		if (c == '<' && !insideComment) {
			insideComment = true;
			return true;
		} else if (c == '>' && insideComment) {
			insideComment = false;
			return true;
		} else if (c == '!') {
			ignoreNext = true;
			return true;
		}

		if (insideComment)
			countOfRemovedGarbage++;
		return insideComment;

	}

	public void printAmmountOfRemovedGarbage() {
		System.out.println("Removed garbage: " + countOfRemovedGarbage);
	}

	private ArrayList<String> getGroups(String input) {
		int open = 0;
		ArrayList<String> groups = new ArrayList<String>();

		String group = "";
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (!isToIgnore(c)) {
				group += c;

				if (c == '{') {
					open++;
				}
				if (c == '}') {
					open--;
					if (open == 0) {
						if (group.startsWith(","))
							group = group.substring(1, group.length());
						groups.add(group);
						group = "";
					}
				}
			}

		}

		return groups;
	}

}
