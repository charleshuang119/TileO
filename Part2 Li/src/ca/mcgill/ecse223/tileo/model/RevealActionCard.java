/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;

// line 142 "../../../../../TileOPersistence.ump"
// line 348 "../../../../../TileO (updated April3).ump"
public class RevealActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RevealActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }


  /**
   * @Override
   */
  // line 353 "../../../../../TileO (updated April3).ump"
   public Game.Mode getActionCardGameMode(){
    return Game.Mode.GAME_REVEALACTIONCARD;
  }

  // line 357 "../../../../../TileO (updated April3).ump"
   public String play(Tile aTile) throws InvalidInputException{
    String type = null;
		String error = "";
		try {
			if (aTile instanceof NormalTile) {
				type = "Normal Tile";
			}
			else if (aTile instanceof ActionTile) {
				type = "Action Tile";
			}
			else if (aTile instanceof WinTile) {
				type = "Win Tile";
			}
			return type;
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 145 TileOPersistence.ump
  private static final long serialVersionUID = 4938573821423152358L ;

  
}