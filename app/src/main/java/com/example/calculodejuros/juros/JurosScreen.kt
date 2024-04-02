package com.example.calculodejuros.juros

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculodejuros.components.CaixaDeEntrada
import com.example.calculodejuros.components.CardResultado

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun JurosScreen(jurosScreenViewModel: JurosScreenViewModel) {

    val capital by jurosScreenViewModel.capital.observeAsState(initial = "")
    val taxa by jurosScreenViewModel.taxa.observeAsState(initial = "")
    val tempo by jurosScreenViewModel.tempo.observeAsState(initial = "")
    val juros by jurosScreenViewModel.juros.observeAsState(initial = 0.0)
    val montante by jurosScreenViewModel.montante.observeAsState(initial = 0.0)


    val controller = LocalSoftwareKeyboardController.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Cálculo de Juros Simples",
            color = Color.Red,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Dados do Investimento", fontWeight = FontWeight.Bold)

                CaixaDeEntrada(
                    label = "Valor do investimento",
                    placeholder = "Quanto deseja investir?",
                    value = capital,
                    keyboardType = KeyboardType.Decimal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    updateValue = {
                        jurosScreenViewModel.onCaptalChanged(it)
                    }
                )

                CaixaDeEntrada(
                    label = "Taxa de juros mensal",
                    placeholder = "Qual a taxa de juros mensal?",
                    value = taxa,
                    keyboardType = KeyboardType.Decimal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    updateValue = {
                        jurosScreenViewModel.onTaxaChanged(it)
                    }
                )

                CaixaDeEntrada(
                    label = "Periodo em meses",
                    placeholder = "Qual o tempo em meses?",
                    value = tempo,
                    keyboardType = KeyboardType.Decimal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    updateValue = {
                        jurosScreenViewModel.onTempoChanged(it)
                    }
                )

                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                    onClick = {
                        if (capital != "" && taxa != "" && tempo != "") {//
                            jurosScreenViewModel.calcularJurosViewModel()
                            jurosScreenViewModel.calcularMontanteViewModel()
                            controller?.hide()
                        }
                    }) {
                    Text(text = "CALCULAR")
                }

            }

        }
        Spacer(modifier = Modifier.height(32.dp))

        CardResultado(juros = juros, montante = montante)
    }
}