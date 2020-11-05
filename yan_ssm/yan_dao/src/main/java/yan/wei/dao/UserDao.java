package yan.wei.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import yan.wei.pojo.Role;
import yan.wei.pojo.UserInfo;

import java.util.List;


@Repository
public interface UserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id=true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id",javaType = java.util.List.class, many=@Many(select = "yan.wei.dao.RoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username);

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users(username,password,email,phoneNum,status)values(#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id=true, property = "id" , column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id",javaType = java.util.List.class, many =@Many(select = "yan.wei.dao.RoleDao.findRoleByUserId")),
    })
    UserInfo findById(String id);

    @Select("select * from role where id not in (select roleid from users_role where userid= #{userId})")
    List<Role> findOtherRoles(String userId);

    @Insert("insert into users_role (userid,roleid)values(#{userIds},#{roleIds})")
    void addRoleToUser(@Param("userIds") String userId, @Param("roleIds")String roleId);
}
