package gerador.gui.desenho.auxiliar

import gerador.core.Valores
import java.awt.Graphics
import java.awt.Image
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import javax.swing.ImageIcon

object Imagem {

    @JvmStatic
    fun desenharNota(unidadeNota: Int, largura: Int, altura: Int, posX: Int, posY: Int, grafico: Graphics){
        grafico.drawImage(imagem(caminhoImagemNota(unidadeNota)!!, largura, altura), posX, posY, null)
    }

    @JvmStatic
    fun desenharPauta(largura: Int, altura: Int, posX: Int, posY: Int, grafico: Graphics) {
        grafico.drawImage(imagem("imagens/musicstaff.svg.png", largura, altura), posX, posY, null)
    }

    @JvmStatic
    fun desenharClaveG(largura: Int, altura: Int, posX: Int, posY: Int, grafico: Graphics) {
        grafico.drawImage(imagem("imagens/G-Clef.svg.png", largura, altura), posX, posY, null)
    }

    @JvmStatic
    fun desenharFormulaCompasso(largura: Int, altura: Int, posX: Int, posY: Int, grafico: Graphics){
        grafico.drawImage(imagem("imagens/commontime.svg.png", largura, altura), posX, posY, null)
    }

    private fun imagem(caminho: String, largura: Int, altura: Int): Image {
        return redimensionada(ImageIcon(caminho).image, largura, altura)
    }

    private fun redimensionada(imagem: Image, novaLargura: Int, novaAltura: Int): Image {
        val ret = BufferedImage(novaLargura, novaAltura, BufferedImage.TYPE_INT_ARGB)
        val auxGraf = ret.createGraphics()
        auxGraf.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
        auxGraf.drawImage(imagem, 0, 0, novaLargura, novaAltura, null)
        auxGraf.dispose()
        return ret
    }

    private fun caminhoImagemNota(unidade: Int): String? {
        when (unidade) {
            Valores.SEMIBREVE -> return "imagens/wholenote501.svg.png"
            Valores.MINIMA -> return "imagens/halfnote500.svg.png"
            Valores.SEMINIMA -> return "imagens/quarternote500.svg.png"
            Valores.COLCHEIA -> return "imagens/eighthnote500.svg.png"
            else -> return null
        }
    }

    @JvmStatic
    fun tamanhoNota(unidade: Int): IntArray {
        when (unidade) {
            Valores.SEMIBREVE -> return intArrayOf(500, 250)
            Valores.MINIMA-> return intArrayOf(500, 833)
            Valores.SEMINIMA -> return intArrayOf(500, 833)
            Valores.COLCHEIA -> return intArrayOf(500, 583)
            else -> return intArrayOf(0, 0)
        }
    }

    @JvmStatic
    val tamanhoPauta = intArrayOf(2000, 1000)

    @JvmStatic
    val tamanhoClaveG = intArrayOf(500, 852)

    @JvmStatic
    val tamanhoFormulaCompassoC = intArrayOf(500, 1500)

    fun tamanhoConvertido(a: IntArray, b: IntArray, c: IntArray): IntArray {
        return intArrayOf(c[0] * b[0] / a[0], c[1] * b[1] / a[1])
    }
}
