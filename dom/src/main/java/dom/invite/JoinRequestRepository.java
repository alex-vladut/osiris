package dom.invite;

import java.util.List;

import dom.activity.Activity;
import dom.user.User;

public interface JoinRequestRepository {

	List<User> findParticipantsForActivity(Activity activity);

	List<JoinRequest> findAllInvitationsForActivity(Activity activity);

	List<Activity> findAllJoinedActivitiesForUser(User user);

	List<JoinRequest> findAllInvitationsForUser(User user);

	int currentNumberOfParticipantsForActivity(Activity activity);

}
