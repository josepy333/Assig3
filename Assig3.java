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
      
      //********** Phase 1 - Test for Card Class **********
      // Create 3 cards - 2 valid and 1 invalid
      Card testCard1 = new Card('Q', Card.Suit.spades); // Valid
      Card testCard2 = new Card('K', Card.Suit.spades); // Valid
      Card testCard3 = new Card('0', Card.Suit.spades); // Invalid
      
      System.out.println("Test for Card Class (Phase 1):");
      System.out.println("Valid Card: " + testCard1.toString());
      System.out.println("Valid Card: " + testCard2.toString());
      System.out.println("Invalid Card: " + testCard3.toString());
      
      // Modify one valid card to be invalid and make the previously invalid card valid
      testCard1.set('0', Card.Suit.spades);
      testCard3.set('4', Card.Suit.spades);
      
      System.out.println("Valid card invalidated: " + testCard1.toString());
      System.out.println("Unchanged card: " + testCard2.toString());
      System.out.println("Invalid card validated: " + testCard3.toString());
      
      //********* Phase 2 - Test for Hand Class ***********
      
      // Create 3 test cards
      Card testCard4 = new Card ('3', Card.Suit.clubs);
      Card testCard5 = new Card ('T', Card.Suit.clubs);
      Card testCard6 = new Card ('9', Card.Suit.hearts);
      
      System.out.println('\n' + "Test for Hand Class ( Phase 2):");
      
      // Creat test hand
      Hand testHand = new Hand();
      testHand.setNumCards(50);
      
      Card [] testCardArray = new Card[3];
      testCardArray[0] = testCard4;
      testCardArray[1] = testCard5;
      testCardArray[2] = testCard6;
      
      System.out.println("Hand full");
      
      for (int i = 0, j = 0; i < testHand.getNumCards(); i++)
      {
         if (j > 2) 
            j = 0;
         testHand.takeCard(testCardArray[j]);
         j++;  
      }
      
      System.out.println("After deal");
      
      System.out.println("Hand = ( " + testHand.toString() + " )");
      System.out.println();
      
      for (int i = 0; i <testHand.getNumCards(); i++)
      {
         System.out.println("Playing " + testHand.playCard());
      }
      
      System.out.println();
      System.out.println("After playing all cards");
      System.out.println( "Hand = ( " + testHand.toString() + " )");
      
      System.out.println("Testing inspectCard() with a legal value:");
      System.out.println(testHand.inspectCard(1));
      
      System.out.println("Testing inspectCard() with an illegal value");
      System.out.println(testHand.inspectCard(52));
      
      //********** Phase 3 - Test for Deck Class **********
      
      System.out.println('\n' + "Test for Deck Class (Phase 3):");
      System.out.println("Deck of 2 packs of cards:");
      Deck testDeck = new Deck(2);
      System.out.println("Dealing all unshuffled cards");
      
      while (testDeck.getTopCard() >= 0) 
      {
          Card card = testDeck.dealCard();
          System.out.print(card + " / ");
      }
      
      testDeck = new Deck(2);
      testDeck.shuffle();
      System.out.println('\n' + "Dealing all SHUFFLED cards");
      
      while (testDeck.getTopCard() >= 0) 
      {
          Card card = testDeck.dealCard();
          System.out.print(card + " / ");
      }
      
      System.out.println('\n' + "Deck of 1 pack of cards:");
      testDeck = new Deck(1);
      System.out.println("Dealing all unshuffled cards");
      
      while (testDeck.getTopCard() >= 0) 
      {
          Card card = testDeck.dealCard();
          System.out.print(card + " / ");
      }
      
      testDeck = new Deck(1);
      testDeck.shuffle();
      System.out.println('\n' + "Dealing all SHUFFLED cards");
      
      while (testDeck.getTopCard() >= 0) 
      {
          Card card = testDeck.dealCard();
          System.out.print(card + " / ");
      }
      System.out.println();
      
      
      // ********** Phase 4 **********
      
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
            if (deck.getTopCard() < 0 )
               break;
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
            if (deck.getTopCard() < 0 )
               break;
            playerHand[i].takeCard(deck.dealCard());
         }
      }
      
        
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
   public static int MAX_CARDS = 100;
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
   
   // returns and removes the card in the top occupied position of the array.
   public Card playCard()
   {
      int topCard = numUsed - 1;
      numUsed--;
      return myCards[topCard];
   }
   
   // Accessor for an individual card
   public Card inspectCard(int k)
   {
      if ((k >= 0) && (k <= numCards))
      {
         return myCards[k];
      }
      else
      {
         Card bogusCard = new Card('G', Card.Suit.hearts);
         return bogusCard;
      }
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
   
   public int getNumUsed()
   {
      return numUsed;
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
}


/* ********** RUN **********
Test for Card Class (Phase 1):
Valid Card: Q of spades
Valid Card: K of spades
Invalid Card: ***Invalid Card***
Valid card invalidated: ***Invalid Card***
Unchanged card: K of spades
Invalid card validated: 4 of spades

Test for Hand Class ( Phase 2):
Hand full
After deal
Hand = (  3 of clubs / T of clubs / 9 of hearts / 3 of clubs / T of clubs / 9 of hearts / 3 of clubs /
 T of clubs / 9 of hearts / 3 of clubs / T of clubs / 9 of hearts / 3 of clubs /
 T of clubs / 9 of hearts / 3 of clubs / T of clubs / 9 of hearts / 3 of clubs /
 T of clubs / 9 of hearts / 3 of clubs / T of clubs / 9 of hearts / 3 of clubs /
 T of clubs / 9 of hearts / 3 of clubs / T of clubs / 9 of hearts / 3 of clubs /
 T of clubs / 9 of hearts / 3 of clubs / T of clubs / 9 of hearts / 3 of clubs /
 T of clubs / 9 of hearts / 3 of clubs / T of clubs / 9 of hearts / 3 of clubs /
 T of clubs / 9 of hearts / 3 of clubs / T of clubs / 9 of hearts / 3 of clubs /
 T of clubs / )

Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs

After playing all cards
Hand = (  )
Testing inspectCard() with a legal value:
T of clubs
Testing inspectCard() with an illegal value
***Invalid Card***

Test for Deck Class (Phase 3):
Deck of 2 packs of cards:
Dealing all unshuffled cards
K of spades / Q of spades / J of spades / T of spades / 9 of spades / 8 of spades / 7 of spades / 6 of spades / 5 of spades / 4 of spades / 3 of spades / 2 of spades / A of spades / K of hearts / Q of hearts / J of hearts / T of hearts / 9 of hearts / 8 of hearts / 7 of hearts / 6 of hearts / 5 of hearts / 4 of hearts / 3 of hearts / 2 of hearts / A of hearts / K of diamonds / Q of diamonds / J of diamonds / T of diamonds / 9 of diamonds / 8 of diamonds / 7 of diamonds / 6 of diamonds / 5 of diamonds / 4 of diamonds / 3 of diamonds / 2 of diamonds / A of diamonds / K of clubs / Q of clubs / J of clubs / T of clubs / 9 of clubs / 8 of clubs / 7 of clubs / 6 of clubs / 5 of clubs / 4 of clubs / 3 of clubs / 2 of clubs / A of clubs / K of spades / Q of spades / J of spades / T of spades / 9 of spades / 8 of spades / 7 of spades / 6 of spades / 5 of spades / 4 of spades / 3 of spades / 2 of spades / A of spades / K of hearts / Q of hearts / J of hearts / T of hearts / 9 of hearts / 8 of hearts / 7 of hearts / 6 of hearts / 5 of hearts / 4 of hearts / 3 of hearts / 2 of hearts / A of hearts / K of diamonds / Q of diamonds / J of diamonds / T of diamonds / 9 of diamonds / 8 of diamonds / 7 of diamonds / 6 of diamonds / 5 of diamonds / 4 of diamonds / 3 of diamonds / 2 of diamonds / A of diamonds / K of clubs / Q of clubs / J of clubs / T of clubs / 9 of clubs / 8 of clubs / 7 of clubs / 6 of clubs / 5 of clubs / 4 of clubs / 3 of clubs / 2 of clubs / A of clubs / 
Dealing all SHUFFLED cards
K of clubs / 5 of spades / 7 of hearts / T of clubs / 8 of spades / J of clubs / Q of spades / 6 of hearts / 2 of clubs / 8 of spades / J of diamonds / 4 of hearts / 5 of clubs / 8 of diamonds / 9 of clubs / A of hearts / J of clubs / Q of clubs / 3 of spades / 5 of spades / 3 of clubs / K of hearts / 5 of clubs / Q of hearts / 2 of diamonds / 9 of clubs / J of hearts / 6 of hearts / 9 of spades / 6 of clubs / 9 of diamonds / T of diamonds / J of spades / 4 of diamonds / 5 of hearts / 6 of spades / 4 of diamonds / 7 of clubs / 3 of diamonds / A of clubs / 4 of clubs / T of hearts / K of diamonds / 9 of hearts / Q of hearts / K of spades / 4 of spades / J of hearts / 6 of clubs / A of spades / 7 of diamonds / A of hearts / 8 of clubs / 7 of hearts / 7 of diamonds / 7 of spades / 9 of spades / 5 of diamonds / J of spades / 5 of diamonds / K of hearts / 2 of diamonds / K of spades / T of spades / 3 of hearts / 2 of hearts / 9 of diamonds / Q of clubs / 3 of hearts / 6 of spades / 7 of clubs / 2 of hearts / 4 of clubs / 2 of clubs / 9 of hearts / 4 of hearts / 3 of spades / 6 of diamonds / Q of diamonds / 6 of diamonds / 2 of spades / 2 of spades / A of spades / A of clubs / Q of diamonds / 8 of hearts / A of diamonds / 3 of clubs / 4 of spades / 7 of spades / 8 of diamonds / T of diamonds / A of diamonds / T of clubs / T of spades / J of diamonds / 5 of hearts / K of clubs / T of hearts / 8 of hearts / K of diamonds / 8 of clubs / Q of spades / 3 of diamonds / 
Deck of 1 pack of cards:
Dealing all unshuffled cards
K of spades / Q of spades / J of spades / T of spades / 9 of spades / 8 of spades / 7 of spades / 6 of spades / 5 of spades / 4 of spades / 3 of spades / 2 of spades / A of spades / K of hearts / Q of hearts / J of hearts / T of hearts / 9 of hearts / 8 of hearts / 7 of hearts / 6 of hearts / 5 of hearts / 4 of hearts / 3 of hearts / 2 of hearts / A of hearts / K of diamonds / Q of diamonds / J of diamonds / T of diamonds / 9 of diamonds / 8 of diamonds / 7 of diamonds / 6 of diamonds / 5 of diamonds / 4 of diamonds / 3 of diamonds / 2 of diamonds / A of diamonds / K of clubs / Q of clubs / J of clubs / T of clubs / 9 of clubs / 8 of clubs / 7 of clubs / 6 of clubs / 5 of clubs / 4 of clubs / 3 of clubs / 2 of clubs / A of clubs / 
Dealing all SHUFFLED cards
Q of hearts / 4 of clubs / Q of diamonds / 5 of clubs / 8 of clubs / J of spades / 9 of clubs / A of spades / 9 of hearts / 6 of diamonds / 7 of hearts / T of diamonds / 8 of diamonds / 7 of spades / Q of clubs / 4 of spades / 8 of hearts / T of clubs / A of diamonds / Q of spades / J of hearts / T of hearts / 6 of hearts / 4 of hearts / 3 of clubs / K of hearts / K of spades / A of clubs / T of spades / K of diamonds / 6 of clubs / 9 of spades / 8 of spades / A of hearts / 5 of diamonds / 5 of spades / 6 of spades / 2 of clubs / 4 of diamonds / 2 of hearts / 3 of diamonds / J of clubs / 5 of hearts / 3 of hearts / 2 of diamonds / K of clubs / 3 of spades / 7 of diamonds / 9 of diamonds / J of diamonds / 7 of clubs / 2 of spades / 
Please enter the number of players (1 - 10):
2

Dealing the unsorted deck resulted in the following hands:

Hand number 1: 
 K of spades / J of spades / 9 of spades / 7 of spades / 5 of spades / 3 of spades / A of spades /
 Q of hearts / T of hearts / 8 of hearts / 6 of hearts / 4 of hearts / 2 of hearts /
 K of diamonds / J of diamonds / 9 of diamonds / 7 of diamonds / 5 of diamonds / 3 of diamonds /
 A of diamonds / Q of clubs / T of clubs / 8 of clubs / 6 of clubs / 4 of clubs /
 2 of clubs /

Hand number 2: 
 Q of spades / T of spades / 8 of spades / 6 of spades / 4 of spades / 2 of spades / K of hearts /
 J of hearts / 9 of hearts / 7 of hearts / 5 of hearts / 3 of hearts / A of hearts /
 Q of diamonds / T of diamonds / 8 of diamonds / 6 of diamonds / 4 of diamonds / 2 of diamonds /
 K of clubs / J of clubs / 9 of clubs / 7 of clubs / 5 of clubs / 3 of clubs /
 A of clubs /

Dealing the sorted deck resulted in the following hands:

Hand number 1: 
 7 of spades / K of clubs / T of clubs / 7 of hearts / 3 of hearts / Q of clubs / A of spades /
 A of clubs / 7 of diamonds / 3 of spades / 6 of spades / 2 of hearts / 8 of hearts /
 J of hearts / 5 of spades / Q of diamonds / 8 of spades / 5 of diamonds / 6 of hearts /
 4 of hearts / A of diamonds / 8 of diamonds / A of hearts / 5 of hearts / 9 of spades /
 Q of hearts /

Hand number 2: 
 9 of hearts / 4 of diamonds / J of clubs / 9 of diamonds / 5 of clubs / 4 of spades / J of spades /
 2 of diamonds / 2 of spades / Q of spades / 6 of clubs / 7 of clubs / K of spades /
 3 of clubs / K of hearts / T of diamonds / K of diamonds / T of hearts / 3 of diamonds /
 T of spades / 2 of clubs / 9 of clubs / J of diamonds / 8 of clubs / 4 of clubs /
 6 of diamonds /
********** */