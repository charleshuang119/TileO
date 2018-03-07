/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;

// line 76 "../../../../../TileOPersistence.ump"
// line 295 "../../../../../TileO (updated April3).ump"
public class RemoveConnectionActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RemoveConnectionActionCard(String aInstructions, Deck aDeck)
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

  // line 299 "../../../../../TileO (updated April3).ump"
   public void play(Connection connection) throws InvalidInputException{
    try{
		  connection.delete();
	  }
	  catch (RuntimeException e) {
		  throw new InvalidInputException(e.getMessage());
	  }
  }


  /**
   * @Override
   */
  // line 309 "../../../../../TileO (updated April3).ump"
   public Game.Mode getActionCardGameMode(){
    return Game.Mode.GAME_REMOVECONNECTIONACTIONCARD;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 79 TileOPersistence.ump
  private static final long serialVersionUID = 4040404040404040404L ;

  
}