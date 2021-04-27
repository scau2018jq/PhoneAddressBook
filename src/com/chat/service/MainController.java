package com.chat.service;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.chat.bean.LinkMan;
import com.chat.bean.group;
import com.chat.dao.Groupdao;
import com.chat.dao.Maindao;
import com.chat.dao.filedao;
import com.chat.list.autotextField.AutoCompleteTextField;
import com.chat.list.autotextField.AutoCompleteTextFieldBuilder;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * 主窗口Controller
 *
 * @author 王俊钦、谢文昊
 *
 */

public class MainController implements Initializable {

	@FXML
	private TableView<LinkMan> tableve;// 数据表格
	@FXML
	private TableColumn<LinkMan, Boolean> cselected;// 是否选中
	@FXML
	private TableColumn<LinkMan, String> cname;// 姓名列
	@FXML
	private TableColumn<LinkMan, String> csex;// 性别列
	@FXML
	private TableColumn<LinkMan, String> ctelephone;// 手机列
	@FXML
	private TableColumn<LinkMan, String> cqq;// qq列
	@FXML
	private TableColumn<LinkMan, String> cemail;// 邮件列
	@FXML
	private TableColumn<LinkMan, String> cbirth;// 生日列
	@FXML
	private TableColumn<LinkMan, String> cgroup;// 分组列

	@FXML
	private Button alllinkman;// 所有联系人
	@FXML
	private Button ngrolinkman;// 未分组联系人
	@FXML
	private Button deleteman;// 删除联系人
	@FXML
	private Button Bfind;// 搜索
	@FXML
	private Button addlinkmanb;// 添加联系人
	@FXML
	private Button addgroup;// 添加组按钮
	@FXML
	private Button f5;// 刷新
	@FXML
	private Button bmoveto;// 移动到
	@FXML
	private Button delegroup;// 删除组
	@FXML
	private ComboBox<String> moveto;// 移动到选择框

	@FXML
	private TextField finder;// 搜索框

	@FXML
	private Pane mmain;// 分组Pane

	public static boolean isadd = false;// 是否有添加组
	public boolean isdelete = false;// 是否有删除组

	String nowchoicebut = null;// 目前选择的按钮名称

	ObservableList<LinkMan> data = Maindao.oselectLinkmanall();// 显示的数据

	List<LinkMan> allLinkMan = Maindao.selectLinkmanall();// 所有联系人

	List<String> finderList = new ArrayList<String>();// 下拉框中数据

	List<LinkMan> ngrolinkman1 = Maindao.selectLinkmanallnpro();// 未分组联系人

	List<group> group = Groupdao.selectgroup();// 分组

	List<String> groupchoice = new ArrayList<String>();// 分组名称

	int allLinkMansize = allLinkMan.size();// 所有联系人总长度
	int ngrolinkmansize = ngrolinkman1.size();// 未分组联系人总长度
	Button[] button = new Button[10];// 分组按钮

	int[] grodata = new int[10];// 分组总长度
	int butnum = 0;// 按钮数
	int butnum1 = 0;// 按钮数

	String alllinkman11 = "所有联系人";
	String ngrolinkman11 = "未分组联系人";

	@FXML
	private MenuItem openfile;// 打开文件
	@FXML
	private MenuItem savefile;// 保存文件

	@FXML
	private Label linmannum;

	public MainController() {

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

	// 数据更新
	public void datainit() {
		data.clear();
		data = Maindao.oselectLinkmanall();// 显示的数据
		System.out.println("datasize" + data.size());
		allLinkMan.clear();
		allLinkMan = Maindao.selectLinkmanall();
		System.out.println("allLinkMansize" + allLinkMan.size());
		ngrolinkman1.clear();
		ngrolinkman1 = Maindao.selectLinkmanallnpro();
		allLinkMansize = allLinkMan.size();// 所有联系人总长度
		ngrolinkmansize = ngrolinkman1.size();// 未分组联系人总长度
		group.clear();
		group = Groupdao.selectgroup();
		groupchoice = new ArrayList<String>();
	}

	/*
	 * 删除联系人 判断是否分组
	 */
	private boolean deleteStudents(List<LinkMan> data) {
		int size = data.size();
		if (size <= 0) {
			return false;
		}
		for (int i = size - 1; i >= 0; i--) {
			LinkMan s = data.get(i);
			if (s.isSelected()) {
				if (s.getGroup() == null) {
					ngrolinkmansize--;
				}
				data.remove(s);
				allLinkMansize--;
				try {
					Maindao.deleteLinkmanone(s);
					allLinkMan = Maindao.selectLinkmanall();
					for (LinkMan a : allLinkMan) {
						finderList.add(a.getName());
						finderList.add(a.getTelephone());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	// 打开添加分组的窗口
	public void openFour() throws Exception {
		Stage stage = new Stage();
		Parent root1 = FXMLLoader.load(Thread.currentThread().getContextClassLoader().getResource("Addgro.fxml"));
		stage.setTitle("添加组");
		stage.setScene(new Scene(root1));
		stage.show();
		// 将第二个窗口保存到map中
		StageManager.STAGE.put("five", stage);
		// 将本窗口保存到map中
		StageManager.CONTROLLER.put("fiveControl", this);
	}

	// 删除分组
	public boolean deletegro() throws Exception {
		System.out.println(nowchoicebut);
		if (Groupdao.deletegroup1(nowchoicebut)) {
			isdelete = true;
			return true;
		}
		return false;
	}

	/**
	 * 删除单个文件
	 *
	 * @param fileName
	 *            要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

	/*
	 * 添加联系人 打开添加窗口 并将窗口添加到StageManager中
	 */
	public void openadd(ActionEvent event) throws Exception {
		/*
		 * FXMLLoader f2 = new FXMLLoader();
		 * f2.setLocation(getClass().getResource("Addpanel.fxml")); Stage stage
		 * = new Stage(); AnchorPane root = (AnchorPane)f2.load(); Scene scene =
		 * new Scene(root); stage.setScene(scene); stage.show(); //
		 * 将第二个窗口保存到map中 StageManager.STAGE.put("second", stage); // 将本窗口保存到map中
		 * StageManager.CONTROLLER.put("MainController", this);
		 */

		Stage stage = new Stage();
		Parent root = FXMLLoader.load(Thread.currentThread().getContextClassLoader().getResource("Addpanel.fxml"));
		stage.setTitle("添加联系人");
		stage.setScene(new Scene(root));
		stage.show();
		// 将第二个窗口保存到map中
		StageManager.STAGE.put("second", stage);
		// 将本窗口保存到map中
		StageManager.CONTROLLER.put("indexControl", this);
	}

	// 添加完分组后刷新
	public void f5xin() {

		group = Groupdao.selectgroup();
		for (int i = butnum1; i < group.size(); i++) {
			groupchoice.add(group.get(i).getGroup());
		}
		for (int i = butnum1; i < group.size(); i++) {
			butnum = i;
			String buttonname = groupchoice.get(i);
			List<LinkMan> grodata1 = Maindao.selectLinkmanbygro(buttonname);
			button[i].setText(groupchoice.get(i));
			button[i].setPrefSize(288.0, 30.0);
			button[i].setStyle("-fx-padding: 0");
			button[i].setStyle("-fx-background-insets: 0");
			button[i].setLayoutX(0);
			button[i].setLayoutY(i * 50 + 200);
			button[i].setVisible(true);

			button[i].setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					data.clear();
					data = Maindao.oselectLinkmanbygro(buttonname);
					System.out.println("--------------------------------------");
					allLinkMan.forEach(System.out::println);
					System.out.println("--------------------------------------");
					ObservableList<LinkMan> oselectLinkmanall = FXCollections.observableArrayList(data);
					tableve.setItems(data);
					nowchoicebut = buttonname;
					linmannum.setText("" + data.size());
				}
			});
			mmain.getChildren().add(button[i]);
		}
		butnum1 = group.size();
	}

	// 删除完分组后刷新
	public void f5xin1() {

		group = Groupdao.selectgroup();
		groupchoice.clear();
		for (int i = 0; i < group.size(); i++) {
			groupchoice.add(group.get(i).getGroup());
		}
		for (int i = 0; i < group.size(); i++) {
			String buttonname = groupchoice.get(i);
			List<LinkMan> grodata1 = Maindao.selectLinkmanbygro(buttonname);
			grodata[i] = grodata1.size();
		}

		for (int i = 0; i < 10; i++) {
			button[i].setText("");
			button[i].setVisible(false);
			button[i] = null;
		}
		for (int i = 0; i < 10; i++) {
			button[i] = new Button("" + i);
		}

		for (int i = 0; i < group.size(); i++) {
			butnum = i;
			String buttonname = groupchoice.get(i);
			List<LinkMan> grodata1 = Maindao.selectLinkmanbygro(buttonname);
			button[i].setText(groupchoice.get(i));
			button[i].setPrefSize(288.0, 30.0);
			button[i].setStyle("-fx-padding: 0");
			button[i].setStyle("-fx-background-insets: 0");
			button[i].setLayoutX(0);
			button[i].setLayoutY(i * 50 + 200);
			button[i].setVisible(true);

			button[i].setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					data.clear();
					data = Maindao.oselectLinkmanbygro(buttonname);
					System.out.println("--------------------------------------");
					allLinkMan.forEach(System.out::println);
					System.out.println("--------------------------------------");
					ObservableList<LinkMan> oselectLinkmanall = FXCollections.observableArrayList(data);
					tableve.setItems(data);
					nowchoicebut = buttonname;
					linmannum.setText("" + data.size());
				}
			});
			mmain.getChildren().add(button[i]);
		}
		butnum1 = group.size();
	}

	// 移动联系人到分组
	public boolean removeto(List<LinkMan> data) {
		String movetogro = moveto.getValue();
		System.out.println(movetogro);
		int size = data.size();
		if (size <= 0) {
			return false;
		}
		for (int i = size - 1; i >= 0; i--) {
			LinkMan s = data.get(i);
			if (s.isSelected()) {
				if (s.getGroup() != null && movetogro == null) {
					ngrolinkmansize++;
				} else if (s.getGroup() == null && movetogro != null) {
					ngrolinkmansize--;
				}
				if (movetogro != null) {
					s.setGroup(movetogro);
					try {
						Maindao.updataLinkmanonegroup(s);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		for (int i = 0; i < group.size(); i++) {
			String buttonname = groupchoice.get(i);
			List<LinkMan> grodata1 = Maindao.selectLinkmanbygro(buttonname);
			grodata[i] = grodata1.size();
		}
		for (int i = 0; i < group.size(); i++) {
			button[i].setText(groupchoice.get(i));
		}

		return true;
	}

	// 打开文件选择器
	public String findopen() {

		Stage fileStage = new Stage();
		FileChooser folderChooser = new FileChooser();
		folderChooser.setTitle("Choose Folder");

		folderChooser.getExtensionFilters().addAll(new ExtensionFilter("csv", "*.csv"),
				new ExtensionFilter("所有类型", "*.*")

		);

		File file = folderChooser.showOpenDialog(fileStage);
		if (file == null) {
			return null;
		}

		String absolutePath = file.getAbsolutePath();
		return absolutePath;
	}

	// tableview表格数据初始化，编辑初始化
	public void tableviewinit() {
		/*
		 * 数据表格可编辑设置
		 */
		// ----------------------------------------------------------------------------
		tableve.setEditable(true);
		cselected.setCellFactory(CellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {

			@Override
			public ObservableValue<Boolean> call(Integer index) {
				final LinkMan g = (LinkMan) tableve.getItems().get(index);
				ObservableValue<Boolean> ret = new SimpleBooleanProperty(g, "selected", g.isSelected());
				ret.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							Boolean newValue) {
						g.setSelected(newValue);
					}
				});
				return ret;
			}
		}));

		cname.setCellValueFactory(new PropertyValueFactory<LinkMan, String>("name"));
		cname.setCellFactory(TextFieldTableCell.<LinkMan>forTableColumn());
		cname.setOnEditCommit((CellEditEvent<LinkMan, String> t) -> {
			((LinkMan) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
			data.forEach(System.out::println);
			System.out.println(t.getTablePosition().getRow());
			System.out.println(data.get(t.getTablePosition().getRow()));
			try {
				Maindao.updataLinkmanonename(data.get(t.getTablePosition().getRow()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		csex.setCellValueFactory(new PropertyValueFactory<LinkMan, String>("sex"));
		csex.setCellFactory(TextFieldTableCell.<LinkMan>forTableColumn());
		csex.setOnEditCommit((CellEditEvent<LinkMan, String> t) -> {
			((LinkMan) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSex(t.getNewValue());
			data.forEach(System.out::println);
			System.out.println(t.getTablePosition().getRow());
			System.out.println(data.get(t.getTablePosition().getRow()));
			try {
				Maindao.updataLinkmanonesex(data.get(t.getTablePosition().getRow()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		ctelephone.setCellValueFactory(new PropertyValueFactory<LinkMan, String>("telephone"));
		ctelephone.setCellFactory(TextFieldTableCell.<LinkMan>forTableColumn());
		ctelephone.setOnEditCommit((CellEditEvent<LinkMan, String> t) -> {
			((LinkMan) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTelephone(t.getNewValue());
			data.forEach(System.out::println);
			System.out.println(t.getTablePosition().getRow());
			System.out.println(data.get(t.getTablePosition().getRow()));
			try {
				Maindao.updataLinkmanonephone(data.get(t.getTablePosition().getRow()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		cqq.setCellValueFactory(new PropertyValueFactory<LinkMan, String>("QQ"));
		cqq.setCellFactory(TextFieldTableCell.<LinkMan>forTableColumn());
		cqq.setOnEditCommit((CellEditEvent<LinkMan, String> t) -> {
			((LinkMan) t.getTableView().getItems().get(t.getTablePosition().getRow())).setQQ(t.getNewValue());
			data.forEach(System.out::println);
			System.out.println(t.getTablePosition().getRow());
			System.out.println(data.get(t.getTablePosition().getRow()));
			try {
				Maindao.updataLinkmanoneqq(data.get(t.getTablePosition().getRow()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		cemail.setCellValueFactory(new PropertyValueFactory<LinkMan, String>("email"));
		cemail.setCellFactory(TextFieldTableCell.<LinkMan>forTableColumn());
		cemail.setOnEditCommit((CellEditEvent<LinkMan, String> t) -> {
			((LinkMan) t.getTableView().getItems().get(t.getTablePosition().getRow())).setEmail(t.getNewValue());
			data.forEach(System.out::println);
			System.out.println(t.getTablePosition().getRow());
			System.out.println(data.get(t.getTablePosition().getRow()));
			try {
				Maindao.updataLinkmanoneemail(data.get(t.getTablePosition().getRow()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		cbirth.setCellValueFactory(new PropertyValueFactory<LinkMan, String>("birthday"));
		cbirth.setCellFactory(TextFieldTableCell.<LinkMan>forTableColumn());
		cbirth.setOnEditCommit((CellEditEvent<LinkMan, String> t) -> {
			((LinkMan) t.getTableView().getItems().get(t.getTablePosition().getRow())).setBirthday(t.getNewValue());
			data.forEach(System.out::println);
			System.out.println(t.getTablePosition().getRow());
			System.out.println(data.get(t.getTablePosition().getRow()));
			try {
				Maindao.updataLinkmanonebirth(data.get(t.getTablePosition().getRow()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		cgroup.setCellValueFactory(new PropertyValueFactory<LinkMan, String>("group"));
		cgroup.setCellFactory(TextFieldTableCell.<LinkMan>forTableColumn());
		cgroup.setOnEditCommit((CellEditEvent<LinkMan, String> t) -> {
			((LinkMan) t.getTableView().getItems().get(t.getTablePosition().getRow())).setGroup(t.getNewValue());
			data.forEach(System.out::println);
			System.out.println(t.getTablePosition().getRow());
			System.out.println(data.get(t.getTablePosition().getRow()));
			try {
				Maindao.updataLinkmanonegroup(data.get(t.getTablePosition().getRow()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		tableve.setItems(data);
		// ----------------------------------------------------------------------

	}

	// 搜索框下拉数据，初始化
	public void finderinit() {
		for (LinkMan a : allLinkMan) {
			finderList.add(a.getName());
		}
		for (LinkMan a : allLinkMan) {
			finderList.add(a.getTelephone());
		}
		// -------------------------------------------------------------------------------

		/*
		 * 搜索下拉框的设置
		 */
		AutoCompleteTextField auto = AutoCompleteTextFieldBuilder.build(finder);
		auto.setCacheDataList(finderList);
		finder.setOnAction(new EventHandler<ActionEvent>() // 设置setOnAction的作用：将当点击enter键时，若文本框中的内容是匹配集合中不存在的，就会将其加入到匹配集合中
		{

			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("");
			}
		});
	}

	// 分组按钮初始化
	public void grobuttoninit() {
		for (int i = 0; i < 10; i++) {
			button[i] = new Button("" + i);
		}

		for (int i = 0; i < group.size(); i++) {
			String buttonname = groupchoice.get(i);
			List<LinkMan> grodata1 = Maindao.selectLinkmanbygro(buttonname);
			grodata[i] = grodata1.size();
		}

		for (int i = 0; i < group.size(); i++) {
			butnum = i;
			String buttonname = groupchoice.get(i);
			List<LinkMan> grodata1 = Maindao.selectLinkmanbygro(buttonname);
			button[i].setText(groupchoice.get(i));
			button[i].setPrefSize(288.0, 30.0);
			button[i].setStyle("-fx-padding: 0");
			button[i].setStyle("-fx-background-insets: 0");
			button[i].setLayoutX(0);
			button[i].setLayoutY(i * 50 + 200);
			button[i].setVisible(true);

			button[i].setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					data.clear();
					data = Maindao.oselectLinkmanbygro(buttonname);
					System.out.println("--------------------------------------");
					allLinkMan.forEach(System.out::println);
					System.out.println("--------------------------------------");
					ObservableList<LinkMan> oselectLinkmanall = FXCollections.observableArrayList(data);
					tableve.setItems(data);
					nowchoicebut = buttonname;
					linmannum.setText("" + data.size());
				}
			});
			mmain.getChildren().add(button[i]);
		}
		butnum1 = group.size();
	}

	// 移动到按钮初始化
	public void xialainit() {
		ObservableList<String> obListgroupchoice = FXCollections.observableArrayList(groupchoice);
		moveto.getItems().clear();
		moveto.getItems().add(null);
		moveto.getItems().addAll(obListgroupchoice); // 设置下拉框的数据来源
		moveto.getSelectionModel().select(0); // 设置下拉框默认选中第1项
		Font font = Font.font("NSimSun", 16); // 创建一个字体对象
		moveto.setStyle(String.format("-fx-font: %f \"%s\";", font.getSize(), font.getFamily())); // 设置下拉框的字体

		moveto.setEditable(false); // 设置下拉框能否编辑。默认不允许编辑

	}

	// 按钮初始化事件
	public void buttonaction() {
		// 删除联系人按钮事件
		deleteman.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				deleteStudents(data);
				try {
					openThir();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// 所有联系人按钮事件
		alllinkman.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				data.clear();
				data = Maindao.oselectLinkmanall();
				System.out.println(data.size());
				/*
				 * LinkMan hahaha = new LinkMan("1", "男", "123", "123", "123",
				 * null, null); Maindao.insertLinkmanone(hahaha); try {
				 * Maindao.deleteLinkmanone(hahaha); } catch (IOException e) {
				 * // TODO Auto-generated catch block e.printStackTrace(); }
				 */
				ObservableList<LinkMan> oselectLinkmanall = FXCollections.observableArrayList(data);
				tableve.setItems(data);
				linmannum.setText("" + data.size());
			}
		});

		// 未分组联系人显示事件
		ngrolinkman.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				data.clear();
				data = Maindao.selectLinkmanallnpro();
				ObservableList<LinkMan> oselectLinkmanall = FXCollections.observableArrayList(data);
				tableve.setItems(data);
				linmannum.setText("" + data.size());
			}
		});

		// 添加分组
		addgroup.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					openFour();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// 刷新按钮
		f5.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (isadd = true) {
					f5xin();
				}
				isadd = false;
				finderinit();
				ObservableList<String> obListgroupchoice = FXCollections.observableArrayList(groupchoice);
				moveto.getItems().clear();
				moveto.getItems().add(null);
				moveto.getItems().addAll(obListgroupchoice); // 设置下拉框的数据来源
			}
		});

		// 搜索按钮事件
		Bfind.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String name = finder.getText();
				data.clear();
				data = Maindao.selectLinkmanone(name);
				tableve.setItems(data);
			}
		});

		// 移动到按钮初始化
		bmoveto.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				removeto(data);
				try {
					openThir();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// 删除组按钮初始化
		delegroup.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					if (deletegro()) {
						for (int i = 0; i < data.size(); i++) {
							data.get(i).setGroup(null);
							Maindao.updataLinkmanonegroup(data.get(i));
						}
						f5xin1();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ObservableList<String> obListgroupchoice = FXCollections.observableArrayList(groupchoice);
				moveto.getItems().clear();
				moveto.getItems().add(null);
				moveto.getItems().addAll(obListgroupchoice); // 设置下拉框的数据来源
				try {
					openThir();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// 保存文件按钮初始化
		savefile.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String findopen = findopen();
				deleteFile(findopen);
				if (findopen != null) {
					filedao.savefile(findopen);
				}
				try {
					openThir();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// 打开文件按钮事件
		openfile.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String findopen = findopen();

				if (findopen != null) {
					filedao.openfile(findopen);
					LinkMan hahaha = new LinkMan("1", "男", "123", "123", "123", null, null);
					Maindao.insertLinkmanone(hahaha);
					try {
						Maindao.deleteLinkmanone(hahaha);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					datainit();
					tableve.setItems(data);
					linmannum.setText("" + data.size());
				}
				try {
					openThir();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * 界面初始化 (non-Javadoc)
	 *
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		////////////////////////////////////////////////
		for (int i = 0; i < group.size(); i++) {
			groupchoice.add(group.get(i).getGroup());
		}

		xialainit();
		///////////////////////////////////////////////

		grobuttoninit();
		///////////////////////////////////////////////

		/*
		 * 按钮样式css
		 */
		// ------------------------------------------------------------------------
		alllinkman.setStyle("-fx-padding: 0");
		alllinkman.setStyle("-fx-background-insets: 0");// 去除边框样式
		alllinkman.setText(alllinkman11);
		ngrolinkman.setStyle("-fx-padding: 0");
		ngrolinkman.setStyle("-fx-background-insets: 0");// 去除边框样式
		ngrolinkman.setText(ngrolinkman11);
		// ------------------------------------------------------------------------

		// ----------------------------------------------------------------------
		// tableview初始化
		tableviewinit();

		// 搜索下拉框初始化
		finderinit();

		// ----------------------------------------------------------------------

		buttonaction();

		// ----------------------------------------------------------------------
		linmannum.setText("" + data.size());
	}

}
