package bestgymever;

import java.util.Scanner;

public class MainApp {

    // file names
    private static final String MEMBERS_FILE = "members.txt";
    private static final String VISITS_FILE = "visits.txt";

    public static void main(String[] args) {

        MemberRepository repo = new MemberRepository();
        repo.loadFromFile(MEMBERS_FILE);

        // Ask the receptionist for input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name or personal number (YYMMDD-XXXX): ");
        String input = scanner.nextLine().trim();

        //basic input validation
        boolean looksLikePersonalNumber = input.matches("\\d{6}-\\d{4}");
        // looksLikeName: letters (including Swedish å, ä, ö) and spaces only
        boolean looksLikeName = input.matches("[a-zA-ZåÅäÄöÖ\\s]+");

        //if input matches neither
        if (!looksLikePersonalNumber && !looksLikeName) {
            System.out.println("Invalid input format. Please enter a valid name or personal number (YYMMDD-XXXX).");
            scanner.close();
            return;
        }

        // find the member
        Member member = null;
        if (looksLikePersonalNumber) {
            member = repo.findByPersonalNumber(input);
        } else if (looksLikeName) {
            member = repo.findByName(input);
        }

        // Decide status using today's real date
        MemberService service = new MemberService();
        MemberStatus status = service.getStatus(member);

        // print result
        if (status == MemberStatus.CURRENT) {
            System.out.println("Current member – membership level: " + member.getMembershipLevel());
            PtLogger logger = new PtLogger();
            logger.logVisit(VISITS_FILE, member);
            System.out.println("Visit recorded in " + VISITS_FILE + ".");
        } else if (status == MemberStatus.FORMER) {
            System.out.println("Former member – last fee was paid more than a year ago.");
        } else {
            System.out.println("Unauthorized – person not found in the system.");
        }

        scanner.close();
    }
}
