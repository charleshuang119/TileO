package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Tile;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class AddConnectionDesignPopOut extends JDialog {
	
	private Tile chosenTile1=null;
	private Tile chosenTile2=null;
	
	private final JPanel contentPanel = new JPanel();
	private JLabel lblChooseYoTiles;
	private JLabel errorMessage;
	private String error= "";

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			AddConnectionDesignPopOut dialog = new AddConnectionDesignPopOut();
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
	
	public AddConnectionDesignPopOut() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setTitle("Connect Two Tiles");
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblChooseYoTiles = new JLabel("Choose your tiles");
		}
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.red);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(176)
							.addComponent(lblChooseYoTiles))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(30)
							.addComponent(errorMessage)))
					.addContainerGap(160, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblChooseYoTiles)
					.addGap(55)
					.addComponent(errorMessage)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnTileChosen = new JButton("Tile 1 Chosen");
				btnTileChosen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						error="";
						if (TileODesignPage.getGrid().aTileIsSelected){
							chosenTile1 = TileODesignPage.getGrid().selectedTile;
						
						}
						if(chosenTile1==null){
							error = "Please select a tile on the board and then press'Tile 1 Chosen' button! ";
						}
						error.trim();
						errorMessage.setText(error);
					}
				});
				buttonPane.add(btnTileChosen);
			}
			{
				JButton btnChosenTile = new JButton("Tile 2 Chosen");
				btnChosenTile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						error="";
						if (TileODesignPage.getGrid().aTileIsSelected){
							chosenTile2 = TileODesignPage.getGrid().selectedTile;
						}
						if(chosenTile2==null){
							error = "Please select a tile on the board and then press'Tile 2 Chosen' button! ";
						}
						error.trim();
						errorMessage.setText(error);
					}
				});
				buttonPane.add(btnChosenTile);
			}
			{
				JButton okButton = new JButton("Connect!");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						error = "";
						DesignModeController dmc = new DesignModeController();
						   if(chosenTile1==null||chosenTile2==null){
							   error = "Please choose the tiles first! ";
						   }
						   error.trim();
						   if(error.length()==0) {
							   try {
									dmc.connectTwoTiles(chosenTile1, chosenTile2);
									close();
								} catch (InvalidInputException e1) {
									error = e1.getMessage();
								}
						   }
						   refreshData();
							
						
					}
				});
				okButton.setActionCommand("Connect");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void refreshData() {
		errorMessage.setText("<html>"+error+"<html>");
		if(error == null || error.length()==0){
			close();
		}
	}

}
