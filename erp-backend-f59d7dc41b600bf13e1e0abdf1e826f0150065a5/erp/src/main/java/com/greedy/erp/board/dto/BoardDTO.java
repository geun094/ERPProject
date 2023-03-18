package com.greedy.erp.board.dto;

import java.util.Date;

import com.greedy.erp.regist.dto.EmpDTO;


public class BoardDTO {

	private int boardCode;
	private EmpDTO emp;
	private Date boardDate;
	private String boardTitle;
	private String boardContent;
	private String noticeYn;

	private Date expireDate;
	private String boardPwd;
	private String anonymousYn;
	private String oldFileName;
	private String newFileName;
	private String boardDiv;
	
	public BoardDTO() {}

	public BoardDTO(int boardCode, EmpDTO emp, Date boardDate, String boardTitle, String boardContent, String noticeYn,
			Date expireDate, String boardPwd, String anonymousYn, String oldFileName, String newFileName,
			String boardDiv) {
		super();
		this.boardCode = boardCode;
		this.emp = emp;
		this.boardDate = boardDate;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.noticeYn = noticeYn;
		this.expireDate = expireDate;
		this.boardPwd = boardPwd;
		this.anonymousYn = anonymousYn;
		this.oldFileName = oldFileName;
		this.newFileName = newFileName;
		this.boardDiv = boardDiv;
	}

	public int getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}

	public EmpDTO getEmp() {
		return emp;
	}

	public void setEmp(EmpDTO emp) {
		this.emp = emp;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getNoticeYn() {
		return noticeYn;
	}

	public void setNoticeYn(String noticeYn) {
		this.noticeYn = noticeYn;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getBoardPwd() {
		return boardPwd;
	}

	public void setBoardPwd(String boardPwd) {
		this.boardPwd = boardPwd;
	}

	public String getAnonymousYn() {
		return anonymousYn;
	}

	public void setAnonymousYn(String anonymousYn) {
		this.anonymousYn = anonymousYn;
	}

	public String getOldFileName() {
		return oldFileName;
	}

	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}
	


	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getBoardDiv() {
		return boardDiv;
	}

	public void setBoardDiv(String boardDiv) {
		this.boardDiv = boardDiv;
	}

	@Override
	public String toString() {
		return "BoardDTO [boardCode=" + boardCode + ", emp=" + emp + ", boardDate=" + boardDate + ", boardTitle="
				+ boardTitle + ", boardContent=" + boardContent + ", noticeYn=" + noticeYn + ", expireDate="
				+ expireDate + ", boardPwd=" + boardPwd + ", anonymousYn=" + anonymousYn + ", oldFileName="
				+ oldFileName + ", newFileName=" + newFileName + ", boardDiv=" + boardDiv + "]";
	}

	
}