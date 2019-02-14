package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //Declaring a new Game object and initializing it
        Game guessGame = new Game();
        //set the filename to  movie file containing movie list
        guessGame.setFileName("Movies.txt");
        //declare and initialize variable to hold the picked movie
        String pickedMovie = guessGame.getPickedMovie();
        // declare a variable to hold replaced movie name with dashes
        String replacedMovie;
        //declaring and initializing int variable to hold total wrong numbers of guessing
        int totalWrongLetters = guessGame.getWrongLettersCount();
        //declaring variable to hold the letter guessed by user
        char guessUserLetter ;
        // catching and handling error occurs if file containing movie list is not found
        try {
            pickedMovie = guessGame.pickTheMovie();
        }catch (IOException ex)
        {
            System.out.println("Can't Find The File , Please check the correct Path!");
            System.out.println("More Details: " + ex);
        }
        // assigning the variable replaceMovie to the returned value
        // from method replacePickedMovie which replaces the name of the movie with dashes
        replacedMovie = guessGame.replacePickedMovie(pickedMovie);
        //setting pickedMovie and replacedMovie of the Class game with new values to update them
        guessGame.setPickedMovie (pickedMovie);
        guessGame.setReplacedMovie(replacedMovie);
        //loop until either the user guessed the movie or user runs out of points totalWrongLetters=10
        while (!guessGame.isGuessed() && totalWrongLetters < 10) {
            //check if the picked Movie equals the replaced Movie
            if (replacedMovie.equals(pickedMovie)) { // user wins if he/she guessed all the letters of the picked movie
                guessGame.winTheGame(true);
                //updating the isGuessed variable in the Game class to true
                guessGame.setGuessed(true);
            } else {
                // still the user guessing different letters
                //every letter from user is hold in guessUserLetter variable
                guessGame.userMessageInput(); // calling the method to take the input letter from the user
                guessUserLetter = guessGame.getUserGuessedLetter(); //stores the input guessed letter
                //checks if the picked movie contains the letter the user entered
                if (pickedMovie.contains(guessUserLetter + "")) {
                    //update the replaced movie in case the guessed letter is correct
                    //replaces all the dashes with the correct letter
                    guessGame.updateReplacedMovie(guessUserLetter);
                    //updating the variable replacedMovie to hold the new movie name after replacing correct guessed letters
                    replacedMovie = guessGame.getReplacedMovie();
                    //updating the userGuessedLetter in the class Game
                    guessGame.setUserGuessedLetter(guessUserLetter);

                }else // here the user guessed wrong letter
                {
                    //first we update the guessedWrongLetter in Game Class
                    guessGame.updateGuessedWrongLetters(guessUserLetter);
                    //then update the variable totalWrongLetters with the new value
                    totalWrongLetters = guessGame.getWrongLettersCount();
                    //updating the isGuessed variable in the Game class to false
                    guessGame.setGuessed(false);
                }
            }
        }
        //when the user fails to guess the picked movie , the program displays the lose message
        //in this case the user reaches the max number to wrong guessing which is 10 points
        //and the isGuessed variable of the Class Game is still false
        guessGame.loseTheGame(totalWrongLetters,guessGame.isGuessed());
    }
}