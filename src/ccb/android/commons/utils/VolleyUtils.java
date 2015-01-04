package ccb.android.commons.utils;

import ccb.android.commons.networks.BaseParmas;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

public class VolleyUtils {

	public static StringRequest getPostRequest(String url,final BaseParmas parmas,Listener<String> listener,ErrorListener errorListener){
		StringRequest req = new StringRequest(Method.POST, url, listener, errorListener){
			protected java.util.Map<String,String> getParams() throws com.android.volley.AuthFailureError {
				if ( parmas != null ){
					return parmas.getParmas();
				}
				return null;
			};
		};
		return req;
	}
}
