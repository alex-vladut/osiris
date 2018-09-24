package dom.activity;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.Exploration;
import org.apache.isis.applib.annotation.NotPersisted;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;

import dom.invite.JoinRequest;
import dom.invite.JoinRequest.JoinRequestType;
import dom.invite.JoinRequestRepository;
import dom.user.Location;
import dom.user.User;
import dom.user.UserRepository;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
public class Activity implements Comparable<Activity> {
	@Persistent
	private ActivityType activityType;
	private String title;
	@Persistent
	private Location location;
	@Persistent
	private TimePeriod period;
	@Persistent
	private User owner;
	private int maximumNumberOfParticipants;
	private String description;
	private boolean autoAccept;
	
	@Inject
	private UserRepository users;
	@Inject
	private JoinRequestRepository requestRepository;
	@Inject
    private DomainObjectContainer container;
	
	public Activity(final ActivityType activityName) {
		this.activityType = activityName;
	}
	
	public String title(){
		return title;
	}

	@javax.jdo.annotations.Column(allowsNull = "true")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@NotPersisted
	public Location getLocation() {
		return location;
	}

	private void setLocation(final Location location) {
		this.location = location;
	}
	
	public Location changeLocation(final String locationName){
		final Location newLocation = new Location(locationName);
		setLocation(newLocation);
		
		return newLocation;
	}
	
	@Column(allowsNull = "true")
	public TimePeriod getPeriod(){
		return this.period;
	}
	
	private void setPeriod(final TimePeriod period) {
		this.period = period;
	}
	
	public TimePeriod changePeriod(
			final Date startDate, 
			final int hourStart, 
			final int minutesStart,
			final int hourEnd,
			final int minutesEnd){
		final TimePeriod newPeriod = new TimePeriod(startDate, hourStart, minutesStart, hourStart, minutesEnd);
		setPeriod(newPeriod);
		
		return newPeriod;
	}
	
	@Column(allowsNull = "false")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int compareTo(final Activity activity) {
		if(activity == null){
			return -1;
		}else if(activity.getTitle() == null){
			return -1;
		}else if(getTitle() == null){
			return 1;
		}
		
		return this.getTitle().compareTo(activity.getTitle());
	}

	@javax.jdo.annotations.Column(allowsNull = "true")
	public int getMaximumNumberOfParticipants() {
		return maximumNumberOfParticipants;
	}

	public void setMaximumNumberOfParticipants(int numberParticipants) {
		this.maximumNumberOfParticipants = numberParticipants;
	}

	@Disabled
	public List<User> getParticipants() {
		return requestRepository.findParticipantsForActivity(this);
	}

	@javax.jdo.annotations.Column(allowsNull = "true")
	public ActivityType getActivityType() {
		return activityType;
	}
	@Disabled
	public void setActivityType(final ActivityType activityType) {
		this.activityType = activityType;
	}
	
	@Disabled
	@Render(Type.EAGERLY)
	public List<JoinRequest> getInvites() {
		return requestRepository.findAllInvitationsForActivity(this);
	}

	public JoinRequest createNewJoinRequest(final  User invitedUser){
		final JoinRequest invite = new JoinRequest(invitedUser, this, JoinRequestType.INVITE);
		
		container.persist(invite);
		
		return invite;
	}
	
	public List<User> choices0CreateNewJoinRequest(){
		return users.findAllUsers();
	}
	
	@javax.jdo.annotations.Column(allowsNull = "true")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Exploration
	public int numberOfFreePlaces(){
		final int currentNoParticipants = requestRepository.currentNumberOfParticipantsForActivity(this);
		return getMaximumNumberOfParticipants() - currentNoParticipants;
	}

	public boolean isAutoAccept() {
		return autoAccept;
	}

	public void setAutoAccept(boolean autoAccept) {
		this.autoAccept = autoAccept;
	}
}
