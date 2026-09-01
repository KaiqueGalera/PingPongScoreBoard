package br.edu.ifsp.scl.sc3046699.pingpongscoreboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.sc3046699.pingpongscoreboard.ui.theme.PingPongScoreBoardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PingPongScoreBoardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(innerPadding)
                }
            }
        }
    }
}

@Composable
fun MainScreen(innerPadding: PaddingValues){
    var scoreA by remember { mutableStateOf(0) }
    var scoreB by remember { mutableStateOf(0) }

    PingPongScoreBoard(scoreA = scoreA, scoreB = scoreB, { scoreA ++}, {scoreB ++}, {scoreA = 0; scoreB = 0},
        modifier = Modifier.padding(innerPadding))
}

@Composable
fun PingPongScoreBoard(scoreA: Int, scoreB: Int, incrementA: ()-> Unit, incrementB: ()-> Unit , reset: ()-> Unit, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Row(horizontalArrangement = Arrangement.spacedBy(45.dp)) {
            PlayerHud("Jogador A", scoreA, incrementA)
            PlayerHud("Jogador B", scoreB, incrementB)
        }
        Button(onClick = reset) {
            Text("Reiniciar partida")
        }

    }
}

@Composable
fun PlayerHud(name: String, score:Int, onIncrement: ()-> Unit){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = name, fontSize = 30.sp)
        Text(text = "$score", fontSize = 40.sp)
        Button(onClick = onIncrement) {
            Text("+1", fontSize = 20.sp)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PingPongScoreBoardPreview() {
    PingPongScoreBoardTheme {
        PingPongScoreBoard(6, 1, { }, {}, { })
    }
}