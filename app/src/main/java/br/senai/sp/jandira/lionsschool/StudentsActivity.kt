package br.senai.sp.jandira.lionsschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.lionsschool.components.CardInfo
import br.senai.sp.jandira.lionsschool.components.LionsWhite
import br.senai.sp.jandira.lionsschool.components.Profile
import br.senai.sp.jandira.lionsschool.model.Student
import br.senai.sp.jandira.lionsschool.model.StudentList
import br.senai.sp.jandira.lionsschool.model.StudentsFromCourseList
import br.senai.sp.jandira.lionsschool.service.RetrofitFactory
import br.senai.sp.jandira.lionsschool.ui.theme.BlueLions
import br.senai.sp.jandira.lionsschool.ui.theme.LionsSchoolTheme
import okhttp3.internal.filterList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentsActivity : ComponentActivity() {
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


    val call = RetrofitFactory().getStudentService().getStudentsFromCourse(sigla, null)

    call.enqueue(object : Callback<StudentList> {
        override fun onResponse(
            call: Call<StudentList>,
            response: Response<StudentList>
        ) {
            students = response.body()!!.alunos
            Log.i("Load AllStudents", "result: $students")

        }

        override fun onFailure(call: Call<StudentList>, t: Throwable) {
            Log.i("StudentsFromCourse", "onFailure: Error get students from courses ${t.message}")
        }
    })

    Column(
       modifier = Modifier
           .fillMaxSize()
   ) {
//       Profile(
//           enterpriseName,
//
//       )
//       Column(
//           verticalArrangement = Arrangement.Center
//       ) {
//           Spacer(modifier = Modifier
//               .fillMaxWidth()
//               .height(33.dp))
//           CardInfo(
//               sigla,
//               nome,
//               carga
//           )
//       }
       Row {
           Button(onClick = {
               val call = RetrofitFactory().getStudentService().getStudentsFromCourse(sigla, null)

               call.enqueue(object : Callback<StudentList> {
                   override fun onResponse(
                       call: Call<StudentList>,
                       response: Response<StudentList>
                   ) {
                       students = response.body()!!.alunos
                       Log.i("Load All Students", "result: $students")

                   }

                   override fun onFailure(call: Call<StudentList>, t: Throwable) {
                       Log.i("Load All Students", "onFailure: Error get students from courses ${t.message}")
                   }
               })
           }) {
               LionsWhite(text = "Todos")
           }
           Button(onClick = {
               val call = RetrofitFactory().getStudentService().getStudentsFromCourse(sigla, "Cursando")

               call.enqueue(object : Callback<StudentList> {
                   override fun onResponse(
                       call: Call<StudentList>,
                       response: Response<StudentList>
                   ) {
                       students = response.body()!!.alunos
                       Log.i("Load Students By Cursando", "result: $students")

                   }

                   override fun onFailure(call: Call<StudentList>, t: Throwable) {
                       Log.i("Load Students By Cursando", "onFailure: Error get students from courses ${t.message}")
                   }
               })

           }) {
               LionsWhite(text = "Cursando")
           }

           Button(onClick = {
               val call = RetrofitFactory().getStudentService().getStudentsFromCourse(sigla, "Finalizado")

               call.enqueue(object : Callback<StudentList> {
                   override fun onResponse(
                       call: Call<StudentList>,
                       response: Response<StudentList>
                   ) {
                       students = response.body()!!.alunos
                       Log.i("Load Students By Finalizado", "result: $students")

                   }

                   override fun onFailure(call: Call<StudentList>, t: Throwable) {
                       Log.i("Load Students By Finalizado", "onFailure: Error get students from courses ${t.message}")
                   }
               })
           }) {
               LionsWhite(text = "Finalizado")
           }
       }
        LazyColumn(){
            items(students){
                Card(modifier = Modifier
                    .size(126.dp)
                    .background(BlueLions)
                    .clickable {
                        val intent = Intent(context, StudentActivity::class.java)
                        intent.putExtra("matricula",it.matricula)
                        context.startActivity(intent)
                        Log.i("Student", "matricula : ${it.matricula}")
                    }) {
                    Column() {
                        Text(text = it.matricula.toString())
                        Text(text = it.nome)
                    }
                }
            }

        }

   }

}
fun getStudentsFromCourse(sigla : String, status : String?) : List<Student> {

    val call = RetrofitFactory().getStudentService().getStudentsFromCourse(sigla, status)
    var students = listOf<Student>()

    call.enqueue(object : Callback<StudentList> {

        override fun onResponse(
            call: Call<StudentList>,
            response: Response<StudentList>
        ) {
            students = response.body()!!.alunos

            Log.i("UseCase StudentsFromCourse", ": $students")

        }

        override fun onFailure(call: Call<StudentList>, t: Throwable) {
            Log.i("UseCase StudentsFromCourse", "onFailure: error call students from course list ${t.message}")
        }
    })
    Log.i("UseCase StudentsFromCourse", "Students: $students")
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