import java.util.Scanner;
import java.util.*;

public class FrenchVerb extends Word{
  private int personType; // First, second, or third person
  private int singPlur; // 1 or 2
  private String verbType; // ER, IR, or RE
  private String infinitive; // The infinitive form of the verb
  
  private String speaker; // Identifies who is speaking
  private String verbOperator; // Identifies the verb
  private String testInfinitiveER; // A test to see if the verb is ER
  private String testInfinitiveRE; // A test to see if the verb is RE
  private Dictionary BilingualDictionary; // Dictionary to translate from French to English
  
  
  /* Constructor takes in the user's input and assigns the personType, singPlur, verbType, 
   * and infinitive variables.
   */
  
  
  public FrenchVerb (String userInput){
    super("verb");
    String[] parts = userInput.split(" "); // Splits the user input by spaces
    BilingualDictionary = new Dictionary ("MonDictionnaire.txt"); // Creates the dictionary
    if (parts.length == 2){ // If the user inputs two words, then one is the speaker and one is
      // the conjugated verb
      speaker = parts[0].toLowerCase(); // Gets the speaker
      verbOperator = parts[1].toLowerCase(); // Gets the conjugated verb
    }
    
    
    else{ // The user has only inputed one word
      verbOperator = parts[0].toLowerCase();
    }
    if (speaker == null){ // If the user Input has no speaker, set personType and singPlur to zero
      personType = 0;
      singPlur = 0;
      if (BilingualDictionary.searchWord(verbOperator) == true){  // If the verbOperator is an infinitive, assign infinitive
        infinitive = verbOperator;
      }
    }
    // If the speaker is "je" (I)
    else if (speaker.equals("je")) {
      personType = 1; // First person
      singPlur = 1; // Singular
      if (verbOperator.charAt(verbOperator.length() - 1) == 'e'){ // Example: je mange
        verbType = "ER";
        infinitive = verbOperator + "r";
      }
      else if (verbOperator.charAt(verbOperator.length() - 1) == 's'){ // Example: je finis
        verbType = "IR";
        infinitive = verbOperator.substring(0, verbOperator.length() -1) + "r";
      }
      else{  // Example: je prends
        verbType = "RE";
        infinitive = verbOperator + "re";  
      }
    }
    
    
    // If the speaker is "tu" (you, sing)
    else if (speaker.equals("tu")){
      personType = 2; // Second person
      singPlur = 1; // Singular
      if (verbOperator.substring(verbOperator.length() - 2).equals("es")){ // Example: tu manges
        verbType = "ER";
        infinitive = verbOperator.substring(0, verbOperator.length()-1) + "r";
      }
      else if (verbOperator.substring(verbOperator.length() - 2).equals("is")){ // Example: tu finis
        verbType = "IR";
        infinitive = verbOperator.substring(0, verbOperator.length() -1) + "r";
      }
      else{  // Example: tu prends
        verbType = "RE";
        infinitive = verbOperator + "re";  
      }
    }
    
    // If the speaker is is "il", "elle", or "on" (he, she, one)
    else if ((speaker.equals("il")) || (speaker.equals("elle")) || speaker.equals("on")){
      personType = 3; // Third person
      singPlur = 1;  // Singular
      if (verbOperator.charAt(verbOperator.length() - 1) == 'e'){ // Example: il mange
        verbType = "ER";
        infinitive = verbOperator + "r";
      }
      else if (verbOperator.charAt(verbOperator.length() - 1) == 't'){ // Example: elle finit
        verbType = "IR";
        infinitive = verbOperator.substring(0, verbOperator.length() -1) + "r";
      }
      else{
        verbType = "RE"; // Example: on prend
        infinitive = verbOperator.substring(0, verbOperator.length() -1) + "re";  
      }
    }
    
    // If the subject is "nous" (we)     
    else if (speaker.equals("nous")){
      personType = 1; // First person
      singPlur = 2; // Plural
      testInfinitiveER = verbOperator.substring(0, verbOperator.length() - 3) + "er"; // Gets the possible infinitive ER verb
      testInfinitiveRE = verbOperator.substring(0, verbOperator.length() - 3) + "re"; // Gets the possible infinitive RE verb
      if (verbOperator.substring(verbOperator.length() -6, verbOperator.length()).equals ("issons")){
        // Example: nous finissons
        verbType = "IR";
        infinitive = verbOperator.substring(0, verbOperator.length()-6) + "ir";
      }
      if (BilingualDictionary.searchWord(testInfinitiveER) == true){ // Tests if the ER infinitive exists
        verbType = "ER";
        infinitive = testInfinitiveER;
      }
      if (BilingualDictionary.searchWord(testInfinitiveRE) == true){ // Tests if the RE infinitive exists
        verbType = "RE";
        infinitive = testInfinitiveRE;
      }
      
    }
    
    // If the speaker is "vous" (you all, or you (formal))
    else if (speaker.equals("vous")){
      personType = 2; // Second person
      singPlur = 2; // Plural
      testInfinitiveER = verbOperator.substring(0, verbOperator.length() - 2) + "er"; // Gets the possible infinitive ER verb
      testInfinitiveRE = verbOperator.substring(0, verbOperator.length() - 2) + "re"; // Gets the possible infinitive RE verb
      if (verbOperator.substring(verbOperator.length() -5, verbOperator.length()).equals ("issez")){
        // Example: vous finissez
        verbType = "IR";
        infinitive = verbOperator.substring(0, verbOperator.length()-5) + "ir";
      }
      if (BilingualDictionary.searchWord(testInfinitiveER) == true){ // Tests if the ER infinitive exists
        verbType = "ER";
        infinitive = testInfinitiveER;
      }
      else if (BilingualDictionary.searchWord(testInfinitiveRE) == true){ // Tests if the RE infinitive exists
        verbType = "RE";
        infinitive = testInfinitiveRE;
      }
      
    }
    
    // If the speaker is "ils" or "elles" (they, masculine or feminine)
    else if (speaker.equals("ils") || speaker.equals("elles")){
      personType = 3; // Third person
      singPlur = 2; // Plural
      testInfinitiveER = verbOperator.substring(0, verbOperator.length() - 3) + "er"; // Gets the possible infinitive ER verb
      testInfinitiveRE = verbOperator.substring(0, verbOperator.length() - 3) + "re"; // Gets the possible infinitive RE verb
      if (verbOperator.substring(verbOperator.length() -6, verbOperator.length()).equals ("issent")){
        // Example: ils finissent
        verbType = "IR";
        infinitive = verbOperator.substring(0, verbOperator.length()-6) + "ir";
      }
      if (BilingualDictionary.searchWord(testInfinitiveER) == true){ // Tests if the ER infinitive exists
        verbType = "ER";
        infinitive = testInfinitiveER;
      }
      if (BilingualDictionary.searchWord(testInfinitiveRE) == true){ // Tests if the RE infinitive exists
        verbType = "RE";
        infinitive = testInfinitiveRE;
      }
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
  
  private String getVerbType (){
    return verbType;
  }
  
  private String getVerbOperator (){
    return verbOperator;
  }
  
  // Given the infinitive, checks the last three letters to get the verb type
  private static String calculateVerbType(String inf){ 
    return inf.substring(inf.length() -2);
  }
  
  private String getInfinitive (){
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
    String FrInfinitive = getInfinitive(); // Finds the French infinitive form of the verb
    String enInfinitive=dico.table.get(FrInfinitive); // Gets the English infinitive form of the verb
    EnglishVerb eVerb=new EnglishVerb(enInfinitive); // Makes a new EnglishVerb object
    Hashtable<String, String> conjugated= eVerb.conjugateAll(eVerb); // Conjugates the English verb given the English infinitive
    if (person==0 && number ==0){
      if (isInfinitive(verbOperator) == true){ // If there is no speaker and the verb is an infinitive
        translated = dico.table.get(verbOperator); // The English translation is just the infinitive
      }
    }
    else if (person==1 && number==1) // Uses the hashtable to the conjugated English translation
    {
      translated = "I "+conjugated.get("I");
    }
    else if (person==2 && number==1)
    {
      translated = "you "+conjugated.get("you");
    }
    else if (person==3 && number==1)
    {
      translated = "she "+conjugated.get("she");
    }
    else if (person==1 && number==2)
    {
      translated = "we "+conjugated.get("we");
    } 
    else if (person==2 && number==2)
    {
      translated = "you all "+conjugated.get("you all");
    }
    else if (person==3 && number==2)
    {
      translated = "they "+conjugated.get("they");
    }
    return translated;
  }
  
  
  // Key will be speaker and values will be conjugated verb
  // First check to see that the inputed verb (should be infinitive) is in the dictionary
  public static Hashtable conjugateAll (FrenchVerb inputWord){
    String myVerb = inputWord.getVerbOperator(); // Gets the string version of the inputed verb
    Hashtable<String, String> table = new Hashtable<String, String> (); // Creates a new hashtable
    Dictionary BilingualDictionary = new Dictionary ("MonDictionnaire.txt"); // Makes the dictionary
    if (BilingualDictionary.searchWord(myVerb) == true){ // Checks to see that the verb is in the dictionary
      String type = calculateVerbType(myVerb); // Calculates the verb type for conjugating
      myVerb = myVerb.substring(0, myVerb.length() -2);
      if (type.equals("er")){ // Adds an exception if the verb is appeler, for example
        if (myVerb.charAt(myVerb.length()-1) == 'l'){
          table.put("je", myVerb + "le");
          table.put("tu", myVerb + "les");
          table.put("elle", myVerb + "le");
          table.put("nous", myVerb + "ons");
          table.put("vous", myVerb + "ez");
          table.put("elles", myVerb + "lent"); 
          return table;
        }
        else{
          // Conjugates according to basic "er" verb rules
          table.put("je", myVerb + 'e');
          table.put("tu", myVerb + "es");
          table.put("elle", myVerb + 'e');
          table.put("nous", myVerb + "ons");
          table.put("vous", myVerb + "ez");
          table.put("elles", myVerb + "ent"); 
          return table;
        }
      }
      if (type.equals("ir")){
        // Conjugates according to "ir" verb rules
        table.put("je", myVerb + "is");
        table.put("tu", myVerb + "is");
        table.put("elle", myVerb + "it");
        table.put("nous", myVerb + "issons");
        table.put("vous", myVerb + "issez");
        table.put("elles", myVerb + "issent"); 
        return table;
      }
      if (type.equals("re")){
        // Conjugates according to "re" verb rules
        table.put("je", myVerb + 's');
        table.put("tu", myVerb + 's');
        table.put("elle", myVerb);
        table.put("nous", myVerb + "ons");
        table.put("vous", myVerb + "ez");
        table.put("elles", myVerb + "ent");
        return table;
      }
      
      else{ // The verb is none of the three regular types
        System.out.println("This is an irregular verb.");
      }
    }
    else{ // The verb was not in the dictionary
      System.out.println("This verb could not be found in our dictionary");
    }
    return table; // Returns the hashtable of conjugations
  }
  
  
  
  
  public static void main (String[] args){
    Scanner scan = new Scanner(System.in);
    String input = scan.nextLine();
    FrenchVerb myVerb = new FrenchVerb(input);
    System.out.println(myVerb.translate()); 
  }
}