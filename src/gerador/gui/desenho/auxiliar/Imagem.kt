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
        grafico.drawImage(imagem("imagens/musicstaff.svg.png", altura, largura), posX, posY, null)
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
            Valores.WHOLE -> return "imagens/wholenote501.svg.png"
            Valores.HALF -> return "imagens/halfnote500.svg.png"
            Valores.QUARTER -> return "imagens/quarternote500.svg.png"
            Valores.EIGHTH -> return "imagens/eighthnote500.svg.png"
            else -> return null
        }
    }

    @JvmStatic
    fun tamanhoImagemNota(unidade: Int): IntArray {
        when (unidade) {
            Valores.WHOLE -> return intArrayOf(500, 250)
            Valores.HALF-> return intArrayOf(500, 833)
            Valores.QUARTER -> return intArrayOf(500, 833)
            Valores.EIGHTH -> return intArrayOf(500, 583)
            else -> return intArrayOf(0, 0)
        }
    }
}
