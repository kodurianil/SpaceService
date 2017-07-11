package com.sony.spe.b2b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sony.spe.b2b.service.ISIFSearchService;
import com.sony.spe.b2b.vo.SearchResultVO;
import com.sony.spe.b2b.vo.SolrRequestVO;

@RestController
public class ISIFController {
	
	@Autowired
	ISIFSearchService isifSearchService;
	
	@RequestMapping(value = "/rest/searchProduct", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public SearchResultVO searchProduct(@RequestBody SolrRequestVO solrRequestVO) throws Exception {
        try {
            return isifSearchService.getProductInformation(solrRequestVO);
        } catch (Exception e) {	
            return new SearchResultVO();
        }
    }
}
