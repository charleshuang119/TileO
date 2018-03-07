package ca.mcgill.ecse223.tileo.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.SystemColor;

public class DesignHelpPO extends JFrame {
	
	private JPanel mainPnl;
	private JTextPane instructionsPnl;
	

	public DesignHelpPO() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 50, 510, 660);
		setTitle("Help");
		setResizable(false);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		mainPnl = new JPanel();
		getContentPane().add(mainPnl);
		
		instructionsPnl = new JTextPane();
		instructionsPnl.setBackground(SystemColor.window);
		mainPnl.add(instructionsPnl);
		instructionsPnl.setEditable(false);
		instructionsPnl.setText(
				"\n"
				+ "\n"
				+ "Add a tile to the game\n"
				+ "\tChoose a type of type and input its coordinates\n"
				+ "\tThen press \"Add Tile\".\n"
				+ "\n"
				+ "Remove a tile from the game\n"
				+ "\tSelect a tile on the board and click \"Remove Tile\".\n"
				+ "\n"
				+ "Connect two tiles\n"
				+ "\tSelect a first tile, then press \"Tile 1 Chosen\"\n"
				+ "\tSelect a second tile, then press \"Tile 2 Chosen\"\n"
				+ "\tPress \"Connect\"\n"
				+ "\n"
				+ "Remove a connection from the game \n"
				+ "\tSelect a connection on the board and click \"Remove \n\tConnection\".\n"
				+ "\n"
				+ "Create a deck\n"
				+ "\tFor each type of action card, input the number of cards \n\tthat you want in your deck.\n"
				+ "\tThe total number of cards must be 32.\n"
				+ "\n"
				+ "Set the starting position of each player in the game\n"
				+ "\tChoose the player that you want to place.\n"
				+ "\tSelect a the starting tile, and press \"Set Starting Tile\"\n"
				+ "\n"
				+ "Don't forget to save your game so that it will load the next time.\n"
				+ "\n"
				+ "To start a game, select the game that you want to play.\n"
				+ "\tIf your present game isn't available, you need to save it first!\n"
				+ "\tIf you want to start your current game, select the current game\n"
				+ "\tthen press \"Start Game\".\n"
				+ "If you start and clone a game, the index of the cloned game will be\n"
				+ "\tone higher than the current game.\n"
				+ "\tThe current game will be in game mode,\n"
				+ "\tand the cloned game will be the design layout."
			);
	}
}
