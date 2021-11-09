package com.giangvt.school;

import java.util.ArrayList;
import java.util.List;

public class StudentList {
    private List<Student> studentList;

    public StudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public boolean addStudent(Student student) {
        boolean result = false;
        if (this.findStudentById(student.getId()) == null) {
            this.studentList.add(student);
            result = true;
        }
        return result;
    }

    public Student findStudentById(String id) {
        Student result = null;
        for (Student student :
                this.studentList) {
            if (student.getId().equals(id)) {
                result = student;
                break;
            }
        }
        return result;
    }

    public Student getXestGPAStudent(boolean x) {
        if (!this.studentList.isEmpty() && this.studentList.size() != 0) {
            Student student = this.studentList.get(0);
            for (Student s :
                    this.studentList) {
                if (x) {
                    if (s.getGPA() > student.getGPA()) {
                        student = s;
                    }
                } else {
                    if (s.getGPA() < student.getGPA()) {
                        student = s;
                    }
                }
            }
            return student;
        }
        return null;
    }
}
