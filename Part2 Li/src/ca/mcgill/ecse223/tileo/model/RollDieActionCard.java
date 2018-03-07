/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.List;

// line 64 "../../../../../TileOPersistence.ump"
// line 256 "../../../../../TileO (updated April3).ump"
public class RollDieActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RollDieActionCard(String aInstructions, Deck aDeck)
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

  // line 260 "../../../../../TileO (updated April3).ump"
   public List<Tile> play(){
    Game currentGame = getDeck().getGame();
	  return currentGame.rollDie();
  }


  /**
   * @Override
   */
  // line 266 "../../../../../TileO (updated April3).ump"
   public Game.Mode getActionCardGameMode(){
    return Game.Mode.GAME_ROLLDIEACTIONCARD;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 67 TileOPersistence.ump
  private static final long serialVersionUID = 2020202020202020202L ;

  
}