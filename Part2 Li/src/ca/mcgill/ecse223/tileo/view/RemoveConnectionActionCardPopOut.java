package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;

import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Tile;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class RemoveConnectionActionCardPopOut extends JFrame {
	
	private PlayController pmc = TileOPlayPage.pmc;
	private JPanel contentPane;
	private Connection chosenConnection=null;
	private String error = "";
	JLabel errorLbl = new JLabel("");
	
	public void close() { 
		this.setVisible(false);
		chosenConnection = null;
		error = "";
//	    this.dispose();
	}
	
	/**
	 * Create the frame.
	 */
	public RemoveConnectionActionCardPopOut() {
		setResizable(false);
		setAlwaysOnTop(true);
		setTitle("Remove Connection Action Card");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(500, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblYouHaveDrawn = new JLabel("You have drawn a Remove Connection Action Card,");
		lblYouHaveDrawn.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		JLabel lblSelectAConnection = new JLabel("select a connection piece on the board and click on the ");
		lblSelectAConnection.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		JButton btnRemoveConnection = new JButton("Remove Connection");
		btnRemoveConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(TileOPlayPage.getGrid().aConnectionIsSelected){
					chosenConnection = TileOPlayPage.getGrid().selectedConnection;
					error = "";
				}
					if (chosenConnection == null)
					{
						error = "Please select a connection.";
					}
					if(error.length()==0) {
					try 
					{						
						pmc.playRemoveConnectionActionCard(chosenConnection);
						TileOPlayPage.refreshData();
						TileOPlayPage.setError("");
						TileOPlayPage.getGrid().aTileIsSelected = false;
						TileOPlayPage.getGrid().aConnectionIsSelected = false;
						TileOPlayPage.getGrid().selectedConnection = null;
						TileOPlayPage.getGrid().selectedTile = null;
						close();
						errorLbl.setText("");
					}
					catch (InvalidInputException e2){
						throw new RuntimeException (e2.getMessage());
					}
					}
					else
					{
						errorLbl.setText(error);
					}
				
				
			}
			
		});
		
		JLabel lblButtonToRemove = new JLabel("\"Remove Connection\" button to remove the selected connection piece.");
		lblButtonToRemove.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		
		errorLbl.setForeground(Color.RED);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addComponent(lblSelectAConnection)
					.addContainerGap(44, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addComponent(lblButtonToRemove)
					.addContainerGap(21, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(61, Short.MAX_VALUE)
					.addComponent(lblYouHaveDrawn)
					.addGap(58))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(138, Short.MAX_VALUE)
					.addComponent(btnRemoveConnection)
					.addGap(133))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(54)
					.addComponent(errorLbl, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(78, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblYouHaveDrawn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSelectAConnection)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblButtonToRemove)
					.addGap(45)
					.addComponent(errorLbl)
					.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
					.addComponent(btnRemoveConnection)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
