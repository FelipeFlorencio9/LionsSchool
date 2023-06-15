package br.senai.sp.jandira.lionsschool.components

import android.content.Intent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionsschool.CourseActivity
import br.senai.sp.jandira.lionsschool.R
import br.senai.sp.jandira.lionsschool.ui.theme.BlueLions
import br.senai.sp.jandira.lionsschool.ui.theme.JuaRegular
import br.senai.sp.jandira.lionsschool.ui.theme.YellowLions

@Composable
fun LionsButton(
    onClick : () -> Unit,
    name : String
) {
    Button(
        shape = RoundedCornerShape(36.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = YellowLions
        ),
        onClick = { onClick() }
    ) {
        Text(
            text = name,
            fontFamily = JuaRegular,
            fontSize = 24.sp,
            color = BlueLions
        )

    }
}