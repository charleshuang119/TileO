/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

/**
 * 32 action cards that form the deck. There are 5 types of action cards and each card has a different effect, enumerated in the action card type.
 */
// line 86 "../../../../../TileO.ump"
public class ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ActionCard Attributes
  private String effect;

  //ActionCard State Machines
  public enum ActionCardType { DieRoll, Connection, Removal, Teleport, TurnLoss }
  private ActionCardType actionCardType;

  //ActionCard Associations
  private Deck deck;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ActionCard(Deck aDeck)
  {
    effect = "Unspecified";
    boolean didAddDeck = setDeck(aDeck);
    if (!didAddDeck)
    {
      throw new RuntimeException("Unable to create actionCard due to deck");
    }
    setActionCardType(ActionCardType.DieRoll);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEffect(String aEffect)
  {
    boolean wasSet = false;
    effect = aEffect;
    wasSet = true;
    return wasSet;
  }

  /**
   * will depend on what type of card it is. Just a textual description of the effect.
   */
  public String getEffect()
  {
    return effect;
  }

  public String getActionCardTypeFullName()
  {
    String answer = actionCardType.toString();
    return answer;
  }

  public ActionCardType getActionCardType()
  {
    return actionCardType;
  }

  public boolean setActionCardType(ActionCardType aActionCardType)
  {
    actionCardType = aActionCardType;
    return true;
  }

  public Deck getDeck()
  {
    return deck;
  }

  public boolean setDeck(Deck aDeck)
  {
    boolean wasSet = false;
    //Must provide deck to actionCard
    if (aDeck == null)
    {
      return wasSet;
    }

    //deck already at maximum (32)
    if (aDeck.numberOfActionCards() >= Deck.maximumNumberOfActionCards())
    {
      return wasSet;
    }
    
    Deck existingDeck = deck;
    deck = aDeck;
    if (existingDeck != null && !existingDeck.equals(aDeck))
    {
      boolean didRemove = existingDeck.removeActionCard(this);
      if (!didRemove)
      {
        deck = existingDeck;
        return wasSet;
      }
    }
    deck.addActionCard(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Deck placeholderDeck = deck;
    this.deck = null;
    placeholderDeck.removeActionCard(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "effect" + ":" + getEffect()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "deck = "+(getDeck()!=null?Integer.toHexString(System.identityHashCode(getDeck())):"null")
     + outputString;
  }
}