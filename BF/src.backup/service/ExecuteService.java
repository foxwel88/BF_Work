//璇蜂笉瑕佷慨鏀规湰鏂囦欢鍚�
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ExecuteService extends Remote {
	
	/**
	 * 鎴戜滑灏嗛�氳繃姝ゆ柟娉曟祴璇曚綘鐨勮В鏋愬櫒鍔熻兘锛岃涓嶈淇敼鏂规硶鍚嶏紝鍙傛暟绫诲瀷锛岃繑鍥炲弬鏁扮被鍨�
	 * @param code bf婧愪唬鐮�
	 * @return 杩愯缁撴灉
	 * @throws RemoteException
	 */
	public String execute(String code, String param) throws RemoteException;
}
