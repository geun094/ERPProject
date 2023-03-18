package com.greedy.erp.regist.entity;

import javax.persistence.*;

@Entity
@Table(name = "STORAGE")
@SequenceGenerator(
		name = "STORAGE_SEQ_GENERATOR",
		sequenceName = "SEQ_STORAGE_CODE",
		initialValue = 1, allocationSize = 1
		)
public class Storage {

	@Id
	@Column(name = "STORAGE_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "STORAGE_SEQ_GENERATOR"
		)
	private int storageCode;
	
	@Column(name = "STORAGE_NAME")
	private String storageName;
	
	@Column(name = "STORAGE_TYPE")
	private String storageType;
	
	public Storage() {}

	public Storage(int storageCode, String storageName, String storageType) {
		super();
		this.storageCode = storageCode;
		this.storageName = storageName;
		this.storageType = storageType;
	}

	public int getStorageCode() {
		return storageCode;
	}

	public void setStorageCode(int storageCode) {
		this.storageCode = storageCode;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}

	@Override
	public String toString() {
		return "StorageDTO [storageCode=" + storageCode + ", storageName=" + storageName + ", storageType="
				+ storageType + "]";
	}
	
}
