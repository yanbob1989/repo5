package yan.wei.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import yan.wei.pojo.Role;
import yan.wei.pojo.UserInfo;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll(int page, int size);

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findOtherRoles(String userId);

    void addRoleToUser(String userId, String[] roles);
}
