package yan.wei.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import yan.wei.pojo.Traveller;

import java.util.List;

@Repository
public interface TravellerDao {

    @Select("select * from traveller where id in (select travellerid from order_traveller where orderid=#{orderId})")
    List<Traveller> findByOrderId(String orderId);
}
