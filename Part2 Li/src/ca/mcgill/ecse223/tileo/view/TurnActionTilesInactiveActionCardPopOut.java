package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.controller.PlayController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TurnActionTilesInactiveActionCardPopOut extends JFrame {

	private JPanel contentPane;
	private PlayController pmc = TileOPlayPage.pmc;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TurnActionTilesInactiveActionCardPopOut frame = new TurnActionTilesInactiveActionCardPopOut();
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
	public TurnActionTilesInactiveActionCardPopOut() {
		setTitle("Turn Action Tiles Inactive Action Card");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblYouDrawnA = new JLabel("You have drawn a TurnActionTilesInactiveActionCard");
		
		JLabel lblAllTilesWill = new JLabel("All tiles will now become inactive");
		
		JLabel lblForTheirRespective = new JLabel(" for their respective time.");
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pmc.playTurnActionTilesInactiveActionCard();
				TileOPlayPage.refreshData();
				close();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblYouDrawnA))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(175)
							.addComponent(btnOk))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(89)
							.addComponent(lblForTheirRespective))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(72)
							.addComponent(lblAllTilesWill)))
					.addContainerGap(8, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblYouDrawnA)
					.addGap(31)
					.addComponent(lblAllTilesWill)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblForTheirRespective)
					.addGap(45)
					.addComponent(btnOk)
					.addContainerGap(66, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public void close() { 
		this.setVisible(false);
	    this.dispose();
	}
}


