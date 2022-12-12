package vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class approvalVO {
	
	private int appr_no;
	private String appr_id;
	private String appr_subject;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date appr_reg_date;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date appr_sign_date;
	
	private String appr_sign_person;
	private String appr_sign_state;
    private String appr_content;
    private String search_type;
    private String searchStatus;
    private String startDate;
    private String endDate; 
    private String search_text;
    private String status;
    private String memberInfo;
    private String writeName;
    
	public String getWriteName() {
		return writeName;
	}
	public void setWriteName(String writeName) {
		this.writeName = writeName;
	}
	public String getMemberInfo() {
		return memberInfo;
	}
	public void setMemberInfo(String memberInfo) {
		this.memberInfo = memberInfo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSearch_text() {
		return search_text;
	}
	public void setSearch_text(String search_text) {
		this.search_text = search_text;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSearchStatus() {
		return searchStatus;
	}
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	public int getAppr_no() {
		return appr_no;
	}
	public void setAppr_no(int appr_no) {
		this.appr_no = appr_no;
	}
	
	public String getAppr_id() {
		return appr_id;
	}
	public void setAppr_id(String appr_id) {
		this.appr_id = appr_id;
	}
	public String getAppr_subject() {
		return appr_subject;
	}
	public void setAppr_subject(String appr_subject) {
		this.appr_subject = appr_subject;
	}
	public Date getAppr_reg_date() {
		return appr_reg_date;
	}
	public void setAppr_reg_date(Date appr_reg_date) {
		this.appr_reg_date = appr_reg_date;
	}
	public Date getAppr_sign_date() {
		return appr_sign_date;
	}
	public void setAppr_sign_date(Date appr_sign_date) {
		this.appr_sign_date = appr_sign_date;
	}
	public String getAppr_sign_person() {
		return appr_sign_person;
	}
	public void setAppr_sign_person(String appr_sign_person) {
		this.appr_sign_person = appr_sign_person;
	}
	public String getAppr_sign_state() {
		return appr_sign_state;
	}
	public void setAppr_sign_state(String appr_sign_state) {
		this.appr_sign_state = appr_sign_state;
	}
	public String getAppr_content() {
		return appr_content;
	}
	public void setAppr_content(String appr_content) {
		this.appr_content = appr_content;
	}
    
    

}
