package com.greedy.erp.board.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp.regist.entity.Emp;

@Entity
@Table(name = "BOARD")
@SequenceGenerator(
		name = "BOARD_SEQ_GENERATOR",
		sequenceName = "SEQ_BOARD_CODE",
		initialValue = 1, allocationSize = 1
	)
public class Board {
	
	@Id
	@Column(name = "BOARD_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "BOARD_SEQ_GENERATOR"
		)
	private int boardCode;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private Emp emp;
	
	@Column(name = "BOARD_DATE")
	private Date boardDate;
	
	@Column(name = "BOARD_TITLE")
	private String boardTitle;
	
	
	@Column(name = "BOARD_CONTENT")
	private String boardContent;
	
	@Column(name = "NOTICE_YN")
	private String noticeYn;
	
	@Column(name = "EXPIRE_DATE")
	private Date expireDate;
	
	@Column(name = "BOARD_PWD")
	private String boardPwd;
	
	@Column(name = "ANONYMOUS_YN")
	private String anonymousYn;
	
	@Column(name = "OLD_FILE_NAME")
	private String oldFileName;
	
	@Column(name = "NEW_FILE_NAME")
	private String newFileName;
	
	@Column(name = "BOARD_DIV")
	private String boardDiv;
	
	public Board() {}

	public Board(int boardCode, Emp emp, Date boardDate, String boardTitle, String boardContent, String noticeYn,
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

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public java.util.Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(java.util.Date boardDate) {
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
		return "Board [boardCode=" + boardCode + ", emp=" + emp + ", boardDate=" + boardDate + ", boardTitle="
				+ boardTitle + ", boardContent=" + boardContent + ", noticeYn=" + noticeYn + ", expireDate="
				+ expireDate + ", boardPwd=" + boardPwd + ", anonymousYn=" + anonymousYn + ", oldFileName="
				+ oldFileName + ", newFileName=" + newFileName + ", boardDiv=" + boardDiv + "]";
	}
	
	@PrePersist
	public void preActive() {
		this.boardDate = this.boardDate == null ? new Date(System.currentTimeMillis()) : this.boardDate;
	}
}
	