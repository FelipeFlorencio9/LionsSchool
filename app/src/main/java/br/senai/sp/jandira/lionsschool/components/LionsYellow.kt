package br.senai.sp.jandira.lionsschool.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionsschool.ui.theme.JuaRegular
import br.senai.sp.jandira.lionsschool.ui.theme.YellowLions


@Composable
fun LionsYellow(text: String) {
    Text(
        text = text,
        color = YellowLions,
        fontFamily = JuaRegular,
        fontSize = 60.sp
    )
}

@Composable
fun LionsWhite(text: String) {
        Text(
            text = text,
            color = Color.White,
            fontFamily = JuaRegular,
            fontSize = 16.sp
        )
}


@Composable
fun ItemCard(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontFamily = JuaRegular,
        fontSize = 24.sp
    )
}
@Composable
fun LionsText(text: String) {
    Text(
        text = text
    )
}

@Preview
@Composable
fun LionsYellowPreview() {
    LionsYellow(text = "School")
}
