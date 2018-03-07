package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayModeController;
import ca.mcgill.ecse223.tileo.model.Game;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class TileODesignPage extends JFrame {

	private static TilePanelDesign grid = new TilePanelDesign(TileOApplication.getTileO().getCurrentGame());
	
	
	
	
	private JPanel contentPane;
	private JComboBox<Game> selectedGameComboBox;
	private String error;
	JLabel errorMessage;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TileODesignPage frame = new TileODesignPage();
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
	public TileODesignPage() {
		WelcomePage wp = new WelcomePage();
		wp.setVisible(true);
		setTitle("Design Mode");
		initComponents();
		refreshData();
	}
	
	public void close() { 
		this.setVisible(false);
	    this.dispose();
	}
	
	private void initComponents(){
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setBounds(100, 100, 450, 300);
		this.setSize(1200, 720);
		this.setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setSize(490, 720);
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setVisible(true);
		//setContentPane(contentPane);
				
		JButton btnOpenNewWindow = new JButton("Add Tiles");
		btnOpenNewWindow.addActionListener(new ActionListener() {
			
	
			public void actionPerformed(ActionEvent e) {
				
				AddTilePopOut te = new AddTilePopOut();
				te.setVisible(true);
			}

			
		});
		
		JButton btnRemoveTiles = new JButton("Remove Tile");
		btnRemoveTiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (grid.aTileIsSelected){
					DesignModeController dmc = new DesignModeController();
					try {
						dmc.removeTileFromGame(grid.selectedTile);
					} catch (InvalidInputException e1) {
						throw new RuntimeException(e1.getMessage());
					}
				}
				
			}
		});
		
		JButton btnAddConnections = new JButton("Add Connection");
		btnAddConnections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddConnectionDesignPopOut acdpo = new AddConnectionDesignPopOut();
				acdpo.setVisible(true);
				
			}
		});
		
		JButton btnRemoveConnections = new JButton("Remove Connection");
		btnRemoveConnections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(grid.aConnectionIsSelected){
					DesignModeController dmc = new DesignModeController ();
					try 
					{
						dmc.removeConnection(grid.selectedConnection);
					}
					catch (InvalidInputException e2){
						throw new RuntimeException (e2.getMessage());
					}
				}
				
			}
		});
		
		JButton btnAddDeck = new JButton("Add Deck");
		btnAddDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeckPopOut deck = new DeckPopOut();
				deck.setVisible(true);
			}
		});
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SavePopOut sdpo = new SavePopOut();
				sdpo.setVisible(true);
				DesignModeController dmc = new DesignModeController();
				dmc.saveDesign();
			}
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				refreshData();
				
			}
		});
		
		JButton btnSetStartingTile = new JButton("Set Starting Tile Of Players");
		btnSetStartingTile.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SetPlayerStartingTilePopOut spstpo = new SetPlayerStartingTilePopOut();
				spstpo.setVisible(true);
				
			}
				
			
		});
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGamePerformed(e);
			}
		});
		
		//selectedGameComboBox = new JComboBox<Game>(new Game[0]);
		
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		selectedGameComboBox = new JComboBox();
		String[] games = new String[TileOApplication.getTileO().numberOfGames()];
		addNums(games, TileOApplication.getTileO().numberOfGames());
		selectedGameComboBox.setModel(new DefaultComboBoxModel(games));
		
//		JComboBox gameNumberIndexCB = new JComboBox();
//		String[] games = new String[TileOApplication.getTileO().numberOfGames()];
//		addNums(games, TileOApplication.getTileO().numberOfGames());
//		gameNumberIndexCB.setModel(new DefaultComboBoxModel(games));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnOpenNewWindow, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRemoveTiles)
								.addComponent(btnAddConnections)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnRefresh)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnRemoveConnections)
											.addGap(41)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddDeck)))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(174))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnSetStartingTile)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(selectedGameComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnStartGame)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(103)
							.addComponent(errorMessage)))
					.addContainerGap(150, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnOpenNewWindow)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRemoveTiles)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddConnections)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRemoveConnections)
						.addComponent(btnAddDeck))
					.addGap(83)
					.addComponent(btnNewButton)
					.addGap(249)
					.addComponent(btnRefresh)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSetStartingTile)
						.addComponent(btnStartGame)
						.addComponent(selectedGameComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(errorMessage)
					.addContainerGap(576, Short.MAX_VALUE))
		);
		//contentPane.setLayout(gl_contentPane);
		
		//----------------------------------------------------------------------------------------------------
//		TilePanel grid = new TilePanel(TileOApplication.getTileO().getCurrentGame());
		grid.setVisible(true);
		grid.setSize(700, 720);
		
		
		
		
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setSize(1200, 720);
	    splitPane.setDividerSize(0);
	    splitPane.setDividerLocation(710);
	    splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
	    splitPane.setLeftComponent(grid);
	    splitPane.setRightComponent(contentPane);

	    getContentPane().add(splitPane);
	    
	    
	}
		
	public static void refreshData(){
		grid.setGame(TileOApplication.getTileO().getCurrentGame());
		grid.setVisible(true);
		grid.setSize(700, 720);
		
	}
	
	public static TilePanelDesign getGrid(){
		return grid;
	}

	public void startGamePerformed(java.awt.event.ActionEvent evt) {
		error = "";
		try{
			int gameIndex = selectedGameComboBox.getSelectedIndex(); 
			PlayModeController pmc = new PlayModeController();
			
			Game selectedGame = TileOApplication.getTileO().getGame(gameIndex);
			pmc.startGame(selectedGame);
		}catch (InvalidInputException e) {
			error = e.getMessage();
		}
		displayPlayBoard();
	}
	public void displayPlayBoard() {
		errorMessage.setText(error);
		if(error==null||error.length()==0){
			TileOPlayPage topp = new TileOPlayPage();
			topp.setVisible(true);
		}
    }
	
	public void addNums(String[] g, int games){
		for (int i = 1; i<games+1; i++){
			g[i-1] = String.valueOf(i);			
		}
	}
}
