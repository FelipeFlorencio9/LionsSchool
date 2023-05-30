package br.senai.sp.jandira.lionsschool.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionsschool.ui.theme.BlueCard
import br.senai.sp.jandira.lionsschool.ui.theme.JuaRegular

@Preview
@Composable
fun CourseCardPreview() {
    CourseCard(
        "DS",
        2023,
        "Desenvolvimento de Sistemas",
        20

    )
}

@Composable
fun CourseCard(
    sigla: String,
    dataConclusao: Int?,
    nomeCurso: String,
    quantidadeAlunos: Int
) {
    Card(
        modifier = Modifier
            .background(Color.Black, CircleShape)
            .size(width = 220.dp, height = 300.dp),
        shape = RoundedCornerShape(36.dp),

        backgroundColor = BlueCard
    ){
        Column (
            modifier = Modifier.padding(16.dp))
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = sigla,
                    color = Color.White,
                    fontFamily = JuaRegular,
                    fontSize = 24.sp
                )
                Text(
                    text = dataConclusao.toString(),
                    color = Color.White,
                    fontFamily = JuaRegular,
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
            )
            Text(
                text = nomeCurso,
                color = Color.White,
                fontFamily = JuaRegular,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "$quantidadeAlunos Alunos",
                color = Color.White,
                fontFamily = JuaRegular,
                fontSize = 20.sp
            )
        }

    }
}