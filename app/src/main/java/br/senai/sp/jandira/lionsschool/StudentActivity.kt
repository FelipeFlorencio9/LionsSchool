package br.senai.sp.jandira.lionsschool

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionsschool.components.CardInfo
import br.senai.sp.jandira.lionsschool.components.LionsWhite
import br.senai.sp.jandira.lionsschool.components.Profile
import br.senai.sp.jandira.lionsschool.components.StudentCardInfo
import br.senai.sp.jandira.lionsschool.model.Discipline
import br.senai.sp.jandira.lionsschool.model.Student
import br.senai.sp.jandira.lionsschool.repository.StudentsRepository
import br.senai.sp.jandira.lionsschool.service.RetrofitFactory
import br.senai.sp.jandira.lionsschool.ui.theme.BlueLions
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueLions)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 33.dp, top = 40.dp)
        ) {
            Profile(enterprise = stringResource(id = R.string.enterprise_name))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(33.dp)
            )
            Column (){
                StudentCardInfo(
                    matricula = "${student.matricula}",
                    nomeAluno = student.nome
                )
            }
        }

    }
}


@Preview
@Composable
fun StudentScreenPreview() {
    StudentScreen(StudentsRepository.getStudent())
}