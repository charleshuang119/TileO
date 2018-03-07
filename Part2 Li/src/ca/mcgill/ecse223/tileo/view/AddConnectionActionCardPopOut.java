package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;

import ca.mcgill.ecse223.tileo.model.Connection;
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

	private PlayController pmc = TileOPlayPage.pmc;
	
	private final JPanel contentPanel = new JPanel();
	private JLabel lblChooseYoTiles;
	private JLabel errorMessage;
	private String error= "";
	private JButton btnTileChosen;
	private JButton btnChosenTile;
	private JButton okButton;


	/**
	 * Create the dialog.
	 */
	
	
	public void close() { 
		this.setVisible(false);
		chosenTile1 = null;
		chosenTile2 = null;
		error = "";
//	    this.dispose();
	}
	
	public AddConnectionActionCardPopOut() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);
		setTitle("Add Connection Action Card");
		setBounds(500, 200, 500, 300);
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
					   if(chosenTile1==null||chosenTile2==null){
						   error = error + "Please choose the tiles first!";
					   }
					   else{
						   if(!isAdjacent(chosenTile1, chosenTile2)){
							   error = error + "Chosen tiles are not adjacent!";
						   }
						   if (isConnected(chosenTile1, chosenTile2)){
							   error = error + "Chosen tiles are already connected!";
						   }
					   }
					   error.trim();
					   errorMessage.setText(error);
					   
					   if(error.length()==0) {
						try {
							pmc.playConnectTilesActionCard(chosenTile1, chosenTile2);
						} catch (InvalidInputException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						TileOPlayPage.getGrid().selectedTile = null;
						TileOPlayPage.getGrid().aTileIsSelected = false;
						TileOPlayPage.refreshData();
						TileOPlayPage.setError("");
						
						TileOPlayPage.getGrid().aTileIsSelected = false;
						TileOPlayPage.getGrid().aConnectionIsSelected = false;
						TileOPlayPage.getGrid().selectedConnection = null;
						TileOPlayPage.getGrid().selectedTile = null;
						
						close();
					   }
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
	//helper method to check if two tiles are adjacent
		public boolean isAdjacent(Tile tile1, Tile tile2) {
			int x1 = tile1.getX();
			int y1 = tile1.getY();
			int x2 = tile2.getX();
			int y2 = tile2.getY();
			if (x1-x2 == -1 || x1-x2 == 1){
				if(y1-y2 ==0){
					return true;
				}
			}
			if (y1-y2 == -1 || y1-y2 == 1){
				if(x1-x2 ==0){
					return true;
				}
			}
			return false;
		}
		
		//helper method two tiles are already connected
		public boolean isConnected (Tile tile1, Tile tile2){
			for (Connection c1: tile1.getConnections()){
				for (Connection c2: tile2.getConnections()){
					if (c1 == c2){
						return true;
					}
				}
			}
			return false;
		}

}

