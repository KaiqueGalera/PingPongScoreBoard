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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import br.edu.ifsp.scl.sc3046699.pingpongscoreboard.ui.theme.PingPongScoreBoardTheme
import androidx.lifecycle.viewmodel.compose.viewModel
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PingPongScoreBoardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PingPongScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

class ScoreboardViewModel : ViewModel() {
    var scoreA by mutableStateOf(0)
    var scoreB by mutableStateOf(0)
    fun incrementA() { scoreA++ }
    fun incrementB() { scoreB++ }
    fun reset() {
        scoreA = 0
        scoreB = 0
    }
}

@Composable
fun PingPongScreen(
    modifier: Modifier = Modifier,
    viewModel: ScoreboardViewModel = viewModel()
) {
    PingPongScoreBoard(
        scoreA = viewModel.scoreA,
        scoreB = viewModel.scoreB,
        onIncrementA = viewModel::incrementA,
        onIncrementB = viewModel::incrementB,
        onReset = viewModel::reset,
        modifier = modifier
    )
}

@Composable
fun PingPongScoreBoard(scoreA: Int, scoreB: Int, onIncrementA: () -> Unit, onIncrementB: () -> Unit, onReset: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(45.dp)) {
            PlayerHud("Jogador A", scoreA, onIncrementA)
            PlayerHud("Jogador B", scoreB, onIncrementB)
        }
        Button(onClick = onReset) {
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