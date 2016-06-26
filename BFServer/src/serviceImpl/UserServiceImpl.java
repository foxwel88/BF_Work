package serviceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import service.UserService;

public class UserServiceImpl implements UserService{
	
	int num;
	String[] namelist=new String[256];
	String[] passwordlist=new String[256];
	
	public void readfile()
	{
		File myfile=new File("user.txt");
		num=0;
		Scanner myscan = null;
		try {
			myscan=new Scanner(myfile);
			String myline="";
			while ((myline=myscan.nextLine())!= null)
			{
				String[] temp=myline.split(" ");
				namelist[num]=temp[0];
				passwordlist[num]=temp[1];
				++num;
			}
			myscan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("new file has been found!");
		}catch (NoSuchElementException e)
		{
			myscan.close();
			return;
			
		}
	}

	public void writefile(String s)
	{
		File myfile=new File("user.txt");
		try {
			PrintWriter mywriter = new PrintWriter(new FileWriter(myfile,true));
			
			mywriter.println(s);
			mywriter.flush();
			mywriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writenewfile()
	{
		File myfile=new File("user.txt");
		try {
			PrintWriter mywriter = new PrintWriter(new FileWriter(myfile));
			for (int i=0;i<num;++i)
			{
				mywriter.println(namelist[i]+" "+passwordlist[i]);
				mywriter.flush();
			}
			mywriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public boolean login(String username, String password) throws RemoteException {
		readfile();
		for (int i=0;i<num;++i)
		{
			if (namelist[i].equals(username))
			{
				if (passwordlist[i].equals(password))
				{
					return true;
				}else return false;
			}
		}
		return false;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}
	
	@Override
	public boolean signup(String username,String password) throws RemoteException
	{
		readfile();
		for (int i=0;i<num;++i)
		{
			if (namelist[i].equals(username))
			{
				return false;
			}
		}
		writefile(username+" "+password);
		return true;
	}
	@Override
	public boolean modify(String username,String oldpassword,String newpassword) throws RemoteException
	{
		
		readfile();
		for (int i=0;i<num;++i)
		{
			if (namelist[i].equals(username))
			{
				if (passwordlist[i].equals(oldpassword))
				{
					passwordlist[i]=newpassword;
					writenewfile();
					return true;
				}else return false;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws RemoteException
	{
		UserServiceImpl test=new UserServiceImpl();
		/*
		System.out.println(test.login("tony","tony123"));
		System.out.println(test.signup("tony","tony123"));

		System.out.println(test.signup("tony1","tony1123"));

		System.out.println(test.signup("tony","tony1233"));

		System.out.println(test.login("tony","tony1223"));
		System.out.println(test.num);
		*/
		System.out.println(test.modify("tony", "tony123", "papap"));
	}
}
