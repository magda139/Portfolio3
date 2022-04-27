package com.company;

public class Schedule {
    private String course;
    private int studentExpected;
    private String timeBlock;
    private String room;
    private int roomCapacity;
    private String teacher;

    public Schedule() {
        this.course = "";
        this.studentExpected = 0;
        this.timeBlock = "";
        this.room = "";
        this.roomCapacity = 0;
        this.teacher = "";

    }

    public Schedule(String course, int studentExpected, String timeBlock, String room, int roomCapacity, String teacher) {
        this.course = course;
        this.studentExpected = studentExpected;
        this.timeBlock = timeBlock;
        this.room = room;
        this.teacher = teacher;
        this.roomCapacity = roomCapacity;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getStudentExpected() {
        return studentExpected;
    }

    public void setStudentExpected(int studentExpected) {
        this.studentExpected = studentExpected;
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

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}


