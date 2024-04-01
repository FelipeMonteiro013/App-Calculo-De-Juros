package com.example.calculodejuros.calculos

fun calcularJuros(captal: Double, taxa: Double, tempo: Double): Double {
    return captal * taxa / 100 * tempo
}

fun calcularMontante(captal: Double, juros: Double): Double {
    return captal + juros
}