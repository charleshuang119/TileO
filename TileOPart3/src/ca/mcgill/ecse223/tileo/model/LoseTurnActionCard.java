/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

// line 95 "../../../../../TileOPersistence.ump"
// line 86 "../../../../../TileO (updated Feb10).ump"
public class LoseTurnActionCard extends ActionCard
{
	
	@Override
	public Game.Mode getActionCardGameMode() {
		  return Game.Mode.GAME_LOSETURNACTIONCARD;
	 }

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
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 98 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 6060606060606060606L ;

  
}