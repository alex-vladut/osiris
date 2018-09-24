package dom.user;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.RegEx;

@PersistenceCapable
public class PhoneNumber implements Comparable<PhoneNumber> {
	private String value;
	
	public PhoneNumber(@RegEx(validation = "[0-9]*")final String value) {
		setValue(value);
	}
	
	public String title(){
		return getValue();
	}
	
	private void setValue(final String value) {
		this.value = value;
	}
	
	@Column(allowsNull = "true")
	public String getValue() {
		return value;
	}

	@Override
	public int compareTo(final PhoneNumber o) {
		return getValue().compareTo(o.getValue());
	}
}
