package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository
{
    HashMap<String , Student> studentDb = new HashMap<>();
    HashMap<String , Teacher> teacherDb = new HashMap<>();
    HashMap<String , List<String>> studentTeacherPairDb = new HashMap<>();
    public void addStudent(Student student){
        studentDb.put(student.getName(),student);
    }
    public void addTeacher(Teacher teacher)
    {
        teacherDb.put(teacher.getName(),teacher);
    }
    public void addStudentTeacherPair(String student , String teacher)
    {
        List<String> teacher_list;
        if(studentTeacherPairDb.containsKey(teacher))
        {
            teacher_list = studentTeacherPairDb.get(teacher);
        }else{
            teacher_list = new ArrayList<>();
        }
        teacher_list.add(student);
        studentTeacherPairDb.put(teacher,teacher_list);
    }
    public Student getStudentByName(String name){
        return studentDb.get(name);
    }
    public Teacher getTeacherByName(String name){
        return  teacherDb.get(name);
    }

    public List<String> getStudentByTeacherName(String teacher){
        return studentTeacherPairDb.get(teacher);
    }


    public List<String> getAllStudent() {
        List<String> list = new ArrayList<>();
        list.add(studentDb.keySet().toString());
        return list;
    }


        public void deleteTeacherByName(String teacher){
        List<String> student_list = studentTeacherPairDb.get(teacher);
        for(String s : student_list){
            studentDb.remove(s);
        }
        teacherDb.remove(teacher);
        studentTeacherPairDb.remove(teacher);
    }

    public void deleteAllTeachers() {
        for (List<String> student_list : studentTeacherPairDb.values()) {
            for (String s : student_list) {
                studentDb.remove(s);
            }
            teacherDb.clear();
            studentTeacherPairDb.clear();
        }
    }
}
