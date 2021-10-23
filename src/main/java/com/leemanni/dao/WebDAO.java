package com.leemanni.dao;

import java.security.cert.TrustAnchor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.context.support.GenericXmlApplicationContext;

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


	public int selectCount() {
		int result = 0;
		try {
			String sql = "select count(*) from mvcboard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (Exception e) {
		}finally {
			if(conn != null) {try{conn.close();}catch (SQLException e) {}}
			if(pstmt != null) {try{pstmt.close();}catch (SQLException e) {}}
			if(rs != null) {try{rs.close();}catch (SQLException e) {}}
		}
		return result;
	}


	public ArrayList<MvcboardVO> selectList(HashMap<String, Integer> hmap) {
		String sql = "select * from("
				+ "select rownum rnum, AA.* from ("
				+ "select * from mvcboard order by gup desc, seq asc"
				+ ")AA where rownum <= ?"
				+") where rnum >= ?";
		ArrayList<MvcboardVO> list = new ArrayList<MvcboardVO>();
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hmap.get("endNo"));
			pstmt.setInt(2, hmap.get("startNo"));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GenericXmlApplicationContext context = new GenericXmlApplicationContext("classpath:application_ctx.xml");
				MvcboardVO mvcboardVO = context.getBean("mvcboardVO", MvcboardVO.class);
				mvcboardVO.setIdx(rs.getInt("idx"));
				mvcboardVO.setName(rs.getString("name"));
				mvcboardVO.setSubject(rs.getString("subject"));
				mvcboardVO.setContent(rs.getString("content"));
				mvcboardVO.setGup(rs.getInt("gup"));
				mvcboardVO.setLev(rs.getInt("lev"));
				mvcboardVO.setSeq(rs.getInt("seq"));
				mvcboardVO.setHit(rs.getInt("hit"));
				mvcboardVO.setWriteDate(rs.getTimestamp("writeDate"));
				list.add(mvcboardVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {try{conn.close();}catch (SQLException e) {}}
			if(pstmt != null) {try{pstmt.close();}catch (SQLException e) {}}
			if(rs != null) {try{rs.close();}catch (SQLException e) {}}
		}
		
		
		return list;
	}

	

}
