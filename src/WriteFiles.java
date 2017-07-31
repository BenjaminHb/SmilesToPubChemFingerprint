import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by benjaminzhang on 31/07/2017.
 * Copyright © benjaminzhang 2017.
 */
class WriteFiles {
    private String writeFileName;

    /**
     * 追加文件尾部：使用RandomAccessFile
     */
    protected WriteFiles(String _writeFileName){
        writeFileName = _writeFileName;
    }

    protected void writeFileFromFileEnd(String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(writeFileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
