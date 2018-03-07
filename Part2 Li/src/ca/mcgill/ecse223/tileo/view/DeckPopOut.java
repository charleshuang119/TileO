package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;


import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import java.awt.Font;

public class DeckPopOut extends JFrame {
	
	private DesignModeController dmc = TileODesignPage.dmc;

	private JLabel errorMessage;
	private JPanel contentPane;
	
	private String error = "";
	
	private JLabel createDeckLbl;
	
    private JButton createDeckBtn;
	
    private JLabel connectTilesLbl;
	private JLabel loseTurnLcl;
	private JLabel loseTurnRdmLbl;
	private JLabel removeConnectionLbl;
	private JLabel revealLcl;
	private JLabel revealActionTilesLbl;
	private JLabel rollDieLbl;
	private JLabel sendStartLbl;
	private JLabel teleportLbl;
	private JLabel teleportOtherLbl;
	private JLabel actionTilesInactiveLbl;
	private JLabel winHintLbl;
	
	private JTextField cards[] = new JTextField[12];
	
	private JTextField connectTilesNum;
	private JTextField loseTurnNum;
	private JTextField loseTurnRdmNum;
	private JTextField removeConnectionNum;
	private JTextField revealNum;
	private JTextField revealActionTilesNum;
	private JTextField rollDieNum;
	private JTextField sendStartNum;
	private JTextField teleportNum;
	private JTextField teleportOtherNum;
	private JTextField actionTilesInactiveNum;
	private JTextField winHintNum;
	   
	
	


	public void close() { 
		this.setVisible(false);
		error = "";
		errorMessage.setText(error);
		for (int i=0; i<cards.length; i++){
			cards[i].setText("");
		}
		
	}
	
	/**
	 * Create the frame.
	 */
	public DeckPopOut() {
		setBounds(500, 200, 450, 555);
		contentPane = new JPanel();
		setAlwaysOnTop(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		createDeckBtn = new JButton("Create Deck");
		
		createDeckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {	
				btnCreateActionPerformed(evt); 
			}
		});
		
		
		createDeckLbl = new JLabel("The total number of cards must add up to 32.");
		createDeckLbl.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		
		errorMessage = new JLabel();
//		errorMessage.setText("error is happening");
		errorMessage.setForeground(Color.red);
		
		connectTilesLbl = new JLabel("Connect Tiles Cards:");
		
		loseTurnLcl = new JLabel("Lose Turn Cards:");
		
		loseTurnRdmLbl = new JLabel("Lose Turns Randomly Cards:");
		
		removeConnectionLbl = new JLabel("Remove Connection Cards:");
		
		revealLcl = new JLabel("Reveal Cards:");
		
		revealActionTilesLbl = new JLabel("Reveal Action Tiles Cards:");
		
		rollDieLbl = new JLabel("Roll Die Cards:");
		
		sendStartLbl = new JLabel("Send Back To Start Cards:");
		
		teleportLbl = new JLabel("Teleport Cards:");
		
		teleportOtherLbl = new JLabel("Teleport Another Player Cards:");
		
		actionTilesInactiveLbl = new JLabel("Turn Action Tiles Inactive Cards:");
		
		winHintLbl = new JLabel("Win Tile Hint Cards:");
		
		cards[0] = connectTilesNum = new JTextField();
		connectTilesNum.setColumns(10);
		
		cards[1] = loseTurnNum = new JTextField();
		loseTurnNum.setColumns(10);
		
		cards[2] = loseTurnRdmNum = new JTextField();
		loseTurnRdmNum.setColumns(10);
		
		cards[3] = removeConnectionNum = new JTextField();
		removeConnectionNum.setColumns(10);
		
		cards[4] = revealNum = new JTextField();
		revealNum.setColumns(10);
		
		cards[5] = revealActionTilesNum = new JTextField();
		revealActionTilesNum.setColumns(10);
		
		cards[6] = rollDieNum = new JTextField();
		rollDieNum.setColumns(10);
		
		cards[7] = sendStartNum = new JTextField();
		sendStartNum.setColumns(10);
		
		cards[8] = teleportNum = new JTextField();
		teleportNum.setColumns(10);
		
		cards[9] = teleportOtherNum = new JTextField();
		teleportOtherNum.setColumns(10);
		
		cards[10] = actionTilesInactiveNum = new JTextField();
		actionTilesInactiveNum.setColumns(10);
		
		cards[11] = winHintNum = new JTextField();
		winHintNum.setColumns(10);
		
		JButton cancelBtn = new JButton("Close");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {	
				close();
			}
			
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(65)
					.addComponent(createDeckBtn)
					.addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
					.addComponent(cancelBtn)
					.addGap(82))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(errorMessage)
						.addComponent(createDeckLbl))
					.addContainerGap(64, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(winHintLbl)
						.addComponent(actionTilesInactiveLbl)
						.addComponent(teleportOtherLbl)
						.addComponent(teleportLbl)
						.addComponent(sendStartLbl)
						.addComponent(rollDieLbl)
						.addComponent(revealActionTilesLbl)
						.addComponent(revealLcl)
						.addComponent(removeConnectionLbl)
						.addComponent(loseTurnRdmLbl)
						.addComponent(loseTurnLcl)
						.addComponent(connectTilesLbl))
					.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(connectTilesNum, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(loseTurnNum, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(loseTurnRdmNum, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(removeConnectionNum, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(revealNum, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(revealActionTilesNum, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(rollDieNum, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(sendStartNum, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(teleportNum, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(teleportOtherNum, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(actionTilesInactiveNum, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addComponent(winHintNum, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(createDeckLbl)
					.addGap(17)
					.addComponent(errorMessage)
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(connectTilesNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(connectTilesLbl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(loseTurnNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(loseTurnLcl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(loseTurnRdmNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(loseTurnRdmLbl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(removeConnectionNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(removeConnectionLbl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(revealNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(revealLcl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(revealActionTilesNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(revealActionTilesLbl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rollDieNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rollDieLbl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(sendStartNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(sendStartLbl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(teleportNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(teleportLbl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(teleportOtherNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(teleportOtherLbl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(actionTilesInactiveNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(actionTilesInactiveLbl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(winHintLbl)
						.addComponent(winHintNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(createDeckBtn)
						.addComponent(cancelBtn))
					.addGap(15))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void btnCreateActionPerformed(java.awt.event.ActionEvent evt)  {
		int connectTilesCNum;
		int loseTurnCNum;
		int loseTurnRdmCNum;
		int removeConnectionCNum;
		int revealCNum;
		int revealActionTilesCNum;
		int rollDieCNum;
		int sendStartCNum;
		int teleportCNum;
		int teleportOtherCNum;
		int actionTilesInactiveCNum;
		int winHintCNum;
		int total = 0;
		
		int numbers[] = new int[]{
				connectTilesCNum = 0,
				loseTurnCNum = 0,
				loseTurnRdmCNum = 0,
				removeConnectionCNum = 0,
				revealCNum = 0,
				revealActionTilesCNum = 0,
				rollDieCNum = 0,
				sendStartCNum = 0,
				teleportCNum = 0,
				teleportOtherCNum = 0,
				actionTilesInactiveCNum = 0,
				winHintCNum = 0
			};
	    //error = "";
		try{
			for (int i=0; i<cards.length; i++){
				if (!cards[i].getText().equals("")){
					numbers[i] = Integer.parseInt(cards[i].getText());
					total += numbers[i];
				}
			}
		}
		catch(NumberFormatException e) {
			error ="The number of action cards needs to be a numerical value";
			errorMessage.setText(error);
		}
		if (total != 32){
			error = "The total number of action cards is not 32";
			errorMessage.setText(error);
		}
		error.trim();
		if (error.length() == 0) {
			
			try {
				dmc.createDeck(numbers[0], numbers[1], numbers[2],
						 numbers[3], numbers[4], numbers[5],
						 numbers[6], numbers[7], numbers[8],
						 numbers[9], numbers[10], numbers[11]);
				error = "Deck created!";
				errorMessage.setText(error);
				error = "";

				for (int i=0; i<cards.length; i++){
					cards[i].setText("");
				}
			} 
			catch (InvalidInputException e) {
				error = e.getMessage();
			}
		}
		error = "";
	}
}
	
