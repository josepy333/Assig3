// Card Class

public class Card
{
   enum Suit {clubs, diamonds, hearts, spades};
   private char value;
   private Suit suit;
   private boolean errorFlag;

   public Card(char value, Suit suit)
   {
      setValue();
      setSuit();
      setCard(value, suit);
   }

   // this method displays the card if valid and Invalid Card otherwise
   public String toString()
   {
      String output = "";
      if (errorFlag == true)
      {
         output = "Invalid Card"; 
      }
      else 
      {

      }
      return output;
   }

   // mutator that accepts legal values 
   public boolean set(char value, Suit suit)
   {
      this.value = value;
      this.suit = suit;
      return errorFlag;
   }

   // accessor for suit
   public String getSuit()
   {

   }

   public String getValue()
   {

   }

   public boolean equals(Card card)
   {

   }

   private boolean isValid(char value, Suit suit)
   {
      switch(value)
      {
         case 'A':
         case '2':
         case '3':
         case '4':
         case '5':
         case '6':
         case '7':
         case '8':
         case '9':
         case 'T':
         case 'J':
         case 'Q':
         case 'K':
            return true;
         default:
            return false;
      }
      
   }

}
