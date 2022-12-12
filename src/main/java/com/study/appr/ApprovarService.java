package com.study.appr;

import java.util.List;

import vo.approvalVO;
import vo.historyVO;
import vo.memberVO;

public interface ApprovarService {

	memberVO loginCheck(memberVO vo);

	List<approvalVO> list(approvalVO vo);

	int insert(approvalVO vo);

	approvalVO detail(int appr_no);

	int number();

	int update(approvalVO vo);

	


	//memberVO login(memberVO vo);

	

	

}
