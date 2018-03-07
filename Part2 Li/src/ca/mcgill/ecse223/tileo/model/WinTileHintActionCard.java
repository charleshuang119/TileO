/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;

// line 121 "../../../../../TileOPersistence.ump"
// line 434 "../../../../../TileO (updated April3).ump"
public class WinTileHintActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WinTileHintActionCard(String aInstructions, Deck aDeck)
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
  // line 439 "../../../../../TileO (updated April3).ump"
   public Game.Mode getActionCardGameMode(){
    return Game.Mode.GAME_WINTILEHINTACTIONCARD;
  }

  // line 443 "../../../../../TileO (updated April3).ump"
   public boolean play(Tile aTile){
    Game currentGame = super.getDeck().getGame();
	    boolean hint = false;
	    Tile topTile = null;
	    Tile bottomTile = null;
	    Tile rightTile = null;
	    Tile leftTile = null;
			for (Tile tileInGame : currentGame.getTiles()){
				if ((aTile.getY() == tileInGame.getY() - 1) && (aTile.getX() == tileInGame.getX())){
					topTile = tileInGame;
				}
				if (aTile.getY() == tileInGame.getY() + 1 && (aTile.getX() == tileInGame.getX())){
					bottomTile = tileInGame;
				}
				if (aTile.getX() == tileInGame.getX() - 1 && (aTile.getY() == tileInGame.getY())){
					leftTile = tileInGame;
				}
				if (aTile.getX() == tileInGame.getX() + 1 && (aTile.getY() == tileInGame.getY())){
					rightTile = tileInGame;
				}
			}
			if (aTile instanceof WinTile) {
				hint  = true;
			}
			if (topTile != null){
				if (topTile instanceof WinTile) {
					hint = true;
				}
			}
			if (bottomTile != null) {
				
				if (bottomTile instanceof WinTile) {
					hint = true;
				}
			}
			if (rightTile != null) {
				if (rightTile instanceof WinTile) {
					hint = true;
				}				
			}
			if (leftTile != null) {
				if (leftTile instanceof WinTile) {
					hint = true;
				}
			}
			return hint;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 124 TileOPersistence.ump
  private static final long serialVersionUID = 1337133713371337133L ;

  
}