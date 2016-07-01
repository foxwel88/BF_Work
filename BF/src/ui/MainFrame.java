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
import javax.swing.ScrollPaneConstants;

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
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import javax.swing.event.ListSelectionEvent;


public class MainFrame extends JFrame 
{
	JTextArea textArea;
	JTextArea inarea;
	JTextArea resultarea;
	JFrame myframe;
	JList list;
	private UndoManager undo;
	
	public void clean()
	{
		inarea.setText("");
		resultarea.setText("");
	}
	
	public MainFrame() 
	{
		undo=new UndoManager();
		setResizable(false);
		UiController.nowversion="";
		UiController.nowfile="";
		myframe=this;
		this.setTitle("BF Client Welcome "+UiController.nowuser);
		this.getContentPane().setForeground(Color.WHITE);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		
		JMenu runMenu=new JMenu("Run");
		runMenu.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try 
				{
					resultarea.setText(RemoteHelper.getInstance().getExecuteService().execute(textArea.getText(), inarea.getText()+" "));
				} catch (RemoteException e1) 
				{
					e1.printStackTrace();
				}
				runMenu.setSelected(false);
			}
		});
		
		menuBar.add(runMenu);
		
		JMenu accountMenu = new JMenu("Acount");

		menuBar.add(accountMenu);
		
				
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		menuBar.add(panel);
		
		JLabel lblVersionList = new JLabel("Version List");
		lblVersionList.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		lblVersionList.setBounds(446, 2, 108, 19);
		panel.add(lblVersionList);
		
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new MenuItemActionListener());
		
		JMenuItem undoItem = new JMenuItem("Undo");
		editMenu.add(undoItem);
		JMenuItem redoItem = new JMenuItem("Redo");
		editMenu.add(redoItem);
		undoItem.addActionListener(new MenuItemActionListener());
		redoItem.addActionListener(new MenuItemActionListener());
		
		JMenuItem modifyItem = new JMenuItem("Modify password");
		accountMenu.add(modifyItem);
		JMenuItem logoutItem = new JMenuItem("Log out");
		accountMenu.add(logoutItem);
		modifyItem.addActionListener(new MenuItemActionListener());
		logoutItem.addActionListener(new MenuItemActionListener());
		
		getContentPane().setLayout(null);
		

		
		textArea = new JTextArea();
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		textArea.setText("Coding from here ...");
		textArea.setBounds(128, 18, 641, 377);
		textArea.setMargin(new Insets(10, 10, 10, 10));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if (textArea.getText().equals("Coding from here ..."))
				{
					textArea.setText("");
				}
			}
		});
		textArea.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (textArea.getText().equals("Coding from here ..."))
				{
					textArea.setText("");
				}
			}
		});
		textArea.getDocument().addUndoableEditListener(new UndoableEditListener()
		{
            public void undoableEditHappened(UndoableEditEvent e) {
            	undo.addEdit(e.getEdit());
            }
		});
		textArea.setLineWrap(true);
		JScrollPane myscroll=new JScrollPane(textArea);
		myscroll.setBounds(-2, -2, 641, 395);
		
		//JScrollPane qs=new JScrollPane(textArea);
		//qs.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//qs.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(myscroll);
		
		
		
		inarea=new JTextArea();
		inarea.setBounds(128, 415, 320, 127);
		inarea.setMargin(new Insets(10, 10, 10, 10));
		inarea.setBackground(Color.LIGHT_GRAY);
		//getContentPane().add(inarea);
		
		inarea.setLineWrap(true);
		JScrollPane myscroll1=new JScrollPane(inarea);
		myscroll1.setBounds(-2, 415, 320, 127);
		getContentPane().add(myscroll1);
		
		resultarea = new JTextArea();
		resultarea.setMargin(new Insets(10, 10, 10, 10));
		resultarea.setBackground(Color.LIGHT_GRAY);
		resultarea.setBounds(454, 415, 317, 127);
		//getContentPane().add(resultarea);
		
		resultarea.setLineWrap(true);
		JScrollPane myscroll2=new JScrollPane(resultarea);
		myscroll2.setBounds(322, 415, 317, 127);
		getContentPane().add(myscroll2);
		
		JLabel lblNewLabel = new JLabel("Result");
		lblNewLabel.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		lblNewLabel.setBounds(330, 395, 69, 19);
		getContentPane().add(lblNewLabel);
				
		JLabel lblPutIn = new JLabel("Input");
		lblPutIn.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		lblPutIn.setBounds(6, 395, 69, 19);
		getContentPane().add(lblPutIn);

		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				String version=(String)list.getSelectedValue();
				if (version!=null)
		 		{
		 			UiController.nowversion=version;
		 			solveopen();
		 		}
		 	}
		});
		list.setFont(new Font("Chalkboard", Font.PLAIN, 15));
		list.setBounds(639, 0, 161, 542);
		getContentPane().add(list);

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 586);
		setLocation(255, 90);
		setVisible(true);
	}

	class MenuItemActionListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String cmd = e.getActionCommand();
			if (cmd.equals("New"))
			{
				String[] temp={};
				list.setListData(temp);
				textArea.setText("Coding from here ...");
				clean();
				UiController.nowfile="";
				UiController.nowversion="";
				myframe.setTitle(UiController.nowuser+" - new file");
			}
			if (cmd.equals("Open")) 
			{
				clean();
				String[] filelist = null;
				try {
					filelist = RemoteHelper.getInstance().getIOService().readFileList(UiController.nowuser);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (filelist!=null)
				{
					OpenFrame myopenframe=new OpenFrame(filelist);
				}
			}
			if (cmd.equals("Save")) 
			{
				if (UiController.nowfile.equals(""))
				{
					UiController.setframe(3);
				}else
				{
					myframe.setTitle(UiController.nowuser+" - "+UiController.nowfile);
					boolean flag;
					try 
					{
						flag=RemoteHelper.getInstance().getIOService().writeFile(textArea.getText(), UiController.nowuser, UiController.nowfile);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					solveversion();
				}
			}
			if (cmd.equals("Log out"))
			{
				UiController.setframe(0);
				dispose();
			}
			if (cmd.equals("Modify password"))
			{
				UiController.setframe(4);
			}
			if (cmd.equals("Undo"))
			{
	            if (undo.canUndo())
	            {
	            	undo.undo();
	            }
			}
			if (cmd.equals("Redo"))
			{
				if (undo.canRedo())
				{
	                undo.redo();
				}
			}
		}
	}
	
	public void solveopen()
	{
		try {
			textArea.setText(RemoteHelper.getInstance().getIOService().readFile(UiController.nowuser, UiController.nowfile, UiController.nowversion));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle(UiController.nowuser+" - "+UiController.nowfile);
	}
	
	public void solveversion()
	{
		String[] versionlist=null;
		try
		{
			versionlist=RemoteHelper.getInstance().getIOService().readversionlist(UiController.nowuser, UiController.nowfile);
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
