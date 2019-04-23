package cn.daniel.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;

@CacheConfig
public interface LayerDataMapper {

    @Select("SELECT * FROM ${tablename} WHERE ROWNUM < 10")
    List<Map<String, Object>> selectByTableName(@Param("tablename") String tablename);

    @Select("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (SELECT * FROM ${owner}.${tablename}) A WHERE ROWNUM <= ${endIndex}) WHERE RN > ${startIndex}")
    List<Map<String, Object>> selectByTableNameWithPage(@Param("owner") String owner,
            @Param("tablename") String tablename, @Param("startIndex") Integer startIndex,
            @Param("endIndex") Integer endIndex);

    @Select("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (SELECT * FROM ${owner}.${tablename} ORDER BY ${orderBy} DESC) A WHERE ROWNUM <= ${endIndex}) WHERE RN > ${startIndex}")
    List<Map<String, Object>> selectByTableNameWithPageOrderDesc(String owner, String tablename, String orderBy,
            Integer startIndex, Integer endIndex);

    @Select("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (SELECT * FROM ${owner}.${tablename} ORDER BY ${orderBy}) A WHERE ROWNUM <= ${endIndex}) WHERE RN > ${startIndex}")
    List<Map<String, Object>> selectByTableNameWithPageOrderAsc(String owner, String tablename, String orderBy,
            Integer startIndex, Integer endIndex);
}
