package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import rmi.RemoteHelper;
import javax.swing.JScrollBar;
import java.awt.Font;


public class MainFrame extends JFrame {
	private JTextArea textArea;
	private JTextArea inarea;
	private JTextArea resultarea;

	public MainFrame() {
		// 创建窗体
		JFrame frame = new JFrame("BF Client");

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		menuBar.add(fileMenu);
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem runMenuItem = new JMenuItem("Run");
		fileMenu.add(runMenuItem);
		frame.setJMenuBar(menuBar);

		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
		frame.getContentPane().setLayout(null);
		

		
		inarea=new JTextArea();
		inarea.setBounds(0, 334, 359, 127);
		inarea.setMargin(new Insets(10, 10, 10, 10));
		inarea.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(inarea);
		
		JLabel lblNewLabel = new JLabel("Result");
		lblNewLabel.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		lblNewLabel.setBounds(363, 312, 69, 19);
		frame.getContentPane().add(lblNewLabel);
		
				textArea = new JTextArea();
				textArea.setBounds(0, 0, 689, 311);
				textArea.setMargin(new Insets(10, 10, 10, 10));
				textArea.setBackground(Color.LIGHT_GRAY);
				frame.getContentPane().add(textArea);
				
				resultarea = new JTextArea();
				resultarea.setMargin(new Insets(10, 10, 10, 10));
				resultarea.setBackground(Color.LIGHT_GRAY);
				resultarea.setBounds(361, 334, 327, 127);
				frame.getContentPane().add(resultarea);
				
				JLabel lblPutIn = new JLabel("Put in");
				lblPutIn.setFont(new Font("Chalkboard", Font.PLAIN, 16));
				lblPutIn.setBounds(5, 313, 69, 19);
				frame.getContentPane().add(lblPutIn);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(689, 506);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

	class MenuItemActionListener implements ActionListener {
		/**
		 * 子菜单响应事件
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("Open")) {
				textArea.setText("Open");
			} else if (cmd.equals("Save")) {
				textArea.setText("Save");
			} else if (cmd.equals("Run")) {
				try {
					resultarea.setText(RemoteHelper.getInstance().getExecuteService().execute(textArea.getText(), inarea.getText()));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String code = textArea.getText();
			try {
				RemoteHelper.getInstance().getIOService().writeFile(code, "admin", "code");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}
}
