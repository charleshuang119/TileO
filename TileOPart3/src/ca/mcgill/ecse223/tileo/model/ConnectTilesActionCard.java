/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;

// line 77 "../../../../../TileOPersistence.ump"
// line 74 "../../../../../TileO (updated Feb10).ump"
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
  
  public void play (Tile tile1, Tile tile2) throws InvalidInputException {
	  // Get game of the card
	  Game currentGame = super.getDeck().getGame();
	  String error = "";
	  
	  // Check if card is connectTilesActionCard
//	  if (super.getDeck().getCurrentCard() != this){
//		  error = "The current card is not a Connect Tiles Action Card.";
//	  }
//	  if (error.length() > 0){
//			throw new InvalidInputException (error.trim());
//	  }
	  
	  try
	  {
		  // Create a connection and add the connection to the selected tiles
		  Connection newConnection = currentGame.addConnection();
		  tile1.addConnection(newConnection);
		  tile2.addConnection(newConnection);
	  }
	  catch (RuntimeException e) 
	  {
		  error = e.getMessage();
		  throw new InvalidInputException(error);
	  }
  }
  
  @Override
  public Game.Mode getActionCardGameMode() {
	  return Game.Mode.GAME_CONNECTTILESACTIONCARD;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 80 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 3030303030303030303L ;

  
}