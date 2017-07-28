import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.fingerprint.IBitFingerprint;
import org.openscience.cdk.fingerprint.PubchemFingerprinter;
import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesParser;

import java.util.BitSet;

/**
 * Created by benjaminzhang on 12/07/2017.
 * Copyright © benjaminzhang 2017.
 */
public class SmilesToPubChemFingerprint {
    private PubchemFingerprinter fprinter = new PubchemFingerprinter(DefaultChemObjectBuilder.getInstance());
    private IBitFingerprint fingerprint;

    protected SmilesToPubChemFingerprint(String smiles){
        try {
            SmilesParser smilesParser = new SmilesParser(SilentChemObjectBuilder.getInstance());
            IAtomContainer atomContainer = smilesParser.parseSmiles(smiles);
            fingerprint = fprinter.getBitFingerprint(atomContainer);
        } catch (InvalidSmilesException e) {
            System.err.println(e.getMessage());
            // System.exit(1);
        } catch (CDKException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            // System.exit(1);
        }
    }

    protected BitSet getFingerprintBitSet(){
        return fingerprint.asBitSet();
    }

    protected String getFingerprintBitSetString(){
        StringBuffer stringBuffer = new StringBuffer();
        try {
            int[] setBitArray = fingerprint.getSetbits();
            stringBuffer.append('[');
            for (int i = 0; i < setBitArray.length; i++) {
                stringBuffer.append(setBitArray[i]);
                if (i + 1 != setBitArray.length) stringBuffer.append(", ");
            }
            stringBuffer.append(']');
            return stringBuffer.toString();
        }
        catch (NullPointerException e){
            return "";
        }
    }

    protected String getFingerPrintBitString(){
        StringBuffer stringBuffer = new StringBuffer();
        int[] setBitArray = fingerprint.getSetbits();
        int setBitArrayIndex = 0;

        for (int i = 0; i < fprinter.getSize(); i++) {
            if (setBitArrayIndex < setBitArray.length && setBitArray[setBitArrayIndex] == i) {
                stringBuffer.append('1');
                setBitArrayIndex++;
            }
            else stringBuffer.append('0');
        }
        return stringBuffer.toString();
    }
}
