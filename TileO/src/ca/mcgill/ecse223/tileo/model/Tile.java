/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

/**
 * When a normal tile is instantiated, a Tile will be instantiated. Basically, a Normal Tile is always a Tile. ActionTile and HiddenTile are ‘’special’’ Tiles.
 */
// line 123 "../../../../../TileO.ump"
public class Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tile Attributes
  private String color;
  private Location locationOfTile;
  private boolean isRightConnected;
  private boolean isLeftConnected;
  private boolean isTopConnected;
  private boolean isBottomConnected;
  private boolean temporarilyVisited;
  private boolean wasVisited;

  //Tile Associations
  private List<ConnectionPiece> connectionPieces;
  private Board board;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tile(Location aLocationOfTile, Board aBoard)
  {
    resetColor();
    locationOfTile = aLocationOfTile;
    isRightConnected = false;
    isLeftConnected = false;
    isTopConnected = false;
    isBottomConnected = false;
    temporarilyVisited = false;
    wasVisited = false;
    connectionPieces = new ArrayList<ConnectionPiece>();
    boolean didAddBoard = setBoard(aBoard);
    if (!didAddBoard)
    {
      throw new RuntimeException("Unable to create tile due to board");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setColor(String aColor)
  {
    boolean wasSet = false;
    color = aColor;
    wasSet = true;
    return wasSet;
  }

  public boolean resetColor()
  {
    boolean wasReset = false;
    color = getDefaultColor();
    wasReset = true;
    return wasReset;
  }

  public boolean setLocationOfTile(Location aLocationOfTile)
  {
    boolean wasSet = false;
    locationOfTile = aLocationOfTile;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsRightConnected(boolean aIsRightConnected)
  {
    boolean wasSet = false;
    isRightConnected = aIsRightConnected;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsLeftConnected(boolean aIsLeftConnected)
  {
    boolean wasSet = false;
    isLeftConnected = aIsLeftConnected;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsTopConnected(boolean aIsTopConnected)
  {
    boolean wasSet = false;
    isTopConnected = aIsTopConnected;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsBottomConnected(boolean aIsBottomConnected)
  {
    boolean wasSet = false;
    isBottomConnected = aIsBottomConnected;
    wasSet = true;
    return wasSet;
  }

  public boolean setTemporarilyVisited(boolean aTemporarilyVisited)
  {
    boolean wasSet = false;
    temporarilyVisited = aTemporarilyVisited;
    wasSet = true;
    return wasSet;
  }

  public boolean setWasVisited(boolean aWasVisited)
  {
    boolean wasSet = false;
    wasVisited = aWasVisited;
    wasSet = true;
    return wasSet;
  }

  /**
   * WHITE if it has not been stepped on, and BLACK if visited
   */
  public String getColor()
  {
    return color;
  }

  public String getDefaultColor()
  {
    return "white";
  }

  public Location getLocationOfTile()
  {
    return locationOfTile;
  }

  /**
   * the x AND y location for tiles must be even
   */
  public boolean getIsRightConnected()
  {
    return isRightConnected;
  }

  public boolean getIsLeftConnected()
  {
    return isLeftConnected;
  }

  public boolean getIsTopConnected()
  {
    return isTopConnected;
  }

  public boolean getIsBottomConnected()
  {
    return isBottomConnected;
  }

  /**
   * isConnected are defaulted to FALSE
   * if a player is currently on the tile - passing on the tile
   */
  public boolean getTemporarilyVisited()
  {
    return temporarilyVisited;
  }

  /**
   * will turn black if it has been landed on
   */
  public boolean getWasVisited()
  {
    return wasVisited;
  }

  public boolean isIsRightConnected()
  {
    return isRightConnected;
  }

  public boolean isIsLeftConnected()
  {
    return isLeftConnected;
  }

  public boolean isIsTopConnected()
  {
    return isTopConnected;
  }

  public boolean isIsBottomConnected()
  {
    return isBottomConnected;
  }

  public boolean isTemporarilyVisited()
  {
    return temporarilyVisited;
  }

  public boolean isWasVisited()
  {
    return wasVisited;
  }

  public ConnectionPiece getConnectionPiece(int index)
  {
    ConnectionPiece aConnectionPiece = connectionPieces.get(index);
    return aConnectionPiece;
  }

  /**
   * One tile can only connect to the max 4 connection piece
   */
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

  public Board getBoard()
  {
    return board;
  }

  public static int minimumNumberOfConnectionPieces()
  {
    return 0;
  }

  public static int maximumNumberOfConnectionPieces()
  {
    return 4;
  }

  public ConnectionPiece addConnectionPiece(Location aLocation, Board aBoard)
  {
    if (numberOfConnectionPieces() >= maximumNumberOfConnectionPieces())
    {
      return null;
    }
    else
    {
      return new ConnectionPiece(aLocation, aBoard, this);
    }
  }

  public boolean addConnectionPiece(ConnectionPiece aConnectionPiece)
  {
    boolean wasAdded = false;
    if (connectionPieces.contains(aConnectionPiece)) { return false; }
    if (numberOfConnectionPieces() >= maximumNumberOfConnectionPieces())
    {
      return wasAdded;
    }

    Tile existingTile = aConnectionPiece.getTile();
    boolean isNewTile = existingTile != null && !this.equals(existingTile);
    if (isNewTile)
    {
      aConnectionPiece.setTile(this);
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
    //Unable to remove aConnectionPiece, as it must always have a tile
    if (!this.equals(aConnectionPiece.getTile()))
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

  public boolean setBoard(Board aBoard)
  {
    boolean wasSet = false;
    if (aBoard == null)
    {
      return wasSet;
    }

    Board existingBoard = board;
    board = aBoard;
    if (existingBoard != null && !existingBoard.equals(aBoard))
    {
      existingBoard.removeTile(this);
    }
    board.addTile(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=connectionPieces.size(); i > 0; i--)
    {
      ConnectionPiece aConnectionPiece = connectionPieces.get(i - 1);
      aConnectionPiece.delete();
    }
    Board placeholderBoard = board;
    this.board = null;
    placeholderBoard.removeTile(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "color" + ":" + getColor()+ "," +
            "isRightConnected" + ":" + getIsRightConnected()+ "," +
            "isLeftConnected" + ":" + getIsLeftConnected()+ "," +
            "isTopConnected" + ":" + getIsTopConnected()+ "," +
            "isBottomConnected" + ":" + getIsBottomConnected()+ "," +
            "temporarilyVisited" + ":" + getTemporarilyVisited()+ "," +
            "wasVisited" + ":" + getWasVisited()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "locationOfTile" + "=" + (getLocationOfTile() != null ? !getLocationOfTile().equals(this)  ? getLocationOfTile().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "board = "+(getBoard()!=null?Integer.toHexString(System.identityHashCode(getBoard())):"null")
     + outputString;
  }
}