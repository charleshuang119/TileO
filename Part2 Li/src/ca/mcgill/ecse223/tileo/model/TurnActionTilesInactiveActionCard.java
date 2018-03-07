/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.List;

// line 114 "../../../../../TileOPersistence.ump"
// line 416 "../../../../../TileO (updated April3).ump"
public class TurnActionTilesInactiveActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TurnActionTilesInactiveActionCard(String aInstructions, Deck aDeck)
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
  // line 420 "../../../../../TileO (updated April3).ump"
   public Game.Mode getActionCardGameMode(){
    return Game.Mode.GAME_TURNACTIONTILESINACTIVEACTIONCARD;
  }

  // line 424 "../../../../../TileO (updated April3).ump"
   public void play(){
    Game currentGame = super.getDeck().getGame();
	   List<Tile> tiles = currentGame.getTiles();
	   for(Tile aTile:tiles) {
		   if (aTile instanceof ActionTile){
			   ((ActionTile) aTile).deactivate();
		   }
	   }
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 117 TileOPersistence.ump
  private static final long serialVersionUID = 3829385123748593214L ;

  
}