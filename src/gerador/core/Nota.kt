package gerador.core

import java.util.*

data class Nota(var unidade: Int, val pontuada: Boolean = false){
    override fun toString(): String {
        var r = ""
        if (Random().nextBoolean()) {
            val stringsNotas = arrayListOf("C", "D", "E", "F", "G", "A", "B")
            r += stringsNotas[Random().nextInt(stringsNotas.size)]

            when (Random().nextInt(3)){
                1 -> r += Valores.SUSTENIDO
                2 -> r += Valores.BEMOL
            }
        } else {
            r += Valores.PAUSA
        }

        r += Valores.duracoesString[unidade]!!

        if (pontuada) r += Valores.PONTO_AUMENTACAO

        return r
    }
}