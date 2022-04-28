package com.company;

import java.util.ArrayList;

public class Model {
    Model(){
    }

        MyDB db = new MyDB();

    /*void addSchedule(String courseName, String time, String roomName, String teacherName){
        db.cmd("insert into Schedule (CourseName, Time, RoomName, TeacherName) values ('%1$s, %2$s, %3$s, %4$s');", ("courseName, time, roomName, teacherName");}
    }*/

    /*void addSchedule(String courseName, String time, String roomName, String teacherName){
        db.cmd(String.format("insert into Schedule (CourseName, Time, RoomName, TeacherName) values ('%1$s, %2$s, %3$s, %4$s');", courseName, time, roomName, teacherName));
     void addTimeslot(String s){ // remember to sanitize your data!
        db.cmd("insert into Timeslot (name) values ('"+s+"');");
    }*/
    void addSchedule (String CourseName, String Time, String RoomName, String TeacherName){
        db.cmd("insert into Schedule (CourseName, Time, RoomName, TeacherName) values ('"+CourseName+","+Time+","+RoomName+","+TeacherName+"');");}

        ArrayList<String> getTeacher() {
            return db.query("select TeacherName from Teacher;", "TeacherName");
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
            db.cmd("insert into Schedule (CourseName,Time, RoomName, TeacherName) values ('" + s + "');");
        }

        ArrayList<String> get() {
            return db.query("select CourseName,Time, RoomName, TeacherName from Schedule order by ScheduleID;", "CourseName,Time, RoomName, TeacherName");
        }

        String findRoom(String c){
            ArrayList<String> Schedule= db.query(
                    "select Room.RoomName from Room inner join Course"
                            +" where Course.CourseName = '"+c+"' and Room.CapacityMax > Course.ExpectedStudents;","RoomName");
            System.out.println(Schedule);
            if(Schedule.size()==0)return "";
            else return Schedule.get(0);
        }

    }

