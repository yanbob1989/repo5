package yan.wei.service;

import yan.wei.pojo.Permission;
import yan.wei.pojo.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll(int page, int size);

    void save(Role role);

    List<Permission> findRoleByOtherPermission(String id);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
