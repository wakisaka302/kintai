package kintai;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

//import practice_db.InfoData;

public class DbOperation {

	String url = "jdbc:postgresql://localhost:5432/postgres" ;	//DBのURL
	String user = "postgres" ;		//DBのユーザー名
	String password = "postgrestest" ;	//DBのパスワード


	//出退勤をDBに挿入するメソッド(引数４つ)
	public void attendance_dataInsert(String id, String day,String startTime, String finishTime) throws Exception {
		String sql = "Insert into attendance_data values (?,?,?,?)" ;
		try (Connection con=DriverManager.getConnection(url, user, password ) ;
				PreparedStatement pstmt = con.prepareStatement(sql); ) {
		Date sqlDate = Date.valueOf(day);
			String sst1 = startTime + ":00";
			String fft1 = finishTime + ":00";
			Time sst = Time.valueOf(sst1);
			Time fft = Time.valueOf(fft1);
			pstmt.setInt(1,Integer.parseInt(id));
			pstmt.setDate(2, sqlDate);
			pstmt.setTime(3,sst);
			pstmt.setTime(4,fft);
			pstmt.execute();
		} catch ( SQLException e ) {
			e.printStackTrace() ;
		}
	}


	
	//出退勤をDBに挿入するメソッド(引数２つ)
	public void attendance_dataInsert(String id, String day) throws Exception {
		String sql = "Insert into attendance_data values (?,?,?,?)" ;
		try (Connection con=DriverManager.getConnection(url, user, password ) ;
				PreparedStatement pstmt = con.prepareStatement(sql); ) {
			Date sqlDate = Date.valueOf(day);
			pstmt.setInt(1,Integer.parseInt(id));
			pstmt.setDate(2,sqlDate);
			pstmt.setTime(3,null);
			pstmt.setTime(4,null);
			pstmt.execute();
		} catch ( SQLException e ) {
			e.printStackTrace() ;
		}
	}






	


	public ArrayList<EmployeeData> dbGet() {
		Connection con=null;
		Statement stmt=null;
		ResultSet result= null;

		ArrayList<EmployeeData>list=new ArrayList<>();

		String sql = "select * from employee_data" ;
		// PostgreSQL に接続(資源付き try 文の場合)
		try {
			con=DriverManager.getConnection(url, user, password ) ;
			PreparedStatement pstmt = con.prepareStatement(sql); 


			result = pstmt.executeQuery();
			while(result.next()) {//実行結果の取得
				int col1=result.getInt(1);
				int col2=result.getInt(2);
				String col3=result.getString(3);
				String col4=result.getString(4);
				list.add(new EmployeeData(col1,col2,col3,col4));


			}
		} catch ( SQLException e ) {
			e.printStackTrace() ;
		}finally {
			try {
				if(result!= null) result.close();
				if(stmt !=null) stmt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}

		}
		return list;
	}
	//	public void dbInsert(int kihon,String seibetu ,String name) {
	//		Connection conn = null;
	//		PreparedStatement ps = null;
	//		String sql = "INSERT INTO employee_data(employe_number, basic_salary, sex, name) "
	//				+ "SELECT MAX(employe_number)+1, ?, ?, ? FROM employee_data";
	//		try {
	//			conn = DriverManager.getConnection(url, user, password);
	//			// conn.setAutoCommit(false); 
	//
	//			ps = conn.prepareStatement(sql);
	//			ps.setInt(1, kihon);
	//			ps.setString(2, seibetu);
	//			ps.setString(3, name);
	//			ps.execute();
	//			System.out.println(name+"を追加しました");
	//
	//		} catch (Exception ex) {
	//			//例外発生時の処理
	//			ex.printStackTrace();  //エラー内容をコンソールに出力する
	//
	//		} finally {
	//			try {
	//				if(ps != null) ps.close();
	//
	//				if(conn != null) conn.close();
	//			}catch(Exception e) {
	//				e.printStackTrace();
	//			}
	//		}
	//	}
	public void dbInsert(int bangou,int kihon,String seibetu ,String name) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO employee_data values(?, ?, ?, ?)";
		try {
			conn = DriverManager.getConnection(url, user, password);
			// conn.setAutoCommit(false); 

			ps = conn.prepareStatement(sql);
			ps.setInt(1, bangou);
			ps.setInt(2, kihon);
			ps.setString(3, seibetu);
			ps.setString(4, name);
			ps.execute();
			System.out.println(name+"を追加しました");

		} catch (Exception ex) {
			//例外発生時の処理
			ex.printStackTrace();  //エラー内容をコンソールに出力する

		} finally {
			try {
				if(ps != null) ps.close();

				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void dbDelete(Object object) {
		Connection conn = null;
		//データベースにアクセスできように接続すること
		PreparedStatement ps = null;
		//① SQL文を受け取って解析し、値があればいつでも実行できる状態にします。
		//　　② SQL文に必要な値をセットします。
		// 　　③ SQL文を実行します。
		String sql = "DELETE FROM employee_data WHERE employe_number = ?";
		try {
			conn = DriverManager.getConnection(url, user, password);

			ps = conn.prepareStatement(sql);
			ps.setInt(1, (int) object);
			int i = ps.executeUpdate();//前処理済みのINSERT文，UPDATE文，およびDELETE文を実行した場合、戻り値で更新行数が返却されます。
			if(i ==1) {
				System.out.println(object+"を削除しました");
			}else {
				System.out.println("削除する対象が存在しません");
			}
		}catch (Exception ex) {
			ex.printStackTrace(); 
		}finally {
			try {
				if(ps != null) ps.close();

				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void dbAttendanceDelete(Object object) {
		Connection conn = null;
		//データベースにアクセスできように接続すること
		PreparedStatement ps = null;
		//① SQL文を受け取って解析し、値があればいつでも実行できる状態にします。
		//　　② SQL文に必要な値をセットします。
		// 　　③ SQL文を実行します。
		String sql = "DELETE FROM attendance_data WHERE employee_id = ?";
		try {
			conn = DriverManager.getConnection(url, user, password);

			ps = conn.prepareStatement(sql);
			ps.setInt(1, (int) object);
			int i = ps.executeUpdate();//前処理済みのINSERT文，UPDATE文，およびDELETE文を実行した場合、戻り値で更新行数が返却されます。
			if(i >=1) {
				System.out.println(object+"を削除しました");
			}else {
				System.out.println("削除する対象が存在しません");
			}
		}catch (Exception ex) {
			ex.printStackTrace(); 
		}finally {
			try {
				if(ps != null) ps.close();

				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public  int IdMaxGet() {
		Connection con=null;
		Statement stmt=null;
		ResultSet result= null;
		int maxid = 0;

		String sql = "SELECT MAX(employe_number) FROM employee_data" ;
		// PostgreSQL に接続(資源付き try 文の場合)
		try {
			con=DriverManager.getConnection(url, user, password ) ;
			PreparedStatement pstmt = con.prepareStatement(sql); 


			result = pstmt.executeQuery();
			while(result.next()) {//実行結果の取得
				maxid = result.getInt(1);


			}
		} catch ( SQLException e ) {
			e.printStackTrace() ;
		}finally {
			try {
				if(result!= null) result.close();
				if(stmt !=null) stmt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}

		}
		return maxid;
	}

	//社員名を取得
	public ArrayList<AData> dbGetEmployeeData() {

		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;

		ArrayList<AData> list = new ArrayList<AData>();


		String sql = "SELECT * FROM employee_data";

		try {
			/// PostgreSQL に接続
			con = DriverManager.getConnection ( url, user, password );

			// SQL を実行するためのインスタンスを生成
			stmt = con.createStatement();

			// SQL の実行結果を格納する
			result = stmt.executeQuery ( sql );

			//InfoDataのインスタンスを生成し、ArrayListに格納
			while ( result.next() ) {
				int col1 = result.getInt (1) ; 
				int col2 = result.getInt (2) ; 
				String col3 =result.getString (3) ; 
				String col4 = result.getString (4) ;

				AData d = new AData(col1,col2,col3,col4);
				list.add(d);
				//list.add(new InfoData(col1,col2,col3));
			}
		} catch ( SQLException e ){
			e.printStackTrace() ;

		}finally{
			try {
				////クローズ処理クローズ処理
				if(con != null) con.close();
				if(stmt != null) stmt.close();
				if(result != null) result.close();
			}catch(Exception e) {
				e.printStackTrace();

			}
		}
		return list;
	}

	//年月を取得
		public ArrayList<String> dbGetYearMonth(int id) {
			
			Connection con = null;
			Statement stmt = null;
			ResultSet result = null;

			ArrayList<String> list = new ArrayList<String>();


			String sql = "SELECT DISTINCT date_trunc('month', date) as yearmonth from attendance_data where employee_id = "+ id + "order by yearmonth desc";

			try {
				/// PostgreSQL に接続
				con = DriverManager.getConnection ( url, user, password );

				// SQL を実行するためのインスタンスを生成
				stmt = con.createStatement();

				// SQL の実行結果を格納する
				result = stmt.executeQuery ( sql );

				//InfoDataのインスタンスを生成し、ArrayListに格納
				while ( result.next() ) {
					Date col1 = result.getDate (1) ; 
					String str = new SimpleDateFormat("yyyy-MM").format(col1);

					list.add(str);
					//list.add(new InfoData(col1,col2,col3));
				}
			} catch ( SQLException e ){
				
				e.printStackTrace() ;

			}finally{
				try {
					////クローズ処理クローズ処理
					if(con != null) con.close();
					if(stmt != null) stmt.close();
					if(result != null) result.close();
				}catch(Exception e) {
					e.printStackTrace();

				}
			}
			return list;
			
		}

	//選択された社員名・年月より勤務表を表示
	public ArrayList<AData> dbGetWorkSchedule(int id,Date m) {

		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;

		ArrayList<AData> display = new ArrayList<AData>();

		//Date sqlDate= Date.valueOf((String) month);

		String sql = "SELECT date,attendance_at_work,leaving_work From attendance_data\n"
				+ "WHERE date_trunc('month', date) = Date '" + m + "'\n"
				+ "and employee_id =" + id;

		try {
			/// PostgreSQL に接続
			con = DriverManager.getConnection ( url, user, password );

			// SQL を実行するためのインスタンスを生成
			stmt = con.createStatement();

			// SQL の実行結果を格納する
			result = stmt.executeQuery ( sql );

			//インスタンスを生成し、ArrayListに格納
			while ( result.next() ) {
				Date col1 = result.getDate (1) ; 
				Time col2 =result.getTime (2) ; 
				Time col3 = result.getTime (3) ;

				AData d = new AData(col1,col2,col3);
				display.add(d);
				//list.add(new InfoData(col1,col2,col3));
			}
		} catch ( SQLException e ){
			e.printStackTrace() ;

		}finally{
			try {
				////クローズ処理クローズ処理
				if(con != null) con.close();
				if(stmt != null) stmt.close();
				if(result != null) result.close();
			}catch(Exception e) {
				e.printStackTrace();

			}
		}

		return display;
	}
	{
}

}
