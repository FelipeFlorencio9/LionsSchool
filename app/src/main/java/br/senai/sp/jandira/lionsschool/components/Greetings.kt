package br.senai.sp.jandira.lionsschool.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionsschool.ui.theme.JuaRegular
import br.senai.sp.jandira.lionsschool.ui.theme.YellowLions

@Composable
fun Greetings(
    phrase : String,
    majorContent : String
) {
    Text(
        text = phrase,
        color = Color.White,
        fontFamily = JuaRegular,
        fontSize = 24.sp
    )
    Text(
        text = majorContent,
        color = YellowLions,
        fontFamily = JuaRegular,
        fontSize = 48.sp
    )
}