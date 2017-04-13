/* Catherine Chen
 * CS230 Final Project: French Flashcards
 * Team Members: Violet Kozloff, Safia Williams, Catherine Chen
 * Date Created: December 10, 2016
 * Date Modified: December 18, 2016
 * Deck.java
 */  


/* Description: Represent a deck of Flashcard objects.
 * 
 * NOTES: Code is based off of Deck.java in CS230 Exam 2. However,
 * instead of using a stack to represent the deck, we use a queue
 * in order to place cards back into the deck to be used at a later
 * time (not immediately).
 */ 

/* BUGS: THE SHUFFLE METHOD DOES NOT SEEM TO GENERATE RANDOM
 * NUMBERS. THE CARDS ARE NOT PLACED IN A RANDOM ORDER EACH TIME.
 */ 



import java.util.*;
import javafoundations.LinkedQueue;

/* Represents a collection of Flashcards
 */

public class Deck {
  
  // the stack of flashcards in a deck
  LinkedQueue<Flashcard> allCards;
  
  private int originalNum;
  
  
  /* Creates an empty deck (not useful for the purposes of
   * our project's extent, but may be useful if, in the 
   * future, we allow the user to create their own 
   * flashcards.
   * param num The number of flashcards in the deck
   */ 
  public Deck(int num) {
    
    this.originalNum = num;
    
    for (int i = 0; i < num; i++)
      allCards.enqueue(new Flashcard());
    
  }
  
  
  /* Constructor: Creates a deck of Flashcards by iterating through 
   * a hashtable (our dictionary)
   * param dictionary A hashtable of French and English word translations.
   * 
   * Created by Safia
   */
  public Deck(Hashtable<String, String> dictionary) {
    allCards=new LinkedQueue<Flashcard>();
    this.originalNum = dictionary.size();
    Enumeration<String> dictKey = dictionary.keys();
    for (int i = 0; i<6; i++){
      String key = dictKey.nextElement();
      String val = dictionary.get(key);
      Flashcard current = new Flashcard(key, val);  
      allCards.enqueue(current);
      FrenchVerb fv = new FrenchVerb(key);
      Hashtable <String, String> conjugated= fv.conjugateAll(fv);
      Set<String> keys = conjugated.keySet(); 
      for (String k: keys)
      {
        FrenchVerb f= new FrenchVerb(k+" "+conjugated.get(k));
        Flashcard flash= new Flashcard (k+" "+conjugated.get(k), f.translate());
        allCards.enqueue(flash);
        
      }
      
    }
  }
  
  
  /* Constructor: Creates a deck of Flashcards by iterating through 
   * a hashtable in a Dictionary object
   * param dict A dictionary object containing a hashtable of French 
   * and English word translations.
   * (key = French, value = English)
   */
  public Deck(Dictionary dict) {
    
    allCards = new LinkedQueue<Flashcard>();
    
    Hashtable<String, String> dictionary = dict.getTable();
    
    this.originalNum = dictionary.size();
    
    Enumeration<String> dictKey = dictionary.keys();
    
    while (dictKey.hasMoreElements()) {
      String key = dictKey.nextElement();
      String val = dictionary.get(key);
      allCards.enqueue(new Flashcard(key, val));
    }
    
  }
  
  
  /* 
   * Shuffles the cards within this Deck object
   */ 
  public void shuffle() {
    
    // remove items from queue and store in an array
    Flashcard[] deck = new Flashcard[allCards.size()];
    for (int i = 0; i < deck.length; i++) {
      Flashcard newCard = allCards.dequeue();
//      System.out.println("Adding to array at " + i + ": " + newCard);
      deck[i] = newCard;
    }
    
    // randomize order of array by continuously swapping two cards
    for (int i = 0; i < deck.length; i++) {
      int r = (int) Math.random();
      Flashcard temp = deck[r];
      deck[r] = deck[i];
      deck[i] = temp;
    }
    
    // place cards back into the queue
    for (int i = 0; i < deck.length; i++) { 
      allCards.enqueue(deck[i]);
    }
    
  }
  
  
  /* 
   * Checks if there are any more flashcards in the deck
   * return True if there is at least one more card
   */
  public boolean hasNext(){
    return !allCards.isEmpty();
  }
  
  
  /* 
   * Checks if the deck is empty
   * return True if there are no more cards left in the deck
   */
  public boolean isEmpty() {
    return allCards.isEmpty();
  }    
  
  
  /*
   * Retrieves the next flashcard from the deck
   * return The top card from the deck
   */
  public Flashcard getNext(){
    return allCards.dequeue();
  }
  
  
  /* 
   * returns the number of flashcards in the deck
   */ 
  public int getSize() {
    return allCards.size();
  }
  
  
  /* 
   * Places a flashcard on the bottom of deck
   */
  public void returnToDeck(Flashcard card) {
    allCards.enqueue(card);
  }
  
  
  /* 
   * provides a String representation of the deck
   */ 
  public String toString() {
    return "Contents of deck: " + allCards; 
  }
  
  
  // MAIN METHOD: TESTING PURPOSES ONLY
  public static void main (String[] args) {
//    Dictionary dict1 = new Dictionary("Test.txt");
//    Deck d = new Deck(dict1);
//    
//    System.out.println("Size of deck: (5) " + d.getSize());
//    d.shuffle();
//    System.out.println("Size of deck: (5) " + d.getSize());
    
//    for (int i = 0; i < 13; i++) {
//      Flashcard f = d.getNext();
//      System.out.println("Returning to deck: " + "[" + i + "]" + f);
//      d.returnToDeck(f);
//    }
    
    Dictionary dict = new Dictionary("MonDictionnaire.txt");
    // System.out.println(dict.getTable());
    Deck d= new Deck(dict.getTable());
    System.out.println(d);
    
  }
  
}