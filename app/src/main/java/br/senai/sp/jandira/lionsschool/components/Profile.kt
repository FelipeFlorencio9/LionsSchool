package br.senai.sp.jandira.lionsschool.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionsschool.R
import br.senai.sp.jandira.lionsschool.ui.theme.JuaRegular


@Composable
fun Profile(enterprise : String){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = Modifier
                .padding(end = 24.dp)
                .size(width = 60.dp, height = 75.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo")
        Text(
            text = "$enterprise",
            fontFamily = JuaRegular,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
   
}

@Preview
@Composable
fun ProfilePreview() {
    Profile("Lions School")
}