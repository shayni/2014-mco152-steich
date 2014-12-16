package teich.triangle;

public class Triangle {

	private int height;

	public Triangle(int height) {
		this.height = height;
	}

	public String toString() {
		StringBuilder build = new StringBuilder();
		int numSpaces = height - 1;
		int spaces = 0;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < numSpaces; j++) {
				build.append(" ");
			}
			build.append("*");
			if (i != 0 && i != height - 1) {
				for (int k = 0; k < spaces; k++) {
					build.append(" ");
				}
				build.append("*");
			}
			numSpaces--;
			spaces += 2;
			if (i == height - 1) {
				int stars = height * 2 - 2;
				for (int s = 0; s < stars; s++) {
					build.append("*");
				}
				return build.toString();
			}
			build.append("\n");
		}

		return build.toString();
	}

}
