package com.alicher.maths;

public class EquationLibrary {

    public static String equationTemplate1 = "a*x^3 + b*x + c = 0"; // coefs: 1, -18, -83 ; bounds: 2, 10 ; answer: 5.7

    public static String equationTemplate2 = "a*sin(x)^2 + b*x^2 + c = 0"; // coefs: 1 -1 1  ; bounds: 1 1,5; answer 1,404

    public static String equationTemplate3 = "a*e^(-x) - b*sin(x)^2 = 0"; // coefs: 1 -1/2 ; bounds: 1 1,5 ; answer 1,017

    public static String systemTemplate1 = "a*x + b*lg(x) - c*y^2 = 0\nd*x^2 - e*x*y - fx + g = 0"; // coefs 1 3 -1 2 -1 -5 1 ; bounds 3,5 2,2 ; answer 3,487 2,261

    public static String systemTemplate2 = "a*x^2 + b*x + c*y^2 + d = 0\ne*x^2 + f*y + g*y^2 + h = 0"; // coefs 1 1 -1 -0,15 1 -1 1 0,17; bounds 0,15 0,17 ;answer 0,1999 0,299

    public static String equation1(double a, double b, double c) {
        String bSign = b > 0 ? "+" : "";
        String cSign = c > 0 ? "+" : "";
        String result = a + "x^3" + bSign + b + "x" + cSign + c;

        return result;
    }

    public static String equation2(double a, double b, double c) {
        String bSign = b > 0 ? "+" : "";
        String cSign = c > 0 ? "+" : "";
        String result = a + "sin(x)^2" + bSign + b + "x^2" + cSign + c;
        return result;
    }

    public static String equation3(double a, double b) {
        String bSign = b > 0 ? "+" : "";
        String result = a + "e^(-x)" + bSign + b + "sin(x)^2";
        return result;
    }

    public static String[] getSystem1(double a, double b, double c, double d, double e, double f, double g) {
        String bSign = b > 0 ? "+" : "";
        String cSign = c > 0 ? "+" : "";
        String eSign = e > 0 ? "+" : "";
        String fSign = f > 0 ? "+" : "";
        String gSign = g > 0 ? "+" : "";
        String result1 = a + "x" + bSign + b + "log10(x)" + cSign + c + "y^2";
        String result2 = d + "x^2" + eSign + e + "xy" + fSign + f + "x" + gSign + g;

        return new String[]{result1, result2};
    }

    public static String[] getSystem2(double a, double b, double c, double d, double e, double f, double g, double h) {
        String bSign = b > 0 ? "+" : "";
        String cSign = c > 0 ? "+" : "";
        String dSign = d > 0 ? "+" : "";
        String fSign = f > 0 ? "+" : "";
        String gSign = g > 0 ? "+" : "";
        String hSign = h > 0 ? "+" : "";
        String result1 = a + "x^2" + bSign + b + "x" + cSign + c + "y^2" + dSign + d;
        String result2 = e + "x^2" + fSign + f + "y" + gSign + g + "y^2" + hSign + h;
        return new String[]{result1, result2};
    }

}
