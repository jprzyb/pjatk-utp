package zad1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil {

    private static String SRC_FILE_ENCODING = "Windows-1250";
    private static Charset DEST_FILE_ENCODING = StandardCharsets.UTF_8;

    public static void processDir(String dirName, String resultFileName) {
        try (BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(new File(resultFileName)), DEST_FILE_ENCODING))) {
            FileVisitor<Path> simpleFileVisitor = new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path visitedFilePath, BasicFileAttributes fileAttributes)
                        throws IOException {
                    try (BufferedReader in = new BufferedReader(
                            new InputStreamReader(new FileInputStream(visitedFilePath.toFile()), SRC_FILE_ENCODING))) {
                        String str;
                        while ((str = in.readLine()) != null) {
                            out.write(str + "\n");
                        }
                    }

                    return FileVisitResult.CONTINUE;
                }
            };


            FileSystem fileSystem = FileSystems.getDefault();
            Path rootPath = fileSystem.getPath(dirName);
            Files.walkFileTree(rootPath, simpleFileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
