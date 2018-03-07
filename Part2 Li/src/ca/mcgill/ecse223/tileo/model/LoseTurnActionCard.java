/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

// line 88 "../../../../../TileOPersistence.ump"
// line 333 "../../../../../TileO (updated April3).ump"
public class LoseTurnActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LoseTurnActionCard(String aInstructions, Deck aDeck)
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
  // line 338 "../../../../../TileO (updated April3).ump"
   public Game.Mode getActionCardGameMode(){
    return Game.Mode.GAME_LOSETURNACTIONCARD;
  }

  // line 342 "../../../../../TileO (updated April3).ump"
   public void play(){
    Game currentGame = super.getDeck().getGame();
	   	Player currentplayer = currentGame.getCurrentPlayer();
	   	currentplayer.loseTurns(currentGame.numberOfPlayers() + 1);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 91 TileOPersistence.ump
  private static final long serialVersionUID = 6060606060606060606L ;

  
}