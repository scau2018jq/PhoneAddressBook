package com.chat.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.chat.bean.fileopen;

public class filedao {
	public static SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

	//@Test
	public static void savefile(String Sfileopen) {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = getSqlSessionFactory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			fileMapper mapper = openSession.getMapper(fileMapper.class);
			fileopen fileopen1 = new fileopen(Sfileopen);
			mapper.savefile1(fileopen1);
			openSession.commit();
			openSession.close();
			/*return LinkMan;*/
		} finally {

		}
	}

	@Test
	public static void openfile(String Sfileopen) {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = getSqlSessionFactory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			fileMapper mapper = openSession.getMapper(fileMapper.class);
			fileopen fileopen1 = new fileopen(Sfileopen);
			mapper.openfile1(fileopen1);
			openSession.commit();
			openSession.close();
			/*return LinkMan;*/
		} finally {

		}
	}
}
