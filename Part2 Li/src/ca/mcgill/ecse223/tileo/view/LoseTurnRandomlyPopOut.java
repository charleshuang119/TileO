package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.PlayController;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class LoseTurnRandomlyPopOut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	
	private PlayController pmc = TileOPlayPage.pmc;
	
	private static int player1LostTurns;
	private static int player2LostTurns;
	private static int player3LostTurns;
	private static int player4LostTurns;

	
	public void close() { 
		setVisible(false);
		TileOPlayPage.refreshData();
	}
	
	/**
	 * Create the dialog.
	 */
	public LoseTurnRandomlyPopOut() {
		
		generateLostTurns();
		//System.out.println("A new window has been created");
		setTitle("Lose Turn Randomly Action Card");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{444, 0};
		gridBagLayout.rowHeights = new int[]{86, 34, 32, 32, 34, 39, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton doneButton = new JButton("You Lost Me With All These Turns Lost");
				doneButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						pmc.playLoseTurnRandomlyActionCard(player1LostTurns, player2LostTurns, player3LostTurns, player4LostTurns);
						close();
					}
				});
				doneButton.setActionCommand("OK");
				buttonPane.add(doneButton);
				getRootPane().setDefaultButton(doneButton);
			}
		}
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel youHaveDrawnLbl = new JLabel("You have drawn a Lose Turn Randomly Action Card!");
			GridBagConstraints gbc_youHaveDrawnLbl = new GridBagConstraints();
			gbc_youHaveDrawnLbl.insets = new Insets(0, 0, 5, 5);
			gbc_youHaveDrawnLbl.gridx = 1;
			gbc_youHaveDrawnLbl.gridy = 0;
			contentPanel.add(youHaveDrawnLbl, gbc_youHaveDrawnLbl);
		}
		{
			JLabel allPlayersWillLbl = new JLabel("All players will now lose a random number of turns");
			GridBagConstraints gbc_allPlayersWillLbl = new GridBagConstraints();
			gbc_allPlayersWillLbl.insets = new Insets(0, 0, 5, 5);
			gbc_allPlayersWillLbl.gridx = 1;
			gbc_allPlayersWillLbl.gridy = 1;
			contentPanel.add(allPlayersWillLbl, gbc_allPlayersWillLbl);
		}
		{
			JLabel between0And2Lbl = new JLabel("between 0 and 2 turns.");
			GridBagConstraints gbc_between0And2Lbl = new GridBagConstraints();
			gbc_between0And2Lbl.insets = new Insets(0, 0, 0, 5);
			gbc_between0And2Lbl.gridx = 1;
			gbc_between0And2Lbl.gridy = 2;
			contentPanel.add(between0And2Lbl, gbc_between0And2Lbl);
		}
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_contentPanel.insets = new Insets(0, 0, 5, 0);
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(contentPanel, gbc_contentPanel);
		{
			JLabel player1Lbl = new JLabel("Player 1 loses " + player1LostTurns + " turns.");
			GridBagConstraints gbc_player1Lbl = new GridBagConstraints();
			gbc_player1Lbl.insets = new Insets(0, 0, 5, 0);
			gbc_player1Lbl.gridx = 0;
			gbc_player1Lbl.gridy = 1;
			getContentPane().add(player1Lbl, gbc_player1Lbl);
		}
		{
			JLabel player2Lbl = new JLabel("Player 2 loses " + player2LostTurns + " turns.");
			GridBagConstraints gbc_player2Lbl = new GridBagConstraints();
			gbc_player2Lbl.insets = new Insets(0, 0, 5, 0);
			gbc_player2Lbl.gridx = 0;
			gbc_player2Lbl.gridy = 2;
			getContentPane().add(player2Lbl, gbc_player2Lbl);
		}
		if(TileOApplication.getTileO().getCurrentGame().numberOfPlayers() >= 3){
			JLabel player3Lbl = new JLabel("Player 3 loses " + player3LostTurns + " turns.");
			GridBagConstraints gbc_player3Lbl = new GridBagConstraints();
			gbc_player3Lbl.insets = new Insets(0, 0, 5, 0);
			gbc_player3Lbl.gridx = 0;
			gbc_player3Lbl.gridy = 3;
			getContentPane().add(player3Lbl, gbc_player3Lbl);
		}
		if(TileOApplication.getTileO().getCurrentGame().numberOfPlayers() == 4){
			JLabel player4Lbl = new JLabel("Player 4 loses " + player4LostTurns + " turns.");
			GridBagConstraints gbc_player4Lbl = new GridBagConstraints();
			gbc_player4Lbl.insets = new Insets(0, 0, 5, 0);
			gbc_player4Lbl.gridx = 0;
			gbc_player4Lbl.gridy = 4;
			getContentPane().add(player4Lbl, gbc_player4Lbl);
		}
		GridBagConstraints gbc_buttonPane = new GridBagConstraints();
		gbc_buttonPane.anchor = GridBagConstraints.NORTH;
		gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPane.gridx = 0;
		gbc_buttonPane.gridy = 5;
		getContentPane().add(buttonPane, gbc_buttonPane);
	}
	
	private static void generateLostTurns(){
		Random randomGenerator = new Random();
		player1LostTurns = randomGenerator.nextInt(3);
		player2LostTurns = randomGenerator.nextInt(3);
		player3LostTurns = randomGenerator.nextInt(3);
		player4LostTurns = randomGenerator.nextInt(3);
	}

}
