package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.controller.PlayController;
import ca.mcgill.ecse223.tileo.model.Tile;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class NoPossibleMovesPopOut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private PlayController pmc = TileOPlayPage.pmc;
	private JLabel lblLooksLikeYoure;


	public void close() { 
		this.setVisible(false);
//	    this.dispose();
	}
	/**
	 * Create the dialog.
	 */
	public NoPossibleMovesPopOut() {
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Sucks For You!");
		setBounds(500, 200, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblLooksLikeYoure = new JLabel("Looks like you're stuck on a tile :(");
		}
		JLabel lblNewLabel = new JLabel("You will land on your own tile once again");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(81)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(108)
							.addComponent(lblLooksLikeYoure)))
					.addContainerGap(100, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(22)
					.addComponent(lblLooksLikeYoure)
					.addGap(18)
					.addComponent(lblNewLabel)
					.addContainerGap(157, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Feels Bad, Man.");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TileOPlayPage.refreshData();
						TileOPlayPage.setError("");
						Tile cTile = TileOApplication.getTileO().getCurrentGame().getCurrentPlayer().getCurrentTile();
						try {
							pmc.land(cTile);
						} catch (InvalidInputException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
						//(TileOApplication.getTileO().getCurrentGame()).setNextPlayer();						
						close();
						TileOPlayPage.refreshData();
					}
				});
				okButton.setActionCommand("FeelsBad");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
