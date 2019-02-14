package com.company;

import java.io.*;
import java.util.Scanner;

public class Game {

    // string variable to hold the filename containing
    // the list of movies where the program will pick one randomly
    private String fileName;
    //string variable to hold the random picked movie
    private String pickedMovie;
    //string variable to hold the replaced picked movie by dashes or correct user guessed letters
    private String replacedMovie;
    //character variable to hold the entered guessed letter by the user
    private char userGuessedLetter;
    //boolean variable to hold whether or not the user guessed the picked movie
    private boolean isGuessed = false;
    //integer variable to count total number of wrong guessed letter the user made
    private int wrongLettersCount = 0;
    //character array to save all wrong guessed letters the user entered , so repeated wrong letters is not counted
    private char[] inputLetters = new char[10];
    ////declaring allWrongLetters to display the whole wrong letters guessed  by the user
    private String allWrongLetters;

    //getter method for the variable wrongLettersCount
    //returns the value of wrongLettersCount
    public int getWrongLettersCount() {
        return wrongLettersCount;
    }

    //setter method for the variable wrongLettersCount
    //sets the value of wrongLettersCount to the value of the parameter
    public void setWrongLettersCount(int wrongLettersCount) {
        this.wrongLettersCount = wrongLettersCount;
    }

    //getter method for the variable isGuessed
    //returns the value of the isGuessed
    public boolean isGuessed() {
        return isGuessed;
    }

    //setter method for the variable isGuessed
    //sets the value of isGuessed to the value of the parameter
    public void setGuessed(boolean guessed) {
        isGuessed = guessed;
    }

    //getter method for the variable fileName
    //returns the value of the fileName
    public String getFileName() {
        return fileName;
    }

    //setter method for the variable fileName
    //sets the value of the fileName to the parameter value
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    //getter method for the variable userGuessedLetter
    //returns the value of the userGuessedLetter
    public char getUserGuessedLetter() {
        return userGuessedLetter;
    }

    //setter method for the variable userGuessedLetter
    //sets the value of the userGuessedLetter to the value of the parameter
    public void setUserGuessedLetter(char userGuessedLetter) {
        this.userGuessedLetter = userGuessedLetter;
    }

    //getter method for the variable pickedMovie
    //returns the value of the pickedMovie
    public String getPickedMovie() {
        return pickedMovie;
    }

    //setter method for the variable pickedMovie
    //sets the value of the pickedMovie to value of the parameter
    public void setPickedMovie(String pickedMovie) {
        this.pickedMovie = pickedMovie;
    }

    //getter method for the variable replacedMovie
    //returns the value of the replacedMovie
    public String getReplacedMovie() {
        return replacedMovie;
    }

    //setter method for the variable replacedMovie
    //sets the value of the replacedMovie to the value of the parameter
    public void setReplacedMovie(String replacedMovie) {
        this.replacedMovie = replacedMovie;
    }

    //Method pickTheMovie opens the file given from the getfilename method
    //reads every line in the file
    //and counts the total number of the movies list so the program can make a random number according.
    public String pickTheMovie() throws IOException { //throws an exception in case where is an error while opening the file

        String movieName = ""; // variable to return the random picked movie name
        File movieFile = new File(this.getFileName()); // declaring and initializing new variable of type File to hold the opened file
        Scanner movieScanner = new Scanner(movieFile); //declaring and initializing new variable of type scanner to read lines from the opened file

        int numberOfMovies = 0; //variable to hold the total count of the movie list

        while (movieScanner.hasNextLine()) {
            movieScanner.nextLine(); //move to next line
            numberOfMovies++; //increment the numberOfMovies by one
        }
        //declaring variable randomNumber to calculate a random number within
        // the range of the movie list count starting from 1 ending with total movie list count ,
        // this integer number is considered the index of the picked move
        int randomNumber = (int) (Math.random() * numberOfMovies) + 1;
        //declaring and initializing new bufferedReader to store the file content of the movie list file
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        //loop until getting the correct picked movie according to the randomNumber (movie index)
        for (int i = 0; i < randomNumber; i++) {
            movieName = bufferedReader.readLine(); //storing the picked movie name in variable movieName
        }

        bufferedReader.close(); //closing the bufferReader
        return movieName; //returning the correct picked movie

    }

    //this method replaces every character in the picked movie name by dash except space remains space
    //and stores it in a variable replacedMovieName
    public String replacePickedMovie(String movieName) {

        String replacedMovieName = ""; //declaring and initializing replacedMovieName
        //loop until reaches to the end of the movie name
        for (int i = 0; i < movieName.length(); i++) {
            if (movieName.charAt(i) == ' ') {
                replacedMovieName = replacedMovieName + ' '; //spaces remain spaces
            } else {
                replacedMovieName = replacedMovieName + '-'; // any other character replaced by dash
            }
        }
        return replacedMovieName; //returning the replacedMovieName (with dashes) variable
    }

    //this method takes the letter entered by the user and stores it in char variable userLetter and returns that variable
    public void userMessageInput() {
        //declaring and initializing new userScanner variable
        Scanner userScanner = new Scanner(System.in);
        //displayed message to the user with the updating replacedMovie variable
        System.out.println("You are guessing :" + this.replacedMovie);
        //displaying message with total number of wrong trials (guessed letters) to the user ,
        // together with the wrong guessed letter/s.
        getAllWrongLetters(); // calls method to dispaly the whole list of wrong guessed letters.
        System.out.println("You have guessed " + this.wrongLettersCount + " wrong letter(s): " + allWrongLetters);
        //displaying a message asking the user to enter a guessed letter
        System.out.println("Guess a Letter");
        //storing the entered letter in userEnteredLetter variable
        this.setUserGuessedLetter(userScanner.next().charAt(0));

        //return userEnteredLetter; // returning userEnteredLetter variable

    }

    //this method updates the replacedMovie with the correct letters guessed by the user

    public void updateReplacedMovie(char userLetter) {
        String movieString = this.replacedMovie; // temp variable used to store last value of replacedMovie
        //loop until the end of the pickedMovie length to update
        // all the occurrence of the correct guessed letter entered by the user
        for (int i = 0; i < this.pickedMovie.length(); i++) {
            int replacedIndex = this.pickedMovie.indexOf(userLetter, i); // determine the index of the correct guessed letter
            if (replacedIndex != -1) { //guessed letter found in the pickedMovie
                movieString = movieString.substring(0, replacedIndex) + userLetter + movieString.substring(replacedIndex + 1, movieString.length());
                this.setReplacedMovie(movieString);//updating the replacedMovie variable with the new value
            }
        }
    }

    public void getAllWrongLetters() {
        //initializing allWrongLetters where it stores all wrong letters in  inputLetters array concatenated together.
        allWrongLetters = "";
        //loop until reaching the last stored wrong letter (wrongLettersCount)
        for (int i = 0; i < this.wrongLettersCount; i++) {
            allWrongLetters += " " + inputLetters[i];
        }
    }
    //this method counts the number of wrong guessed letters and stores int the array inputLetters
    //which contains all the wrong guessed letters
    //so it keeps track of wrong letters so user don't lose points for guessing the same letter twice
    //and warning the user in case he entered same wrong guess (letter) again.

    public void updateGuessedWrongLetters(char inputLetter) {
        // variable to check if the guessed letter is entered  before or not , if found in array inputLetters

        boolean isFound = false;
        int wrongLetters = this.getWrongLettersCount();//variable wrongLetters to keep updating wrong trials
        //loop in the array to check whether the new guessed letter is stored before.
        for (int i = 0; i <= wrongLetters; i++) {
            if (inputLetters[i] == inputLetter) {
                isFound = true;
                break; // break the loop id the new guessed letter found in the inputLe tters array
            }
        }

        if (!isFound) { //adding new guessed letter in inputLetters array
            inputLetters[wrongLetters] = inputLetter;
            wrongLetters++;
            this.setWrongLettersCount(wrongLetters); //updating the wrongLettersCount after counting the new wrong trial
        } else {
            //displaying message to warn the user in case he/she guessed wrong letter twice
            System.out.println("You have guessed " + inputLetter + " before try another letter!");
        }

    }

    //this method in case the user loses the game
    //only when the user reaches maximum number of trials before guessing the correct movie name

    public void loseTheGame(int wrongLetters, boolean userGuessed) {
        if (wrongLetters == 10 && !userGuessed) { //checking if number of wrong trials reaches ten and users didn't guess the movie
            //displaying messages to the user with total number of wrong trials and the correct picked movie name
            getAllWrongLetters();
            System.out.println("You have guessed " + wrongLetters + " wrong letter(s):" + allWrongLetters);
            System.out.println("Sorry you loose...You run out of points :(");
            System.out.println("The movie is '" + this.pickedMovie + "'");
        }

    }

    //this method in case the user wins the game and manages to guess the pickedMoive name
    public void winTheGame(boolean userGuessed) {
        this.setGuessed(true); //updating the isGuessed to true
        //dispalying winning message and the name of the picked movie
        System.out.println("Congratulation .. You Win!:)");
        System.out.println("You have Guessed '" + this.replacedMovie + "' Correctly.");
    }
}