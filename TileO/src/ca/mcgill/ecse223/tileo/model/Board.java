/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

/**
 * Board specifies the size of the specific game, X-axis size and Y-axis size indicate the dimension of the whole board,which is a subclass of the Element class. This way, an origin can also be set to ease locations of the elements. The board also contains the Tiles and Connection Piece.
 */
// line 98 "../../../../../TileO.ump"
public class Board extends Element
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Board Attributes
  private int xAxisSize;
  private int yAxisSize;

  //Board Associations
  private List<Tile> tiles;
  private List<ConnectionPiece> connectionPieces;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board(SpecificGame aBoardgame, int aXAxisSize, int aYAxisSize)
  {
    super(aBoardgame);
    xAxisSize = aXAxisSize;
    yAxisSize = aYAxisSize;
    tiles = new ArrayList<Tile>();
    connectionPieces = new ArrayList<ConnectionPiece>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getXAxisSize()
  {
    return xAxisSize;
  }

  public int getYAxisSize()
  {
    return yAxisSize;
  }

  public Tile getTile(int index)
  {
    Tile aTile = tiles.get(index);
    return aTile;
  }

  public List<Tile> getTiles()
  {
    List<Tile> newTiles = Collections.unmodifiableList(tiles);
    return newTiles;
  }

  public int numberOfTiles()
  {
    int number = tiles.size();
    return number;
  }

  public boolean hasTiles()
  {
    boolean has = tiles.size() > 0;
    return has;
  }

  public int indexOfTile(Tile aTile)
  {
    int index = tiles.indexOf(aTile);
    return index;
  }

  public ConnectionPiece getConnectionPiece(int index)
  {
    ConnectionPiece aConnectionPiece = connectionPieces.get(index);
    return aConnectionPiece;
  }

  public List<ConnectionPiece> getConnectionPieces()
  {
    List<ConnectionPiece> newConnectionPieces = Collections.unmodifiableList(connectionPieces);
    return newConnectionPieces;
  }

  public int numberOfConnectionPieces()
  {
    int number = connectionPieces.size();
    return number;
  }

  public boolean hasConnectionPieces()
  {
    boolean has = connectionPieces.size() > 0;
    return has;
  }

  public int indexOfConnectionPiece(ConnectionPiece aConnectionPiece)
  {
    int index = connectionPieces.indexOf(aConnectionPiece);
    return index;
  }

  public static int minimumNumberOfTiles()
  {
    return 0;
  }

  public Tile addTile(Location aLocationOfTile)
  {
    return new Tile(aLocationOfTile, this);
  }

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    Board existingBoard = aTile.getBoard();
    boolean isNewBoard = existingBoard != null && !this.equals(existingBoard);
    if (isNewBoard)
    {
      aTile.setBoard(this);
    }
    else
    {
      tiles.add(aTile);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTile(Tile aTile)
  {
    boolean wasRemoved = false;
    //Unable to remove aTile, as it must always have a board
    if (!this.equals(aTile.getBoard()))
    {
      tiles.remove(aTile);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTileAt(Tile aTile, int index)
  {  
    boolean wasAdded = false;
    if(addTile(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTileAt(Tile aTile, int index)
  {
    boolean wasAdded = false;
    if(tiles.contains(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTileAt(aTile, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfConnectionPieces()
  {
    return 0;
  }

  public ConnectionPiece addConnectionPiece(Location aLocation, Tile aTile)
  {
    return new ConnectionPiece(aLocation, this, aTile);
  }

  public boolean addConnectionPiece(ConnectionPiece aConnectionPiece)
  {
    boolean wasAdded = false;
    if (connectionPieces.contains(aConnectionPiece)) { return false; }
    Board existingBoard = aConnectionPiece.getBoard();
    boolean isNewBoard = existingBoard != null && !this.equals(existingBoard);
    if (isNewBoard)
    {
      aConnectionPiece.setBoard(this);
    }
    else
    {
      connectionPieces.add(aConnectionPiece);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeConnectionPiece(ConnectionPiece aConnectionPiece)
  {
    boolean wasRemoved = false;
    //Unable to remove aConnectionPiece, as it must always have a board
    if (!this.equals(aConnectionPiece.getBoard()))
    {
      connectionPieces.remove(aConnectionPiece);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addConnectionPieceAt(ConnectionPiece aConnectionPiece, int index)
  {  
    boolean wasAdded = false;
    if(addConnectionPiece(aConnectionPiece))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConnectionPieces()) { index = numberOfConnectionPieces() - 1; }
      connectionPieces.remove(aConnectionPiece);
      connectionPieces.add(index, aConnectionPiece);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveConnectionPieceAt(ConnectionPiece aConnectionPiece, int index)
  {
    boolean wasAdded = false;
    if(connectionPieces.contains(aConnectionPiece))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConnectionPieces()) { index = numberOfConnectionPieces() - 1; }
      connectionPieces.remove(aConnectionPiece);
      connectionPieces.add(index, aConnectionPiece);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addConnectionPieceAt(aConnectionPiece, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (tiles.size() > 0)
    {
      Tile aTile = tiles.get(tiles.size() - 1);
      aTile.delete();
      tiles.remove(aTile);
    }
    
    while (connectionPieces.size() > 0)
    {
      ConnectionPiece aConnectionPiece = connectionPieces.get(connectionPieces.size() - 1);
      aConnectionPiece.delete();
      connectionPieces.remove(aConnectionPiece);
    }
    
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "xAxisSize" + ":" + getXAxisSize()+ "," +
            "yAxisSize" + ":" + getYAxisSize()+ "]"
     + outputString;
  }
}