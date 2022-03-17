package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COMPANIES;
import static seedu.address.model.Model.PREDICATE_SHOW_NO_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_NO_PERSONS;

import seedu.address.model.Model;

public class ListCompanyCommand extends Command {

    public static final String COMMAND_WORD = "listc";

    public static final String MESSAGE_SUCCESS = "Listed all companies";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        //Show all the events and empty out all the temporary list for events and companies
        model.updateFilteredEventList(PREDICATE_SHOW_NO_EVENTS);
        model.updateFilteredPersonList(PREDICATE_SHOW_NO_PERSONS);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, true, false);
    }
}
