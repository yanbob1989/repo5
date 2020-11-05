package yan.wei.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import yan.wei.pojo.Orders;
import yan.wei.pojo.Role;
import yan.wei.pojo.UserInfo;
import yan.wei.service.IUserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    //查询全部
    @RequestMapping("/findAll")
    @PostAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name="size",required = true,defaultValue = "4")Integer size){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> list = userService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    //添加用户
    @RequestMapping("/save")
    @PreAuthorize("authentication.principal.username=='cctv3'")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
       return "redirect:findAll.do";
    }

    //查询用户详情
    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        UserInfo userInfo = userService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    //根据用户关联角色操作
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true)String userId){

        UserInfo userInfo =userService.findById(userId);
        //根据用户id查询出没有的角色
        List<Role> roleList = userService.findOtherRoles(userId);

        ModelAndView mv = new ModelAndView();
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    //用户关联角色-添加角色
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name ="ids",required = true)String[] roles){
        userService.addRoleToUser(userId,roles);
        return "redirect:findAll.do";
    }

}
