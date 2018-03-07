package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayModeController;
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

public class WelcomePage extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel errorMessage;
	String error;
	ButtonModel chosenNumber;
	String numberOfPlayers;
	int chosenNumberOfPlayers;
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					WelcomePage frame = new WelcomePage();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public WelcomePage() {
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 605, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Create a new game by selecting the number of players");
		
		JRadioButton radioButton = new JRadioButton("2");
		radioButton.setActionCommand("2");
		buttonGroup.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("3");
		radioButton_1.setActionCommand("3");
		buttonGroup.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("4");
		radioButton_2.setActionCommand("4");
		buttonGroup.add(radioButton_2);
		
		JButton btnCreateGame = new JButton("Create Game");
		btnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ButtonModel playerNumber;
				error = "";
				playerNumber = buttonGroup.getSelection();
			
				if(playerNumber==null) {
					error = "Please select the number of players! ";
				}
				error.trim();
				if(error.length()==0) {						
					try {
						DesignModeController dmc = new DesignModeController ();
						dmc.createGame( Integer.parseInt(playerNumber.getActionCommand()));
					
						TileODesignPage.refreshData();
						close();
					} catch (InvalidInputException e) {			
						error = e.getMessage();
					}					
				}
				refreshData();
				
					//System.out.print("" + TileOApplication.getTileO().getCurrentGame()); //TODO: DELETE
					
//					TileOApplication.getTileO().setCurrentGame(newGame);
					//System.out.print(" " + chosenNumberOfPlayers);
					
					
					//System.out.print("" + TileOApplication.getTileO().getCurrentGame()); //TODO: DELETE
				
				
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Load existing game by selecting one existing game");
		
		JComboBox gameNumberIndexCB = new JComboBox();
		String[] games = new String[TileOApplication.getTileO().numberOfGames()];
		addNums(games, TileOApplication.getTileO().numberOfGames());
		gameNumberIndexCB.setModel(new DefaultComboBoxModel(games));
		
		JLabel lblContinueEditing = new JLabel("");
		
		JButton btnNewButton = new JButton("Continue designing last design?");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DesignModeController dmc = new DesignModeController();
				PlayModeController pmc = new PlayModeController();
				int chosenGameIndex = gameNumberIndexCB.getSelectedIndex(); //getSelected returns 0 for first one
				try {
//					Game newGame =dmc.loadDesign(chosenGameIndex);
//					new NormalTile(1, 1, newGame);
					if(TileOApplication.getTileO().getGame(chosenGameIndex).getMode() == Mode.DESIGN){
						TileOApplication.getTileO().setCurrentGame(dmc.loadDesign(chosenGameIndex));
					}
					else if(TileOApplication.getTileO().getGame(chosenGameIndex).getMode() == Mode.GAME){
						TileOApplication.getTileO().setCurrentGame(pmc.loadGame(chosenGameIndex));
						TileOPlayPage topp = new TileOPlayPage();
						topp.setVisible(true);
						

					}
					TileODesignPage.refreshData();
					//System.out.print("" + TileOApplication.getTileO().getCurrentGame());
					close();
				} catch (InvalidInputException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(107)
					.addComponent(radioButton)
					.addGap(18)
					.addComponent(radioButton_1)
					.addGap(18)
					.addComponent(radioButton_2)
					.addGap(116)
					.addComponent(btnCreateGame)
					.addContainerGap(98, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(379, Short.MAX_VALUE)
					.addComponent(lblContinueEditing)
					.addGap(210))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(291, Short.MAX_VALUE)
					.addComponent(gameNumberIndexCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(261))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(269, Short.MAX_VALUE)
					.addComponent(loadButton)
					.addGap(255))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(138, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(170))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(66, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addGap(131))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(50, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(123))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(225)
					.addComponent(errorMessage)
					.addContainerGap(292, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(radioButton_1)
						.addComponent(radioButton)
						.addComponent(radioButton_2)
						.addComponent(btnCreateGame))
					.addGap(28)
					.addComponent(lblNewLabel_1)
					.addGap(18)
					.addComponent(gameNumberIndexCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblContinueEditing)
						.addComponent(loadButton))
					.addGap(34)
					.addComponent(btnNewButton)
					.addGap(38)
					.addComponent(errorMessage)
					.addContainerGap(91, Short.MAX_VALUE))
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
		errorMessage.setText(error);
		if(error == null || error.length()==0){
			close();
		}
	}
}
