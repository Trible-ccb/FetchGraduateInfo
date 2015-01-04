package ccb.android.fetchgraduateinfo.biz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ccb.android.commons.networks.BaseParmas;
import ccb.android.commons.utils.VolleyUtils;
import ccb.android.fetchgraduateinfo.pojos.StudentInfo;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class FetchGraduateInfoImpl implements IFetchStudentInfo{

	String URL = "http://gr.uestc.edu.cn/welcome/queryRegistrationState.shtml";
	String PhotoPrefix = "http://gr.uestc.edu.cn/welcome/";
	
	RequestQueue mQuene;
	public FetchGraduateInfoImpl(RequestQueue q){
		mQuene = q;
	}
	
	@Override
	public void fetch(final String name, final String number, final FetchCallback c) {
		BaseParmas p = new BaseParmas();
		p.add("xm", name);
		p.add("hm", number);
		StringRequest req = VolleyUtils.getPostRequest(URL, p,
				new Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						StudentInfo info = null;
						if ( arg0 == null || arg0.indexOf(name) == -1){
						} else {
							String regex = "" +
									"录取学院：</span><span class=\"rightpart\">(.*)</span></li>"
									+"[^.]*录取专业：</span><span class=\"rightpart\">(.*)</span></li>"
									+"[^.]*身份证号：</span>(.*)</li>"
									+"[^.]*录取导师：</span>(.*)</li>"
									+"[^.]*<img src='(.*)'"
									+".*";
							Pattern pattern = Pattern.compile(regex);
							Matcher matcher = pattern.matcher(arg0);
							if ( matcher.find() ){
								info = new StudentInfo();
								int offet = 1;
								info.setAcademy(matcher.group(offet));
								info.setMajor(matcher.group(offet + 1));
								info.setID(matcher.group(offet + 2));
								info.setTeacher(matcher.group(offet + 3));
								info.setPhotoUrl(PhotoPrefix+matcher.group(offet + 4));
								info.setName(name);
								info.setNumber(number);
							}
							
						}
						if ( c != null ){
							c.onFetch(info, null);
						}
					}
				},
				new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						if ( c != null ){
							c.onFetch(null, arg0);
						}
					}
				});
		mQuene.add(req);
	}

}
