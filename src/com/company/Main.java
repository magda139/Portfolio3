package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Model model=new Model();
    private Controller con=new Controller(model,this);
    private TextArea area=new TextArea();
    TableView<Schedule> table = new TableView<Schedule>();
    HBox hb = new HBox();

    ComboBox<String> teacher = new ComboBox<>();
    ComboBox<String> courses = new ComboBox<>();
    ComboBox<String> rooms = new ComboBox<>();
    ComboBox<String> time= new ComboBox<>();

    Button buttonT = new Button("Available teachers");
    Button buttonR = new Button("Find Room");

    void setArea(String s){area.setText(s);}

    @Override
    public void start(Stage stage) {
        con.initArea();

        GridPane newDataPane = this.getScheduleDataPane();

        final VBox vbox = new VBox(courses, time,buttonR, rooms, buttonT, teacher, area);
        vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setSpacing(10); //gab
        vbox.setStyle("-fx-font: 10 arial;"); //text
        vbox.getChildren().addAll(newDataPane, table,hb);

        courses.setPromptText("Select course");
        courses.getItems().addAll(model.getCourse());

        time.setPromptText("Select time");
        time.getItems().addAll(model.getTimeBlock());

        rooms.setPromptText("Select room");
        rooms.getItems().addAll(model.getRoom());

        teacher.setPromptText("Select teacher");
        teacher.getItems().addAll(model.getTeacher());


        TableColumn CourseColumn = new TableColumn<Schedule,String> ("Course");
        CourseColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("course"));

        TableColumn TimeBlockColumn = new TableColumn<Schedule,String> ("Time block");
        TimeBlockColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("timeBlock"));

        TableColumn RoomColumn = new TableColumn<Schedule,String> ("Room");
        RoomColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("room"));

        TableColumn TeacherColumn = new TableColumn<Schedule,String> ("Teacher");
        TeacherColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("teacher"));


        table.getColumns().addAll(CourseColumn);
        table.getColumns().addAll(TimeBlockColumn);
        table.getColumns().addAll(RoomColumn);
        table.getColumns().addAll(TeacherColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //columns size

        buttonR.setOnAction(e->con.findRoom(courses.getValue()));

        BorderPane border = new BorderPane();
        border.setLeft(vbox);
        border.setCenter(table);
        border.setBottom(area);

        Scene scene = new Scene(border, 500, 600);
        stage.setTitle("Portfolio3");
        stage.setScene(scene);
        stage.show();
    }

    public GridPane getScheduleDataPane() {

        // Create the GridPane
        GridPane pane = new GridPane();

        // Set the hgap and vgap properties
        pane.setHgap(10);
        pane.setVgap(5);

        Button AddButton = new Button("Add to the Schedule");
        AddButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
                if (courses.getValue() != null &&
                        time.getValue() != null &&
                        rooms.getValue() != null &&
                        teacher.getValue() != null)
                {
                    model.addSchedule(courses.getValue(), time.getValue(), rooms.getValue(), teacher.getValue());
                    addToTable();
                }
                else
                {
                    System.out.println("You need to choose all values.");
                }
            }
        });

        pane.add(AddButton, 1, 10);
        return pane;
    }
    public void addToTable(){
        Schedule Schedule = new Schedule(courses.getValue(), time.getValue(), rooms.getValue(), teacher.getValue());
        table.getItems().add(Schedule);
    }
    public static void main (String[]args){
        launch(args);
    }
    }
