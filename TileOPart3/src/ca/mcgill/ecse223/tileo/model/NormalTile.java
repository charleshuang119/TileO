/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 41 "../../../../../TileOPersistence.ump"
// line 48 "../../../../../TileO (updated Feb10).ump"
public class NormalTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public NormalTile(int aX, int aY, Game aGame)
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
  	 
  	  // get the current player
  	  Player currentPlayer = currentGame.getCurrentPlayer();
  	 
  	  // set the player's current tile to the tile he has to move to
  	  // possible moves from Charles
  	  currentPlayer.setCurrentTile(this);
  	 
  	  // get the index of the current player
  	  //currentGame.setCurrentPlayer(currentGame.getPlayer(currentGame.indexOfPlayer(currentPlayer) + 1))
  	  int playerIndex = currentGame.indexOfPlayer(currentPlayer);
  	 
  	  // get the number of players in the game
  	  int numberOfPlayers = currentGame.numberOfPlayers();
  	 
  	  // if it's the last player's turn
  	  if(playerIndex == numberOfPlayers-1){
  		  
  		  // it is now player 1's turn
  		 // Player playerOne = currentGame.getPlayer(0);	 
  		 
  		  currentGame.setCurrentPlayer(currentGame.getPlayer(0));
  	  }else{
  		  // it is now the next player's turn
  		  Player followingPlayer = currentGame.getPlayer(currentGame.indexOfPlayer(currentPlayer)+1);
  		 
  		  currentGame.setCurrentPlayer(followingPlayer);
  		 
  	  	}
  	 
  	  // set possible tile that player chose, that it has been visited
  	  this.setHasBeenVisited(true);
  	 
  	  currentGame.setMode(Game.Mode.GAME);
  }


  public void delete()
  {
    super.delete();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 44 ../../../../../TileOPersistence.ump
  private static final long serialVersionUID = 6666666666666666666L ;

  
}