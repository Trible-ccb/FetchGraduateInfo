package ccb.android.fetchgraduateinfo.pojos;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("StudentInfo")
public class StudentInfo extends AVObject {

	public void setName(String value){
		put(FieldName.NAME, value);
	}
	public String getName(){
		return getString(FieldName.NAME);
	}
	
	public void setNumber(String value){
		put(FieldName.NUMBER, value);
	}
	public String getNumber(){
		return getString(FieldName.NUMBER);
	}
	
	public void setPhotoUrl(String value){
		put(FieldName.PHOTO_URL, value);
	}
	public String getPhotoUrl(){
		return getString(FieldName.PHOTO_URL);
	}
	
	public void setAcademy(String value){
		put(FieldName.ACADEMY, value);
	}
	public String getAcademy(){
		return getString(FieldName.ACADEMY);
	}
	
	public void setMajor(String value){
		put(FieldName.MAJOR, value);
	}
	public String getMajor(){
		return getString(FieldName.MAJOR);
	}
	
	public void setTeacher(String value){
		put(FieldName.TEACHER, value);
	}
	public String getTeacher(){
		return getString(FieldName.TEACHER);
	}
	
	public void setPhone(String value){
		put(FieldName.PHONE, value);
	}
	public String getPhone(){
		return getString(FieldName.PHONE);
	}
	
	public void setAddress(String value){
		put(FieldName.ADDRESS, value);
	}
	public String getAddress(){
		return getString(FieldName.ADDRESS);
	}
	
	public void setID(String value){
		put(FieldName.ID, value);
	}
	public String getID(){
		return getString(FieldName.ID);
	}
	
	public interface FieldName{
		String NAME = "name";
		String NUMBER = "std_number";
		String PHOTO_URL = "photo_url";
		String ACADEMY = "academy";
		String MAJOR = "major";
		String ID = "std_ID";
		String TEACHER = "teacher";
		String ADDRESS = "address";
		String PHONE = "phone";
	}
}
