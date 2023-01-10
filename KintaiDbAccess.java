package kintai;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;



public class KintaiDbAccess {

	String url = "jdbc:postgresql://localhost:5432/postgres";
	String user = "postgres";
	String password = "postgrestest";

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


	public ArrayList<AData> dbGetAttendanceData() {

		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;

		ArrayList<AData> list = new ArrayList<AData>();


		String sql = "SELECT * FROM attendance_data";

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
				Date col2 = result.getDate (2) ; 
				Time col3 =result.getTime (3) ; 
				Time col4 = result.getTime (4) ;

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


	public void dbGetEmployeeId(String name) {

		String sql = "SELECT employe_number From employee_data WHERE (name = '?')";
		

		try
		(Connection con=DriverManager.getConnection(url, user, password );
				PreparedStatement pstmt = con.prepareStatement(sql); ) {
			//SQL 文の出金額の？の場所にセットする。
			pstmt.setString(1,name); 
			

			//SELECT文を実行する
			pstmt.executeUpdate();


		}catch ( SQLException e ) {
			e.printStackTrace() ;

		}
	}
	
	//勤務表取得
	public void dbGetWorkSchedule(int id) {

		String sql = "SELECT date,attendance_at_work,leaving_work From attendance_data WHERE (employee_id = ?)";

		try
		(Connection con=DriverManager.getConnection(url, user, password );
				PreparedStatement pstmt = con.prepareStatement(sql); ) {
			//SQL 文の出金額の？の場所にセットする。
			pstmt.setInt(1,id); 
			

			//SELECT文を実行する
			pstmt.executeUpdate();


		}catch ( SQLException e ) {
			e.printStackTrace() ;

		}
	}
}
