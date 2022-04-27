package com.company;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;


public class Main extends Application {

    private Model model=new Model();
    private Controller con=new Controller(model,this);
    private TextField field=new TextField();
    private TextArea area=new TextArea();

    ComboBox<String> teacher = new ComboBox<>();
    ComboBox<String> courses = new ComboBox<>();
    ComboBox<String> rooms = new ComboBox<>();
    ComboBox<String> time= new ComboBox<>();

    Button buttonC = new Button("Add Course");
    Button buttonT = new Button("Add Teacher ");
    Button buttonR = new Button("Add Room");
    Button buttonTi = new Button("Add Time");

    void setArea(String s){area.setText(s);}
    void clearField(){field.setText("");}

    @Override
    public void start(Stage stage) {
        con.initArea();
        field.setOnAction(e->con.enterText(field.getText()));

        VBox vbox = new VBox(courses, teacher, rooms, time, field);
        vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setSpacing(10); //gab
        vbox.setStyle("-fx-font: 10 arial;"); //text
        ;

        courses.setPromptText("Select course");
        courses.getItems().addAll(model.getCourse());

        teacher.setPromptText("Select teacher");
        teacher.getItems().addAll(model.getTeacher());

        rooms.setPromptText("Select room");
        rooms.getItems().addAll(model.getRoom());

        time.setPromptText("Select time");
        time.getItems().addAll(model.getTimeBlock());

        HBox hbox = new HBox(buttonC, buttonT, buttonR,buttonTi);
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-font: 10 arial; -fx-base: red; -fx-text-fill: white;");

        buttonC.setOnAction(e->con.addCourse(field.getText()));
        buttonT.setOnAction(e->con.addTeacher(field.getText()));
        buttonR.setOnAction(e->con.addRoom(field.getText()));
        buttonTi.setOnAction(e->con.addTimeBlock(field.getText()));

        BorderPane border = new BorderPane();
        border.setTop(vbox);
        border.setBottom(hbox);
        border.setCenter(area);

        Scene scene = new Scene(border, 500, 500);
        stage.setTitle("Portfolio3");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Controller {
    Model model;
    Main view;

    Controller(Model model, Main view) {
        this.model = model;
        this.view = view;

    }

    void initArea() {
        String toarea = "";
        for (String t : model.get()) toarea += t + "\n";
        view.setArea(toarea);
    }

    void enterText(String s) {
        model.add(s);
        view.clearField();
        String toarea = "";
        for (String t : model.get()) toarea += t + "\n";
        view.setArea(toarea);
    }

    void addTeacher(String s) {
        if (model.hasTeacher(s)) {
            view.setArea("Cannot insert Teacher (repeat) " + s);
        } else {
            model.addTeacher(s);
            view.teacher.getItems().add(s);
        }
    }
    void addCourse(String s) {
        if (model.hasCourse(s)) {
            view.setArea("Cannot insert Course (repeat) " + s);
        } else {
            model.addCourse(s);
            view.courses.getItems().add(s);
        }
    }
    void addRoom(String s) {
        if (model.hasRoom(s)) {
            view.setArea("Cannot insert Room (repeat) " + s);
        } else {
            model.addRoom(s);
            view.courses.getItems().add(s);
        }
    }
    void addTimeBlock(String s) {
        if (model.hasTimeBlock(s)) {
            view.setArea("Cannot insert TimeBlock (repeat) " + s);
        } else {
            model.addTimeBlock(s);
            view.courses.getItems().add(s);
        }
    }
}

class Model {

    MyDB db = new MyDB();

    Model() {
    }

    void addTeacher(String s){
        db.cmd("insert into Teacher (name) values ('"+s+"');");
    }
    ArrayList<String> getTeacher() {
        return db.query("select name from Teacher;", "name");
    }

    void addCourse(String s){
        db.cmd("insert into Course (CourseName) values ('"+s+"');");
    }
    ArrayList<String> getCourse() {
        return db.query("select CourseName from Course;", "CourseName");
    }

    void addRoom(String s){
        db.cmd("insert into Room (RoomName) values ('"+s+"');");
    }
    ArrayList<String> getRoom() {
        return db.query("select RoomName from Room;", "RoomName");
    }

    void addTimeBlock(String s){
        db.cmd("insert into TimeBlock (Time) values ('"+s+"');");
    }
    ArrayList<String> getTimeBlock() {
        return db.query("select Time from TimeBlock;", "Time");
    }

    void add(String s) { // remember to sanitize your data!
        db.cmd("insert into Teacher (fld2) values ('" + s + "');");
    }

    ArrayList<String> get() {
        return db.query("select name from Teacher order by name;", "name");
    }

    boolean hasTeacher(String s) {
        ArrayList<String> lst = db.query("select name from Teacher where name = '" + s + "';", "name");
        System.out.println(lst);
        return lst.size() > 0;

    }
    boolean hasCourse(String s) {
        ArrayList<String> lst = db.query("select CourseName from Course where name = '" + s + "';", "CourseName");
        System.out.println(lst);
        return lst.size() > 0;

    }
    boolean hasRoom(String s) {
        ArrayList<String> lst = db.query("select RoomName from Room where name = '" + s + "';", "RoomName");
        System.out.println(lst);
        return lst.size() > 0;

    }
    boolean hasTimeBlock(String s) {
        ArrayList<String> lst = db.query("select name from TimeBlock where name = '" + s + "';", "Time");
        System.out.println(lst);
        return lst.size() > 0;
    }

}

class MyDB {
    Connection conn = null;

    MyDB() {
        if (conn == null) open();
    }

    public void open() {
        try {
            String url = "jdbc:sqlite:database\\database.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            if (conn != null) close();
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("cannot close");
        }
        conn = null;
    }

    public void cmd(String sql) {
        if (conn == null) open();
        if (conn == null) {
            System.out.println("No connection");
            return;
        }
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException E) {
            System.out.println("Error in statement " + sql);
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Error in statement" + sql);
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Error in statement " + sql);
        }
    }

    public ArrayList<String> query(String query, String fld) {
        ArrayList<String> res = new ArrayList<>();
        if (conn == null) open();
        if (conn == null) {
            System.out.println("No connection");
            return res;
        }
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString(fld);
                res.add(name);
            }
        } catch (SQLException e) {
            System.out.println("Error in statement " + query + " " + fld);
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Error in statement " + query + " " + fld);
        }
        return res;
    }

}


