package br.senai.sp.jandira.lionsschool.views


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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionsschool.R
import br.senai.sp.jandira.lionsschool.components.CardInfo
import br.senai.sp.jandira.lionsschool.components.LionsWhite
import br.senai.sp.jandira.lionsschool.components.Profile
import br.senai.sp.jandira.lionsschool.model.Discipline
import br.senai.sp.jandira.lionsschool.model.Student
import br.senai.sp.jandira.lionsschool.model.StudentList
import br.senai.sp.jandira.lionsschool.repository.StudentsRepository
import br.senai.sp.jandira.lionsschool.service.RetrofitFactory
import br.senai.sp.jandira.lionsschool.ui.theme.*
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt

class StudentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getIntExtra("id", 0)
        val sigla = intent.getStringExtra("sigla") ?: "Sigla Default"
        val nome = intent.getStringExtra("nome") ?: "Nome Default"
        val carga = intent.getIntExtra("carga", 1234)

        var allStudents = listOf<Student>()
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
                        carga,
                        allStudents
                    )
                }
            }
        }
    }
}


fun getDisciplinesAverage(student: Student): Double {
    var cursoAluno = student.curso[0]
    var notas = mutableListOf<Int>()
    cursoAluno.disciplinas.forEach { discipline ->
        notas.add(discipline.media)
    }
    var mediaDasDisciplinas = notas.average()
    var valorNotaMaxima = 100
    var porcentagemDaMediaDasDisciplinas = (mediaDasDisciplinas * valorNotaMaxima) / 100

    Log.i(
        "Media",
        "StudentsFromCourse: ${student.nome} " + "Media : $porcentagemDaMediaDasDisciplinas%"
    )

    return porcentagemDaMediaDasDisciplinas
}

fun getDisciplinesMaximum(student: Student): Discipline? {
    var curso = student.curso[0]
    return curso.disciplinas.maxByOrNull { it.media }
}

fun getDisciplinesMinimum(student: Student): Discipline? {
    var curso = student.curso[0]
    return curso.disciplinas.minByOrNull { it.media }
}

fun getDisciplineItemColor(disciplineNote: Int): Color {
    var decisiveNote = 80
    var examNote = 60

    return if (disciplineNote >= decisiveNote) {
        Approved
    } else if (disciplineNote in examNote until decisiveNote) {
        return InExam
    } else {
        Disapproved
    }
}

@Composable
fun StudentsFromCourse(
    enterpriseName: String,
    sigla: String,
    nome: String,
    carga: Int,
    studentsList: List<Student>
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
            Log.i("Load All Students", "result: $students")
        }

        override fun onFailure(call: Call<StudentList>, t: Throwable) {
            Log.i(
                "Load All Students",
                "onFailure: Error get students from courses ${t.message}"
            )
        }
    })

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
            Profile(enterprise = enterpriseName)

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )
            CardInfo(sigla = sigla, nome = enterpriseName, carga = 1200)

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )

            Row {
                Button(
                    shape = RoundedCornerShape(36.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = YellowLions
                    ),
                    onClick = {
                        val call =
                            RetrofitFactory().getStudentService().getStudentsFromCourse(sigla, null)

                        call.enqueue(object : Callback<StudentList> {
                            override fun onResponse(
                                call: Call<StudentList>,
                                response: Response<StudentList>
                            ) {
                                students = response.body()!!.alunos
                                Log.i("Load All Students", "result: $students")

                            }

                            override fun onFailure(call: Call<StudentList>, t: Throwable) {
                                Log.i(
                                    "Load All Students",
                                    "onFailure: Error get students from courses ${t.message}"
                                )
                            }
                        })
                    }
                ) {
                    Text(
                        text = "All",
                        fontFamily = JuaRegular,
                        fontSize = 24.sp,
                        color = BlueLions
                    )

                }
                Spacer(
                    modifier = Modifier
                        .width(14.dp)
                )
                Button(
                    shape = RoundedCornerShape(36.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = YellowLions
                    ),
                    onClick = {
                        val call = RetrofitFactory().getStudentService()
                            .getStudentsFromCourse(sigla, "Cursando")

                        call.enqueue(object : Callback<StudentList> {
                            override fun onResponse(
                                call: Call<StudentList>,
                                response: Response<StudentList>
                            ) {
                                students = response.body()!!.alunos
                                Log.i("Load All Students", "result: $students")

                            }

                            override fun onFailure(call: Call<StudentList>, t: Throwable) {
                                Log.i(
                                    "Load All Students",
                                    "onFailure: Error get students from courses ${t.message}"
                                )
                            }
                        })
                    }
                ) {
                    Text(
                        text = "Studing",
                        fontFamily = JuaRegular,
                        fontSize = 24.sp,
                        color = BlueLions
                    )

                }
                Spacer(
                    modifier = Modifier
                        .width(14.dp)
                )
                Button(
                    shape = RoundedCornerShape(36.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = YellowLions
                    ),
                    onClick = {
                        val call = RetrofitFactory().getStudentService()
                            .getStudentsFromCourse(sigla, "Finalizado")

                        call.enqueue(object : Callback<StudentList> {
                            override fun onResponse(
                                call: Call<StudentList>,
                                response: Response<StudentList>
                            ) {
                                students = response.body()!!.alunos
                                Log.i("Load All Students", "result: $students")

                            }

                            override fun onFailure(call: Call<StudentList>, t: Throwable) {
                                Log.i(
                                    "Load All Students",
                                    "onFailure: Error get students from courses ${t.message}"
                                )
                            }
                        })
                    }
                ) {
                    Text(
                        text = "Finished",
                        fontFamily = JuaRegular,
                        fontSize = 24.sp,
                        color = BlueLions
                    )
                }
            }
        }


        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            items(students) {
                var media = getDisciplinesAverage(it)
                var disciplinaComMaiorNota = getDisciplinesMaximum(it)
                var disciplinaComMenorNota = getDisciplinesMinimum(it)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val intent = Intent(context, StudentActivity::class.java)
                            intent.putExtra("matricula", it.matricula)
                            context.startActivity(intent)
                        },
                    backgroundColor = BlueLions,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Card(
                            shape = CircleShape,
                            modifier = Modifier
                                .size(48.dp)
                        ) {
//                            Image(
//                                painter = painterResource(id = R.drawable.ic_launcher_background),
//                                contentDescription = "teste" )
                            AsyncImage(
                                model = it.foto,
                                contentDescription = it.nome,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(13.dp)
                        )
                        Column {
                            LionsWhite(text = it.nome)
                            Row {
                                if (disciplinaComMaiorNota != null) {
                                    Text(
                                        text = disciplinaComMaiorNota.nome,
                                        color = getDisciplineItemColor(disciplinaComMaiorNota.media),
                                        fontSize = 8.sp
                                    )
                                }
                                Text(
                                    text = " | ",
                                    color = Color.White,
                                    fontSize = 8.sp
                                )
                                if (disciplinaComMenorNota != null) {
                                    Text(
                                        text = disciplinaComMenorNota.nome,
                                        color = getDisciplineItemColor(disciplinaComMenorNota.media),
                                        fontSize = 8.sp
                                    )
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(),
                            horizontalAlignment = Alignment.End
                        ) {
                            LionsWhite(text = "${media.roundToInt()}%")
                        }
                    }

                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )
            }
        }

    }

//    Column(
//       modifier = Modifier
//           .fillMaxSize()
//           .background(BlueLions)
////           .padding(top = 40.dp, start = 33.dp)
//    , verticalArrangement = Arrangement.SpaceAround)
//    {
//
//        Profile(enterpriseName)
//
//        Column(modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center)
//        {
//
//            Spacer(modifier = Modifier
//                .fillMaxWidth()
//                .height(33.dp))
//
//            CardInfo(
//               sigla,
//               nome,
//               carga
//           )
////           Spacer(modifier = Modifier
////               .fillMaxWidth()
////               .height(74.dp))
//            Text(text = "Apareca!")
////            Row() {
////               Button(
////                   shape = RoundedCornerShape(36.dp),
////                   colors = ButtonDefaults.buttonColors(
////                       backgroundColor = YellowLions
////                   ),
////                   onClick = {
////                       val call = RetrofitFactory().getStudentService().getStudentsFromCourse(sigla, null)
////
////                   call.enqueue(object : Callback<StudentList> {
////                       override fun onResponse(
////                           call: Call<StudentList>,
////                           response: Response<StudentList>
////                       ) {
////                           students = response.body()!!.alunos
////                           Log.i("Load All Students", "result: $students")
////
////                       }
////
////                       override fun onFailure(call: Call<StudentList>, t: Throwable) {
////                           Log.i("Load All Students", "onFailure: Error get students from courses ${t.message}")
////                       }
////                   })
////                   }
////               ) {
////                   Text(
////                       text = "All",
////                       fontFamily = JuaRegular,
////                       fontSize = 24.sp,
////                       color = BlueLions
////                   )
////
////               }
////               Spacer(modifier = Modifier
////                   .fillMaxHeight()
////                   .width(14.dp))
////               Button(
////                   shape = RoundedCornerShape(36.dp),
////                   colors = ButtonDefaults.buttonColors(
////                       backgroundColor = YellowLions
////                   ),
////                   onClick = { }
////               ) {
////                   Text(
////                       text = "Studing",
////                       fontFamily = JuaRegular,
////                       fontSize = 24.sp,
////                       color = BlueLions
////                   )
////
////               }
////               Spacer(modifier = Modifier
////                   .fillMaxHeight()
////                   .width(14.dp))
////               Button(
////                   shape = RoundedCornerShape(36.dp),
////                   colors = ButtonDefaults.buttonColors(
////                       backgroundColor = YellowLions
////                   ),
////                   onClick = { }
////               ) {
////                   Text(
////                       text = "Finished",
////                       fontFamily = JuaRegular,
////                       fontSize = 24.sp,
////                       color = BlueLions
////                   )
////               }
////           }
//
//       }
//        LazyColumn {
//            items(students) {
//                Text(text = it.nome)
//            }
//        }
//
//
////        Row {
////           Button(onClick = {
////               val call = RetrofitFactory().getStudentService().getStudentsFromCourse(sigla, null)
////
////               call.enqueue(object : Callback<StudentList> {
////                   override fun onResponse(
////                       call: Call<StudentList>,
////                       response: Response<StudentList> ////                   ) {
////                       students = response.body()!!.alunos
////                       Log.i("Load All Students", "result: $students")
////
////                   }
////
////                   override fun onFailure(call: Call<StudentList>, t: Throwable) {
////                       Log.i("Load All Students", "onFailure: Error get students from courses ${t.message}")
////                   }
////               })
////           }) {
////               LionsWhite(text = "Todos")
////           }
////           Button(onClick = {
////               val call = RetrofitFactory().getStudentService().getStudentsFromCourse(sigla, "Cursando")
////
////               call.enqueue(object : Callback<StudentList> {
////                   override fun onResponse(
////                       call: Call<StudentList>,
////                       response: Response<StudentList>
////                   ) {
////                       students = response.body()!!.alunos
////                       Log.i("Load Students By Cursando", "result: $students")
////
////                   }
////
////                   override fun onFailure(call: Call<StudentList>, t: Throwable) {
////                       Log.i("Load Students By Cursando", "onFailure: Error get students from courses ${t.message}")
////                   }
////               })
////
////           }) {
////               LionsWhite(text = "Cursando")
////           }
////
////           Button(onClick = {
////               val call = RetrofitFactory().getStudentService().getStudentsFromCourse(sigla, "Finalizado")
////
////               call.enqueue(object : Callback<StudentList> {
////                   override fun onResponse(
////                       call: Call<StudentList>,
////                       response: Response<StudentList>
////                   ) {
////                       students = response.body()!!.alunos
////                       Log.i("Load Students By Finalizado", "result: $students")
////
////                   }
////
////                   override fun onFailure(call: Call<StudentList>, t: Throwable) {
////                       Log.i("Load Students By Finalizado", "onFailure: Error get students from courses ${t.message}")
////                   }
////               })
////           }) {
////               LionsWhite(text = "Finalizado")
////           }
////       }
//
//
//   }

}


fun getStudentsFromCourse(sigla: String, status: String?): List<Student> {

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
            Log.i(
                "UseCase StudentsFromCourse",
                "onFailure: error call students from course list ${t.message}"
            )
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
            carga = 1200,
            studentsList = StudentsRepository.getStudents()
        )
    }
}