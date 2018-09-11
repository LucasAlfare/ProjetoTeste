package gerador.core

object Valores {

    const val WHOLE = 1
    const val HALF = 2
    const val QUARTER = 4
    const val EIGHTH = 8
    const val SIXTEENTH = 16
    const val THIRTY_SECOND = 32
    const val SIXTY_FOURTH = 64

    //unidade / valor respectivo
    val duracoes = mutableMapOf(
            WHOLE to    4.0,
            HALF to    2.0,
            QUARTER to    1.0,
            EIGHTH to    0.5,
            SIXTEENTH to   0.25,
            THIRTY_SECOND to  0.125,
            SIXTY_FOURTH to 0.0625
    )

    val nomes = mutableMapOf(
            WHOLE to    "semibreve",
            HALF to       "mínima",
            QUARTER to     "semínima",
            EIGHTH to     "colcheia",
            SIXTEENTH to "semicolcheia",
            THIRTY_SECOND to         "fusa",
            SIXTY_FOURTH to     "semifusa"
    )
}