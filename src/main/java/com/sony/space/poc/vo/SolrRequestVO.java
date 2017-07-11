package com.sony.space.poc.vo;

public class SolrRequestVO {

	private String mainCategory;
	private String locale;
	private String searchString;
	private Integer currentPage;
	private Integer maxRowsPerPage;
	
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
	
	
}
