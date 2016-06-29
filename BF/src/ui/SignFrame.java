package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rmi.RemoteHelper;

import java.awt.Canvas;

public class SignFrame extends JFrame {

	private JPanel contentPane;
	JFrame myframe;
	private JPasswordField passwordField;
	private JTextField textField;
	private JLabel lblConfirmPassword;
	private JPasswordField passwordField_1;

	/**
	 * Create the frame.
	 */
	public SignFrame() {
	
		myframe=this;
		setTitle("Sign up!");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 245, 420);
		setLocation(520, 200);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Sign up");
		btnNewButton.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (passwordField.getText().equals(passwordField_1.getText()))
				{
					boolean flag = false;
					try {
						flag=RemoteHelper.getInstance().getUserService().signup(textField.getText(), passwordField.getText());
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (flag)
					{
						FrameCenter.nowuser=textField.getText();
						myframe.dispose();
						FrameCenter.setframe(1);
					}else
					{
						passwordField.setText("");
						passwordField_1.setText("");
					}
				}
			}
		});
		btnNewButton.setBounds(58, 318, 125, 37);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(32, 264, 180, 30);
		contentPane.add(passwordField);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		lblUserName.setBounds(32, 99, 83, 16);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Create your password");
		lblPassword.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		lblPassword.setBounds(32, 166, 158, 16);
		contentPane.add(lblPassword);
		
		lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		lblConfirmPassword.setBounds(32, 236, 180, 16);
		contentPane.add(lblConfirmPassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(32, 194, 180, 30);
		contentPane.add(passwordField_1);
		
		JLabel lblGetStartWith = new JLabel("Get start with us!");
		lblGetStartWith.setFont(new Font("Chalkboard", Font.PLAIN, 25));
		lblGetStartWith.setBounds(16, 18, 228, 37);
		contentPane.add(lblGetStartWith);
		
		textField = new JTextField();
		textField.setBounds(32, 127, 180, 30);
		contentPane.add(textField);
		textField.setColumns(10);
				this.setVisible(true);
		
	}
}
