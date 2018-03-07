package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;

import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.TileO;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

public class TileOPlayPage extends JFrame {

	private static TilePanelPlay grid = new TilePanelPlay(TileOApplication.getTileO().getCurrentGame());
	public static PlayController pmc = new PlayController();
	static String currentPlayer;
	public static List<Tile> pMoves;
	private static int currentPlayerNb = 1;
	
	
	//all our pop outs
	private static AddConnectionActionCardPopOut addConnectionPO = new AddConnectionActionCardPopOut();
	private static LoseTurnWarningPopOut loseTurnPO = new LoseTurnWarningPopOut();
	private static RemoveConnectionActionCardPopOut removeConnectionPO = new RemoveConnectionActionCardPopOut();
	private static RevealPopOut revealPO = new RevealPopOut();
	private static RollDiePopOut rollPO = new RollDiePopOut();
//	private static TeleportPopOut teleportPO = new TeleportPopOut();
	private static RevealTilesPopOut revealTilesPO = new RevealTilesPopOut();
	private static TurnActionTilesInactiveActionCardPopOut turnInactivePO= new TurnActionTilesInactiveActionCardPopOut();
	private static WinTileHintActionCardPopOut winHintPO = new WinTileHintActionCardPopOut();
	private static SendBackToStartPopOut sendStartPO = new SendBackToStartPopOut();
	private static TeleportOtherPopOut teleportOtherPO = new TeleportOtherPopOut();
	//doesn't work like this lmao
//	private static LoseTurnRandomlyPopOut loseTurnRandomlyPO = new LoseTurnRandomlyPopOut();
	
	private JPanel contentPane;
	private JSplitPane splitPane;
	private static JLabel playerTurnLbl;
	private static JLabel modeLbl;
	private static JLabel errorLbl;
	static JButton btnRollDie;
	private static JLabel gameModeLbl;
	private JButton saveBtn;
	
	private static JLabel numberLbl;
	private static JLabel rollLbl;
	public static boolean rollDieOpen = false;
	
	private static JLabel p1InactivityLbl = new JLabel("");
	private static JLabel p2InactivityLbl = new JLabel("");
	private static JLabel p3InactivityLbl = new JLabel("");
	private static JLabel p4InactivityLbl = new JLabel("");
	

	/**
	 * Create the frame.
	 */
	public TileOPlayPage() {
		Game currentGame = TileOApplication.getTileO().getCurrentGame();
		setTitle("Play Mode: Game number " + String.valueOf(TileOApplication.getTileO().indexOfGame(currentGame)+1));
		initComponents();
		refreshData();
	}
	
	public void close() { 
		this.setVisible(false);
	    this.dispose();
	}
	
	public static void setError(String error ){
		errorLbl.setText(error);
	}
	
	private void initComponents(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		this.setSize(1200, 720);
		this.setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setSize(490, 720);

		contentPane.setVisible(true);
		
		splitPane = new JSplitPane();
		splitPane.setSize(1200, 720);
	    splitPane.setDividerSize(0);
	    splitPane.setDividerLocation(710);
	    splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
	    splitPane.setLeftComponent(grid);
	    splitPane.setRightComponent(contentPane);
	    
	    playerTurnLbl = new JLabel();
	    playerTurnLbl.setText("It is currently player X's turn.");
	    playerTurnLbl.setFont(new Font("Lucida Grande", Font.BOLD, 22));
	    
	    errorLbl = new JLabel("");
	    errorLbl.setForeground(Color.RED);
	    
	    modeLbl = new JLabel("Mode");
	    
	    saveBtn = new JButton("Save");
	    saveBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		if(pmc.getMode().equals(PlayController.Mode.Ready) || pmc.getMode().equals(PlayController.Mode.Roll)){
	    			pmc.saveGame();
		    		SavePopOut spo = new SavePopOut();
		    		spo.setVisible(true);
		    		//refreshData();
	    		}
	    		else {
	    			errorLbl.setText("You cannot save during an action");
	    		}
	    	}
	    });
	    
	    btnRollDie = new JButton("Roll Die");
	    btnRollDie.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		if(pmc.getMode() == PlayController.Mode.Roll){
	    			pmc.rollDie();	    		
		    		pMoves = pmc.getPossibleMoves();
		    		
		    		grid.isAPlayerTurn = true; 	    		
		    		//refreshData();
		    		
		    		TileOPlayPage.getGrid().aTileIsSelected = false;
					TileOPlayPage.getGrid().aConnectionIsSelected = false;
					TileOPlayPage.getGrid().selectedConnection = null;
					TileOPlayPage.getGrid().selectedTile = null;
		    		
		    		grid.possibleMoves = pMoves;
		    		grid.refreshBoard();
		    		
		    		if (pMoves.isEmpty()){
		    			NoPossibleMovesPopOut npm = new NoPossibleMovesPopOut();
						npm.setVisible(true);			
						refreshData();
		    		}
		    		else {
		    			SelectTilePlayPopOut stpop = new SelectTilePlayPopOut();
			    		stpop.setVisible(true);
			    		rollDieOpen = true;
		    		}
	    		}
	    		else{
	    			errorLbl.setText("Can't roll. Lmao.");
	    		}
	    		refreshData();
	    	}
	    });
	    
	    gameModeLbl = new JLabel("Game.Mode");
	    
	    numberLbl = new JLabel("5");
	    numberLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
	    
	    rollLbl = new JLabel("You rolled a..");
	    rollLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
//	    p1InactivityLbl.setText("Player1 inactivity:");
//	    
//	    p2InactivityLbl.setText("Player 2 inactivity:");
//	    
//	    p3InactivityLbl.setText("Player 3 inactivity:");
//	    
//	    p4InactivityLbl.setText("Player 4 inactivity:");
	    GroupLayout gl_contentPane = new GroupLayout(contentPane);
	    gl_contentPane.setHorizontalGroup(
	    	gl_contentPane.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_contentPane.createSequentialGroup()
	    			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addGap(30)
	    					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
	    						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	    							.addComponent(p2InactivityLbl)
	    							.addComponent(p1InactivityLbl)
	    							.addComponent(p3InactivityLbl)
	    							.addComponent(p4InactivityLbl))
	    						.addGroup(gl_contentPane.createSequentialGroup()
	    							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	    								.addComponent(saveBtn)
	    								.addComponent(btnRollDie))
	    							.addGap(15))))
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addGap(22)
	    					.addComponent(errorLbl)
	    					.addGap(18)
	    					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	    						.addComponent(playerTurnLbl)
	    						.addComponent(gameModeLbl)
	    						.addComponent(modeLbl)))
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addGap(179)
	    					.addComponent(rollLbl))
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addGap(232)
	    					.addComponent(numberLbl)))
	    			.addContainerGap(121, Short.MAX_VALUE))
	    );
	    gl_contentPane.setVerticalGroup(
	    	gl_contentPane.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_contentPane.createSequentialGroup()
	    			.addGap(25)
	    			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addComponent(errorLbl)
	    					.addGap(22))
	    				.addComponent(playerTurnLbl))
	    			.addGap(34)
	    			.addComponent(modeLbl)
	    			.addGap(29)
	    			.addComponent(gameModeLbl)
	    			.addGap(18)
	    			.addComponent(btnRollDie)
	    			.addGap(104)
	    			.addComponent(rollLbl)
	    			.addGap(18)
	    			.addComponent(numberLbl)
	    			.addGap(29)
	    			.addComponent(p1InactivityLbl)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(p2InactivityLbl)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(p3InactivityLbl)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(p4InactivityLbl)
	    			.addPreferredGap(ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
	    			.addComponent(saveBtn)
	    			.addGap(85))
	    );
	    contentPane.setLayout(gl_contentPane);

	    getContentPane().add(splitPane);
	}
		
	public static void refreshData(){
		TileO tileO = TileOApplication.getTileO();
		if (tileO.hasGames() && !(tileO.getCurrentGame().getMode() == Mode.DESIGN)){
			Game currentGame = tileO.getCurrentGame();
			Game.Mode currentMode = currentGame.getMode();
			Player player = currentGame.getCurrentPlayer();						
			currentPlayerNb = player.getNumber();
			
			grid.setGame(currentGame);
			try {
				pmc.load(tileO.indexOfGame(currentGame));
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			grid.setVisible(true);
			grid.setSize(700, 720);
			
			modeLbl.setText("CurrentMode: " + pmc.getModeFullName());
			gameModeLbl.setText("CurrentGameMode: " + currentGame.getModeFullName());
			
			if (pmc.getMode() == PlayController.Mode.Move && rollDieOpen){
				rollLbl.setVisible(true);
				numberLbl.setVisible(true);
				numberLbl.setText(Integer.toString(currentGame.getCurrentRolledNumber()));
			}
			else {
				rollLbl.setVisible(false);
				numberLbl.setVisible(false);
			}
			
			
			switch (player.getColor()){
				case RED:
					playerTurnLbl.setForeground(Color.RED);
					break;
				case BLUE:
					playerTurnLbl.setForeground(Color.BLUE);
					break;
				case GREEN:
					playerTurnLbl.setForeground(Color.GREEN);
					break;
				case YELLOW:
					playerTurnLbl.setForeground(Color.YELLOW);
					break;
			}
			playerTurnLbl.setText("It is currently player " + currentPlayerNb + "'s turn.");

			
//			p1InactivityLbl.setText("Player 1 inactivity: " + currentGame.getPlayer(0).getTurnsUntilActive());
//			p2InactivityLbl.setText("Player 2 inactivity: " + currentGame.getPlayer(1).getTurnsUntilActive());
//			p3InactivityLbl.setText("Player 3 inactivity: " + currentGame.getPlayer(2).getTurnsUntilActive());
//			p4InactivityLbl.setText("Player 4 inactivity: " + currentGame.getPlayer(3).getTurnsUntilActive());

			
			switch (currentMode){
				case GAME_ROLLDIEACTIONCARD:
					rollPO.setVisible(true);
					break;
				case GAME_CONNECTTILESACTIONCARD:
					addConnectionPO.setVisible(true);
					break;
				case GAME_REMOVECONNECTIONACTIONCARD:
					removeConnectionPO.setVisible(true);
					break;
				case GAME_TELEPORTACTIONCARD:
					TeleportPopOut teleportPO = new TeleportPopOut();
					teleportPO.setVisible(true);
					break;
				case GAME_LOSETURNACTIONCARD:
					loseTurnPO.setVisible(true);
					break;
				case GAME_REVEALACTIONCARD:
					revealPO.setVisible(true);
					break;
					

				case GAME_REVEALACTIONTILESACTIONCARD:
					revealTilesPO.setVisible(true);
					break;
				case GAME_LOSETURNRANDOMLYACTIONCARD:
					LoseTurnRandomlyPopOut loseTurnRandomlyPO = new LoseTurnRandomlyPopOut();
					loseTurnRandomlyPO.setVisible(true);
					break;
				case GAME_TURNACTIONTILESINACTIVEACTIONCARD:
					turnInactivePO.setVisible(true);
					break;
				case GAME_WINTILEHINTACTIONCARD:
					winHintPO.setVisible(true);
					break;
				case GAME_SENDBACKTOSTARTACTIONCARD:
					sendStartPO.setVisible(true);
					SendBackToStartPopOut.refreshComboxBox(TileOApplication.getTileO().getCurrentGame().numberOfPlayers());
					break;
					
				case GAME_TELEPORTOTHERACTIONCARD:
					teleportOtherPO.setVisible(true);
					TeleportOtherPopOut.refreshComboBox(TileOApplication.getTileO().getCurrentGame().numberOfPlayers());
					break;
				case GAME_WON:
					GameWonPopOut gameWonPO = new GameWonPopOut();
					gameWonPO.setVisible(true);
				default:
			}
		}		
	}
	

	
	public static TilePanelPlay getGrid(){
		return grid;
	}
}
