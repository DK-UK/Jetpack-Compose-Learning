package com.enjay.jetpacktutorial.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enjay.jetpacktutorial.ui.theme.ui.theme.JetpackTutorialTheme

class Row_Column : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTutorialTheme {
                // A surface container using the 'background' color from the theme

                DefaultPreview()
            }
        }
    }
}

@Composable
fun Greeting() {
    val list : MutableList<String> = mutableListOf("Parthi", "Asmita", "Dhaval", "suraj")
    Column() {
        for(name in list){
            showText(name = name)
        }
    }
}

@Composable
fun showText(name : String){

    var expanded = remember { mutableStateOf(false)}

    var extraPadding = if(expanded.value) 40.dp else 0.dp

    androidx.compose.material.Surface(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colors.primary
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(20.dp)
        ) {

            Column(
                modifier = Modifier
                    .padding(bottom = extraPadding)
                    .weight(1f)
            ) {

                Text(
                    modifier = Modifier
                        .padding(5.dp)
                        ,
                    text = "Hello $name",
                )
            }
            Spacer(Modifier.width(5.dp))

            OutlinedButton(onClick = { expanded.value = !expanded.value },) {
                Text(text = if (expanded.value) "Show Less" else "Show More")
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
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    JetpackTutorialTheme {
        var shouldShowOnBoarding by remember { mutableStateOf(true) }

        if (shouldShowOnBoarding){
            onBoardingScreen(onClickedContinue = { shouldShowOnBoarding = false})
        }
        else {
            Greeting()
        }
    }
}