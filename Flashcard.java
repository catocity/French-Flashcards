/* Catherine Chen
 * CS230 Final Project: French Flashcards
 * Team Members: Violet Kozloff, Safia Williams, Catherine Chen
 * Date Created: December 10, 2016
 * Date Modified: December 18, 2016
 * Flashcard.java
 */ 


/* Description: Represents a single Flashcard object
 * 
 * NOTES: For our project, we will have the front of the card in French and the
 * back in English. However, this object has the freedom to have any string set 
 * to the front and back of the Flashcard.
 */ 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Flashcard extends JPanel {
  
  private String front, back;
  
  /* constructor: creates a blank flashcard
   * Will not be used in our project for CS230, but 
   * can be used in the future if we decide to add code
   * to allow the user to create their own flashcards.
   */ 
  public Flashcard() {
    this.front = this.back = "";
  }
  
  
  /* constructor: takes two parameters to create a flashcard
   * param front The phrase on the front of the card
   * param back The phrase on the back of the card
   * Automatically sets the default side of the card shown
   * to be the front. 
   */ 
  public Flashcard(String front, String back) {
    this.front = front;
    this.back = back;
  }
  
  
  /*
   * Getter for front
   * return The phrase on the front of the card
   */
  public String getFront() {
    return this.front;
  }
  
  
  /*
   * Getter for back
   * return The phrase on the back of the card
   */
  public String getBack() {
    return this.back;
  }
  
  
  /*
   * Setter for front
   * param phrase The new front
   */
  public void setFront(String phrase) {
    this.front = phrase;
  }
  
  
  /*
   * Setter for back
   * param phrase The new back
   */
  public void setBack(String phrase) {
    this.back = phrase;
  }
  
  
  // flips the flashcard over
  public void flipCard() {
    String temp = front;
    this.front = back;
    this.back = temp;
  }
  
  
  // String representation of the flashcard
  public String toString() {
    String result = "";
    result += "Front of flashcard: " + front;
    result += "\nBack of flashcard: " + back;
    return result; 
  }
  
  
  // FOR TESTING PURPOSES ONLY
  public static void main(String[] args) {
    
    Flashcard f1 = new Flashcard();
    f1.setFront("French");
    f1.setBack("English");
    System.out.println("Front (French): " + f1.getFront());
    System.out.println("Back (English): " + f1.getBack());
    System.out.println(f1); 
    
  }
  
}