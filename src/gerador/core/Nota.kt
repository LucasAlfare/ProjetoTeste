package gerador.core

//TODO: adicionar boolean controle para indicar quiáltera
data class Nota(val unidade: Int, val pontuada: Boolean = false){
    override fun toString(): String {
        return "${Valores.nomes[unidade]!!}${if (pontuada) "." else ""}"
    }
}