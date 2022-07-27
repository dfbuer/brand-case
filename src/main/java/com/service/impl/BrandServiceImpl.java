package com.service.impl;

import com.buer.mapper.BrandMapper;
import com.buer.pojo.Brand;
import com.buer.pojo.PageBean;
import com.buer.util.SqlSessionFactoryUtils;
import com.service.BrandService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    //初始化工厂，创建SqlSessionFactory工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public List<Brand> selectAll() {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //调用方法
        List<Brand> brands = mapper.selectAll();

        //释放资源,查询方法不用提交事务
        sqlSession.close();

        return brands;
    }

    /**
     * 新增数据
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //调用方法
        mapper.add(brand);

        //提交事务
        sqlSession.commit();

        //释放资源
        sqlSession.close();
    }

    /**
     * 根据id集合批量删除
     * @param ids
     */
    @Override
    public void deleteByIds(int[] ids) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //调用方法
        mapper.deleteByIds(ids);

        //提交事务
        sqlSession.commit();

        //释放资源
        sqlSession.close();
    }

    /**
     * 分页
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @return
     */
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //查询当前页数
        List<Brand> rows = mapper.selectByPage(begin,size);

        //查询总记录数
        int totalCount = mapper.selectTotalCount();

        //封装pageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //释放资源
        sqlSession.close();

        return pageBean;
    }

    /**
     * 条件查询用的分页
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;

        //处理brand条件，模糊表达式
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0){
            brand.setBrandName("%"+brandName+"%");
        }

        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0){
            brand.setCompanyName("%"+companyName+"%");
        }


        //查询当前页数
        List<Brand> rows = mapper.selectByPageAndCondition(begin,size,brand);

        //查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);

        //封装pageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public void deleteById(int id) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //调用方法
        mapper.deleteById(id);

        //提交事务
        sqlSession.commit();

        //释放资源
        sqlSession.close();
    }



    @Override
    public void update(Brand brand) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //调用方法
        mapper.update(brand);

        //提交事务
        sqlSession.commit();

        //释放资源
        sqlSession.close();
    }


}
