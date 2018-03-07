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

import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.TileO;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Color;

public class TeleportPopOut extends JFrame {
	private Tile chosenTile = null;
	private String error;
	private PlayController pmc = TileOPlayPage.pmc;
	private JLabel errorLbl = new JLabel("");

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public void close() { 
		this.setVisible(false);
		chosenTile = null;
		error = "";
	}
	
	public TeleportPopOut() {
		setResizable(false);
		setTitle("Teleport Action Card");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblYouHaveDrawn = new JLabel("You have drawn a Teleport Action Card, please select a tile ");
		lblYouHaveDrawn.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblOnTheBoard = new JLabel("on the board and click on the \"Teleport\" button ");
		lblOnTheBoard.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnTeleport = new JButton("Teleport");
		btnTeleport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				if (TileOPlayPage.getGrid().aTileIsSelected){
					chosenTile = TileOPlayPage.getGrid().selectedTile;
					if(chosenTile==null){ //possibly redundant error checking
						error = error + "Please click a tile on the board! ";
						errorLbl.setText(error);
					}
					try {
						pmc.playTeleportActionCard(chosenTile);
						TileOPlayPage.setError("");
						TileOPlayPage.refreshData();
						TileOPlayPage.getGrid().aTileIsSelected = false;
						TileOPlayPage.getGrid().aConnectionIsSelected = false;
						TileOPlayPage.getGrid().selectedConnection = null;
						TileOPlayPage.getGrid().selectedTile = null;
						close();
					} 
					catch (InvalidInputException e1) {
						throw new RuntimeException(e1.getMessage());
					}
			    }
				else{
					error = error + "Please click a tile on the board! ";
					errorLbl.setText(error);
				}
				
			}
		});
		
		JLabel lblToTeleportTo = new JLabel("to teleport to the selected tile. ");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addComponent(lblYouHaveDrawn)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(212, Short.MAX_VALUE)
					.addComponent(btnTeleport)
					.addGap(170))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(65)
					.addComponent(lblOnTheBoard)
					.addContainerGap(69, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(115)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(errorLbl)
						.addComponent(lblToTeleportTo))
					.addContainerGap(127, Short.MAX_VALUE))
		);
		errorLbl.setForeground(Color.RED);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblYouHaveDrawn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblOnTheBoard)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblToTeleportTo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(errorLbl)
					.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
					.addComponent(btnTeleport)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
