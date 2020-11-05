package yan.wei.test;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import yan.wei.dao.RoleDao;
import yan.wei.pojo.Role;

import java.util.List;

public class TestRoleDao {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext");
        RoleDao roleDao = context.getBean(RoleDao.class);
        List<Role> list = roleDao.findAll();
        for (Role role : list) {
            System.out.println(role.getRoleName());
        }
    }
}
