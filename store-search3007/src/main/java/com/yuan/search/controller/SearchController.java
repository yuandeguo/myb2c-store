package com.yuan.search.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yuan.param.ProductSearchParam;
import com.yuan.search.service.SearchService;
import com.yuan.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/4 15:43
 * @Description null
 */
@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    private SearchService searchService;
    @PostMapping("product")
    public R productList(@RequestBody ProductSearchParam productSearchParam) throws JsonProcessingException {


        return searchService.search(productSearchParam);
    }


}
