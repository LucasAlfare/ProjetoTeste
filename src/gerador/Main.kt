/**
 * Agradecimentos especiais a David Koelle por fornecer este belo guia
 * para utilização da biblioteca jFugue:
 *
 * <link>https://medium.com/@dmkoelle/a-quick-reference-for-making-music-in-jfugue-7a15c1dbc9c3</link>
 */

package gerador

import gerador.core.Compasso
import org.jfugue.integration.LilyPondParserListener
import org.jfugue.midi.MidiFileManager
import org.jfugue.pattern.Pattern
import org.jfugue.player.Player
import org.staccato.StaccatoParser
import java.io.File

fun main(args: Array<String>) {
    val parser = StaccatoParser()
    val listener = LilyPondParserListener()
    parser.addParserListener(listener)
    parser.parse(gerarMIDI_Aleatorio(numCompassos = 10))
    println(listener.lyString)
}

fun gerarMIDI_Aleatorio(numCompassos: Int = 1, tempo: Int = 60): String {
    var staccatoStr = "G#5i Ri G#5i Ri G#5i Ri G#5i Ri | "

    for (i in 0 until numCompassos){
        for (n in Compasso.randomCompasso(4, 4).notas){
            staccatoStr += n.toString() + " "
        }
        if (i < numCompassos - 1) staccatoStr += "| "
    }

    println(staccatoStr)
    val p = Pattern(staccatoStr).setTempo(tempo)
    MidiFileManager.save(Player().getSequence(p), File("random_compasso.mid"))

    return staccatoStr
}