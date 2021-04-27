package com.chat.dao;

import java.util.List;

import com.chat.bean.group;

/**
 * groupmapper接口
 *
 * @author 王俊钦、谢文昊
 *
 */

public interface GroupMapper {

	public List<group> selectgroup();// 搜索

	public void addgroup(group group);// 添加

	public void deletegroup(group group);// 删除
}
