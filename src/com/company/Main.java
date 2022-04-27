package com.company;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class Main extends Application {

    private Model model=new Model();
    private Controller con=new Controller(model,this);
    private TextField field=new TextField();
    private TextArea area=new TextArea();
    TableView<Schedule> table = new TableView<Schedule>();
    HBox hb = new HBox();

    ComboBox<String> teacher = new ComboBox<>();
    ComboBox<String> courses = new ComboBox<>();
    ComboBox<String> rooms = new ComboBox<>();
    ComboBox<String> time= new ComboBox<>();

    Button buttonC = new Button("Add Course");
    Button buttonT = new Button("Add Teacher ");
    Button buttonR = new Button("Find Room");
    Button buttonTi = new Button("Add Time");
    Button Addbutton = new Button("Add to the Schedule");


    void setArea(String s){area.setText(s);}
    void clearField(){field.setText("");}

    @Override
    public void start(Stage stage) {
        //con.initArea();
       // field.setOnAction(e->con.enterText(field.getText()));


        final VBox vbox = new VBox(courses, teacher, rooms, time);
        vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setSpacing(10); //gab
        vbox.setStyle("-fx-font: 10 arial;"); //text
        vbox.getChildren().addAll(table,hb);


        courses.setPromptText("Select course");
        courses.getItems().addAll(model.getCourse());

        teacher.setPromptText("Select teacher");
        teacher.getItems().addAll(model.getTeacher());

        rooms.setPromptText("Select room");
        rooms.getItems().addAll(model.getRoom());

        time.setPromptText("Select time");
        time.getItems().addAll(model.getTimeBlock());


        TableColumn CourseColumn = new TableColumn<Schedule,String> ("Course");
        CourseColumn.setCellFactory(new PropertyValueFactory<Schedule, String>("course"));

        TableColumn StudentExpectedColumn = new TableColumn<Schedule,String> ("Expected students");
        StudentExpectedColumn.setCellFactory(new PropertyValueFactory<Schedule, String>("studentExpected"));

        TableColumn TimeBlockColumn = new TableColumn<Schedule,String> ("Time block");
        TimeBlockColumn.setCellFactory(new PropertyValueFactory<Schedule, String>("timeBlock"));

        TableColumn RoomColumn = new TableColumn<Schedule,String> ("Room");
        RoomColumn.setCellFactory(new PropertyValueFactory<Schedule, String>("room"));

        TableColumn RoomCapacityColumn = new TableColumn<Schedule,String> ("Room capacity");
        RoomCapacityColumn.setCellFactory(new PropertyValueFactory<Schedule, String>("roomCapacity"));

        TableColumn TeacherColumn = new TableColumn<Schedule,String> ("Teacher");
        TeacherColumn.setCellFactory(new PropertyValueFactory<Schedule, String>("teacher"));



        table.getColumns().add(CourseColumn);
        table.getColumns().addAll(StudentExpectedColumn);
        table.getColumns().addAll(TimeBlockColumn);
        table.getColumns().addAll(RoomColumn);
        table.getColumns().addAll(RoomCapacityColumn);
        table.getColumns().addAll(TeacherColumn);


       // HBox hbox = new HBox(buttonC, buttonT, buttonR,buttonTi);
        //hbox.setPadding(new Insets(15, 12, 15, 12));
      //  hbox.setSpacing(10);
       // hbox.setStyle("-fx-font: 10 arial; -fx-base: red; -fx-text-fill: white;");

        buttonC.setOnAction(e->con.addCourse(field.getText()));
        buttonT.setOnAction(e->con.addTeacher(field.getText()));
        //buttonR.setOnAction(e->con.addRoom(field.getText()));
        buttonR.setOnAction(e->con.findRoom(courses.getValue()));
        buttonTi.setOnAction(e->con.addTimeBlock(field.getText()));

        hb.getChildren().addAll(Addbutton); //add more buttons here for the footer
        hb.setSpacing(10);

        BorderPane border = new BorderPane();
        border.setTop(vbox);
     //   border.setBottom(hbox);
        border.setCenter(area);

        Scene scene = new Scene(border, 500, 600);
        stage.setTitle("Portfolio3");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



