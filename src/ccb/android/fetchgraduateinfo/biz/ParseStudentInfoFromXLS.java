package ccb.android.fetchgraduateinfo.biz;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ParseStudentInfoFromXLS {
	public static List<String[]> parse(File file) {    
	    List<String[]> excelValueList = new ArrayList<String[]>();    
	    if (file.exists() && file.canRead() ) {    
	        Workbook workbook = null;    
	        try {    
	            workbook = Workbook.getWorkbook(file);   
	            Sheet sheet = workbook.getSheet(0);    
	            int row = sheet.getRows();    
	            int col = sheet.getColumns();    
	            for (int r = 0; r < row; r++) {    
	                String[] rowValue = new String[col];    
	                for (int c = 0; c < col; c++) {    
	                    rowValue[c] = sheet.getCell(c, r).getContents() != null ? sheet    
	                            .getCell(c, r).getContents()    
	                            : "";    
	                }    
	                excelValueList.add(rowValue);    
	            }    
	        } catch (BiffException e) {    
	            // TODO Auto-generated catch block    
	            e.printStackTrace();    
	        } catch (IOException e) {    
	            // TODO Auto-generated catch block    
	            e.printStackTrace();    
	        } finally {    
	            if (workbook != null) {    
	                workbook.close();    
	            }    
	        }    
	    }    
	    return excelValueList;    
	} 
	public static List<String[]> parse(InputStream is) {    
	    List<String[]> excelValueList = new ArrayList<String[]>();    
        Workbook workbook = null;    
        try {    
            workbook = Workbook.getWorkbook(is);   
            Sheet sheet = workbook.getSheet(0);    
            int row = sheet.getRows();    
            int col = sheet.getColumns();    
            for (int r = 0; r < row; r++) {    
                String[] rowValue = new String[col];    
                for (int c = 0; c < col; c++) {    
                    rowValue[c] = sheet.getCell(c, r).getContents() != null ? sheet    
                            .getCell(c, r).getContents()    
                            : "";    
                }    
                excelValueList.add(rowValue);    
            }    
        } catch (BiffException e) {    
            // TODO Auto-generated catch block    
            e.printStackTrace();    
        } catch (IOException e) {    
            // TODO Auto-generated catch block    
            e.printStackTrace();    
        } finally {    
            if (workbook != null) {    
                workbook.close();    
            }    
        }    
	    return excelValueList;    
	} 
}
