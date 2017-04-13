import java.util.Scanner;
import java.util.*;

public class EnglishVerb extends Word{
  private int personType; // First, second, or third person
  private int singPlur; // 1 or 2
  private String infinitive; // The infinitive form of the verb
  
  private String speaker; // Identifies who is speaking
  private String verbOperator; // Identifies the verb
  private Dictionary BilingualDictionary; // Dictionary to translate from English to French
  
  
  /* Constructor takes in the user's input and assigns the personType, singPlur, 
   * and infinitive variables.
   */
  
  
  public EnglishVerb (String userInput){
    super("verb");
    String[] parts = userInput.split(" "); // Splits the user input by spaces
    
    if (parts[0].equals("to")){ // If the first word the user inputs is "to," then they have inputed an infinitive
      // Example: "to eat"
      verbOperator = parts[0].toLowerCase() + " " + parts[1].toLowerCase();
      infinitive = verbOperator;
    }
    else if (parts.length == 1){ // The user has not entered an infinitive 
      System.out.println("Please enter a valid form of the English verb. For example: I eat or To eat");
      verbOperator = parts[0];
    }
    else if (parts[1].equals ("all")){ // This recognizes the case in which the user enters "you all"
      speaker = parts[0].toLowerCase() + " " + parts[1].toLowerCase();
      verbOperator = parts[2].toLowerCase();
    }
    else{ // Otherwise, they have inputed a simple clause ("they eat")
      speaker = parts[0].toLowerCase(); // Gets the speaker
      verbOperator = parts[1].toLowerCase(); // Gets the conjugated verb
      BilingualDictionary = new Dictionary ("MonDictionnaire.txt"); // Creates the dictionary
    }
    
    if (speaker == null){ // If the user Input has no speaker, set personType and singPlur to zero
      personType = 0;
      singPlur = 0;
    }
    // If the speaker is I
    else if (speaker.equals("i")) {
      personType = 1; // First person
      singPlur = 1; // Singular
      infinitive = "to " + verbOperator;
    }
    
    
    // If the speaker is you
    else if (speaker.equals("you")){
      personType = 2; // Second person
      singPlur = 1; // Singular
      infinitive = "to " + verbOperator;
    }
    
    // If the speaker is is he, she, or one
    else if ((speaker.equals("he")) || (speaker.equals("she")) || speaker.equals("one")){
      personType = 3; // Third person
      singPlur = 1;  // Singular
      infinitive = "to " + verbOperator.substring(verbOperator.length()-1);
    }
    
    // If the subject is we     
    else if (speaker.equals("we")){
      personType = 1; // First person
      singPlur = 2; // Plural
      infinitive = "to " + verbOperator;
    }
    
    // If the speaker is you all
    else if (speaker.equals("you all")){
      personType = 2; // Second person
      singPlur = 2; // Plural
      infinitive = "to " + verbOperator;
      
    }
    
    // If the speaker is they
    else if (speaker.equals("they")){
      personType = 3; // Third person
      singPlur = 2; // Plural
      infinitive = "to " + verbOperator;
    }
    
    else{
      System.out.println("Invalid speaker");
      singPlur = -1;
      personType = -1;
    }
    
  }
  
  
  private int getSingPlur (){
    return singPlur;
  }
  
  private int getPersonType(){
    return personType;
  }
  
  
  private String getVerbOperator (){
    return verbOperator;
  }
  
  
  public String getInfinitive (){
    return infinitive; 
  }
  
  // Checks if the verb entered is the same as its infinitive
  private boolean isInfinitive(String verb){
    String inf = getInfinitive();
    if (inf.equals(verb)){
      return true;
    }
    return false;
  }
  
  public String translate()
  {
    String translated="";
    int person=this.getPersonType(); //get person
    int number=this.getSingPlur(); // get number 
    String inputFile="MonDictionnaire.txt"; 
    Dictionary dico= new Dictionary (inputFile); // create new dictionary
    String EnInfinitive=getInfinitive();
    String FrInfinitive = "unknown";
    for (String frenchVerb: dico.table.keySet()){ // Iterates through all of the values in the dictionary to see if the English infinitive exists
      if (dico.table.get(frenchVerb).equals(EnInfinitive)){
        FrInfinitive = frenchVerb; // If so, assign the French infinitive to be the key that coresponds to that particular value
        break;
      }
    }
    FrenchVerb fVerb=new FrenchVerb(FrInfinitive); 
    Hashtable<String, String> conjugated= fVerb.conjugateAll(fVerb);
    if (person==0 && number ==0){ // If there is no speaker
      if (isInfinitive(verbOperator) == true){ // And the verb is in its infinitive form
        translated = FrInfinitive; // The translated form is the infinitive of the verb
      }
    }
    if (person==1 && number==1) 
    {
      translated = "Je "+conjugated.get("je");
    }
    else if (person==2 && number==1)
    {
      translated = "Tu "+conjugated.get("tu");
    }
    else if (person==3 && number==1)
    {
      translated = "Elle "+conjugated.get("elle");
    }
    else if (person==1 && number==2)
    {
      translated = "Nous "+conjugated.get("nous");
    } 
    else if (person==2 && number==2)
    {
      translated = "Vous "+conjugated.get("vous");
    }
    else if (person==3 && number==2)
    {
      translated = "Elles "+conjugated.get("elles");
    }
    
    return translated;
  }
  
  // Key will be speaker and values will be conjugated verb
  // First check to see that the inputed verb (should be infinitive) is in the dictionary
  public static Hashtable conjugateAll (EnglishVerb inputWord){
    String myVerb = inputWord.getVerbOperator(); // Gets the string version of the inputed verb
    Hashtable<String, String> table = new Hashtable<String, String> (); // Creates a new hashtable
    Dictionary BilingualDictionary = new Dictionary ("MonDictionnaire.txt"); // Makes the dictionary
    if (BilingualDictionary.valueSearch(myVerb) == true){ // Checks to see that the verb is in the dictionary
      String[] parts = myVerb.split(" "); // Splits the user input by spaces
      myVerb = parts[1].toLowerCase(); // Eliminites the "to" when the user enters "to eat"
      table.put("I", myVerb);
      table.put("you", myVerb);
      table.put("she", myVerb + 's');
      table.put("we", myVerb);
      table.put("you all", myVerb);
      table.put("they", myVerb); 
      return table;
    }
    else if (!myVerb.substring(0,2).equals("to")){
      System.out.println("Please enter in the English infinitive in the form. For example: to ___ ");
    }
    else{ // The verb was not in the dictionary
      System.out.println("This verb could not be found in our dictionary");
    }
    return table; // Returns the hashtable of conjugations
  }
  
  
  
  
  public static void main (String[] args){
    Scanner scan = new Scanner(System.in);
    String input = scan.nextLine();
    EnglishVerb myVerb = new EnglishVerb(input);
    System.out.println(myVerb.translate());
}
}