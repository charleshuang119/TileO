package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;
import ca.mcgill.ecse223.tileo.model.Tile;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class WinTileHintActionCardPopOut extends JFrame {

	private JPanel contentPane;
	private PlayController pmc = TileOPlayPage.pmc;
	
	private JLabel drawnCardLabel;
	private JLabel selectTileLabel;
	private JLabel errorLabel;
	private JLabel hintLabel;
	
	private JButton getHintButton;
	private JButton willRemButton;
	
	private int chosen = 0;
	private Tile chosenTile = null;
	private String error = "";
	private String hint = "";
	private boolean isWin;
	
	public void close() { 
		this.setVisible(false);
		chosen = 0;
		error = "";
		hint = "";
		errorLabel.setText(error);
		hintLabel.setText(hint);
		chosenTile = null;
	}
	
	
	/**
	 * Create the frame.
	 */
	public WinTileHintActionCardPopOut() {
		setAlwaysOnTop(true);
		setTitle("Win Tile Hint Action Card");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(500, 200, 550, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		drawnCardLabel = new JLabel ("You have drawn the Win Tile Hint Action Card!");
		drawnCardLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		selectTileLabel = new JLabel("Select a tile on the board to check if it, or one of its neighbours is the Win Tile!");
		selectTileLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		getHintButton = new JButton("Get Hint!");
		getHintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				chosenTile = null;
				if (TileOPlayPage.getGrid().aTileIsSelected){
					chosenTile = TileOPlayPage.getGrid().selectedTile;
				}
				if (chosenTile == null){
					error = "Please click a tile on the board! ";
				}
				if (chosen == 0){
					if (chosenTile == null){
						error = "Please click a tile on the board! ";
					}
				
					if(error.length() == 0){
						errorLabel.setText("");
						
						pmc.playWinTileHintActionCard(chosenTile);
						isWin = pmc.isTheWinTile;
						if (isWin == true){
							hint = "Win Tile around here!";
						}
						else if (isWin == false) {
							hint = "No Win Tile Here!";
						}
						hintLabel.setText(hint);
						chosen = 1;
					}
					else{
						errorLabel.setText(error);
					}
				}
				else if (chosen == 1){
					error = "You have already selected a tile!";
					errorLabel.setText(error);
				}
			}
		});
		
		willRemButton = new JButton("I will remember!");
		willRemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chosen == 1) {
					TileOPlayPage.setError("");
					TileOPlayPage.refreshData();
					TileOPlayPage.getGrid().aTileIsSelected = false;
					TileOPlayPage.getGrid().aConnectionIsSelected = false;
					TileOPlayPage.getGrid().selectedConnection = null;
					TileOPlayPage.getGrid().selectedTile = null;
					close();
				}
				else {
					error = "You have not select a tile yet!";
					errorLabel.setText(error);
				}
			}
		});
		
		hintLabel = new JLabel("");
		hintLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		
		JLabel lbltwoTilesAre = new JLabel("*Two tiles are neighbours if they are next to each other vertically or horizontally, ");
		
		JLabel lblTheyAreNot = new JLabel("but they are not necessarily connected!");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(74)
							.addComponent(getHintButton)
							.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
							.addComponent(willRemButton)
							.addGap(94))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(84)
							.addComponent(hintLabel, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(110)
							.addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(51, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(selectTileLabel)
					.addContainerGap(24, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(82)
					.addComponent(drawnCardLabel)
					.addContainerGap(120, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbltwoTilesAre)
					.addContainerGap(21, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTheyAreNot, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(drawnCardLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(selectTileLabel)
					.addGap(26)
					.addComponent(errorLabel)
					.addGap(18)
					.addComponent(hintLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(getHintButton)
						.addComponent(willRemButton))
					.addGap(26)
					.addComponent(lbltwoTilesAre)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTheyAreNot)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
