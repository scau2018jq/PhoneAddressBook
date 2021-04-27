package com.chat.service;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * 添加失败窗口
 * 
 * @author 王俊钦、谢文昊
 *
 */
public class FourController implements Initializable {
	@FXML
	private Button no;
	@FXML
	public Label labeladdno;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	// 关闭窗口
	public void closeThis(ActionEvent event) {
		StageManager.STAGE.get("four").close();
		StageManager.STAGE.remove("four");
	}

	// 传输数据
	public void voidData() {
		AddController secondControl = (AddController) StageManager.CONTROLLER.get("secondControl");
		secondControl.setTranDataToIndex("第三个窗口的数据");
		// 如果本窗口还使用该控制器先不remove这个控制器;
		StageManager.CONTROLLER.remove("secondControl");

	}
}
