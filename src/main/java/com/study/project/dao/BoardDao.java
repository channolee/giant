package com.study.project.dao;

import java.util.List;
import java.util.Map;

public interface BoardDao {

	List<Map<String, Object>> list(Map<String, Object> map);

	int insert(Map<String, Object> map);

	Map<String, Object> map(int seq);

	int update(Map<String, Object> map);

	Map<String, Object> delete(int seq);

	int pageMap(Map<String, Object> map);

	List<Map<String, Object>> mi_list(Map<String, Object> map);

	int upload(Map<String, Object> uploadMap);

	int listSeq();

	List<Map<String, Object>> fileList(int seq);

	
}
