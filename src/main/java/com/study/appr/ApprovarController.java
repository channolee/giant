package com.study.appr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vo.approvalVO;
import vo.historyVO;
import vo.memberVO;

@Controller
public class ApprovarController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private ApprovarService approvarService;

	// 로그인 페이지 이동
	@RequestMapping("login")
	public String login() {	
		return "appr/login";
	}
	
	// 로그인 체크
	@RequestMapping("loginCheck")
	public String loginCheck(@ModelAttribute memberVO vo, HttpSession session, Model model) {
		
		memberVO loginCheckVO = approvarService.loginCheck(vo);
		
		String id = vo.getMember_id();
		String pw = vo.getMember_pwd();

		String url = "";
		
		//System.out.println(pw);
		//System.out.println(loginCheckVO);
		//System.out.println(vo.getMember_pwd());
		
		if(loginCheckVO == null) {
			model.addAttribute("loginCheck", "idFail");
			url = "appr/login";
		} else if(!pw.equals(loginCheckVO.getMember_pwd())) {
			model.addAttribute("loginCheck", "pwdFail");
			url = "appr/login";
		} else {
			session.setAttribute("memberInfo", loginCheckVO);
			url = "redirect:list";
		}
		
		return url;
		
	}
	
	/*
	 * @RequestMapping("list") 
	 * public String list(@ModelAttribute memberVO vo, Model model) { 
	 * System.out.println(vo.getMember_id());
	 * 
	 * memberVO login = approvarService.login(vo);
	 * 
	 * return "appr/list"; }
	 * 에베베베벱베베ㅔ
	 */
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:login";
	}
	
	//리스트 메인 페이지
	@RequestMapping("list")
	public String list(@ModelAttribute approvalVO vo, Model model, HttpSession session) {
	
		
		
		List<approvalVO> list = new ArrayList<approvalVO>();
		list = approvarService.list(vo);
		
//		approvalVO.set("memberInfo", session.getAttribute("memberInfo"));
		model.addAttribute("list", list);
		model.addAttribute("vo", vo);
		
		return "appr/list";
	}
	
	// 검색 할 때 ajax
	@RequestMapping("ajax")
	public String ajax(@ModelAttribute approvalVO vo, Model model, HttpSession session) {
		
		List<approvalVO> list = new ArrayList<approvalVO>();
		list = approvarService.list(vo);
		model.addAttribute("list", list);
		model.addAttribute("vo", vo);
		
		return "appr/ajaxView";
	}
	
	// 글쓰기 화면 이동
	@RequestMapping("write")
	public String write(Model model) {
		
		int number = approvarService.number();
		
		model.addAttribute("number", number);
		model.addAttribute("mode", "add");
		
		return "appr/write";
	}
	
	@RequestMapping("writeProc")
	public String writeProc(@ModelAttribute approvalVO vo, HttpSession session) {

//			int seq = sqlSession.selectOne("mapper.dataChk", map.get("seq").toString());
			
		approvalVO memberInfo = (approvalVO)session.getAttribute("memberInfo");
			
			
			map.put("memberInfo", memberInfo);
			String rank = memberInfo.get("member_rank").toString();
			String aChk = "Y";
			if((!"save".equals(map.get("status").toString())) && ("1".equals(rank) || "2".equals(rank))){
				map.put("appChk", aChk);
			}
			
			
			
			if(seq == 0) {
				sqlSession.insert("mapper.insert", map);
			}else {
				sqlSession.update("mapper.update", map);
			}
			
			sqlSession.insert("mapper.history", map);
				
			return "redirect:list";
	}
	
	// 글 등록
//	@RequestMapping("insert")
//	public String insert(@ModelAttribute approvalVO vo) {
//		int insert = approvarService.insert(vo);
//
//		return "redirect:list";
//		
//	}
	
	// 상세보기
	@RequestMapping("detail")
	public String detail(@ModelAttribute approvalVO vo, int appr_no, Model model) {
		
		approvalVO detailVO = new approvalVO();
		detailVO = approvarService.detail(appr_no);
		
		model.addAttribute("detailVO", detailVO);
		
		return "appr/write";
	}
	
//	@RequestMapping("update")
//	public String update(@ModelAttribute approvalVO vo, Model model) {
//		
//		int update = approvarService.update(vo);
//		System.out.println(update);
//		
//		model.addAttribute("update", update);
//		
//		return "redirect:list";
//		
//	}
}
