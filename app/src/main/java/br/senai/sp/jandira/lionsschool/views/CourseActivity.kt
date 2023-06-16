package br.senai.sp.jandira.lionsschool.views


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.lionsschool.components.CourseCard
import br.senai.sp.jandira.lionsschool.components.LionsWhite
import br.senai.sp.jandira.lionsschool.components.LionsYellow
import br.senai.sp.jandira.lionsschool.components.Profile
import br.senai.sp.jandira.lionsschool.model.Course
import br.senai.sp.jandira.lionsschool.model.CourseList
import br.senai.sp.jandira.lionsschool.service.RetrofitFactory
import br.senai.sp.jandira.lionsschool.ui.theme.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val courses = getCourses()
        Log.i("ds2m", "onCreateInCourseActivity: $courses")
        setContent {
            LionsSchoolTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   CourseScreen()

                }
            }
        }
    }
}

@Composable
fun CourseScreen () {
    var context = LocalContext.current
    var courses by remember {
        mutableStateOf(listOf<Course>())
    }

    val call = RetrofitFactory().getCourseService().getCourses()
    call.enqueue(object : Callback<CourseList>{
        override fun onResponse(call: Call<CourseList>, response: Response<CourseList>) {
            var coursesBody = response.body()!!.cursos
            for (course in coursesBody){
                var nomeCurso = course.nome.split(" - ")[1];
                course.nome = nomeCurso
            }
            courses = coursesBody

        }

        override fun onFailure(call: Call<CourseList>, t: Throwable) {
            Log.i("ds2m", "onFailure: algo deu errado")
        }

    })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueLions)
            .padding(
                start = 36.dp, end = 36.dp, top = 40.dp
            )
    ) {

        Profile(enterprise = "Lions School")

        Column(
            modifier = Modifier.padding(top = 40.dp),
        ) {
            LionsYellow(text = "Choose")
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(12.dp))
            LionsYellow(text = "Your")
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(12.dp))
            LionsYellow(text = "Course")
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(28.dp))

        LionsWhite(
            text = "Click to learn more information's"
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp))

        LazyRow (){
            items(courses) {
                CourseCard(
                    sigla = it.sigla,
                    dataConclusao = 2023,
                    nomeCurso = it.nome,
                    quantidadeAlunos = 22){

                    Log.i("Id na montagem do card", "${it.id}")
                    val intent = Intent(context, StudentsActivity::class.java)
                    intent.putExtra("id",it.id.toInt())
                    intent.putExtra("sigla", it.sigla)
                    intent.putExtra("nome",  it.nome)
                    intent.putExtra("carga", it.carga)

                    context.startActivity(intent)
                }
                Spacer(modifier = Modifier
                    .fillMaxHeight()
                    .width(24.dp)
                )
            }
        }
    }

}
fun getCourses() : List<Course> {

    val call = RetrofitFactory().getCourseService().getCourses()
    var courses = listOf<Course>()

    call.enqueue(object : Callback<CourseList> {
        override fun onResponse(call: Call<CourseList>, response: Response<CourseList>) {
            var coursesBody = response.body()!!.cursos
            for (course in coursesBody){
                var nomeCurso = course.nome.split(" - ")[1]
                course.nome = nomeCurso
                Log.i(
                    "test", "onResponse: $nomeCurso")
            }
            courses = coursesBody
            Log.i("set Courses for Compose", "onResponse: $courses")
            Log.i("ds2m", "onResponse: ${response.body()!!.cursos}")

        }

        override fun onFailure(call: Call<CourseList>, t: Throwable) {
            Log.i(
                "ds2m",
                "onFailure ${t.message}"
            )
        }
    })

    return courses
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LionsSchoolTheme {
        CourseScreen()
    }
}