package yan.wei.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import yan.wei.pojo.Orders;
import yan.wei.pojo.Product;

import java.util.List;

@Repository
public interface ProductDao {

    @Select("select * from product where id = #{id}")
    public Product findById(String id);


    @Select("select * from product")
    @Results({
            @Result(id=true,property ="id",column = "id"),
            @Result(property = "productNum",column = "productNum"),
            @Result(property = "productName",column = "productName"),
            @Result(property = "cityName",column = "cityName"),
            @Result(property = "departureTime",column = "departureTime"),
            @Result(property = "productStatus",column = "productStatus"),
            @Result(property = "productPrice",column = "productPrice"),
            @Result(property = "productDesc",column = "productDesc"),
            @Result(property = "orders",column = "ORDERSID",javaType = Orders.class, one=@One(select = "yan.wei.dao.OrdersDao.findOrdersById"))
    })
    List<Product> findAll();

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
