package kintai;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

//import practice_db.InfoData;

public class DbOperation {

	String url = "jdbc:postgresql://localhost:5432/postgres" ;	//DBのURL
	String user = "postgres" ;		//DBのユーザー名
	String password = "postgrestest" ;	//DBのパスワード


	//出退勤両方を受け取るコンストラクタ
	public void attendance_dataInsert(String id, String day,String startTime, String finishTime) throws Exception {
		String sql = "Insert into attendance_data values (?,?,?,?)" ;
		try (Connection con=DriverManager.getConnection(url, user, password ) ;
				PreparedStatement pstmt = con.prepareStatement(sql); ) {
			
			
			
			//dayをDate型に変換  startTime,finishTimeをTime型に変換
			
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
			
			
			//SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
			//Date d = f.parse(day);
			
			//SimpleDateFormat g = new SimpleDateFormat("HH:mm");
			//Date st = g.parse(startTime);
			//Date ft = g.parse(finishTime);
			
//			pstmt.setInt(1,Integer.parseInt(id));
//			pstmt.setDate(2,(java.sql.Date)d);
			
			
//			if( !(startTime.equals("") || finishTime.equals(""))) {
//				pstmt.setDate(3,(java.sql.Date) st);
//				pstmt.setDate(4,(java.sql.Date) ft);
//			} else {
//				pstmt.setDate(3, null);
//				pstmt.setDate(4, null);
//			}
//			
//			pstmt.execute();
			
		} catch ( SQLException e ) {
			e.printStackTrace() ;
		}
	}
	
	
	public void attendance_dataInsert(String id, String day) throws Exception {
		String sql = "Insert into attendance_data values (?,?,?,?)" ;
		try (Connection con=DriverManager.getConnection(url, user, password ) ;
				PreparedStatement pstmt = con.prepareStatement(sql); ) {
			
			Date sqlDate = Date.valueOf(day);
//			SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
//			Date d = f.parse(day);
			
			pstmt.setInt(1,Integer.parseInt(id));
			pstmt.setDate(2,sqlDate);
			pstmt.setTime(3,null);
			pstmt.setTime(4,null);
			
			pstmt.execute();

		} catch ( SQLException e ) {
			e.printStackTrace() ;
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
//
//	public void dbCompanyInsert(int id, int m_code, String name) {
//		// SQL 文の作成
//		//プレースホルダを使用して値を後設定
//		//プレースホルダとは「？」の部分のことを指す。
//		String sql = "Insert into comp (c_id,m_code,c_name) values (?,?,?)" ;
//		// PostgreSQL に接続(資源付き try 文の場合)
//		try (Connection con=DriverManager.getConnection(url, user, password ) ;
//				PreparedStatement pstmt = con.prepareStatement(sql); ) {
//			//SQL 文の出金額の？の場所に 3000 をセットする。
//			pstmt.setInt(1,id);
//			pstmt.setInt(2,m_code);
//			pstmt.setString(3,name);
//
//
//			pstmt.execute();
//			//SQL 文を実行し、結果を ResultSet に格納する。
//			//			ResultSet result = pstmt.executeQuery();
//			//			while(result.next()) {//実行結果の取得
//			//				Date col1 = result.getTimestamp(1) ; //日付
//			//				String col2 = result.getString ( 2 ) ; //費目
//			//				String col3 = result.getString ( 3 ) ; //メモ
//			//				int col4 = result.getInt ( 4 ) ; //入金額
//			//				int col5 = result.getInt ( 5 ) ; //出金額
//			//				System.out.println(df.format(col1) + ":" + col2 + ":" + col3 + ":"
//			//						+ col4 + ":" + col5); //表示
//			//			}
//		} catch ( SQLException e ) {
//			e.printStackTrace() ;
//		}
//	}
//
//
//	public void dbGoodsDelete(String name) {
//		// SQL 文の作成
//		//プレースホルダを使用して値を後設定
//		//プレースホルダとは「？」の部分のことを指す。
//		String sql = "Delete from goods where p_name = ?" ;
//		// PostgreSQL に接続(資源付き try 文の場合)
//		try (Connection con=DriverManager.getConnection(url, user, password ) ;
//				PreparedStatement pstmt = con.prepareStatement(sql); ) {
//			//SQL 文の出金額の？の場所に 3000 をセットする。
//			pstmt.setString(1,name);
//
//			int i = pstmt.executeUpdate();
//			if(i==0) {
//				System.out.println("除する対象のデータが存在しませんでした");
//			}
//
//
//			//SQL 文を実行し、結果を ResultSet に格納する。
//			//			ResultSet result = pstmt.executeQuery();
//			//			while(result.next()) {//実行結果の取得
//			//				Date col1 = result.getTimestamp(1) ; //日付
//			//				String col2 = result.getString ( 2 ) ; //費目
//			//				String col3 = result.getString ( 3 ) ; //メモ
//			//				int col4 = result.getInt ( 4 ) ; //入金額
//			//				int col5 = result.getInt ( 5 ) ; //出金額
//			//				System.out.println(df.format(col1) + ":" + col2 + ":" + col3 + ":"
//			//						+ col4 + ":" + col5); //表示
//			//			}
//		} catch ( SQLException e ) {
//			e.printStackTrace() ;
//		}
//	}
//	
//	
//	
	
	
//	public ArrayList<InfoData> dbGetGoodsInfo(){
//		Connection con = null;
//		Statement stmt = null;
//		ResultSet result = null;
//		String sql = "select goods.p_name, goods.price, comp.c_name from goods join comp on goods.m_code=comp.m_code" ;
//		ArrayList<InfoData> list = new ArrayList<InfoData>();
//		//String sql = "select * from 家計簿 where 出金額 >=" + 1000 ;
//		try {
//			// PostgreSQL に接続
//			con = DriverManager.getConnection ( url, user, password );
//			// SQL を実行するためのインスタンスを生成
//			stmt = con.createStatement();
//			// SQL の実行結果を格納する
//			result = stmt.executeQuery ( sql );
//			//家計簿は「日付、費目、メモ、入金額、出金額」の順に列が出来ている。
//			while ( result.next() ) {
//
//				String gname = result.getString (1) ; //費目
//				int gprice = result.getInt (2) ; //入金額
//				String cname = result.getString (3) ; //メモ
//
//				InfoData infodata = new InfoData(gname,gprice,cname);
//
//				list.add(infodata);
//
//
//
//
//				//System.out.println(df.format(col1) + ":" + col2 + ":" + col3 + ":"
//				//		+ col4 + ":" + col5);//表示
//			}
//		} catch ( SQLException e ) {
//			e.printStackTrace() ;
//		}finally{
//			//クローズ処理
//			try {
//				if(result != null) result.close();
//				if(stmt != null) stmt.close();
//				if(con != null) con.close();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//
//
//		return list;
//	}






}