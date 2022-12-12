package com.study.project.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.project.dao.BoardDao;

@Repository("dao")
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.list", map);
	}

	@Override
	public int insert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.insert("mapper.insert", map);
	}

	@Override
	public Map<String, Object> map(int seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.map", seq);
	}

	@Override
	public int update(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.update("mapper.update", map);
	}

	@Override
	public Map<String, Object> delete(int seq) {
	// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.delete", seq);
	}

	@Override
	public int pageMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.pageMap", map);
	}

	@Override
	public List<Map<String, Object>> mi_list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.mi_list", map);
	}

	@Override
	public int upload(Map<String, Object> uploadMap) {
		// TODO Auto-generated method stub
		return sqlSession.insert("mapper.upload", uploadMap);
	}

	@Override
	public int listSeq() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.listSeq");
	}

	@Override
	public List<Map<String, Object>> fileList(int seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.fileList", seq);
	}


}
