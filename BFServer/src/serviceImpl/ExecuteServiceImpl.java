//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;

import service.ExecuteService;

public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 请实现该方法
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
		
		int[] data=new int[256];
		int[] stack=new int[256];
		String res ="";
		int n=-1;
		int ptr=0;
		int m=0;
		for (int i=0;i<code.length();++i)
		{			
			if (code.charAt(i)=='>')
			{
				++ptr;
				continue;
			}
			if (code.charAt(i)=='<')
			{
				--ptr;
				continue;
			}
			if (code.charAt(i)=='+')
			{
				++data[ptr];
				continue;
			}
			if (code.charAt(i)=='-')
			{
				--data[ptr];
				continue;
			}
			if (code.charAt(i)=='.')
			{
				res=res+(char)data[ptr];
				continue;
			}
			if (code.charAt(i)==',')
			{
				data[ptr]=(int)param.charAt(m);
				++m;
				continue;				
			}
			if (code.charAt(i)=='[')
			{
				if (data[ptr]==0)
				{
					while (code.charAt(i)!=']') ++i;
					continue;
				}
				++n;
				stack[n]=i;
			}
			if (code.charAt(i)==']')
			{
				if (data[ptr]==0)
				{
					--n;
					continue;
				}
				i=stack[n];
				continue;
			}
		}
		return res;
	}
}
