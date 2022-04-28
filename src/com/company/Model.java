package com.company;

import java.util.ArrayList;

public class Model {

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
            db.cmd("insert into Room (RoomName) values ('"+s+"');");}
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
            db.cmd("insert into Schedule (Course, StudentExpected,Teacher, Room, RoomCapacity) values ('" + s + "');");
        }
        ArrayList<String> get() {
            return db.query("select Course, StudentExpected,Teacher, Room, RoomCapacity from Schedule order by LectureID;", "Course, StudentExpected,Teacher, Room, RoomCapacity");
        }

        String findRoom(String c){
            ArrayList<String> Schedule= db.query(
                    "select Room.RoomName from Room inner join Course"
                            +" where Course.CourseName = '"+c+"' and Room.CapacityMax > Course.ExpectedStudents;","RoomName");
            System.out.println(Schedule);
            if(Schedule.size()==0)return "";
            else return Schedule.get(0);
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

