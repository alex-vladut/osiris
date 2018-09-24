package dom.user;

import java.io.Serializable;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Immutable;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@Immutable
public class Title implements Serializable {
	private static final long serialVersionUID = -9208302776105808832L;
	@Persistent(mappedBy = "name")
	private String name;
	@Persistent(mappedBy = "iconName")
	private String iconName;
	
	public Title(final String name, final String iconName) {
		this.name = name;
		this.iconName = iconName;
	}
	
	public String title(){
		return getName();
	}
	
	public String iconName(){
		return getIconName();
	}
	
	@javax.jdo.annotations.Column(allowsNull="true")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@javax.jdo.annotations.Column(allowsNull="true")
	public String getIconName() {
		return iconName;
	}
	
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
}
