package ca.mcgill.ecse223.tileo.controller;
import java.util.*;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.ConnectTilesActionCard;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Deck;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.model.LoseTurnActionCard;
import ca.mcgill.ecse223.tileo.model.LoseTurnRandomlyActionCard;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.RemoveConnectionActionCard;
import ca.mcgill.ecse223.tileo.model.RevealActionCard;
import ca.mcgill.ecse223.tileo.model.RevealActionTilesActionCard;
import ca.mcgill.ecse223.tileo.model.RollDieActionCard;
import ca.mcgill.ecse223.tileo.model.SendBackToStartActionCard;
import ca.mcgill.ecse223.tileo.model.TeleportActionCard;
import ca.mcgill.ecse223.tileo.model.TeleportOtherActionCard;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.model.TurnActionTilesInactiveActionCard;
import ca.mcgill.ecse223.tileo.model.WinTile;
import ca.mcgill.ecse223.tileo.model.WinTileHintActionCard;


public class DesignModeController {
	

	private static final String rollDieInstruction = "Roll the die for an extra turn.";
	private static final String connectTilesInstruction = "Connect two adjacent tiles with a connection piece from the pile of spare connection pieces."; 
	private static final String teleportInstruction = "Move your playing piece to an arbitrary tile that is not your current tile.";
	private static final String removeConnectionInstruction = "Remove a connection piece from the board and place it in the pile of spare connection pieces.";
	private static final String loseTurnInstruction = "You lose your next turn.";
	private static final String revealInstruction = "Choose a tile to reveal its type.";
	private static final String loseTurnRandomlyInstruction = "Make all players lose their turns a random number of times.";
	private static final String revealActionTilesInstruction = "Show all action tiles for 5 seconds.";
	private static final String sendBackToStartInstruction = "Send other player back to start.";
	private static final String teleportOtherInstruction = "Move a player other than yourself to another tile.";
	private static final String turnActiontilesInactiveInstruction = "Make all active tiles inactive.";
	private static final String winTileHintInstruction = "Choose a tile to see if it is connected to the win tile";


	public void addDesignTile(int x, int y, String TileType, int aInactivityPeriod) throws InvalidInputException {		
		Game currentGame = TileOApplication.getTileO().getCurrentGame();
		String error = "";
		
		if(x>50||y>50) {
			error = "To make the screen legible, enter a coordinate value of less than 50.";
		}		
		if(x<=0 || y<=0) {
			error = "The coordinate value must be greater than zero! (The origin is (1,1)).";
		}		
        for(Tile aTile: currentGame.getTiles()) {	//iterates through each tiles of the currentGame
        	int xTile = aTile.getX();
        	int yTile = aTile.getY();
        	if(xTile == x && yTile == y){
        		error = "The tile already exists.";
        	}
        }
        if((TileType == "ActionTile") && (aInactivityPeriod<0)) {
        	error = "The inactivity period must be a positive value!";        	
        }
                
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
				
		try {
			if(TileType == "NormalTile") {
				NormalTile normalTile = new NormalTile(x, y, currentGame);
	            currentGame.addTile(normalTile);	            
			}			
			else if(TileType == "ActionTile") {
				createActionTile(x, y, currentGame, aInactivityPeriod);
			}			
			else if(TileType == "WinTile") {	
				createWinTile(x, y, currentGame);				
			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(error);
		}
	}
	
	
	
	/*
	 * 1. Create a game with a number of players
	 * Justin
	 */
	public void createGame(int numberOfPlayers) throws InvalidInputException{
		TileO tileO = TileOApplication.getTileO();
		Game game = new Game (32,tileO);
		tileO.setCurrentGame(game);
		game.setMode(Mode.DESIGN);
			
		// Assign player numbers to each player
		for(int playerNumber = 1; playerNumber <= numberOfPlayers; playerNumber++){
			game.addPlayer(playerNumber);
			game.getPlayer(playerNumber-1).setColor(Player.Color.values()[playerNumber-1]);
		}			
	}
	
	
	/*
	 * 2. Place a tile on the game board
	 * Chris
	 */
	// deleted method
	
	
	/*
	 * 3. Remove a tile from the game board
	 * Li
	 */
	public void removeTileFromGame(Tile aTile) throws InvalidInputException{
		Game currentGame = TileOApplication.getTileO().getCurrentGame();
		if (aTile.hasConnections()) {
			String error = "Cannot delete a tile that has connections.";
			throw new InvalidInputException(error.trim());
		}
		try{
			if(aTile instanceof WinTile){
				currentGame.setWinTile(null);
			}
			aTile.delete();			
		}
		catch (RuntimeException e) {
			throw new InvalidInputException (e.getMessage());
		}
	}
	
	
	/*
	 * 4. Connect two tiles with a connection piece
	 * Justin
	 */
	public void connectTwoTiles (Tile selectedTile1, Tile selectedTile2) throws InvalidInputException{
		TileO tileO = TileOApplication.getTileO();
		Game currentGame = tileO.getCurrentGame();
		
		String error = "";
		// Check if there are tiles that can be selected in the game
		if (currentGame.numberOfTiles() < 2){
			error = "There are less than 2 tiles in the current game.";
		}
		// Check if the selected tiles are adjacent
		if (!isAdjacent(selectedTile1, selectedTile2)){
			error = "The selected tiles are not adjacent!";
		}		
		// Check if connection already exist at a location
		if (isConnected(selectedTile1, selectedTile2)){
			error = "The two selected tiles are already connected.";
		}		
		if (error.length() > 0){
			throw new InvalidInputException (error.trim());
		}

		try {
			Connection newConnection =  currentGame.addConnection();
			selectedTile1.addConnection(newConnection);
			selectedTile2.addConnection(newConnection);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException (e.getMessage());
		}
	}
	
	
	//helper method to check if two tiles are adjacent
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
	
	
	//helper method two tiles are already connected
	public boolean isConnected (Tile tile1, Tile tile2){
		for (Connection c1: tile1.getConnections()){
			for (Connection c2: tile2.getConnections()){
				if (c1 == c2){
					return true;
				}
			}
		}
		return false;
	}
	
		
	/*
	 * 5. Remove connection between two tiles
	 * Justin
	 */
	public void removeConnection (Connection selectedConnection) throws InvalidInputException{
		try {
			// Delete the selected connection
			selectedConnection.delete();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException (e.getMessage());
		}
	}
	
	
	/*
	 * 6. Identify the hidden tile
	 * CM
	 */
	public void createWinTile(int x, int y, Game game) throws InvalidInputException {
		String error = "";
		if(game.hasWinTile()){
			error = "You already have a Win Tile on the board!";
		}		
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		
		try {
			WinTile winTile = new WinTile(x,y,game);			
			game.addTile(winTile);
			game.setWinTile(winTile);
		}
		catch (RuntimeException e){
			throw new InvalidInputException (e.getMessage());
		}
	}
	
	
	/*
	 * 7. Identify the starting tile of a player
	 * Chris
	 */
	public void setPlayerStartingTile(int playerNumber, Tile startingTile) throws InvalidInputException {
		TileO tileO = TileOApplication.getTileO();
		Game currentGame = tileO.getCurrentGame();
		String error = "";		
		
		if(playerNumber > currentGame.numberOfPlayers()) {
			error = "The player doesn't exist!";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		try {
			for (Player aPlayer: currentGame.getPlayers()){
				if (aPlayer.getNumber() == playerNumber){
					aPlayer.setStartingTile(startingTile);
					aPlayer.setCurrentTile(startingTile);
				}
			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(error);
		}
	}
	
	
	/*
	 * 8. Identify an action tile (inactivity period not required for this iteration)
	 * Victor
	 */
	public void createActionTile(int x, int y, Game game, int aInactivityPeriod) throws InvalidInputException {
		try{
			ActionTile actionTile = new ActionTile( x, y, game, aInactivityPeriod);
	        game.addTile(actionTile);
		}
		catch (RuntimeException e){
			throw new InvalidInputException (e.getMessage());
		}
	}
	
	
	/*
	 * 9. Select 32 cards from predefined choices
	 * Charles
	 */
	public void createDeck(
			int connectTiles, 
			int loseTurn,
			int loseTurnRdm,
			int removeConnection,
			int reveal,
			int revealActionTiles,
			int rollDie,
			int sendBackStart,
			int teleport,
			int teleportOther,
			int turnInactive,
			int winHint) throws InvalidInputException {
		
		int cards[] = new int[]{
			connectTiles, 
			loseTurn,
			loseTurnRdm,
			removeConnection,
			reveal,
			revealActionTiles,
			rollDie,
			sendBackStart,
			teleport,
			teleportOther,
			turnInactive,
			winHint
		};
		
		String error = "";
		int totalCards = 0;
		for (int i = 0; i<cards.length; i++){
			totalCards += cards[i];
			if(cards[i]<0) {
				error = "The number of each action card should be positive";
			}
		}
		
		if(totalCards != 32) {
			error = "The sum of numbers of different types of action cards should be 32!";
		}		
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		
		try{
			TileO tileO = TileOApplication.getTileO();
			Game currentGame = tileO.getCurrentGame();
			Deck deck = currentGame.getDeck();
			
			//remove all cards if deck already has cards
			if(deck.hasCards()){
				deck.clearCards();
			}
			
			int i = 0;
			for (int j = 0; j < cards[0]; j++, i++){
				deck.addCardAt( new ConnectTilesActionCard(connectTilesInstruction, deck), i);
			}
			for (int j = 0; j < cards[1]; j++, i++) {
				deck.addCardAt( new LoseTurnActionCard(loseTurnInstruction,deck), i);
			}
			for (int j = 0; j < cards[2]; j++, i++){
				deck.addCardAt( new LoseTurnRandomlyActionCard(loseTurnRandomlyInstruction,deck), i);
			}
			for (int j = 0; j < cards[3]; j++, i++) {
				deck.addCardAt( new RemoveConnectionActionCard(removeConnectionInstruction,deck), i);
			}
			for (int j = 0; j < cards[4]; j++, i++) {
				deck.addCardAt( new RevealActionCard(revealInstruction,deck), i);
			}
			for (int j = 0; j < cards[5]; j++, i++) {
				deck.addCardAt( new RevealActionTilesActionCard(revealActionTilesInstruction,deck), i);
			}
			for (int j = 0; j < cards[6]; j++, i++) {
				deck.addCardAt( new RollDieActionCard(rollDieInstruction,deck), i);
			}
			for (int j = 0; j < cards[7]; j++, i++) {
				deck.addCardAt( new SendBackToStartActionCard(sendBackToStartInstruction,deck), i);
			}
			for (int j = 0; j < cards[8]; j++, i++) {
				deck.addCardAt( new TeleportActionCard(teleportInstruction,deck), i);
			}
			for (int j = 0; j < cards[9]; j++, i++) {
				deck.addCardAt( new TeleportOtherActionCard(teleportOtherInstruction,deck), i);
			}
			for (int j = 0; j < cards[10]; j++, i++) {
				deck.addCardAt( new TurnActionTilesInactiveActionCard(turnActiontilesInactiveInstruction,deck), i);
			}
			for (int j = 0; j < cards[11]; j++, i++) {
				deck.addCardAt( new WinTileHintActionCard(winTileHintInstruction,deck), i);
			}
			
		}	
		catch (RuntimeException e) {
			throw new InvalidInputException(error);
		}
	}

	
	/*
	 * 10. Save and load the game design
	 * Victor
	 */
	public void saveDesign() {
		//TODO: VICTORIQUE
		TileOApplication.save();
	}
	
	public Game loadDesign(int index) throws InvalidInputException{
		String error = "";
		if (!TileOApplication.getTileO().hasGames()) {
			error = "The current TileO does not have games!";
			throw new InvalidInputException(error.trim());
		}
		
		try{
			Game selectedGame = TileOApplication.getTileO().getGame(index);
			selectedGame.setMode(Mode.DESIGN);
			//Player.reinitializeUniquePlayer(selectedGame.getPlayers()); //ADDED
			return selectedGame;
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
}

