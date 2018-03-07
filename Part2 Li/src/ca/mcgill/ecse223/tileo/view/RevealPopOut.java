package ca.mcgill.ecse223.tileo.view;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;
import ca.mcgill.ecse223.tileo.model.Tile;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class RevealPopOut extends JFrame{
	
	private JPanel contentPane;
	private PlayController pmc = TileOPlayPage.pmc;
	
	private JLabel lblYouHaveDrawn;
	private JLabel lblOnTheBoard;
	private JLabel chosenTileLbl;
	private JLabel errorLbl;
	private JButton revealBtn;
	private JButton rememberBtn;
	
	private int chosen = 0;
	private String error;
	private Tile chosenTile = null;
	private String tileType = "";
	

	/**
	 * Create the frame.
	 */
	public void close() { 
		this.setVisible(false);
		chosen = 0;
		error = "";
		chosenTile = null;
		tileType = "";
		errorLbl.setText(error);
		chosenTileLbl.setText(tileType);
	}
	
	public RevealPopOut() {
		setTitle("Reveal Action Card");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(500, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblYouHaveDrawn = new JLabel("You have drawn a Reveal Action Card, please select a tile ");
		lblYouHaveDrawn.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblOnTheBoard = new JLabel("on the board and click on the \"Reveal\" button ");
		lblOnTheBoard.setHorizontalAlignment(SwingConstants.CENTER);
		
		revealBtn = new JButton("Reveal");
		revealBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				if (chosen == 0){
					if(chosenTile == null){
						error = "Please click a tile on the board! ";
						errorLbl.setText(error);
					}
					if (TileOPlayPage.getGrid().aTileIsSelected){
						chosenTile = TileOPlayPage.getGrid().selectedTile;						
						try {
							pmc.playRevealActionCard(chosenTile);
							tileType = pmc.getRevealedTile();
							errorLbl.setText("");
							chosenTileLbl.setText("The chosen tile is of type: " + tileType);
							chosen = 1;
						}
						catch (InvalidInputException e1) {
							throw new RuntimeException(e1.getMessage());
						}
					}
				}
				else if (chosen == 1) {
					error = "You have already revealed a tile!";
					errorLbl.setText(error);
				}
			}
		});
		
		rememberBtn = new JButton("I'll remember!");
		rememberBtn.addActionListener(new ActionListener() {
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
				else if (chosen == 0){
					error = "You didn't reveal a tile yet!";
					errorLbl.setText(error);
				}
			}
		});
		
		chosenTileLbl = new JLabel("");
		chosenTileLbl.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		errorLbl = new JLabel("");
		errorLbl.setForeground(Color.RED);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addComponent(lblYouHaveDrawn))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(74)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(revealBtn)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(rememberBtn))
								.addComponent(lblOnTheBoard)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(84)
							.addComponent(chosenTileLbl, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)))
					.addGap(44))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(110)
					.addComponent(errorLbl, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(121, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(lblYouHaveDrawn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblOnTheBoard)
					.addGap(26)
					.addComponent(errorLbl)
					.addGap(18)
					.addComponent(chosenTileLbl)
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(revealBtn)
						.addComponent(rememberBtn))
					.addGap(64))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
