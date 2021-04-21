package com;

import generator.entity.Category;
import generator.entity.CategoryExample;
import generator.mapper.CategoryMapper;
import generator.mapper.CategoryMapperExt;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SqlSession session = null;
        SqlSession batchSession = null;
        SqlSession reUseSession = null;
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            session = sqlSessionFactory.openSession();
            batchSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
            reUseSession = sqlSessionFactory.openSession(ExecutorType.REUSE);

            // 不用接口的方式
            // User user = session.selectOne("com.dao.UserMapper.selectUser",1);
            CategoryMapperExt categoryMapperExt = reUseSession.getMapper(CategoryMapperExt.class);
//            Category category = categoryMapperExt.selectByPrimaryKey(1L);

            CategoryExample categoryExample = new CategoryExample();

//            categoryExample.setOrderByClause("criterion_score desc");
            categoryExample.createCriteria().andNameLike("_全%");
            List<Category> list= categoryMapperExt.selectByExample(categoryExample);

            System.out.println(list);
            reUseSession.commit();
            session.commit();
            batchSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (batchSession != null) {
                batchSession.close();
            }
            if (reUseSession != null) {
                reUseSession.close();
            }
        }
    }
}