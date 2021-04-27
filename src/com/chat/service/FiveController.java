package com.chat.service;

import com.chat.dao.Groupdao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FiveController {

	@FXML
	private Button grobut;
	@FXML
	private TextField groname;
	public static String proname1 = null;

	public void closeThis(ActionEvent event) {
		proname1 = groname.getText();
		Groupdao.addgroup1(proname1);
		MainController.isadd = true;
		System.out.println(proname1);
		StageManager.STAGE.get("five").close();
		StageManager.STAGE.remove("five");
		try {
			openThir();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 添加成功，显示Thirpanel.fxml
	public void openThir() throws Exception {
		Stage stage = new Stage();
		Parent root1 = FXMLLoader.load(Thread.currentThread().getContextClassLoader().getResource("Thirpanel.fxml"));
		stage.setTitle("成功！");
		stage.setScene(new Scene(root1));
		stage.show();
		// 将第二个窗口保存到map中
		StageManager.STAGE.put("thrid", stage);
		// 将本窗口保存到map中
		StageManager.CONTROLLER.put("secondControl", this);
	}
}
