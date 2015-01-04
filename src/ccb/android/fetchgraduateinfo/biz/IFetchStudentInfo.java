package ccb.android.fetchgraduateinfo.biz;

import ccb.android.fetchgraduateinfo.pojos.StudentInfo;

public interface IFetchStudentInfo {

	/**
	 * @param name xingming
	 * @param number xuehao
	 * @return
	 */
	void fetch(String name,String number,FetchCallback c);
	
	public interface FetchCallback{
		void onFetch(StudentInfo info,Exception e);
	}
}
