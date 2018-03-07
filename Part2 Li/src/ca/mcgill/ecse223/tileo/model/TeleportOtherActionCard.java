/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

// line 135 "../../../../../TileOPersistence.ump"
// line 509 "../../../../../TileO (updated April3).ump"
public class TeleportOtherActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TeleportOtherActionCard(String aInstructions, Deck aDeck)
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
  // line 514 "../../../../../TileO (updated April3).ump"
   public Game.Mode getActionCardGameMode(){
    return Game.Mode.GAME_TELEPORTOTHERACTIONCARD;
  }

  // line 518 "../../../../../TileO (updated April3).ump"
   public void play(int playerNumber, Tile aTile){
    Game currentGame = super.getDeck().getGame();
    	Player chosenPlayer = currentGame.getPlayer(playerNumber);
    	chosenPlayer.setCurrentTile(aTile);
    	aTile.setHasBeenVisited(true);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 138 TileOPersistence.ump
  private static final long serialVersionUID = 1230984328754368234L ;

  
}