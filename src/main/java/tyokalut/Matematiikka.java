package tyokalut;

public  class Matematiikka {


    public static int minimi(int x, int y) {
        if (x < y) {
            return x;
        }
        return y;
    }

    public static double minimi(double x, double y) {
        if (x < y) {
            return x;
        }
        return y;
    }

    public static int maksimi(int x, int y) {
        if (x < y) {
            return y;
        }
        return x;
    }

    public static double maksimi(double x, double y) {
        if (x < y) {
            return y;
        }
        return x;
    }

    public static int itseisarvo(int n) {
        if (n > 0) {
            return n;
        }
        return n * -1;
    }

    public static double itseisarvo(double n) {
        if (n > 0) {
            return n;
        }
        return n * -1;
    }
}
