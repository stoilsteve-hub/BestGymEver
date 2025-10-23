package bestgymever;

import java.time.LocalDate;

public class MemberService {

    // decides the status for a member using today's real date
    public MemberStatus getStatus(Member member) {
        return getStatus(member, LocalDate.now());
    }

    // decides the status for a member as of the given date "today" (I use it in the @test)
    public MemberStatus getStatus(Member member, LocalDate today) {
        if (member == null) return MemberStatus.UNKNOWN;

        // "current" if lastFeePaid is AFTER (strictly less than one year ago)
        LocalDate oneYearAgo = today.minusYears(1);
        if (member.getLastFeePaid().isAfter(oneYearAgo)) {
            return MemberStatus.CURRENT;
        } else {
            return MemberStatus.FORMER;
        }
    }
}
