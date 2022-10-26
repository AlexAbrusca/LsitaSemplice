package com.example.listasemplice.data

import com.example.listasemplice.model.PersonalInfo

class DataSource {
    var personalInfoList = mutableListOf<PersonalInfo>()
    fun add (name: String, surname: String, age: String){
        personalInfoList.add(PersonalInfo(name = name, surname = surname, age = age))
    }
    override fun toString(): String {
        return "DataSource(personalInfoList=$personalInfoList)"
    }
}


