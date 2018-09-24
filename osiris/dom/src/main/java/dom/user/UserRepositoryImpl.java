package dom.user;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Named;

@DomainService(menuOrder = "20", repositoryFor = User.class)
@Named("Users")
public class UserRepositoryImpl extends AbstractFactoryAndRepository implements UserRepository{
	
	@Override
	public User createUser(
			@Named("Title:")final Title title,
			@Named("First Name")final String firstName,
			@Named("Last Name")final String lastName){
		final User user = newTransientInstance(User.class);
		final Name name = new Name(title, firstName, lastName);
		user.changePhoneNumber("000");
		user.setName(name);
		
		persist(user);
		
		return user;
	}
	
	public List<Title> choices0CreateUser(){
		return allInstances(Title.class);
	}
	
	@Override
	public List<User> findAllUsers(){
		return allInstances(User.class);
	}
	
}
