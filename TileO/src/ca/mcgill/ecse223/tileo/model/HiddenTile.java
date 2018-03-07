/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.util.*;

/**
 * this type of tile will be turned into Grey when a player lands on it and will end the game
 */
// line 148 "../../../../../TileO.ump"
public class HiddenTile extends Tile
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HiddenTile(Location aLocationOfTile, Board aBoard)
  {
    super(aLocationOfTile, aBoard);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}