/* Catherine Chen
 * CS230 Final Project: French Flashcards
 * Team Members: Violet Kozloff, Safia Williams, Catherine Chen
 * Date Created: December 11, 2016
 * Date Modified: December 18, 2016
 * FrenchFlashcardsGUI.java
 */ 

import javax.swing.JFrame;

/* DESCRIPTION: Sets up the GUI for the French Flashcards study tool.
 */ 


public class FrenchFlashcardsGUI {
  
  
  public static void main(String[] args) {
    
    // creates and shows a Frame 
    JFrame frame = new JFrame("French Flashcards");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // create an instance of the study tool    
//    FrenchFlashcards game = new FrenchFlashcards("MonDictionnaire.txt");
    
    // Using smaller text files to test so there are less cards in the deck
//    FrenchFlashcards test1 = new FrenchFlashcards("Test.txt"); //TESTING FILE
//    FrenchFlashcards test2 = new FrenchFlashcards("MonDico.txt"); // TESTING FILE
    
    Dictionary testDict = new Dictionary("MonDictionnaire.txt"); // tests translator
    FrenchFlashcards test3 = new FrenchFlashcards(testDict);
    
    // create a panel and add it to the frame
//    FrenchFlashcardsPanel panel = new FrenchFlashcardsPanel(game);
    FrenchFlashcardsPanel panel = new FrenchFlashcardsPanel(test3);
    
    frame.getContentPane().add(panel);
    
    frame.pack();
    frame.setVisible(true);
    
  }
  
}