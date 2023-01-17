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



	//コンストラクタ(引数：ファイルパス)
	CSV(String filepath){
		fc = new FileChooser();
		attendance_filepath = filepath;
		db = new DbOperation();
	}


	//コンストラクタ(引数：ファイルパス、年月)
	CSV(String filepath, String ym){
		fc = new FileChooser();
		attendance_filepath = filepath;
		YearMonth =  ym;
		db = new DbOperation();
	}



	//CSVファイルから読み込むメソッド
	public void csvAttendance_dataReader() throws Exception {
		File file = new File(attendance_filepath);
		//		FileInputStream is = new FileInputStream(file);
		//        InputStreamReader in = new InputStreamReader(is, "UTF-8");
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			int count = -1;
			String id="";
			while ((line = br.readLine()) != null) {
				count++;
				String[] s = line.split(",");
				if(count==0) {//idをとってくる
					id = s[0];
				} else {
					if(DateEception(s)) {
					} else {
						String day = YearMonth+"-"+count;
						db.attendance_dataInsert(id, day);
						continue;
					}
					String day = YearMonth+"-"+s[0];
					if(s.length>2) {  //出退勤両方記載されている場合
						db.attendance_dataInsert(id, day, s[1],s[2]);
					} else {  //出退勤両方記載されていない場合
						db.attendance_dataInsert(id, day);
					}
				}
			}
			br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


	//CSVからidだけを読み取るメソッド
	public String csvAttendance_data_id_only_Reader() throws Exception {
		File file = new File(attendance_filepath);
		//		FileInputStream is = new FileInputStream(file);
		//        InputStreamReader in = new InputStreamReader(is, "UTF-8");
		String id="";
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			int count = -1;
			while ((line = br.readLine()) != null) {
				count++;
				String[] s = line.split(",");
				if(count==0) {//idをとってくる
					id = s[0];
				} else {
					break;
				}
			}
			br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return id;
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
//			System.out.println("日付は正しいです");
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







}
