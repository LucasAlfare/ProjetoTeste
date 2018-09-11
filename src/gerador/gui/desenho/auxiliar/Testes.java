package gerador.gui.desenho.auxiliar;

import gerador.core.Valores;

import javax.swing.*;
import java.awt.*;

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
            int[] t = Imagem.tamanhoImagemNota(Valores.QUARTER);
            //Imagem.desenharNota(Valores.QUARTER, t[0] / 10, t[1] / 10, 10, 10, g);
            Imagem.desenharClaveG(100, 100, 10, 10, g);
        }
    }

    public static void main(String[] args) {
        new Testes().setVisible(true);
    }
}
