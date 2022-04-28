package com.company;

import java.util.ArrayList;

public class Model {
    Model(){
    }

        MyDB db = new MyDB();

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



       /* void add(String s) { // remember to sanitize your data!
            db.cmd("insert into Schedule (Course, StudentExpected,Teacher, Room, RoomCapacity) values ('" + s + "');");
        }*/

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

    }

