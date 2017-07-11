package com.sony.spe.b2b.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.sony.spe.b2b.vo.SearchResultVO;
import com.sony.spe.b2b.vo.SolrRequestVO;
import com.sony.spe.solr.service.SolrSearchService;
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ISIFSearchService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	private SolrSearchService solrSearchService;

	/**
	 * returns solr documents for the given request
	 * 
	 * @return
	 * @throws Exception
	 */
	public SearchResultVO getProductInformation(SolrRequestVO solrRequestVO) throws Exception {
		SearchResultVO searchResultVO = new SearchResultVO();
		try {
			searchResultVO =  solrSearchService.getResponseStringFromSolr(solrRequestVO);
		} catch (Exception e) {
			log.error("getProductInformation - Error occured : " + e.getMessage());
			return searchResultVO;
		}
		return searchResultVO;
	}

}
