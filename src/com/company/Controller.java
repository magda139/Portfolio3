package com.company;

public class Controller {
        Model model;
        Main view;

        Controller(Model model, Main view) {
            this.model = model;
            this.view = view;

        }

        //print area
        void initArea() {
            String toarea = "";
            for (String t : model.get()) toarea += t + "\n";
            view.setArea(toarea);
        }

        void findRoom(String c) {
            String room = model.findRoom(c);
            if (room.equals("")) view.setArea("No Room");
            else view.setArea("Room: " + room);
        }
    void availableTeachers(String d) {
        String teachers = model.availableTeachers(d);
        if (teachers.equals("")) view.setArea("No teachers");
        else view.setArea("Available Teachers: " + teachers);
    }
    }
