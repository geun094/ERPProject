package com.greedy.erp.production.production.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.greedy.erp.regist.entity.Client;
import com.greedy.erp.regist.entity.Emp;

@Entity
@Table(name = "INSTRUCTION")
@SequenceGenerator(
		name = "INSTRUCTION_SEQ_GENERATOR",
		sequenceName = "SEQ_INSTRUCTION_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Instruction {

	@Id
	@Column( name = "INSTRUCTION_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "INSTRUCTION_SEQ_GENERATOR"
		)
	private int instructionCode;
	
	@Column( name = "INSTRUCTION_DATE")
	private Date instructionDate;
	
	@Column( name = "INSTRUCTION_DELIVERY")
	private Date instructionDelivery;
	
	@ManyToOne // 사원 1명 당 1개 이상의 지시서 등록 가능
	@JoinColumn( name = "EMP_CODE")
	private Emp emp;
	
	@ManyToOne // 하나의 거래처 당 1개 이상의 지시서 등록 가능
	@JoinColumn( name = "CLIENT_CODE")
	private Client client;
	
	// 1개의 지시서 당 1개 이상의 지시내역 등록 가능
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn( name = "INSTRUCTION_CODE")
	private List<InstructionDetail> instructionDetail;

	public Instruction() {
	}

	public Instruction(int instructionCode, Date instructionDate, Date instructionDelivery, Emp emp, Client client,
			List<InstructionDetail> instructionDetail) {
		this.instructionCode = instructionCode;
		this.instructionDate = instructionDate;
		this.instructionDelivery = instructionDelivery;
		this.emp = emp;
		this.client = client;
		this.instructionDetail = instructionDetail;
	}

	public int getInstructionCode() {
		return instructionCode;
	}

	public void setInstructionCode(int instructionCode) {
		this.instructionCode = instructionCode;
	}

	public Date getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	public Date getInstructionDelivery() {
		return instructionDelivery;
	}

	public void setInstructionDelivery(Date instructionDelivery) {
		this.instructionDelivery = instructionDelivery;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<InstructionDetail> getInstructionDetail() {
		return instructionDetail;
	}

	public void setInstructionDetail(List<InstructionDetail> instructionDetail) {
		this.instructionDetail = instructionDetail;
	}

	@Override
	public String toString() {
		return "Instruction [instructionCode=" + instructionCode + ", instructionDate=" + instructionDate
				+ ", instructionDelivery=" + instructionDelivery + ", emp=" + emp + ", client=" + client
				+ ", instructionDetail=" + instructionDetail + "]";
	}
	
	  @PrePersist
	   public void preActiveYn() {
	      this.instructionDate = this.instructionDate == null ? new Date(System.currentTimeMillis()) : this.instructionDate;
	   }
	
	
}
