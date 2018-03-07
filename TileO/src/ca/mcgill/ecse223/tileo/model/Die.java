/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

/**
 * Each game has a die. The designer decides for the number of faces, and the rolled number is determined by a rollingDie method and is used to determine by how many tiles a playing piece moves.
 */
// line 64 "../../../../../TileO.ump"
public class Die extends Element
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Die Attributes
  private int numberOfFaces;
  private int rolledNumber;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Die(SpecificGame aBoardgame, int aNumberOfFaces, int aRolledNumber)
  {
    super(aBoardgame);
    numberOfFaces = aNumberOfFaces;
    rolledNumber = aRolledNumber;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberOfFaces(int aNumberOfFaces)
  {
    boolean wasSet = false;
    numberOfFaces = aNumberOfFaces;
    wasSet = true;
    return wasSet;
  }

  public boolean setRolledNumber(int aRolledNumber)
  {
    boolean wasSet = false;
    rolledNumber = aRolledNumber;
    wasSet = true;
    return wasSet;
  }

  public int getNumberOfFaces()
  {
    return numberOfFaces;
  }

  public int getRolledNumber()
  {
    return rolledNumber;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "numberOfFaces" + ":" + getNumberOfFaces()+ "," +
            "rolledNumber" + ":" + getRolledNumber()+ "]"
     + outputString;
  }
}