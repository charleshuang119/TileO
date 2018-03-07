package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.DesignModeController;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayModeController;
import ca.mcgill.ecse223.tileo.model.Connection;
import ca.mcgill.ecse223.tileo.model.Tile;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RemoveConnectionActionCardPopOut extends JFrame {
	
	public void close() { 
		this.setVisible(false);
	    this.dispose();
	}
	
	Connection chosenConnection=null;
	String error;
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RemoveConnectionActionCardPopOut frame = new RemoveConnectionActionCardPopOut();
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
	public RemoveConnectionActionCardPopOut() {
		setResizable(false);
		setAlwaysOnTop(true);
		setTitle("Remove Connection Action Card");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblYouHaveDrawn = new JLabel("You have drawn a Remove Connection Action Card,");
		lblYouHaveDrawn.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		JLabel lblSelectAConnection = new JLabel("select a connection piece on the board and click on the ");
		lblSelectAConnection.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		JButton btnRemoveConnection = new JButton("Remove Connection");
		btnRemoveConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayModeController pmc = new PlayModeController();
				if(TileOPlayPage.getGrid().aConnectionIsSelected){
					chosenConnection = TileOPlayPage.getGrid().selectedConnection;
					try 
					{						
						pmc.playRemoveConnectionActionCard(chosenConnection);
						TileOPlayPage.refreshData();
						close();
					}
					catch (InvalidInputException e2){
						throw new RuntimeException (e2.getMessage());
					}
				}
				
			}
			
		});
		
		JLabel lblButtonToRemove = new JLabel("\"Remove Connection\" button to remove the selected connection piece.");
		lblButtonToRemove.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(138, Short.MAX_VALUE)
					.addComponent(btnRemoveConnection)
					.addGap(133))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addComponent(lblSelectAConnection)
					.addContainerGap(183, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addComponent(lblButtonToRemove)
					.addContainerGap(21, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(61, Short.MAX_VALUE)
					.addComponent(lblYouHaveDrawn)
					.addGap(58))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblYouHaveDrawn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSelectAConnection)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblButtonToRemove)
					.addPreferredGap(ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
					.addComponent(btnRemoveConnection)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
