package dom.user;

import java.io.Serializable;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Location implements Serializable {
	private static final long serialVersionUID = 1989998892444086851L;
	private String value;

	public Location(final String value) {
		setValue(value);
	}
	
	private void setValue(final String value) {
		this.value = value;
	}
	
	@Column(allowsNull = "false")
	public String getValue() {
		return value;
	}

}
