package com.buer.mapper;

import com.buer.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Dao层
 */
public interface BrandMapper {

    /**
     * 查询所有
     * @return
     */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /**
     * 添加数据
     * @param brand
     */
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

    @Select("select * from tb_brand limit #{begin},#{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);

    @Select("select count(*) from tb_brand")
    int selectTotalCount();


    @ResultMap("brandResultMap")
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("brand") Brand brand);


    int selectTotalCountByCondition(Brand brand);

    /**
     * 删除单条数据
     */
    @Delete("delete from tb_brand where id = #{id}")
    void deleteById(int id);



    void update(Brand brand);
}
