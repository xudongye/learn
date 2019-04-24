package me.own.learn.sync.controller;

import me.own.learn.sync.service.SearchService;
import me.own.learn.sync.vo.BusinessKeyVo;
import me.own.learn.sync.vo.CityKeyVo;
import me.own.learn.sync.vo.DistrictKeyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yexudong
 * @date 2019/4/19 16:02
 */
@RestController
@RequestMapping(value = "/api/tmc/search/data")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/areaKey", method = RequestMethod.GET)
    public ResponseEntity getAreaStringByKey(HttpServletRequest request,
                                             @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                             @RequestParam(required = false) String keyword) {

        Page<CityKeyVo> areaKeyVos = searchService.findByCityKey(keyword, pageNum, pageSize);
        Page<DistrictKeyVo> districtKeyVos = searchService.findByDistrictKey(keyword, pageNum, pageSize);
        Page<BusinessKeyVo> businessKeyVos = searchService.findByBusinessKey(keyword, pageNum, pageSize);
        Map<String, Object> data = new HashMap<>();
        data.put("city", areaKeyVos);
        data.put("district", districtKeyVos);
        data.put("business", businessKeyVos);
        return new ResponseEntity(data, HttpStatus.OK);
    }
}
