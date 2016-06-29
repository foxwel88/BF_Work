package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import rmi.RemoteHelper;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class newfile extends JFrame {

	private JPanel contentPane;
	private JTextField textField;



	/**
	 * Create the frame.
	 */
	public newfile() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 288, 137);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(53, 37, 180, 30);
		contentPane.add(textField);
		
		JLabel lblYour = new JLabel("Your file name");
		lblYour.setFont(new Font("Chalkboard", Font.PLAIN, 18));
		lblYour.setBounds(84, 13, 124, 16);
		contentPane.add(lblYour);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameCenter.nowfile=textField.getText();

				if (!FrameCenter.nowfile.equals(""))
				{
					FrameCenter.nowversion="";
					FrameCenter.mainframe.setTitle(FrameCenter.nowuser+" - "+FrameCenter.nowfile);//(FrameCenter.nowfile);
					boolean flag;
					try {
						flag=RemoteHelper.getInstance().getIOService().writeFile(FrameCenter.mainframe.textArea.getText(), FrameCenter.nowuser, FrameCenter.nowfile);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					FrameCenter.mainframe.solveversion();
				}
				dispose();
			}
		});
		btnOk.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		btnOk.setBounds(84, 71, 125, 30);
		contentPane.add(btnOk);
		this.setVisible(true);
	}
	

}
