package dom.user;


@javax.jdo.annotations.PersistenceCapable
public class Name implements Comparable<Name>{
	private Title title;
	private String firstName;
	private String lastName;
	
	public Name(
			final Title title, 
			final String firstName, 
			final String lastName) {
		setTitle(title);
		setFirstName(firstName);
		setLastName(lastName);
	}
	
	public String title(){
		return getTitle().getName() + " " + getFirstName() + " " +
					getLastName();
	}
	
	public String iconName(){
		return getTitle().getIconName();
	}

	@javax.jdo.annotations.Column(allowsNull="true")
	public String getFirstName() {
		return firstName;
	}

	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@javax.jdo.annotations.Column(allowsNull="true")
	public String getLastName() {
		return lastName;
	}

	private void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@javax.jdo.annotations.Column(allowsNull="true")
	public Title getTitle() {
		return title;
	}

	private void setTitle(Title title) {
		this.title = title;
	}

	@Override
	public int compareTo(Name o) {
		if(getLastName().compareTo(o.getLastName()) != 0){
			return getLastName().compareTo(o.getLastName());
		}else{
			return getFirstName().compareTo(o.getLastName());
		}
	}

}
