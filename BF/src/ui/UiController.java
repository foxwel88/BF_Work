package ui;

public class UiController {
	public static String nowuser="";
	public static String nowfile="";
	public static String nowversion="";
	public static MainFrame mainframe;
	public static void setframe(int n)
	{
		if (n==0)
		{
			LoginFrame newframe=new LoginFrame();
		}
		if (n==1)
		{
			mainframe=new MainFrame();
		}
		if (n==2)
		{
			RegisterFrame signframe=new RegisterFrame();
		}
		if (n==3)
		{
			NewFileFrame mynewfile=new NewFileFrame();
		}
		if (n==4)
		{
			ModifyFrame mymodifyframe=new ModifyFrame();
		}
	}
}
