package serviceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

import service.IOService;

public class IOServiceImpl implements IOService{
	
	int num;
	String[] filelist=new String[256];
	
	public String gettime()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(new Date());
	}
	
	public void readuserlist(String username)
	{
		File myfile=new File("_list_"+username+".txt");
		num=0;
		Scanner myscan = null;
		try {
			myscan=new Scanner(myfile);
			String myline="";
			while ((myline=myscan.nextLine())!= null)
			{
				filelist[num]=myline;
				++num;
			}
			myscan.close();
		} catch (FileNotFoundException e) {
			System.out.println("new user:"+username+"'s list file has been found!");
		}catch (NoSuchElementException e)
		{
			myscan.close();
			return;
		}
	}
	
	@Override
	public boolean writeFile(String file, String userId, String fileName) throws RemoteException
	{
		boolean flag=true;
		readuserlist(userId);
		if (num!=0)
		{
			for (int i=0;i<num;++i)
			{
				if (filelist[i].equals(fileName))
				{
					flag=false;
					break;
				}
			}
		}
		if (flag)
		{
			File userf = new File("_list_"+userId+".txt");
			PrintWriter mywriter;
			try {
				mywriter = new PrintWriter(new FileWriter(userf,true));			
				mywriter.println(fileName);
				mywriter.flush();
				mywriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		File f = new File("userfile/"+userId + "_" + fileName+"_"+gettime());
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String readFile(String userId, String fileName, String version) throws RemoteException{
		
	
		String[] versionlist=readversionlist(userId,fileName);
		String name=userId+"_"+fileName+"_";
		if (version.equals(""))
		{
			name=name+versionlist[0];
		}else name=name+version;
		File f = new File("userfile/"+name);
		try {
			Scanner myscanner=new Scanner(f);
			return myscanner.nextLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("User's file can not be found!");
			return "";
		}
	}

	@Override
	public String[] readFileList(String userId) throws RemoteException{
		readuserlist(userId);
		String[] res=new String[num];
		for (int i=0;i<num;++i)
		{
			res[i]=filelist[i];
		}
		return res;
	}
	
	public boolean comp(String s1,String s2)
	{
		for (int i=0;i<s1.length();++i)
		{
			if (s1.charAt(i)<s2.charAt(i)) return true;
			if (s1.charAt(i)>s2.charAt(i)) return false;
		}
		return false;
	}
	@Override
	public String[] readversionlist(String userId, String fileName)throws RemoteException
	{
		File file=new File("userfile");
		File[] templist = file.listFiles();
		int num=0;
		String[] restemp=new String[256];
		for (int i=0;i<templist.length;++i)
		{
			String ss=templist[i].getName();
			String[] sp=ss.split("_");
			if ((sp[0].equals(userId))&&(sp[1].equals(fileName)))
			{
				restemp[num]=sp[2];
				++num;
			}
		}
		String[] res=new String[num];
		for (int i=0;i<num;++i)
		{
			res[i]=restemp[i];
		}
		for (int i=0;i<num-1;++i)
		{
			for (int j=0;j<num-i-1;++j)
			{
				if (comp(res[j],res[j+1]))
				{
					String temp=res[j];
					res[j]=res[j+1];
					res[j+1]=temp;
				}
			}
		}
		return res;
	}
}
