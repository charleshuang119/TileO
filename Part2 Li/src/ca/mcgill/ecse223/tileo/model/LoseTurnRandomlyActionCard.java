/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

// line 100 "../../../../../TileOPersistence.ump"
// line 378 "../../../../../TileO (updated April3).ump"
public class LoseTurnRandomlyActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LoseTurnRandomlyActionCard(String aInstructions, Deck aDeck)
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
  // line 383 "../../../../../TileO (updated April3).ump"
   public Game.Mode getActionCardGameMode(){
    return Game.Mode.GAME_LOSETURNRANDOMLYACTIONCARD;
  }

  // line 387 "../../../../../TileO (updated April3).ump"
   public void play(int p1LostTurns, int p2LostTurns, int p3LostTurns, int p4LostTurns){
    Game currentGame = getDeck().getGame();
	   int numberOfPlayers = currentGame.numberOfPlayers();
	   
	   if(p1LostTurns > 0){
		   currentGame.getPlayer(0).loseTurns(p1LostTurns * numberOfPlayers + 1);
	   }
	   
	   if(p2LostTurns > 0){
		   currentGame.getPlayer(1).loseTurns(p2LostTurns * numberOfPlayers + 1);
	   }
	   
	   if(p3LostTurns > 0 && numberOfPlayers >= 3){
		   currentGame.getPlayer(2).loseTurns(p3LostTurns * numberOfPlayers + 1);
	   }
	   
	   if(p3LostTurns > 0 && numberOfPlayers == 4){
		   currentGame.getPlayer(3).loseTurns(p4LostTurns * numberOfPlayers + 1);
	   }
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 103 TileOPersistence.ump
  private static final long serialVersionUID = 1029384756019283745L ;

  
}