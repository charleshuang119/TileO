/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;

// line 70 "../../../../../TileOPersistence.ump"
// line 270 "../../../../../TileO (updated April3).ump"
public class ConnectTilesActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ConnectTilesActionCard(String aInstructions, Deck aDeck)
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

  // line 274 "../../../../../TileO (updated April3).ump"
   public void play(Tile tile1, Tile tile2) throws InvalidInputException{
    // Get game of the card
	  	Game currentGame = super.getDeck().getGame();
	  	String error = "";
	  	try {
			// Create a connection and add the connection to the selected tiles
			Connection newConnection = currentGame.addConnection();
			tile1.addConnection(newConnection);
			tile2.addConnection(newConnection);
		}
	  	catch (RuntimeException e) {
		  	error = e.getMessage();
			throw new InvalidInputException(error);
	  	}
  }


  /**
   * @Override
   */
  // line 291 "../../../../../TileO (updated April3).ump"
   public Game.Mode getActionCardGameMode(){
    return Game.Mode.GAME_CONNECTTILESACTIONCARD;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 73 TileOPersistence.ump
  private static final long serialVersionUID = 3030303030303030303L ;

  
}