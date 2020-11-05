package yan.wei.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yan.wei.dao.SysLogDao;
import yan.wei.pojo.SysLog;
import yan.wei.service.ISyslogService;

import java.util.List;

@Service
@Transactional
public class SyslogServiceImpl implements ISyslogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }
}
