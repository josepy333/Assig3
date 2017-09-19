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
   public static Scanner keyboard = new Scanner(System.in);

   public static void main(String[] args)
   {
      int numPlayers = 0;
      int handNumber = 0;
      int input;
                
      // Get input from user and check value
      System.out.println("Please enter the number of players (1 - 10):");
      input = keyboard.nextInt();
        
      while ((input < 1) || input > 10)
      {
         System.out.println("Your entry was not recognized.");
         System.out.println("Please enter the number of players (1 - 10):");
         input = keyboard.nextInt();
      }
                
      numPlayers = input;
        
      // Create the deck
      Deck deck = new Deck();
      deck.init(1);
        
      // Create array of hand objects
      Hand [] playerHand = new Hand[numPlayers];
      
      for (int i = 0; i < numPlayers; i++)
      {
         playerHand[i] = new Hand();
      }
      
      
        
      // Deal the unshuffled deck out to the players
      while (deck.getTopCard() >= 0)
      {
         for (int i = 0; i < numPlayers; i++)
         {
            playerHand[i].takeCard(deck.dealCard());
         }
      }
        
      // Display the hands
      System.out.println('\n' + "Dealing the unsorted deck resulted in the following hands:");
        
      for (int i = 0; i < numPlayers; i++)
      {
         handNumber = i + 1;
         System.out.println('\n' + "Hand number " + handNumber + ": ");
         System.out.println(playerHand[i].toString());
      }
        
      // Reset the hands
      for (int i = 0; i < numPlayers; i++)
      {
         playerHand[i].resetHand();
      }
        
      // Re-populate deck and shuffle it
      deck.init(1);
      deck.shuffle();
      handNumber = 0;
        
      // Deal the shuffled hands
      while (deck.getTopCard() >= 0)
      {
         for (int i = 0; i < numPlayers; i++)
         {
            playerHand[i].takeCard(deck.dealCard());
         }
      }
      
      System.out.println("Test");
      
        
      // Display the hands
      System.out.println('\n' + "Dealing the sorted deck resulted in the following hands:");

      for (int i = 0; i < numPlayers; i++)
      {
         handNumber = i + 1;
         System.out.println('\n' + "Hand number " + handNumber + ": ");
         System.out.println(playerHand[i].toString());
      }
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
      myCards = new Card[MAX_CARDS];
      numUsed = 0;
   }
   
   /* Method that adds a card to the next available position in the myCards
    * array
    */
   public boolean takeCard(Card card)
   {
      if (numUsed > myCards.length)
      {
         System.out.println("Error: The hand is full.");
         return false;
      }
      else
      {
           myCards[numUsed] = new Card(card);
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
      for (int i =0; i < numUsed; i++)
      {
         handString = handString + " " + myCards[i] + " /";
         if (i % 6 == 0 && i != 0)
         {
            handString = handString +"\n";
         }
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

/*
 *  Deck class
 */
class Deck {
 
  public final int MAX_CARDS = 6*52;
  
  private static Card[] masterPack;
  
  private Card[] cards;
  private int topCard;
  private int numPacks;
  
  // Constructor
  public Deck() 
  {
      init(1);
  }
  
  // Constructor
  public Deck(int numPacks) 
  {
      init(numPacks);
  }
  
  // Method to initialize Deck
  public void init(int numPacks) 
  {
      allocateMasterPack();
      this.numPacks = numPacks;
      cards = new Card[52*numPacks];
      int i = 0;
      for (int j = 0; j < numPacks; j++) 
      {
          for (int k = 0; k < 52; k++) 
          {
              cards[i++] = masterPack[k];
          }
      }
      this.topCard = 52 * numPacks - 1;
  }
  
  // Method to shuffle Deck
  public void shuffle() 
  {
      for (int i = 0; i < cards.length; i++) 
      {
          Card original = cards[i];
          int j = (int)(Math.random() * cards.length);
          cards[i] = cards[j];
          cards[j] = original;
      }
  }
  
  // Method to deal Card from Deck
  public Card dealCard() 
  {
      if (topCard >= 0) 
      {
          return cards[topCard--];
      } else {
          return null;
      }
  }
  
  // Method to get index of top Card
  public int getTopCard() 
  {
      return topCard;
  }
  
  // Method to inspect Card
  public Card inspectCard(int k) 
  {
      if (k < 0 || k >= topCard) 
      {
          return new Card('0', Card.Suit.spades);
      } else 
      {
          return cards[k];
      }
  }
  
  // Method to allocate Master Deck
  private static void allocateMasterPack() 
  {
    if (masterPack == null) 
    {
      masterPack = new Card[52];
      Card.Suit[] suits = {Card.Suit.clubs, Card.Suit.diamonds, Card.Suit.hearts, Card.Suit.spades};
      String values = "A23456789TJQK";
      int i = 0;
      for (Card.Suit suit: suits) 
      {
        for (char value: values.toCharArray()) 
        {
          Card card = new Card(value, suit);
          masterPack[i++] = card;
        }
      }
    }
  }
  
  // Main method which executes test code for Deck
  public static void main(String[] args) 
  {
      System.out.println("Deck of 2 packs of cards:");
      Deck deck = new Deck(2);
      System.out.println("Dealing all unshuffled cards");
      while (deck.getTopCard() >= 0) 
      {
          Card card = deck.dealCard();
          System.out.print(card + " / ");
      }
      System.out.println();
      deck = new Deck(2);
      deck.shuffle();
      System.out.println("Dealing all SHUFFLED cards");
      while (deck.getTopCard() >= 0) 
      {
          Card card = deck.dealCard();
          System.out.print(card + " / ");
      }
      System.out.println("\n");
      System.out.println("Deck of 1 pack of cards:");
      deck = new Deck(1);
      System.out.println("Dealing all unshuffled cards");
      while (deck.getTopCard() >= 0) 
      {
          Card card = deck.dealCard();
          System.out.print(card + " / ");
      }
      System.out.println();
      deck = new Deck(1);
      deck.shuffle();
      System.out.println("Dealing all SHUFFLED cards");
      while (deck.getTopCard() >= 0) 
      {
          Card card = deck.dealCard();
          System.out.print(card + " / ");
      }
      System.out.println();      
  }
  
}