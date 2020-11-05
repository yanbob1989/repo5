package yan.wei.service;

import yan.wei.pojo.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll(int page,int size) throws Exception;

    void save(Product product);
}
