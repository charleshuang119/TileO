/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

/**
 * Abstract class to specify the existence of some elements in each specific game
 */
// line 45 "../../../../../TileO.ump"
public abstract class Element
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Element Associations
  private SpecificGame boardgame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Element(SpecificGame aBoardgame)
  {
    if (aBoardgame == null || aBoardgame.getElement() != null)
    {
      throw new RuntimeException("Unable to create Element due to aBoardgame");
    }
    boardgame = aBoardgame;
  }

  public Element(int aNumberOfPlayersForBoardgame, Designer aDesignerForBoardgame)
  {
    boardgame = new SpecificGame(aNumberOfPlayersForBoardgame, this, aDesignerForBoardgame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public SpecificGame getBoardgame()
  {
    return boardgame;
  }

  public void delete()
  {
    SpecificGame existingBoardgame = boardgame;
    boardgame = null;
    if (existingBoardgame != null)
    {
      existingBoardgame.delete();
    }
  }

}