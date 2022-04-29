package com.company;

import java.util.ArrayList;

public class Model {
    MyDB db = new MyDB();

    ArrayList<String> getTeacher() {
            return db.query("select TeacherName from Teacher;", "TeacherName");}

        ArrayList<String> getCourse() {
            return db.query("select CourseName from Course;", "CourseName");
        }

        ArrayList<String> getRoom() {
            return db.query("select RoomName from Room;", "RoomName");
        }

        ArrayList<String> getTimeBlock() {
            return db.query("select Time from TimeBlock;", "Time");
        }

        ArrayList<String> get() {
            return db.query("select fld1 from lst order by fld2;", "fld1");}

        String findRoom(String c){
            ArrayList<String> lst= db.query(
                    "select Room.RoomName from Room inner join Course"
                            +" where Course.CourseName = '"+c+"' and Room.CapacityMax > Course.ExpectedStudents;","RoomName");
            System.out.println(lst);
            if(lst.size()==0)return "";
            else return String.valueOf(lst);
        }
    void addSchedule (String CourseName, String Time, String RoomName, String TeacherName){
        db.cmd("insert into Schedule (ScheduleID, CourseName, Time, RoomName, TeacherName) values (NULL, '"+CourseName+"','"+Time+"','"+RoomName+"','"+TeacherName+"');");}

}



