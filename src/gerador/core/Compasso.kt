package gerador.core

import java.util.*

class Compasso(var quantidade: Int, var unidade: Int) {

    val notas = arrayListOf<Nota>()
    val duracaoMaxima = quantidade * Valores.duracoes[unidade]!!.toDouble()

    fun duracaoAtual(): Double {
        var total = 0.0
        for (n in notas){
            var totalNota = Valores.duracoes[n.unidade]!!
            if (n.pontuada) totalNota += totalNota / 2
            total += totalNota
        }

        return total
    }

    fun adicionarNoFim(nota: Nota) {
        val compassoTeste = Compasso(quantidade, unidade)
        compassoTeste.notas.addAll(notas)
        compassoTeste.notas.add(nota)
        if (compassoTeste.duracaoAtual() <= duracaoMaxima) {
            notas.add(nota)
        }
    }

    override fun toString(): String {
        return "$quantidade/$unidade: $notas"
    }

    companion object {
        val notasOpcoes = arrayListOf(
                Nota(Valores.SEMIBREVE),
                Nota(Valores.MINIMA),
                Nota(Valores.SEMINIMA),
                Nota(Valores.COLCHEIA),
                Nota(Valores.SEMICOLCHEIA),
                Nota(Valores.FUSA),
                Nota(Valores.SEMIFUSA)

                /*
                TODO: implementar notas com ponto de aumentação
                ,Nota(Valores.SEMIBREVE, true),
                Nota(Valores.MINIMA, true),
                Nota(Valores.SEMINIMA, true),
                Nota(Valores.COLCHEIA, true),
                Nota(Valores.SEMICOLCHEIA, true),
                Nota(Valores.FUSA, true),
                Nota(Valores.SEMIFUSA, true)
                Nota(Valores.SEMIFUSA, true)
                 */
        )

        //utilizaado para quando não forem passados parâmetros
        private val unidades = arrayListOf(1, 2, 4, 8, 16, 32, 64)

        fun randomCompasso(
                quantidade: Int = Random().nextInt(64) + 1,
                unidade: Int = unidades[Random().nextInt(unidades.size)]): Compasso {
            val c = Compasso(quantidade, unidade)

            while (c.duracaoAtual() < c.duracaoMaxima){
                c.adicionarNoFim(notasOpcoes[Random().nextInt(notasOpcoes.size)])

                /*
                if (c.duracaoAtual() >= 3.9375 && c.duracaoAtual() < c.duracaoMaxima()) {
                    println("ficou incompleto...")
                    break
                }
                 */
            }

            return c
        }
    }
}