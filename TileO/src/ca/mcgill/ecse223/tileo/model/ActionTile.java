/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

/**
 * this type of tile will grant the player the action of drawCard
 */
// line 155 "../../../../../TileO.ump"
public class ActionTile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ActionTile Attributes
  private boolean isNormal;
  private int turnsBeforeAction;
  private int turnCounter;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ActionTile(int aTurnsBeforeAction)
  {
    resetIsNormal();
    turnsBeforeAction = aTurnsBeforeAction;
    resetTurnCounter();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsNormal(boolean aIsNormal)
  {
    boolean wasSet = false;
    isNormal = aIsNormal;
    wasSet = true;
    return wasSet;
  }

  public boolean resetIsNormal()
  {
    boolean wasReset = false;
    isNormal = getDefaultIsNormal();
    wasReset = true;
    return wasReset;
  }

  public boolean setTurnCounter(int aTurnCounter)
  {
    boolean wasSet = false;
    turnCounter = aTurnCounter;
    wasSet = true;
    return wasSet;
  }

  public boolean resetTurnCounter()
  {
    boolean wasReset = false;
    turnCounter = getDefaultTurnCounter();
    wasReset = true;
    return wasReset;
  }

  public boolean getIsNormal()
  {
    return isNormal;
  }

  public boolean getDefaultIsNormal()
  {
    return false;
  }

  /**
   * normal means that it acts as a Normal Tile.
   * will have a method that will draw a (top) card from the deck
   * Value set by the designer: number of turns before the the ActionTile is effective again
   */
  public int getTurnsBeforeAction()
  {
    return turnsBeforeAction;
  }

  /**
   * Will start to count after a player lands on the tile.
   */
  public int getTurnCounter()
  {
    return turnCounter;
  }

  public int getDefaultTurnCounter()
  {
    return 0;
  }

  public boolean isIsNormal()
  {
    return isNormal;
  }

  public void delete()
  {}


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "isNormal" + ":" + getIsNormal()+ "," +
            "turnsBeforeAction" + ":" + getTurnsBeforeAction()+ "," +
            "turnCounter" + ":" + getTurnCounter()+ "]"
     + outputString;
  }
}