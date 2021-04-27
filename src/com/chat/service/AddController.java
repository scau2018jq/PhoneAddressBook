package com.chat.service;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.chat.bean.LinkMan;
import com.chat.bean.group;
import com.chat.dao.Groupdao;
import com.chat.dao.Maindao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 添加窗口
 *
 * @author 王俊钦、谢文昊
 *
 */
public class AddController implements Initializable {

	private String tranDataToIndex;// 窗口之间传输数据

	public String getTranDataToIndex() {
		return tranDataToIndex;
	}

	public void setTranDataToIndex(String tranDataToIndex) {
		this.tranDataToIndex = tranDataToIndex;
	}

	public AddController() {

	}

	VBox vbox = new VBox();

	List<String> sexchoice = Arrays.asList("男", "女", "其他", " ");// 性别下拉框数据来源
	List<String> yearchoice = new ArrayList<String>();
	List<String> monthchoice = new ArrayList<String>();
	List<group> group = Groupdao.selectgroup();
	List<String> groupchoice = new ArrayList<String>();
	ObservableList<String> obListsexchoice = FXCollections.observableArrayList(sexchoice);

	public boolean addyes1 = false;

	@FXML
	private Button addyes;
	@FXML
	private Button addno;
	@FXML
	private Button yes;
	@FXML
	private TextField addname;
	@FXML
	private TextField addtele;
	@FXML
	private TextField addqq;
	@FXML
	private TextField addemail;
	@FXML
	private ComboBox<String> addsex;// 性别选择框
	@FXML
	private ComboBox<String> addgroup;// 分组选择框
	@FXML
	private DatePicker addday;// 日期表

	private String saddname;
	private String saddtele;
	private String saddqq;
	private String saddemail;
	private String saddsex;
	private String sadddata;
	private String saddgroup;

	/*
	 * 添加事件 姓名与手机不能为空，显示窗口：失败 不为空，成功，显示窗口：成功
	 */
	public void openThrid(ActionEvent event) throws Exception {
		saddname = addname.getText();
		saddtele = addtele.getText();
		saddqq = addqq.getText();
		saddemail = addemail.getText();
		saddsex = addsex.getValue();
		LocalDate data = addday.getValue();
		System.out.println(data);
		if (data == null) {
			sadddata = null;
		} else {
			sadddata = data.toString();
		}
		saddgroup = addgroup.getValue();
		if (addname.getText().trim().isEmpty()) {
			saddname = null;
		}
		System.out.println(saddname);

		System.out.println(saddsex);
		if (addtele.getText().trim().isEmpty()) {
			saddtele = null;
		}
		System.out.println(saddtele);
		if (addqq.getText().trim().isEmpty()) {
			saddqq = null;
		}
		System.out.println(saddqq);
		if (addemail.getText().trim().isEmpty()) {
			saddemail = null;
		}
		System.out.println(saddemail);

		System.out.println(sadddata);
		LinkMan addlinkman = new LinkMan(saddname, saddsex, saddtele, saddqq, saddemail, sadddata, saddgroup);
		System.out.println(saddgroup);
		if (saddname == null || saddtele == null) {
			System.out.println("添加错误");
			addyes1 = false;
			openFour();
		} else if (Maindao.insertLinkmanone(addlinkman)) {
			addyes1 = true;
			openThir();

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

	// 添加失败，显示no.fxml
	public void openFour() throws Exception {
		Stage stage = new Stage();
		Parent root1 = FXMLLoader.load(Thread.currentThread().getContextClassLoader().getResource("no.fxml"));
		stage.setTitle("失败！");
		stage.setScene(new Scene(root1));
		stage.show();
		// 将第二个窗口保存到map中
		StageManager.STAGE.put("four", stage);
		// 将本窗口保存到map中
		StageManager.CONTROLLER.put("secondControl", this);
	}

	// 关闭添加窗口，传输数据
	public void closeThis(ActionEvent event) {
		if (this.tranDataToIndex != null) {
			MainController index = (MainController) StageManager.CONTROLLER.get("indexControl");
			this.tranDataToIndex = "第三个窗口";
		}
		StageManager.STAGE.get("second").close();
		// 删除map中的引用
		StageManager.STAGE.remove("second");
		StageManager.CONTROLLER.remove("indexControl");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// TODO Auto-generated method stub
		/*
		 * 下拉框设置数据来源 css 样式
		 */
		// ---------------------------------------------

		groupchoice.add("");
		for (int i = 0; i < group.size(); i++) {
			groupchoice.add(group.get(i).getGroup());
		}

		ObservableList<String> obListgroupchoice = FXCollections.observableArrayList(groupchoice);

		addsex.getItems().addAll(obListsexchoice); // 设置下拉框的数据来源
		addsex.getSelectionModel().select(0); // 设置下拉框默认选中第1项
		Font font = Font.font("NSimSun", 16); // 创建一个字体对象
		addsex.setStyle(String.format("-fx-font: %f \"%s\";", font.getSize(), font.getFamily())); // 设置下拉框的字体

		addsex.setEditable(false); // 设置下拉框能否编辑。默认不允许编辑

		addday.setShowWeekNumbers(true);

		System.out.println(group);
		addgroup.getItems().addAll(obListgroupchoice); // 设置下拉框的数据来源
		addgroup.getSelectionModel().select(0); // 设置下拉框默认选中第1项
		addgroup.setStyle(String.format("-fx-font: %f \"%s\";", font.getSize(), font.getFamily())); // 设置下拉框的字体
		addgroup.setEditable(false); // 设置下拉框能否编辑。默认不允许编辑
		// ------------------------------------------------------------

		// 关闭添加窗口
		addno.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Stage stage = (Stage) addno.getScene().getWindow();
				stage.close();
			}
		});
	}

}
