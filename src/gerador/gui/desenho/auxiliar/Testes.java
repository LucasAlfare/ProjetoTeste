package gerador.gui.desenho.auxiliar;

import gerador.core.Valores;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
            g.drawImage(resize(new ImageIcon(getCaminhoByUnidade(Valores.QUARTER)).getImage(), 500, 833), 0, 0, this);
        }

        public Image resize(Image img, int width, int height) {
            BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resizedImg.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(img, 0, 0, width, height, null);
            g2.dispose();
            return resizedImg;
        }

        public String getCaminhoByUnidade(int unidade){
            switch (unidade){
                case Valores.WHOLE: return "imagens/wholenote501.svg.png";
                case Valores.HALF: return "imagens/halfnote500.svg.png";
                case Valores.QUARTER: return "imagens/quarternote500.svg.png";
                case Valores.EIGHTH: return "imagens/eighthnote500.svg.png";
                default: return null;
            }
        }
    }

    public static void main(String[] args) {
        new Testes().setVisible(true);
    }
}
