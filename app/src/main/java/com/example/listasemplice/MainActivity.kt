package com.example.listasemplice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listasemplice.model.PersonalInfo
import com.example.listasemplice.ui.theme.ListaSempliceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaSempliceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppList(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun AppList (
    modifier: Modifier = Modifier,
    listView: ListView = viewModel()
){
    val listUIState by listView.uiList.collectAsState()
    val mContext = LocalContext.current

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
            ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = listView.textFieldName,
                onValueChange = {listView.updateTextFieldName(it)},
                label = { Text(text = "Insert Name") }
            )
            OutlinedTextField(
                value = listView.textFieldSurname,
                onValueChange = {listView.updateTextFieldSurname(it)},
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Insert Surname") }
            )
            OutlinedTextField(
                value = listView.textFieldAge,
                onValueChange = {listView.updateTextFieldAge(it)},
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Insert Age") }
            )
            Button(onClick = {
                listView.addPersonalInfo(mContext)

            }) {
                Text(text = "Add Personal Info")
            }
        }
        PersonalInfoList(personalInfoList = listUIState.personalInfoList)
    }
}

@Composable
fun CardList (
    modifier: Modifier = Modifier,
    personalInfo: PersonalInfo
){
    Card(
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        elevation = 6.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(horizontalArrangement = Arrangement.Start) {
                Text(text = "Name:", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = personalInfo.name)
            }
            Row(horizontalArrangement = Arrangement.Start) {
                Text(text = "Surname:", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = personalInfo.surname)
            }
            Row(horizontalArrangement = Arrangement.Start) {
                Text(text = "Age:", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = personalInfo.age)
            }


        }
    }
}
@Composable
fun PersonalInfoList (personalInfoList: List<PersonalInfo> ){
    LazyColumn{
        items(personalInfoList){ item ->
            CardList(personalInfo = item, modifier = Modifier.fillMaxWidth())
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ListaSempliceTheme {
        AppList()
    }
}