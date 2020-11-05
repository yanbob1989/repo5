package yan.wei.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yan.wei.dao.RoleDao;
import yan.wei.pojo.Permission;
import yan.wei.pojo.Role;
import yan.wei.service.IRoleService;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleDao roleDao;

    //查询全部
    @Override
    public List<Role> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    //保存
    @Override
    public void save(Role role) {
       roleDao.save(role);
    }

    //找到角色没有的权限
    @Override
    public List<Permission> findRoleByOtherPermission(String id) {
        return roleDao.findRoleByOtherPermission(id);
    }

    //保存权限
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }



}
