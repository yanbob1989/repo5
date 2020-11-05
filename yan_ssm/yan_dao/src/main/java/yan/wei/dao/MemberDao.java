package yan.wei.dao;


import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import yan.wei.pojo.Member;

@Repository
public interface MemberDao {

    @Select("select * from member where id=#{id}")
    Member findById(String id) throws Exception;
}
