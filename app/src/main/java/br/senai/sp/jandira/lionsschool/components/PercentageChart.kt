package br.senai.sp.jandira.lionsschool.components

import android.graphics.drawable.shapes.Shape
import android.widget.GridView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.lionsschool.ui.theme.Approved
import br.senai.sp.jandira.lionsschool.ui.theme.BlueLions
import br.senai.sp.jandira.lionsschool.ui.theme.Disapproved
import br.senai.sp.jandira.lionsschool.ui.theme.InExam


@Composable
fun PercentageChart() {
    Column() {
        Text(text = "Percentage Chart")
    }
}

@Preview
@Composable
fun PercentageChartPreview() {
    Box(
        modifier = Modifier
            .size(width = 296.dp, height = 264.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(BlueLions)

    ){
        Card(
            modifier = Modifier
                .size(140.dp)
                .absoluteOffset(36.dp, 36.dp),
            backgroundColor = Color.Cyan
        ) {
            Box(modifier = Modifier
            .size(100.dp)
            .offset(40.dp, 40.dp)){
            Card(modifier = Modifier
                .size(width = 88.dp, 80.dp)
                .clip(RoundedCornerShape(100)),
                backgroundColor = Disapproved
            ){}
            }
            Box(modifier = Modifier
                .size(100.dp)
                .offset(8.dp, 30.dp)){
                Card(modifier = Modifier
                    .size(width = 88.dp, 80.dp)
                    .clip(RoundedCornerShape(100)),
                    backgroundColor = InExam
                ){}
            }
            Box(modifier = Modifier
                .size(100.dp)
                .offset(36.dp, 8.dp)){
                Card(modifier = Modifier
                    .size(width = 88.dp, 80.dp)
                    .clip(RoundedCornerShape(100)),
                    backgroundColor = Approved
                ){
                    Card(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(12.dp),
                        backgroundColor = Color.DarkGray
                    ){

                    }
                }
            }


        }
    }
}