package com.chat.service;

import java.util.HashMap;
import java.util.Map;

import javafx.stage.Stage;

/**
 * 需要操作的舞台与控制器的引用保存到map中,使用完毕后将这个map里面的引用删除,不排除java不释放内存导致泄漏
 *
 * @author 王俊钦、谢文昊
 *
 */
public class StageManager {
	public static Map<String, Stage> STAGE = new HashMap<String, Stage>();

	public static Map<String, Object> CONTROLLER = new HashMap<String, Object>();
}
