package com.chat.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.chat.bean.LinkMan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author //修改联系人
 *
 */
public class Maindao {

	// 会话工厂，连接数据库资源
	public static SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

	// @Test
	public static List<LinkMan> selectLinkmanall() {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = getSqlSessionFactory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			LinkManMapper mapper = openSession.getMapper(LinkManMapper.class);

			List<LinkMan> LinkMan = mapper.selectall();
			openSession.commit();
			openSession.close();
			return LinkMan;
		} finally {

		}
	}

	// @Test
	public /* static List<LinkMan> */void selectLinkmanall1() {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = getSqlSessionFactory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			LinkManMapper mapper = openSession.getMapper(LinkManMapper.class);

			List<LinkMan> LinkMan = mapper.selectall();
			openSession.commit();
			openSession.close();
			// return LinkMan;
		} finally {

		}
	}

	public static List<LinkMan> selectLinkmanbygro(String gro) {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = getSqlSessionFactory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			LinkManMapper mapper = openSession.getMapper(LinkManMapper.class);
			LinkMan linkman = new LinkMan(null, null, gro);
			List<LinkMan> LinkMan = mapper.selectbygro(linkman);
			openSession.commit();
			openSession.close();
			return LinkMan;
		} finally {

		}
	}

	public static ObservableList<LinkMan> oselectLinkmanbygro(String gro) {
		List<LinkMan> allLinkMan = null;
		allLinkMan = Maindao.selectLinkmanbygro(gro);
		ObservableList<LinkMan> data = FXCollections.observableArrayList(allLinkMan);
		return data;
	}

	public static ObservableList<LinkMan> oselectLinkmanall() {
		List<LinkMan> allLinkMan = null;
		allLinkMan = Maindao.selectLinkmanall();
		ObservableList<LinkMan> data = FXCollections.observableArrayList(allLinkMan);
		return data;
	}

	public static ObservableList<LinkMan> selectLinkmanallnpro() {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = getSqlSessionFactory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			LinkManMapper mapper = openSession.getMapper(LinkManMapper.class);

			List<LinkMan> LinkMan = mapper.selectallngro();
			ObservableList<LinkMan> oLinkMan = FXCollections.observableArrayList(LinkMan);
			openSession.commit();
			openSession.close();
			return oLinkMan;
		} finally {

		}
	}

	public static ObservableList<LinkMan> oselectLinkmanallnpro() {
		List<LinkMan> allLinkMan = null;
		allLinkMan = Maindao.selectLinkmanallnpro();
		ObservableList<LinkMan> data = FXCollections.observableArrayList(allLinkMan);
		return data;
	}

	// @Test
	public static ObservableList<LinkMan> selectLinkmanone(String name) {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = getSqlSessionFactory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			LinkManMapper mapper = openSession.getMapper(LinkManMapper.class);
			LinkMan linkman = new LinkMan(name, null);
			List<LinkMan> selectbyname = mapper.selectbyname(linkman);
			ObservableList<LinkMan> oselectbyname = FXCollections.observableArrayList(selectbyname);
			openSession.commit();
			openSession.close();
			return oselectbyname;
		} finally {

		}
	}

	// @Test
	public static boolean insertLinkmanone(LinkMan linkman) {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = getSqlSessionFactory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			LinkManMapper mapper = openSession.getMapper(LinkManMapper.class);
			mapper.insertone(linkman);
			System.out.println("添加成功");
			openSession.commit();
			openSession.close();
			return true;
		} finally {

		}
	}

	// @Test
	public void insertLinkmanmany() {
		for (int j = 0; j < 10; j++) {
			for (int i = 600; i < 100000; i++) {
				LinkMan linkman = new LinkMan(i + "王" + j, "男", j + "190397" + i, "315412" + i + j,
						"154121" + i + j + "@qq.com", "1999-01-21", "w", "0", null);
				insertLinkmanone(linkman);
			}
		}
	}

	// @Test
	public static void deleteLinkmanone(LinkMan linkman) throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			LinkManMapper mapper = openSession.getMapper(LinkManMapper.class);
			mapper.deleteone(linkman);
			System.out.println("删除成功");
			openSession.commit();
			openSession.close();

		} finally {

		}
	}

	/*
	 * <!-- public void updateonename(LinkMan linkman); --> <update
	 * id="updateonename"> UPDATE linkman SET `name`=#{name} WHERE `telephone` =
	 * #{telephone}; </update>
	 */
	// @Test
	public static void updataLinkmanonename(LinkMan linkman) throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			LinkManMapper mapper = openSession.getMapper(LinkManMapper.class);
			mapper.updateonename(linkman);
			System.out.println("修改成功");
			openSession.commit();
			openSession.close();

		} finally {

		}
	}
	/*
	 * <!-- public void updateonephone(LinkMan linkman);// 修改联系人 --> <update
	 * id="updateonephone" > UPDATE linkman SET `telephone`=#{telephone} WHERE
	 * `name` = #{name} and `QQ` = #{QQ}; </update>
	 */
	// @Test
		public static void updataLinkmanonephone(LinkMan linkman) throws IOException {
			SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
			SqlSession openSession = sqlSessionFactory.openSession();
			try {
				LinkManMapper mapper = openSession.getMapper(LinkManMapper.class);
				mapper.updateonephone(linkman);
				System.out.println("修改成功");
				openSession.commit();
				openSession.close();

			} finally {

			}
		}
	/*
	 * <!-- public void updateonenameqq(LinkMan linkman);// 修改联系人 --> <update
	 * id="updateonenameqq" > UPDATE linkman SET `QQ`=#{QQ} WHERE `telephone` =
	 * #{telephone}; </update>
	 */
		// @Test
		public static void updataLinkmanoneqq(LinkMan linkman) throws IOException {
			SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
			SqlSession openSession = sqlSessionFactory.openSession();
			try {
				LinkManMapper mapper = openSession.getMapper(LinkManMapper.class);
				mapper.updateonenameqq(linkman);
				System.out.println("修改成功");
				openSession.commit();
				openSession.close();

			} finally {

			}
		}
	/*
	 * <!-- public void updateoneemail(LinkMan linkman);// 修改联系人 --> <update
	 * id="updateoneemail" > UPDATE linkman SET `email`=#{email} WHERE
	 * `telephone` = #{telephone}; </update>
	 */
		// @Test
		public static void updataLinkmanoneemail(LinkMan linkman) throws IOException {
			SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
			SqlSession openSession = sqlSessionFactory.openSession();
			try {
				LinkManMapper mapper = openSession.getMapper(LinkManMapper.class);
				mapper.updateoneemail(linkman);
				System.out.println("修改成功");
				openSession.commit();
				openSession.close();

			} finally {

			}
		}
	/*
	 * <!-- public void updateonenamesex(LinkMan linkman);// 修改联系人 --> <update
	 * id="updateonenamesex" > UPDATE linkman SET `sex`=#{sex} WHERE `telephone`
	 * = #{telephone}; </update>
	 */
		// @Test
		public static void updataLinkmanonesex(LinkMan linkman) throws IOException {
			SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
			SqlSession openSession = sqlSessionFactory.openSession();
			try {
				LinkManMapper mapper = openSession.getMapper(LinkManMapper.class);
				mapper.updateonenamesex(linkman);
				System.out.println("修改成功");
				openSession.commit();
				openSession.close();

			} finally {

			}
		}
	/*
	 * <!-- public void updateonebirth(LinkMan linkman);// 修改联系人 --> <update
	 * id="updateonebirth" > UPDATE linkman SET `birthday`=#{birthday} WHERE
	 * `telephone` = #{telephone}; </update>
	 */
		// @Test
		public static void updataLinkmanonebirth(LinkMan linkman) throws IOException {
			SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
			SqlSession openSession = sqlSessionFactory.openSession();
			try {
				LinkManMapper mapper = openSession.getMapper(LinkManMapper.class);
				mapper.updateonebirth(linkman);
				System.out.println("修改成功");
				openSession.commit();
				openSession.close();

			} finally {

			}
		}
	/*
	 * <!-- public void updateonenamegroup(LinkMan linkman);// 修改联系人 --> <update
	 * id="updateonenamegroup" > UPDATE linkman SET `group`=#{group} WHERE
	 * `telephone` = #{telephone}; </update>
	 */
		// @Test
		public static void updataLinkmanonegroup(LinkMan linkman) throws IOException {
			SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
			SqlSession openSession = sqlSessionFactory.openSession();
			try {
				LinkManMapper mapper = openSession.getMapper(LinkManMapper.class);
				mapper.updateonenamegroup(linkman);
				System.out.println("修改成功");
				openSession.commit();
				openSession.close();

			} finally {

			}
		}
}
