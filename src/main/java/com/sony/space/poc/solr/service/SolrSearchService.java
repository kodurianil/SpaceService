package com.sony.space.poc.solr.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrException;
import org.apache.solr.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sony.space.poc.vo.SearchResultVO;
import com.sony.space.poc.vo.SolrRequestVO;

@Service
public class SolrSearchService {

	public HttpSolrClient solrInstance = null;

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("deprecation")
	public SearchResultVO getResponseStringFromSolr(SolrRequestVO solrRequestVO) {
		QueryResponse response = null;
        
		Integer maxRowsPerPage = solrRequestVO.getMaxRowsPerPage() != null ? solrRequestVO.getMaxRowsPerPage() : 30;
		Integer currentPage = solrRequestVO.getCurrentPage() != null ? solrRequestVO.getCurrentPage() : 0;
		SearchResultVO searchResultVO = new SearchResultVO();
		LinkedHashMap<String, Map<String, Long>> facetResult = new LinkedHashMap<>();
		
		try {
			
			SolrQuery solrQuery = new SolrQuery();
			solrQuery.setStart(currentPage);
			//solrQuery.setRows(maxRowsPerPage);
			solrQuery.setQuery("*:*");
			
			String mainCategory = solrRequestVO.getMainCategory();
			if(!StringUtils.isEmpty(mainCategory)) {
				solrQuery.addFilterQuery("mainCategory:"+mainCategory);
			}
			
			String locale = solrRequestVO.getLocale();
			if(!StringUtils.isEmpty(locale)){
				solrQuery.addFilterQuery("locale:"+locale);
			}
			
			String searchString = solrRequestVO.getSearchString();
			if(!StringUtils.isEmpty(searchString)){
				solrQuery.addFilterQuery("fullProductInfo:*"+ClientUtils.escapeQueryChars(searchString)+"*");
			}
			
			List<String> tvType = solrRequestVO.getTvType();
			if(null != tvType && tvType.size() > 0) {
				solrQuery.addFilterQuery(getSolrInString("tvType", tvType));
			}
			
			List<String> televisionScreenSize = solrRequestVO.getTelevisionScreenSize();
			if(null != televisionScreenSize && televisionScreenSize.size() > 0) {
				solrQuery.addFilterQuery(getSolrInString("televisionScreenSize", televisionScreenSize));
			}
			
			List<String> tvFeatures = solrRequestVO.getTvFeatures();
			if(null != tvFeatures && tvFeatures.size() > 0) {
				solrQuery.addFilterQuery(getSolrInString("tvFeatures", tvFeatures));
			}
			
			List<String> cybershotBestFor = solrRequestVO.getCybershotBestFor();
			if(null != cybershotBestFor && cybershotBestFor.size() > 0) {
				solrQuery.addFilterQuery(getSolrInString("cybershotBestFor", cybershotBestFor));
			}
			
			List<String> cybershotFeatures = solrRequestVO.getCybershotFeatures();
			if(null != cybershotFeatures && cybershotFeatures.size() > 0) {
				solrQuery.addFilterQuery(getSolrInString("cybershotFeatures", cybershotFeatures));
			}
			
			List<String> cybershotMegapixels = solrRequestVO.getCybershotMegapixels();
			if(null != cybershotMegapixels && cybershotMegapixels.size() > 0) {
				solrQuery.addFilterQuery(getSolrInString("cybershotMegapixels", cybershotMegapixels));
			}
			
			List<String> cameraType = solrRequestVO.getCameraType();
			if(null != cameraType && cameraType.size() > 0) {
				solrQuery.addFilterQuery(getSolrInString("cameraType",cameraType));
			}
			
			generateFacetConditions(mainCategory, solrQuery);
			solrInstance = new HttpSolrClient(buildSolrInstance());
			LOG.info("SolrQuery Before hitting Solr : " + solrQuery);
			try{
				response = solrInstance.query(solrQuery);
			}catch(SolrException se){
				return searchResultVO;
			}
			if(response != null){
				searchResultVO.setDocumentList(response.getResults());
				searchResultVO.setTotalRows(response.getResults().getNumFound());
				getFacetFields(response.getFacetFields(), facetResult);
				searchResultVO.setFacetResult(facetResult);
			}
			LOG.info("Request finished from Solr : " + System.currentTimeMillis());
		} catch (SolrServerException e) {
			LOG.error("Solr Exception occured while getting response from Solr ", e);
		} catch (IOException e) {
			LOG.error("Exception occured while getting response ", e);
		}
		return searchResultVO;
	}

	private static String getSolrInString(String fieldName, List<String> strList) {
		StringBuffer fieldQuery = new StringBuffer();
		for(String str: strList) {
			if(fieldQuery.length() != 0) {
				fieldQuery.append(" AND ");
			}
			fieldQuery.append(fieldName+":"+ClientUtils.escapeQueryChars(str));
		}
		System.out.println(fieldQuery);
		return fieldQuery.toString();
	}
	
	private String buildSolrInstance() {
		return "http://localhost:8983/solr/space2";
	}

	private void getFacetFields(List<FacetField> fflist, LinkedHashMap<String, Map<String, Long>> resultFacet) {
		if (fflist != null) {
			for (FacetField ff : fflist) {
				getFinalFacets(resultFacet, ff);
			}
		}
	}

	private void getFinalFacets(LinkedHashMap<String, Map<String, Long>> resultFacet, FacetField ff) {
		String ffname = ff.getName();
		List<Count> counts = ff.getValues();
		LinkedHashMap<String, Long> res = new LinkedHashMap<String, Long>();
		for (Count c : counts) {
			String facetLabel = c.getName();
			long facetCount = c.getCount();
			res.put(facetLabel, facetCount);
		}
		if (!counts.isEmpty()) {
			resultFacet.put(ffname, res);
		}
	}

	public void generateFacetConditions(String mainCategory, SolrQuery solrQuery) {
		
		if ("televisions".equals(mainCategory)) {
			solrQuery.addFacetField("tvType");
			solrQuery.addFacetField("televisionScreenSize");
			solrQuery.addFacetField("tvFeatures");
		} else if ("cameras".equals(mainCategory)) {
			solrQuery.addFacetField("cameraType");
			solrQuery.addFacetField("cybershotBestFor");
			solrQuery.addFacetField("cybershotFeatures");
			solrQuery.addFacetField("cybershotMegapixels");
		}
		solrQuery.setFacetSort("index");
		solrQuery.setFacetMinCount(1);
	}
	
	/*public static void main(String[] args) {
		List<String> strList = new ArrayList<String>();
		strList.add("Android TV");
		strList.add("Full HD");
		strList.add("LED");
		getSolrInString("tvType", strList);
	}*/
	
}
