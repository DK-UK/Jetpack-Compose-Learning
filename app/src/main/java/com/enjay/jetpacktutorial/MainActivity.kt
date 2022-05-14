package com.enjay.jetpacktutorial

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.enjay.jetpacktutorial.ui.theme.JetpackTutorialTheme
import com.enjay.jetpacktutorial.ui.theme.Row_Column
import com.enjay.jetpacktutorial.ui.theme.Shapes
import java.util.regex.Pattern

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            greet()
            startActivity(Intent(MainActivity@this, Row_Column::class.java))
        }
    }

    @Preview(name = "Light Mode")
    @Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode")
    @Composable
   fun greet(){
        val list : MutableList<String> = mutableListOf()
        for(i in 1..50){
            list.add("this is a statement\n you're seeing full paragraph at index $i")
        }
        LazyColumn {
            items(list){msg->

                morningGreet(greet = msg)
            }
        }
    }
    
    @Composable
    fun morningGreet(greet : String) {
        JetpackTutorialTheme {

            var isExpanded by remember {
                mutableStateOf(false)
            }

            Column {

                Row(
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .clickable { isExpanded = !isExpanded },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Contact profile picture",
                        Modifier
                            .clip(CircleShape)
                            .size(40.dp)
                            .border(1.dp, color = MaterialTheme.colors.primary, shape = CircleShape)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    val surfaceColor by animateColorAsState(
                        if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
                    )
                    androidx.compose.material.Surface(
                        shape = MaterialTheme.shapes.medium, elevation = 2.dp,
                        modifier = Modifier.padding(10.dp),
                        color = surfaceColor
                    ) {
                        Text(
                            text = "$greet Dhaval",
                            modifier = Modifier.padding(5.dp),
                            color = MaterialTheme.colors.secondary,
                            style = MaterialTheme.typography.h5,
                            maxLines = if (isExpanded) Int.MAX_VALUE else 1
                        )

                    }
                }
            }
        }
    }
}
