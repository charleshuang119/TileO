/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

/**
 * Person who designs his or her specific game. The designer will design the initial tiles’ locations, connection pieces, starting positions, etc.
 */
// line 10 "../../../../../TileO.ump"
public class Designer extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Designer Associations
  private SpecificGame specificGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Designer(SpecificGame aSpecificGame)
  {
    super();
    if (aSpecificGame == null || aSpecificGame.getDesigner() != null)
    {
      throw new RuntimeException("Unable to create Designer due to aSpecificGame");
    }
    specificGame = aSpecificGame;
  }

  public Designer(int aNumberOfPlayersForSpecificGame, Element aElementForSpecificGame)
  {
    super();
    specificGame = new SpecificGame(aNumberOfPlayersForSpecificGame, aElementForSpecificGame, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public SpecificGame getSpecificGame()
  {
    return specificGame;
  }

  public void delete()
  {
    SpecificGame existingSpecificGame = specificGame;
    specificGame = null;
    if (existingSpecificGame != null)
    {
      existingSpecificGame.delete();
    }
    super.delete();
  }

}