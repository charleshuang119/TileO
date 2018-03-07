/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

/**
 * Pack of extra connection pieces that can be used by the players when he or she draw a Connection card.There are only 32 extra connection pieces in the pack. Players can remove a connection piece and put it in the pack. Players can also use a connection piece from the pack. This will be done by the add/remove connection method
 */
// line 111 "../../../../../TileO.ump"
public class PackOfConnectionPieces extends Element
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PackOfConnectionPieces Attributes
  private int spareConnectionPieces;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PackOfConnectionPieces(SpecificGame aBoardgame)
  {
    super(aBoardgame);
    resetSpareConnectionPieces();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSpareConnectionPieces(int aSpareConnectionPieces)
  {
    boolean wasSet = false;
    spareConnectionPieces = aSpareConnectionPieces;
    wasSet = true;
    return wasSet;
  }

  public boolean resetSpareConnectionPieces()
  {
    boolean wasReset = false;
    spareConnectionPieces = getDefaultSpareConnectionPieces();
    wasReset = true;
    return wasReset;
  }

  /**
   * while in the playing state, it starts with 32 pieces
   */
  public int getSpareConnectionPieces()
  {
    return spareConnectionPieces;
  }

  public int getDefaultSpareConnectionPieces()
  {
    return 32;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "spareConnectionPieces" + ":" + getSpareConnectionPieces()+ "]"
     + outputString;
  }
}