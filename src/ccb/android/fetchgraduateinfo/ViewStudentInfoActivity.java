package ccb.android.fetchgraduateinfo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import ccb.android.commons.utils.Bog;
import ccb.android.commons.utils.ListUtil;
import ccb.android.fetchgraduateinfo.adapters.StudentInfosListAdapter;
import ccb.android.fetchgraduateinfo.pojos.StudentInfo;
import ccb.android.fetchgraduateinfo.pojos.StudentInfo.FieldName;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVQuery.CachePolicy;
import com.avos.avoscloud.FindCallback;


public class ViewStudentInfoActivity extends CustomFragmentActivity {

	ListView mStdInfoListView;
	List<StudentInfo> mStudentInfos;
	StudentInfosListAdapter mAdatper;
	int limit = 1000;
	int currentPage = 0;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_view_std_infos);
		mStudentInfos = new ArrayList<StudentInfo>();
		initView();
		loadInfos();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_view_std_info, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_search:
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	void initView(){
		mStdInfoListView = (ListView) findViewById(R.id.student_info_list);
		mAdatper = new StudentInfosListAdapter(this);
		mStdInfoListView.setAdapter(mAdatper);
	}
	void loadInfos (){
		setTitle("loading...", R.color.blue_qq);
		AVQuery<StudentInfo> query = AVQuery.getQuery(StudentInfo.class);
		query.setCachePolicy(CachePolicy.NETWORK_ELSE_CACHE);
		query.setLimit(limit);
		query.setSkip(mAdatper.getCount());
		query.orderByAscending(FieldName.NAME);
		query.findInBackground(new FindCallback<StudentInfo>() {
			
			@Override
			public void done(List<StudentInfo> arg0, AVException arg1) {
				if( arg1 == null ){
					mStudentInfos.addAll(arg0);
					mAdatper.setData(mStudentInfos);
					if ( ListUtil.isNotEmpty(arg0) ){
						loadInfos();
					}
				} else {
					Bog.toast(arg1.getMessage());
				}
				setTitle("load done:" + mAdatper.getCount(), R.color.blue_qq);
			}
		});
	}
}
