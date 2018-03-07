package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;

import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.model.Tile;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSpinner;

public class TileODesignPage extends JFrame {

	private static TilePanelDesign grid = new TilePanelDesign(TileOApplication.getTileO().getCurrentGame());
	public static DesignModeController dmc = new DesignModeController();
	private PlayController pmc = new PlayController();
	private static DesignHelpPO helpPop = new DesignHelpPO();
	private static SavePopOut savePop = new SavePopOut();
	private static DeckPopOut deckPop = new DeckPopOut();
	public static int numberOfPlayers;
	private static int numberOfGames = 0;
	private JPanel contentPane;
	private static String error;	
	
	//error label
	private static JLabel errorLbl;
	
	//Add tile Panel
	private JPanel AddTilePnl;
	private JLabel addTileLbl;
	private JRadioButton normalTileBtn;
	private JRadioButton actionTileBtn;
	private JTextField inactivePeriod;
	private JRadioButton winTileBtn;
	private final ButtonGroup tileBtnGroup = new ButtonGroup();
	private JLabel xLbl;
	private JLabel yLbl;
	private JTextField xComponent;
	private JTextField yComponent;
	private JButton addTileBtn;
	
	//Remove tile Panel
	private JPanel removeTilePnl;
	private JLabel removeTileLbl;
	
	//Add connection Panel
	private JPanel addConnectionPnl;
	private JLabel addConnectionLbl;
	private JButton tile1Btn;
	private JButton tile2Btn;
	private JButton connectBtn;
	private Tile chosenTile1=null;
	private Tile chosenTile2=null;
	
	//Remove connection Panel
	private JPanel removeConnectionPnl;
	private JLabel removeConnectionLbl;
	private JButton removeConnectionBtn;
	
	//Create deck Panel
	private JLabel createDeckLBl;
	private JPanel createDeckPnl;
    private JButton createDeckBtn;
	
	//Setting player starting tile Panel
	private JPanel startingTilePnl;
	private JLabel startingTileLbl;
	private JLabel playerLbl;
	private static JComboBox playerNumCBox;
	private JButton setStartTileBtn;
	
	//Save, start game, and help Panel
	private JPanel savePnl;
	private JLabel saveLbl;
	private JButton saveBtn;
	private JLabel startGameLbl;
	private static JComboBox startGameCBox;
	private JButton startGameBtn;
	private JLabel unclearLbl;
	private JButton helpBtn;
	private JLabel lblNewLabel;
	private JLabel lblInDesignMode;
	
	
	
    
	/**
	 * Create the frame.
	 */
	public TileODesignPage() {
		if (TileOApplication.getTileO().getCurrentGame() != null){
			Game currentGame = TileOApplication.getTileO().getCurrentGame();
			setTitle("Design Mode: Game  " + String.valueOf(TileOApplication.getTileO().indexOfGame(currentGame)+1));
			numberOfPlayers = currentGame.numberOfPlayers();
		}
		initComponents();
		refreshData();
	}
	
	public void close() { 
		this.setVisible(false);
	    this.dispose();
	}
	
	private void initComponents(){
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200, 720);
		this.setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setSize(490, 720);
		contentPane.setVisible(true);
		errorLbl = new JLabel();
		errorLbl.setForeground(Color.RED);
		String[] games = new String[TileOApplication.getTileO().numberOfGames()];
		addNums(games, TileOApplication.getTileO().numberOfGames());
	
		
		grid.setVisible(true);
		grid.setSize(700, 720);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setSize(1200, 720);
	    splitPane.setDividerSize(0);
	    splitPane.setDividerLocation(710);
	    splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
	    splitPane.setLeftComponent(grid);
	    splitPane.setRightComponent(contentPane);
	    
	    AddTilePnl = new JPanel();
	    AddTilePnl.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    
	    removeTilePnl = new JPanel();
	    removeTilePnl.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    
	    addConnectionPnl = new JPanel();
	    addConnectionPnl.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    
	    removeConnectionPnl = new JPanel();
	    removeConnectionPnl.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    
	    removeConnectionBtn = new JButton("Remove Connection");
	    removeConnectionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				if(!grid.aConnectionIsSelected){
					error = "Please select a connection to delete.";
				}			
				else if (grid.aConnectionIsSelected) {
					try 
					{
						dmc.removeConnection(grid.selectedConnection);
						grid.aConnectionIsSelected = false;
						grid.selectedConnection = null;
					}
					catch (InvalidInputException e2){
						throw new RuntimeException (e2.getMessage());
					}
				}
				refreshData();
			}
		});
	    
	    removeConnectionLbl = new JLabel("Select a connection to remove it");
	    GroupLayout gl_removeConnectionPnl = new GroupLayout(removeConnectionPnl);
	    gl_removeConnectionPnl.setHorizontalGroup(
	    	gl_removeConnectionPnl.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_removeConnectionPnl.createSequentialGroup()
	    			.addGap(17)
	    			.addComponent(removeConnectionLbl)
	    			.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
	    			.addComponent(removeConnectionBtn)
	    			.addGap(32))
	    );
	    gl_removeConnectionPnl.setVerticalGroup(
	    	gl_removeConnectionPnl.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_removeConnectionPnl.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_removeConnectionPnl.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(removeConnectionLbl)
	    				.addComponent(removeConnectionBtn))
	    			.addContainerGap(7, Short.MAX_VALUE))
	    );
	    removeConnectionPnl.setLayout(gl_removeConnectionPnl);
	    
	    createDeckPnl = new JPanel();
	    createDeckPnl.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    
	    startingTilePnl = new JPanel();
	    startingTilePnl.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    
	    savePnl = new JPanel();
	    savePnl.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    
	    GroupLayout gl_contentPane = new GroupLayout(contentPane);
	    gl_contentPane.setHorizontalGroup(
	    	gl_contentPane.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_contentPane.createSequentialGroup()
	    			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addGap(16)
	    					.addComponent(errorLbl))
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addContainerGap()
	    					.addComponent(AddTilePnl, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addContainerGap()
	    					.addComponent(removeTilePnl, GroupLayout.PREFERRED_SIZE, 474, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addContainerGap()
	    					.addComponent(addConnectionPnl, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addContainerGap()
	    					.addComponent(removeConnectionPnl, GroupLayout.PREFERRED_SIZE, 474, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addContainerGap()
	    					.addComponent(createDeckPnl, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addContainerGap()
	    					.addComponent(startingTilePnl, GroupLayout.PREFERRED_SIZE, 472, GroupLayout.PREFERRED_SIZE))
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addContainerGap()
	    					.addComponent(savePnl, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)))
	    			.addContainerGap(7, Short.MAX_VALUE))
	    );
	    gl_contentPane.setVerticalGroup(
	    	gl_contentPane.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_contentPane.createSequentialGroup()
	    			.addGap(23)
	    			.addComponent(errorLbl)
	    			.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
	    			.addComponent(AddTilePnl, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(removeTilePnl, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(addConnectionPnl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(removeConnectionPnl, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(createDeckPnl, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(startingTilePnl, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(savePnl, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
	    			.addContainerGap())
	    );
	    
	    
	    startGameBtn = new JButton("Start Game");
	    startGameBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		startGamePerformed(e);
	    	}
	    });
	    
	    startGameCBox = new JComboBox();
	    if (numberOfGames != 0){
	    	String[] gameNum = new String[numberOfGames];
			addNums(gameNum, numberOfGames);
			startGameCBox.setModel(new DefaultComboBoxModel(gameNum));
	    }
	    	    
	    startGameLbl = new JLabel("Select a game to start it");
	    
	    saveBtn = new JButton("Save");
	    saveBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
//	    		SavePopOut sdpo = new SavePopOut();
	    		savePop.setVisible(true);
	    		dmc.saveDesign();
	    		refreshData();
	    	}
	    });
	    
	    saveLbl = new JLabel("Don't forget to save!");
	    
	    unclearLbl = new JLabel("Unclear?");
	    
	    helpBtn = new JButton("Help!");
	    helpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				helpPop.setVisible(true);
			}
		});
	    
	    JButton btnStartClone = new JButton("Start & Clone");
	    btnStartClone.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		error = "";
	    		if(startGameCBox.getItemCount() == 0) {
	    			error = "There are no games to play.";
	    		}
	    		else if (startGameCBox.getItemCount() > 0){
	    			try{
	    				int gameIndex = startGameCBox.getSelectedIndex();			
	    				Game selectedGame = TileOApplication.getTileO().getGame(gameIndex);
	    				TileOApplication.getTileO().setCurrentGame(selectedGame);
	    				pmc.startGame(true);
	    				TileOApplication.pp.close();
	    				close();
	    				TileOApplication.pp = new TileOPlayPage();
	    				TileOApplication.pp.setVisible(true);
	    			}
	    			catch (InvalidInputException e1) {
	    				error = e1.getMessage();
	    			}
	    		}
	    		refreshData();	
	    	}
	    });
	    
	    lblNewLabel = new JLabel("Cloned game has index currentIndex + 1,");
	    
	    lblInDesignMode = new JLabel("in Design mode");
	    GroupLayout gl_savePnl = new GroupLayout(savePnl);
	    gl_savePnl.setHorizontalGroup(
	    	gl_savePnl.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_savePnl.createSequentialGroup()
	    			.addGap(17)
	    			.addComponent(saveLbl)
	    			.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
	    			.addComponent(startGameLbl)
	    			.addGap(58))
	    		.addGroup(gl_savePnl.createSequentialGroup()
	    			.addGroup(gl_savePnl.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_savePnl.createSequentialGroup()
	    					.addGap(42)
	    					.addComponent(unclearLbl))
	    				.addGroup(Alignment.TRAILING, gl_savePnl.createSequentialGroup()
	    					.addContainerGap(29, Short.MAX_VALUE)
	    					.addGroup(gl_savePnl.createParallelGroup(Alignment.LEADING)
	    						.addComponent(helpBtn)
	    						.addComponent(saveBtn))
	    					.addGap(71)))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addGroup(gl_savePnl.createParallelGroup(Alignment.TRAILING)
	    				.addGroup(gl_savePnl.createSequentialGroup()
	    					.addComponent(lblInDesignMode)
	    					.addGap(18)
	    					.addComponent(btnStartClone))
	    				.addGroup(gl_savePnl.createSequentialGroup()
	    					.addComponent(startGameCBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(startGameBtn))
	    				.addComponent(lblNewLabel))
	    			.addGap(31))
	    );
	    gl_savePnl.setVerticalGroup(
	    	gl_savePnl.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_savePnl.createSequentialGroup()
	    			.addGap(15)
	    			.addGroup(gl_savePnl.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(saveLbl)
	    				.addComponent(startGameLbl))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addGroup(gl_savePnl.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(startGameBtn)
	    				.addComponent(startGameCBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(saveBtn))
	    			.addGap(18)
	    			.addGroup(gl_savePnl.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(unclearLbl)
	    				.addComponent(lblNewLabel))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addGroup(gl_savePnl.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(btnStartClone)
	    				.addComponent(lblInDesignMode)
	    				.addComponent(helpBtn))
	    			.addContainerGap())
	    );
	    savePnl.setLayout(gl_savePnl);
	    
	    startingTileLbl = new JLabel("Select the starting tile of each player");
	    
	    playerLbl = new JLabel("Player");

	    setStartTileBtn = new JButton("Set Starting Tile");
	    setStartTileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				startTileActionPerformed(evt);
			}
		});
	    
	    playerNumCBox = new JComboBox();
    	String[] playerNum = new String[numberOfPlayers];
    	//System.out.println(numberOfPlayers);
		addNums(playerNum, numberOfPlayers);
		playerNumCBox.setModel(new DefaultComboBoxModel(playerNum));
	    
	    
		
	    GroupLayout gl_startingTilePnl = new GroupLayout(startingTilePnl);
	    gl_startingTilePnl.setHorizontalGroup(
	    	gl_startingTilePnl.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_startingTilePnl.createSequentialGroup()
	    			.addGap(15)
	    			.addComponent(startingTileLbl)
	    			.addContainerGap(223, Short.MAX_VALUE))
	    		.addGroup(gl_startingTilePnl.createSequentialGroup()
	    			.addGap(31)
	    			.addComponent(playerLbl)
	    			.addGap(18)
	    			.addComponent(playerNumCBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
	    			.addComponent(setStartTileBtn)
	    			.addGap(14))
	    );
	    gl_startingTilePnl.setVerticalGroup(
	    	gl_startingTilePnl.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_startingTilePnl.createSequentialGroup()
	    			.addGap(14)
	    			.addComponent(startingTileLbl)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addGroup(gl_startingTilePnl.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(playerLbl)
	    				.addComponent(setStartTileBtn)
	    				.addComponent(playerNumCBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    );
	    startingTilePnl.setLayout(gl_startingTilePnl);
	    
	    createDeckBtn = new JButton("Create Deck");
	    createDeckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {	
				deckPop.setVisible(true);;
			}
		});
	    
	    
	    createDeckLBl = new JLabel("Create a new Deck");
	    
	    
	    GroupLayout gl_createDeckPnl = new GroupLayout(createDeckPnl);
	    gl_createDeckPnl.setHorizontalGroup(
	    	gl_createDeckPnl.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_createDeckPnl.createSequentialGroup()
	    			.addGap(41)
	    			.addComponent(createDeckLBl)
	    			.addPreferredGap(ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
	    			.addGroup(gl_createDeckPnl.createParallelGroup(Alignment.LEADING)
	    				.addGroup(Alignment.TRAILING, gl_createDeckPnl.createSequentialGroup()
	    					.addComponent(createDeckBtn)
	    					.addGap(55))))
	    );
	    gl_createDeckPnl.setVerticalGroup(
	    	gl_createDeckPnl.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(gl_createDeckPnl.createSequentialGroup()
	    			.addGap(14)
	    			.addGroup(gl_createDeckPnl.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(createDeckLBl)
	    				.addComponent(createDeckBtn))
	    			.addGap(89)
	    			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    );
	    createDeckPnl.setLayout(gl_createDeckPnl);
	    
	    addConnectionLbl = new JLabel("Add a connection between two tiles");
	    
	    tile1Btn = new JButton("Tile 1 Chosen");
	    tile1Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				error="";
				if (TileODesignPage.getGrid().aTileIsSelected){
					chosenTile1 = TileODesignPage.getGrid().selectedTile;				
				}
				if(chosenTile1==null){
					error = "Please select a tile on the board and then press the \"Tile 1 Chosen\" button! ";
				}
				error.trim();
				errorLbl.setText(error);
			}
		});
	    
	    tile2Btn = new JButton("Tile 2 Chosen");
	    tile2Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error="";
				if (TileODesignPage.getGrid().aTileIsSelected){
					chosenTile2 = TileODesignPage.getGrid().selectedTile;
				}
				if(chosenTile2==null){
					error = "Please select a tile on the board and then press the \"Tile 2 Chosen\" button! ";
				}
				error.trim();
				errorLbl.setText(error);
			}
		});
	    
	    connectBtn = new JButton("Connect");
	    connectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				   if(chosenTile1==null||chosenTile2==null){
					   error = "Please choose the tiles first! ";
				   }
				   error.trim();
				   if(error.length()==0) {
					   try {
							dmc.connectTwoTiles(chosenTile1, chosenTile2);
							grid.aTileIsSelected = false;
							grid.selectedTile = null;
							chosenTile1 = null;
							chosenTile2 = null;
						} 
					   catch (InvalidInputException e1) {
							error = e1.getMessage();
						}
				   }
				   refreshData();
			}
		});
	    
	    GroupLayout gl_addConnectionPnl = new GroupLayout(addConnectionPnl);
	    gl_addConnectionPnl.setHorizontalGroup(
	    	gl_addConnectionPnl.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_addConnectionPnl.createSequentialGroup()
	    			.addGap(18)
	    			.addGroup(gl_addConnectionPnl.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_addConnectionPnl.createSequentialGroup()
	    					.addGap(6)
	    					.addComponent(tile1Btn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(tile2Btn)
	    					.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
	    					.addComponent(connectBtn)
	    					.addGap(42))
	    				.addGroup(gl_addConnectionPnl.createSequentialGroup()
	    					.addComponent(addConnectionLbl)
	    					.addContainerGap(231, Short.MAX_VALUE))))
	    );
	    gl_addConnectionPnl.setVerticalGroup(
	    	gl_addConnectionPnl.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_addConnectionPnl.createSequentialGroup()
	    			.addGap(14)
	    			.addComponent(addConnectionLbl)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addGroup(gl_addConnectionPnl.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(tile1Btn)
	    				.addComponent(tile2Btn)
	    				.addComponent(connectBtn))
	    			.addContainerGap())
	    );
	    addConnectionPnl.setLayout(gl_addConnectionPnl);
	    
	    removeTileLbl = new JLabel("Select a tile to remove it");
	    
	    
	    JButton removeTileBtn = new JButton("Remove Tile");
	    removeTileBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		error = "";
	    		if (!grid.aTileIsSelected){
	    			error = "Please select a tile to delete.";	    			
	    		}
	    		if (grid.aTileIsSelected && grid.selectedTile.hasConnections()){
	    			error = "You cannot delete a tile that has connections.";
	    		}
	    		if (error.length() == 0 && grid.aTileIsSelected) {
	    			try {
	    				dmc.removeTileFromGame(grid.selectedTile);
	    				grid.selectedTile = null;	
	    				grid.aTileIsSelected = false;
	    				
	    			} 
	    			catch (InvalidInputException e1) {
	    				throw new RuntimeException(e1.getMessage());
	    			}
	    		}
	    		else {	    			
	    			errorLbl.setText(error);
	    		}
	    		refreshData();	    		
	    	}
	    });
	    GroupLayout gl_removeTilePnl = new GroupLayout(removeTilePnl);
	    gl_removeTilePnl.setHorizontalGroup(
	    	gl_removeTilePnl.createParallelGroup(Alignment.LEADING)
	    		.addGroup(Alignment.TRAILING, gl_removeTilePnl.createSequentialGroup()
	    			.addGap(17)
	    			.addComponent(removeTileLbl)
	    			.addPreferredGap(ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
	    			.addComponent(removeTileBtn)
	    			.addGap(31))
	    );
	    gl_removeTilePnl.setVerticalGroup(
	    	gl_removeTilePnl.createParallelGroup(Alignment.LEADING)
	    		.addGroup(Alignment.TRAILING, gl_removeTilePnl.createSequentialGroup()
	    			.addContainerGap(17, Short.MAX_VALUE)
	    			.addGroup(gl_removeTilePnl.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(removeTileBtn)
	    				.addComponent(removeTileLbl))
	    			.addContainerGap())
	    );
	    removeTilePnl.setLayout(gl_removeTilePnl);
	    
		addTileBtn = new JButton("Add Tile");
		addTileBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		addTileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addTileActionPerformed(evt);
			}
		});
		
		addTileLbl = new JLabel("Add a tile to the board");
		
		normalTileBtn = new JRadioButton("Normal Tile");
		normalTileBtn.setActionCommand("NormalTile");
		tileBtnGroup.add(normalTileBtn);
		
		actionTileBtn = new JRadioButton("Action Tile");
		actionTileBtn.setActionCommand("ActionTile");
		tileBtnGroup.add(actionTileBtn);
		
		winTileBtn = new JRadioButton("Win Tile");
		winTileBtn.setActionCommand("WinTile");
		tileBtnGroup.add(winTileBtn);
						
		xLbl = new JLabel("X:");
		xComponent = new JTextField();
		xComponent.setColumns(10);
		
		yLbl = new JLabel("Y:");
		yComponent = new JTextField();
		yComponent.setColumns(10);
		
		inactivePeriod = new JTextField();
		inactivePeriod.setColumns(10);
		
		GroupLayout gl_AddTilePnl = new GroupLayout(AddTilePnl);
		gl_AddTilePnl.setHorizontalGroup(
			gl_AddTilePnl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AddTilePnl.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_AddTilePnl.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_AddTilePnl.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_AddTilePnl.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_AddTilePnl.createSequentialGroup()
									.addComponent(xLbl)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(xComponent, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_AddTilePnl.createSequentialGroup()
									.addComponent(normalTileBtn)
									.addGap(18)))
							.addGroup(gl_AddTilePnl.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_AddTilePnl.createSequentialGroup()
									.addGap(28)
									.addComponent(yLbl)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(yComponent, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addGap(68)
									.addComponent(addTileBtn))
								.addGroup(gl_AddTilePnl.createSequentialGroup()
									.addGap(6)
									.addComponent(actionTileBtn)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(inactivePeriod, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addGap(27)
									.addComponent(winTileBtn))))
						.addComponent(addTileLbl))
					.addGap(71))
		);
		gl_AddTilePnl.setVerticalGroup(
			gl_AddTilePnl.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_AddTilePnl.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(addTileLbl)
					.addGap(18)
					.addGroup(gl_AddTilePnl.createParallelGroup(Alignment.BASELINE)
						.addComponent(actionTileBtn)
						.addComponent(normalTileBtn)
						.addComponent(inactivePeriod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(winTileBtn))
					.addGap(10)
					.addGroup(gl_AddTilePnl.createParallelGroup(Alignment.BASELINE)
						.addComponent(xComponent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(xLbl)
						.addComponent(yLbl)
						.addComponent(yComponent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(addTileBtn))
					.addGap(9))
		);
		AddTilePnl.setLayout(gl_AddTilePnl);
	    contentPane.setLayout(gl_contentPane);

	    getContentPane().add(splitPane);
	}
	
	private void startTileActionPerformed(ActionEvent evt) {		
		error = "";
		int chosenPlayerNumber = playerNumCBox.getSelectedIndex() + 1;
		Tile chosenTile = null;
		if (TileODesignPage.getGrid().aTileIsSelected){
			chosenTile = TileODesignPage.getGrid().selectedTile;
		}
		if(chosenTile == null){
			error = "Please choose a tile on the board!";
		}
		error.trim();
		if(error.length()==0) {
			try {
				dmc.setPlayerStartingTile(chosenPlayerNumber, chosenTile);
				error = "Player " + chosenPlayerNumber + " starting tile set!";
				grid.aTileIsSelected = false;
				grid.selectedTile = null;
			} 
			catch (InvalidInputException e1) {
				error = e1.getMessage();
			}	
		}
		refreshData();		
	}

	private void addTileActionPerformed(java.awt.event.ActionEvent evt){
		ButtonModel chosenTile = null;
		int chosenXComp = 0;
		int chosenYComp = 0;
		int inactive = 0;
		error = "";
		try {
			chosenTile = tileBtnGroup.getSelection();
		}
		catch (NullPointerException e){
			error = "Please select a Tile Type.";			
		}
		
		if(chosenTile == null) {
			error = "Please select a Tile Type.";
		}		
		try {
			chosenXComp = Integer.parseInt(xComponent.getText());
		}
		catch(NumberFormatException e){
			error ="The x-coordinate needs to be a numerical value!\n";
		}
		try {
			chosenYComp = Integer.parseInt(yComponent.getText());
		}
		catch(NumberFormatException e){
			error ="The y-coordinate needs to be a numerical value!\n";
		}
		
		if(chosenTile != null && chosenTile.getActionCommand() == "ActionTile"){
			try {
				inactive = Integer.parseInt(inactivePeriod.getText());
			}
			catch(NumberFormatException e) {
				error ="The inactivity period needs to be a numerical value!\n";
			}
		}
		
		error.trim();
		if (error.length()==0) {
			try{
				dmc.addDesignTile(chosenXComp, chosenYComp,chosenTile.getActionCommand(), inactive);
			} 
			catch (InvalidInputException er) {
			error = er.getMessage();
			}
		}
		yComponent.setText("");
		xComponent.setText("");
//		inactivePeriod.setText("");
		refreshData();		
	}
		
	public static void refreshData(){
		grid.setGame(TileOApplication.getTileO().getCurrentGame());
		errorLbl.setText(error);
		
//		if (TileOApplication.getTileO().hasCurrentGame()){
//			String[] playerNum = new String[TileOApplication.getTileO().getCurrentGame().numberOfPlayers()];
//			addNums(playerNum, TileOApplication.getTileO().getCurrentGame().numberOfPlayers());
//			playerNumCBox.setModel(new DefaultComboBoxModel(playerNum));					
//		}
		
		List<Game> games = TileOApplication.getTileO().getGames();	
		numberOfGames = 0;
		for (Game aGame: games){
			numberOfGames += 1;		
		}
		String[] gameList = new String[numberOfGames];
		addNums(gameList, numberOfGames);
		startGameCBox.setModel(new DefaultComboBoxModel(gameList));		
	}
	
	public static TilePanelDesign getGrid(){
		return grid;
	}

	public void startGamePerformed(java.awt.event.ActionEvent evt) {
		error = "";
		if(startGameCBox.getItemCount() == 0) {
			error = "There are no games to play.";
		}
		else if (startGameCBox.getItemCount() > 0){
			try{
				int gameIndex = startGameCBox.getSelectedIndex();			
				Game selectedGame = TileOApplication.getTileO().getGame(gameIndex);
				TileOApplication.getTileO().setCurrentGame(selectedGame);
				pmc.startGame(false);
				TileOApplication.pp.close();
				close();
				TileOApplication.pp = new TileOPlayPage();
				TileOApplication.pp.setVisible(true);
			}
			catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		refreshData();	
	}
	
	public void displayPlayBoard() {		
		errorLbl.setText(error);
		if(error==null||error.length()==0){
			TileOPlayPage topp = new TileOPlayPage();
			topp.setVisible(true);
		}
    }
	
	public static void addNums(String[] str, int number){
		for (int i = 1; i < number+1 ; i++){
			str[i-1] = String.valueOf(i);			
		}
	}
}
