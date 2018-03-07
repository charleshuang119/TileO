/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

// line 128 "../../../../../TileOPersistence.ump"
// line 492 "../../../../../TileO (updated April3).ump"
public class SendBackToStartActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SendBackToStartActionCard(String aInstructions, Deck aDeck)
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
  // line 497 "../../../../../TileO (updated April3).ump"
   public Game.Mode getActionCardGameMode(){
    return Game.Mode.GAME_SENDBACKTOSTARTACTIONCARD;
  }

  // line 500 "../../../../../TileO (updated April3).ump"
   public void play(int index){
    Game currentGame = super.getDeck().getGame();
	   Player chosenPlayer;
	   chosenPlayer = currentGame.getPlayer(index);
	   Tile startingTile = chosenPlayer.getStartingTile();
	   chosenPlayer.setCurrentTile(startingTile);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 131 TileOPersistence.ump
  private static final long serialVersionUID = 1483212342385938245L ;

  
}