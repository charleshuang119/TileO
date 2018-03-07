package ca.mcgill.ecse223.tileo.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.DesignModeController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

public class AddTilePopOut extends JFrame {

	private JPanel contentPane;
	private JTextField xComponent;
	private JTextField yComponent;
	private JLabel errorMessage;
	private String error = null;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTilePopOut frame = new AddTilePopOut();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	
	public void close() { 
		this.setVisible(false);
	    this.dispose();
	}
	
	public AddTilePopOut() {
		setTitle("Add a new tile ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 468, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Add Tile");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addTileActionPerformed(evt);
			}
		});
		
		JRadioButton rdbtnNormalTile = new JRadioButton("Normal Tile");
		rdbtnNormalTile.setActionCommand("NormalTile");
		buttonGroup.add(rdbtnNormalTile);
		
		JRadioButton rdbtnActionTile = new JRadioButton("Action Tile");
		rdbtnActionTile.setActionCommand("ActionTile");
		rdbtnActionTile.setHorizontalAlignment(SwingConstants.CENTER);
		buttonGroup.add(rdbtnActionTile);
		
		JRadioButton rdbtnWinTile = new JRadioButton("Win Tile");
		rdbtnWinTile.setActionCommand("WinTile");
		buttonGroup.add(rdbtnWinTile);
		
		JLabel xLabel = new JLabel("x component:");
		
		xComponent = new JTextField();
		xComponent.setColumns(10);
		
		JLabel yLabel = new JLabel("y component:");
		
		yComponent = new JTextField();
		yComponent.setColumns(10);
		
		JLabel lblAddANew = new JLabel("Add a new tile to the board game!");
		
		errorMessage= new JLabel("");
		errorMessage.setForeground(Color.RED);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(126)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(136, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(xComponent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(yComponent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(240, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(66, Short.MAX_VALUE)
					.addComponent(lblAddANew)
					.addGap(110))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(errorMessage)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(xLabel)
							.addComponent(rdbtnNormalTile)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(44)
							.addComponent(yLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(39)
							.addComponent(rdbtnActionTile)))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addComponent(rdbtnWinTile)
					.addGap(21))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(lblAddANew)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(errorMessage)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(rdbtnWinTile)
							.addComponent(rdbtnNormalTile))
						.addComponent(rdbtnActionTile))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(xLabel)
						.addComponent(yLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(xComponent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(yComponent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void addTileActionPerformed(java.awt.event.ActionEvent evt){
		
		
		ButtonModel chosenTile;
		int chosenXComp = 0;
		int chosenYComp=0;

		
		error = "";
		chosenTile = buttonGroup.getSelection();


		if(chosenTile==null) {
			error = "Please select a Tile Type. ";
		}
		
		try {
			chosenXComp = Integer.parseInt(xComponent.getText());
		}
		catch(NumberFormatException e){
			error = error+ "The coordinate x needs to be a numerical value! ";
		}
		try {
			chosenYComp = Integer.parseInt(yComponent.getText());
		}
		catch(NumberFormatException e){
			error = error + "The coordinate y needs to be a numerical value! ";
		}
		error.trim();
		if (error.length()==0) {
			try{
			DesignModeController dmc = new DesignModeController();
			dmc.addDesignTile(chosenXComp, chosenYComp,chosenTile.getActionCommand());
			} catch (InvalidInputException er) {
			error = er.getMessage();
			}
			
		}
		refreshData();		
	}
	private void refreshData() {
		errorMessage.setText("<html>"+error+"<html>");
		if(error == null || error.length()==0){
			close();
		}
	}
}
