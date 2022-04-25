package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    Button button = new Button("Add teacher");

    void setArea(String s){area.setText(s);}
    void clearField(){field.setText("");}

    @Override
    public void start(Stage stage) {
        con.initArea();
        field.setOnAction(e->con.enterText(field.getText()));
        //VBox root = new VBox(courses,teacher,rooms,time,field,button,button2, area);
        VBox root = new VBox(courses,teacher,rooms, field, time,button, area);
        root.setSpacing(10);

        courses.setStyle("-fx-font: 15 arial;");
        courses.setPromptText("Select course");
        courses.getItems().addAll(model.getCourse());

        teacher.setStyle("-fx-font: 15 arial;");
        teacher.setPromptText("Select teacher");
        teacher.getItems().addAll(model.getTeacher());

        rooms.setStyle("-fx-font: 15 arial;");
        rooms.setPromptText("Select room");
        rooms.getItems().addAll(model.getRoom());

        time.setStyle("-fx-font: 15 arial;");
        time.setPromptText("Select time");
        time.getItems().addAll(model.getTimeBlock());

        //button.setOnAction(e->con.model.addTeacher(field.getText()));
        button.setStyle("-fx-font: 10 arial; -fx-base: red; -fx-text-fill: white;");
        button.setOnAction(e->con.addTeacher(field.getText()));

        Scene scene = new Scene(root, 500, 500);
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

    ArrayList<String> getCourse() {
        return db.query("select CourseName from Course;", "CourseName");
    }

    ArrayList<String> getRoom() {
        return db.query("select RoomName from Room;", "RoomName");
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
        //return getTeacher().contains(s);
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


