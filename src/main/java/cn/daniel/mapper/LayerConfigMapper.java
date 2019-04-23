package cn.daniel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.cache.annotation.CacheConfig;

import cn.daniel.entity.LayerConfig;

@CacheConfig
public interface LayerConfigMapper {

    @Results(id = "layerConfigResults", value = {
            @Result(id = true, column = "ID", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "OWNER", property = "owner", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TABLENAME", property = "tablename", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FIELD_ID", property = "field_id", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FIELD_UPDATETIME", property = "field_updatetime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FIELD_X", property = "field_x", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FIELD_Y", property = "field_y", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FIELD_LABEL", property = "field_label", jdbcType = JdbcType.VARCHAR) })
    @Select("SELECT ID,OWNER,TABLENAME,FIELD_ID,FIELD_UPDATETIME,FIELD_X,FIELD_Y,FIELD_LABEL FROM T_LAYER_CONFIG")
    List<LayerConfig> selectAll();
}
