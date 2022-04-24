package com.company;

import java.sql.*;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class ScheduleModel {
    Connection conn=null;
    String url;
    Statement stmt=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;

    ScheduleModel(String url){
        this.url=url;
    }
    public void connect() throws SQLException {
        conn=getConnection(url);
    }
    public void close() throws SQLException {
        if (conn !=null)
            conn.close();
    }
    public void CreateStatement() throws SQLException {
        this.stmt=conn.createStatement();
    }
    public ArrayList<String> SQLQueryTeacherNames(){
        ArrayList<String> Names=new ArrayList<>();
        String sql="Select Name From Teacher;";
        try {
            rs=stmt.executeQuery(sql);
            while (rs != null && rs.next()){
                String name = rs.getString(1);
                Names.add(name);
            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return Names;
    }
    public void PreparedStmtFindTeachers(){
        String sql=
    }
    public void PrintTeachers(ArrayList<String> Teachers){
        for(int i=0; i<Teachers.size();i++){
            System.out.println(Teachers.get(i));
        }
    }
}
