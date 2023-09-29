package com.example.mymoneycounter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymoneycounter.ui.theme.MyMoneyCounterTheme
import java.math.BigDecimal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMoneyCounterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}


@Composable
fun MyApp() {
    val tapCounter = remember {
        mutableStateOf(0)
    }
    val buttonText : String = if (tapCounter.value == 0){
        "Add $1 to your money"
    }else{
        "Add $${tapCounter.value*2-tapCounter.value} to your money"
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), color = Color(color = 0xFF546E7A)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "${tapCounter.value}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.size(20.dp))
            CreateCircle(tapCounter = tapCounter.value){newValue ->
                tapCounter.value = newValue
            }
            Text(text = buttonText , color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}


@Composable
fun CreateCircle(tapCounter: Int, updatedMoney: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(3.dp)
            .size(105.dp)
            .clickable {
                if (tapCounter == 0) {
                    updatedMoney(tapCounter + 1)
                } else {
                    updatedMoney(tapCounter * 2)
                }
            },
        shape = CircleShape,
        elevation = CardDefaults.cardElevation()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "+",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }

}

//@Preview
@Composable
fun GreetingPreview() {
    MyMoneyCounterTheme {
        MyApp()
    }
}