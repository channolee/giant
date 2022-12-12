package com.study.appr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vo.approvalVO;
import vo.historyVO;
import vo.memberVO;

@Service("service")
public class ApprovarServiceImpl implements ApprovarService{
	
	@Autowired
	private ApprovarDao approvarDao;

	@Override
	public memberVO loginCheck(memberVO vo) {
		// TODO Auto-generated method stub
		return approvarDao.loginCheck(vo);
	}

	@Override
	public List<approvalVO> list(approvalVO vo) {
		// TODO Auto-generated method stub
		return approvarDao.list(vo);
	}

	@Override
	public int insert(approvalVO vo) {
		// TODO Auto-generated method stub
		return approvarDao.insert(vo);
	}

	@Override
	public approvalVO detail(int appr_no) {
		// TODO Auto-generated method stub
		return approvarDao.detail(appr_no);
	}

	@Override
	public int number() {
		// TODO Auto-generated method stub
		return approvarDao.number();
	}

	@Override
	public int update(approvalVO vo) {
		// TODO Auto-generated method stub
		return approvarDao.update(vo);
	}


	/*
	 * @Override public memberVO login(memberVO vo) { 
	 * // TODO Auto-generated method stub 
	 * memberVO resultVO = new memberVO();
	 * resultVO = approvarDao.login(vo);
	 * 
	 * return resultVO; }
	 */

	

	

}
