package com.study.project.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.study.project.service.BoardService;
import com.tobesoft.platform.PlatformRequest;
import com.tobesoft.platform.PlatformResponse;
import com.tobesoft.platform.data.ColumnInfo;
import com.tobesoft.platform.data.Dataset;
import com.tobesoft.platform.data.DatasetList;
import com.tobesoft.platform.data.PlatformData;
import com.tobesoft.platform.data.VariableList;

	@Controller
	public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("list")
	public String list(@RequestParam Map<String, Object> map, Model model) {

		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = boardService.list(map);
		
		Map<String, Object> pageMap = boardService.pageMap(map);
		
		model.addAttribute("list", list);
		//jsp에서 입력했던 값을 다시 jsp로 돌려준다.(검색기록을 다시 list.jsp에 보여준다.)
		model.addAttribute("map", map);
		model.addAttribute("pageMap", pageMap);
		
		return "board/list";
	}
	
	@RequestMapping("ajax")
	public String ajax(@RequestParam Map<String, Object> map, Model model) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = boardService.list(map);
		
		Map<String, Object> pageMap = boardService.pageMap(map);
		
		model.addAttribute("list", list);
		//jsp에서 입력했던 값을 다시 jsp로 돌려준다.(검색기록을 다시 list.jsp에 보여준다.)
		model.addAttribute("map", map);
		model.addAttribute("pageMap", pageMap);
		
		return "board/ajaxView";
		
	}
	
	//글쓰기 페이지로 이동
	@RequestMapping("write")
	public String write() {
		return "board/write";
	}
	
	//글 등록
	private static final String filePath = "C:/Users/chann/Desktop/gsupload/";
	@RequestMapping("insert")
	public String insert(@RequestParam Map<String, Object> map, MultipartHttpServletRequest mRequest) 
			throws IllegalStateException, IOException{
		// Service에서 등록처리를 하기 위해서 호출	
		
		int seq = boardService.listSeq();
		map.put("listSeq", seq);
		
		Iterator<String> iterator = mRequest.getFileNames();
		
//		File file = new File(filePath);
//		if(file.exists() == false) {
//			file.mkdirs();
//		}
		
		while(iterator.hasNext()) {
			MultipartFile mFile = mRequest.getFile(iterator.next());
			
			UUID uuid = UUID.randomUUID();
			String realName = mFile.getOriginalFilename();
			String saveName = uuid + "_" + realName;
				
			mFile.transferTo(new File(filePath + saveName));
				
			Map<String, Object> uploadMap = new HashMap<String, Object>();
			uploadMap.put("realName", realName);
			uploadMap.put("saveName", saveName);
			uploadMap.put("filePath", filePath);
			uploadMap.put("listSeq", seq);
				
			int fileInsert = boardService.upload(uploadMap);
			
		}
		map.put("seq", seq);
		int insert = boardService.insert(map);
		if(insert == 0) {
			
		} else {
			
		}
		return "redirect:list";
		
	}
	
	// 상세보기
	@RequestMapping("map")
	public String view(@RequestParam int seq, Model model) {
	    
		Map<String, Object> map = boardService.map(seq);
		
		List<Map<String, Object>> fileList = boardService.fileList(seq);
		
		model.addAttribute("map", map);
		model.addAttribute("fileList", fileList);
		
		return "board/write";
	}
	
	// 파일 다운로드
	@RequestMapping("download")
	public void download(@RequestParam String realName, @RequestParam String saveName, HttpServletResponse response) {
		try {
			String path = filePath + saveName;
			
			File file = new File(path);
			response.setHeader("Content-Disposition", "attachment;filename=" + realName);
			
			FileInputStream fileInputStream = new FileInputStream(path);
			OutputStream out = response.getOutputStream();
			
			int read = 0;
				byte[] buffer = new byte[1024];
				while((read = fileInputStream.read(buffer)) != -1) {
					out.write(buffer, 0, read);
				}
		} catch(Exception e) {
			
		}
	}
	
	// 수정
	@RequestMapping("update")
	public String update(@RequestParam Map<String, Object> map) {
		
		int update = boardService.update(map);
		
		return "redirect:list";
	}
		
	// 체크박스 선택삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam ("delete")int[] delete, Model model) {
	      
	    for (int seq : delete) {
	    Map<String, Object> map = boardService.delete(seq);
	    model.addAttribute("map", map);
	    }
	    
	    return "redirect:list";    

	 }
	
	@RequestMapping("miData")
	public void miData(@RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		PlatformRequest pReq = new PlatformRequest(request, "UTF-8");
		pReq.receiveData();
		
		DatasetList paraDs1 = pReq.getDatasetList();
		
		Dataset paramDs = paraDs1.getDataset("javaSearch");
		
		String searchType = paramDs.getColumnAsString(0, "searchType");
		String searchText = paramDs.getColumnAsString(0, "searchText");
		String startDate = paramDs.getColumnAsString(0, "startDate");
		
		/*
		 * for(int i = 0; i < paramDs.getRowCount(); i++) {
		 * 
		 * }
		 */
		
		System.out.println(searchType + "//" + searchText);
		
		System.out.println("=====================================================");
		
		VariableList paramV1 = pReq.getVariableList();
		String type = paramV1.getValueAsString("type");
		String text = paramV1.getValueAsString("text");
		String date = paramV1.getValueAsString("date");
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list = boardService.mi_list(map);
		
		Dataset ds = new Dataset("javaList");
		ds.addColumn("seq", ColumnInfo.COLUMN_TYPE_INT, 100);
		ds.addColumn("mem_name", ColumnInfo.COLUMN_TYPE_STRING, 256);
		ds.addColumn("board_subject", ColumnInfo.COLUMN_TYPE_STRING, 256);
		//ds.addColumn("reg_date", ColumnInfo.COLUMN_TYPE_DATE, 256);
		//ds.addColumn("upt_date", ColumnInfo.COLUMN_TYPE_DATE, 256);
		//ds.addColumn("view_cnt", ColumnInfo.COLUMN_TYPE_INT, 100);
		
		for(int i = 0; i < list.size(); i++) {
			int row = ds.appendRow();
		    ds.setColumn(row, "seq", list.get(i).get("seq").toString()); 
		    ds.setColumn(row, "mem_name", list.get(i).get("memName").toString()); 
		    ds.setColumn(row, "board_subject", list.get(i).get("boardSubject").toString()); 
		    //ds.setColumn(row, "reg_date", list.get(i).get("regDate").toString()); 
		    //ds.setColumn(row, "upt_date", list.get(i).get("uptDate").toString()); 
		    //ds.setColumn(row, "view_cnt", list.get(i).get("viewCnt").toString()); 
		   
		}
		
		DatasetList dl = new DatasetList();
		dl.add(ds);
		
		VariableList vl = new VariableList();
		
		//PlatformData pData = new PlatformData();
		//pData.addDataset(ds); 
		//데이터 한개씩 받아오는 것
		
		PlatformResponse pRes = new PlatformResponse(response, PlatformRequest.JSP_XML, "UTF-8");
		pRes.sendData(vl, dl);
	
	}
	
}
	
	