package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SavePopOut extends JFrame {

	private JPanel contentPane;
	
	public void close() { 
		this.setVisible(false);
	    this.dispose();
	}
	
	/**
	 * Create the frame.
	 */
	public SavePopOut() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 450, 300);
		contentPane = new JPanel();
		setAlwaysOnTop(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblGameDesignSuccessfully = new JLabel("Game successfully saved!");
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TileOPlayPage.setError("");
				TileOPlayPage.getGrid().aTileIsSelected = false;
				TileOPlayPage.getGrid().aConnectionIsSelected = false;
				TileOPlayPage.getGrid().selectedConnection = null;
				TileOPlayPage.getGrid().selectedTile = null;
				close();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(145, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnOk)
							.addGap(177))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblGameDesignSuccessfully)
							.addGap(134))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblGameDesignSuccessfully)
					.addPreferredGap(ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
					.addComponent(btnOk)
					.addGap(37))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
