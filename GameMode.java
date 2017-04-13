/* Catherine Chen
 * CS230 Final Project: French Flashcards
 * Team Members: Violet Kozloff, Safia Williams, Catherine Chen
 * Date Created: December 11, 2016
 * Date Modified: December 18, 2016
 * GameMode.java
 */ 


/* Description: To be used with our GUI. Plays the quiz game.
 * The user inputted answer will be checked against the Flashcard
 * to see if the answer is correct. Keeps track of how many wrong
 * answers there are. 
 */ 


import java.util.Scanner;
import java.util.*;

public class GameMode extends ModeOfPlay {
  
  private int wrong; // keeps track of the number of wrong answers
  
  /* CONSTUCTOR: Creates a deck and initializes the wrong answers counter.
   * param file Takes in a file to be converted in a deck to be used in the game.
   */ 
  public GameMode(String file) {
    super(file);
    this.wrong = 0;
  }
  
  /* CONSTUCTOR: Creates a deck and initializes the wrong answers counter.
   * param dict Takes in a dictionary of French infinitive verbs to be translated 
   * and converted into a deck of flashcards to be used in the game.
   */ 
  public GameMode(Dictionary dict) {
    super(dict);
    this.wrong = 0;
  }
  
  
  /* Getter for number of wrong answers
   * return Number of wrong answers
   */
  public int getWrong() {
    return wrong; 
  }
  
  
  /* Returns true is the given answer matches the front of the flashcard. 
   * If the answer does not match the front of the flashcard, it increments
   * the wrong counter and returns false.
   * param card A flashcard object
   * param answer A String representing the user input
   */ 
  public boolean checkAnswer (Flashcard card, String answer) {
    if (card.getBack().equals(answer))
      return true;
    else {
      wrong++;
//      System.out.println("WRONG"); // testing
      return false;
    }
  }
  
  
  /* Gets the number of cards is the deck
   * return Size of deck
   */ 
  public int sizeOfDeck() {
    return this.getDeck().getSize();
  }
  
}