package ca.mcgill.ecse223.tileo.controller;

import java.util.ArrayList;
import java.util.List;


import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.ActionCard;
import ca.mcgill.ecse223.tileo.model.ConnectTilesActionCard;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.model.Die;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.RemoveConnectionActionCard;
import ca.mcgill.ecse223.tileo.model.RollDieActionCard;
import ca.mcgill.ecse223.tileo.model.TeleportActionCard;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.TileO;

public class PlayModeController {

	// METHODS
	
	/*
	 * 1. Start a game (shuffle the action cards, place players on board)
	 * Charles
	 */
	public void startGame(Game selectedGame) throws InvalidInputException {
		//TODO: CHARLES
		String error = "";
		if(selectedGame.getDeck().numberOfCards()!= 32){
			error = "The action cards in the Deck must be equal to 32. ";
		}
		if(selectedGame.hasWinTile() == false) {
			error = error+"The winTile does not exist. " ;
		}
		for(int i=0 ;i<selectedGame.numberOfPlayers();i++) {
			if(selectedGame.getPlayer(i).hasStartingTile() == false){
				error = error+"The player"+(i+1)+" does not have a startingTile. " ;
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
			for(int i = 0 ; i < selectedGame.numberOfTiles();i++){
				selectedGame.getTiles().get(i).setHasBeenVisited(false);
			}
			for(int j = 0; j < selectedGame.numberOfPlayers();j++){
				Tile startingTile = selectedGame.getPlayer(j).getStartingTile();
				selectedGame.getPlayers().get(j).setCurrentTile(startingTile);
				selectedGame.getPlayers().get(j).getCurrentTile().setHasBeenVisited(true);
			}
			selectedGame.setCurrentPlayer(selectedGame.getPlayers().get(0));
			selectedGame.setCurrentConnectionPieces(selectedGame.SpareConnectionPieces);
			selectedGame.setMode(Mode.GAME);
		}
		catch(RuntimeException e){
			error = e.getMessage();
			throw new InvalidInputException(error);
		}		
	}

	
	/*
	 * 2. Take a turn (roll the die, move to new position)
	 * Charles
	 */
	public List<Tile> rollDie(){
		//TODO: CHARLES
		TileO tileO = TileOApplication.getTileO(); 
		Game currentGame = tileO.getCurrentGame();
		List<Tile> possibleMoves = currentGame.rollDie();
		return possibleMoves; //method in Game class
	}
	
	/*
	 * 3. Land on a tile (basic behavior for hidden, regular, and action tiles)
	 * Chris
	 */
	public void landedOnTile(Tile tile) throws InvalidInputException{
		
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
			tile.land();

		}
		catch(RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}

	
	/*
	 * 4. Take the first card from the deck of cards
	 * CM
	 */
	
	//helper method called within this controller
	private ActionCard drawCard(Game currentGame) {
		
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

	
	/*
	 * 5. Action card "Roll the die for an extra turn"
	 * CM
	 */
	public void playRollDieExtraTurn() {
		TileO tileO = TileOApplication.getTileO();
		Game currentGame = tileO.getCurrentGame();
		
		//the drawn card needs to be a rollDieActionCard since this method is only called in the appropriate mode
		RollDieActionCard rollDieActionCard = (RollDieActionCard) drawCard(currentGame);
		
		rollDieActionCard.play();
		
		currentGame.setMode(Mode.GAME);		
		
	}

	
	/*
	 * 6. Action card "Connect two adjacent tiles with a connection piece from the pile of spare connection pieces"
	 * Justin
	 */
	public void playConnectTilesActionCard (Tile selectedTile1, Tile selectedTile2) throws InvalidInputException{
		TileO tileO = TileOApplication.getTileO();
		Game currentGame = tileO.getCurrentGame();
		//Deck deck = currentGame.getDeck();
		ConnectTilesActionCard connectTilesActionCard = (ConnectTilesActionCard) drawCard(currentGame);
//		
//		Player currentPlayer = currentGame.getCurrentPlayer();
//		
//		int x1 = selectedTile1.getX();
//		int y1 = selectedTile1.getY();
//		int x2 = selectedTile2.getX();
//		int y2 = selectedTile2.getY();
//		
		String error = "";
		// Check if there are tiles that can be selected in the game
		if (currentGame.numberOfTiles() < 2){
			error = "There are less than 2 tiles in the current game.";
		}
		// Check if the selected tiles are adjacent
		if (!isAdjacent(selectedTile1, selectedTile2)){
			error = error + "The selected tiles are not adjacent";
		}
		// Check if connection pieces are available
		if (currentGame.getCurrentConnectionPieces() < 1){
			error = error + "There are 0 connection pieces available";
		}
		// Check if connection already exist at a location
			if (isConnected(selectedTile1, selectedTile2)){
				error = error + "The two selected tiles are already connected.";
			}
//		// Check if there are two tiles that are adjacent
//			if (!areAdjacent (selectedTile1, currentGame)){
//				error = error + "No tiles are adjacent; it is impossible to connect two tiles.";
//			}
		
		// Check if errors are detected
		if (error.length() > 0){
			throw new InvalidInputException (error.trim());
		}
		
		
		
		
		try 
		{
			// Connect the two selected tiles
			connectTilesActionCard.play(selectedTile1, selectedTile2);
			
			// Check if current player is last player
			if (currentGame.indexOfPlayer(currentGame.getCurrentPlayer()) == currentGame.numberOfPlayers()-1){
				currentGame.setCurrentPlayer(currentGame.getPlayer(0));
			}
			else{
				currentGame.setCurrentPlayer(currentGame.getPlayer(currentGame.indexOfPlayer(currentGame.getCurrentPlayer())+1));
			}
//			// Check if current card is the last card
//			if (deck.numberOfCards() == 1){
//				deck.shuffle();
//			}
//			else{
//				deck.setCurrentCard(deck.getCard(deck.indexOfCard(deck.getCurrentCard())+1));
//			}
			
			// Set game mode to GAME
			currentGame.setMode(Mode.GAME);
		}
		catch (RuntimeException e) 
		{
			error = e.getMessage();
			throw new InvalidInputException(error);
		}
	}
	
	
	/*
	 * 7. Action card "Remove a connection piece from the board and place it in the pile of spare connection pieces"
	 * Li
	 */
	public void playRemoveConnectionActionCard(Connection connection) throws InvalidInputException {
		//TODO: LI
		TileO tileO = TileOApplication.getTileO();
		Game currentGame = tileO.getCurrentGame();
		List<Connection> connectionList = currentGame.getConnections();
		
		if (connectionList.contains(connection)) {
			//Deck deck = currentGame.getDeck();
			ActionCard currentCard = drawCard(currentGame);
			//Player currentPlayer = currentGame.getCurrentPlayer();
			
			try {
				//current card needs to be a remove connection action card
				if (currentCard instanceof RemoveConnectionActionCard) {
					//play(connection)???
					//"play() needs to be added to the removeconnectionactioncard class"					
					((RemoveConnectionActionCard) currentCard).play(connection);
				}				
				setNextPlayer(currentGame);		
				currentGame.setMode(Mode.GAME);
			} 
			catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());	
			}
		}						
	}
	
	
	
	/*
	 * 8. Action card "Move your playing piece to an arbitrary tile that is not your current tile"
	 * Victor
	 */
	public void playTeleportActionCard(Tile tile) throws InvalidInputException {
		//TODO: VICTORIQUE
		
		TileO tileO = TileOApplication.getTileO();
		Game currentGame = tileO.getCurrentGame();
		//TODO remove Deck deck = currentGame.getDeck();
		
		TeleportActionCard teleportcard = (TeleportActionCard) drawCard(currentGame);
		
		//deck.setCurrentCard(deck.getCard(deck.indexOfCard(teleportcard)+1));

		List<Tile> tiles = currentGame.getTiles();
		String error = "";
		if(tiles.contains(tile)==false){
			error = "Please select an existing tile on the board.";
		}
		if(error.length() > 0){
			throw new InvalidInputException(error.trim());
		}
		try{
		teleportcard.play(tile);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
	}

	
	/*
	 * 9. Save and load game to continue playing at a later point
	 * Li
	 */
	public void saveGame() {
		//TODO: LI
		//TileO tileO = TileOApplication.getTileO();
		//Game currentGame = tileO.getCurrentGame();
		TileOApplication.save();
		
	}
	
	public Game loadGame(int index) throws InvalidInputException {
		//TODO: LI check if it's load() or this function
		//set mode to "GAME" when loading a game
		
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
	
	
	//helper method for setting the next player
	public void setNextPlayer(Game aGame) {
		List<Player> playerList = aGame.getPlayers();
		Player currentPlayer = aGame.getCurrentPlayer();
		int playerIndex = aGame.indexOfPlayer(currentPlayer);
				
		//checks if current player is the last player
		if (playerIndex + 1 == playerList.size()) {
			//if it is, set the first player to current player
			aGame.setCurrentPlayer(playerList.get(0));
		}
		//if it's not, set the next player
		else {
			Player nextPlayer = playerList.get(playerIndex + 1);
			aGame.setCurrentPlayer(nextPlayer);
		}		
	}
	
	//helper method to check if tiles are adjacent
	public boolean isAdjacent(Tile tile1, Tile tile2) {
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
	
	//helper method to check if connection already exists
		public boolean isConnected (Tile tile1, Tile tile2){
			
			
			Connection [] possibleConnections1 = tile1.getConnections().toArray(new Connection [tile1.getConnections().size()]);
			Connection [] possibleConnections2 = tile2.getConnections().toArray(new Connection [tile2.getConnections().size()]);
			
			for (int i = 0; i < possibleConnections1.length; i++){
				for (int j = 0; j < possibleConnections2.length; j++){
					if (possibleConnections1[i] == possibleConnections2[j]){
						return true;
					}
				}
			}
			return false;
		}
	
//	// helper method to check is there exist two adjacent tiles
//		public boolean areAdjacent(Tile tile1, Game game1){
//			Tile [] activeTiles = game1.getTiles().toArray(new Tile [game1.getTiles().size()]);
//			
//			for (int i = 0; i < activeTiles.length; i++){
//				if (isAdjacent(tile1, activeTiles[i])){
//					return true;
//				}
//			}
//			return false;
//		}


	
	
}
