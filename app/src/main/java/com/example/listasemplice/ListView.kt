package com.example.listasemplice

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.listasemplice.data.DataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListView: ViewModel() {
    private val _uiList = MutableStateFlow(DataSource())
    val uiList: StateFlow<DataSource> = _uiList.asStateFlow()

    var textFieldName by mutableStateOf("")
    var textFieldSurname by mutableStateOf("")
    var textFieldAge by mutableStateOf("")


    fun updateTextFieldName (tFN: String){
        textFieldName = tFN
    }
    fun updateTextFieldSurname (tFS: String){
        textFieldSurname = tFS
    }
    fun updateTextFieldAge (tFA: String){
        textFieldAge = tFA
    }
    fun addPersonalInfo (context: Context){
        if(textFieldName != "" && textFieldSurname != "" && textFieldAge != ""){
       _uiList.value.add(textFieldName,textFieldSurname,textFieldAge)
        textFieldName = ""
        textFieldSurname = ""
        textFieldAge = ""
        }else{
            Toast.makeText(context, "Insert all the argument please!", Toast.LENGTH_LONG).show()
        }
    }


}