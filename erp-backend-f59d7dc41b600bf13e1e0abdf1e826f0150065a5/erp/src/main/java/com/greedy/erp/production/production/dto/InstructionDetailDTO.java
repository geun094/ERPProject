package com.greedy.erp.production.production.dto;

import com.greedy.erp.regist.dto.ProductDTO;
import com.greedy.erp.regist.dto.StorageDTO;

public class InstructionDetailDTO {

	private int instructionNo;
	private int instructionAmount;
	private String instructionNote;
	
	private int instructionCode;
	private StorageDTO storage;
	private ProductDTO product;
	
	public InstructionDetailDTO() {
	}
	public InstructionDetailDTO(int instructionNo, int instructionAmount, String instructionNote, int instructionCode,
			StorageDTO storage, ProductDTO product) {
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
	public StorageDTO getStorage() {
		return storage;
	}
	public void setStorage(StorageDTO storage) {
		this.storage = storage;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "InstructionDetailDTO [instructionNo=" + instructionNo + ", instructionAmount=" + instructionAmount
				+ ", instructionNote=" + instructionNote + ", instructionCode=" + instructionCode + ", storage="
				+ storage + ", product=" + product + "]";
	}
	
	
}
