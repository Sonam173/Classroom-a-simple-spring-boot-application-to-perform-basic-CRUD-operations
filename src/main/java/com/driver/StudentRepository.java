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
    HashMap<String , List<String>> teacherStudent = new HashMap<>();
    public void addStudent(Student student){
        String key= student.getName();
        studentDb.put(key,student);
        return ;
    }
    public void addTeacher(Teacher teacher)
    {
        String key= teacher.getName();
        teacherDb.put(key,teacher);
        return ;
    }
    public void addStudentTeacherPair(String student , String teacher)
    {
        List<String> students=teacherStudent.get(teacher);
        if(students==null)
        {
            students = new ArrayList<>();

        }
        students.add(student);
        teacherStudent.put(teacher,students);
        return ;

    }
    public Student getStudentByName(String name)
    {
        return studentDb.get(name);
    }
    public Teacher getTeacherByName(String name)
    {

        return  teacherDb.get(name);
    }

    public List<String> getStudentByTeacherName(String teacher){
        return teacherStudent.get(teacher);
    }


    public List<String> getAllStudent() {
        List<String> students = new ArrayList<>();
        for(String s: studentDb.keySet())
        {
            students.add(s);
        }
        return students;
    }


        public void deleteTeacherByName(String teacher)
        {
        for(String str : teacherStudent.get(teacher)){
            studentDb.remove(str);
        }
        teacherStudent.remove(teacher);
        teacherDb.remove(teacher);
    }

    public void deleteAllTeachers() {
        for (String teacher : teacherDb.keySet()) {
            for (String str : teacherStudent.get(teacher)) {
                studentDb.remove(str);
            }
            teacherStudent.remove(teacher);
            teacherDb.remove(teacher);
        }
    }
}
