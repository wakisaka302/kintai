//ファイルチューザーからym(年月の値)をとってくる

package kintai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSV {
	private static String attendance_filepath;
	private static String c_filepath;
	private DbOperation db;
	
	CSV(){
		attendance_filepath = "";
		c_filepath = "";
		db = new DbOperation();
	}
	
	
	public void csvAttendance_dataReader() throws Exception {
		File file = new File(attendance_filepath);
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
		String line;
		int count = 0;
		String id="";
		
		
		
		while ((line = br.readLine()) != null) {
			String[] s = line.split(",");
			if(count==0) {//idをとってくる
				id = s[0];
			} else {//出退勤情報をとってくる(id以外の行をとってくる)
				//ファイルチューザーからym(年月の値)をとってくる
				//年、月、日をつなぐときはハイフン「-」で
				String day = FileChooser.ym+"-"+s[0];
				System.out.println(day);
				
				if(s.length>2) {  //出退勤両方記載されている場合
					db.attendance_dataInsert(id, day, s[1],s[2]);
				} else {  //出退勤両方記載されていない場合
					db.attendance_dataInsert(id, day);
				}
			}

//			if(count>0) {
//				db.dbGoodsInsert(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]));				
//			}
			count++;
		}
		br.close();
		} catch (IOException ex) {
		ex.printStackTrace();
		}

	}
	
	
//	public void csvCompanyReader() {
//		File file = new File(c_filepath);
//		try (BufferedReader br = new BufferedReader(new FileReader(file))){
//		String line;
//		int count = 0;
//		while ((line = br.readLine()) != null) {
//			String[] s = line.split(",");
//			if(count>0) {
//				db.dbCompanyInsert(Integer.parseInt(s[0]), Integer.parseInt(s[1]), s[2]);
//			}
//			count++;
//		
//			//System.out.println(line);
//		}
//		br.close();
//		} catch (IOException ex) {
//		ex.printStackTrace();
//		}
//
//	}
	
	
	


}
