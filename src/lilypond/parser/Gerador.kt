package lilypond.parser

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
        "", "is", "es"
)

val oitavas = listOf(
        //",",
        //"",
        "'",
        "''"
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

val duracoes = listOf(
        4.0, 2.0, 1.0, 0.5, 0.25, 0.125, 0.0625
)

fun aleatorioDe(lista: List<String>): String {
    return lista[Random().nextInt(lista.size)]
}

fun aleatorioDe(lista: List<Int>): Int {
    return lista[Random().nextInt(lista.size)]
}

fun aleatorioDe(lista: List<Double>): Double {
    return lista[Random().nextInt(lista.size)]
}

@Suppress("IMPLICIT_CAST_TO_ANY")
class Nota(val nota: String,
           val acidente: String,
           val oitava: String,
           val unidade: Int,
           val complemento: String,
           val duracao: Double){

    override fun toString() = "$nota$acidente$oitava$unidade$complemento"

    companion object {
        fun randomNota(): Nota {
            val u = aleatorioDe(unidades)
            val d = when (u){
                1 -> 4.0
                2 -> 2.0
                4 -> 1.0
                8 -> 0.5
                16 -> 0.25
                32 -> 0.125
                64 -> 0.0625
                else -> 0
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

class Compasso(val quantidadeTempos: Int, val unidade: Int) {

    private val mapaDuracoes = mutableMapOf(
            1 to 4.0,
            2 to 2.0,
            4 to 1.0,
            8 to 0.5,
            16 to 0.25,
            32 to 0.125,
            64 to 0.0625
    )

    val duracaoMaxima = quantidadeTempos * mapaDuracoes[unidade]!!.toDouble()
    var notas = arrayListOf<Nota>()
    var primeiroCompasso = true
    var ultimoCompasso = false

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
        var s = if (!primeiroCompasso) "{" else ""
        for (nota in notas) s += "$nota "
        if (ultimoCompasso) s += "}"
        return (if (primeiroCompasso) "\\new Staff {\\time $quantidadeTempos/$unidade {" else "") + s + "}"
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
    val s = randomLilypondString(numCompassos = 20).replace(" }", "}")
    compilarLilyArquivo(nomeArquivo, s)
}

fun randomLilypondString(temposCompasso: Int = 4, unidadeCompasso: Int = 4, numCompassos: Int = 3): String {
    var r = ""

    for (i in 0 until numCompassos){
        val c = Compasso.randomCompasso(temposCompasso, unidadeCompasso)
        if (i > 0 && i < numCompassos){
            c.primeiroCompasso = false
            c.ultimoCompasso = false
        }

        if (i == numCompassos - 1) c.ultimoCompasso = true
        r += c.toString()
    }

    return r
}

fun salvarLilyArquivo(nomeArquivo: String, lilyString: String){
    log = "Tentando salvar LilyString em arquivo..."
    println(log)
    val arquivo = File(nomeArquivo)
    arquivo.writeText(lilyString)
    log = "Arquivo $nomeArquivo gravado com sucesso."
    println(log)
}

fun compilarLilyArquivo(nomeArquivo: String, conteudo: String){
    salvarLilyArquivo(nomeArquivo, conteudo)
    log = "Tentando compilar LilyString..."
    println(log)

    executarComando("lilypond $nomeArquivo")

    log = "LilyString compilada com sucesso."
    println(log)

    abrirPDF(nomeArquivo)
}

fun abrirPDF(nomeArquivo: String){
    val novoNome = nomeArquivo.replace("ly", "pdf")
    log = "Tentando abrir o arquivo $novoNome"
    println(log)

    executarComando("xdg-open $novoNome")

    log = "O arquivo PDF foi aberto com sucesso. Obrigado por usar!"
    println(log)
}

fun executarComando(comando: String){
    val p = Runtime.getRuntime().exec(comando)
    val bri = BufferedReader(InputStreamReader(p.inputStream))

    while (bri.readLine() != null){
        println(bri.readLine())
    }

    bri.close()
    p.waitFor()
}