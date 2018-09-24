package dom.activity;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Exploration;
import org.apache.isis.applib.annotation.Named;

import com.google.common.base.Predicate;

import dom.user.User;

@DomainService(menuOrder="30", repositoryFor=Activity.class)
@Named("Activities")
public class ActivityRepositoryImpl extends AbstractFactoryAndRepository implements ActivityRepository{
	
	@Override
	public List<Activity> findAllActivities(){
		return allInstances(Activity.class);
	}

	@Override
	@Exploration
	public List<Activity> findAllActivitiesOwnedBy(final User owner) {
		return allMatches(Activity.class, new Predicate<Activity>() {

			@Override
			public boolean apply(final Activity activity) {
				return activity.getOwner() == owner;
			}
		});
	}
}
