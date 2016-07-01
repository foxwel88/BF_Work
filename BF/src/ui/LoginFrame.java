package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import rmi.RemoteHelper;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Component;
import java.awt.Color;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	JFrame myframe;
	private JButton button;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {

		setResizable(false);
		myframe=this;

		setTitle("Welcome!");
		setBounds(100, 100, 368, 205);
		setLocation(455, 250);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon bg=new ImageIcon("gg1.jpg");
		JLabel bglabel=new JLabel(bg);
		bglabel.setBounds(0, 0, bg.getIconWidth(),bg.getIconHeight());
		this.getLayeredPane().add(bglabel,new Integer(Integer.MIN_VALUE));
		JPanel mypanel=(JPanel)this.getContentPane();
		mypanel.setOpaque(false);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.setFont(new Font("Chalkboard", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String flag="";
				try 
				{
					flag=RemoteHelper.getInstance().getUserService().login(textField.getText(),passwordField.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,flag, "Log in", JOptionPane.PLAIN_MESSAGE);
				if (flag.equals("Login Successful!"))
				{
					UiController.nowuser=textField.getText();
					myframe.dispose();
					UiController.setframe(1);
				}else
				if (flag.equals("Check your password please..."))
				{
					passwordField.setText("");
				}else
				{
					textField.setText("");	
					passwordField.setText("");
				}
			}
		});
		btnNewButton.setBounds(45, 120, 125, 37);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Chalkboard", Font.PLAIN, 17));
		passwordField.setBounds(157, 75, 160, 30);
		contentPane.add(passwordField);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		textField.setBounds(157, 31, 160, 30);
		contentPane.add(textField);
		textField.setColumns(10);

		textField.setOpaque(false);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Chalkboard", Font.PLAIN, 20));
		lblUserName.setBounds(45, 38, 100, 16);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Chalkboard", Font.PLAIN, 20));
		lblPassword.setBounds(45, 79, 92, 16);
		lblPassword.setOpaque(false);
		contentPane.add(lblPassword);
		
		button = new JButton("I am new");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//myframe.dispose();
				boolean flag;
				
				UiController.setframe(2);
			}
		});
		button.setFont(new Font("Chalkboard", Font.PLAIN, 18));
		button.setBounds(187, 120, 125, 37);
		contentPane.add(button);
		this.setVisible(true);
		
	}
}
