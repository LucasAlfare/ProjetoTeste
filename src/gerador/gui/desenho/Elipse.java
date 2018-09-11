package gerador.gui.desenho;

public class Elipse {                            // x^2/a2 ++ y^2/c2 = 1

    private double major, minor;
    private double a2, c2;

    public Elipse(double major, double minor) {
        this.minor = minor;
        this.major = major;
        a2 = minor*minor;
        c2 = major*major;
    }

    public double getMinor() {
        return minor;
    }

    public double getMajor() {
        return major;
    }

    public boolean in(double x, double y) {
        return x*x/a2 +  y*y/c2 <= 1.0;
    }

}