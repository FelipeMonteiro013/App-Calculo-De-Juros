package com.example.calculodejuros.juros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculodejuros.calculos.calcularJuros
import com.example.calculodejuros.calculos.calcularMontante

class JurosScreenViewModel : ViewModel() {

    private val _capital = MutableLiveData<String>()
    private val _taxa = MutableLiveData<String>()
    private val _tempo = MutableLiveData<String>()
    private val _juros = MutableLiveData<Double>()
    private val _montante = MutableLiveData<Double>()

    val capital: LiveData<String> = _capital
    val taxa: LiveData<String> = _taxa
    val tempo: LiveData<String> = _tempo
    val juros: LiveData<Double> = _juros
    val montante: LiveData<Double> = _montante

    fun onCaptalChanged(newCaptal: String) {
        _capital.value = newCaptal
    }

    fun onTaxaChanged(newTaxa: String) {
        _taxa.value = newTaxa
    }

    fun onTempoChanged(newTempo: String) {
        _tempo.value = newTempo
    }

    fun calcularJurosViewModel() {
        _juros.value =
            calcularJuros(
                captal = _capital.value!!.toDouble(),
                taxa = _taxa.value!!.toDouble(),
                tempo = _tempo.value!!.toDouble()
            )
    }

    fun calcularMontanteViewModel() {
        _montante.value = calcularMontante(
            captal = _capital.value!!.toDouble(),
            juros = _juros.value!!.toDouble()
        )
    }


}