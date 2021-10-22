package com.leemanni.dao;

import java.security.cert.TrustAnchor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.leemanni.vo.MvcboardVO;

public class WebDAO {
	DataSource dataSource ;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public WebDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/oracle");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 연결 실패");
		}
		
	}
	
	
	public void insert(MvcboardVO mvcboardVO) {
		try {
			String sql = "insert into mvcboard (idx, name, subject, content, gup, lev, seq) "
					+ "values(mvcboard_idx_seq.nextval, ?, ?, ?,mvcboard_idx_seq.currval, 0 , 0 )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mvcboardVO.getName());
			pstmt.setString(2, mvcboardVO.getSubject());
			pstmt.setString(3, mvcboardVO.getContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {try{conn.close();}catch (SQLException e) {}}
			if(pstmt != null) {try{pstmt.close();}catch (SQLException e) {}}
		}
	}

	

}
