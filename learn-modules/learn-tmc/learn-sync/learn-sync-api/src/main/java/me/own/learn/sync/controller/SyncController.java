package me.own.learn.sync.controller;

import me.own.learn.commons.base.utils.enums.EnumUtil;
import me.own.learn.sync.constant.SyncConstant;
import me.own.learn.sync.service.SignatureService;
import me.own.learn.sync.service.SyncService;
import me.own.learn.sync.vo.CountryVo;
import me.own.learn.sync.vo.SignatureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:simonye
 * @date 21:28 2019/4/16
 **/
@RestController
@RequestMapping(value = "/api/tmc/sync/data")
public class SyncController {

    @Autowired
    private SyncService syncService;

    @Autowired
    private SignatureService signatureService;

    @RequestMapping(value = "/signatures", method = RequestMethod.POST)
    public ResponseEntity getSignature(HttpServletRequest request) {
        List<Map<String, Object>> signatures = EnumUtil.getEnumNameValueList(SyncConstant.Signature.class);
        List<SignatureVo> vos = new ArrayList<>();
        for (Map<String, Object> signature : signatures) {
            SignatureVo signatureVo = signatureService.create((String) signature.get("name"));
            vos.add(signatureVo);
        }
        return new ResponseEntity(vos, HttpStatus.CREATED);
    }

    /**
     * @param request
     * @param countryNames
     * @return
     */
    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public ResponseEntity syncCountry(HttpServletRequest request,
                                      @RequestParam(required = false) String[] countryNames) {

        List<CountryVo> countryVos = syncService.syncCountries(countryNames);

        return new ResponseEntity(countryVos, HttpStatus.OK);
    }

    /**
     * @param request
     * @param cityNames
     * @return
     */
    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public ResponseEntity syncCity(HttpServletRequest request,
                                   @RequestParam String countryCode,
                                   @RequestParam(required = false) String[] cityNames) {
        CountryVo countryVo = syncService.syncCities(countryCode, cityNames);
        return new ResponseEntity(countryVo, HttpStatus.OK);
    }

}
