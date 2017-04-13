/* Catherine Chen
 * CS230 Final Project: French Flashcards
 * Team Members: Violet Kozloff, Safia Williams, Catherine Chen
 * Date Created: December 11, 2016
 * Date Modified: December 18, 2016
 * FrenchFlashcardsPanel.java
 */ 


/* PURPOSE: Sets up the panel that contains the French Flashcards interaction.
 * 
 * BUGS (see individual methods for more details):
 * MUST RESIZE THE WINDOW INITIALLY AND CANNOT RESIZE AFTER CLICKING A BUTTON
 * (A GREY PANEL COVERS THE SCREEN); FONT SIZE DOES NOT SHRINK/ GROW WITH THE
 * CHANGING OF THE WINDOW SIZE (WOULD HAVE LIKED TO MAKE IT DYNAMIC); TEXT DOES
 * NOT WRAP (GET '...'); CANNOT PLAY ANOTHER QUIZ GAME ONCE ONE IS FINISHED 
 * (WOULD LIKE TO HAVE AN OPTION)
 * 
 * WOULD LIKE TO FIX (IF HAD MORE TIME):
 * LAYOUT OF THE STUDY PANEL (CHANGE TO A BORDER LAYOUT? SO COULD LOOK MORE 
 * RECTANGULAR AND LIKE A FLASHCARD); BUGS LISTED ABOVE; INSERT A MESSAGE PANEL
 * THAT TELLS YOU IF YOU GOT AN ANSWER CORRECT/ INCORRECT; HAVE INSTRUCTIONS 
 * DISPLAYED ON THE BOTTOM OF THE MAIN SCREEN;CURRENTLY: ANSWER IS CASE-SENSITIVE 
 * (CHANGE SO THAT IT ACCEPTS THE ANSWER IN BOTH UPPER/LOWER CASE)
 * 
 * CREATED AND MODIFIED BY: CATHERINE AND VIOLET
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class FrenchFlashcardsPanel extends JPanel {
  
  // instance variables
  private FrenchFlashcards game;
  private Deck studyDeck, quizDeck;
  private Flashcard studyCard, quizCard; // flashcard displayed in StudyMode, QuizMode
  private JLabel top; // represents front of the flashcard displayed
  private boolean studyInPlay, quizInPlay; // returns true if study, game mode are in play
  
  // main screen instance variables
  private JLabel title, inst; // displays the mode of the game, how to play
  private JButton study, quiz, quit; // main screen buttons
  
  // study mode instance variables
  private JButton flip, returnToDeck, removeFromDeck; // StudyMode buttons
  
  // quiz mode instance variables
  private JTextField yourAnswer; // VKadded: user input
  private JLabel cardsLeft, numWrong; // number of cards left in deck, num of wrong answers
  
  
  /* 
   * Constructor: takes in an instance of the game as an input
   */ 
  public FrenchFlashcardsPanel(FrenchFlashcards g) {
    
    this.game = g;
    this.studyInPlay = false;
    this.quizInPlay = false;
    
    setLayout(new BorderLayout());
    setBackground(Color.blue);
    
    // Create buttons
    study = new JButton("Study Mode");
    quiz = new JButton("Quiz Game");
    quit = new JButton("Quit");
    
    // add action listeners
    study.addActionListener(new ButtonListener());
    quiz.addActionListener(new ButtonListener());
    quit.addActionListener(new ButtonListener());
    
    // add panels to the layout
    add(quit, BorderLayout.NORTH);
    add(study, BorderLayout.WEST);
    add(makeCenterPanel(), BorderLayout.CENTER);
    add(quiz, BorderLayout.EAST);
    add(makeSouthPanel(), BorderLayout.SOUTH);
    
  }
  
  
  /* Creates and returns a JPanel. Panel is blank until one of 
   * the two modes, study or quiz, is selected. Then the interaction
   * will begin in the panel.
   * return JPanel 
   */
  private JPanel makeCenterPanel() {
    
    // create centerPanel using BorderLayout manager
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BorderLayout());
    
    return centerPanel;
  }
  
  
  /* Creates and returns a JPanel containing a welcome message and the 
   * instructions.
   * return JPanel 
   */ 
  private JPanel makeSouthPanel() {
    
    JPanel southPanel = new JPanel();
    southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
    
    // will only display instructions
    title = new JLabel("Welcome to French Flashcards!");
    title.setFont(new Font("Open Sans", Font.BOLD, 40));//vkadd
    title.setAlignmentX(CENTER_ALIGNMENT); // align text in the center of the panel
    southPanel.add(title);
    
    // instructions 
    inst = new JLabel("Click on the game you want to play");
    inst.setFont(new Font("Open Sans", Font.BOLD, 20));//vkadd
    inst.setAlignmentX(CENTER_ALIGNMENT); // align text in the center of the panel
    southPanel.add(inst);    
    
    return southPanel;
    
  }
  
  
  /* Creates a panel where StudyMode is run
   * return JPanel
   * 
   * BUG (COMMENTED OUT): WE WANTED TO CHANGE THE PANEL AT THE BOTTOM OF THE MAIN SCREEN
   * AND REPLACE THE TEXT TO SHOW THE SPECIFIC TITLE AND INSTRUCTIONS FOR STUDY MODE, 
   * BUT A GREY PANEL APPEARS OVER THE GAME.
   */ 
  private JPanel makeStudyPanel() {
    
    studyDeck = game.getStudyMode().getDeck();
    studyInPlay = true;
    
    // update the bottom panel in main screen
    // BUG: GREY PANEL APPEARS OVER THE GAME
//    title.setText("Study Mode");
//    inst.setText("Click flip to see the back of the card. Return or remove the card.");
//    inst.setFont(new Font("Open Sans", Font.BOLD, 10));  
    
    // create a new panel
    JPanel studyPanel = new JPanel();
    
    // set the layout 
    studyPanel.setLayout(new GridLayout(1,3));
    
    // create buttons
    flip = new JButton("Flip");
    returnToDeck = new JButton("Return to deck");
    removeFromDeck = new JButton("Remove from deck");
    
    // add action listeners to buttons
    flip.addActionListener(new StudyListener());
    returnToDeck.addActionListener(new StudyListener());
    removeFromDeck.addActionListener(new StudyListener());
    
    // create panels to store buttons
    JPanel left = new JPanel(); // flip
    JPanel center = new JPanel(); // displays the flashcards
    JPanel right = new JPanel();// returnToDeck and removeFromDeck
    
    center.setBackground(Color.white);
    
    // create layout
    GridLayout rightLayout = new GridLayout(2,1);
    
    // set layout
    right.setLayout(rightLayout);
    
    // add buttons into panels
    left.add(flip);
    right.add(returnToDeck);
    right.add(removeFromDeck);
    
    // add and display first flashcard
    studyCard = studyDeck.getNext();
    top = new JLabel(studyCard.getFront());
    top.setFont(new Font("Open Sans", Font.BOLD, 30));//vkadd
    center.add(top);    
    
    // add left, right, center panels to studyPanel
    studyPanel.add(left);
    studyPanel.add(center);
    studyPanel.add(right);
    
    return studyPanel;
  }
  
  
  /* Creates a panel where QuizMode is run
   * return JPanel
   * 
   * BUG (COMMENTED OUT): WE WANTED TO CHANGE THE PANEL AT THE BOTTOM OF THE MAIN SCREEN
   * AND REPLACE THE TEXT TO SHOW THE SPECIFIC TITLE AND INSTRUCTIONS FOR QUIZ MODE, 
   * BUT A GREY PANEL APPEARS OVER THE GAME.
   */ 
  private JPanel makeQuizPanel() {
    
    quizDeck = game.getGameMode().getDeck();
    quizInPlay = true;
    
    // update the bottom panel in main screen
    // BUG: GREY PANEL APPEARS OVER THE GAME
//    title.setText("Quiz Mode");
//    inst.setText("Type the correct translation and hit enter to check your answer.");
//    inst.setFont(new Font("Open Sans", Font.BOLD, 10));
    
    JPanel quizPanel = new JPanel();
    
    //set the layout 
    quizPanel.setLayout(new BorderLayout());
    
    // add first flashcard
    quizCard = quizDeck.getNext();
    top = new JLabel(quizCard.getFront() + " (" + quizCard.getBack() + ")"); // add correct answer in () for testing
    top.setFont(new Font("Open Sans", Font.BOLD, 40));//vkadd
    
    
    // create labels
    cardsLeft = new JLabel("Cards left: " + quizDeck.getSize());
    numWrong = new JLabel("Wrong answers: " + game.getGameMode().getWrong());
    yourAnswer= new JTextField(10);
    
    // add action listener to textfield
    yourAnswer.addActionListener(new QuizListener());
    
// create panels to store labels
    JLabel north = new JLabel();
    north.setBackground(Color.white);//vkadd
    north.setFont(new Font("Open Sans", Font.BOLD, 40));//vkadd
    JPanel left = new JPanel(); //VKAdd 
    left.setBackground(Color.white);//vkadd
    JPanel center = new JPanel(); 
    center.setBackground(Color.white);//vkadd
    JPanel right = new JPanel();//  VKAdd 
    right.setBackground(Color.white);//vkadd
    JPanel bottom = new JPanel();// yourAnswer, VKAdded 
    bottom.setBackground(Color.white);//vkadd
    
    // add labels into panels
    left.add(cardsLeft);
    right.add(numWrong); //VKAdd
    bottom.add(yourAnswer);   
    center.add(top);
    
    // add left, right, center panels to studyPanel
    quizPanel.add(north, BorderLayout.NORTH);
    quizPanel.add(top, BorderLayout.CENTER);
    Border border = BorderFactory.createLineBorder(Color.blue);
    top.setBorder(border);
    quizPanel.add(left, BorderLayout.WEST);
    quizPanel.add(right, BorderLayout.EAST);
    quizPanel.add(bottom, BorderLayout.SOUTH);
    
    return quizPanel;
    
  }
  
  
  /* 
   * Quits the program if the quit button is pushed.
   * Changes the south panel on the main screen to study mode or quiz mode
   * if the according button is pressed.
   * param event The event which causes the action to be performed. 
   * 
   * BUGS: WHEN THE WINDOW RESIZES, THE OLD PANEL SHOWS UP AGAIN AND COVERS
   * THE NEW PANEL UP- NEED TO CHECK HOW WE REPLACE THE PANELS; CANNOT PLAY 
   * QUIZ GAME AGAIN AFTER ONE GAME IS PLAYED SINCE
   * THE DECK IS EMPTY
   */ 
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      
      // Study Mode button is pressed
      if (event.getSource() == study) {
        // Case: in main screen (neither study nor quiz has been selected)
        // Enter into study mode by replacing the center panel with the study panel
        if (!quizInPlay && !studyInPlay) {
          studyInPlay = true;
          remove(makeCenterPanel());
          add(makeStudyPanel(), BorderLayout.CENTER);
          revalidate();
        }
        // Case: in study mode, click study mode
        else if (studyInPlay && !quizInPlay) {
          top.setText("You are already in study mode!"); // display error message
          top.setFont(new Font("Open Sans", Font.BOLD, 10));
        }
        // Case: in quiz mode, click study mode
        else if (quizInPlay){
          top.setText("You must finish the quiz game first!"); // display error message
          top.setFont(new Font("Open Sans", Font.BOLD, 20));
          yourAnswer.setText(""); // clear textfield
        }
      }
      
      // Quiz Game button is pressed
      else if (event.getSource() == quiz) {
        // Case: main screen or study mode
        // Enter into quiz mode by replacing the center panel with the quiz panel
        if (!quizInPlay) {
          quizInPlay = true;
          remove(makeCenterPanel());
          add(makeQuizPanel(), BorderLayout.CENTER);
          revalidate();
        }
        // Case: returning back to quiz mode after trying to click study mode
        else if (!studyInPlay && quizInPlay) {
          if (!quizDeck.isEmpty()) { // while the deck still has cards
            // display the current flashcard
            top.setText(quizCard.getFront() + " (" + quizCard.getBack() + ")");
            top.setFont(new Font("Open Sans", Font.BOLD, 40));
          }
          else { // no cards left in the deck (game is over)
            top.setText("You have already learned these cards!"); // display error message
            top.setFont(new Font("Open Sans", Font.BOLD, 20));
            quizInPlay = false;
          }
        }        
        // Case: in quiz mode, click quiz mode
        else if (quizInPlay) {
          if (!quizDeck.isEmpty()) {
            top.setText("You are already in the quiz game!"); // display error message
            top.setFont(new Font("Open Sans", Font.BOLD, 20));
            yourAnswer.setText(""); // clear textfield
            // FIX: HOW DOES USER GET BACK TO DISPLAYING THE SAME CARD? 
            // RIGHT NOW, THEY EITHER MUST: REMEMBER THE CARD DISPLAYED LAST OR
            // FORFEIT THE ROUND
          }
          else { // finished game, cannot play again (sorry)
            top.setText("You have already learned these cards!");
          }
        }
        
      }
      else if (event.getSource() == quit) { // quit
        System.exit(0);
      }
    } 
  }
  
  
  /*
   * Action Listeners for StudyMode
   * 
   * BUGS: WHEN THERE ARE NO CARDS LEFT IN THE DECK, THE TEXT MESSAGE THAT
   * DISPLAYS THERE ARE NO MORE CARDS LEFT IN THE DECK DOES NOT WRAP. FIGURE
   * OUT HOW TO WRAP THE TEXT (temporarily fixed this problem by making the 
   * font smaller).
   * 
   */ 
  private class StudyListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      
      // flip the card over and display
      if (event.getSource() == flip) {
        studyCard.flipCard();
        top.setText(studyCard.getFront()); // replace JLabel
        top.setFont(new Font("Open Sans", Font.BOLD, 30));//vkadd
      }
      // return the card back to the deck and display the next one
      else if (event.getSource() == returnToDeck) {
        studyDeck.returnToDeck(studyCard);
        studyCard = studyDeck.getNext();
        top.setText(studyCard.getFront());
        top.setFont(new Font("Open Sans", Font.BOLD, 30));//vkadd
      }
      // display the next card in the deck
      else if (event.getSource() == removeFromDeck) {
        if (studyDeck.hasNext()) {
          studyCard = studyDeck.getNext();
          top.setText(studyCard.getFront());
          top.setFont(new Font("Open Sans", Font.BOLD, 30));//vkadd
        }
        else { // no cards left in the deck, display error message
          top.setText("You are done studying. There are no more cards left in the deck!");
          top.setFont(new Font("Open Sans", Font.BOLD, 10));//vkadd
          studyInPlay = false;
        }
      }
    }
  }
  
  
  /*
   * Action Listeners for QuizMode
   * 
   * BUG: The card in the middle covers up the number of cards left; depends on sizing. 
   * try to put w/ wrong answers on right? //vkadd
   * 
   */ 
  private class QuizListener implements ActionListener {
    
    public void actionPerformed (ActionEvent event) {
      
      String text = yourAnswer.getText(); // user input
      boolean ans = game.getGameMode().checkAnswer(quizCard, text);
      
      // Compares user input to back of card. 
      if (ans) // user entered the correct answer
      {
        // if they are the same, display the next card in the deck
        if (quizDeck.hasNext()) {
          quizCard = quizDeck.getNext();
          top.setText(quizCard.getFront() + " (" + quizCard.getBack() + ")");
          top.setFont(new Font("Open Sans", Font.BOLD, 40));//vkadd
          cardsLeft.setText("Cards Left: " + quizDeck.getSize());
          numWrong.setText("Wrong answers: " + game.getGameMode().getWrong());
          yourAnswer.setText(""); // clear textfield
        }
        else { // no more cards left in the deck
          yourAnswer.setText(""); // clear textfield
          top.setText("Congratulations, you have learned all of the words!");
          numWrong.setText("Wrong answers: " + game.getGameMode().getWrong());
          top.setFont(new Font("Open Sans", Font.BOLD, 15));//vkadd
        }
        
      }
      // If they are not the same, return the card back to the deck and display the next one
      else {
        quizDeck.returnToDeck(quizCard);
        quizCard = quizDeck.getNext();
        top.setText(quizCard.getFront() + " (" + quizCard.getBack() + ")");
        top.setFont(new Font("Open Sans", Font.BOLD, 40));//vkadd
        numWrong.setText("Wrong Answers: "+ game.getGameMode().getWrong());
        yourAnswer.setText(""); // clear textfield
      }
    }  
  }
}