package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.PlayModeController;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.Tile;
import ca.mcgill.ecse223.tileo.model.TileO;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JLabel;

public class TileOPlayPage extends JFrame {

	private static TilePanelPlay grid = new TilePanelPlay(TileOApplication.getTileO().getCurrentGame());
	
	static String currentPlayer;
	
	
	
	
	private JPanel contentPane;
	private static JLabel playerTurnLabel;
	private static JLabel currentModeLabel;
	public static List<Tile> pMoves;
	TilePanelPlay tpp ;
	
	static JButton btnRollDie;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TileOPlayPage frame = new TileOPlayPage();
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
	public TileOPlayPage() {
		setTitle("Play Mode");
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

		
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setSize(1200, 720);
	    splitPane.setDividerSize(0);
	    splitPane.setDividerLocation(710);
	    splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
	    splitPane.setLeftComponent(grid);
	    splitPane.setRightComponent(contentPane);
	    
	    playerTurnLabel = new JLabel("It is currently Player x's turn.");
	    
	    JLabel lblJlabelForErrors = new JLabel("JLabel for Errors");
	    
	    currentModeLabel = new JLabel("JLabel for Mode");
	    
	    JButton btnSave = new JButton("Save");
	    btnSave.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		PlayModeController pmc = new PlayModeController();
	    		pmc.saveGame();
	    		SavePopOut spo = new SavePopOut();
	    		spo.setVisible(true);
	    		refreshData();
	    		
	    	}
	    });
	    
	    btnRollDie = new JButton("Roll Die");
	    btnRollDie.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// TODO: Roll the die
	    		PlayModeController pmc = new PlayModeController();
	    		pMoves = pmc.rollDie();
	    		grid.possibleMoves=pMoves;
	    		
	    		grid.isAPlayerTurn = true;	//added by Li
	    		
	    		SelectTilePlayPopOut stpop = new SelectTilePlayPopOut();
	    		stpop.setVisible(true);
	    		
	    	
	    	}
	    });
	    GroupLayout gl_contentPane = new GroupLayout(contentPane);
	    gl_contentPane.setHorizontalGroup(
	    	gl_contentPane.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_contentPane.createSequentialGroup()
	    			.addContainerGap(403, Short.MAX_VALUE)
	    			.addComponent(btnSave)
	    			.addContainerGap())
	    		.addGroup(gl_contentPane.createSequentialGroup()
	    			.addGap(198)
	    			.addComponent(btnRollDie)
	    			.addContainerGap(198, Short.MAX_VALUE))
	    		.addGroup(gl_contentPane.createSequentialGroup()
	    			.addGap(185)
	    			.addComponent(currentModeLabel)
	    			.addContainerGap(186, Short.MAX_VALUE))
	    		.addGroup(gl_contentPane.createSequentialGroup()
	    			.addGap(183)
	    			.addComponent(lblJlabelForErrors)
	    			.addContainerGap(183, Short.MAX_VALUE))
	    		.addGroup(gl_contentPane.createSequentialGroup()
	    			.addGap(152)
	    			.addComponent(playerTurnLabel)
	    			.addContainerGap(152, Short.MAX_VALUE))
	    );
	    gl_contentPane.setVerticalGroup(
	    	gl_contentPane.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_contentPane.createSequentialGroup()
	    			.addGap(23)
	    			.addComponent(playerTurnLabel)
	    			.addGap(18)
	    			.addComponent(lblJlabelForErrors)
	    			.addGap(18)
	    			.addComponent(currentModeLabel)
	    			.addGap(53)
	    			.addComponent(btnRollDie)
	    			.addPreferredGap(ComponentPlacement.RELATED, 432, Short.MAX_VALUE)
	    			.addComponent(btnSave)
	    			.addContainerGap())
	    );
	    contentPane.setLayout(gl_contentPane);

	    getContentPane().add(splitPane);
	    
	    
	}
		
	public static void refreshData(){
		TileO tileO = TileOApplication.getTileO();
		Game currentGame = tileO.getCurrentGame();
		Game.Mode currentMode = currentGame.getMode();
		Player player = currentGame.getCurrentPlayer();
		
		
		int playerNumber = player.getNumber();
		//int playerNumber = player.getNumber(); //gives error if uncommented
		
		
		grid.setGame(TileOApplication.getTileO().getCurrentGame());
		grid.setVisible(true);
		grid.setSize(700, 720);
		
		playerTurnLabel.setText("It is currently player " + playerNumber + "'s turn.");
		currentModeLabel.setText("CurrentMode: " + currentMode.name());
		
		switch (currentMode){
			case GAME_ROLLDIEACTIONCARD:
				RollDiePopOut rdpo = new RollDiePopOut();
				rdpo.setVisible(true);
				break;
			case GAME_CONNECTTILESACTIONCARD:
				AddConnectionActionCardPopOut acacpo = new AddConnectionActionCardPopOut();
				acacpo.setVisible(true);
				break;
			case GAME_REMOVECONNECTIONACTIONCARD:
				RemoveConnectionActionCardPopOut rcacpo = new RemoveConnectionActionCardPopOut();
				rcacpo.setVisible(true);
				break;
			case GAME_TELEPORTACTIONCARD:
				TeleportPopOut tpo = new TeleportPopOut();
				tpo.setVisible(true);
				break;
			case GAME_WON:
				GameWonPopOut gwpo = new GameWonPopOut();
				gwpo.setVisible(true);
			default:
				
		}
		
		
		
		
	}
	

	public static TilePanelPlay getGrid(){
		return grid;
	}
}
