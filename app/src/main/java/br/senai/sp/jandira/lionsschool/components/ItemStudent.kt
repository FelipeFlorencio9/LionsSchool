package br.senai.sp.jandira.lionsschool.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.lionsschool.ui.theme.BlueLions





@Composable
fun ItemStudent(
    nome : String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = BlueLions,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .size(48.dp)
            ) {
                Image(
                    painter = painterResource(id = br.senai.sp.jandira.lionsschool.R.drawable.ic_launcher_background),
                    contentDescription = "teste"
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(13.dp)
            )
            Column() {
                LionsWhite(text = nome)
                Row() {
                    Text(text = "Maior Nota | Menor Nota")
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                horizontalAlignment = Alignment.End
            ) {
                LionsWhite(text = "20%")
            }
        }
    }
}

    @Preview
    @Composable
    fun ItemStudentPreview() {
        Column() {
            ItemStudent( "José")
        }

    }