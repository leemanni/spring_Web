package com.leemanni.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.leemanni.spring_Web.Constant;
import com.leemanni.vo.MvcboardVO;

public class WebDAO {
	private JdbcTemplate template;
	public WebDAO() {
		template = Constant.template;
//		
	}
	
//	메인글 삽입
	public void insert(final MvcboardVO mvcboardVO) {
		String sql = "insert into mvcboard (idx, name, subject, content, gup, lev, seq) "
				+ "values(mvcboard_idx_seq.nextval, ?, ?, ?,mvcboard_idx_seq.currval, 0 , 0 )";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, mvcboardVO.getName());
				ps.setString(2, mvcboardVO.getSubject());
				ps.setString(3, mvcboardVO.getContent());
			}
		});
	}

//	전체 글 계산
	public int selectCount() {
		String sql = "select count(*) from mvcboard";
		return template.queryForInt(sql);
	}

//	1페이지 분량 글 담기
	public ArrayList<MvcboardVO> selectList(HashMap<String, Integer> hmap) {
		String sql = "select * from("
				+ "select rownum rnum, AA.* from ("
				+ "select * from mvcboard order by gup desc, seq asc"
				+ ")AA where rownum <= " +hmap.get("endNo")
				+") where rnum >= " + hmap.get("startNo");
		return (ArrayList<MvcboardVO>) template.query(sql, new BeanPropertyRowMapper(MvcboardVO.class));
	}

//	조회수 증가
	public void increment(final int idx) {
		String sql = "update mvcboard set hit = hit + 1 where idx = ?";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, idx);
			}
		});
	}

//	1건의 메인글 불러오기
	public MvcboardVO selectByIdx(int idx) {
		String sql = "select * from mvcboard where idx = " + idx;
		return template.queryForObject(sql, new BeanPropertyRowMapper<>(MvcboardVO.class));
	}

//	메인 글 수정
	public void update(final MvcboardVO mvcboardVO) {
		String sql = "update mvcboard set content = ? , subject = ? where idx = ?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, mvcboardVO.getContent());
				ps.setString(2, mvcboardVO.getSubject());
				ps.setInt(3, mvcboardVO.getIdx());
			}
		});
	}

//	메인 글 삭제
	public void delete(final int idx) {
		System.out.println("DAO => delete");
		String sql = "delete from mvcboard where idx = ?";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, idx);
			}
		});
	}

}
