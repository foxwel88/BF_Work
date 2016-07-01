package ui;

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

public class NewFileFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	public NewFileFrame() 
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(490, 210, 288, 137);
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
				UiController.nowfile=textField.getText();

				if (!UiController.nowfile.equals(""))
				{
					UiController.nowversion="";
					UiController.mainframe.setTitle(UiController.nowuser+" - "+UiController.nowfile);//(FrameCenter.nowfile);
					boolean flag;
					try {
						flag=RemoteHelper.getInstance().getIOService().writeFile(UiController.mainframe.textArea.getText(), UiController.nowuser, UiController.nowfile);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					UiController.mainframe.solveversion();
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
