package ca.mcgill.ecse223.tileo.view;

import javax.swing.JPanel;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.ActionTile;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.TileO;
import ca.mcgill.ecse223.tileo.model.WinTile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;


public class TilePanelDesign extends JPanel{

	public boolean aTileIsSelected = false;
	public Tile selectedTile;
	
	public boolean aConnectionIsSelected = false;
	public Connection selectedConnection;

	public boolean isTileModified = false;

	//UI elements
	private List<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
	private static final int MINAXISSIZE = 10;
	
	// data element;
	private Game myGame;
	private HashMap<Rectangle2D, Tile> tiles;
	private HashMap<Tile, Rectangle2D> tRectangles;
	private HashMap<Rectangle2D, Connection> connections;
	private HashMap<Connection, Rectangle2D> cRectangles;

	
	public TilePanelDesign(Game game) {
		super();
		
	}
	
	private void init(Game game) {
		this.myGame = game;
		tiles = new HashMap<Rectangle2D, Tile>();
		connections = new HashMap<Rectangle2D, Connection>();
		tRectangles = new HashMap<Tile, Rectangle2D>();
		cRectangles = new HashMap<Connection, Rectangle2D>();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();							
				for (Rectangle2D rectangle : rectangles) {
					if (rectangle.contains(x, y)) {
						if(tiles.containsKey(rectangle)){
							selectedTile = tiles.get(rectangle);
							selectedConnection = null;
							break;
						}
						else if(connections.containsKey(rectangle)){
							selectedConnection = connections.get(rectangle);
							selectedTile = null;
							break;
						}
					}
				}
				repaint();
			}
		});
	}
	
	public void setGame(Game game) {
		init(game);
		//repaint();
	}

	
	//below are 3 helper methods, DONT TOUCH :)))
	public int getXAxis(Game aGame) {
		List<Tile> listTiles = aGame.getTiles();
		int xSize=0;
		
		for (Tile tempTile: listTiles) {
			if (tempTile.getX()>xSize){
				xSize = tempTile.getX();
			}
		}		
		return xSize;		
	}
	
	public int getYAxis(Game aGame) {
		List<Tile> listTiles = aGame.getTiles();
		int ySize = 0;
		 
		for (Tile tempTile: listTiles) {
			if (tempTile.getY()>ySize){
				ySize = tempTile.getY();
			}
		}		
		return ySize;
	}
	
	public int equalize(int xAxisSize, int yAxisSize) {
		int biggest = MINAXISSIZE;
		
		if (xAxisSize>MINAXISSIZE || yAxisSize>MINAXISSIZE) {
			if(xAxisSize>yAxisSize) {
				biggest = xAxisSize;
			}
			else {
				biggest = yAxisSize;
			}
		}		
		return biggest;
	}
	

	public void doDrawing(Graphics g) {		
		if (myGame != null) {
			Graphics2D g2d = (Graphics2D) g.create();
			
			//base size
			int axisSize = equalize(getXAxis(myGame), getYAxis(myGame));
			float squareSize = (float) ((700/axisSize) * (2.9/5.0));
			float SPACING = (float) ((700/axisSize) * (1.0/5.0));
			
			float gridspace = (float)(squareSize + 2*SPACING);
			float locationX = SPACING;
			float locationY = SPACING;
			
			//iterates through all the tiles of the game
			for (Tile aTile: myGame.getTiles()) {
				// drawing colored tiles ------------
				int x = aTile.getX()-1;
				int y = aTile.getY()-1;
				locationX = (float) (SPACING + gridspace*x);
				locationY = (float) (SPACING + gridspace*y);

				Rectangle2D rect = new Rectangle2D.Float(
						locationX, 
						locationY, 
						squareSize, 
						squareSize);
				rectangles.add(rect);
				tiles.put(rect, aTile);
				tRectangles.put(aTile, rect);
				
				if(aTile instanceof NormalTile){
					g2d.setColor(Color.BLUE);
				}
				
				else if(aTile instanceof WinTile){
					g2d.setColor(Color.BLACK);
				}
				
				else if(aTile instanceof ActionTile){
					g2d.setColor(Color.RED);
				}
				
				g2d.draw(rect);
				g2d.fill(rect);
				
				//---------------end of colored tiles
				
				
				//write for each action tile the # for inactivity period
				if (aTile instanceof ActionTile) {
					Rectangle2D r = tRectangles.get(aTile);
					String inactive = String.valueOf(((ActionTile) aTile).getInactivityPeriod());
					g2d.setColor(Color.BLACK);
					g2d.drawString(inactive,
							(int) r.getCenterX()+5, 
							(int) r.getCenterY()+5);
				}
				//-------------end of inactivity period
				
				
				repaint();
			}
		
			//if a tile is selected, color it in pink
			if (selectedTile != null) {
				aTileIsSelected = true;
				Rectangle2D rectangle = tRectangles.get(selectedTile);				
				g2d.setColor(Color.PINK);			
				g2d.fill(rectangle);
			}
			
			for (Player aPlayer: myGame.getPlayers()){
				if (aPlayer.hasStartingTile()){
					Tile cTile = aPlayer.getCurrentTile();
					Rectangle2D r = tRectangles.get(cTile);
					String number = String.valueOf(aPlayer.getNumber());
					
					g2d.setColor(Color.WHITE);
					g2d.drawString(number, (int) r.getCenterX()-13, (int) r.getCenterY()+5);
				}
			}
			
			//iterates through the connections of the game
			for (Connection aConnection: myGame.getConnections()){
				List<Tile> tempTiles = aConnection.getTiles();
				if(tempTiles.size() == 2){
					Tile tile1 = tempTiles.get(0);
					Tile tile2 = tempTiles.get(1);
					
					//horizontal
					if (isH(tile1, tile2)) {				
						locationX = (float) (SPACING + squareSize + smallestXIndex(tile1, tile2)*gridspace);
						locationY = (float) (((700/axisSize)/2 - SPACING/2) + smallestYIndex(tile1, tile2)*gridspace);
						
						Rectangle2D rect = new Rectangle2D.Float(
								locationX, 
								locationY, 
								2*SPACING, 
								SPACING);
						rectangles.add(rect);
						connections.put(rect, aConnection);
						cRectangles.put(aConnection, rect);
						g2d.setColor(Color.BLACK);
						g2d.fill(rect);
					}					
					//vertical			
					else {
						locationX = (float) (((700/axisSize)/2 - SPACING/2) + smallestXIndex(tile1, tile2)*gridspace);
						locationY = (float) (SPACING + squareSize + smallestYIndex(tile1, tile2)*gridspace);

						Rectangle2D rect = new Rectangle2D.Float(
								locationX, 
								locationY, 
								SPACING, 
								2*SPACING);
						rectangles.add(rect);
						connections.put(rect, aConnection);
						cRectangles.put(aConnection, rect);
						g2d.setColor(Color.BLACK);
						g2d.fill(rect);
					}										
				}			
			}
			
			//if a connection is selected
			if (selectedConnection != null){
				aConnectionIsSelected = true;
				Rectangle2D rectangle = cRectangles.get(selectedConnection);
				g2d.setColor(Color.PINK);
				g2d.fill(rectangle);
			}	
		}
	}
	
	public int smallestXIndex(Tile tile1, Tile tile2){
		int smallest = tile1.getX();
		if (tile2.getX()<smallest){
			smallest = tile2.getX();
		}
		return smallest-1;
	}
	
	public int smallestYIndex(Tile tile1, Tile tile2){
		int smallest = tile1.getY();
		if (tile2.getY()<smallest) {
			smallest = tile2.getY();
		}
		return smallest-1;
	}
	
	
	public boolean isH(Tile tile1, Tile tile2){
		if (tile1.getY() == tile2.getY()){
			return true;
		}		
		return false;
	}
	public boolean isV(Tile tile1, Tile tile2){
		if (tile1.getX() == tile2.getX()){
			return true;
		}
		return false;
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
		repaint();
	}
}

