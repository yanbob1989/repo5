package yan.wei.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yan.wei.dao.ProductDao;
import yan.wei.pojo.Product;
import yan.wei.service.IProductService;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll(int page, int size) throws Exception{
        PageHelper.startPage(page, size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}