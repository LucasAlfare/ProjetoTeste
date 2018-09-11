package gerador.core

//TODO: adicionar campos para intonação
object Valores {

    const val SEMIBREVE    =  1
    const val MINIMA       =  2
    const val SEMINIMA     =  4
    const val COLCHEIA     =  8
    const val SEMICOLCHEIA = 16
    const val FUSA         = 32
    const val SEMIFUSA     = 64

    const val PAUSA = "R"

    const val SUSTENIDO = "#"
    const val BEMOL = "b"
    const val PONTO_AUMENTACAO = "."

    //_unidade_ to _valor_
    val duracoes = mutableMapOf(
            SEMIBREVE to    4.0,
            MINIMA to    2.0,
            SEMINIMA to    1.0,
            COLCHEIA to    0.5,
            SEMICOLCHEIA to   0.25,
            FUSA to  0.125,
            SEMIFUSA to 0.0625
    )

    val duracoesString = mutableMapOf(
            SEMIBREVE to "w",
            MINIMA to "h",
            SEMINIMA to "q",
            COLCHEIA to "i",
            SEMICOLCHEIA to "s",
            FUSA to "t",
            SEMIFUSA to "x"
    )
}