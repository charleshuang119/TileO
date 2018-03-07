package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.controller.PlayController;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class RollDiePopOut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private PlayController pmc = TileOPlayPage.pmc;
	private JLabel lblYouHaveDrawn;
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public RollDiePopOut() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);
		init();
	}
	
	private void init(){
		setTitle("Extra Turn Action Card");
		setResizable(false);
		setBounds(500, 200, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblYouHaveDrawn = new JLabel("You have drawn the Extra Turn Action Card, please click");
		}
		
		JLabel lblClickOnThe = new JLabel("on the \"Roll Die\" button to roll the die to take another turn.");
		{
			cancelButton = new JButton("Roll Die");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
//					TileOPlayPage.rollDieOpen = true;
					pmc.playRollDieActionCard();
					TileOPlayPage.btnRollDie.doClick();
					TileOPlayPage.refreshData();
					TileOPlayPage.setError("");
					TileOPlayPage.getGrid().aTileIsSelected = false;
					TileOPlayPage.getGrid().aConnectionIsSelected = false;
					TileOPlayPage.getGrid().selectedConnection = null;
					TileOPlayPage.getGrid().selectedTile = null;
					close();
				}
			});
			cancelButton.setActionCommand("Roll Die");
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(42)
							.addComponent(lblYouHaveDrawn))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(31)
							.addComponent(lblClickOnThe))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(173)
							.addComponent(cancelButton)))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addComponent(lblYouHaveDrawn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblClickOnThe)
					.addPreferredGap(ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
					.addComponent(cancelButton)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	
	
	public void close() { 
		this.setVisible(false);
//	    this.dispose();
	}
}
