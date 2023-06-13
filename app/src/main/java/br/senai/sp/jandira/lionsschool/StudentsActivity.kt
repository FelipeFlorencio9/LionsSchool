package br.senai.sp.jandira.lionsschool

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.lionsschool.components.CardInfo
import br.senai.sp.jandira.lionsschool.components.Profile
import br.senai.sp.jandira.lionsschool.model.Course
import br.senai.sp.jandira.lionsschool.service.RetrofitFactory
import br.senai.sp.jandira.lionsschool.ui.theme.BlueLions
import br.senai.sp.jandira.lionsschool.ui.theme.LionsSchoolTheme

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
       Chip(onClick = { Log.i("teste", "StudentsFromCourse: teste")}) {
           Text(text = "Hello")
       }

   }

}
fun getStudentsFromCourse(sigla : String) {
    
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