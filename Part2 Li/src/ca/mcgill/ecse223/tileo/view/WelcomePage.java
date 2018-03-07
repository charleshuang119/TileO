package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;

import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.model.NormalTile;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class WelcomePage extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel errorMessage;
	int chosenNumberOfPlayers;
	
	String error;
	String numberOfPlayers;
	private JLabel createNewGameLabel;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton_2;
	private JButton btnCreateGame;
	private JLabel loadGameLabel;
	private JComboBox loadGameComboBox;
	private JButton loadButton;
	private JButton closeButton;
	
	/**
	 * Create the frame.
	 */
	public WelcomePage() {
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(250, 200, 605, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		String[] games = new String[TileOApplication.getTileO().numberOfGames()];
		addNums(games, TileOApplication.getTileO().numberOfGames());
		
		
		createNewGameLabel = new JLabel("Create a new game by selecting the number of players");
		createNewGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnCreateGame = new JButton("Create Game");
		btnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				error = "";
				ButtonModel playerNumber = buttonGroup.getSelection();
			
				if(playerNumber==null) {
					error = "Please select the number of players!";
				}
				error.trim();
				if(error.length()==0) {						
					try {
						DesignModeController dmc = new DesignModeController();
						dmc.createGame(Integer.parseInt(playerNumber.getActionCommand()));						
						TileOApplication.dp.numberOfPlayers = Integer.parseInt(playerNumber.getActionCommand());
						TileOApplication.dp.close();
						TileOApplication.dp = new TileODesignPage();	
						TileOApplication.dp.setVisible(true);									
						close();
						TileOApplication.pp.close();
					} 
					catch (InvalidInputException e) {			
						error = e.getMessage();
					}					
				}
				refreshData();
			}
		});
		
		radioButton_2 = new JRadioButton("4");
		radioButton_2.setActionCommand("4");
		buttonGroup.add(radioButton_2);
		
		radioButton_1 = new JRadioButton("3");
		radioButton_1.setActionCommand("3");
		buttonGroup.add(radioButton_1);
		
		radioButton = new JRadioButton("2");
		radioButton.setActionCommand("2");
		buttonGroup.add(radioButton);
		
		loadGameLabel = new JLabel("Load existing game by selecting one existing game");
		
		loadGameComboBox = new JComboBox();
		String[] game = new String[TileOApplication.getTileO().numberOfGames()];
		addNums(game, TileOApplication.getTileO().numberOfGames());
		loadGameComboBox.setModel(new DefaultComboBoxModel(game));
    
		
				loadButton = new JButton("Load");
				loadButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						error = "";					
						if(loadGameComboBox.getItemCount() == 0) {
							error = "TileO has no game!";
						}
						error.trim();
						if (error.length() == 0){
							DesignModeController dmc = new DesignModeController();
							PlayController pmc = new PlayController();
							int chosenGameIndex = loadGameComboBox.getSelectedIndex(); //getSelected returns 0 for first one
							try {
								if(TileOApplication.getTileO().getGame(chosenGameIndex).getMode() == Mode.DESIGN){
									TileOApplication.getTileO().setCurrentGame(dmc.loadDesign(chosenGameIndex));
									TileOApplication.dp.close();
									TileOApplication.dp = new TileODesignPage();
									TileOApplication.dp.setVisible(true);
									TileOApplication.pp.close();
								}
								else{
									pmc.load(chosenGameIndex);
									TileOApplication.pp.close();
									TileOApplication.pp = new TileOPlayPage();
									TileOApplication.pp.setVisible(true);
									TileOApplication.dp.close();
								}
								close();
							} 
							catch (InvalidInputException e1) {
								error = e1.getMessage();
							}
						}
						refreshData();
					}
				});
		
		errorMessage = new JLabel();
		errorMessage.setText("\n");
		errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		errorMessage.setForeground(Color.RED);
		
		
		closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(TileOApplication.dp.isVisible() || TileOApplication.pp.isVisible())){
					System.exit(0);
				}
				else{
					close();
					
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(189, Short.MAX_VALUE)
					.addComponent(loadGameComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(96)
					.addComponent(loadButton)
					.addGap(183))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(124)
					.addComponent(createNewGameLabel)
					.addContainerGap(131, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(116)
							.addComponent(radioButton)
							.addGap(18)
							.addComponent(radioButton_1)
							.addGap(18)
							.addComponent(radioButton_2)
							.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
							.addComponent(btnCreateGame))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(129)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(errorMessage, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
								.addComponent(closeButton, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
								.addComponent(loadGameLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(143))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(38, Short.MAX_VALUE)
					.addComponent(createNewGameLabel)
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreateGame)
						.addComponent(radioButton_2)
						.addComponent(radioButton_1)
						.addComponent(radioButton))
					.addGap(41)
					.addComponent(loadGameLabel)
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(loadGameComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(loadButton))
					.addGap(28)
					.addComponent(errorMessage, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(closeButton)
					.addGap(66))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	
	public void addNums(String[] g, int games){
		for (int i = 1; i<games+1; i++){
			g[i-1] = String.valueOf(i);			
		}
	}
	
	public void close() { 
		this.setVisible(false);
	    this.dispose();
	}
	private void refreshData() {
		if (!(error.length()==0)){
			errorMessage.setText(error);
		}
	}
}
