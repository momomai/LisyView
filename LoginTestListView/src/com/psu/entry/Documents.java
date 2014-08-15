package com.psu.entry;

public class Documents {

	public String getTpiNo() {
		return TpiNo;
	}
	public void setTpiNo(String tpiNo) {
		TpiNo = tpiNo;
	}
	public String getRfrNo() {
		return RfrNo;
	}
	public void setRfrNo(String rfrNo) {
		RfrNo = rfrNo;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDocType() {
		return DocType;
	}
	public void setDocType(String docType) {
		DocType = docType;
	}
	private String TpiNo;
	private String RfrNo;
	private String Title;
	private String DocType;
}

