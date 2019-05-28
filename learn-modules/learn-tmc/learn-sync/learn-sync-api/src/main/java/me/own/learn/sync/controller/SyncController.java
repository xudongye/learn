package me.own.learn.sync.controller;

import me.own.learn.commons.base.utils.enums.EnumUtil;
import me.own.learn.sync.constant.SyncConstant;
import me.own.learn.sync.service.SignatureService;
import me.own.learn.sync.service.SyncService;
import me.own.learn.sync.vo.*;
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

    @RequestMapping(value = "/signatures", method = RequestMethod.GET)
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
    @RequestMapping(value = "/countries-code", method = RequestMethod.GET)
    public ResponseEntity matchCountry(HttpServletRequest request,
                                       @RequestParam(required = false) String[] countryNames) {

        List<CountryVo> countryVos = syncService.matchCountries(countryNames);

        return new ResponseEntity(countryVos, HttpStatus.OK);
    }

    @RequestMapping(value = "/hotel-info", method = RequestMethod.GET)
    public ResponseEntity matchHotelInfo(HttpServletRequest request,
                                         @RequestParam String cityCode) {
        syncService.syncHotelInfos(cityCode);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/hotel-id", method = RequestMethod.GET)
    public ResponseEntity matchHotel(HttpServletRequest request,
                                     @RequestParam String cityCode) {
        syncService.syncHotels(cityCode);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


    /**
     * @param request
     * @param countryName
     * @param countryCode
     * @return
     */
    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public ResponseEntity syncCity(HttpServletRequest request,
                                   @RequestParam String countryCode,
                                   @RequestParam String countryName) {
        List<ProvinceVo> provinceVos = syncService.syncCities(countryCode, countryName);
        return new ResponseEntity(provinceVos, HttpStatus.OK);
    }

    @RequestMapping(value = "/business-district", method = RequestMethod.GET)
    public ResponseEntity syncBusinessDistrict(HttpServletRequest request,
                                               @RequestParam String cityCode) {

        Map<String, Object> data = new HashMap<>();
        List<DistrictKeyVo> districtKeyVos = syncService.syncDistricts(cityCode);
        List<BusinessKeyVo> businessKeyVos = syncService.syncBusiness(cityCode);
        data.put("district", districtKeyVos);
        data.put("business", businessKeyVos);
        return new ResponseEntity(data, HttpStatus.OK);
    }

}
