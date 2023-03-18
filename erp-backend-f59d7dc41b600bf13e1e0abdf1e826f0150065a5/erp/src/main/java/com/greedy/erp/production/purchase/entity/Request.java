package com.greedy.erp.production.purchase.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.greedy.erp.regist.entity.Client;
import com.greedy.erp.regist.entity.Emp;
import com.greedy.erp.regist.entity.Storage;


@Entity
@Table(name = "REQUEST")
@SequenceGenerator(
			name = "REQUEST_SEQ_GENERATOR",
			sequenceName = "SEQ_REQUEST_CODE",
			initialValue = 1, allocationSize = 1
			)
public class Request {
	
		
	@Id
	@Column( name = "REQUEST_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "REQUEST_SEQ_GENERATOR"
		)
	private int requestCode;
		
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column( name = "REQUEST_DATE")
	private Date requestDate;

		@Column(name = "REQUEST_STATUS")
		private String requestStatus;

		@ManyToOne
		@JoinColumn(name = "CLIENT_CODE")
		private Client client;

		@ManyToOne
		@JoinColumn(name = "EMP_CODE")
		private Emp emp;
				
		@ManyToOne
		@JoinColumn(name = "STORAGE_CODE")
		private Storage storage;

		@OneToMany
		@JoinColumn(name = "REQUEST_CODE")
		private List<RequestDetail> requestDetail;
		
		public Request() {}
		
		public Request(int requestCode, Date requestDate, String requestStatus, Client client, Emp emp,
				Storage storage, List<RequestDetail> requestDetail) {
			super();
			this.requestCode = requestCode;
			this.requestDate = requestDate;
			this.requestStatus = requestStatus;
			this.client = client;
			this.emp = emp;
			this.storage = storage;
			this.requestDetail = requestDetail;
		}

		public int getRequestCode() {
			return requestCode;
		}

		public void setRequestCode(int requestCode) {
			this.requestCode = requestCode;
		}

		public Date getRequestDate() {
			return requestDate;
		}

		public void setRequestDate(Date requestDate) {
			this.requestDate = requestDate;
		}

		public String getRequestStatus() {
			return requestStatus;
		}

		public void setRequestStatus(String requestStatus) {
			this.requestStatus = requestStatus;
		}

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}

		public Emp getEmp() {
			return emp;
		}

		public void setEmp(Emp emp) {
			this.emp = emp;
		}

		public Storage getStorage() {
			return storage;
		}

		public void setStorage(Storage storage) {
			this.storage = storage;
		}
		public List<RequestDetail> getRequestDetail() {
			return requestDetail;
		}



		public void setRequestDetail(List<RequestDetail> requestDetail) {
			this.requestDetail = requestDetail;
		}

		@Override
		public String toString() {
			return "Request [requestCode=" + requestCode + ", requestDate=" + requestDate + ", requestStatus="
					+ requestStatus + ", client=" + client + ", emp=" + emp + ", storage=" + storage
					+ ", requestDetail=" + requestDetail + "]";
		}

		
		
		
		
		
		
}
