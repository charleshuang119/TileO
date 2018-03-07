package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;


import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;

public class DeckPopOut extends JFrame {
	
    private JLabel errorMessage;
	private JPanel contentPane;
	private JTextField teleport;
	private JTextField connectTiles;
	private JTextField removeConnection;
	private JTextField rollDie;
	private JButton btnCreate;
	private JLabel message;
	private String error = null;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					deck frame = new deck();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public void close() { 
		this.setVisible(false);
	    this.dispose();
	}
	/**
	 * Create the frame.
	 */
	public DeckPopOut() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNumberOfTeleport = new JLabel("Number of Teleport Cards:");
		
		JLabel lblNumberOfConnecttiles = new JLabel("Number of ConnectTiles Cards:");
		
		JLabel lblNumberOfRemoveconnection = new JLabel("Number of RemoveConnection Cards:");
		
		JLabel lblNumberOfRolldie = new JLabel("Number of RollDIe Cards:");
		
		teleport = new JTextField();
		teleport.setColumns(10);
		
		connectTiles = new JTextField();
		connectTiles.setColumns(10);
		
		removeConnection = new JTextField();
		removeConnection.setColumns(10);
		
		rollDie = new JTextField();
		rollDie.setColumns(10);
		
		btnCreate = new JButton("Add");
		
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {	
				btnCreateActionPerformed(evt); 
			}
		});
		
		
		message = new JLabel("The total number of cards must add up to 32.");
		
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.red);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(237, Short.MAX_VALUE)
					.addComponent(btnCreate)
					.addGap(159))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(87)
					.addComponent(message)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(46)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNumberOfRolldie)
								.addComponent(lblNumberOfConnecttiles)
								.addComponent(lblNumberOfTeleport)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNumberOfRemoveconnection, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(teleport, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
						.addComponent(connectTiles, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
						.addComponent(removeConnection, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
						.addComponent(rollDie, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
					.addGap(82))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(errorMessage)
					.addContainerGap(335, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(message)
					.addGap(1)
					.addComponent(errorMessage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfTeleport)
						.addComponent(teleport, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfConnecttiles)
						.addComponent(connectTiles, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(removeConnection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNumberOfRemoveconnection, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumberOfRolldie)
						.addComponent(rollDie, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(btnCreate)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void btnCreateActionPerformed(java.awt.event.ActionEvent evt)  {
		int numberOfTeleportActionCard=0;
		int numberOfConnectTilesActionCard=0;
		int numberOfRemoveConnectionActionCard=0;
		int numberOfRollDieActionCard=0;
	    error = "";
		try{
			numberOfTeleportActionCard = Integer.parseInt(teleport.getText());
		}
		catch(NumberFormatException e) {
			error = error + "The number of teleport action cards needs to be a numerical value! ";
		}
		
		try{
			numberOfConnectTilesActionCard = Integer.parseInt(connectTiles.getText());
		}
		catch(NumberFormatException e) {
			error = error + "The number of connectTile action cards needs to be a numerical value! ";
		}
		try{
			numberOfRemoveConnectionActionCard = Integer.parseInt(removeConnection.getText());
		}
		catch(NumberFormatException e) {
			error = error + "The number of romoveConnection action cards needs to be a numerical value! ";
		}
		try{
			numberOfRollDieActionCard = Integer.parseInt(rollDie.getText());
		}
		catch(NumberFormatException e) {
			error = error + "The number of rollDie action cards needs to be a numerical value! ";
		}
		error.trim();
		if (error.length() == 0) {
			
			DesignModeController dmc = new DesignModeController();
			try {
				dmc.createDeck(numberOfRollDieActionCard, numberOfConnectTilesActionCard, numberOfRemoveConnectionActionCard, numberOfTeleportActionCard);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		refreshData();
	}
	private void refreshData() {
		errorMessage.setText("<html>"+error+"<html>");
		DesignModeController dmc = new DesignModeController();
		if(error == null || error.length()==0){
			close();
		}
	}
}