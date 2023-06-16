package br.senai.sp.jandira.lionsschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionsschool.model.Student
import br.senai.sp.jandira.lionsschool.model.StudentList
import br.senai.sp.jandira.lionsschool.service.RetrofitFactory
import br.senai.sp.jandira.lionsschool.ui.theme.JuaRegular
import br.senai.sp.jandira.lionsschool.ui.theme.LionsSchoolTheme
import br.senai.sp.jandira.lionsschool.ui.theme.YellowLions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import br.senai.sp.jandira.lionsschool.components.Greetings
import br.senai.sp.jandira.lionsschool.model.Course
import br.senai.sp.jandira.lionsschool.model.CourseList
import br.senai.sp.jandira.lionsschool.ui.theme.BlueLions
import br.senai.sp.jandira.lionsschool.views.CourseActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionsSchoolTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   HomeScreen(
                       logo = painterResource(id = R.drawable.logo), 
                       logoDescription = stringResource(id = R.string.logo_description),
                       greetings = stringResource(id = R.string.greetings),
                       enterpriseName = stringResource(id = R.string.enterprise_name),
                       description = stringResource(id = R.string.app_description)
                   )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    logo : Painter,
    logoDescription : String,
    greetings : String,
    enterpriseName : String,
    description : String,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.lions_blue)),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            modifier = Modifier
                .size(width = 80.dp, height = 280.dp),
            painter = logo,
            contentDescription = logoDescription
        )

        Greetings(
            greetings,
            enterpriseName
        )

        Text(
            modifier = Modifier
                .padding(
                    horizontal = 32.dp,
                    vertical = 80.dp
                ),
            text = description,
            color = Color.White,
            fontFamily = JuaRegular,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Button(
            shape = RoundedCornerShape(36.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = YellowLions
            ),
            onClick = {
                val intent = Intent(context, CourseActivity::class.java)
                context.startActivity(intent)
            }
        ) {
            Text(
                text = stringResource(id = R.string.app_init),
                fontFamily = JuaRegular,
                fontSize = 24.sp,
                color = BlueLions
            )

        }


    }

}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LionsSchoolTheme {
        HomeScreen(
            logo = painterResource(id = R.drawable.logo),
            logoDescription = stringResource(id = R.string.logo_description),
            greetings = stringResource(id = R.string.greetings),
            enterpriseName = stringResource(id = R.string.enterprise_name),
            description = stringResource(id = R.string.app_description)
        )
    }
}