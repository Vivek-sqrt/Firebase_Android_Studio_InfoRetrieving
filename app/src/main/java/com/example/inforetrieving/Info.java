package com.example.inforetrieving;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Info {
    private String name;
    private String age;
    private String universityname;

    public Info(){

    }

    public Info(String name, String age, String universityname){
        this.name = name;
        this.age = age;
        this.universityname = universityname;
    }

    public String getName(){
        return name;
    }
    public String getAge(){
        return age;
    }
    public String getUniversityname(){
        return universityname;
    }

    public String toStringMine(){
        return name+" "+age+" "+universityname;
    }

}
