package dom.user;

import java.util.List;

public interface UserRepository {

	public User createUser(
			Title title,
			String firstName,
			String lastName);
	
	public List<User> findAllUsers();
}
