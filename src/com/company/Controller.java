package com.company;

public class Controller {
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



        void findRoom(String c) {
            String room = model.findRoom(c);
            if (room.equals("")) view.setArea("No Room");
            else view.setArea("Room: " + room);
        }
    }
