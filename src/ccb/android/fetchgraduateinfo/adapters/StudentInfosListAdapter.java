package ccb.android.fetchgraduateinfo.adapters;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ccb.android.commons.managers.SDCardManager;
import ccb.android.commons.utils.ImageUtil;
import ccb.android.fetchgraduateinfo.R;
import ccb.android.fetchgraduateinfo.pojos.StudentInfo;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class StudentInfosListAdapter extends BaseAdapter {

	RequestQueue mQueue ;
	BitmapCache mCache;
	Context mContext;
	LayoutInflater mInflater;
	List<StudentInfo> infos;
	ImageLoader mloader;
	
	public StudentInfosListAdapter(Context c){
		mContext = c;
		mInflater = LayoutInflater.from(c);
		mQueue = Volley.newRequestQueue(c);
		mCache = new BitmapCache();
		mloader = new ImageLoader(mQueue, mCache);
	}
	
	public void setData(List<StudentInfo> data){
		infos = data;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return infos == null ? 0 : infos.size();
	}

	@Override
	public Object getItem(int position) {
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null;
		if ( convertView == null ){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.adapter_view_std_infos_item, null);
			holder.imageView = (NetworkImageView) convertView.findViewById(R.id.std_photo);
			holder.name = (TextView) convertView.findViewById(R.id.std_name);
			holder.academy = (TextView) convertView.findViewById(R.id.std_academy);
			holder.major = (TextView) convertView.findViewById(R.id.std_major);
			holder.number = (TextView) convertView.findViewById(R.id.std_number);
			holder.teacher = (TextView) convertView.findViewById(R.id.std_teacher);
			holder.ID = (TextView) convertView.findViewById(R.id.std_ID);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		StudentInfo info = (StudentInfo) getItem(position);
		holder.name.setText(info.getName());
		holder.academy.setText(info.getAcademy());
		holder.number.setText(info.getNumber());
		holder.major.setText(info.getMajor());
		holder.teacher.setText(info.getTeacher());
		holder.ID.setText(info.getID());
		holder.imageView.setErrorImageResId(R.drawable.ic_launcher);
		holder.imageView.setDefaultImageResId(R.drawable.ic_launcher);
		holder.imageView.setImageUrl(info.getPhotoUrl(), mloader);
		return convertView;
	}

	public class BitmapCache implements ImageCache {    
        private LruCache<String, Bitmap> mCache;    
            
        public BitmapCache() {    
            int maxSize = 4 * 1024 * 1024;    
            mCache = new LruCache<String, Bitmap>(maxSize) {    
                @Override    
                protected int sizeOf(String key, Bitmap value) {    
                    return value.getRowBytes() * value.getHeight();    
                }    
                    
            };    
        }    
        
        @Override    
        public Bitmap getBitmap(String url) {    
            return mCache.get(url);    
//            return ImageUtil.getScaleOptionImageByScaleOfWinWidth(
//            		mContext, 
//            		SDCardManager.getInstance().getFileOf(
//            				SDCardManager.PATH_IMAGECACHE, url), 1f);
        }    
        
        @Override    
        public void putBitmap(String url, Bitmap bitmap) {    
            mCache.put(url, bitmap);
//            try {
//				SDCardManager.compressImage(bitmap, 500, 
//						new FileOutputStream(
//								SDCardManager.getInstance().getFileOf(
//										SDCardManager.PATH_IMAGECACHE, url)));
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
        }    
        
    }
	class ViewHolder {
		NetworkImageView imageView;
		TextView name,number,academy,major,teacher,ID;
	}
}
