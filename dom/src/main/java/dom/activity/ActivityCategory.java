package dom.activity;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bounded;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@Bounded
public class ActivityCategory {
	private String name;
	@Persistent
	private SortedSet<ActivityType> activityTypes = new TreeSet<ActivityType>();
	
	public ActivityCategory(final String name) {
		this.name = name;
	}
	
	public String title(){
		return name;
	}

	@javax.jdo.annotations.Column(allowsNull = "false", name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public SortedSet<ActivityType> getActivityTypes() {
		return activityTypes;
	}
	
	public ActivityType createActivityType(final String name){
		final ActivityType type = new ActivityType(name);
		type.setCategory(this);
		container.persist(type);
		getActivityTypes().add(type);
		
		return type;
	}

	@Inject
	private DomainObjectContainer container;
}
