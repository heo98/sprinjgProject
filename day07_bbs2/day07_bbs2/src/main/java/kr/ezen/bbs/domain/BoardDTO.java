package kr.ezen.bbs.domain;

import java.sql.Date;

import lombok.Data;

//@Setter @Getter @ToString
@Data
public class BoardDTO {
	private int bid;
	private String subject;
	private String contents;
	private int hit;
	private String writer;
	private Date reg_date;
	
	public String show() {
		return this.subject;
	}
}
