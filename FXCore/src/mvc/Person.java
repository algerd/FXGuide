
package mvc;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;

/*
The Person is a model.

The Person class contains the code for the domain data and the business rules. 
In real life you might want to separate the two into multiple classes.

The Person class declares an AgeCategory enum to represents different ages.

The person ID, first name, last name, and birth date are represented by JavaFX properties. The personId
property is declared read-only and it is autogenerated. Relevant setter and getter methods are provided for
these properties.

The isValidBirthDate() and isValidPerson() methods are included to perform domain-specific validations. 
The getAgeCategory() method belongs to the Person class as it computes the age category of a
person based on his birth date. I have made up some date ranges to divide the age of a person into different
categories. You may be tempted to add this method to the view. However, you would then need to duplicate
the logic inside this method for each view. The method uses the model data and computes a value. It knows
nothing about views, so it belongs to the model, not to the view.

The save() method saves the personal data. The save method is trivial; it simply displays a message
on the standard output if the personal data are valid. In a real world application, it would save the data to a
database or a file.
*/
public class Person {
	// An enum for age categories
	public enum AgeCategory {
		BABY, CHILD, TEEN, ADULT, SENIOR, UNKNOWN
	};

	private final ReadOnlyIntegerWrapper personId = new ReadOnlyIntegerWrapper(this, "personId", personSequence.incrementAndGet());
	private final StringProperty firstName = new SimpleStringProperty(this, "firstName", null);
	private final StringProperty lastName = new SimpleStringProperty(this, "lastName", null);
	private final ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>(this, "birthDate", null);

	// Keeps track of last generated person id
	private static AtomicInteger personSequence = new AtomicInteger(0);

	public Person() {
		this(null, null, null);
	}

	public Person(String firstName, String lastName, LocalDate birthDate) {
		this.firstName.set(firstName);
		this.lastName.set(lastName);
		this.birthDate.set(birthDate);
	}

	/* personId Property */
	public final int getPersonId() {
		return personId.get();
	}

	public final ReadOnlyIntegerProperty personIdProperty() {
		return personId.getReadOnlyProperty();
	}

	/* firstName Property */
	public final String getFirstName() {
		return firstName.get();
	}

	public final void setFirstName(String firstName) {
		firstNameProperty().set(firstName);
	}

	public final StringProperty firstNameProperty() {
		return firstName;
	}

	/* lastName Property */
	public final String getLastName() {
		return lastName.get();
	}

	public final void setLastName(String lastName) {
		lastNameProperty().set(lastName);
	}

	public final StringProperty lastNameProperty() {
		return lastName;
	}

	/* birthDate Property */
	public final LocalDate getBirthDate() {
		return birthDate.get();
	}

	public final void setBirthDate(LocalDate birthDate) {
		birthDateProperty().set(birthDate);
	}

	public final ObjectProperty<LocalDate> birthDateProperty() {
		return birthDate;
	}

	/* Domain specific business rules */
	public boolean isValidBirthDate(LocalDate bdate) {
		return isValidBirthDate(bdate, new ArrayList<>());
	}
	
	/* Domain specific business rules */
	public boolean isValidBirthDate(LocalDate bdate, List<String> errorList) {
		if (bdate == null) {
			return true;
		}
		// Birth date cannot be in the future
		if (bdate.isAfter(LocalDate.now())) {
			errorList.add("Birth date must not be in future.");
			return false;
		}
		return true;
	}

	/* Domain specific business rules */
	public boolean isValidPerson(List<String> errorList) {
		return isValidPerson(this, errorList);
	}

	/* Domain specific business rules */
	public boolean isValidPerson(Person p, List<String> errorList) {
		boolean isValid = true;

		String fn = p.firstName.get();
		if (fn == null || fn.trim().length() == 0) {
			errorList.add("First name must contain minimum one character.");
			isValid = false;
		}

		String ln = p.lastName.get();
		if (ln == null || ln.trim().length() == 0) {
			errorList.add("Last name must contain minimum one character.");
			isValid = false;
		}

		if (!isValidBirthDate(this.birthDate.get(), errorList)) {
			isValid = false;
		}
		return isValid;
	}

	/* Domain specific business rules */
	public AgeCategory getAgeCategory() {
		if (birthDate.get() == null) {
			return AgeCategory.UNKNOWN;
		}
		
		long years = ChronoUnit.YEARS.between(birthDate.get(), LocalDate.now());
		if (years >= 0 && years < 2) {
			return AgeCategory.BABY;
		}
		else if (years >= 2 && years < 13) {
			return AgeCategory.CHILD;
		}
		else if (years >= 13 && years <= 19) {
			return AgeCategory.TEEN;
		} 
		else if (years > 19 && years <= 50) {
			return AgeCategory.ADULT;
		} 
		else if (years > 50) {
			return AgeCategory.SENIOR;
		} else {
			return AgeCategory.UNKNOWN;
		}
	}

	/* Domain specific business rules */
	public boolean save(List<String> errorList) {
		boolean isSaved = false;
		if (isValidPerson(errorList)) {
			System.out.println("Saved " + this.toString());
			isSaved = true;
		}	
		return isSaved;
	}

	@Override
	public String toString() {
		return "[personId=" + personId.get() + 
		       ", firstName=" + firstName.get() + 
		       ", lastName=" + lastName.get() + 
		       ", birthDate=" + birthDate.get() + "]";
	}
}

