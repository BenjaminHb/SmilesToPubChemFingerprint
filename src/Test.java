import java.util.Scanner;

/**
 * Created by benjaminzhang on 28/07/2017.
 * Copyright © benjaminzhang 2017.
 */
public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String smiles = input.nextLine();
        SmilesToPubChemFingerprint smilesToPubChemFingerprint = new SmilesToPubChemFingerprint(smiles);
        System.out.println("Smiles = "+smilesToPubChemFingerprint.getFingerprintBitSetString()+"-");

    }
}
