package br.senai.sp.jandira.lionsschool

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
       verticalArrangement = Arrangement.SpaceBetween
   ) {
       Profile(
           enterpriseName,

       )
       CardInfo(
           sigla,
           nome,
           carga
       )
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