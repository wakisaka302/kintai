//ファイルチューザーからym(年月の値)をとってくる

package kintai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSV {
	private String attendance_filepath;
	//	private static String c_filepath;
	private DbOperation db;
	private FileChooser fc;
	private String YearMonth;


	CSV(String filepath, String ym){
		fc = new FileChooser();
		attendance_filepath = filepath;
		//		System.out.println("コンストラクタ"+attendance_filepath);
		//		c_filepath = "";
		YearMonth =  ym;
		db = new DbOperation();
	}


	public void csvAttendance_dataReader() throws Exception {
		//		System.out.println(attendance_filepath);	
		File file = new File(attendance_filepath);
//		FileInputStream is = new FileInputStream(file);
//        InputStreamReader in = new InputStreamReader(is, "UTF-8");
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			int count = -1;
			String id="";

			while ((line = br.readLine()) != null) {
				System.out.println(line);
				count++;
				String[] s = line.split(",");
				if(count==0) {//idをとってくる
					id = s[0];
					//				System.out.println(id);

				} else {//出退勤情報をとってくる(id以外の行をとってくる)
					//ファイルチューザーからym(年月の値)をとってくる
					//年、月、日をつなぐときはハイフン「-」で

					if(DateEception(s)) {
						System.out.println("日付");
					} else {
						String day = YearMonth+"-"+count;
						System.out.println(day);
						db.attendance_dataInsert(id, day);
						continue;
					}

					System.out.println("=======");
					String day = YearMonth+"-"+s[0];
					System.out.println(day);



					if(s.length>2) {  //出退勤両方記載されている場合
						System.out.println("aaaaa");
//						if(ALexception(s)) {
//							System.out.println("a");
							db.attendance_dataInsert(id, day, s[1],s[2]);
//						} else {
//							System.out.println("b");
//							db.attendance_dataInsert(id, day);
//						}
//						System.out.println(count);
							System.out.println("aaaac");
					} else {  //出退勤両方記載されていない場合
						System.out.println("aaaab");
						db.attendance_dataInsert(id, day);
					}
				}
				
				
				System.out.println("------------------");
				
				
				//			if(count>0) {
				//				db.dbGoodsInsert(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3]));				
				//			}
			}
			br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}




	//日付エラー判別メソッド
	public boolean DateEception(String[] s) {
		if(!s[0].matches("[0-9]{1,2}")) {
			System.out.println("日付が数値以外です");
			return false;
		} else if(Integer.parseInt(s[0])<1 || 32<Integer.parseInt(s[0])) {
			System.out.println("日付が正しくありません");
			return false;
		} else {
			System.out.println("日付は正しいです");
			return true;
		}
	}


	//出退勤時間エラー判別メソッド
	public boolean ALexception(String[] s) {
		//出退勤時間エラー判別
		if(!(s[1].matches("[0-9]{1,2}"))) {
			System.out.println("出勤時間が数値以外です");
			return false;
		} else if(Integer.parseInt(s[1])<0 || 25<Integer.parseInt(s[1])) {
			System.out.println("出勤時間が正しくありません");
			return false;
		} else if(!(s[2].matches("[0-9]{1,2}"))){
			System.out.println("退勤時間が数値以外です");
			return false;
		} else if(Integer.parseInt(s[2])<0 || 49<Integer.parseInt(s[2])) {
			System.out.println("退勤時間が正しくありません");
			return false;
		} else {
			System.out.println("出退勤問題ありません");
			return true;
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
