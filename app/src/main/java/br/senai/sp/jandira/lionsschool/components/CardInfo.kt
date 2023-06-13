package br.senai.sp.jandira.lionsschool.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.lionsschool.ui.theme.BlueCard


@Preview
@Composable
fun CardInfoPreview() {
    CardInfo(sigla = "DS", nome = "Desenvolvimento de Sistemas", carga = 1200)
}
@Composable
fun CardInfo(
    sigla : String,
    nome : String,
    carga : Int
) {
    Card(
        modifier = Modifier
            .background(Color.Black, CircleShape)
            .fillMaxWidth()
            .height(192.dp),
        shape = RoundedCornerShape(36.dp),
        backgroundColor = BlueCard
    ){
        Column (
            modifier = Modifier.padding(
                horizontal = 22.dp,
                vertical = 56.dp
            ).fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
                LionsWhite(text = "Sigla : $sigla")
                LionsWhite(text = "Nome : $nome")
                LionsWhite(text = "Carga : ${carga}H")
            }
        }

}
