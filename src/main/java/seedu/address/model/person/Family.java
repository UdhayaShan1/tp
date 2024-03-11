package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
public class Family {
    public static final String MESSAGE_CONSTRAINTS = "Family size should be at least one";
    private Integer familySize;

    public Family(String familySize) {
        requireNonNull(familySize);
        this.familySize = Integer.valueOf(familySize);
    }

    public static boolean isValidFamily(String familySize) {
        return Integer.parseInt(familySize) >= 0;
    }
}