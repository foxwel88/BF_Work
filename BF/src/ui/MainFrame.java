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
import javax.swing.JScrollPane;
import java.awt.Choice;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class MainFrame extends JFrame {
	JTextArea textArea;
	private JTextArea inarea;
	private JTextArea resultarea;
	JFrame myframe;
	JList list;
	public MainFrame() {
		FrameCenter.nowversion="";
		FrameCenter.nowfile="";
		// 创建窗体
		myframe=this;
		this.setTitle("BF Client Welcome "+FrameCenter.nowuser);
		this.getContentPane().setForeground(Color.WHITE);

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
		setJMenuBar(menuBar);
		
		JPanel panel = new JPanel();
		menuBar.add(panel);
		panel.setForeground(Color.WHITE);
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		
		JMenu mnNewMenu = new JMenu("Log out");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrameCenter.setframe(0);
				dispose();
			}
		});
		menuBar.add(mnNewMenu);

		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new MenuItemActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
		getContentPane().setLayout(null);
		

		
		inarea=new JTextArea();
		inarea.setBounds(128, 415, 320, 127);
		inarea.setMargin(new Insets(10, 10, 10, 10));
		inarea.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(inarea);
		
		JLabel lblNewLabel = new JLabel("Result");
		lblNewLabel.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		lblNewLabel.setBounds(458, 395, 69, 19);
		getContentPane().add(lblNewLabel);
		
				textArea = new JTextArea();
				textArea.setText("Coding from here ^_^");
				textArea.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if (textArea.getText().equals("Coding from here ^_^"))
						{
							textArea.setText("");
						}
					}
				});
				textArea.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (textArea.getText().equals("Coding from here ^_^"))
						{
							textArea.setText("");
						}
					}
				});
				textArea.setBounds(128, 18, 641, 377);
				textArea.setMargin(new Insets(10, 10, 10, 10));
				textArea.setBackground(Color.LIGHT_GRAY);
				getContentPane().add(textArea);
				
				resultarea = new JTextArea();
				resultarea.setMargin(new Insets(10, 10, 10, 10));
				resultarea.setBackground(Color.LIGHT_GRAY);
				resultarea.setBounds(454, 415, 317, 127);
				getContentPane().add(resultarea);
				
				JLabel lblPutIn = new JLabel("Put in");
				lblPutIn.setFont(new Font("Chalkboard", Font.PLAIN, 16));
				lblPutIn.setBounds(132, 395, 69, 19);
				getContentPane().add(lblPutIn);

				 list = new JList();
				 list.addListSelectionListener(new ListSelectionListener() {
				 	public void valueChanged(ListSelectionEvent e) {
				 		String version=(String)list.getSelectedValue();
				 		if (version!=null)
				 		{
				 			FrameCenter.nowversion=version;
				 			solveopen();
				 		}
				 	}
				 });
				list.setFont(new Font("Chalkboard", Font.PLAIN, 16));
				list.setBounds(0, 18, 128, 524);
				getContentPane().add(list);
				
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(770, 588);
		setLocation(400, 200);
		setVisible(true);
	}

	class MenuItemActionListener implements ActionListener {
		/**
		 * 子菜单响应事件
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("New"))
			{
				list=new JList();
				textArea.setText("Coding from here ^_^");
				FrameCenter.nowfile="";
				FrameCenter.nowversion="";
				myframe.setTitle(FrameCenter.nowuser+" - new file");
			}
			if (cmd.equals("Open")) 
			{
				String[] filelist = null;
				try {
					filelist = RemoteHelper.getInstance().getIOService().readFileList(FrameCenter.nowuser);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (filelist!=null)
				{
					openFrame myopenframe=new openFrame(filelist);
					
				}
			}
			if (cmd.equals("Save")) 
			{
				if (FrameCenter.nowfile.equals(""))
				{
					FrameCenter.setframe(3);
				}else
				{
					myframe.setTitle(FrameCenter.nowuser+" - "+FrameCenter.nowfile);
					boolean flag;
					try {
						flag=RemoteHelper.getInstance().getIOService().writeFile(textArea.getText(), FrameCenter.nowuser, FrameCenter.nowfile);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					solveversion();
				}
			}
			if (cmd.equals("Run")) 
			{
				try {
					resultarea.setText(RemoteHelper.getInstance().getExecuteService().execute(textArea.getText(), inarea.getText()));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	public void solveopen()
	{
		try {
			textArea.setText(RemoteHelper.getInstance().getIOService().readFile(FrameCenter.nowuser, FrameCenter.nowfile, FrameCenter.nowversion));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle(FrameCenter.nowuser+" - "+FrameCenter.nowfile);
	}
	public void solveversion()
	{
		String[] versionlist=null;
		try {
			versionlist=RemoteHelper.getInstance().getIOService().readversionlist(FrameCenter.nowuser, FrameCenter.nowfile);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (versionlist!=null)
		{
			list.setListData(versionlist);
		}
	}

}
