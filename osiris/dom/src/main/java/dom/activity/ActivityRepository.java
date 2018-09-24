package dom.activity;

import java.util.List;

import dom.user.User;

public interface ActivityRepository {
	
	public List<Activity> findAllActivities();
	
	public List<Activity> findAllActivitiesOwnedBy(User owner);
}
