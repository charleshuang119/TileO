package ca.mcgill.ecse223.tileo.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ErrorPopOut extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ErrorPopOut frame = new ErrorPopOut();
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
	public ErrorPopOut() {
		init();
		refreshData();
	}
	
	private void init(){
		setTitle("You have encountered an error.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnAightsIGet = new JButton("Aights I get it lmao");
		btnAightsIGet.setToolTipText("lmao");
		contentPane.add(btnAightsIGet, BorderLayout.SOUTH);
		
		JLabel lblThisIsAn = new JLabel("This is an error message.");
		lblThisIsAn.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblThisIsAn, BorderLayout.CENTER);
	}
	
	private void refreshData(){
		
	}

}
