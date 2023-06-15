package br.senai.sp.jandira.lionsschool

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionsschool.model.Discipline
import br.senai.sp.jandira.lionsschool.model.Student
import br.senai.sp.jandira.lionsschool.service.RetrofitFactory
import br.senai.sp.jandira.lionsschool.ui.theme.LionsSchoolTheme
import retrofit2.Call
import retrofit2.Response

class StudentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val matricula = intent.getLongExtra("matricula", 0)
        Log.i("MatriculaOnStudent", "onCreate: $matricula")



        setContent {
            LionsSchoolTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var student by remember {
                        mutableStateOf<Student?>(null)
                    }
                    val call = RetrofitFactory().getStudentService().getStudentByMatricula(matricula)
                    call.enqueue(object : retrofit2.Callback<Student> {
                        override fun onResponse(call: Call<Student>, response: Response<Student>) {
                            student = Student(
                                id = response.body()!!.id,
                                foto = response.body()!!.foto,
                                nome = response.body()!!.nome,
                                matricula = response.body()!!.matricula,
                                sexo = response.body()!!.sexo,
                                curso = response.body()!!.curso,
                                status = response.body()!!.status
                            )
                            Log.i("StudentByMatricula", "Created Student: $student ")

                        }

                        override fun onFailure(call: Call<Student>, t: Throwable) {
                            Log.i("StudentByMatriculaError", "onFailure: Error Request ${t.message}")

                        }
                    })
                    student?.let { StudentScreen(student = it) }
                }
            }
        }
    }
}

@Composable
fun StudentScreen(student: Student) {

    var disciplinesFromCourse by remember {
        mutableStateOf(listOf<Discipline>())
    }

    Column() {
        Text(text = "Hello ${student.nome}!")
        Text(text = "This is your matricula: ${student.matricula}!")
        Text(text = "Here is your photo: ${student.foto}!")
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(24.dp))

        Text(text = "Cursos:")
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(24.dp))


        LazyColumn(){
           items(student.curso){
               Column() {
                   Text(text = "Nome: ${it.nome}")
                   Text(text = "Sigla: ${it.sigla}")
                   Text(text = "Icone: ${it.icone}")
                   Text(text = "Conclusão: ${it.conclusao}")
                   Text(text = "Carga: ${it.carga}")
                   Spacer(modifier = Modifier
                       .fillMaxWidth()
                       .height(24.dp))
                   disciplinesFromCourse = it.disciplinas
               }
           }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(24.dp))
        Text(text = "Disciplinas: ")
        LazyColumn(){
            items(disciplinesFromCourse){

                Text(text = "${it.nome}"
                , fontSize = 24.sp)
                Text(text = "Carga: ${it.carga}")
                Text(text = "Média: ${it.media}")
                Text(text = "Status: ${it.status}")
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp))


            }
        }
    }
//               LazyColumn(){
//                   items(it.disciplinas){
//                       Column() {
//                           Text(text = it.nome)
//                           Text(text = it.carga.toString())
//                           Text(text = it.status)
//                           Text(text = it.media.toString())
//                       }
//
//                   }
//               }
//           }
//        }


    }



