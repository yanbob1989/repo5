package yan.wei.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import yan.wei.pojo.SysLog;
import yan.wei.service.ISyslogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date visitTime;//开始时间
    private Class claszz; // 访问类
    private Method method;


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISyslogService syslogService;

    //前置通知
    @Before("execution(* yan.wei.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        claszz = jp.getTarget().getClass();//具体访问类
        //获取执行的方法名称
        String methodName = jp.getSignature().getName();//获取访问的方法名称
        //获取访问的方法参数
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0) {
            method = claszz.getMethod(methodName);//只能获取无参的方法
        }else {
            Class[] classArgs = new Class[args.length];
            for(int i=0; i< args.length;i++){
                classArgs[i]=args[i].getClass();
            }
            //封装参数
            claszz.getMethod(methodName,classArgs);
        }
    }

    //后置通知
    @After("execution(* yan.wei.controller.*.*(..))")
    public void doBefore() throws Exception {
       long time = new Date().getTime() - visitTime.getTime();//访问时长
        //获取操作的url
        String url ="";

        if(claszz !=null && method!=null && claszz!=LogAop.class){
            //获取类上的requestMapping注解， 获取注解里的内容
            RequestMapping classAnnotation = (RequestMapping) claszz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                String[] classValue = classAnnotation.value();//获取类上的RequestMapping的value值
                //获取方法上的RequestMapping注解
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                //取出value值
                String[] methodValue = methodAnnotation.value();
                //拼接
                url = classValue[0] + methodValue[0];

                String ip = request.getRemoteAddr();//获取访问的ip地址

                //获取当前操作的用户
                SecurityContext context = SecurityContextHolder.getContext();
                //获取当前操作的用户对象
                User principal = (User) context.getAuthentication().getPrincipal();
                //获取用户名
                String username = principal.getUsername();
                SysLog sysLog = new SysLog();
                sysLog.setIp(ip);//ip地址
                sysLog.setExecutionTime(time);//执行时长
                sysLog.setMethod("[类名]"+claszz.getName()+"[方法名]"+method.getName());//访问方法
                sysLog.setUrl(url);//请求url
                sysLog.setUsername(username);//当前访问的用户
                sysLog.setVisitTime(visitTime);//访问的时间
                syslogService.save(sysLog);

            }
        }

    }
}
