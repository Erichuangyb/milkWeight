package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
/**
 * @author Yibin Huang
 * @email huang448@wisc.edu
 * @Description This file contains the Main class to run the GUI
 */
import java.util.List;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This class contains objects to run the GUI
 **/
public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 500;
	private static final String APP_TITLE = "Farm Milk Data!";
	private static final String Months[] = { "Jan", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov",
			"Dec" };
	private static DataStorage dataStorage = new DataStorage();

	@Override
	/**
	 * this method contains the main objects of building GUI and show the data
	 */
	public void start(Stage primaryStage) throws Exception {
		// save args example
		args = this.getParameters().getRaw();
		// Main layout is Border Pane example (top,left,center,right,bottom)
		Pane root = new Pane();
		// Add the image
		Image image = new Image("file:///Users/huangyibin/Desktop/myworkspace/a2_milestone/farm.png");
		ImageView imageView = new ImageView(image);
		// Set the image position
		imageView.setX(150);
		imageView.setY(150);
		// Set the image size
		imageView.setFitHeight(200);
		imageView.setFitWidth(200);
		imageView.setPreserveRatio(true);
		root.getChildren().add(imageView);
		// Add Read File button
		Button readFileR = new Button("Read File");
		readFileR.setPrefWidth(140);
		readFileR.setLayoutX(180);
		readFileR.setLayoutY(100);
		root.getChildren().add(readFileR);
		// set the button on action
		readFileR.setOnAction(new SingleFcButtonListener());

		// Add Farm Report button
		Button farmR = new Button("Farm Report");
		farmR.setPrefWidth(140);
		farmR.setLayoutX(80);
		farmR.setLayoutY(300);
		root.getChildren().add(farmR);

		// Add Monthly Report button
		Button MonthR = new Button("Monthly Report");
		MonthR.setPrefWidth(140);
		MonthR.setLayoutX(280);
		MonthR.setLayoutY(300);
		root.getChildren().add(MonthR);

		// Add Annual Report button
		Button AnnualR = new Button("Annual Report");
		AnnualR.setPrefWidth(140);
		AnnualR.setLayoutX(80);
		AnnualR.setLayoutY(350);
		root.getChildren().add(AnnualR);

		// Add Date Range Report button
		Button dateRangeR = new Button("Date Range Report");
		dateRangeR.setPrefWidth(140);
		dateRangeR.setLayoutX(280);
		dateRangeR.setLayoutY(350);
		root.getChildren().add(dateRangeR);

		// action events
		EventHandler<ActionEvent> farmReportEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					farmReport(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		EventHandler<ActionEvent> monthlyReportEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					monthlyReport(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		EventHandler<ActionEvent> annualReportEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					annualReport(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		EventHandler<ActionEvent> selectDateReportEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					DateRangeReport(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		EventHandler<ActionEvent> exitEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					primaryStage.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};

		farmR.setOnAction(farmReportEvent);
		MonthR.setOnAction(monthlyReportEvent);
		AnnualR.setOnAction(annualReportEvent);
		dateRangeR.setOnAction(selectDateReportEvent);

		// Add an exit button
		Button exitButton = new Button("Exit");
		exitButton.setPrefWidth(50);
		exitButton.setLayoutX(450);
		exitButton.setLayoutY(472);
		root.getChildren().add(exitButton);
		exitButton.setOnAction(e -> Platform.exit());
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	/*
	 * This method is create the GUI that user chose farm Report
	 * 
	 * @param stage the stage of the program
	 * 
	 * @throws Exception when exception occurs
	 */
	public void farmReport(Stage stage) throws Exception {
		Pane farmReport = new Pane();
		// Add the farm label
		Label farmLabel = new Label("Farm ID: ");
		farmLabel.setLayoutX(80);
		farmLabel.setLayoutY(155);
		farmReport.getChildren().add(farmLabel);
		// Create a new textField that allows input farm id
		TextField inputFarmID = new TextField();
		inputFarmID.setPrefSize(200, 25);
		inputFarmID.setEditable(true);
		inputFarmID.setAlignment(Pos.CENTER_LEFT);
		inputFarmID.setPrefColumnCount(20);
		inputFarmID.setPromptText("Please input Farm ID here");
		farmReport.getChildren().add(inputFarmID);
		inputFarmID.setLayoutX(140);
		inputFarmID.setLayoutY(150);
		// Create a new textField that allows input year
		TextField inputYear = new TextField();
		inputYear.setPrefSize(200, 25);
		inputYear.setEditable(true);
		inputYear.setAlignment(Pos.CENTER_LEFT);
		inputYear.setPrefColumnCount(20);
		inputYear.setPromptText("Please input year here");
		farmReport.getChildren().add(inputYear);
		inputYear.setLayoutX(140);
		inputYear.setLayoutY(200);
		// Add the year label
		Label yearLabel = new Label("Year: ");
		yearLabel.setLayoutX(80);
		yearLabel.setLayoutY(205);
		farmReport.getChildren().add(yearLabel);

		// Add a get data button
		/**
		 * still need to complete the function of reading file
		 */
		Button reportButton = new Button("Get Data");
		reportButton.setPrefWidth(100);
		reportButton.setLayoutX(195);
		reportButton.setLayoutY(260);
		farmReport.getChildren().add(reportButton);

		EventHandler<ActionEvent> continueEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					if (inputFarmID.getText().isEmpty() || inputYear.getText().isEmpty()) {
						Label error = new Label("Must enter all information correctly");
						farmReport.getChildren().add(error);
						error.relocate(135, 300);
					} else {
						FarmTable(stage, inputFarmID.getText(), Integer.parseInt(inputYear.getText()));
					}
				} catch (Exception e1) {
					Label error = new Label("The FarmID / Year does not exist");
					farmReport.getChildren().add(error);
					error.relocate(135, 300);
				}
			}
		};
		reportButton.setOnAction(continueEvent);

		// Build the scene
		Scene farmScene = new Scene(farmReport, WINDOW_WIDTH, WINDOW_HEIGHT);
		Stage farmStage = new Stage();
		farmStage.setTitle("Farm Report");
		farmStage.setScene(farmScene);
		farmStage.show();
		// Add an exit button
		Button exitButton = new Button("Exit");
		exitButton.setPrefWidth(80);
		exitButton.setLayoutX(425);
		exitButton.setLayoutY(473);
		farmReport.getChildren().add(exitButton);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				farmStage.close();
			}
		});
	}

	/**
	 * Show the table of Farm Report
	 * 
	 * @param stage
	 * @param farmID
	 * @param year
	 * @throws Exception
	 */
	public void FarmTable(Stage stage, String farmID, int year) throws Exception {

		// Create a table to show the data
		TableView tableView = new TableView();
		TableColumn<String, Farm> column1 = new TableColumn<>("Month");
		column1.setCellValueFactory(new PropertyValueFactory<>("month"));
		TableColumn<String, Farm> column2 = new TableColumn<>("Production");
		column2.setCellValueFactory(new PropertyValueFactory<>("milkWeight"));
		TableColumn<String, Farm> column3 = new TableColumn<>("Percentage");
		column3.setCellValueFactory(new PropertyValueFactory<>("percentage"));

		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);

		tableView.getItems()
				.add(new Farm("January", dataStorage.get("Farm " + farmID).getMonthWeight(1, year),
						(double) dataStorage.get("Farm " + farmID).getMonthWeight(1, year)
								/ dataStorage.getTotalMonthWeight(1, year) * 100));

		tableView.getItems()
				.add(new Farm("February", dataStorage.get("Farm " + farmID).getMonthWeight(2, year),
						(double) dataStorage.get("Farm " + farmID).getMonthWeight(2, year)
								/ dataStorage.getTotalMonthWeight(2, year) * 100));

		tableView.getItems()
				.add(new Farm("March", dataStorage.get("Farm " + farmID).getMonthWeight(3, year),
						(double) dataStorage.get("Farm " + farmID).getMonthWeight(3, year)
								/ dataStorage.getTotalMonthWeight(3, year) * 100));

		tableView.getItems()
				.add(new Farm("April", dataStorage.get("Farm " + farmID).getMonthWeight(4, year),
						(double) dataStorage.get("Farm " + farmID).getMonthWeight(4, year)
								/ dataStorage.getTotalMonthWeight(4, year) * 100));

		tableView.getItems()
				.add(new Farm("May", dataStorage.get("Farm " + farmID).getMonthWeight(5, year),
						(double) dataStorage.get("Farm " + farmID).getMonthWeight(5, year)
								/ dataStorage.getTotalMonthWeight(5, year) * 100));

		tableView.getItems()
				.add(new Farm("June", dataStorage.get("Farm " + farmID).getMonthWeight(6, year),
						(double) dataStorage.get("Farm " + farmID).getMonthWeight(6, year)
								/ dataStorage.getTotalMonthWeight(6, year) * 100));

		tableView.getItems()
				.add(new Farm("July", dataStorage.get("Farm " + farmID).getMonthWeight(7, year),
						(double) dataStorage.get("Farm " + farmID).getMonthWeight(7, year)
								/ dataStorage.getTotalMonthWeight(7, year) * 100));

		tableView.getItems()
				.add(new Farm("August", dataStorage.get("Farm " + farmID).getMonthWeight(8, year),
						(double) dataStorage.get("Farm " + farmID).getMonthWeight(8, year)
								/ dataStorage.getTotalMonthWeight(8, year) * 100));

		tableView.getItems()
				.add(new Farm("September", dataStorage.get("Farm " + farmID).getMonthWeight(9, year),
						(double) dataStorage.get("Farm " + farmID).getMonthWeight(9, year)
								/ dataStorage.getTotalMonthWeight(9, year) * 100));
		tableView.getItems()
				.add(new Farm("October", dataStorage.get("Farm " + farmID).getMonthWeight(10, year),
						(double) dataStorage.get("Farm " + farmID).getMonthWeight(10, year)
								/ dataStorage.getTotalMonthWeight(10, year) * 100));
		tableView.getItems()
				.add(new Farm("November", dataStorage.get("Farm " + farmID).getMonthWeight(11, year),
						(double) dataStorage.get("Farm " + farmID).getMonthWeight(11, year)
								/ dataStorage.getTotalMonthWeight(11, year) * 100));
		tableView.getItems()
				.add(new Farm("December", dataStorage.get("Farm " + farmID).getMonthWeight(12, year),
						(double) dataStorage.get("Farm " + farmID).getMonthWeight(12, year)
								/ dataStorage.getTotalMonthWeight(12, year) * 100));

		int maxWeight = 0;
		int minWeight = 0;
		int index = 0;
		int month = 1;

		for (Entry<String, Farm> entry : dataStorage.dataStorage.entrySet()) {
			if (month == 13) {
				break;
			}
			if (index == 0) {
				minWeight = ((Entry<String, Farm>) entry).getValue().getMonthWeight(month, year);
			}
			if (((Entry<String, Farm>) entry).getValue().getMonthWeight(month, year) < minWeight) {
				minWeight = ((Entry<String, Farm>) entry).getValue().getMonthWeight(month, year);
			}
			if (((Entry<String, Farm>) entry).getValue().getMonthWeight(month, year) > maxWeight) {
				maxWeight = ((Entry<String, Farm>) entry).getValue().getMonthWeight(month, year);
			}
			++index;
			++month;
		}
		double averageWeight = dataStorage.dataStorage.get("Farm " + farmID).getYearWeight(year) / 12;

		Label note = new Label("click each column's title to sort(ascending / descending) ");
		Label max = new Label("Maximum: " + maxWeight);
		Label min = new Label("Minimum: " + minWeight);
		Label avg = new Label("Average: " + averageWeight);
		VBox vbox = new VBox(note, tableView, max, min, avg);

		Scene scene = new Scene(vbox);
		Stage farmData = new Stage();
		farmData.setHeight(400);
		farmData.setWidth(400);

		farmData.setTitle("Farm " + farmID + " Year:" + year);
		farmData.setScene(scene);
		farmData.show();
	}

	/*
	 * This method is create the GUI that user chose monthly Report
	 * 
	 * @param stage the stage of the program
	 * 
	 * @throws Exception when exception occurs
	 */
	public void monthlyReport(Stage stage) throws Exception {
		Pane monthReport = new Pane();
		// Create a textField to enter year
		TextField enterYear = new TextField();
		enterYear.setLayoutX(140);
		enterYear.setLayoutY(200);
		monthReport.getChildren().add(enterYear);
		// Create a textField to enter month
		TextField enterMonth = new TextField();
		enterMonth.setLayoutX(140);
		enterMonth.setLayoutY(260);
		monthReport.getChildren().add(enterMonth);
		// Add the year label
		Label yearLabel = new Label("Year: ");
		yearLabel.setLayoutX(80);
		yearLabel.setLayoutY(205);
		monthReport.getChildren().add(yearLabel);
		// Add the month label
		Label monthLabel = new Label("Month: ");
		monthLabel.setLayoutX(80);
		monthLabel.setLayoutY(265);
		monthReport.getChildren().add(monthLabel);
		// Add a get data button
		/**
		 * still need to complete the function of reading file
		 */
		Button dataButton = new Button("Get Data");
		dataButton.setPrefWidth(100);
		dataButton.setLayoutX(195);
		dataButton.setLayoutY(330);
		monthReport.getChildren().add(dataButton);
		// continue event
		EventHandler<ActionEvent> monthlyReportTableEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					if (enterYear.getText().isEmpty() || enterMonth.getText().isEmpty()) {
						Label error = new Label("Must enter all information correctly");
						monthReport.getChildren().add(error);
						error.relocate(135, 300);
					} else {
						MonthTable(Integer.parseInt(enterYear.getText()), Integer.parseInt(enterMonth.getText()));

					}
				} catch (Exception e1) {
					Label error = new Label("The Year / Month you entered does not exist");
					monthReport.getChildren().add(error);
					error.relocate(135, 300);
				}
			}
		};
		dataButton.setOnAction(monthlyReportTableEvent);
		// Build the scene
		Scene annualScene = new Scene(monthReport, WINDOW_WIDTH, WINDOW_HEIGHT);
		Stage annualStage = new Stage();
		annualStage.setTitle("Monthly Report");
		annualStage.setScene(annualScene);
		annualStage.show();
		// Add an exit button
		Button exitButton = new Button("Exit");
		exitButton.setPrefWidth(80);
		exitButton.setLayoutX(425);
		exitButton.setLayoutY(473);
		monthReport.getChildren().add(exitButton);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				annualStage.close();
			}
		});
	}

	/**
	 * Show the table of month report
	 * 
	 * @param stage
	 * @param farmID
	 * @param year
	 * @throws Exception
	 */
	public void MonthTable(int year, int month) throws Exception {
		// Create a table to show the data
		TableView tableView = new TableView();
		TableColumn<String, Farm> column1 = new TableColumn<>("ID");
		column1.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<String, Farm> column2 = new TableColumn<>("Production");
		column2.setCellValueFactory(new PropertyValueFactory<>("milkWeight"));
		TableColumn<String, Farm> column3 = new TableColumn<>("Percentage");
		column3.setCellValueFactory(new PropertyValueFactory<>("percentage"));
		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);

		int maxWeight = 0;
		int minWeight = 0;
		int index = 0;

		for (Entry<String, Farm> entry : dataStorage.dataStorage.entrySet()) {
			tableView.getItems()
					.add(new MonthReport(((Entry<String, Farm>) entry).getKey(),
							((Entry<String, Farm>) entry).getValue().getMonthWeight(month, year),
							(double) ((Entry<String, Farm>) entry).getValue().getMonthWeight(month, year)
									/ dataStorage.getTotalMonthWeight(month, year) * 100));
			if (index == 0) {
				minWeight = ((Entry<String, Farm>) entry).getValue().getMonthWeight(month, year);
			}
			if (((Entry<String, Farm>) entry).getValue().getMonthWeight(month, year) < minWeight) {
				minWeight = ((Entry<String, Farm>) entry).getValue().getMonthWeight(month, year);
			}
			if (((Entry<String, Farm>) entry).getValue().getMonthWeight(month, year) > maxWeight) {
				maxWeight = ((Entry<String, Farm>) entry).getValue().getMonthWeight(month, year);
			}
			++index;
		}
		double averageWeight = dataStorage.getTotalMonthWeight(month, year) / dataStorage.getSize();
		Label note = new Label("click each column's title to sort(ascending / descending) ");
		Label max = new Label("Maximum: " + maxWeight);
		Label min = new Label("Minimum: " + minWeight);
		Label avg = new Label("Average: " + averageWeight);
		VBox vbox = new VBox(note, tableView, max, min, avg);
		Scene scene = new Scene(vbox);
		Stage farmData = new Stage();
		farmData.setHeight(400);
		farmData.setWidth(400);
		farmData.setTitle("Month " + month + " Year:" + year);
		farmData.setScene(scene);
		farmData.show();
	}

	/**
	 * Create objects of Annually Report
	 * 
	 * @param stage
	 * @throws Exception
	 */
	public void annualReport(Stage stage) throws Exception {
		Pane annualReport = new Pane();
		// Create a textField to enter year
		TextField enterYear = new TextField();
		enterYear.setLayoutX(140);
		enterYear.setLayoutY(200);
		annualReport.getChildren().add(enterYear);
		// Add the year label
		Label yearLabel = new Label("Year: ");
		yearLabel.setLayoutX(80);
		yearLabel.setLayoutY(205);
		annualReport.getChildren().add(yearLabel);
		// Add a get data button
		Button dataButton = new Button("Get Data");
		dataButton.setPrefWidth(100);
		dataButton.setLayoutX(195);
		dataButton.setLayoutY(260);
		annualReport.getChildren().add(dataButton);
		// continue event
		EventHandler<ActionEvent> annualReportTableEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					if (enterYear.getText().isEmpty()) {
						Label error = new Label("Must enter all information correctly");
						annualReport.getChildren().add(error);
						error.relocate(135, 300);
					} else {
						annualTable(Integer.parseInt(enterYear.getText()));
					}
				} catch (Exception e1) {
					Label error = new Label("The Year you entered does not exist");
					annualReport.getChildren().add(error);
					error.relocate(135, 300);
				}
			}
		};
		dataButton.setOnAction(annualReportTableEvent);
		// Build the scene
		Scene annualScene = new Scene(annualReport, WINDOW_WIDTH, WINDOW_HEIGHT);
		Stage annualStage = new Stage();
		annualStage.setTitle("Annual Report");
		annualStage.setScene(annualScene);
		annualStage.show();
		// Add an exit button
		Button exitButton = new Button("Exit");
		exitButton.setPrefWidth(80);
		exitButton.setLayoutX(425);
		exitButton.setLayoutY(473);
		annualReport.getChildren().add(exitButton);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				annualStage.close();
			}
		});
	}

	/**
	 * Show the table of annual report
	 * 
	 * @param stage
	 * @param farmID
	 * @param year
	 * @throws Exception
	 */
	public void annualTable(int year) throws Exception {
		// Create a table to show the data
		TableView tableView = new TableView();
		TableColumn<String, Farm> column1 = new TableColumn<>("ID");
		column1.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<String, Farm> column2 = new TableColumn<>("Production");
		column2.setCellValueFactory(new PropertyValueFactory<>("milkWeight"));
		TableColumn<String, Farm> column3 = new TableColumn<>("Percentage");
		column3.setCellValueFactory(new PropertyValueFactory<>("percentage"));
		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);
		int maxWeight = 0;
		int minWeight = 0;
		int index = 0;

		for (Entry<String, Farm> entry : dataStorage.dataStorage.entrySet()) {
			tableView.getItems()
					.add(new MonthReport(((Entry<String, Farm>) entry).getKey(),
							((Entry<String, Farm>) entry).getValue().getYearWeight(year),
							(double) ((Entry<String, Farm>) entry).getValue().getYearWeight(year)
									/ dataStorage.getTotalYearWeight(year) * 100));
			if (index == 0) {
				minWeight = ((Entry<String, Farm>) entry).getValue().getYearWeight(year);
			}
			if (((Entry<String, Farm>) entry).getValue().getYearWeight(year) < minWeight) {
				minWeight = ((Entry<String, Farm>) entry).getValue().getYearWeight(year);
			}
			if (((Entry<String, Farm>) entry).getValue().getYearWeight(year) > maxWeight) {
				maxWeight = ((Entry<String, Farm>) entry).getValue().getYearWeight(year);
			}
			++index;
		}
		double averageWeight = dataStorage.getTotalYearWeight(year) / dataStorage.getSize();

		Label note = new Label("click each column's title to sort(ascending / descending) ");
		Label max = new Label("Maximum: " + maxWeight);
		Label min = new Label("Minimum: " + minWeight);
		Label avg = new Label("Average: " + averageWeight);
		VBox vbox = new VBox(note, tableView, max, min, avg);
		Scene scene = new Scene(vbox);
		Stage farmData = new Stage();
		farmData.setHeight(400);
		farmData.setWidth(400);
		farmData.setTitle("Year: " + year);
		farmData.setScene(scene);
		farmData.show();
	}

	/**
	 * create objects of Date Range Report
	 * 
	 * @param stage
	 * @throws Exception
	 */
	public void DateRangeReport(Stage stage) throws Exception {
		Pane dateRangeReport = new Pane();
		// Create a text field to enter start date
		TextField startDate = new TextField();
		startDate.setLayoutX(160);
		startDate.setLayoutY(180);
		dateRangeReport.getChildren().add(startDate);
		// Create a combo box to select start date
		// Create a text field to enter start date
		TextField endDate = new TextField();
		endDate.setLayoutX(160);
		endDate.setLayoutY(280);
		dateRangeReport.getChildren().add(endDate);
		// Add the year label
		Label startDateLabel = new Label("Start date: (year-month-day)");
		startDateLabel.setLayoutX(80);
		startDateLabel.setLayoutY(155);
		dateRangeReport.getChildren().add(startDateLabel);
		// Add the year label
		Label endDateLabel = new Label("End date: (month-day)");
		endDateLabel.setLayoutX(80);
		endDateLabel.setLayoutY(255);
		dateRangeReport.getChildren().add(endDateLabel);
		// Add a get data button
		Button dataButton = new Button("Get Data");
		dataButton.setPrefWidth(100);
		dataButton.setLayoutX(195);
		dataButton.setLayoutY(370);
		dateRangeReport.getChildren().add(dataButton);
		EventHandler<ActionEvent> selectDateReportTableEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					if (startDate.getText().isEmpty() || endDate.getText().isEmpty()
							|| !startDate.getText().contains("-") || !endDate.getText().contains("-")) {
						Label error = new Label("Must enter all information correctly");
						dateRangeReport.getChildren().add(error);
						error.relocate(135, 300);
					} else {
						dateTable(stage, startDate.getText(), endDate.getText());
					}
				} catch (IllegalArgumentException e2) {
					showAlertWrongDate();
					e2.printStackTrace();
				} catch (Exception e1) {
					Label error = new Label("The date you entered does not exist");
					dateRangeReport.getChildren().add(error);
					error.relocate(135, 300);
				}
			}
		};
		dataButton.setOnAction(selectDateReportTableEvent);
		// Build the scene
		Scene annualScene = new Scene(dateRangeReport, WINDOW_WIDTH, WINDOW_HEIGHT);
		Stage annualStage = new Stage();
		annualStage.setTitle("Date Range Report");
		annualStage.setScene(annualScene);
		annualStage.show();
		// Add an exit button
		Button exitButton = new Button("Exit");
		exitButton.setPrefWidth(80);
		exitButton.setLayoutX(425);
		exitButton.setLayoutY(473);
		dateRangeReport.getChildren().add(exitButton);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				annualStage.close();
			}
		});
	}

	/**
	 * Build the GUI of Date Range Report
	 * 
	 * @param stage
	 * @param startDate
	 * @param endDate
	 */
	public void dateTable(Stage stage, String startDate, String endDate) {
		// Create a table to show the data
		TableView tableView = new TableView();
		TableColumn<String, DateRangeReport> column1 = new TableColumn<>("Farm ID");
		column1.setCellValueFactory(new PropertyValueFactory<>("farmID"));
		TableColumn<String, DateRangeReport> column2 = new TableColumn<>("Production");
		column2.setCellValueFactory(new PropertyValueFactory<>("milkWeight"));
		TableColumn<String, DateRangeReport> column3 = new TableColumn<>("Percentage");
		column3.setCellValueFactory(new PropertyValueFactory<>("percentage"));
		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);
		int rangeTotalFarms = 0;
		int maxWeight = 0;
		int minWeight = 0;
		int index = 0;
		
		try {
			for (Entry<String, Farm> entry : dataStorage.dataStorage.entrySet()) {
				rangeTotalFarms += ((Entry<String, Farm>) entry).getValue().getRangeWeight(startDate, endDate);
			}
		} catch (ParseException e) {
			
		}
		
		try {
			for (Entry<String, Farm> entry : dataStorage.dataStorage.entrySet()) {
				tableView.getItems()
						.add(new DateRangeReport(((Entry<String, Farm>) entry).getKey(),
								((Entry<String, Farm>) entry).getValue().getRangeWeight(startDate, endDate),(
								(double) ((Entry<String, Farm>) entry).getValue().getRangeWeight(startDate, endDate)
										/ (rangeTotalFarms)) * 100));
				if (index == 0) {
					minWeight = ((Entry<String, Farm>) entry).getValue().getRangeWeight(startDate, endDate);
				}
				if (((Entry<String, Farm>) entry).getValue().getRangeWeight(startDate, endDate) < minWeight) {
					minWeight = ((Entry<String, Farm>) entry).getValue().getRangeWeight(startDate, endDate);
				}
				if (((Entry<String, Farm>) entry).getValue().getRangeWeight(startDate, endDate) > maxWeight) {
					maxWeight = ((Entry<String, Farm>) entry).getValue().getRangeWeight(startDate, endDate);
				}
				++index;
			}
		} catch (ParseException e) {
			
		}
		
	double averageWeight = rangeTotalFarms / index;
	Label note = new Label("click each column's title to sort(ascending / descending) ");
	Label max = new Label("Maximum: " + maxWeight);
	Label min = new Label("Minimum: " + minWeight);
	Label avg = new Label("Average: " + averageWeight);
	VBox vbox = new VBox(note, tableView, max, min, avg);
		Scene scene = new Scene(vbox);
		Stage farmData = new Stage();
		farmData.setScene(scene);
		farmData.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * inner class to help read the file
	 * 
	 * @author Yibin Huang
	 *
	 */
	private class SingleFcButtonListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			showSingleFileChooser();
		}
	}

	/**
	 * private method to build the file chooser
	 */
	private void showSingleFileChooser() {
		FileChooser fileChooser = new FileChooser();
		DataStorage dataStorage1 = new DataStorage();
		dataStorage1 = dataStorage;
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			try {
				ReadFile a = new ReadFile(selectedFile, dataStorage);
				showFileUploaded();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e2) {
				showAlertWrongFile();
			}
		}
	}

	/**
	 * private method to create alert GUI
	 */
	private void showAlertWrongDate() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error alert");
		alert.setHeaderText("Date Wrong");
		alert.setContentText("The date you entered does not exist");
		alert.showAndWait();
	}

	/**
	 * private method to create alert GUI
	 */
	private void showAlertWrongFile() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error alert");
		alert.setHeaderText("File Wrong");
		alert.setContentText("The file you uploaded has errors");
		alert.showAndWait();
	}

	/**
	 * private method to create alert GUI
	 */
	private void showFileUploaded() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("File uploaded successfully");
		alert.showAndWait();
	}

}
