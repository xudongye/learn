package me.own.learn.role.service.impl;

import me.own.commons.base.dao.PageQueryResult;
import me.own.commons.base.dao.QueryConstants;
import me.own.commons.base.dao.QueryCriteriaUtil;
import me.own.commons.base.dao.QueryOrder;
import me.own.commons.base.utils.mapper.Mapper;
import me.own.learn.role.dao.PermissionDao;
import me.own.learn.role.dao.RoleDao;
import me.own.learn.role.dto.PermissionDto;
import me.own.learn.role.dto.RoleDto;
import me.own.learn.role.exception.PermissionDoNotGiveException;
import me.own.learn.role.exception.PermissionNotFoundException;
import me.own.learn.role.exception.RoleExistException;
import me.own.learn.role.exception.RoleNotFoundException;
import me.own.learn.role.po.Permission;
import me.own.learn.role.po.Role;
import me.own.learn.role.service.RoleQueryCondition;
import me.own.learn.role.service.RoleService;
import me.own.learn.role.vo.PermissionVo;
import me.own.learn.role.vo.RoleVo;
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
 * @date 2019/5/28 15:39
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    @Transactional(readOnly = true)
    public PageQueryResult<RoleVo> page(int pageNum, int pageSize, RoleQueryCondition condition) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Role.class);
        query.setDeletedFalseCondition();
        if (condition != null) {
            if (condition.getName() != null) {
                query.setSimpleCondition("name", condition.getName(), QueryConstants.SimpleQueryMode.Equal);
            }
            if (condition.getDescription() != null) {
                query.setSimpleCondition("description", condition.getDescription(), QueryConstants.SimpleQueryMode.Like);
            }
        }
        List<QueryOrder> orders = new ArrayList<>();
        QueryOrder order = new QueryOrder();
        order.setColumnName("id");
        order.setOder(QueryOrder.ASC);
        PageQueryResult<Role> result = roleDao.pageQuery(pageNum, pageSize, query, orders);
        return result.mapItems(RoleVo.class);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleVo getById(long roleId) {
        Role role = roleDao.get(roleId);
        if (role == null || role.getDeleted()) {
            throw new RoleNotFoundException();
        }
        return Mapper.Default().map(role, RoleVo.class);
    }

    @Override
    @Transactional
    public RoleVo create(RoleDto roleDto) {
        if (roleExist(roleDto.getName())) {
            throw new RoleExistException();
        }
        Role role = Mapper.Default().map(roleDto, Role.class);
        role.setDeleted(false);
        role.setCreateTime(new Date());
        roleDao.create(role);
        LOGGER.info("create new role {}", role.getName());
        return Mapper.Default().map(role, RoleVo.class);
    }

    private boolean roleExist(String name) {
        QueryCriteriaUtil query = new QueryCriteriaUtil(Role.class);
        query.setDeletedFalseCondition();
        query.setSimpleCondition("name", name, QueryConstants.SimpleQueryMode.Equal);
        return roleDao.getCount(query) > 0;
    }

    @Override
    @Transactional
    public RoleVo update(RoleDto roleDto) {
        Role role = roleDao.get(roleDto.getId());
        if (role == null || role.getDeleted()) {
            throw new RoleNotFoundException();
        }
        if (roleDto.getDescription() != null) {
            role.setDescription(roleDto.getDescription());
        }
        if (roleDto.getName() != null) {
            role.setName(roleDto.getName());
        }
        if (roleDto.getLevel() != 0) {
            role.setLevel(roleDto.getLevel());
        }
        roleDao.update(role);
        return Mapper.Default().map(role, RoleVo.class);
    }

    @Override
    @Transactional
    public void deleteRole(long id) {
        Role role = roleDao.get(id);
        if (role == null || role.getDeleted()) {
            throw new RoleNotFoundException();
        }
        role.setDeleted(true);
    }

    @Override
    @Transactional
    public RoleVo givePerm(long roleId, Long[] permIds) {
        Role role = roleDao.get(roleId);
        if (role == null || role.getDeleted()) {
            throw new RoleNotFoundException();
        }
        List<Permission> hadPerms = role.getPermissions();
        if (permIds != null && permIds.length != 0) {
            for (Long permId : permIds) {
                Permission permission = permissionDao.get(permId);
                //权限最小等级要低于角色等级，则可以授权，反之不可授权到该角色
                if (permission.getMinLevel() < role.getLevel() || hadPerms.contains(permission)) {
                    throw new PermissionDoNotGiveException();
                }
                hadPerms.add(permission);
            }
        }
        role.setPermissions(hadPerms);
        role.setModifyTime(new Date());
        LOGGER.info("role {} add new permissions {}", role.getName(), permIds);
        return Mapper.Default().map(role, RoleVo.class);
    }

    @Override
    @Transactional
    public PermissionVo create(PermissionDto permissionDto) {
        Permission permission = Mapper.Default().map(permissionDto, Permission.class);
        permission.setDeleted(false);
        permission.setCreateTime(new Date());
        permissionDao.create(permission);
        LOGGER.info("create new permission {}", permission.getName());
        return Mapper.Default().map(permission, PermissionVo.class);
    }

    @Override
    @Transactional
    public PermissionVo update(PermissionDto permissionDto) {
        Permission permission = permissionDao.get(permissionDto.getId());
        if (permission == null || permission.getDeleted()) {
            throw new PermissionNotFoundException();
        }
        if (permissionDto.getDescription() != null) {
            permission.setDescription(permissionDto.getDescription());
        }
        if (permission.getName() != null) {
            permission.setName(permissionDto.getName());
        }
        if (permission.getMinLevel() != 0) {
            permission.setMinLevel(permissionDto.getMinLevel());
        }
        permissionDao.update(permission);
        return Mapper.Default().map(permission, PermissionVo.class);
    }

    @Override
    @Transactional
    public void deletedPerm(long permId) {
        Permission permission = permissionDao.get(permId);
        if (permission == null || permission.getDeleted()) {
            throw new PermissionNotFoundException();
        }
        permission.setDeleted(true);
    }
}
