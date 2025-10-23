package bestgymever;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class PtLogger {

    // appends one visit line to the visits file
    // format: Name;PersonalNumber;YYYY-MM-DD
    public void logVisit(String path, Member member) {
        if (member == null) return;

        String line = member.getName() + ";" + member.getPersonalNumber() + ";" + LocalDate.now() + System.lineSeparator();

        // Open the file in append mode and create it if it does not exist
        try (BufferedWriter bw = Files.newBufferedWriter(
                Paths.get(path),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            bw.write(line);

        } catch (IOException e) {
            System.out.println("Could not write to visits file '" + path + "': " + e.getMessage());
        }
    }
}
