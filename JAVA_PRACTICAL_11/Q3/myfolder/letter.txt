
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class FolderReport {
    public static void main(String[] args) throws IOException {
        Path root = Paths.get("myfolder");
        Path report = Paths.get("folder-report.txt");

        Files.createDirectories(root);
        List<String> lines = new ArrayList<>();

        lines.add("FOLDER TREE REPORT");
        lines.add("Root: " + root.toAbsolutePath());
        lines.add("----------------------------------------");

        long[] totals = {0, 0}; // [file count, total bytes]

        Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(
                    Path file, BasicFileAttributes attrs) {

                long size = attrs.size();
                Instant modified = attrs.lastModifiedTime().toInstant();

                lines.add(
                    root.relativize(file)
                        + " | Size: " + size + " bytes"
                        + " | Modified: " + modified
                );

                totals[0]++;
                totals[1] += size;

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(
                    Path file, IOException exc) {

                lines.add("Could not access: " + file
                          + " | " + exc.getMessage());

                return FileVisitResult.CONTINUE;
            }
        });

        lines.add("----------------------------------------");
        lines.add("Total files: " + totals[0]);
        lines.add("Total size: " + totals[1] + " bytes");

        Files.write(report, lines);

        System.out.println("Report created: "
                           + report.toAbsolutePath());
        System.out.println("Files found: " + totals[0]);
        System.out.println("Total size: " + totals[1] + " bytes");
    }
}
