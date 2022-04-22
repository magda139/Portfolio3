package com.company;

import java.sql.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;


public class Main extends Application {

    private Model model=new Model();
    private Controller con=new Controller(model,this);
    private TextField field=new TextField();
    private TextArea area=new TextArea();
    ComboBox<String> lecturer = new ComboBox<>();
    ComboBox<String> courses = new ComboBox<>();
    ComboBox<String> rooms = new ComboBox<>();
    ComboBox<String> timeslot = new ComboBox<>();
    Button button = new Button("Add lecturer");
    Button button2 = new Button("Find room");
    void setArea(String s){area.setText(s);}
    void clearField(){field.setText("");}
    @Override
    public void start(Stage stage) {
        con.initArea();
        field.setOnAction(e->con.enterText(field.getText()));
        //VBox root = new VBox(courses,lecturer,rooms,timeslot,field,button,button2,area);
        VBox root = new VBox(lecturer,field,button,button2,area);
        lecturer.getItems().addAll(model.getTeacher());
        //courses.getItems().addAll(model.getCourses());
        //rooms.getItems().addAll(model.getRoom());
        //timeslot.getItems().addAll(model.getTimeslot());
        button.setOnAction(e->con.model.addTeacher(field.getText()));
        //button2.setOnAction(e->con.findRoom(courses.getValue()));
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("Portfolio3");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

class Controller{
    Model model;
    Main view;
    Controller(Model model, Main view){
        this.model=model; this.view=view;
    }
    void initArea(){
        String toarea="";
        for(String t:model.get())toarea+=t+"\n";
        view.setArea(toarea);
    }
    void enterText(String s){
        model.add(s);
        view.clearField();
        String toarea="";
        for(String t:model.get())toarea+=t+"\n";
        view.setArea(toarea);
    }
    /*void addLecturer(String s){
        if(model.hasLecturer(s)){
            view.setArea("Cannot insert lecturer (repeat) "+s);
        } else {
            model.addLecturer(s);
            view.lecturer.getItems().add(s);
        }*/
}

class Model{

    MyDB db = new MyDB();
    Model(){
        //db.cmd("create table if not exists Teacher" + "fld1 integer primary key autoincrement, fld2 text);");
        db.cmd("drop table if exists Teacher ;");
        db.cmd("create table if not exists Teacher "+ "(name text);");
        for(String s: getTeacher())System.out.println(s);
    }
    void addTeacher(String s){
        db.cmd("insert into Teacher (name) values ('"+s+"');");
    }
    ArrayList<String> getTeacher(){
        return db.query("select name from Teacher;","name");
    }
    void add(String s){ // remember to sanitize your data!
        db.cmd("insert into Teacher (name) values ('"+s+"');");
    }
    ArrayList<String> get(){
        return db.query("select name from Teacher order by name;","name");
    }

    //ArrayList<String> list=new ArrayList<>();
    //void add(String s){list.add(s);}
    //ArrayList<String> get(){return list;}
}

class MyDB{
    Connection conn = null;
    MyDB(){


        if(conn == null)open();
    }
    public void open(){
        try{
            String url = "jdbc:sqlite:C:\\Users\\Magda\\IdeaProjects\\portfolio3\\database\\database.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("cannot open");
            if(conn != null)close();
        };
    }
    public void close(){
        try{
            if(conn != null ) {
                conn.close();
            }
        } catch (SQLException e){
            System.out.println("cannot close");
        }
        conn=null;
    }
    public void cmd(String sql){
        if(conn == null)open();
        if (conn==null){
            System.out.println("No connection");
            return;
        }
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }catch (SQLException E){
            System.out.println("Error in statement " + sql);
        }
        try{
            if(stmt != null){
                stmt.close();
            }
        }catch (SQLException e){
            System.out.println("Error in statement" + sql);
        }
        try {
            if (stmt !=null){
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Error in statement " + sql);
        }
    }

    public ArrayList<String> query(String query,String fld){
        ArrayList<String> res=new ArrayList<>();
        if(conn==null)open();
        if(conn==null){System.out.println("No connection");return res;}
        Statement stmt=null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString(fld);
                res.add(name);
            }
        } catch (SQLException e ) {
            System.out.println("Error in statement "+query+" "+fld);
        }
        try {
            if (stmt != null) { stmt.close(); }
        } catch (SQLException e ) {
            System.out.println("Error in statement "+query+" "+fld);
        }
        return res;
    }

}
//Example from Moodle
//Model();{
// db.cmd(" drop table ");
// db.cmd("course" " 100 " );
// }
//addRoom, addLecturer
//for(int i = 1; i < 10; i++) addSlot("Slot" + i);
//boolean hasLecturer(String s)

