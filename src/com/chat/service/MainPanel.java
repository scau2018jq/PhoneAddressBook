package com.chat.service;

import java.io.IOException;
import java.util.List;

import com.chat.bean.LinkMan;
import com.chat.dao.Maindao;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 显示主窗口、启动
 * 
 * @author 王俊钦、谢文昊
 *
 */
public class MainPanel extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {

		// FXMLLoader f1 = new FXMLLoader();
		// FXMLLoader f2 = new FXMLLoader();
		// f1.setLocation(getClass().getResource("Mainpanel.fxml"));
		// f2.setLocation(getClass().getResource("Addpanel.fxml"));
		// URL url = f1.getClassLoader().getResource("MainPanel.fxml");
		// f1.setLocation(url);
		// AnchorPane root = (AnchorPane)f1.load();

		Parent root = FXMLLoader.load(Thread.currentThread().getContextClassLoader().getResource("Mainpanel.fxml"));// 映射Resouce中的fxml文件

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setHeight(750);
		primaryStage.setTitle("通讯录");
		primaryStage.setWidth(1300);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
