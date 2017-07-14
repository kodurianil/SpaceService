package com.sony.space.poc.vo;

import java.util.List;

public class SolrRequestVO {

	private String mainCategory;
	private String locale;
	private String searchString;
	private Integer currentPage;
	private Integer maxRowsPerPage;
    
    private List<String> tvType;
    private List<String> televisionScreenSize;
    private List<String> tvFeatures;
    
    private List<String> cybershotBestFor;
    private List<String> cybershotFeatures;
    private List<String> cybershotMegapixels;
    private List<String> cameraType;
	
	public String getMainCategory() {
		return mainCategory;
	}
	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getMaxRowsPerPage() {
		return maxRowsPerPage;
	}
	public void setMaxRowsPerPage(Integer maxRowsPerPage) {
		this.maxRowsPerPage = maxRowsPerPage;
	}
	public List<String> getTvType() {
		return tvType;
	}
	public void setTvType(List<String> tvType) {
		this.tvType = tvType;
	}
	public List<String> getTelevisionScreenSize() {
		return televisionScreenSize;
	}
	public void setTelevisionScreenSize(List<String> televisionScreenSize) {
		this.televisionScreenSize = televisionScreenSize;
	}
	public List<String> getTvFeatures() {
		return tvFeatures;
	}
	public void setTvFeatures(List<String> tvFeatures) {
		this.tvFeatures = tvFeatures;
	}
	public List<String> getCybershotBestFor() {
		return cybershotBestFor;
	}
	public void setCybershotBestFor(List<String> cybershotBestFor) {
		this.cybershotBestFor = cybershotBestFor;
	}
	public List<String> getCybershotFeatures() {
		return cybershotFeatures;
	}
	public void setCybershotFeatures(List<String> cybershotFeatures) {
		this.cybershotFeatures = cybershotFeatures;
	}
	public List<String> getCybershotMegapixels() {
		return cybershotMegapixels;
	}
	public void setCybershotMegapixels(List<String> cybershotMegapixels) {
		this.cybershotMegapixels = cybershotMegapixels;
	}
	public List<String> getCameraType() {
		return cameraType;
	}
	public void setCameraType(List<String> cameraType) {
		this.cameraType = cameraType;
	}
	
}
