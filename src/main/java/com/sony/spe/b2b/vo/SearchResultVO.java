package com.sony.spe.b2b.vo;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;


import org.apache.solr.common.SolrDocumentList;

public class SearchResultVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private SolrDocumentList documentList;
	private LinkedHashMap<String, Map<String,Long>> facetResult;
	private LinkedHashMap<String, String> inputtagResult;
	private Long totalRows = 0l;

	public SolrDocumentList getDocumentList() {
		return documentList;
	}
	public void setDocumentList(SolrDocumentList documentList) {
		this.documentList = documentList;
	}
	public LinkedHashMap<String, Map<String, Long>> getFacetResult() {
		return facetResult;
	}
	public void setFacetResult(LinkedHashMap<String, Map<String, Long>> facetResult) {
		this.facetResult = facetResult;
	}
	public LinkedHashMap<String, String> getInputtagResult() {
		return inputtagResult;
	}
	public void setInputtagResult(LinkedHashMap<String, String> inputtagResult) {
		this.inputtagResult = inputtagResult;
	}
	public Long getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}

}
