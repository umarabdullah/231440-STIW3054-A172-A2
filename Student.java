/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stiw3054_assignment.quiz1xcel;

/**
 *
 * @author abdullah
 */
public class Student {
    private String matric;
    private String name;
    private String num;
    private String sv;
    
    public Student(){}

    public Student(String matric, String name, String num, String sv) {
        this.matric= matric;
        this.name = name;
        this.num = num;
        this.sv = sv;

    }

    public String getMatric() {
        return matric;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    public void setSv(String sv) {
        this.sv = sv;
    }

    public void setMatric(String matric) {
        this.matric = matric;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(String num) {
        this.num = num;
    }
    
    public String toString() {
        String s = "Matric : " + matric + "\n Name : " + name + "\nE-mail : " + num + "\nSupervisor : " +sv+"\n\n";
       return s;
    }

    public String getSv() {
        return sv;
    }

}
