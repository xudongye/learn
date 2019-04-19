package me.own.learn.sync.controller;

import me.own.learn.sync.service.SearchService;
import me.own.learn.sync.vo.CountryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yexudong
 * @date 2019/4/19 16:02
 */
@RestController
@RequestMapping(value = "/api/tmc/search/data")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/country", method = RequestMethod.GET)
    public ResponseEntity getSignature(HttpServletRequest request,
                                       @RequestParam(required = false) String cn) {

        List<CountryVo> countryVos = searchService.findByCountryName(cn);

        return new ResponseEntity(countryVos, HttpStatus.OK);
    }
}
