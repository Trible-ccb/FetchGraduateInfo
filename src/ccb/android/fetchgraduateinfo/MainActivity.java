package ccb.android.fetchgraduateinfo;

import java.io.IOException;
import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ccb.android.commons.utils.Bog;
import ccb.android.fetchgraduateinfo.biz.FetchGraduateInfoImpl;
import ccb.android.fetchgraduateinfo.biz.IFetchStudentInfo;
import ccb.android.fetchgraduateinfo.biz.IFetchStudentInfo.FetchCallback;
import ccb.android.fetchgraduateinfo.biz.ParseStudentInfoFromXLS;
import ccb.android.fetchgraduateinfo.pojos.StudentInfo;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SaveCallback;

public class MainActivity extends CustomFragmentActivity implements OnClickListener{

	Button mStartFetchBtn,mViewInfosBtn;
	boolean isFetching;
	IFetchStudentInfo mFetchControl;
	volatile int mFetchnum;
	RequestQueue mReqQueue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mReqQueue = Volley.newRequestQueue(this);
		mFetchControl = new FetchGraduateInfoImpl(mReqQueue);
		initView();
	}

	void initView(){
		mStartFetchBtn = (Button) findViewById(R.id.start_fetch);
		mViewInfosBtn = (Button) findViewById(R.id.view_infos);
		mStartFetchBtn.setOnClickListener(this);
		mViewInfosBtn.setOnClickListener(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	void doFetch() throws IOException{ 
		String name = "黄金凤";
		String number = "201421030317";
//		清水河
		String file1 = "qshStdInfo.XLS";
//		沙河
		String file2 = "shStdInfo.XLS";
		List<String[]> info1 = ParseStudentInfoFromXLS.parse(getAssets().open(file1));
		List<String[]> info2 = ParseStudentInfoFromXLS.parse(getAssets().open(file2));
		info1.addAll(info2);
		
		mFetchnum = 0;
		
		for ( String[] s : info1 ){
			name = s[2];
			number = s[1];
			if ( !number.startsWith("2") ){
				continue;
			}
			mStartFetchBtn.setEnabled(false);
			mFetchControl.fetch(name, number,new FetchCallback() {
				@Override
				public void onFetch(StudentInfo info, Exception e) {
					mStartFetchBtn.setEnabled(true);
					if ( e != null || info == null ){
						Bog.toast("exc"+e+"info = "+info);
						return;
					}
					info.saveInBackground(new SaveCallback() {
						@Override
						public void done(AVException arg0) {
							if ( arg0 == null ){
								mFetchnum++;
								setTitle(""+mFetchnum,R.color.blue_qq);
							} else {
								Bog.toast(arg0.getMessage());
							}
						}
					});
				}
			});
		}
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start_fetch:
			try {
				doFetch();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case R.id.view_infos:
			simpleDisplayActivity(ViewStudentInfoActivity.class);
			break;
		default:
			break;
		}
	}

}
