//ファイルチューザーからym(年月の値)をとってくる

package kintai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSV {
	private String attendance_filepath;
	//	private static String c_filepath;
	private DbOperation db;
	private FileChooser fc;
	private String YearMonth;
	final Logger logger = Logger.getLogger("SampleLogging");



	//コンストラクタ(引数：ファイルパス)
	CSV(String filepath){
		fc = new FileChooser();
		attendance_filepath = filepath;
		db = new DbOperation();

		try {
			// 出力ファイルを指定する
			FileHandler fh = new FileHandler("SampleLog.log");
			logger.addHandler(fh);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}


	//コンストラクタ(引数：ファイルパス、年月)
	CSV(String filepath, String ym){
		fc = new FileChooser();
		attendance_filepath = filepath;
		YearMonth =  ym;
		db = new DbOperation();

		try {
			// 出力ファイルを指定する
			FileHandler fh = new FileHandler("SampleLog.log");
			logger.addHandler(fh);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	//CSVファイルから読み込み、DBに登録するメソッド
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
				if(count==0) {//１行目のidをとってくる
					id = s[0];
				} else {//２行目以降の出退勤データをとってくる
					if(DateEception(s[0],count)) {
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


	//CSVファイルから読み込み、エラーを探すメソッド
	public boolean csvAttendance_data_error_finder() throws Exception {
		int errorCount=0;
		File file = new File(attendance_filepath);
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			int count = -1;
			String id="";
			while ((line = br.readLine()) != null) {
				count++;
				String[] s = line.split(",");
				if(count==0) {//１行目のidをとってくる
					id = s[0];
				} else {//２行目以降の出退勤データをとってくる
					//日付判別
					if(DateEception(s[0],count)) {
					} else {
						errorCount++;
					}				

					//退勤判別
					if(no_Leaving_Exception(count,s.length)) {
					} else {
						errorCount++;
					}

					//出勤判別
					if(s.length>=3) {
						if(no_Attendance_Exception(s[1],s[2],count)) {
							if(isTimeType(s[1]) && isTimeType(s[2])){
							} else {
								errorCount++;
							}							
						} else {
							errorCount++;
						}
					}
				}
			}
			br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		//errorCountにより、エラー判別
		if(errorCount!=0) {
			return false;  //エラーあり
		} else {
			return true;  //エラーなし
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
	public boolean DateEception(String s, int count) {
		if(s.equals(String.valueOf(count))) {
			return true;
		} else {
			System.out.println("日付が正しくありません");
			logger.log(Level.WARNING, "warning:日付が正しくありません　"+(count+1)+"行目:"+count+"日");
			return false;
		}
	}


	//退勤時間が入力されているか判別するメソッド
	public boolean no_Leaving_Exception(int day, int length) {
		if(length==2) {
			System.out.println(day+"日：LeavingTimeが未入力です");
			logger.log(Level.WARNING, "warning:退勤時間が未入力です　"+(day+1)+"行目:"+day+"日");
			return false;
		} else {
			return true;
		}
	}


	//出勤時間が入力されているか判別するメソッド
	public boolean no_Attendance_Exception(String s1, String s2, int day) {
		if(!(s2.equals(""))) {
			if(s1.equals("")) {
				System.out.println(day+"日：AttendanceTimeがに未入力です");
				logger.log(Level.WARNING, "warning:出勤時間が未入力です　"+(day+1)+"行目:"+day+"日");
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}


	//出退勤時間が正しいものか判別するメソッド(Time型判別)
	public static boolean isTimeType(String value) {
		if ( value == null || "".equals(value) )
			return false;
		Pattern p = Pattern.compile("^([0-1][0-9]|[2][0-3]):[0-5][0-9]$");
		Pattern p2 = Pattern.compile("^([1-9]):[0-5][0-9]$");
		Matcher m = p.matcher(value);
		Matcher m2 = p2.matcher(value);
		if ( m.find() ) {
			return true;
		} else if(m2.find()) {
			return true;
		} else {
			System.out.println("不適切な値です："+ value);
			return false;
		}
	}	






	//
	//
	//	//CSVファイルから読み込むメソッド
	//	public void csvAttendance_dataReader() throws Exception {
	//		File file = new File(attendance_filepath);
	//		//		FileInputStream is = new FileInputStream(file);
	//		//        InputStreamReader in = new InputStreamReader(is, "UTF-8");
	//		try (BufferedReader br = new BufferedReader(new FileReader(file))){
	//			String line;
	//			int count = -1;
	//			String id="";
	//			while ((line = br.readLine()) != null) {
	//				count++;
	//				String[] s = line.split(",");
	//				if(count==0) {//idをとってくる
	//					id = s[0];
	//				} else {
	//					if(DateEception(s)) {
	//					} else {
	//						String day = YearMonth+"-"+count;
	//						db.attendance_dataInsert(id, day);
	//						continue;
	//					}
	//					String day = YearMonth+"-"+s[0];
	//					if(s.length>2) {  //出退勤両方記載されている場合
	//						db.attendance_dataInsert(id, day, s[1],s[2]);
	//					} else {  //出退勤両方記載されていない場合
	//						db.attendance_dataInsert(id, day);
	//					}
	//				}
	//			}
	//			br.close();
	//		} catch (IOException ex) {
	//			ex.printStackTrace();
	//		}
	//	}
	//
	//
	//	//CSVからidだけを読み取るメソッド
	//	public String csvAttendance_data_id_only_Reader() throws Exception {
	//		File file = new File(attendance_filepath);
	//		//		FileInputStream is = new FileInputStream(file);
	//		//        InputStreamReader in = new InputStreamReader(is, "UTF-8");
	//		String id="";
	//		try (BufferedReader br = new BufferedReader(new FileReader(file))){
	//			String line;
	//			int count = -1;
	//			while ((line = br.readLine()) != null) {
	//				count++;
	//				String[] s = line.split(",");
	//				if(count==0) {//idをとってくる
	//					id = s[0];
	//				} else {
	//					break;
	//				}
	//			}
	//			br.close();
	//		} catch (IOException ex) {
	//			ex.printStackTrace();
	//		}
	//		return id;
	//	}
	//
	//
	//
	//	//日付エラー判別メソッド
	//	public boolean DateEception(String[] s) {
	//		if(!s[0].matches("[0-9]{1,2}")) {
	//			System.out.println("日付が数値以外です");
	//			return false;
	//		} else if(Integer.parseInt(s[0])<1 || 32<Integer.parseInt(s[0])) {
	//			System.out.println("日付が正しくありません");
	//			return false;
	//		} else {
	////			System.out.println("日付は正しいです");
	//			return true;
	//		}
	//	}
	//
	//
	//	//出退勤時間エラー判別メソッド
	//	public boolean ALexception(String[] s) {
	//		//出退勤時間エラー判別
	//		if(!(s[1].matches("[0-9]{1,2}"))) {
	//			System.out.println("出勤時間が数値以外です");
	//			return false;
	//		} else if(Integer.parseInt(s[1])<0 || 25<Integer.parseInt(s[1])) {
	//			System.out.println("出勤時間が正しくありません");
	//			return false;
	//		} else if(!(s[2].matches("[0-9]{1,2}"))){
	//			System.out.println("退勤時間が数値以外です");
	//			return false;
	//		} else if(Integer.parseInt(s[2])<0 || 49<Integer.parseInt(s[2])) {
	//			System.out.println("退勤時間が正しくありません");
	//			return false;
	//		} else {
	//			System.out.println("出退勤問題ありません");
	//			return true;
	//		}	
	//	}







}
