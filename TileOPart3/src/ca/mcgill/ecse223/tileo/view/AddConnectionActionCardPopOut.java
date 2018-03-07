package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayModeController;
import ca.mcgill.ecse223.tileo.model.Tile;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AddConnectionActionCardPopOut extends JDialog {
	
	private Tile chosenTile1=null;
	private Tile chosenTile2=null;
	
	private final JPanel contentPanel = new JPanel();
	private JLabel lblChooseYoTiles;
	private JLabel errorMessage;
	private String error= "";
	private JButton btnTileChosen;
	private JButton btnChosenTile;
	private JButton okButton;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AddConnectionPlayPopOut dialog = new AddConnectionPlayPopOut();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	
	
	public void close() { 
		this.setVisible(false);
	    this.dispose();
	}
	
	public AddConnectionActionCardPopOut() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setTitle("Add Connection Action Card");
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblChooseYoTiles = new JLabel("You have drawn an Add Connection Action card, ");
		}
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.red);
		JLabel lblNewLabel = new JLabel("please select two tiles on the board and click on the");
		JLabel lblToAddA = new JLabel("\"Connect\" button to add a connection between them.");
		{
			btnTileChosen = new JButton("Tile 1 Chosen");
			btnTileChosen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					error="";
					if (TileOPlayPage.getGrid().aTileIsSelected){
						chosenTile1 = TileOPlayPage.getGrid().selectedTile;
					
					}
					if(chosenTile1==null){
						error = "Please select a tile on the board and then press 'Tile 1 Chosen' button! ";
					}
					error.trim();
					errorMessage.setText(error);
				}
			});
		}
		{
			btnChosenTile = new JButton("Tile 2 Chosen");
			btnChosenTile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					error="";
					if (TileOPlayPage.getGrid().aTileIsSelected){
						chosenTile2 = TileOPlayPage.getGrid().selectedTile;
					}
					if(chosenTile2==null){
						error = "Please select a tile on the board and then press 'Tile 2 Chosen' button! ";
					}
					error.trim();
					errorMessage.setText(error);
				}
			});
		}
		{
			okButton = new JButton("Connect");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					error = "";
					PlayModeController pmc = new PlayModeController();
					   if(chosenTile1==null||chosenTile2==null){
						   error = "Please choose the tiles first! ";
					   }
					   error.trim();
					   if(error.length()==0) {
						   try {
								pmc.playConnectTilesActionCard(chosenTile1, chosenTile2);
								TileOPlayPage.refreshData();
								close();
							} catch (InvalidInputException e1) {
								error = e1.getMessage();
							}
					   }
					   refreshData();
				}
			});
			okButton.setActionCommand("Connect");
			getRootPane().setDefaultButton(okButton);
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(73)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblToAddA)
								.addComponent(lblNewLabel)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(89)
							.addComponent(lblChooseYoTiles))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(9)
									.addComponent(btnTileChosen)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnChosenTile)
									.addGap(18)
									.addComponent(okButton))
								.addComponent(errorMessage))))
					.addContainerGap(65, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblChooseYoTiles)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblToAddA)
					.addGap(16)
					.addComponent(errorMessage)
					.addPreferredGap(ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTileChosen)
						.addComponent(okButton)
						.addComponent(btnChosenTile)))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	private void refreshData() {
		errorMessage.setText("<html>"+error+"<html>");
		if(error == null || error.length()==0){
			close();
		}
	}

}

