package vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class historyVO {
	
	private int history_no;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date history_sign_date;
	private String history_sign_person;
	private String history_sign_state;
	private int appr__no;
	
	public int getHistory_no() {
		return history_no;
	}
	public void setHistory_no(int history_no) {
		this.history_no = history_no;
	}
	public Date getHistory_sign_date() {
		return history_sign_date;
	}
	public void setHistory_sign_date(Date history_sign_date) {
		this.history_sign_date = history_sign_date;
	}
	public String getHistory_sign_person() {
		return history_sign_person;
	}
	public void setHistory_sign_person(String history_sign_person) {
		this.history_sign_person = history_sign_person;
	}
	public String getHistory_sign_state() {
		return history_sign_state;
	}
	public void setHistory_sign_state(String history_sign_state) {
		this.history_sign_state = history_sign_state;
	}
	public int getAppr__no() {
		return appr__no;
	}
	public void setAppr__no(int appr__no) {
		this.appr__no = appr__no;
	}
	
	

}
