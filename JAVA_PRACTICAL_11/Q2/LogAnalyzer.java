
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class LogAnalyzer {
    public static void main(String[] args) throws IOException {
        Path folder = Paths.get("logs");
        Files.createDirectories(folder);

        // Create sample log files
        Files.write(folder.resolve("app1.txt"), List.of(
            "INFO Application started",
            "ERROR Database connection failed",
            "INFO User logged in"
        ));

        Files.write(folder.resolve("app2.txt"), List.of(
            "WARNING Low memory",
            "ERROR File not found",
            "ERROR Network timeout"
        ));

        Files.write(folder.resolve("app3.txt"), List.of(
            "INFO Server running",
            "INFO Request completed"
        ));

        String keyword = "ERROR";
        long totalLines = 0;
        long matchingLines = 0;

        try (DirectoryStream<Path> files =
                 Files.newDirectoryStream(folder, "*.txt")) {

            for (Path file : files) {
                long fileLines = 0;
                long fileMatches = 0;

                // Read one line at a time
                try (var reader = Files.newBufferedReader(file)) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        fileLines++;

                        if (line.contains(keyword)) {
                            fileMatches++;
                        }
                    }
                }

                totalLines += fileLines;
                matchingLines += fileMatches;

                BasicFileAttributes attrs =
                    Files.readAttributes(
                        file, BasicFileAttributes.class);

                System.out.println("File: " + file.getFileName());
                System.out.println("Size: " + attrs.size() + " bytes");
                System.out.println("Lines: " + fileLines);
                System.out.println("Matching lines: " + fileMatches);
                System.out.println();
            }
        }

        System.out.println("Total lines: " + totalLines);
        System.out.println("Lines containing " + keyword + ": "
                           + matchingLines);
    }
}
