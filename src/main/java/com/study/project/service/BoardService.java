package com.study.project.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

	List<Map<String, Object>> list(Map<String, Object> map);

	public int insert(Map<String, Object> map);

	Map<String, Object> map(int seq);

	int update(Map<String, Object> map);

	Map<String, Object> delete(int seq);

	Map<String, Object> pageMap(Map<String, Object> map);

	List<Map<String, Object>> mi_list(Map<String, Object> map);

	int upload(Map<String, Object> uploadMap);

	int listSeq();

	List<Map<String, Object>> fileList(int seq);

}
