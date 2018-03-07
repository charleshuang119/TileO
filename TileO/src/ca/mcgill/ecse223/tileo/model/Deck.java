/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

/**
 * A deck of 32 action cards, having different numbers of cards of each type. The deck is defined by the top card of the stack, which is the card drawn by a player when he/she lands on an action tile. This top card will be determined by a drawCard method to be implemented later
 */
// line 71 "../../../../../TileO.ump"
public class Deck extends Element
{

  //------------------------
  // STATIC VARIABLES
  //------------------------


  /**
   * Since there will always be 32 ActionCards in a deck
   */
  public static final int NUMBEROFCARDS = 32;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Deck Attributes
  private int numberOfDieRollCards;
  private int numberOfConnectionCards;
  private int numberOfRemovalCards;
  private int numberOfTeleportCards;
  private int numberOfTurnLossCards;
  private ActionCard topCard;

  //Deck Associations
  private List<ActionCard> actionCards;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Deck(SpecificGame aBoardgame, int aNumberOfDieRollCards, int aNumberOfConnectionCards, int aNumberOfRemovalCards, int aNumberOfTeleportCards, int aNumberOfTurnLossCards)
  {
    super(aBoardgame);
    numberOfDieRollCards = aNumberOfDieRollCards;
    numberOfConnectionCards = aNumberOfConnectionCards;
    numberOfRemovalCards = aNumberOfRemovalCards;
    numberOfTeleportCards = aNumberOfTeleportCards;
    numberOfTurnLossCards = aNumberOfTurnLossCards;
    actionCards = new ArrayList<ActionCard>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberOfDieRollCards(int aNumberOfDieRollCards)
  {
    boolean wasSet = false;
    numberOfDieRollCards = aNumberOfDieRollCards;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfConnectionCards(int aNumberOfConnectionCards)
  {
    boolean wasSet = false;
    numberOfConnectionCards = aNumberOfConnectionCards;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfRemovalCards(int aNumberOfRemovalCards)
  {
    boolean wasSet = false;
    numberOfRemovalCards = aNumberOfRemovalCards;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfTeleportCards(int aNumberOfTeleportCards)
  {
    boolean wasSet = false;
    numberOfTeleportCards = aNumberOfTeleportCards;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfTurnLossCards(int aNumberOfTurnLossCards)
  {
    boolean wasSet = false;
    numberOfTurnLossCards = aNumberOfTurnLossCards;
    wasSet = true;
    return wasSet;
  }

  public boolean setTopCard(ActionCard aTopCard)
  {
    boolean wasSet = false;
    topCard = aTopCard;
    wasSet = true;
    return wasSet;
  }

  public int getNumberOfDieRollCards()
  {
    return numberOfDieRollCards;
  }

  public int getNumberOfConnectionCards()
  {
    return numberOfConnectionCards;
  }

  public int getNumberOfRemovalCards()
  {
    return numberOfRemovalCards;
  }

  public int getNumberOfTeleportCards()
  {
    return numberOfTeleportCards;
  }

  public int getNumberOfTurnLossCards()
  {
    return numberOfTurnLossCards;
  }

  /**
   * number of cards == 32
   * will have a method that will choose a random card depending cards in the deck
   */
  public ActionCard getTopCard()
  {
    return topCard;
  }

  public ActionCard getActionCard(int index)
  {
    ActionCard aActionCard = actionCards.get(index);
    return aActionCard;
  }

  public List<ActionCard> getActionCards()
  {
    List<ActionCard> newActionCards = Collections.unmodifiableList(actionCards);
    return newActionCards;
  }

  public int numberOfActionCards()
  {
    int number = actionCards.size();
    return number;
  }

  public boolean hasActionCards()
  {
    boolean has = actionCards.size() > 0;
    return has;
  }

  public int indexOfActionCard(ActionCard aActionCard)
  {
    int index = actionCards.indexOf(aActionCard);
    return index;
  }

  public boolean isNumberOfActionCardsValid()
  {
    boolean isValid = numberOfActionCards() >= minimumNumberOfActionCards() && numberOfActionCards() <= maximumNumberOfActionCards();
    return isValid;
  }

  public static int requiredNumberOfActionCards()
  {
    return 32;
  }

  public static int minimumNumberOfActionCards()
  {
    return 32;
  }

  public static int maximumNumberOfActionCards()
  {
    return 32;
  }

  public ActionCard addActionCard()
  {
    if (numberOfActionCards() >= maximumNumberOfActionCards())
    {
      return null;
    }
    else
    {
      return new ActionCard(this);
    }
  }

  public boolean addActionCard(ActionCard aActionCard)
  {
    boolean wasAdded = false;
    if (actionCards.contains(aActionCard)) { return false; }
    if (numberOfActionCards() >= maximumNumberOfActionCards())
    {
      return wasAdded;
    }

    Deck existingDeck = aActionCard.getDeck();
    boolean isNewDeck = existingDeck != null && !this.equals(existingDeck);

    if (isNewDeck && existingDeck.numberOfActionCards() <= minimumNumberOfActionCards())
    {
      return wasAdded;
    }

    if (isNewDeck)
    {
      aActionCard.setDeck(this);
    }
    else
    {
      actionCards.add(aActionCard);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeActionCard(ActionCard aActionCard)
  {
    boolean wasRemoved = false;
    //Unable to remove aActionCard, as it must always have a deck
    if (this.equals(aActionCard.getDeck()))
    {
      return wasRemoved;
    }

    //deck already at minimum (32)
    if (numberOfActionCards() <= minimumNumberOfActionCards())
    {
      return wasRemoved;
    }
    actionCards.remove(aActionCard);
    wasRemoved = true;
    return wasRemoved;
  }

  public void delete()
  {
    while (actionCards.size() > 0)
    {
      ActionCard aActionCard = actionCards.get(actionCards.size() - 1);
      aActionCard.delete();
      actionCards.remove(aActionCard);
    }
    
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "numberOfDieRollCards" + ":" + getNumberOfDieRollCards()+ "," +
            "numberOfConnectionCards" + ":" + getNumberOfConnectionCards()+ "," +
            "numberOfRemovalCards" + ":" + getNumberOfRemovalCards()+ "," +
            "numberOfTeleportCards" + ":" + getNumberOfTeleportCards()+ "," +
            "numberOfTurnLossCards" + ":" + getNumberOfTurnLossCards()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "topCard" + "=" + (getTopCard() != null ? !getTopCard().equals(this)  ? getTopCard().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}