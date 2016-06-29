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
import javax.swing.JList;

public class openFrame extends JFrame {

	private JPanel contentPane;
	private JList list;



	/**
	 * Create the frame.
	 */
	public openFrame(String[] filelist) {
		setResizable(false);
		setBounds(100, 100, 288, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYour = new JLabel("Your file name");
		lblYour.setFont(new Font("Chalkboard", Font.PLAIN, 18));
		lblYour.setBounds(84, 13, 124, 16);
		contentPane.add(lblYour);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((String)list.getSelectedValue())!=null)
				{
					String myfile=(String)list.getSelectedValue();
					FrameCenter.nowfile=myfile;
					FrameCenter.nowversion="";
					FrameCenter.mainframe.solveopen();
					
					FrameCenter.mainframe.solveversion();
					
					dispose();
				}
			}
		});

		btnOk.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		btnOk.setBounds(83, 282, 125, 30);
		contentPane.add(btnOk);
		
		list = new JList();
		list.setBounds(48, 41, 199, 229);
		list.setListData(filelist);
		
		contentPane.add(list);
		this.setVisible(true);
	}
	

}
