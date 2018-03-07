/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

/**
 * The designer designed SpecificGame, the SpecificGame indicates the numbers of players which should be between 2 to 4 players, the SpecificGame is divided into 3 states: Designing state, playing state, ending state. After one player wins the specific game, game ends.
 */
// line 32 "../../../../../TileO.ump"
public class SpecificGame extends TileO
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificGame Attributes
  private int numberOfPlayers;

  //SpecificGame State Machines
  public enum GameStateType { Designing, Playing, End }
  private GameStateType gameStateType;

  //SpecificGame Associations
  private Element element;
  private Designer designer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificGame(int aNumberOfPlayers, Element aElement, Designer aDesigner)
  {
    super();
    numberOfPlayers = aNumberOfPlayers;
    if (aElement == null || aElement.getBoardgame() != null)
    {
      throw new RuntimeException("Unable to create SpecificGame due to aElement");
    }
    element = aElement;
    if (aDesigner == null || aDesigner.getSpecificGame() != null)
    {
      throw new RuntimeException("Unable to create SpecificGame due to aDesigner");
    }
    designer = aDesigner;
    setGameStateType(GameStateType.Designing);
  }

  public SpecificGame(int aNumberOfPlayers)
  {
    super();
    numberOfPlayers = aNumberOfPlayers;
    element = new Element(this);
    designer = new Designer(this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberOfPlayers(int aNumberOfPlayers)
  {
    boolean wasSet = false;
    numberOfPlayers = aNumberOfPlayers;
    wasSet = true;
    return wasSet;
  }

  public int getNumberOfPlayers()
  {
    return numberOfPlayers;
  }

  public String getGameStateTypeFullName()
  {
    String answer = gameStateType.toString();
    return answer;
  }

  public GameStateType getGameStateType()
  {
    return gameStateType;
  }

  public boolean setGameStateType(GameStateType aGameStateType)
  {
    gameStateType = aGameStateType;
    return true;
  }

  public Element getElement()
  {
    return element;
  }

  public Designer getDesigner()
  {
    return designer;
  }

  public void delete()
  {
    Element existingElement = element;
    element = null;
    if (existingElement != null)
    {
      existingElement.delete();
    }
    Designer existingDesigner = designer;
    designer = null;
    if (existingDesigner != null)
    {
      existingDesigner.delete();
    }
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "numberOfPlayers" + ":" + getNumberOfPlayers()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "element = "+(getElement()!=null?Integer.toHexString(System.identityHashCode(getElement())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "designer = "+(getDesigner()!=null?Integer.toHexString(System.identityHashCode(getDesigner())):"null")
     + outputString;
  }
}