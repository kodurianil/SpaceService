package com.sony.spe.solr.service;

import java.io.IOException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sony.spe.b2b.vo.SearchResultVO;
import com.sony.spe.b2b.vo.SolrRequestVO;

@Service
public class SolrSearchService {

	public HttpSolrClient solrInstance = null;

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("deprecation")
	public SearchResultVO getResponseStringFromSolr(SolrRequestVO solrRequestVO) {
		QueryResponse response = null;
		Integer maxRowsPerPage = solrRequestVO.getMaxRowsPerPage() != null ? solrRequestVO.getMaxRowsPerPage() : 30;
		Integer currentPage = solrRequestVO.getCurrentPage() != null ? solrRequestVO.getCurrentPage() : 0;
		SolrQuery solrQuery = new SolrQuery();
		StringBuilder query= new StringBuilder();
		SearchResultVO searchResultVO = new SearchResultVO();
		LinkedHashMap<String, Map<String, Long>> facetResult = new LinkedHashMap<>();
		try {
			solrQuery.setStart(currentPage);
			solrQuery.setRows(maxRowsPerPage);
			String mainCategory = solrRequestVO.getMainCategory();
			String locale = solrRequestVO.getLocale();
			String searchString = solrRequestVO.getSearchString();
			if(mainCategory != null && !"".equals(mainCategory)){
				query.append("mainCategory:"+mainCategory);
				if(locale != null && !"".equals(locale) && query != null && !query.toString().equals("")){
					query.append(" AND locale:"+locale);
				}else{
					query.append("");
				}
				if(searchString != null && !"".equals(searchString) && query != null && !query.toString().equals("")){
					query.append(" AND fullProductInfo:*"+ClientUtils.escapeQueryChars(searchString)+"*");
				}else{
					query.append("");
				}
				solrQuery.setQuery(query.toString());
			}else{
				solrQuery.setQuery("*:*");
			}
			generateFacetConditions(solrQuery);
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

	private String buildSolrInstance() {
		return "http://usdl013:6178/solr/space2";
	}

	public void getFacetFields(List<FacetField> fflist, LinkedHashMap<String, Map<String, Long>> resultFacet) {
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

	public void generateFacetConditions(SolrQuery solrQuery) {
		solrQuery.addFacetField("tvType");
		solrQuery.addFacetField("televisionScreenSize");
		solrQuery.addFacetField("tvFeatures");
		solrQuery.setFacetSort("index");
		solrQuery.setFacetMinCount(1);
	}
	
}
