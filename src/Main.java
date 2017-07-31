import java.io.*;
import java.util.Scanner;

/**
 * Created by benjaminzhang on 12/07/2017.
 * Copyright © benjaminzhang 2017.
 */
public class Main {
    private String readFileName;
    private String writeFileName;
    private String errorFileName;
    private StringBuffer stringBuffer = new StringBuffer();

    protected void Run() {
        File file = new File(readFileName);
        Reader reader = null;
        WriteFiles writeFiles = new WriteFiles(writeFileName);

        try {
            //System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            int singleQuoteMark = 0;
            int count = 0;
            writeFiles.writeFileFromFileEnd("[");
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，rn这两个字符在一起时，表示一个换行。但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉r，或者屏蔽n。否则，将会多出很多空行。
                if (((char) tempchar) != 'r' && (char) tempchar != ',' && (char) tempchar != ' ') {
                    // System.out.print("Reading...");
                    if ((char) tempchar == '\'') singleQuoteMark++;
                    else stringBuffer.append((char) tempchar);
                    if (singleQuoteMark == 2) {
                        singleQuoteMark = 0;
                        SmilesToPubChemFingerprint smilesToPubChemFingerprint = new SmilesToPubChemFingerprint(errorFileName, stringBuffer.toString());
                        writeFiles.writeFileFromFileEnd(smilesToPubChemFingerprint.getFingerprintBitSetString() + ", ");
                        stringBuffer.delete(0, stringBuffer.length());
                        System.out.println("\nreadFileStatus = true " + count++);
                    }
                }
            }
            reader.close();
            writeFiles.writeFileFromFileEnd("]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fileNameProcess(String readFileName) {
        String pathNameTemp = "";
        String fileName = "";
        if (readFileName.contains("\\")) {
            pathNameTemp = readFileName.substring(0, readFileName.lastIndexOf('\\') + 1);
            fileName = readFileName.substring(readFileName.lastIndexOf('\\') + 1);
        } else if (readFileName.contains("/")) {
            pathNameTemp = readFileName.substring(0, readFileName.lastIndexOf('/') + 1);
            fileName = readFileName.substring(readFileName.lastIndexOf('/') + 1);
        }
        if (fileName.contains(".")) {
            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
        }
        writeFileName = pathNameTemp + "OUT_PubChemFingerprint_" + fileName + ".txt";
        errorFileName = pathNameTemp + "ERROR_PubChemFingerprint_" + fileName + ".txt";
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("Please input read file pathname:");
        main.readFileName = new Scanner(System.in).nextLine();
        main.fileNameProcess(main.readFileName);
        System.out.println("Output file name:\n"+main.writeFileName);
        System.out.println("Errors occur during processing are saved into file:\n"+main.errorFileName);
        main.Run();
    }
}
