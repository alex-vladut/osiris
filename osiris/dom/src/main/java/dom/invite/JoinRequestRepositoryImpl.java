package dom.invite;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Exploration;
import org.apache.isis.applib.annotation.Named;

import com.google.common.base.Predicate;

import dom.activity.Activity;
import dom.invite.JoinRequest.JoinRequestType;
import dom.user.User;

@DomainService(repositoryFor=JoinRequest.class)
@Named("Requests")
public class JoinRequestRepositoryImpl extends AbstractFactoryAndRepository implements JoinRequestRepository {

	@Override
	@Exploration
	public List<User> findParticipantsForActivity(final Activity activity){
		final List<JoinRequest> requests = allMatches(JoinRequest.class, new Predicate<JoinRequest>() {

			@Override
			public boolean apply(final JoinRequest input) {
				return (input.getActivity() == activity) && input.isAccepted();
			}
		});
		
		final List<User> participants = new ArrayList<User>();
		for(final JoinRequest joinRequest : requests){
			participants.add(joinRequest.getUser());
		}
		
		return participants;
	}
	
	@Override
	@Exploration
	public List<JoinRequest> findAllInvitationsForActivity(final Activity activity){
		return allMatches(JoinRequest.class, new Predicate<JoinRequest>() {

			@Override
			public boolean apply(final JoinRequest input) {
				return (input.getActivity() == activity) &&
						(input.getRequestType() == JoinRequestType.INVITE);
			}
		});
	}
	
	@Override
	@Exploration
	public List<Activity> findAllJoinedActivitiesForUser(final User user) {
		final List<JoinRequest> requests = allMatches(JoinRequest.class, new Predicate<JoinRequest>() {
			@Override
			public boolean apply(final JoinRequest input) {
				return (input.getUser() == user) && 
						(input.isAccepted());
			}
		});
		
		final List<Activity> result = new ArrayList<Activity>();
		for(final JoinRequest joinRequest : requests){
			result.add(joinRequest.getActivity());
		}
		return result;
	}
	
	@Override
	@Exploration
	public List<JoinRequest> findAllInvitationsForUser(final User user){
		return allMatches(JoinRequest.class, new Predicate<JoinRequest>() {
			
			@Override
			public boolean apply(final JoinRequest input) {
				return (input.getUser() == user) &&
						(input.getRequestType() == JoinRequestType.INVITE);
			}
		});
	}
	
	@Override
	@Exploration
	public int currentNumberOfParticipantsForActivity(final Activity activity) {
		return findParticipantsForActivity(activity).size();
	}
}
