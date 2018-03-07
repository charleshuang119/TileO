/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.controller;
import java.util.List;
import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.ActionCard;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.ActionTile.ActionTileStatus;
import ca.mcgill.ecse223.tileo.model.ConnectTilesActionCard;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.LoseTurnActionCard;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.RemoveConnectionActionCard;
import ca.mcgill.ecse223.tileo.model.RollDieActionCard;
import ca.mcgill.ecse223.tileo.model.TeleportActionCard;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.model.WinTile;

// line 4 "../../../../../playState.ump"
public class PlayModeController
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayModeController State Machines
  public enum GameState { ReadyToStart, Start, ChoosingTile, ActionTileState, RemoveConnection, Teleport, LoseTurn, ExtraRoll, AddConnection, Win }
  private GameState gameState;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayModeController()
  {
    setGameState(GameState.ReadyToStart);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getGameStateFullName()
  {
    String answer = gameState.toString();
    return answer;
  }

  public GameState getGameState()
  {
    return gameState;
  }

  public boolean startGame(Game game) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    GameState aGameState = gameState;
    switch (aGameState)
    {
      case ReadyToStart:
        if (game.getMode().equals(Game.Mode.GAME))
        {
        // line 30 "../../../../../playState.ump"
          doStartGame(game);
          setGameState(GameState.Start);
          wasEventProcessed = true;
          break;
        }
        if (game.getMode().equals(Game.Mode.GAME_ROLLDIEACTIONCARD))
        {
        // line 33 "../../../../../playState.ump"
          
          setGameState(GameState.ExtraRoll);
          wasEventProcessed = true;
          break;
        }
        if (game.getMode().equals(Game.Mode.GAME_CONNECTTILESACTIONCARD))
        {
        // line 35 "../../../../../playState.ump"
          
          setGameState(GameState.AddConnection);
          wasEventProcessed = true;
          break;
        }
        if (game.getMode().equals(Game.Mode.GAME_REMOVECONNECTIONACTIONCARD))
        {
        // line 37 "../../../../../playState.ump"
          
          setGameState(GameState.RemoveConnection);
          wasEventProcessed = true;
          break;
        }
        if (game.getMode().equals(Game.Mode.GAME_TELEPORTACTIONCARD))
        {
        // line 39 "../../../../../playState.ump"
          
          setGameState(GameState.Teleport);
          wasEventProcessed = true;
          break;
        }
        if (game.getMode().equals(Game.Mode.GAME_LOSETURNACTIONCARD))
        {
        // line 41 "../../../../../playState.ump"
          
          setGameState(GameState.LoseTurn);
          wasEventProcessed = true;
          break;
        }
        if (game.getMode().equals(Game.Mode.GAME_WON))
        {
        // line 43 "../../../../../playState.ump"
          
          setGameState(GameState.Win);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean takeATurn()
  {
    boolean wasEventProcessed = false;
    
    GameState aGameState = gameState;
    switch (aGameState)
    {
      case Start:
        // line 48 "../../../../../playState.ump"
        doRollDie();
        setGameState(GameState.ChoosingTile);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean choose(Tile tile) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    GameState aGameState = gameState;
    switch (aGameState)
    {
      case ChoosingTile:
        if (isWinTile(tile))
        {
        // line 55 "../../../../../playState.ump"
          doLandedOnTile(tile);
          setGameState(GameState.Win);
          wasEventProcessed = true;
          break;
        }
        if (isNormalTile(tile))
        {
        // line 59 "../../../../../playState.ump"
          doLandedOnTile(tile);
          setGameState(GameState.Start);
          wasEventProcessed = true;
          break;
        }
        if (isActionTile(tile))
        {
        // line 63 "../../../../../playState.ump"
          doLandedOnTile(tile);
          setGameState(GameState.ActionTileState);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean RemoveConnectionAction()
  {
    boolean wasEventProcessed = false;
    
    GameState aGameState = gameState;
    switch (aGameState)
    {
      case ActionTileState:
        if (checkGameMode(Game.Mode.GAME_REMOVECONNECTIONACTIONCARD))
        {
          setGameState(GameState.RemoveConnection);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean TeleportAction()
  {
    boolean wasEventProcessed = false;
    
    GameState aGameState = gameState;
    switch (aGameState)
    {
      case ActionTileState:
        if (checkGameMode(Game.Mode.GAME_TELEPORTACTIONCARD))
        {
          setGameState(GameState.Teleport);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean LoseTurnAction()
  {
    boolean wasEventProcessed = false;
    
    GameState aGameState = gameState;
    switch (aGameState)
    {
      case ActionTileState:
        if (checkGameMode(Game.Mode.GAME_LOSETURNACTIONCARD))
        {
          setGameState(GameState.LoseTurn);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean ExtraRollAction()
  {
    boolean wasEventProcessed = false;
    
    GameState aGameState = gameState;
    switch (aGameState)
    {
      case ActionTileState:
        if (checkGameMode(Game.Mode.GAME_ROLLDIEACTIONCARD))
        {
          setGameState(GameState.ExtraRoll);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean AddConnectionAction()
  {
    boolean wasEventProcessed = false;
    
    GameState aGameState = gameState;
    switch (aGameState)
    {
      case ActionTileState:
        if (checkGameMode(Game.Mode.GAME_CONNECTTILESACTIONCARD))
        {
          setGameState(GameState.AddConnection);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean removeconnection(Connection connection) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    GameState aGameState = gameState;
    switch (aGameState)
    {
      case RemoveConnection:
        // line 82 "../../../../../playState.ump"
        doPlayRemoveConnectionActionCard(connection);
        setGameState(GameState.Start);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean teleport(Tile selectedTile) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    GameState aGameState = gameState;
    switch (aGameState)
    {
      case Teleport:
        if (isNormalTile(selectedTile))
        {
        // line 88 "../../../../../playState.ump"
          doPlayTeleportActionCard(selectedTile);
          setGameState(GameState.Start);
          wasEventProcessed = true;
          break;
        }
        if (isActionTile(selectedTile))
        {
        // line 91 "../../../../../playState.ump"
          doPlayTeleportActionCard(selectedTile);
          setGameState(GameState.ActionTileState);
          wasEventProcessed = true;
          break;
        }
        if (isWinTile(selectedTile))
        {
        // line 94 "../../../../../playState.ump"
          doPlayTeleportActionCard(selectedTile);
          setGameState(GameState.Win);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean loseTurn() throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    GameState aGameState = gameState;
    switch (aGameState)
    {
      case LoseTurn:
        // line 100 "../../../../../playState.ump"
        doPlayLoseTurnActionCard();
        setGameState(GameState.Start);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean rollDieAgain()
  {
    boolean wasEventProcessed = false;
    
    GameState aGameState = gameState;
    switch (aGameState)
    {
      case ExtraRoll:
        // line 106 "../../../../../playState.ump"
        doPlayRollDieExtraTurn();
        setGameState(GameState.ChoosingTile);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean connectionAdd(Tile t1,Tile t2) throws InvalidInputException
  {
    boolean wasEventProcessed = false;
    
    GameState aGameState = gameState;
    switch (aGameState)
    {
      case AddConnection:
        // line 112 "../../../../../playState.ump"
        doPlayConnectTilesActionCard(t1,t2);
        setGameState(GameState.Start);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setGameState(GameState aGameState)
  {
    gameState = aGameState;
  }

  public void delete()
  {}


  /**
   * Condition
   */
  // line 125 "../../../../../playState.ump"
   public boolean checkGameMode(Game.Mode aMode){
    return aMode == TileOApplication.getTileO().getCurrentGame().getMode();
  }

  // line 129 "../../../../../playState.ump"
   public boolean isNormalTile(Tile selectedTile){
    return (selectedTile instanceof NormalTile);
  }

  // line 133 "../../../../../playState.ump"
   public boolean isActionTile(Tile selectedTile){
    return (selectedTile instanceof ActionTile);
  }

  // line 137 "../../../../../playState.ump"
   public boolean isWinTile(Tile selectedTile){
    return (selectedTile instanceof WinTile);
  }

  // line 141 "../../../../../playState.ump"
   public void doStartGame(Game selectedGame) throws InvalidInputException{
    //TODO: CHARLES
		String error = "";
		if(selectedGame.getDeck().numberOfCards() != 32) {
			error = "The action cards in the deck must be equal to 32.";
		}
		if(selectedGame.hasWinTile() == false) {
			error = "The winTile does not exist." ;
		}
		for(int i = 0 ; i < selectedGame.numberOfPlayers(); i++) {
			if(selectedGame.getPlayer(i).hasStartingTile() == false){
				error = "Player " + (i+1) + " does not have a starting tile." ;
			}
		}
		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		
		TileO tileO = TileOApplication.getTileO();
		try {
			tileO.setCurrentGame(selectedGame);
			Deck deck = selectedGame.getDeck();
			deck.shuffle();
			for(int i = 0 ; i < selectedGame.numberOfTiles(); i++){
				selectedGame.getTiles().get(i).setHasBeenVisited(false);
			}
			
			for(int j = 0; j < selectedGame.numberOfPlayers(); j++){
				Tile startingTile = selectedGame.getPlayer(j).getStartingTile();
				selectedGame.getPlayers().get(j).setCurrentTile(startingTile);
				selectedGame.getPlayers().get(j).getCurrentTile().setHasBeenVisited(true);
			}
			
			selectedGame.setCurrentPlayer(selectedGame.getPlayers().get(0));
			selectedGame.setCurrentConnectionPieces(Game.SpareConnectionPieces);
			selectedGame.setMode(Mode.GAME);
		}
		catch(RuntimeException e){
			error = e.getMessage();
			throw new InvalidInputException(error);
		}
  }


  /**
   * 2. Take a turn (roll the die, move to new position)
   * Charles
   */
  // line 189 "../../../../../playState.ump"
   public List<Tile> doRollDie(){
    //TODO: CHARLES
		TileO tileO = TileOApplication.getTileO(); 
		Game currentGame = tileO.getCurrentGame();
		List<Tile> possibleMoves = currentGame.rollDie();
		return possibleMoves; //method in Game class
  }


  /**
   * 3. Land on a tile (basic behavior for hidden, regular, and action tiles)
   * Chris
   */
  // line 202 "../../../../../playState.ump"
   public void doLandedOnTile(Tile tile) throws InvalidInputException{
    TileO tileO = TileOApplication.getTileO();
		Game currentGame = tileO.getCurrentGame();
		List<Tile> tiles = currentGame.getTiles();
	
		String error = "";
		if(tiles.contains(tile) == false){
			error = "The tile does not exist in the Board.";
		}
		if (error.length() > 0){
			throw new InvalidInputException(error.trim());
		}
		
		try{
			tile.doLand();
		}
		catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
  }


  /**
   * 4. Take the first card from the deck of cards
   * CM
   * helper method called within this controller
   */
  // line 229 "../../../../../playState.ump"
   private ActionCard drawCard(Game currentGame){
    Deck deck = currentGame.getDeck();
		ActionCard drawnCard = deck.getCurrentCard();
		ActionCard nextCard;
		
		if(deck.indexOfCard(drawnCard) < 31){ //index range: [0,31]
			nextCard = deck.getCard(deck.indexOfCard(drawnCard) + 1);
		}
		else{ //the index of the card must be 31, which is the last card
			deck.shuffle();
			nextCard = deck.getCard(0);
		}
		deck.setCurrentCard(nextCard);		
		return drawnCard;
  }


  /**
   * 5. Action card "Roll the die for an extra turn"
   * CM
   */
  // line 250 "../../../../../playState.ump"
   public void doPlayRollDieExtraTurn(){
    TileO tileO = TileOApplication.getTileO();
		Game currentGame = tileO.getCurrentGame();		
		//the drawn card needs to be a rollDieActionCard since this method is only called in the appropriate mode
		RollDieActionCard rollDieActionCard = (RollDieActionCard) drawCard(currentGame);
		
		rollDieActionCard.play();
		
		currentGame.setMode(Mode.GAME);
  }


  /**
   * 6. Action card "Connect two adjacent tiles with a connection piece from the pile of spare connection pieces"
   * Justin
   */
  // line 266 "../../../../../playState.ump"
   public void doPlayConnectTilesActionCard(Tile selectedTile1, Tile selectedTile2) throws InvalidInputException{
    TileO tileO = TileOApplication.getTileO();
		Game currentGame = tileO.getCurrentGame();
		ConnectTilesActionCard connectTilesActionCard = (ConnectTilesActionCard) drawCard(currentGame);
		
		String error = "";
		// Check if there are tiles that can be selected in the game
		if (currentGame.numberOfTiles() < 2){
			error = "There are less than 2 tiles in the current game.";
		}
		// Check if the selected tiles are adjacent
		if (!isAdjacent(selectedTile1, selectedTile2)){
			error = "The selected tiles are not adjacent.";
		}
		// Check if connection pieces are available
		if (currentGame.getCurrentConnectionPieces() < 1){
			error = "There are 0 connection pieces available.";
		}
		// Check if connection already exist at a location
		if (isConnected(selectedTile1, selectedTile2)){
			error = "The two selected tiles are already connected.";
		}
		// Check if errors are detected
		if (error.length() > 0){
			throw new InvalidInputException (error.trim());
		}
		
		try {
			// Connect the two selected tiles
			connectTilesActionCard.play(selectedTile1, selectedTile2);			
			currentGame.setNextPlayer();
			currentGame.setMode(Mode.GAME);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
  }


  /**
   * 7. Action card "Remove a connection piece from the board and place it in the pile of spare connection pieces"
   * Li
   */
  // line 309 "../../../../../playState.ump"
   public void doPlayRemoveConnectionActionCard(Connection connection) throws InvalidInputException{
    //TODO: LI
		TileO tileO = TileOApplication.getTileO();
		Game currentGame = tileO.getCurrentGame();
		List<Connection> connectionList = currentGame.getConnections();
		
		if (connectionList.contains(connection)) {
			ActionCard currentCard = drawCard(currentGame);
			
			try {
				if (currentCard instanceof RemoveConnectionActionCard) {				
					((RemoveConnectionActionCard) currentCard).play(connection);
				}				
				currentGame.setNextPlayer();		
				currentGame.setMode(Mode.GAME);
			} 
			catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());	
			}
		}
  }


  /**
   * 8. Action card "Move your playing piece to an arbitrary tile that is not your current tile"
   * Victor
   */
  // line 336 "../../../../../playState.ump"
   public void doPlayTeleportActionCard(Tile tile) throws InvalidInputException{
    //TODO: VICTORIQUE
		
		TileO tileO = TileOApplication.getTileO();
		Game currentGame = tileO.getCurrentGame();
		ActionCard currentCard= drawCard(currentGame);
		List<Tile> tiles = currentGame.getTiles();
		String error = "";
		if(tiles.contains(tile)==false){
			error = "Please select an existing tile on the board.";
		}
		if(error.length() > 0){
			throw new InvalidInputException(error.trim());
		}
		
		try{
			if (currentCard instanceof TeleportActionCard){
				((TeleportActionCard) currentCard).play(tile);
			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
  }


  /**
   * 9. Save and load game to continue playing at a later point
   * Li
   */
  // line 366 "../../../../../playState.ump"
   public void saveGame(){
    TileOApplication.save();
  }

  // line 370 "../../../../../playState.ump"
   public Game loadGame(int index) throws InvalidInputException{
    TileO tileO = TileOApplication.getTileO();
		try {
			Game loadedGame = tileO.getGame(index);
			loadedGame.setMode(Mode.GAME);
			return loadedGame;
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
  }


  /**
   * helper method to check if tiles are adjacent
   */
  // line 383 "../../../../../playState.ump"
   public boolean isAdjacent(Tile tile1, Tile tile2){
    int x1 = tile1.getX();
		int y1 = tile1.getY();
		int x2 = tile2.getX();
		int y2 = tile2.getY();
		if (x1-x2 == -1 || x1-x2 == 1){
			if(y1-y2 ==0){
				return true;
			}
		}
		if (y1-y2 == -1 || y1-y2 == 1){
			if(x1-x2 ==0){
				return true;
			}
		}		
		return false;
  }
   public void doPlayLoseTurnActionCard() throws InvalidInputException{
	    TileO tileO = TileOApplication.getTileO();
			Game currentGame = tileO.getCurrentGame();
			ActionCard currentCard = drawCard(currentGame);
			if (currentCard instanceof LoseTurnActionCard) {
				((LoseTurnActionCard) currentCard).play();
			
			currentGame.setNextPlayer();
			//currentGame.updateTileStatus();
			currentGame.setMode(Game.Mode.GAME);
			}
	  }

  /**
   * helper method two tiles are already connected
   */
  // line 402 "../../../../../playState.ump"
   public boolean isConnected(Tile tile1, Tile tile2){
    for (Connection c1: tile1.getConnections()){
			for (Connection c2: tile2.getConnections()){
				if (c1 == c2){
					return true;
				}
			}
		}
		return false;
  }

}