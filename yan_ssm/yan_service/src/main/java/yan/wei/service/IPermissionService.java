package yan.wei.service;

import yan.wei.pojo.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll(int page, int size);

    void save(Permission permission);
}
