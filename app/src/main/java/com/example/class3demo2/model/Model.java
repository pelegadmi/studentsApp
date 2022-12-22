package com.example.class3demo2.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private static final Model _instance = new Model();

    public static Model instance() {
        return _instance;
    }

    private Model() {
    }

    List<Student> data = new LinkedList<>();

    public List<Student> getAllStudents() {
        return data;
    }

    public void addStudent(Student st) {
        data.add(st);
    }

    public void updateStudent(Student st, int studentPos) {
        data.set(studentPos, st);
    }

    public void deleteStudent(int studentPos) {
        data.remove(studentPos);
    }
}
