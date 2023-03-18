package com.greedy.erp.production.production.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp.regist.entity.Product;
import com.greedy.erp.regist.entity.Storage;

@Entity
@Table(name = "INSTRUCTIONDETAIL")
@SequenceGenerator(
		name = "INSTRUCTIONDETAIL_SEQ_GENERATOR",
		sequenceName = "SEQ_INSTRUCTION_NO",
		initialValue = 1, allocationSize = 1
		)
public class InstructionDetail {

	@Id
	@Column( name = "INSTRUCTION_NO")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "INSTRUCTIONDETAIL_SEQ_GENERATOR"
		)
	private int instructionNo;
	
	@Column( name = "INSTRUCTION_AMOUNT")
	private int instructionAmount;
	
	@Column( name = "INSTRUCTION_NOTE")
	private String instructionNote;
	
	@Column( name = "INSTRUCTION_CODE", insertable = false, updatable = false)
	private int instructionCode;
	
	@ManyToOne 
	@JoinColumn( name = "STORAGE_CODE")
	private Storage storage;
	
	@ManyToOne 
	@JoinColumn( name = "PRODUCT_CODE")
	private Product product;

	public InstructionDetail() {
	}

	public InstructionDetail(int instructionNo, int instructionAmount, String instructionNote, int instructionCode,
			Storage storage, Product product) {
		this.instructionNo = instructionNo;
		this.instructionAmount = instructionAmount;
		this.instructionNote = instructionNote;
		this.instructionCode = instructionCode;
		this.storage = storage;
		this.product = product;
	}

	public int getInstructionNo() {
		return instructionNo;
	}

	public void setInstructionNo(int instructionNo) {
		this.instructionNo = instructionNo;
	}

	public int getInstructionAmount() {
		return instructionAmount;
	}

	public void setInstructionAmount(int instructionAmount) {
		this.instructionAmount = instructionAmount;
	}

	public String getInstructionNote() {
		return instructionNote;
	}

	public void setInstructionNote(String instructionNote) {
		this.instructionNote = instructionNote;
	}

	public int getInstructionCode() {
		return instructionCode;
	}

	public void setInstructionCode(int instructionCode) {
		this.instructionCode = instructionCode;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "InstructionDetail [instructionNo=" + instructionNo + ", instructionAmount=" + instructionAmount
				+ ", instructionNote=" + instructionNote + ", instructionCode=" + instructionCode + ", storage="
				+ storage + ", product=" + product + "]";
	}
	
	
}
