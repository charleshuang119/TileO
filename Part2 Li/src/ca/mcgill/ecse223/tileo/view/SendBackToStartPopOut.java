package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;
import ca.mcgill.ecse223.tileo.model.Game;
import ca.mcgill.ecse223.tileo.model.Player;
import ca.mcgill.ecse223.tileo.model.TileO;

import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SendBackToStartPopOut extends JFrame {
	

	private JPanel contentPane;
	//public static int numberOfPlayers;
	String error;
	private PlayController pmc = TileOPlayPage.pmc;
	public static JComboBox comboBox_1 = new JComboBox();
		


	/**
	 * Create the frame.
	 */
	public SendBackToStartPopOut() {
		setTitle("Send Back To Start Action Card");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JLabel errorLbl = new JLabel("");
		errorLbl.setForeground(Color.RED);
		
		JLabel lblYouHaveDrawn = new JLabel("You have drawn a Send Back To Start Action Card, please select");
		
		JLabel lblAPleyerTo = new JLabel("a player and click \"Send\" button to send");
		
		JLabel lblThatPlayerBack = new JLabel("that player back to its starting position.");
		
//		JComboBox comboBox_1 = new JComboBox();
		
		
		
//		String[] playerNum = new String[TileOApplication.getTileO().getCurrentGame().numberOfPlayers()];
//		addNums(playerNum, TileOApplication.getTileO().getCurrentGame().numberOfPlayers());
//		comboBox_1.setModel(new DefaultComboBoxModel(playerNum));
			
		
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				error = "";
				TileO tileO = TileOApplication.getTileO();
				Game currentGame = tileO.getCurrentGame();
				Player currentPlayer = currentGame.getCurrentPlayer();
				int number = currentPlayer.getNumber()-1;
				int i = (comboBox_1.getSelectedIndex());
				
				
				
				
//				if(i == number){
//					error = "You cannot send yourself back to your starting position!";
//					
//				}
				
				
				
				error.trim();
				if(error.length()==0){
				
						
						pmc.playSendBackToStartActionCard(i);
						TileOPlayPage.refreshData();

					close();
					error = "";
					errorLbl.setText("");
				}
//				else
//				{
//					errorLbl.setText(error);
//				}
				
				
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblYouHaveDrawn)
							.addGap(16))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblAPleyerTo)
							.addGap(89))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblThatPlayerBack)
							.addGap(89))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(192))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnSend)
							.addGap(178))))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(errorLbl, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(lblYouHaveDrawn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAPleyerTo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblThatPlayerBack)
					.addGap(28)
					.addComponent(errorLbl, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnSend)
					.addGap(31))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public static void addNums(String[] str, int number){
		for (int i = 1; i < number+1 ; i++){
			str[i-1] = String.valueOf(i);			
		}
	}
	public void close() { 
		this.setVisible(false);
	    this.dispose();
	}
	
	public static void refreshComboxBox(int numberOfPlayers){
		String[] playerNum = new String[numberOfPlayers];
		addNums(playerNum, numberOfPlayers);
		comboBox_1.setModel(new DefaultComboBoxModel(playerNum));
	}
}
