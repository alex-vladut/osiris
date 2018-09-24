package dom.invite;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.RecoverableException;
import org.apache.isis.applib.annotation.Disabled;

import dom.activity.Activity;
import dom.user.User;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
public class JoinRequest implements Comparable<JoinRequest>{
	public enum JoinRequestType{
		/** an Invite sent from owner **/
		INVITE,
		/** join request sent from an user **/
		REQUEST
	}
	private User user;
	private Activity activity;
	private JoinRequestType requestType;
	private boolean accepted;
	private boolean declined;
	
	public JoinRequest(
			final User user, 
			final Activity activity,
			final JoinRequestType requestType) {
		setUser(user);
		setActivity(activity);
		setRequestType(requestType);
	}

	@javax.jdo.annotations.Column(allowsNull="false")
	public User getUser() {
		return user;
	}

	private void setUser(final User user) {
		this.user = user;
	}

	@javax.jdo.annotations.Column(allowsNull="false")
	public Activity getActivity() {
		return activity;
	}

	private void setActivity(final Activity activity) {
		this.activity = activity;
	}

	public JoinRequest acceptInvite() {
		if(isActivityFull()){
			throw new RecoverableException("Maximum number of participants already achieved!");
		}
		setDeclined(false);
		setAccepted(true);
		
		return this;
	}
	
	private boolean isActivityFull(){
		int numberFreePlaces = getActivity().numberOfFreePlaces();
		if(numberFreePlaces <= 0){
			return true;
		}
		return false;
	}

	public JoinRequest declineInvite() {
		setAccepted(false);
		setDeclined(true);

		return this;
	}

	private void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	@Disabled
	@Column(allowsNull="true")
	public boolean isAccepted() {
		return accepted;
	}

	private void setDeclined(boolean declined) {
		this.declined = declined;
	}

	@Disabled
	@Column(allowsNull="true")
	public boolean isDeclined() {
		return declined;
	}

	@Disabled
	@Column(allowsNull="true")
	public JoinRequestType getRequestType() {
		return requestType;
	}
	
	private void setRequestType(final JoinRequestType requestType) {
		this.requestType = requestType;
	}
	
	@Override
	public int compareTo(final JoinRequest o) {
		if(getUser().compareTo(o.getUser()) != 0){
			return getUser().compareTo(o.getUser());
		}else{
			return getActivity().compareTo(o.getActivity());
		}
	}
	
}
