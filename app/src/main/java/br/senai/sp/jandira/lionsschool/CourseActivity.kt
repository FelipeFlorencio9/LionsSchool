package br.senai.sp.jandira.lionsschool


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
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

    var courses by remember {
        mutableStateOf(listOf<Course>())
    }

    val call = RetrofitFactory().getCourseService().getCourses()
    call.enqueue(object : Callback<CourseList>{
        override fun onResponse(call: Call<CourseList>, response: Response<CourseList>) {
            courses = response.body()!!.cursos
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
                horizontal = 36.dp, vertical = 40.dp
            )
    ) {

        Profile(enterprise = "Lions School")

        Column(
            modifier = Modifier.padding(top = 40.dp),
        ) {
            LionsYellow(text = "Choose")
            LionsYellow(text = "Your")
            LionsYellow(text = "Course")
        }

        LionsWhite(
            text = "Click to learn more information's"
        )

        LazyRow {
            items(courses) {
                CourseCard(
                    sigla = it.sigla,
                    dataConclusao = 2023,
                    nomeCurso = it.nome,
                    quantidadeAlunos = 22)
            }
        }
    }

}
fun getCourses() : List<Course> {

    val call = RetrofitFactory().getCourseService().getCourses()
    var courses = listOf<Course>()

    call.enqueue(object : Callback<CourseList> {
        override fun onResponse(call: Call<CourseList>, response: Response<CourseList>) {
            courses = response.body()!!.cursos
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