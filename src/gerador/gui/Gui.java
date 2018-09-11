package gerador.gui;

import gerador.gui.desenho.Nota;
import gerador.gui.desenho.Pauta;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    public Gui(){
        setSize(500, 500);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        add(new Comp());
    }

    class Comp extends JComponent {
        private Nota[] nota = new Nota[16];

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);

            int nwx = 20;
            int nwy = 20;
            int width = 512;

            int sdx = 12;
            int sdy = 6;

            Pauta staff = new Pauta();
            staff.setNW(nwx, nwy);
            staff.setWidth(width-2*nwx);
            staff.setSpace(sdy);

            int x = 0;
            for (int i=0; i<8; i++) {
                nota[i] = new Nota(sdy*2, 4);
            }
            for (int i=8; i<12; i++) {
                nota[i] = new Nota(sdy*2, 2);
            }
            for (int i=12; i<16; i++) {
                nota[i] = new Nota(sdy*2, 1);
            }
            for (int i=0; i<4; i++) {
                nota[i].setNW(nwx + (x += sdx), nwy+i*sdy);
            }
            for (int i=4; i<8; i++) {
                nota[i].setNW(nwx + (x += sdx), nwy+(i-4)*sdy);
            }
            for (int i=8; i<16; i++) {
                nota[i].setNW(nwx + (x += sdx), nwy+(i-8)*sdy);
            }

            staff.draw(g);

            for (Nota aNota : nota) {
                aNota.draw(g);
            }
        }
    }

    public static void main(String[] args) {
        new Gui().setVisible(true);
    }
}
