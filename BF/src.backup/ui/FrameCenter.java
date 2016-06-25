package ui;

public class FrameCenter {

	public static void setframe(int n)
	{
		if (n==0)
		{
			newFrame newframe=new newFrame();
		}
		if (n==1)
		{
			MainFrame mainframe=new MainFrame();
		}
		if (n==2)
		{
			SignFrame signframe=new SignFrame();
		}
	}
}
