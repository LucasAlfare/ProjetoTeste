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

            int[] tp = {500, 100};
            int[] tcg = getTamanhoClaveG();

            desenharPauta(tp[0], tp[1], 0, 0, g);
        }
    }

    public static void main(String[] args) {
        new Testes().setVisible(true);
    }
}
