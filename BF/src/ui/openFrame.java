package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class OpenFrame extends JFrame 
{

	private JPanel contentPane;
	private JList list;


	public OpenFrame(String[] filelist) 
	{
		setResizable(false);
		setBounds(490, 210, 288, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYour = new JLabel("Choose your file");
		lblYour.setFont(new Font("Chalkboard", Font.PLAIN, 18));
		lblYour.setBounds(75, 13, 160, 16);
		contentPane.add(lblYour);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (((String)list.getSelectedValue())!=null)
				{
					String myfile=(String)list.getSelectedValue();
					UiController.nowfile=myfile;
					UiController.nowversion="";
					UiController.mainframe.solveopen();
					UiController.mainframe.solveversion();
					dispose();
				}
			}
		});

		btnOk.setFont(new Font("Chalkboard", Font.PLAIN, 16));
		btnOk.setBounds(83, 282, 125, 30);
		contentPane.add(btnOk);
		
		list = new JList();
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		list.setBounds(48, 41, 199, 232);
		list.setListData(filelist);
		JScrollPane myscroll=new JScrollPane(list);
		myscroll.setBounds(48, 41, 199, 229);
		
		contentPane.add(myscroll);
		this.setVisible(true);
	}
}
