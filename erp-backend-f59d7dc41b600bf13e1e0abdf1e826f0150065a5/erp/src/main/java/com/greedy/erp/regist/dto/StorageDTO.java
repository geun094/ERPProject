package com.greedy.erp.regist.dto;

public class StorageDTO {

	private int storageCode;
	private String storageName;
	private String storageType;
	
	public StorageDTO() {}

	public StorageDTO(int storageCode, String storageName, String storageType) {
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
