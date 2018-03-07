/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

/**
 * Person who plays the game by means of turns, each player has a number and is related to his or her respective playing piece
 */
// line 15 "../../../../../TileO.ump"
public class Player extends User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Player> playersByPlayerNumber = new HashMap<Integer, Player>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private int playerNumber;
  private boolean myTurn;
  private boolean canDrawCard;
  private boolean willLoseNextTurn;

  //Player Associations
  private PlayingPiece avatar;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(int aPlayerNumber, PlayingPiece aAvatar)
  {
    super();
    resetMyTurn();
    resetCanDrawCard();
    resetWillLoseNextTurn();
    if (!setPlayerNumber(aPlayerNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate playerNumber");
    }
    if (aAvatar == null || aAvatar.getPlayer() != null)
    {
      throw new RuntimeException("Unable to create Player due to aAvatar");
    }
    avatar = aAvatar;
  }

  public Player(int aPlayerNumber, SpecificGame aBoardgameForAvatar, String aColorForAvatar, Location aStartLocationForAvatar, Location aCurrentLocationForAvatar)
  {
    super();
    playerNumber = aPlayerNumber;
    resetMyTurn();
    resetCanDrawCard();
    resetWillLoseNextTurn();
    avatar = new PlayingPiece(aBoardgameForAvatar, aColorForAvatar, aStartLocationForAvatar, aCurrentLocationForAvatar, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPlayerNumber(int aPlayerNumber)
  {
    boolean wasSet = false;
    Integer anOldPlayerNumber = getPlayerNumber();
    if (hasWithPlayerNumber(aPlayerNumber)) {
      return wasSet;
    }
    playerNumber = aPlayerNumber;
    wasSet = true;
    if (anOldPlayerNumber != null) {
      playersByPlayerNumber.remove(anOldPlayerNumber);
    }
    playersByPlayerNumber.put(aPlayerNumber, this);
    return wasSet;
  }

  public boolean setMyTurn(boolean aMyTurn)
  {
    boolean wasSet = false;
    myTurn = aMyTurn;
    wasSet = true;
    return wasSet;
  }

  public boolean resetMyTurn()
  {
    boolean wasReset = false;
    myTurn = getDefaultMyTurn();
    wasReset = true;
    return wasReset;
  }

  public boolean setCanDrawCard(boolean aCanDrawCard)
  {
    boolean wasSet = false;
    canDrawCard = aCanDrawCard;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCanDrawCard()
  {
    boolean wasReset = false;
    canDrawCard = getDefaultCanDrawCard();
    wasReset = true;
    return wasReset;
  }

  public boolean setWillLoseNextTurn(boolean aWillLoseNextTurn)
  {
    boolean wasSet = false;
    willLoseNextTurn = aWillLoseNextTurn;
    wasSet = true;
    return wasSet;
  }

  public boolean resetWillLoseNextTurn()
  {
    boolean wasReset = false;
    willLoseNextTurn = getDefaultWillLoseNextTurn();
    wasReset = true;
    return wasReset;
  }

  /**
   * Can take values from 1 to 4
   */
  public int getPlayerNumber()
  {
    return playerNumber;
  }

  public static Player getWithPlayerNumber(int aPlayerNumber)
  {
    return playersByPlayerNumber.get(aPlayerNumber);
  }

  public static boolean hasWithPlayerNumber(int aPlayerNumber)
  {
    return getWithPlayerNumber(aPlayerNumber) != null;
  }

  /**
   * Turns true when it is that player’s turn
   */
  public boolean getMyTurn()
  {
    return myTurn;
  }

  public boolean getDefaultMyTurn()
  {
    return false;
  }

  /**
   * if the player lands on an action tile, he or she can draw a card
   */
  public boolean getCanDrawCard()
  {
    return canDrawCard;
  }

  public boolean getDefaultCanDrawCard()
  {
    return false;
  }

  public boolean getWillLoseNextTurn()
  {
    return willLoseNextTurn;
  }

  public boolean getDefaultWillLoseNextTurn()
  {
    return false;
  }

  public boolean isMyTurn()
  {
    return myTurn;
  }

  public boolean isCanDrawCard()
  {
    return canDrawCard;
  }

  public boolean isWillLoseNextTurn()
  {
    return willLoseNextTurn;
  }

  public PlayingPiece getAvatar()
  {
    return avatar;
  }

  public void delete()
  {
    playersByPlayerNumber.remove(getPlayerNumber());
    PlayingPiece existingAvatar = avatar;
    avatar = null;
    if (existingAvatar != null)
    {
      existingAvatar.delete();
    }
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "playerNumber" + ":" + getPlayerNumber()+ "," +
            "myTurn" + ":" + getMyTurn()+ "," +
            "canDrawCard" + ":" + getCanDrawCard()+ "," +
            "willLoseNextTurn" + ":" + getWillLoseNextTurn()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "avatar = "+(getAvatar()!=null?Integer.toHexString(System.identityHashCode(getAvatar())):"null")
     + outputString;
  }
}