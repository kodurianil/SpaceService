package com.sony.space.poc.vo;

import java.io.Serializable;
import java.util.List;

public class SearchTitlesVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pageNumber;
    private int rowsPerPage;
    private String lob;
    private String gpmsIds;
    private String letter;
    private Integer genre;
    private Integer relYear;
    private String searchName;
    private Integer searchCategory;
    private List<Integer> progTypeIds;
    private String sortIndex;
    private String sortOrder;
    private boolean isInternalAndAdminSearch;
    private Boolean isAdminUser;
    private Boolean isAdminCheck;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public String getGpmsIds() {
        return gpmsIds;
    }

    public void setGpmsIds(String gpmsIds) {
        this.gpmsIds = gpmsIds;
    }

    public String getLob() {
        return lob;
    }

    public void setLob(String lob) {
        this.lob = lob;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Integer getRelYear() {
        return relYear;
    }

    public void setRelYear(Integer relYear) {
        this.relYear = relYear;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public List<Integer> getProgTypeIds() {
        return progTypeIds;
    }

    public void setProgTypeIds(List<Integer> progTypeIds) {
        this.progTypeIds = progTypeIds;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public Integer getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(Integer searchCategory) {
        this.searchCategory = searchCategory;
    }

	public String getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(String sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public boolean isInternalAndAdminSearch() {
		return isInternalAndAdminSearch;
	}

	public void setInternalAndAdminSearch(boolean isInternalAndAdminSearch) {
		this.isInternalAndAdminSearch = isInternalAndAdminSearch;
	}

	public Boolean getIsAdminUser() {
		return isAdminUser;
	}

	public void setIsAdminUser(Boolean isAdminUser) {
		this.isAdminUser = isAdminUser;
	}

	public Boolean getIsAdminCheck() {
		return isAdminCheck;
	}

	public void setIsAdminCheck(Boolean isAdminCheck) {
		this.isAdminCheck = isAdminCheck;
	}
}
