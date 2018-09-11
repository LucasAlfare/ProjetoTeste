package gerador.gui.desenho.auxiliar;

import gerador.core.Valores;

import javax.swing.*;
import java.awt.*;
import static gerador.gui.desenho.auxiliar.Imagem.*;

public class Testes extends JFrame {

    public Testes(){
        setSize(500, 500);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        add(new Comp());
    }

    class Comp extends JComponent {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);

            int posPautaX = 0, posPautaY = 50;

            int[] tp = {500, 100};
            int[] tcg = tamanhoConvertido(getTamanhoPauta(), getTamanhoClaveG(), tp);

            desenharPauta(tp[0], tp[1], posPautaX, posPautaY, g);
            desenharClaveG(tcg[0], tcg[1], posPautaX, posPautaY + 10, g);
        }
    }

    public static void main(String[] args) {
        new Testes().setVisible(true);
    }
}
