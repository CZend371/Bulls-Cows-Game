import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BCPlayer {

	private String gameWord;

	// The total amount of guesses the user can make.
	private final int MAX_GUESSES = 15;

	// To ask for users input.
	private static Scanner scanner = new Scanner(System.in);

	// a collection to hold all of the guesses.
	private ArrayList<String> guessList = new ArrayList<String>();

	// This is where the words from the text file will be held.
	private ArrayList<String> wordBank = new ArrayList<String>();

	// This will control whether the game continues or ends.
	public boolean gameEnded = false;

	// constructor that will retrieve the words from the text file.
	public BCPlayer(String file_name) {

		try {
			Scanner input = new Scanner(new File(file_name));
			while (input.hasNext()) {
				String word = input.next();
				wordBank.add(word);
			}
			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file was not found!");
		}
		// System.out.println(wordBank);
	}

	// gets a random word based on the length.
	public String getRandomWordByLength(int x) {
		// provides the range according the the arrayList size.
		int max = wordBank.size();
		int min = 0;
		int range = max - min;
		boolean flag = false;

		while (flag == false) {

			int random_number = (int) (Math.random() * range) + min;

			String random_word = wordBank.get(random_number);
			// System.out.println("This is the random word: " + random_word);

			if (random_word.length() == x) {
				flag = true;
				return random_word;
			}
			continue;
		}
		return "Something went wrong!";
	}

	// retrieves the word length from the user
	public int getWordLength() {
		// asks the user how long the word should be.
		System.out.println("What should the length of the word be? (Enter a number)");
		int word_length = scanner.nextInt();
		return word_length;
	}

	// retrieves the bull count
	public int getBulls(String guessed_word) {
		return 0;
	}

	// retrieves the cow count
	public int getCows(String guessed_word) {
		return 0;
	}

	// compares the guessed_word with gameWord
	// and returns the number of bulls if any
	public int calculateBullCount(String guessed_word) {
		int bull_counter = 0;
		guessed_word = guessed_word.toUpperCase();
		// System.out.println(guessed_word);
		try {
			for (int i = 0; i < gameWord.length(); i++) {
				char a = gameWord.charAt(i);
				char b = guessed_word.charAt(i);

				if (a == b) {
					bull_counter++;
					continue;
				}
			}
		} catch (Exception StringIndexOutofBoundsException) {
			System.out.println("The word you guess must be the same length as the number you chose!");
		}
		return bull_counter;
	}

	// compares the guessed_word with gameWord
	// and returns the number of cows if any
	public int calculateCowCount(String guessed_word) {
		int cow_counter = 0;

		// Creates a new string with all uppercase letters.
		guessed_word = guessed_word.toUpperCase();

		try {
			// Will iterate through the game word to compare to the guessed word.
			for (int i = 0; i < gameWord.length(); i++) {
				char a = gameWord.charAt(i);
				int a_index = gameWord.indexOf(a);
				for (int j = 0; j < guessed_word.length(); j++) {
					char b = guessed_word.charAt(j);
					int b_index = guessed_word.indexOf(b);

					// System.out.println(a + "\n" + b);
					if (a == b && a_index != b_index) {
						cow_counter++;
					}
				}
			}
		} catch (Exception StringIndexOutofBoundsException) {
			System.out.println("The word you guess must be the same length as the number you chose!");
		}
		return cow_counter;
	}

	// Starts the game for the user
	public void startGame() {
		System.out.println("------------------------");
		System.out.println("Welcome to Cows and Bulls!");
		System.out.println("You have 15 guesses to figure out the word. Good luck!");
		System.out.println("------------------------");

		// initializes a guess instance.
		Guess guess_x = null;

		// will keep track of the guesses and will be sent to the Guess Class.
		int guess_counter = 0;

		while (gameEnded == false) {
			guess_x = new Guess();

			// Will decide if the random word must be chosen.
			if (guess_counter == 0) {
				gameWord = getRandomWordByLength(getWordLength());
			}
			// This will make sure that the gameWord is not null before continuing.
			if (gameWord != null) {
				// sets the users guess
				guess_x.setWordGuessed();

				// adds the guess to the list.
				guessList.add(guess_x.getWordGuessed());

				// keeps track of guess number
				guess_counter++;
				guess_x.setGuessNumber(guess_counter);

				// calculates the bull count
				guess_x.setBullsCount(calculateBullCount(guess_x.getWordGuessed()));

				// calculates the cow count
				guess_x.setCowsCount(calculateCowCount(guess_x.getWordGuessed()));

				System.out.println("------------------------");
				System.out.println("Game Board: " + guess_x);
				System.out.println("------------------------");
			}
			// Displays a message depending on which condition is met.
			if (guess_x.getBullsCount()== gameWord.length()) {
				System.out.println("You figured it out!");
				gameEnded = true;
			} else if (guess_counter == MAX_GUESSES) {
				System.out.println("You lose!");
				System.out.println("The word was " + gameWord);
				gameEnded = true;
			} else {
				System.out.println("Try again!");
			}
		}
	}

	public static void main(String[] args) {
		// construct a BCPlayer object
		BCPlayer bcp = new BCPlayer("word_list.txt");

		bcp.startGame();

	}

}
