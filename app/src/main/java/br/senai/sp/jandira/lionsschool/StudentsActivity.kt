package br.senai.sp.jandira.lionsschool

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.lionsschool.components.CardInfo
import br.senai.sp.jandira.lionsschool.components.LionsWhite
import br.senai.sp.jandira.lionsschool.components.Profile
import br.senai.sp.jandira.lionsschool.model.Course
import br.senai.sp.jandira.lionsschool.model.CourseList
import br.senai.sp.jandira.lionsschool.model.Student
import br.senai.sp.jandira.lionsschool.model.StudentList
import br.senai.sp.jandira.lionsschool.service.RetrofitFactory
import br.senai.sp.jandira.lionsschool.ui.theme.BlueLions
import br.senai.sp.jandira.lionsschool.ui.theme.LionsSchoolTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentsAcitivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getIntExtra("id", 0)
        val sigla = intent.getStringExtra("sigla") ?: "Sigla Default"
        val nome = intent.getStringExtra("nome") ?: "Nome Default"
        val carga = intent.getIntExtra("carga", 1234)

        
        Log.i("pendurando id", "Extras : $id")
        setContent {
            LionsSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                        StudentsFromCourse(
                            stringResource(id = R.string.enterprise_name),
                            sigla,
                            nome,
                            carga
                        )
                    }
                }
            }
        }
    }


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StudentsFromCourse( 
    enterpriseName : String,
    sigla : String,
    nome : String,
    carga : Int
) {
    var context = LocalContext.current
    var students by remember {
        mutableStateOf(listOf<Student>())
    }

    val call = RetrofitFactory().getStudentService().getStudentsFromCourse(sigla)
    call.enqueue(object : Callback<StudentList> {
        override fun onResponse(call: Call<StudentList>, response: Response<StudentList>) {
            students = response.body()!!.alunos
        }

        override fun onFailure(call: Call<StudentList>, t: Throwable) {
            Log.i("ds2m", "onFailure: Error data ${t.message}",)
        }
    })


   Column(
       modifier = Modifier
           .fillMaxSize()
           .background(BlueLions)
           .padding(
               vertical = 40.dp,
               horizontal = 36.dp
           ),
   ) {
       Profile(
           enterpriseName,

       )
       Column(
           verticalArrangement = Arrangement.Center
       ) {
           Spacer(modifier = Modifier
               .fillMaxWidth()
               .height(33.dp))
           CardInfo(
               sigla,
               nome,
               carga
           )
       }
       Row() {
           Button(onClick = {
               students = getStudentsFromCourse( "Aprovado")
           }) {
               LionsWhite(text = "Aprovado")
           }
           Spacer(modifier = Modifier
               .fillMaxHeight()
               .width(14.dp))
           Button(onClick = {
               students = getStudentsFromCourse( "Exame")
           }) {
               LionsWhite(text = "Em Exame")
           }
           Spacer(modifier = Modifier
               .fillMaxHeight()
               .width(14.dp))
           Button(
               onClick = {
                   students = getStudentsFromCourse( "Reprovado")
               }
           ) {
               LionsWhite(text = "Reprovado")
           }

       }
       LazyColumn(){
        items(students){
            Text(text = it.id.toString())
            Text(text = it.nome)
            Text(text = it.foto)
            Text(text = it.status)
            Text(text = it.curso.toString())
            Text(text = it.matricula.toString())


        }
       }
   }

}
fun getStudentsFromCourse(sigla : String) : List<Student> {

    val call = RetrofitFactory().getStudentService().getStudentsFromCourse(sigla)
    var students = listOf<Student>()

    call.enqueue(object : Callback<StudentList> {

        override fun onResponse(call: Call<StudentList>, response: Response<StudentList>) {
            students = response.body()!!.alunos
            Log.i("ds2m", "onResponse: Students : $students")
        }

        override fun onFailure(call: Call<StudentList>, t: Throwable) {
            Log.i(
                "ds2m",
                "onFailure ${t.message}"
            )
        }


    })

    return students
}
@Preview(showBackground = true)
@Composable
fun StudentsFromCoursePreview() {
    LionsSchoolTheme {
        StudentsFromCourse(
            enterpriseName = stringResource(id = R.string.enterprise_name),
            sigla = "DS", 
            nome = "Desenvolvimento de Sistemas", 
            carga = 1200)
    }
}