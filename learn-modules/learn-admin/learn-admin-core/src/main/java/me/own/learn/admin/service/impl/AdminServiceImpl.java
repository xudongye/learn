package me.own.learn.admin.service.impl;

import me.own.learn.admin.dao.AdminDao;
import me.own.learn.admin.dto.AdminDto;
import me.own.learn.admin.exception.AdminNameExistException;
import me.own.learn.admin.exception.AdminNotFoundException;
import me.own.learn.admin.exception.CellphoneExistException;
import me.own.learn.admin.exception.EmailExistException;
import me.own.learn.admin.po.Admin;
import me.own.learn.admin.service.AdminQueryCondition;
import me.own.learn.admin.service.AdminService;
import me.own.learn.admin.vo.AdminVo;
import me.own.learn.commons.base.dao.PageQueryResult;
import me.own.learn.commons.base.dao.QueryConstants;
import me.own.learn.commons.base.dao.QueryCriteriaUtil;
import me.own.learn.commons.base.dao.QueryOrder;
import me.own.learn.commons.base.utils.encode.MD5;
import me.own.learn.commons.base.utils.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author yexudong
 * @date 2019/5/28 14:25
 */
@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminDao adminDao;

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<AdminVo> page(int pageNum, int pageSize, AdminQueryCondition condition) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Admin.class);
        query.setDeletedFalseCondition();
        if (condition != null) {
            if (condition.getCellphone() != null) {
                query.setSimpleCondition("cellphone", condition.getCellphone(), QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getEmail() != null) {
                query.setSimpleCondition("email", condition.getCellphone(), QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getName() != null) {
                query.setSimpleCondition("name", condition.getName(), QueryConstants.SimpleQueryMode.Like);
            }
        }
        //FIXME
        List<QueryOrder> orders = new ArrayList<>();
        QueryOrder order = new QueryOrder();
        order.setColumnName("createTime");
        order.setOder(QueryOrder.DESC);
        PageQueryResult<Admin> result = adminDao.pageQuery(pageNum, pageSize, query, orders);
        return result.mapItems(AdminVo.class);
    }

    @Override
    @Transactional
    public AdminVo create(AdminDto adminDto) {
        if (nameExist(adminDto.getName())) {
            throw new AdminNameExistException();
        }
        if (cellphoneExist(adminDto.getCellphone())) {
            throw new CellphoneExistException();
        }
        if (emailExist(adminDto.getEmail())) {
            throw new EmailExistException();
        }
        Admin admin = Mapper.Default().map(adminDto, Admin.class);
        admin.setCreateTime(new Date());
        admin.setDeleted(false);
        admin.setPassword(MD5.getMD5Code(adminDto.getPassword()));
        adminDao.create(admin);
        LOGGER.info("create new admin {}", admin.getName());
        return Mapper.Default().map(admin, AdminVo.class);
    }

    @Override
    @Transactional
    public AdminVo update(AdminDto adminDto) {
        Admin admin = adminDao.get(adminDto.getId());
        if (admin == null || admin.getDeleted()) {
            throw new AdminNotFoundException();
        }
        if (adminDto.getCellphone() != null) {
            if (cellphoneExist(adminDto.getCellphone())) {
                throw new CellphoneExistException();
            }
            admin.setCellphone(adminDto.getCellphone());
        }
        if (adminDto.getEmail() != null) {
            if (emailExist(adminDto.getEmail())) {
                throw new EmailExistException();
            }
            admin.setEmail(adminDto.getEmail());
        }
        if (adminDto.getHeadImg() != null) {
            admin.setHeadImg(adminDto.getHeadImg());
        }
        if (adminDto.getName() != null && !admin.getName().equals(adminDto.getName())) {
            if (nameExist(adminDto.getName())) {
                throw new AdminNameExistException();
            }
            admin.setName(adminDto.getName());
        }
        admin.setModifyTime(new Date());
        adminDao.update(admin);
        return Mapper.Default().map(admin, AdminVo.class);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Admin admin = adminDao.get(id);
        if (admin == null || admin.getDeleted()) {
            throw new AdminNotFoundException();
        }
        admin.setDeleted(true);
    }

    @Override
    @Transactional
    public AdminVo bindRole(long adminId, long roleId) {
        Admin admin = adminDao.get(adminId);
        if (admin == null || admin.getDeleted()) {
            throw new AdminNotFoundException();
        }
        admin.setRoleId(roleId);
        admin.setModifyTime(new Date());
        adminDao.update(admin);
        LOGGER.info("admin bind role {}", roleId);
        return Mapper.Default().map(admin, AdminVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public AdminVo getByLoginLabel(String loginLabel) {
        Admin admin = adminDao.getByLoginLabel(loginLabel);
        if (admin == null || admin.getDeleted()) {
            throw new AdminNotFoundException();
        }
        return Mapper.Default().map(admin, AdminVo.class);
    }

    private boolean nameExist(String name) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Admin.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("name", name, QueryConstants.SimpleQueryMode.Equal);
        return adminDao.getCount(query) > 0;
    }

    private boolean cellphoneExist(String cellphone) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Admin.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("cellphone", cellphone, QueryConstants.SimpleQueryMode.Equal);
        return adminDao.getCount(query) > 0;
    }

    private boolean emailExist(String email) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Admin.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("email", email, QueryConstants.SimpleQueryMode.Equal);
        return adminDao.getCount(query) > 0;
    }
}
