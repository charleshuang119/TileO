package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;


import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoseTurnWarningPopOut extends JFrame {

	private JPanel contentPane;
	private PlayController pmc = TileOPlayPage.pmc;

	
	public void close() { 
		this.setVisible(false);
//	    this.dispose();
	}

	/**
	 * Create the frame.
	 */
	public LoseTurnWarningPopOut() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(500, 200, 450, 300);
		contentPane = new JPanel();
		setAlwaysOnTop(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblYouveJustDrawn = new JLabel("You've just drawn a Lose Your Next Turn Action Card!");
		
		JLabel lblYouWillLose = new JLabel("You will lose your next turn.");
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pmc.playLoseTurnActionCard();
					TileOPlayPage.setError("");
					TileOPlayPage.getGrid().aTileIsSelected = false;
					TileOPlayPage.getGrid().aConnectionIsSelected = false;
					TileOPlayPage.getGrid().selectedConnection = null;
					TileOPlayPage.getGrid().selectedTile = null;					
				} 
				catch (InvalidInputException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				TileOPlayPage.refreshData();
				close();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(57)
					.addComponent(lblYouveJustDrawn)
					.addContainerGap(46, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(136, Short.MAX_VALUE)
					.addComponent(lblYouWillLose)
					.addGap(126))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(188, Short.MAX_VALUE)
					.addComponent(btnOk)
					.addGap(177))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblYouveJustDrawn)
					.addGap(62)
					.addComponent(lblYouWillLose)
					.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
					.addComponent(btnOk)
					.addGap(50))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
