package dom.user;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;

import dom.activity.Activity;
import dom.activity.ActivityCategory;
import dom.activity.ActivityRepository;
import dom.activity.ActivityType;
import dom.invite.JoinRequest;
import dom.invite.JoinRequestRepository;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
public class User implements Comparable<User>{
	@Persistent
	private Name name;
	private String selfDescription;
	@Persistent
	private PhoneNumber phoneNumber;
	@Persistent
	private Location location;
	
	@Inject
	private ActivityRepository activityRepository;
	@Inject
	private JoinRequestRepository requestRepository;
	
	public String title(){
		if(getName() == null){
			return "No Name (Error)";
		}
		
		return getName().getFirstName() + " " + getName().getLastName();
	}
	
	public Activity createActivity(
			@Named("Activity Category:") final ActivityCategory category,
			@Named("Activity Type:") final ActivityType name,
			@Named("Title:")final String title){
		final Activity activity = name.createActivity(title, this);
		
		return activity;
	}
	
	public Set<ActivityType> choices1CreateActivity(final ActivityCategory category){
		if(category == null){
			return Collections.emptySet();
		}
		return category.getActivityTypes();
	}

	@Disabled
	@Render(Type.EAGERLY)
	public List<Activity> getOwnedActivities() {
		return activityRepository.findAllActivitiesOwnedBy(this);
	}

	@Override
	public int compareTo(final User o) {
		return getName().compareTo(o.getName());
	}

	@Disabled
	@Render(Type.EAGERLY)
	public List<Activity> getJoinedActivities() {
		return requestRepository.findAllJoinedActivitiesForUser(this);
	}

	@Disabled
	@Render(Type.EAGERLY)
	public List<JoinRequest> getInvites() {
		return requestRepository.findAllInvitationsForUser(this);
	}
	
	@Column(allowsNull="true")
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}

	@Column(allowsNull="true")
	public String getSelfDescription() {
		return selfDescription;
	}

	public void setSelfDescription(final String selfDescription) {
		this.selfDescription = selfDescription;
	}

	@Column(allowsNull="true")
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}
	
	public User changePhoneNumber(final String newPhoneNumber){
		setPhoneNumber(new PhoneNumber(newPhoneNumber));
		
		return this;
	}
	
	private void setPhoneNumber(final PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(allowsNull="false")
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
}
