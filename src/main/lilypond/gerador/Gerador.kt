package main.lilypond.gerador

/**
 * ESTE CÓDIGO KOTLIN IRÁ GERAR, COMPILAR, SALVAR E ABRIR UM
 * ARQUIVO PDF CONTENDO COMPASSOS COM NOTAS ALEATÓRIAS!
 */

import java.io.*
import java.util.*

val notas = listOf(
        "c", "d", "e", "f", "g", "a", "b"
)

val acidentes = listOf(
        ""
        //"is",
        //"es"
)

val oitavas = listOf(
        //",",
        //"",
        "'"
        //"''"
        //"'''",
        //"''''"
)

val unidades = listOf(
        //1,
        2,
        4,
        8
        //16,
        //32,
        //64
)

val complementos = listOf(
        ""
        //"."
)

fun aleatorioDe(lista: List<String>): String {
    return lista[Random().nextInt(lista.size)]
}

fun aleatorioDe(lista: List<Int>): Int {
    return lista[Random().nextInt(lista.size)]
}

@Suppress("IMPLICIT_CAST_TO_ANY")
class Nota(private val nota:     String,
           private val acidente: String,
           private val oitava:   String,
           private val unidade:     Int,
           val complemento:      String,
           val duracao: Double){

    override fun toString() = "$nota$acidente$oitava$unidade$complemento"

    companion object {
        fun randomNota(): Nota {
            val u = aleatorioDe(unidades)
            val d = when (u){
                 1   ->    4.0
                 2   ->    2.0
                 4   ->    1.0
                 8   ->    0.5
                16   ->   0.25
                32   ->  0.125
                64   -> 0.0625
                else ->      0
            }

            return Nota(
                    aleatorioDe(notas),
                    aleatorioDe(acidentes),
                    aleatorioDe(oitavas),
                    u,
                    aleatorioDe(complementos),
                    d as Double
            )
        }
    }
}

class Compasso(private val quantidadeTempos: Int, private val unidade: Int) {

    private val mapaDuracoes = mutableMapOf(
             1 to    4.0,
             2 to    2.0,
             4 to    1.0,
             8 to    0.5,
            16 to   0.25,
            32 to  0.125,
            64 to 0.0625
    )

    val duracaoMaxima = quantidadeTempos * mapaDuracoes[unidade]!!.toDouble()
    var notas         = arrayListOf<Nota>()
    var primeiro      = true
    var ultimo        = false

    //TODO: implementar incremento para quiálteras
    fun duracaoAtual(): Double {
        var total = 0.0
        for (n in notas){
            var totalNota = n.duracao
            if (n.complemento == ".") totalNota += totalNota / 2
            total += totalNota
        }

        return total
    }

    fun adicionarNoFim(nota: Nota) {
        val compassoTeste = Compasso(quantidadeTempos, unidade)
        compassoTeste.notas.addAll(notas)
        compassoTeste.notas.add(nota)
        if (compassoTeste.duracaoAtual() <= duracaoMaxima) {
            notas.add(nota)
        }
    }

    override fun toString(): String {
        var s = if (!primeiro) "{" else ""
        for (nota in notas) s += "$nota "
        if (ultimo) s += "}"
        return (if (primeiro) "\\new Staff {\\time $quantidadeTempos/$unidade {" else "") + s + "}"
    }

    companion object {
        fun randomCompasso(quantidadeTempos: Int = 4, unidade: Int = 4): Compasso {
            val c = Compasso(quantidadeTempos, unidade)

            while (c.duracaoAtual() < c.duracaoMaxima){
                val n = Nota.randomNota()
                c.adicionarNoFim(n)

                /*
                if (c.duracaoAtual() >= 3.9375 && c.duracaoAtual() < c.duracaoMaxima) {
                    println("ficou incompleto...")
                    break
                }
                 */
            }

            return c
        }
    }
}

var log = ""

fun main(args: Array<String>) {
    val nomeArquivo = "arquivo.ly"
    val s = randomLilyString(temposCompasso = 4, numCompassos = 4).replace(" }", "}")
    compilarEAbrirLilyArquivo(nomeArquivo, s)
}

private fun randomLilyString(temposCompasso: Int = 4, unidadeCompasso: Int = 4, numCompassos: Int = 3): String {
    var r = ""

    for (i in 0 until numCompassos){
        val c = Compasso.randomCompasso(temposCompasso, unidadeCompasso)
        if (i in 1..(numCompassos - 1)){
            c.primeiro = false
            c.ultimo = false
        }

        if (i == numCompassos - 1) c.ultimo = true
        r += c.toString()
    }

    return r
}

private fun salvarLilyArquivo(nomeArquivo: String, lilyString: String){
    log = "Tentando salvar LilyString em arquivo..."
    println(log)
    val arquivo = File(nomeArquivo)
    arquivo.writeText(lilyString)
    log = "Arquivo $nomeArquivo gravado com sucesso."
    println(log)
}

private fun compilarEAbrirLilyArquivo(nomeArquivo: String, conteudo: String){
    salvarLilyArquivo(nomeArquivo, conteudo)
    log = "Tentando compilar LilyString..."
    println(log)

    executarComando("lilypond $nomeArquivo")

    log = "LilyString compilada com sucesso."
    println(log)

    abrirPDF(nomeArquivo)
}

private fun abrirPDF(nomeArquivo: String){
    val novoNome = nomeArquivo.replace("ly", "pdf")
    log = "Tentando abrir o arquivo $novoNome"
    println(log)

    executarComando("xdg-open $novoNome")

    log = "O arquivo PDF foi aberto com sucesso. Obrigado por usar!"
    println(log)
}

private fun executarComando(comando: String){
    log = "Preparando para executar comando..."
    val p = Runtime.getRuntime().exec(comando)
    val bri = BufferedReader(InputStreamReader(p.inputStream))

    while (bri.readLine() != null){
        println(bri.readLine())
    }

    bri.close()
    p.waitFor()
}