package bestgymever;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    // List to hold all members read from the file
    private List<Member> members = new ArrayList<>();

    // Reads members from the file and fills the list
    public void loadFromFile(String path) {
        members.clear(); // empty list before loading new data

        // try-with-resources to close the file automatically when done
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            String line;
            int lineNo = 0;

            while ((line = br.readLine()) != null) {
                lineNo++;

                // skip the header line and empty lines
                if (lineNo == 1 || line.trim().isEmpty()) continue;

                String[] parts = line.split(";");
                if (parts.length != 7) {
                    System.out.println("Bad row format at line " + lineNo + " → " + line);
                    continue;
                }

                try {
                    String name = parts[0].trim();
                    String address = parts[1].trim();
                    String email = parts[2].trim();
                    String personalNumber = parts[3].trim();
                    LocalDate memberSince = LocalDate.parse(parts[4].trim());
                    LocalDate lastFeePaid = LocalDate.parse(parts[5].trim());
                    String levelText = parts[6].trim();

                    // translate Swedish text to English enum
                    MembershipLevel level;
                    if (levelText.equalsIgnoreCase("guld")) {
                        level = MembershipLevel.GOLD;
                    } else if (levelText.equalsIgnoreCase("platina")) {
                        level = MembershipLevel.PLATINUM;
                    } else {
                        level = MembershipLevel.STANDARD;
                    }

                    Member member = new Member(
                            name,
                            address,
                            email,
                            personalNumber,
                            memberSince,
                            lastFeePaid,
                            level
                    );

                    members.add(member);

                } catch (Exception e) {
                    System.out.println("Error parsing line " + lineNo + ": " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
    }

    // Finds a member by personal number
    public Member findByPersonalNumber(String personalNumber) {
        if (personalNumber == null) return null;

        for (Member m : members) {
            if (m.getPersonalNumber().equals(personalNumber.trim())) {
                return m;
            }
        }
        return null;
    }

    // Finds a member by name (case-insensitive)
    public Member findByName(String name) {
        if (name == null) return null;

        String target = name.trim().toLowerCase();

        for (Member m : members) {
            if (m.getName().toLowerCase().equals(target)) {
                return m;
            }
        }

        return null;
    }
}
