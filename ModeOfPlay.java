/* Catherine Chen
 * CS230 Final Project: French Flashcards
 * Team Members: Violet Kozloff, Safia Williams, Catherine Chen
 * Date Created: December 11, 2016
 * Date Modified: December 18, 2016
 * ModeOfPlay.java
 */ 

/* Description: Parent class that creates the deck of flashcards to be used 
 * in either Study or Quiz Mode.
 */ 


import java.util.*;

public class ModeOfPlay {
  
  private Deck deck;
  
  /* 
   * constructor: Create a deck of flashcards of French/English translations
   * from infinitive verbs to set up for the two modes of play: Study and 
   * Quiz Game.
   * param dict A dictionary of French/English translations
   */ 
  public ModeOfPlay(Dictionary dict) {
    this.deck = new Deck(dict.getTable());
    deck.shuffle();
  }
  
  
  /* Constructor: create a deck of flashcards from a file
   */ 
  public ModeOfPlay(String file) {
    Dictionary dict = new Dictionary(file);
    this.deck = new Deck(dict);
    deck.shuffle();
  }
  
  
  /* 
   * return The deck of flashcard objects
   */ 
  public Deck getDeck() {
    return this.deck;
  }

}