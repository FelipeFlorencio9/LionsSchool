package br.senai.sp.jandira.lionsschool.components

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


@Composable
fun StudentCardInfo(
    matricula : String,
    nomeAluno : String
) {
    Card(
        modifier = Modifier
            .background(Color.Black, CircleShape)
            .width(300.dp)
            .height(192.dp),
        shape = RoundedCornerShape(36.dp),
        backgroundColor = BlueCard
    ){
        Column(
            modifier = Modifier.padding(
                horizontal = 24.dp,
                vertical = 16.dp
            ).fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = matricula,
                color = Color.White,
                fontFamily = JuaRegular,
                fontSize = 20.sp
            )
            Text(
                text = nomeAluno,
                color = Color.White,
                fontFamily = JuaRegular,
                fontSize = 20.sp
            )
        }

    }
    
}

@Preview
@Composable
fun StudendCardInfoPreviw() {
    StudentCardInfo(matricula = "12345612", nomeAluno = "Giovana")
}


