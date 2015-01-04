package ccb.android.commons.networks;

import java.util.HashMap;
import java.util.Map;

public class BaseParmas {

	Map<String, String> mParmas;
	
	public BaseParmas(){
		mParmas = new HashMap<String, String>();
	}
	
	public void add(String key,String v){
		mParmas.put(key, v);
	}
	public String get(String key,String v){
		return mParmas.get(key);
	}
	public Map<String, String> getParmas(){
		return mParmas;
	}
}
