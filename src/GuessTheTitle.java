import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.Set;
import java.util.HashSet;

public class GuessTheTitle {

    public static void main(String[] args) throws FileNotFoundException {

        Set<String> previousGuesses = new HashSet<>();     // Used to store the previous guesses of the user
        File file = new File("movieTitles.txt"); // Setting the scanner for the file
        Scanner fileScanner = new Scanner(file);

        // Choosing a random number and casting it to an integer between 1 and the number of movies
        int nrMovies=fileScanner.nextInt();
        int movieRand = (int) (Math.random()*nrMovies)+1;

        // Choosing the movie using the random number from the text file
        while(movieRand!=0)
        {
            fileScanner.nextLine();
            movieRand--;
        }

        //Reading the title and making all the characters into lower caps
        String movieTitle=fileScanner.nextLine();
        movieTitle.toLowerCase();

        // Creating a string of chars of the length of movieTitle but filled with '-' and another string of chars to be able to compare the letters
        char [] movie=movieTitle.toCharArray();
        char [] movieToGuess=movieTitle.toCharArray();
        for(int i=0;i<movieTitle.length();i++)
            movieToGuess[i]='-';

        System.out.println("\nYour movie title to guess : ");
        int points = 10 ;   // Setting the maximum number of mistakes to 10
        boolean guessed,repeated;  // Declaring two booleans to keep track of the input from the user
        do{
            guessed=false;
            repeated=false;
            System.out.println(movieToGuess);
            System.out.println("You have " + points + " guesses left.Please enter a lower case letter : ");
            Scanner keyScanner = new Scanner(System.in);

            // Reading the input char into a string so I can manipulate it with the hashSet
            String tchar= keyScanner.nextLine().toLowerCase().substring(0, 1);
            char titleChar = tchar.charAt(0);  // Converting it into a char

            // Checking if the user guessed the letter already and if so printing the previous guesses
            if (previousGuesses.contains(tchar)) {
                System.out.println("You already guessed this letter! Your previous guesses were: ");
                System.out.println(previousGuesses.stream().reduce("", String::concat));
                repeated=true;
                }
            previousGuesses.add(tchar); // If the letter was not guessed we add it to the hashSet

            // Checking if the letter is in the title
            if(!repeated) {
                for (int index = 0; index < movieTitle.length(); index++)
                    if (titleChar == movie[index]) {
                        movieToGuess[index] = titleChar;
                        guessed = true;
                    }
            }
        // If there are still guesses left and the movie was already guessed the user wom
        if(points>0 && String.valueOf(movieToGuess).equals(movieTitle)){
            System.out.println("Congrats ,you won!");
            break;}
        // If the letter was not repeated and it's not in the title either the user loses a point
        if(!guessed && !repeated)
            points--;
        // If the user has no more points left he loses
        if(points==0)
            System.out.println("You lost! The movie title was : "+movieTitle);
        }while(points!=0);

    }
}
