import java.util.Scanner;

public class Guess {
	// will display the amount of guesses taken.
	int guessNumber = 0;
	
	//holds the word that is guessed by the user.
	String wordGuessed;
	
	//will display the bulls counter.
	int bullsCount;
	//will display the cows counter.
	int cowsCount;

	public Guess() {

	}

	public Guess(int guessNumber, String wordGuessed, int bullsCount, int cowsCount) {
		super();
		this.guessNumber = guessNumber;
		this.wordGuessed = wordGuessed;
		this.bullsCount = bullsCount;
		this.cowsCount = cowsCount;
	}

	// To ask for users input.
	private static Scanner scanner = new Scanner(System.in);

	public int getGuessNumber() {
		return guessNumber;
	}

	public void setGuessNumber(int guessNumber) {
		this.guessNumber = guessNumber;
	}

	public String getWordGuessed() {
		return wordGuessed;
	}
	
	//asks the user for their guess.
	public void setWordGuessed() {
		// ask the user for the guess (guessed_word)
		System.out.println("Please enter your guess.");
		this.wordGuessed = scanner.nextLine();
	}

	public int getBullsCount() {
		return bullsCount;
	}

	public void setBullsCount(int bullsCount) {
		this.bullsCount = bullsCount;
	}

	public int getCowsCount() {
		return cowsCount;
	}

	public void setCowsCount(int cowsCount) {
		this.cowsCount = cowsCount;
	}

	public String toString() {
		String temp = "\nNumber of Guesses = " + guessNumber + "\nYour Guess = " + wordGuessed + "\nBulls = "
				+ bullsCount + "\nCows = " + cowsCount;

		return temp;
	}
}
