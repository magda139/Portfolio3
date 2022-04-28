package com.company;

public class Schedule {
    private String course;

    private String timeBlock;
    private String room;

    private String teacher;

   // public Schedule(String value, String timeValue, String roomsValue, String teacherValue) {
      //  this.course = "";
        //this.timeBlock = "";
        //this.room = "";
        //this.teacher = "";

    //}

    public Schedule(String course, String timeBlock, String room, String teacher) {
        this.course = course;
        this.timeBlock = timeBlock;
        this.room = room;
        this.teacher = teacher;

    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }





    public String getTimeBlock() {
        return timeBlock;
    }

    public void setTimeBlock(String timeBlock) {
        this.timeBlock = timeBlock;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }



    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;

    }


}



