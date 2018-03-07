package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;

import ca.mcgill.ecse223.tileo.model.Tile;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class SelectTilePlayPopOut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private PlayController pmc = TileOPlayPage.pmc;
	
	private Tile chosenTile=null;
	private String error = "";
    private JLabel errorMessage;
    private JLabel lblPleaseClickOn;
	

	/**
	 * Create the dialog.
	 */
	
	public void close() { 
		this.setVisible(false);
		chosenTile = null;
		TileOPlayPage.getGrid().aTileIsSelected = false;
		error = "";
		errorMessage.setText("");
	    this.dispose();
	}
	
	public SelectTilePlayPopOut() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Move To A Tile");
		setResizable(false);
		setAlwaysOnTop(true);
		setBounds(500, 200, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);
		{
			lblPleaseClickOn = new JLabel("Please click on a highlighted tile on the board in order to move to it.");
		}
		
		errorMessage = new JLabel("");
		errorMessage.setForeground(Color.RED);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPleaseClickOn)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(56)
							.addComponent(errorMessage)))
					.addContainerGap(10, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblPleaseClickOn)
					.addGap(65)
					.addComponent(errorMessage)
					.addContainerGap(143, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Move To This Tile");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						error = "";
						if (TileOPlayPage.getGrid().aTileIsSelected){
							chosenTile = TileOPlayPage.getGrid().selectedTile;
						}
						if(chosenTile == null){
							error = "Please choose on tile on the board! ";
						}
						if(!TileOPlayPage.pMoves.contains(chosenTile)){
							error = "Please choose one of the highlighted tiles! ";
						}
						error.trim();
						
						if (error.length()==0){					
							try {
								pmc.land(chosenTile);
								//For testing purposes
								//System.out.println("Successfully(?) landed on tile: x: " + chosenTile.getX() + " y: " + chosenTile.getY());
								TileOPlayPage.getGrid().isAPlayerTurn = false;	//added by Li	
								TileOPlayPage.refreshData();
								TileOPlayPage.setError("");
								
								TileOPlayPage.getGrid().aTileIsSelected = false;
								TileOPlayPage.getGrid().aConnectionIsSelected = false;
								TileOPlayPage.getGrid().selectedConnection = null;
								TileOPlayPage.getGrid().selectedTile = null;
								
								TileOPlayPage.rollDieOpen = false;
								
								close();
							} catch (InvalidInputException e) {
								throw new RuntimeException(e.getMessage());
							}
							
						}
						else
							errorMessage.setText(error);
					}
				});
				
				okButton.setActionCommand("MoveToThisTile");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
