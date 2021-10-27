package com.leemanni.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.leemanni.vo.MvcboardVO;

public interface DAO {

	void insert(MvcboardVO mvcboardVO);

	int selectCount();

	ArrayList<MvcboardVO> selectList(HashMap<String, Integer> hmap);

	void increment(int idx);

	MvcboardVO selectByIdx(int idx);

	void update(MvcboardVO mvcboardVO);

	void delete(int idx);

}
