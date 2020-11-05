package yan.wei.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yan.wei.dao.OrdersDao;
import yan.wei.pojo.Orders;
import yan.wei.service.IOrdersService;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //参数pagenumber页码，pagesize条数
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String orderId) throws Exception {
        return ordersDao.findByOrderId(orderId);
    }
}
