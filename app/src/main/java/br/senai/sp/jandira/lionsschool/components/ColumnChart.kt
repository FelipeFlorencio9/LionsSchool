package br.senai.sp.jandira.lionsschool.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.lionsschool.views.getDisciplineItemColor
import br.senai.sp.jandira.lionsschool.model.Discipline
import br.senai.sp.jandira.lionsschool.repository.StudentsRepository
import br.senai.sp.jandira.lionsschool.views.setAbbreviationByDisciplineName


@Composable
fun ColumnChart( disciplinesNotes : List<Discipline>) {
    var disciplinesContext by remember {
        mutableStateOf(disciplinesNotes)
    }
    LazyRow(){
        items(disciplinesContext){
           ItemColumn(
               notaAluno = it.media,
               siglaDisciplina = setAbbreviationByDisciplineName(it.nome)
           )
            Spacer(modifier = Modifier
                .fillMaxHeight()
                .width(24.dp))
        }
    }
}


@Composable
fun ItemColumn(notaAluno: Int, siglaDisciplina: String){
    //Para pegar o valor em fraçao e ser usado no componente de alturra
    val porcentagemNota = (notaAluno * 100f) / 100f / 100f

    Column (
       Modifier.width(40.dp),
       horizontalAlignment = Alignment.CenterHorizontally
    ){
       LionsWhite(text = "${(porcentagemNota * 100).toInt()}%")
       Spacer(modifier = Modifier
           .fillMaxWidth()
           .height(8.dp))
       Column(modifier = Modifier
           .fillMaxWidth()
           .height(154.dp),
       verticalArrangement = Arrangement.Bottom) {
           Card(modifier = Modifier
               .fillMaxWidth()
               .fillMaxHeight(porcentagemNota),
               backgroundColor = getDisciplineItemColor((porcentagemNota * 100).toInt())
           ) {}
       }
       Spacer(modifier = Modifier
           .fillMaxWidth()
           .height(8.dp))
       LionsWhite(text = siglaDisciplina)
   }
}
@Preview
@Composable
fun ColumnChartPreview() {
    ColumnChart(
        StudentsRepository.getDisciplines()
    )


}