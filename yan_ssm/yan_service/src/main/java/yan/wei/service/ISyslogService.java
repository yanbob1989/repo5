package yan.wei.service;

import yan.wei.pojo.SysLog;

import java.util.List;

public interface ISyslogService {
    void save(SysLog sysLog);

    List<SysLog> findAll(int page, int size);
}
