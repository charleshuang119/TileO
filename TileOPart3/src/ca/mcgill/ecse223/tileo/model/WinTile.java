/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

// line 47 "../../../../../TileOPersistence.ump"
// line 52 "../../../../../TileO (updated Feb10).ump"
public class WinTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WinTile(int aX, int aY, Game aGame)
  {
    super(aX, aY, aGame);
  }

  //------------------------
  // INTERFACE
  //------------------------
  
  @Override
  public void land()
  { 
  	  // get the current game
  	  Game currentGame = this.getGame();
  	  
  	  this.setHasBeenVisited(true);
  	  
  	  // get the current player
  	  Player currentPlayer = currentGame.getCurrentPlayer();
  	 
  	  // set the player's current tile to the tile he has to move to
  	  // possible moves from Charles
  	  currentPlayer.setCurrentTile(this); //not in the sequence diagram though
  	 
  	  currentGame.setMode(Game.Mode.GAME_WON);
  	 
  	  // set possible tile that player chose, that it has been visited
  	  
  	 

  }

  public void delete()
  {
    super.delete();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 50 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 7777777777777777777L ;

  
}