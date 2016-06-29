package ui;

public class FrameCenter {
	public static String nowuser="";
	public static String nowfile="";
	public static String nowversion="";
	public static MainFrame mainframe;
	public static void setframe(int n)
	{
		if (n==0)
		{
			newFrame newframe=new newFrame();
		}
		if (n==1)
		{
			mainframe=new MainFrame();
		}
		if (n==2)
		{
			SignFrame signframe=new SignFrame();
		}
		if (n==3)
		{
			newfile mynewfile=new newfile();
		}
	}

}
