package gerador.core

object Valores {

    const val SEMIBREVE    =  1
    const val MINIMA       =  2
    const val SEMINIMA     =  4
    const val COLCHEIA     =  8
    const val SEMICOLCHEIA = 16
    const val FUSA         = 32
    const val SEMIFUSA     = 64

    //unidade to valor respectivo
    val duracoes = mutableMapOf(
            SEMIBREVE    to    4.0,
            MINIMA       to    2.0,
            SEMINIMA     to    1.0,
            COLCHEIA     to    0.5,
            SEMICOLCHEIA to   0.25,
            FUSA         to  0.125,
            SEMIFUSA     to 0.0625
    )

    val nomes = mutableMapOf(
            SEMIBREVE    to    "semibreve",
            MINIMA       to       "mínima",
            SEMINIMA     to     "semínima",
            COLCHEIA     to     "colcheia",
            SEMICOLCHEIA to "semicolcheia",
            FUSA         to         "fusa",
            SEMIFUSA     to     "semifusa"
    )
}