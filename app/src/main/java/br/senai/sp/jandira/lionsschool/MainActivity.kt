package br.senai.sp.jandira.lionsschool

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.lionsschool.model.Student
import br.senai.sp.jandira.lionsschool.model.StudentList
import br.senai.sp.jandira.lionsschool.service.RetrofitFactory
import br.senai.sp.jandira.lionsschool.ui.theme.LionsSchoolTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionsSchoolTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    var students by remember {
        mutableStateOf(listOf<Student>())
    }

    Column() {
        Text(text = "testando")
        Button(
            onClick = {
                val call = RetrofitFactory().getStudentService().getStudents()

                call.enqueue(object : Callback<StudentList> {

                    override fun onResponse(
                        call: Call<StudentList>,
                        response: Response<StudentList>
                    ) {
                        students = response.body()!!.alunos
                        Log.i("ds2m", "onResponse: $students")
                    }

                    override fun onFailure(call: Call<StudentList>, t: Throwable) {
                        Log.i(
                            "ds2m",
                            "onFailure ${t.message}"
                        )
                    }
                })
            }
        ) {
            Text(text = "List All Students")
        }
        LazyColumn(){
            items(students) {
                Text(text = it.nome)
                Text(text = it.sexo)
            }
        }
    }



}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LionsSchoolTheme {
        Greeting("Android")
    }
}