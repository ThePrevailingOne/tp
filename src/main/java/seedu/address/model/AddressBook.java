package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.entry.Company;
import seedu.address.model.entry.Event;
import seedu.address.model.entry.Person;
import seedu.address.model.entry.UniqueEntryList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameEntry comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    /**
     * {@code Comparator} that compares {@code Entry}s.
     */
    private static final Comparator<Person> COMPARATOR_PERSON_BY_NAME = (p1, p2) -> p1.getName().toString()
                                                        .compareTo(p2.getName().toString());
    private static final Comparator<Company> COMPARATOR_COMPANY_BY_NAME = (c1, c2) -> c1.getName().toString()
                                                        .compareTo(c2.getName().toString());
    private static final Comparator<Event> COMPARATOR_EVENT_BY_DATE = (e1, e2) -> e1.getDate().compareTo(e2.getDate());

    private final UniqueEntryList<Person> persons;
    private final UniqueEntryList<Company> companies;
    private final UniqueEntryList<Event> events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueEntryList<>();
        companies = new UniqueEntryList<>();
        events = new UniqueEntryList<>();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setEntries(persons);
    }

    /**
     * Replaces the contents of the companies list with {@code companies}.
     * {@code companies} must not contain duplicate companies.
     */
    public void setCompanies(List<Company> companies) {
        this.companies.setEntries(companies);
    }

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        this.events.setEntries(events);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setCompanies(newData.getCompanyList());
        setEvents(newData.getEventList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setEntry(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// company-level operations

    /**
     * Returns true if a company with the same identity as {@code company} exists in the address book.
     */
    public boolean hasCompany(Company company) {
        requireNonNull(company);
        return companies.contains(company);
    }

    /**
     * Adds a company to the address book.
     * The company must not already exist in the address book.
     */
    public void addCompany(Company c) {
        companies.add(c);
    }

    /**
     * Replaces the given company {@code target} in the list with {@code editedCompany}.
     * {@code target} must exist in the address book.
     * The company identity of {@code editedCompany} must not be the same as another existing company in the
     * address book.
     */
    public void setCompany(Company target, Company editedCompany) {
        requireNonNull(editedCompany);

        companies.setEntry(target, editedCompany);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeCompany(Company key) {
        companies.remove(key);
    }

    //// event-level operations

    /**
     * Returns true if an event with the same identity as {@code event} exists in the address book.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds an event to the address book.
     * The event must not already exist in the address book.
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);

        events.setEntry(target, editedEvent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    /**
     * Returns sorted list of all {@code Person}s in the address book.
     */
    public ObservableList<Person> getSortedPersons(boolean ascending) {
        if (ascending) {
            return persons.asSortedList(COMPARATOR_PERSON_BY_NAME);
        } else {
            return persons.asReversedSortedList(COMPARATOR_PERSON_BY_NAME);
        }
    }

    /**
     * Returns sorted list of all {@code Company}s in the address book.
     */
    public ObservableList<Company> getSortedCompanies(boolean ascending) {
        if (ascending) {
            return companies.asSortedList(COMPARATOR_COMPANY_BY_NAME);
        } else {
            return companies.asReversedSortedList(COMPARATOR_COMPANY_BY_NAME);
        }
    }

    /**
     * Returns sorted list of all {@code Event}s in the address book.
     */
    public ObservableList<Event> getSortedEvents(boolean ascending) {
        if (ascending) {
            return events.asSortedList(COMPARATOR_EVENT_BY_DATE);
        } else {
            return events.asReversedSortedList(COMPARATOR_EVENT_BY_DATE);
        }
    }

    @Override
    public void updateCompanyNames(String oldName, String newName) {
        persons.updateCompanyNames(oldName, newName);
        events.updateCompanyNames(oldName, newName);
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Company> getCompanyList() {
        return companies.asUnmodifiableObservableList();
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons,"
                + companies.asUnmodifiableObservableList().size() + " companies, "
                + events.asUnmodifiableObservableList().size() + " events";
        // TODO: refine later
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons)
                && companies.equals(otherAddressBook.companies)
                && events.equals(otherAddressBook.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, companies, events);
    }

}
