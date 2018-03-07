package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.NormalTile;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.TileO;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SetPlayerStartingTilePopOut extends JFrame {
	
	Tile chosenTile=null;

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel errorMessage;
	
	
	String error;
	
	String playerNumber;
	int chosenPlayerNumber;
	
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SetPlayerStartingTIlePopOut frame = new SetPlayerStartingTIlePopOut();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public void close() { 
		this.setVisible(false);
	    this.dispose();
	}
	
	public SetPlayerStartingTilePopOut() {
		setAlwaysOnTop(true);
		setTitle("Choose Starting Tile");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSelectTheNumber = new JLabel("Select the player's number and");
		
		JRadioButton radioButton = new JRadioButton("2");
		radioButton.setActionCommand("2");
		buttonGroup.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("3");
		radioButton_1.setActionCommand("3");
		buttonGroup.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("4");
		radioButton_2.setActionCommand("4");
		buttonGroup.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("1");
		radioButton_3.setActionCommand("1");
		buttonGroup.add(radioButton_3);
		
		JLabel lblChooseItsStarting = new JLabel("choose its starting tile from the board.");
		
		JButton btnSet = new JButton("Set");
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonModel chosenNumber;
				error = "";
				chosenNumber = buttonGroup.getSelection();

			    if(chosenNumber == null) {
			    	error = "Please select a player! ";
			    }
				if (TileODesignPage.getGrid().aTileIsSelected){
						chosenTile = TileODesignPage.getGrid().selectedTile;
				}
				if(chosenTile==null){
					error = error + "Please click a tile on the board! ";
				}
				error.trim();
				if(error.length()==0) {
					DesignModeController dmc = new DesignModeController();
					chosenPlayerNumber = Integer.parseInt(chosenNumber.getActionCommand());
					try {
						dmc.setPlayerStartingTile(chosenPlayerNumber, chosenTile);
						System.out.println("Starting Tile for player" + chosenPlayerNumber + "successfully set");
						System.out.println(TileOApplication.getTileO().getCurrentGame().getPlayer(chosenPlayerNumber-1).getCurrentTile().getX());
					} catch (InvalidInputException e1) {
						error = e1.getMessage();
					}	
				}
				refreshData();
				
				
			}
		});
		
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(188)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(radioButton_2)
						.addComponent(radioButton_1)
						.addComponent(radioButton)
						.addComponent(radioButton_3)
						.addComponent(btnSet))
					.addContainerGap(75, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(53, Short.MAX_VALUE)
					.addComponent(lblChooseItsStarting)
					.addGap(49))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(94, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(errorMessage)
						.addComponent(lblSelectTheNumber))
					.addGap(88))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(errorMessage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSelectTheNumber)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblChooseItsStarting)
					.addGap(22)
					.addComponent(radioButton_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(radioButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(radioButton_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(radioButton_2)
					.addGap(5)
					.addComponent(btnSet)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	private void refreshData() {
		errorMessage.setText(error);
		if(error == null || error.length()==0){
			close();
		}
	}
}
