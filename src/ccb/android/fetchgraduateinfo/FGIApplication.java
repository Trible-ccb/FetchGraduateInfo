package ccb.android.fetchgraduateinfo;

import android.app.Application;
import ccb.android.commons.managers.PrefManager;
import ccb.android.commons.managers.SDCardManager;
import ccb.android.commons.utils.Bog;
import ccb.android.fetchgraduateinfo.pojos.StudentInfo;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;

public class FGIApplication extends Application {

	public final static boolean LOG_DEBUG = true;
	public final static boolean ENV_DEBUG = true;
	
	final String AVAPPID = "hqudsf4ime95zbdg1gqjes95xdwgjc87u7jdef9g3snci9qs";
	final String AVAPPKEY = "oxsokgc5nyq4507vzjdr9n7abtop9xhsf7n66x55ljknkqp3";
	final String AVMASTERKEY = "upwzudz16xico9dmjnyiq0qpl9ottwmpphn7ypiqfsji8baj";
	
	@Override
	public void onCreate() {
		super.onCreate();
		initSystem();
	}
	
	void initSystem(){
		
		String name = getString(R.string.app_name);
		Bog.init(this, name);
		PrefManager.initPrefManager(this, name);
		SDCardManager.initStorageWithClassicPaths(getApplicationContext(), name);
		initAVOS();
	}
	
	void initAVOS(){
		AVObject.registerSubclass(StudentInfo.class);
		AVOSCloud.initialize(this, AVAPPID, AVAPPKEY);
	}
}
