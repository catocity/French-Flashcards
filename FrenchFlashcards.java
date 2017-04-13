/* Catherine Chen
 * CS230 Final Project: French Flashcards
 * Team Members: Violet Kozloff, Safia Williams, Catherine Chen
 * Date Created: December 15, 2016
 * Date Modified: December 18, 2016
 * FrenchFlashcards.java
 */ 


/* Description: Implements the French Flashcards study tool, without 
 * an interface. 
 * It is meant to work together with a GUI program. 
 */ 


public class FrenchFlashcards {
  
  private StudyMode study;
  private GameMode game;  
  
  // constructor
  public FrenchFlashcards(String file) {
    
    // create the two options: study and game
    study = new StudyMode(file);
    game = new GameMode(file);
    
  }
  
  
  /* Constructor: Allows us to use a dictionary that translates French 
   * infinitive verbs
   */ 
  public FrenchFlashcards(Dictionary dict) {
    study = new StudyMode(dict);
    game = new GameMode(dict);
  }
  
  
  public StudyMode getStudyMode() {
    return study;
  }
  
  public GameMode getGameMode() {
    return game;
  }
  
}