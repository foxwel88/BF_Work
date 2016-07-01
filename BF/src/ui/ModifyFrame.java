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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rmi.RemoteHelper;

import java.awt.Canvas;

public class ModifyFrame extends JFrame {

	private JPanel contentPane;
	JFrame myframe;
	private JPasswordField passwordField;
	private JPasswordField textField;
	private JLabel lblConfirmPassword;
	private JPasswordField passwordField_1;

	/**
	 * Create the frame.
	 */
	public ModifyFrame() {
		setResizable(false);
		myframe=this;
		setTitle("Modify password");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 239, 357);
		setLocation(520, 200);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("modify");
		btnNewButton.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (passwordField.getText().equals(passwordField_1.getText()))
				{
					String flag ="";
					try {
						flag=RemoteHelper.getInstance().getUserService().modify(UiController.nowuser, textField.getText(),passwordField.getText());
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					JOptionPane.showMessageDialog(null,flag, "Sign up", JOptionPane.PLAIN_MESSAGE);
					if (flag.equals("Modify your password Successful!"))
					{
						myframe.dispose();
					}else
					{
						textField.setText("");
						passwordField.setText("");
						passwordField_1.setText("");
					}
				}else
				{
					JOptionPane.showMessageDialog(null,"Plase check your password!", "Sign up", JOptionPane.PLAIN_MESSAGE);
					textField.setText("");
					passwordField.setText("");
					passwordField_1.setText("");
				}
			}
		});
		btnNewButton.setBounds(57, 277, 125, 37);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(27, 232, 180, 30);
		contentPane.add(passwordField);
		
		JLabel lblUserName = new JLabel("Old Password");
		lblUserName.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		lblUserName.setBounds(27, 67, 106, 16);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("New Password");
		lblPassword.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		lblPassword.setBounds(27, 134, 158, 16);
		contentPane.add(lblPassword);
		
		lblConfirmPassword = new JLabel("Confirm New Password");
		lblConfirmPassword.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		lblConfirmPassword.setBounds(27, 204, 180, 16);
		contentPane.add(lblConfirmPassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(27, 162, 180, 30);
		contentPane.add(passwordField_1);
		
		JLabel lblGetStartWith = new JLabel("Modify your password");
		lblGetStartWith.setFont(new Font("Chalkboard", Font.PLAIN, 20));
		lblGetStartWith.setBounds(17, 18, 202, 37);
		contentPane.add(lblGetStartWith);
		
		textField = new JPasswordField();
		textField.setBounds(27, 95, 180, 30);
		contentPane.add(textField);
				this.setVisible(true);
		
	}
}
