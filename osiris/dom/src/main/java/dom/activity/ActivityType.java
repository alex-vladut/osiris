package dom.activity;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bounded;

import dom.user.User;
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@Bounded
public class ActivityType implements Comparable<ActivityType> {
	@javax.inject.Inject
    private DomainObjectContainer container;
	@Persistent(mappedBy = "name")
	private ActivityCategory category;
	private String name;
	
	public ActivityType(final String name) {
		this.name = name;
	}
	
	public String title(){
		return name;
	}
	
	@javax.jdo.annotations.Column(allowsNull = "true")
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	@Override
	public int compareTo(final ActivityType o) {
		return getName().compareTo(o.getName());
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
	public ActivityCategory getCategory() {
		return category;
	}
	
	public void setCategory(ActivityCategory category) {
		this.category = category;
	}
	
	public Activity createActivity(final String activityTile, final User owner){
		final Activity activity = new Activity(this);
		activity.setTitle(activityTile);
		activity.setOwner(owner);
		
		container.persist(activity);
	
		return activity;
	}
}
