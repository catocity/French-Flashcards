//Study Mode: Created by Violet

import java.util.Scanner;
import javax.swing.JFrame;

public class StudyMode{
  
  Scanner scan;
  Deck deck;
  String french;
  String english;
  Dictionary dictionary;
  
  public StudyMode(String fileName)
  {
    Dictionary d=new Dictionary (fileName);
    deck = new Deck(d);
    deck.shuffle();
  }

  
  public StudyMode(Dictionary dict) {
    deck = new Deck(dict.getTable());
    deck.shuffle();
  }
  
  
  public Deck getDeck() {
    return this.deck;
  }
  
  public String getFrench()
  {
    return this.deck.getNext().getFront();
  }
  
   public String getEnglish()
  {
    return this.deck.getNext().getBack();
  }

   
   
  public void playOneRound()
  {
    if (deck.getSize()>0)
    {
      Flashcard card = deck.getNext();
      french = card.getFront();
      System.out.println(french);
      english=card.getBack();
      System.out.println("To flip, enter 1");
      scan=new Scanner(System.in);
      int flip=scan.nextInt();
      if (flip==1)
      {
        this.flip(card);
      }
      System.out.println("Study again? Enter 1 for yes, 2 for no");
      if (scan.nextInt()==1)
      {
        this.returnToDeck(card);
      }
    }
  }
  
  private void flip(Flashcard c)
  {
    System.out.println(c.getBack());
  }
  
  public void returnToDeck(Flashcard c)
  {
    this.deck.allCards.enqueue(c);
  }
  
  public void playOneGame()
  {
    while (deck.allCards.size()>0)
    {
      this.playOneRound();
    }
  }
  public static void main (String [] args)
  {
    StudyMode s= new StudyMode("MonDictionnaire.txt");
    System.out.println(s.deck);
    s.playOneRound();
  }
}



