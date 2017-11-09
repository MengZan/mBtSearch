package cn.mayzan.mBtSearch.entity;

import java.util.List;

public class BtInfo {

	private String name;
	private String magnetUrl;
	private String btUrl;
	private String size;
	private String createDate;
	private List<File> files;
	private int pv;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMagnetUrl() {
		return magnetUrl;
	}

	public void setMagnetUrl(String magnetUrl) {
		this.magnetUrl = magnetUrl;
	}

	public String getBtUrl() {
		return btUrl;
	}

	public void setBtUrl(String btUrl) {
		this.btUrl = btUrl;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

}
