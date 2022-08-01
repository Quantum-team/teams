package mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.cache.decorators.FifoCache;
import pojo.User;

import java.util.List;

@CacheNamespace(
        eviction= FifoCache.class,
        flushInterval=60000,
        size=512,
        readWrite = true
)
/*
* 取数据时机:取数据时先从二级缓存中取，如果没有再去一级缓存中取。
* */

public interface UserMapper {
    //查询全部
    List<User> findAll();

/*    //根据id查用户
    User findById(Long id);

//    User findById1(Long id);

    int delById(Long id);*/

}
