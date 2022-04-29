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
            String rooms = model.findRoom(c);
            if (rooms.equals("")) view.setArea("No Room");
            else view.setArea("Recommended Room: " + rooms);
        }
    }
