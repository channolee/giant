package com.study.appr;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.approvalVO;
import vo.historyVO;
import vo.memberVO;

@Repository("dao")
public class ApprovarDaoImpl implements ApprovarDao{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public memberVO loginCheck(memberVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("member_mapper.loginCheck", vo);
	}

	@Override
	public List<approvalVO> list(approvalVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("member_mapper.list", vo);
	}

	@Override
	public int insert(approvalVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("member_mapper.insert", vo);
	}

	@Override
	public approvalVO detail(int appr_no) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("member_mapper.detail", appr_no);
	}

	@Override
	public int number() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("member_mapper.number");
	}

	@Override
	public int update(approvalVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("member_mapper.update", vo);
	}


	

	/*
	 * @Override public memberVO login(memberVO vo) { 
	 * // TODO Auto-generated method stub 
	 * memberVO resultVO = new memberVO(); 
	 * System.out.println("다오왔음 " + vo);
	 * resultVO = sqlSession.selectOne("member_mapper.login", vo);
	 * System.out.println("조회결과 " + resultVO);
	 * 
	 * return resultVO; }
	 */

	
	
	

}
