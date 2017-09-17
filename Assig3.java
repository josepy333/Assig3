/**
 * 
 * Joseph Cortez
 * Lyndsay Hackett
 * Mokhlis Awad
 * Ahdia Fuller
 *
 * Assig3 Class: Set of classes to use for playing card games
 *
 */

import java.util.*;
import java.lang.Math;


public class Assig3
{

   public static void main(String[] args)
   {
      // TODO Auto-generated method stub

   }

}

//Card Class
class Card
{
   enum Suit {clubs, diamonds, hearts, spades};
   private char value;
   private Suit suit;
   private boolean errorFlag;

   public Card(char value, Suit suit)
   {
      set(value, suit);
   }
   
   // Copy Constructor
   public Card(Card aCard)
   {
      if (aCard == null)      // Not a real card
      {
         System.out.println("Fatal Error.");
         System.exit(0);
      }
      
      /*
       *  Want to check this
       */
      value = aCard.value;
      suit = aCard.suit;
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

// Hand class
class Hand
{
   public static int MAX_CARDS = 50;
   private Card[] myCards;
   private int numCards;
   private int numUsed;       // Number of indeces currently in use
   private boolean errorFlag;
   
   // Default Constructor
   public Hand()
   {
      myCards = new Card[MAX_CARDS];
      numCards = 1;
   }
   
   // Copy constructor
   public Hand(Hand object)
   {
      int lengthOfArrays = object.myCards.length;
      this.myCards = new Card[lengthOfArrays];
      for (int i =0; i < lengthOfArrays; i++)
         this.myCards[i] = new Card(object.myCards[i]);
   }
   
   // Method that removes all cards from the hand
   public void resetHand()
   {
      myCards = new Card[numCards];
   }
   
   /* Method that adds a card to the next available position in the myCards
    * array
    */
   public boolean takeCard(Card card)
   {
      if (numUsed >= myCards.length)
      {
         System.out.println("Error: The hand is full.");
         return false;
      }
      else
      {
           myCards[numUsed] = card;
           numUsed++;
           return true;
      }
   }
   
   // Accessor for an individual card
   public Card inspectCard(int k)
   {
      Card[] temp = new Card[myCards.length];
      if (k >= myCards.length)
      {
         errorFlag = true;
      }
      else
      {
         
         for (int i = 0; i < myCards.length; i++)
         {
            temp[i] = new Card(myCards[i]);
         }
      }
      return temp[k];
   }
   
   // Converts hand to a string and displays the entire String
   public String toString()
   {
      String handString = "";
      if (errorFlag)
      {
         return "***Invalid Hand***"; 
      }
      for (int i =0; i < myCards.length; i++)
      {
         handString = handString + " " + myCards[i];
      }
      return handString; 
   }
   
   // Mutator method for numCards
   public void setNumCards(int num)
   {
      numCards = num;
   }
   
   // Accessor method for numCards
   public int getNumCards()
   {
      return numCards;
   }
   
}
