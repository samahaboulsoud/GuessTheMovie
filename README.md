# GuessTheMovie
-------------------------------------------------------------------------------------------------------------------------------
a game where the player gets to guess the movie name given the number of letters in it (pretty much like hangman but with movies)!

The rules are simple, the computer randomly picks a movie title, and shows you how many letters it's made up of. 

the goal is that the user try to figure out the movie by guessing one letter at a time.

If a letter is indeed in the title the computer will reveal its correct position in the word, if not, user loses a point. If user loses 10 points, game over!

BUT the more correct letters the user guess the more obvious the movie becomes and at a certain point the user should be able to figure it out.
The program will randomly pick a movie title from a text file that contains a large list of movies.

Once the computer picks a random title, it will display underscores "_" in place of the real letters, thereby only giving away the number of letters in the movie title.

Then it will wait for the player to enter their first letter guess.

If the letter was indeed in the word, the underscores "_" that match that letter will be replaced with the correct letter revealing how many letters have matched the guess and where they are.

If the guess isn't in the movie title, then the output will display the same output as the previous step as well as any letters that have been previously guessed wrong.

Eventually, if the player manages to guess all the letters in the movie title correctly before they lost 10 points, they win

the movie list :
the shawshank redemption
the godfather
the dark knight
schindler's list
pulp fiction
the lord of the rings
the good the bad and the ugly
fight club
the lord of the rings
forrest gump
star wars
inception
the lord of the rings
the matrix
samurai
star wars
city of god
the silence of the lambs
batman begins
die hard
chinatown
room
dunkirk
fargo
no country for old men
