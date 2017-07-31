import java.util.Scanner;

/**
 * Created by benjaminzhang on 28/07/2017.
 * Copyright © benjaminzhang 2017.
 */
public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String a = input.nextLine();
        System.out.println("a = -" + a + "-");

        if (a.contains("\\")) {
            String b = a.substring(0, a.lastIndexOf('\\') + 1);
            System.out.println("b = -" + b + "-");
            String c = a.substring(a.lastIndexOf('\\') + 1);
            System.out.println("c = -" + c + "-");
            String d = "OUT_PubChemFingerprint_" + c;
            String e = "ERROR_PubChemFingerprint_" + c;
            System.out.println(d + "\n" + e);
        } else if (a.contains("/")) {
            String b = a.substring(0, a.lastIndexOf('/') + 1);
            System.out.println("b = -" + b + "-");
            String c = a.substring(a.lastIndexOf('/') + 1);
            System.out.println("c = -" + c + "-");
            if (c.contains(".")) {
                c = c.substring(0, c.lastIndexOf('.'));
            }
            String d = "OUT_PubChemFingerprint_" + c + ".txt";
            String e = "ERROR_PubChemFingerprint_" + c + ".txt";
            System.out.println(d + "\n" + e);
        }
    }
}
