package com.chat.dao;

import java.util.List;

import com.chat.bean.LinkMan;

/**
 * LinkManMapper接口
 *
 * @author 王俊钦、谢文昊
 *
 */

public interface LinkManMapper {

	public List<LinkMan> selectall();// 所有联系人

	public List<LinkMan> selectallngro();// 未分组联系人

	public List<LinkMan> selectbygro(LinkMan linkman);//按分组来搜索

	public List<LinkMan> selectbyname(LinkMan linkman);// 通过名字搜索

	public List<LinkMan> selectbytele(LinkMan linkman);// 通过手机搜索

	public void deleteone(LinkMan linkman);// 删除

	public void insertone(LinkMan linkman);// 添加

	public void updateone(LinkMan linkman);// 修改联系人

	public void updateonename(LinkMan linkman);// 修改联系人

	public void updateonephone(LinkMan linkman);// 修改联系人

	public void updateonenameqq(LinkMan linkman);// 修改联系人

	public void updateoneemail(LinkMan linkman);// 修改联系人

	public void updateonenamesex(LinkMan linkman);// 修改联系人

	public void updateonebirth(LinkMan linkman);// 修改联系人

	public void updateonenamegroup(LinkMan linkman);// 修改联系人

}
