package com.google;

import com.google.controller.impl.StudentControllerImpl;

public class Main {
    public static void main(String[] args) {
        StudentControllerImpl controller = new StudentControllerImpl();
        controller.start();
    }
}