package com.chat.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.chat.bean.group;

/**
 * group数据库操作
 *
 * @author 王俊钦、谢文昊
 *
 */

public class Groupdao {
	public static SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

	/*
	 * 搜索所有group
	 */
	// @Test
	public static List<group> selectgroup() {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = getSqlSessionFactory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			GroupMapper mapper = openSession.getMapper(GroupMapper.class);

			List<group> group = mapper.selectgroup();
			group.forEach(System.out::println);
			openSession.commit();
			openSession.close();
			return group;
		} finally {

		}
	}

	/*
	 * 添加group
	 */
	//@Test
	public static void addgroup1(String group1) {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = getSqlSessionFactory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			GroupMapper mapper = openSession.getMapper(GroupMapper.class);
			group group = new group(group1);
			mapper.addgroup(group);
			openSession.commit();
			openSession.close();

		} finally {

		}
	}

	//@Test
	public static boolean deletegroup1(String group1) {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = getSqlSessionFactory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			GroupMapper mapper = openSession.getMapper(GroupMapper.class);
			group group = new group(group1);
			mapper.deletegroup(group);
			openSession.commit();
			openSession.close();
			return true;
		} finally {

		}
	}

}
