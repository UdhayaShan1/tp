package seedu.realodex.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_HOUSINGTYPE;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.realodex.commons.util.ToStringBuilder;
import seedu.realodex.logic.Messages;
import seedu.realodex.model.Model;
import seedu.realodex.model.person.Person;

/**
 * Filters and lists all persons in realodex whose name contains the argument keyphrase.
 * Keyphrase matching is case-insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters all clients by specified field (name, remark, tag, birthday, housing type) "
            + "with the specified keyphrase (non-empty, case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Note that although the fields are listed as optional, ONE field must strictly be present.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "KEYPHRASE] "
            + "[" + PREFIX_REMARK + "REMARK]"
            + "[" + PREFIX_TAG + "TAG]\n"
            + "[" + PREFIX_BIRTHDAY + "MONTH] "
            + "[" + PREFIX_HOUSINGTYPE + "HOUSING-TYPE]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "alice tan";
    public static final String MESSAGE_FILTER_HELP = "Filter Command: "
            + "Filters clients by ONE specified field (name, remark, tag, birthday, housing type). "
            + "All inputs must be non-empty and are case-insensitive.\n"
            + "Name and remark are filtered with a keyphrase, "
            + "tags are filtered with a valid tag name (buyer/seller), "
            + "birthdays are filtered in MMM format (jan, feb, mar etc)\n"
            + "and housing type is filtered with a valid housing type (HDB, Condominium, Landed property, Good "
            + "Class Bungalow).\n"
            + "Results are displayed as a list with index numbers.\n"
            + "Format: filter [n/NAME_KEYPHRASE] [r/REMARK_KEYPHRASE] [t/TAG] [b/BIRTHDAY] [h/HOUSING_TYPE]\n"
            + "Examples: filter n/Jus, filter r/likes coffee, filter t/buyer, filter b/oct, filter h/landed property\n";

    public static final String MESSAGE_FILTER_CONFLICT = "Filter command can only filter by one field.\n";

    public static final String MESSAGE_FILTER_EMPTY_REMARK = "Cannot filter by empty remark.\n";

    private final Predicate<Person> predicate;

    public FilterCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return predicate.equals(otherFilterCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
