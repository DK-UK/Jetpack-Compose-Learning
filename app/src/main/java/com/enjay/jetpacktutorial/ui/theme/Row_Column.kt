package com.enjay.jetpacktutorial.ui.theme

import android.animation.Keyframe
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enjay.jetpacktutorial.R
import com.enjay.jetpacktutorial.ui.theme.ui.theme.JetpackTutorialTheme
import kotlin.math.exp
import kotlin.math.log

class Row_Column : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTutorialTheme {
//                DefaultPreview()
            }
        }
    }
}

@Composable
fun Greeting(names : List<String> = List(1000){"$it"}) {
    LazyColumn(
        modifier = Modifier.padding(all = 4.dp)
    ) {
       items(items = names)
       {name ->
           showText(name = name)
       }
    }
}

@Composable
fun showText(name : String){

    var expanded = remember { mutableStateOf(false)}


   /* val colorState by animateColorAsState(
        if (expanded.value) Color.Cyan else Color.LightGray,
        spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness =  Spring.StiffnessLow
        )
    )*/


    androidx.compose.material.Surface(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
//        color = colorState

    ) {

        Row(

            modifier = Modifier
                .padding(20.dp)
                .animateContentSize(
                    spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {

                Text(
                    text = "Hello $name",
                    modifier = Modifier.padding(all = 10.dp)
                )

                if (expanded.value) {
                    Text(
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable {
                                       Log.e("Dhaval", "clicked")
                            },
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy").repeat(4)
                        )
                }
            }

            Spacer(Modifier.width(5.dp))

            IconButton(onClick = { expanded.value =  !expanded.value}) {
                Icon(
                    imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded.value) stringResource(id = R.string.show_less) else stringResource(
                        id = R.string.show_more)
                    )
            }
        }
    }
}

@Composable
fun onBoardingScreen(onClickedContinue : () -> Unit){

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the Basic Codelab!")
            Button(onClick = onClickedContinue,
            modifier = Modifier.padding(10.dp)) {
                Text(text = "Continue")
            }
        }
    }    
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun DefaultPreview() {
    JetpackTutorialTheme {
        var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }

        if (shouldShowOnBoarding){
            onBoardingScreen(onClickedContinue = { shouldShowOnBoarding = false})
        }
        else {
            Greeting()
        }
    }
}