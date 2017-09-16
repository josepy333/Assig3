// Card Class
public class Card
{
   enum Suit {clubs, diamonds, hearts, spades};
   private char value;
   private Suit suit;
   private boolean errorFlag;

   public Card(char value, Suit suit)
   {
      set(value, suit);
   }

   //  method to display the card if valid and Invalid Card otherwise
   @Override
   public String toString()
   {
      
      if (errorFlag)
      {
         return "***Invalid Card***"; 
      }
      return String.format("%s of %s", value, suit);
   }

   // mutator that accepts legal values 
   public boolean set(char value, Suit suit)
   {
      if (isValid(value, suit)) 
      {
         this.value = value;
         this.suit = suit;
         errorFlag = false;
      }
      else
      {
         errorFlag = true;
      }
      return errorFlag;
   }

   // accessor for suit
   public Suit getSuit()
   {
      return suit;
   }
   
   // accessor for value
   public char getValue()
   {
      return value;
   }
   
   // checks if all fields are identical 
   public boolean equals(Card card)
   {
      return suit.equals(card.suit) && value == card.value;
   }
   
   // checks if card value is valid
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
