package me.own.learn.sync.service;

import me.own.learn.sync.bo.BusinessKeyBo;
import me.own.learn.sync.bo.CityKeyBo;
import me.own.learn.sync.bo.DistrictKeyBo;
import me.own.learn.sync.vo.BusinessKeyVo;
import me.own.learn.sync.vo.CityKeyVo;
import me.own.learn.sync.vo.DistrictKeyVo;
import org.springframework.data.domain.Page;


/**
 * @author yexudong
 * @date 2019/4/19 16:03
 */
public interface SearchService {

    Page<CityKeyVo> findByCityKey(String keyword, int pageNum, int pageSize);

    Page<DistrictKeyVo> findByDistrictKey(String keyword, int pageNum, int pageSize);

    Page<BusinessKeyVo> findByBusinessKey(String keyword, int pageNum, int pageSize);

    CityKeyVo insertCityKey(CityKeyBo cityKeyBo);

    DistrictKeyVo insertDistrictKey(DistrictKeyBo districtKeyBo);

    BusinessKeyVo insertBusinessKey(BusinessKeyBo businessKeyBo);

}
