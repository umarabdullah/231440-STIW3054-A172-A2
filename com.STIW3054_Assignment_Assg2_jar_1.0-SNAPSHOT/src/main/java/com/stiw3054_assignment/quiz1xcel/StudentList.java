/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stiw3054_assignment.quiz1xcel;

import java.util.LinkedList;

/**
 *
 * @author abdullah
 */
public class StudentList {
    Student student;
    LinkedList <Student> studentList = new LinkedList <Student> ();

    public StudentList() {
       
    }

    public Student getStudent(int i) {
        return studentList.get(i);
    }

    public LinkedList<Student> getStudentList() {
        return studentList;
    }
    public int getSize (){
      return studentList.size();
    }

    public void setStudent(Student student) {
        studentList.add(student);
    }

    public void setStudentList(LinkedList<Student> studentList) {
        this.studentList = studentList;
    }
    
    
    
    
    
}
