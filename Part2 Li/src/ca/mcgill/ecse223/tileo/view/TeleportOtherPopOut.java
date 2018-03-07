package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;
import ca.mcgill.ecse223.tileo.model.Tile;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TeleportOtherPopOut extends JFrame {
	
	JLabel errorLbl;
	
	public void close() { 
		this.setVisible(false);
		chosenTile = null;
		error = "";
		errorLbl.setText(error);
		
	}

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public static JComboBox comboBox = new JComboBox();
	
	public static void refreshComboBox(int numberOfPlayers){
		String[] playerNum = new String[numberOfPlayers];
		addNums(playerNum, numberOfPlayers);
		comboBox.setModel(new DefaultComboBoxModel(playerNum));
	}
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TeleportOtherPopOut frame = new TeleportOtherPopOut();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
private PlayController pmc = TileOPlayPage.pmc;
private Tile chosenTile = null;
String error = "";
	public static void addNums(String[] str, int number){
		for (int i = 1; i < number+1 ; i++){
			str[i-1] = String.valueOf(i);			
		}
	}
	/**
	 * Create the frame.
	 */
	public TeleportOtherPopOut() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("You have drawn a TeleportOther Action Card!");
		errorLbl = new JLabel("");
		errorLbl.setForeground(Color.RED);
		JLabel lblPleaseSelectA = new JLabel("Please select a player you would like to move and the tile");
		
//		String[] playerNum = new String[TileOApplication.getTileO().getCurrentGame().numberOfPlayers()];
//		addNums(playerNum, TileOApplication.getTileO().getCurrentGame().numberOfPlayers());
//		comboBox.setModel(new DefaultComboBoxModel(playerNum));	
		
		JButton btnMove = new JButton("Move");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (TileOPlayPage.getGrid().aTileIsSelected){
					chosenTile = TileOPlayPage.getGrid().selectedTile;
				//	if(chosenTile==null){ //possibly redundant error checking
				//		error = error + "Please click a tile on the board! ";
					//	errorLbl.setText(error);
				//	}
				}
				
				error = "";
				int chosenPlayerNumber = comboBox.getSelectedIndex();
				//Tile chosenTile = null;
				if (chosenPlayerNumber == TileOApplication.getTileO().getCurrentGame().getCurrentPlayer().getNumber() - 1)
				{
					error = "You cannot teleport yourself!";
				}
				if (TileODesignPage.getGrid().aTileIsSelected){
					chosenTile = TileODesignPage.getGrid().selectedTile;
				}
				if(chosenTile == null){
					error = "Please choose a tile on the board!";
				}
				error.trim();
				if(error.length()==0) {
					pmc.playTeleportOtherActionCard(chosenPlayerNumber, chosenTile);
					TileOPlayPage.setError("");
					TileOPlayPage.refreshData();
					TileOPlayPage.getGrid().aTileIsSelected = false;
					TileOPlayPage.getGrid().aConnectionIsSelected = false;
					TileOPlayPage.getGrid().selectedConnection = null;
					TileOPlayPage.getGrid().selectedTile = null;
					close();
				}
				else
				{
					errorLbl.setText(error);
				}
				//refreshData();		
				
			}
		});
		
		JLabel lblWarningIfYou = new JLabel("Warning: if you teleport a player to the WinTile, the game keeps going!");
		
		JLabel lblEvenIfThe = new JLabel("This will also set the WinTile as visited... The mind games are real :)");
		
	
		
		//JComboBox comboBox = new JComboBox();
		
							
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(84)
					.addComponent(lblNewLabel)
					.addContainerGap(73, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnMove)
					.addGap(62))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(73, Short.MAX_VALUE)
					.addComponent(errorLbl, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
					.addGap(62))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(lblWarningIfYou)
					.addContainerGap(41, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(lblEvenIfThe)
					.addContainerGap(46, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(46, Short.MAX_VALUE)
					.addComponent(lblPleaseSelectA)
					.addGap(38))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(lblPleaseSelectA)
					.addGap(18)
					.addComponent(errorLbl, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMove))
					.addGap(18)
					.addComponent(lblWarningIfYou, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(lblEvenIfThe)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
}
