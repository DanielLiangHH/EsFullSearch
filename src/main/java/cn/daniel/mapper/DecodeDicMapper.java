package cn.daniel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.cache.annotation.CacheConfig;

import cn.daniel.entity.DecodeDic;

@CacheConfig
public interface DecodeDicMapper {

    @Results(id = "decodeDicResults", value = {
            @Result(id = true, column = "ID", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "TYPE", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CODE", property = "code", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DECODE", property = "decode", jdbcType = JdbcType.VARCHAR) })
    @Select("SELECT ID,TYPE,CODE,DECODE FROM T_DECODE_DIC")
    List<DecodeDic> selectAll();

    @ResultMap("decodeDicResults")
    @Select("SELECT ID,TYPE,CODE,DECODE FROM T_DECODE_DIC WHERE TYPE = #{type}")
    List<DecodeDic> selectByType(@Param("type") String type);
}
