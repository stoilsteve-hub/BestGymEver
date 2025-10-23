package bestgymever;

import java.time.LocalDate;

public class Member {

    //the data we keep for each person
    private String name;
    private String address;
    private String email;
    private String personalNumber;
    private LocalDate memberSince;
    private LocalDate lastFeePaid;
    private MembershipLevel membershipLevel;

    //Constructor
    public Member(String name, String address, String email, String personalNumber,
                  LocalDate memberSince, LocalDate lastFeePaid, MembershipLevel membershipLevel) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.personalNumber = personalNumber;
        this.memberSince = memberSince;
        this.lastFeePaid = lastFeePaid;
        this.membershipLevel = membershipLevel;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }

    public LocalDate getLastFeePaid() {
        return lastFeePaid;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }
}
