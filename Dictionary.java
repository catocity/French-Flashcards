/**
 * Dictionary
 * French Conjugation Flashcards
 * Violet Kozloff 
 */

import java.util.*;
import java.io.*;

public class Dictionary{
  
  Hashtable <String, String> table=new Hashtable <String, String>();
  String dictionaryName;
  
  public Dictionary(String dico)
  {
    this.dictionaryName=dico;
    try 
    {
      Scanner fileScan=new Scanner (new File(dictionaryName));
      while (fileScan.hasNext())
      {
         String french;
         String english;
         String[] words = fileScan.nextLine().split(" Ð ");
         french=words[0];
         english=words[1];
      addWord(french, english);
      }
    }
    catch (IOException ex)
    {
    System.out.println(ex);
    }

  }
  
  public Boolean valueSearch(String value)
  {
    return this.table.containsValue(value);
  }
  
  
  
  public Hashtable <String, String> getTable()
  {
    return this.table;
  }
  
  // Adds input Strings to the hashtable. Associates French key with English value
    public void addWord(String french, String english)
    { 
      table.put(french, english);
    } 
  
  // Searches the dictionary for the input String. Returns true if found, false otherwise.
  public boolean searchWord(String word)
  {
    if (table.containsKey(word))
    {
      return true;
    }
    else 
    {
      return false;
    }
  }
  
  // Removes from the dictionary for the input String. It does nothing if the input word is not in the dictionary.
  
  public void removeWord(String word)
  {
    if (searchWord(word))
    {
      table.remove(word);
    }
  }

  
  public static void main(String[] args) {
    Dictionary BilingualDictionary = new Dictionary ("MonDictionnaire.txt");
    System.out.println("casser: " + BilingualDictionary.searchWord("casser"));
    System.out.println("bouillir: " + BilingualDictionary.searchWord("bouillir"));
    System.out.println("should say false: "+BilingualDictionary.valueSearch("to boil"));
    System.out.println("Adding bouillir to dictionary");
    BilingualDictionary.addWord("bouillir", "to boil");
    System.out.println("bouillir: " + BilingualDictionary.searchWord("bouillir"));
    System.out.println("vendre: " + BilingualDictionary.searchWord("vendre"));
    System.out.println(BilingualDictionary.table.values());
    Collection<String> val= BilingualDictionary.table.values();
    System.out.println(BilingualDictionary.table.get("bouillir"));
    System.out.println("should say true: "+BilingualDictionary.valueSearch("to boil"));
    //System.out.println(BilingualDictionary.table.keySet());
  }
}
