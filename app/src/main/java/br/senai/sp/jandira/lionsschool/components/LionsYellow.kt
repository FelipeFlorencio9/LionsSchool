package br.senai.sp.jandira.lionsschool.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionsschool.ui.theme.JuaRegular
import br.senai.sp.jandira.lionsschool.ui.theme.YellowLions


@Composable
fun LionsYellow(text: String) {
    Text(
        modifier = Modifier,
        text = text,
        color = YellowLions,
        fontFamily = JuaRegular,
        fontSize = 40.sp
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
fun SectionTitle(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontFamily = JuaRegular,
        fontSize = 20.sp
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
    Column( verticalArrangement = Arrangement.Center) {
        LionsWhite(text = "Lions")
        LionsYellow(text = "School")
        SectionTitle(text = "Section Title")
    }

}
