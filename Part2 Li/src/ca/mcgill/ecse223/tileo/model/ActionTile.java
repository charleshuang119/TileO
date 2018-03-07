/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

// line 28 "../../../../../TileOPersistence.ump"
// line 1 "../../../../../TileOStates.ump"
// line 158 "../../../../../TileO (updated April3).ump"
public class ActionTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ActionTile Attributes
  private int inactivityPeriod;
  private int turnsUntilActive;

  //ActionTile State Machines
  public enum ActionTileStatus { Active, Inactive }
  private ActionTileStatus actionTileStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ActionTile(int aX, int aY, Game aGame, int aInactivityPeriod)
  {
    super(aX, aY, aGame);
    inactivityPeriod = aInactivityPeriod;
    turnsUntilActive = 0;
    setActionTileStatus(ActionTileStatus.Active);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTurnsUntilActive(int aTurnsUntilActive)
  {
    boolean wasSet = false;
    turnsUntilActive = aTurnsUntilActive;
    wasSet = true;
    return wasSet;
  }

  public int getInactivityPeriod()
  {
    return inactivityPeriod;
  }

  public int getTurnsUntilActive()
  {
    return turnsUntilActive;
  }

  public String getActionTileStatusFullName()
  {
    String answer = actionTileStatus.toString();
    return answer;
  }

  public ActionTileStatus getActionTileStatus()
  {
    return actionTileStatus;
  }

  public boolean deactivate()
  {
    boolean wasEventProcessed = false;
    
    ActionTileStatus aActionTileStatus = actionTileStatus;
    switch (aActionTileStatus)
    {
      case Active:
        if (getInactivityPeriod()>0)
        {
        // line 4 "../../../../../TileOStates.ump"
          setTurnsUntilActive(getInactivityPeriod() + 1);
          setActionTileStatus(ActionTileStatus.Inactive);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean takeTurn()
  {
    boolean wasEventProcessed = false;
    
    ActionTileStatus aActionTileStatus = actionTileStatus;
    switch (aActionTileStatus)
    {
      case Inactive:
        if (getTurnsUntilActive()>1)
        {
        // line 9 "../../../../../TileOStates.ump"
          setTurnsUntilActive(getTurnsUntilActive() - 1);
          setActionTileStatus(ActionTileStatus.Inactive);
          wasEventProcessed = true;
          break;
        }
        if (getTurnsUntilActive()<=1)
        {
        // line 12 "../../../../../TileOStates.ump"
          setTurnsUntilActive(0);
          setActionTileStatus(ActionTileStatus.Active);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setActionTileStatus(ActionTileStatus aActionTileStatus)
  {
    actionTileStatus = aActionTileStatus;
  }

  public void delete()
  {
    super.delete();
  }


  /**
   * @Override
   */
  // line 166 "../../../../../TileO (updated April3).ump"
   public void doLand(){
    Game currentGame = getGame();
		Player currentPlayer = currentGame.getCurrentPlayer();
		currentPlayer.setCurrentTile(this);
		
		if (getActionTileStatus() == ActionTileStatus.Inactive) {
			currentGame.setNextPlayer();
	  	  	// set possible tile that player chose, that it has been visited
	  	  	this.setHasBeenVisited(true);  	 
	  	  	currentGame.setMode(Game.Mode.GAME);
		}
		else {
			Deck deck = currentGame.getDeck();
			ActionCard currentCard = deck.getCurrentCard();	  
			currentGame.setMode(currentCard.getActionCardGameMode());
			deactivate();
			setHasBeenVisited(true);			
		}
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "inactivityPeriod" + ":" + getInactivityPeriod()+ "," +
            "turnsUntilActive" + ":" + getTurnsUntilActive()+ "]"
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 31 TileOPersistence.ump
  private static final long serialVersionUID = 5555555555555555555L ;

  
}