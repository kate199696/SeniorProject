/* This problem takes phrases from a file, then takes each phrase and adds it 
 * to a array of structs. Then the program sorts the phrases by the number
 * of unique letters in the phrase
 * 
 * By Kaitlyn Vinson
 */

#include <iostream>
#include <string>
#include <cctype>
#include <fstream>
#include <iomanip>
#include <cstdlib>
#include <cmath>
#include <ctime>
using namespace std;

// This struct type contains the text phrase, how many unique letters, and
// if it is used already or not
struct Phrase
{
	string text;
	string::size_type guessesRequired;
	bool isUnused;
};

//this is used to draw the figure
void drawGallows(int missCount);

//outputs the phrase as underscores and letters, depending on if they were
//correctly guessed
string phraseWithBlanks(const string &phrase, const string &correctGuesses);

// gets the difficulty
int getDifficultyLevel();

// gets the index of the random phrase
int randNum(const int difficulty, int count);

// these constants are used to set the rest of the areas in the file that need
// the file name or the maximum amount of phrases
const string FILE_NAME = "phrases.txt";
const int MAX_PHRASE_COUNT = 100;

// this function determines if the character in string is a letter
bool isLetter(char letter);

// this function counts the amount of types of letters in the phrase
int uniqueLetterCount(const string &phrase);

// takes the info from the file and adds the info to the array of structs
bool loadPhrasesFromFile(const string &FILE_NAME, Phrase phrase[], int &length);

// this function sorts the phrases by the unique letters
void sortPhrases(Phrase phrase[], int length);

// prints the info in the array
void printPhrases(const Phrase phrase[], int length);

// swaps two values
void swap(Phrase &phrase1, Phrase &phrase2);

// determines where the smallest index is
int smallestIndex(const Phrase phrase[], int length, int startIndex);

//runs the game once
void runGame(Phrase &phrase);

//makes sure the letter is either a yes or a no
bool isYesOrNo(char letter);

//is true when the letter is a 'y' or a 'Y'
bool isContinue(char letter);

//takes a string and converts all the letters to lower case
string toLower(const string &text);

int main()
{
	// declaring the variables
	Phrase phrases[MAX_PHRASE_COUNT];
	int phraseCount = MAX_PHRASE_COUNT;
	int difficulty;
	int indexOfRandPhrase;
	int countOfTimesUsed;
	char yesOrNo;

	string test;

	//used to get random number
	unsigned int seed = static_cast<unsigned int>(time(nullptr));

	//seeds the number
	srand(seed);

	// getting the info from the file
	if (!loadPhrasesFromFile(FILE_NAME, phrases, phraseCount))
	{
		return 1;
	}

	//sorting the info by the unique letter count
	sortPhrases(phrases, phraseCount);

	//repeats the game when the user says yes
	do
	{
		//gets the difficulty
		difficulty = getDifficultyLevel();

		//formating
		cout << endl;

		//gets the random index, repeats until finds an unused one
		do
		{
			indexOfRandPhrase = randNum(difficulty, phraseCount);
		} while (!phrases[indexOfRandPhrase].isUnused);

		//runs the game with the phrase at the random index
		runGame(phrases[indexOfRandPhrase]);

		//sets the phrase to used
		phrases[indexOfRandPhrase].isUnused = false;

		//increases the number of times used
		++countOfTimesUsed;

		//repeats until the user inputs y or n
		do
		{
			//gets the character
			cout << endl
				 << "Would you like to play again? (y/n): ";
			cin >> yesOrNo;
		} while (!isYesOrNo(yesOrNo) && countOfTimesUsed < (phraseCount / 3));

	} while (countOfTimesUsed < phraseCount && isContinue(yesOrNo));

	return 0;
}

bool isLetter(char letter)
{
	// determines if the character is a letter
	if ((letter >= 'A' && letter <= 'Z') || (letter >= 'a' && letter <= 'z'))
		return true;

	// returns false if it is not a letter
	return false;
}

bool isYesOrNo(char letter)
{
	//if the letter is a y or n, returns true
	switch (letter)
	{
	case 'y':
	case 'Y':
	case 'n':
	case 'N':
		return true;
	default:
		return false;
	}
}

bool isContinue(char letter)
{
	//if the letter is a y returns true to continue
	switch (letter)
	{
	case 'y':
	case 'Y':
		return true;

		//returns false to not continue
	case 'n':
	case 'N':
		return false;
	}
}

int uniqueLetterCount(const string &phrase)
{
	// declaring a string and char to add to
	string uniqueLetters = "";
	char current;

	// loops through the phrase
	for (string::size_type index = 0; index < phrase.length(); ++index)
	{
		// makes the character into a lowercase letter
		current = tolower(phrase[index]);

		// checks to see if the char is already found
		if (uniqueLetters.find(current) == string::npos && isLetter(current))
		{
			// adds the current char if it is not in the phrase already
			uniqueLetters += current;
		}
	}

	// returns the amount of unique letters
	return uniqueLetters.length();
}

bool loadPhrasesFromFile(const string &FILE_NAME, Phrase phrase[], int &length)
{
	// decares the ifstream
	ifstream inData;

	// creates the index
	int index;

	// opens the file
	inData.open(FILE_NAME);

	if (!inData.is_open())
	{
		cout << "Unable to open " << FILE_NAME;
		return false;
	}

	//loops through the file and gets the phrase
	for (index = 0; index < length && !inData.eof(); ++index)
	{
		// gets the phrase
		getline(inData, phrase[index].text);

		// sets the guessesRequired to the amount of unique letters
		phrase[index].guessesRequired = uniqueLetterCount(phrase[index].text);

		// shows that the phrase is now used
		phrase[index].isUnused = true;
	}

	// sets the length to the number of phrases in the file
	length = index;

	// closes the file
	inData.close();

	return true;
}

void swap(Phrase &phrase1, Phrase &phrase2)
{
	// declares a temp as the first phrase
	Phrase temp = phrase1;

	// switches the two phrases
	phrase1 = phrase2;
	phrase2 = temp;
}

int smallestIndex(const Phrase phrase[], int length, int startIndex)
{
	// creates a first index to start at
	int minIndex = startIndex;

	// loops through to find where the lowest guessesRequired is
	for (int index = startIndex + 1; index < length; ++index)
	{
		// if the first is greater than the second, then make index into the
		// new minimum
		if (phrase[minIndex].guessesRequired > phrase[index].guessesRequired)
		{
			minIndex = index;
		}
	}

	// returns the location of the smallest number
	return minIndex;
}

void sortPhrases(Phrase phrase[], int length)
{
	// creates a min index to assign
	int minIndex;

	// loops through the array to sort the phrases by their uniqueLetterCount
	for (int firstUnsortedIndex = 0; firstUnsortedIndex < length;
		 ++firstUnsortedIndex)
	{
		// starts at the first phrase and looks for the minimum
		minIndex = smallestIndex(phrase, length, firstUnsortedIndex);

		// if it is not already swapped, swap the smallest with the biggest
		if (firstUnsortedIndex != minIndex)
			swap(phrase[firstUnsortedIndex], phrase[minIndex]);
	}
}

void printPhrases(const Phrase phrase[], int length)
{
	// loops through the array to print
	for (int index = 0; index < length; ++index)
	{
		// outputs the amount of guesses required, the phrase and if it is used
		cout << left << setw(4) << phrase[index].guessesRequired << setw(50)
			 << phrase[index].text << setw(10);

		// if used, output used, if unused, output unused
		if (phrase[index].isUnused)
			cout << setw(10) << "unused" << endl;
		else
			cout << setw(10) << "used" << endl;
	}
}

int getDifficultyLevel()
{
	//declares the integer to be returned
	int difficulty;
	char difficultyChar;

	//gets the difficulty
	cout << "Enter a Difficulty level; (1) Easy, (2) Medium, (3) Hard: ";
	cin >> difficultyChar;
	difficulty = difficultyChar - '0';

	//if the input is not from 1 to 3 asks until it is
	while (difficulty < 1 || difficulty > 3)
	{
		cin.ignore(INT_MAX, '\n');
		cout << difficultyChar << " is not a valid character, enter a new one: ";
		cin >> difficultyChar;
		difficulty = difficultyChar - '0';
	}

	//returns the diffuculty integer
	return difficulty;
}

void drawGallows(int missCount)
{
	// Output the top of the gallows
	cout
		<< "   +----+     \n"
		<< "   |    |     \n";

	// Output the stand and body
	switch (missCount)
	{
	case 0:
		cout
			<< "   |       \n"
			<< "   |       \n"
			<< "   |       \n"
			<< "   |       \n";
		break;

	case 1:
		cout
			<< "   |    O  \n"
			<< "   |    |  \n"
			<< "   |       \n"
			<< "   |       \n";
		break;

	case 2:
		cout
			<< "   |    O  \n"
			<< "   |   /|  \n"
			<< "   |       \n"
			<< "   |       \n";
		break;

	case 3:
		// The \\ will translate as '\', because it is a special char
		cout
			<< "   |    O   \n"
			<< "   |   /|\\ \n"
			<< "   |        \n"
			<< "   |        \n";
		break;

	case 4:
		cout
			<< "   |    O   \n"
			<< "   |   /|\\ \n"
			<< "   |     \\ \n"
			<< "   |        \n";
		break;

	default:
		cout
			<< "   |    O    \n"
			<< "   |   /|\\  \n"
			<< "   |   / \\  \n"
			<< "   |You're Dead\n";
	}

	// Output the base
	cout << "  =============\n"
		 << endl;
}

int randNum(const int difficulty, int count)
{
	//declares variables to use to find the range
	int range = round(count / 3.0);
	int min = (difficulty - 1) * range;

	if (difficulty == 3)
	{
		range = (count - min);
	}

	//returns the range that the phrase can be in
	return rand() % range + min;
}

string phraseWithBlanks(const string &phrase, const string &correctGuesses)
{
	//declares variables
	char current;
	string output;

	//repeats through the whole phrase
	for (string::size_type index = 0;
		 index < phrase.length(); ++index)
	{
		// lowers the current index
		current = tolower(phrase[index]);

		//if the current is is correctGuesses or is not a letter add to output
		if (!isLetter(current) || correctGuesses.find(current) != string::npos)
		{
			output += " ";
			output += current;
		}
		else
		{
			output += " _";
		}
	}

	return output;
}
void runGame(Phrase &phrase)
{
	// declares variables
	string incorrectGuesses;
	string correctGuesses;
	string lowerText = toLower(phrase.text);
	char guess;
	string result;

	do
	{
		//draws the gallows
		drawGallows(incorrectGuesses.length());

		//gets the line with underscores and letters
		result = phraseWithBlanks(phrase.text, correctGuesses);

		//format and output result
		cout << endl
			 << result << endl
			 << endl;

		//gets the guess
		cout << "Enter a guess: ";
		cin >> guess;

		//sets the guess to the lower value
		guess = tolower(guess);

		//if not a letter, guess again
		if (!isLetter(guess))
		{
			cout << guess << " is not a valid guess. Please enter a letter.";
			cin >> guess;
		}
		//if in either correctGuesses or incorrectGuesses, move on to guess again
		else if (correctGuesses.find(guess) != string::npos || incorrectGuesses.find(guess) != string::npos)
		{
			cout << "You have already guessed an " << guess << endl;
		}
		//if in the lowercase phrase add to correctGuesses
		else if (lowerText.find(guess) != string::npos)
		{
			cout << "Good guess" << endl;
			correctGuesses += guess;
		}
		//if not in phrase add to incorrectGuesses
		else
		{
			cout << "Sorry, bad guess" << endl;
			incorrectGuesses += guess;
		}

		//outputs the incorrect so far
		cout << "Your guesses: " << incorrectGuesses << endl;

		//makes sure the guess is less than incorrect and guessesRequired
	} while (incorrectGuesses.length() < 5 && correctGuesses.length() < phrase.guessesRequired);

	//if not able to get correct, output the actual phrase, you died
	if (incorrectGuesses.length() >= 5)
	{
		cout << "You're Dead! The phrase was: ";
		cout << phrase.text;
	}
	else
	{
		//you won the game
		cout << "You Win!" << endl;
		cout << "You guessed the phrase: " << phrase.text;
	}
}

string toLower(const string &text)
{
	//creates a string
	string newText;

	//goes through whole string and makes lowercase
	for (string::size_type index = 0; index < text.length(); ++index)
	{
		newText += tolower(text[index]);
	}

	//returns lowercase string
	return newText;
}