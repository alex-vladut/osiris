package dom.activity;

import dom.user.User;

public class ActivityRequest {

	private boolean accepted;
	private boolean declined;
	private User user;
	private Activity activity;

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isDeclined() {
		return declined;
	}

	public void setDeclined(boolean declined) {
		this.declined = declined;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
}
