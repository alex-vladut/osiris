package fixture.titles;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import dom.user.Title;

public class TitleFixtures extends FixtureScript{

	public TitleFixtures() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		
		createTitle(executionContext, "Mr", "Man");
		createTitle(executionContext, "Mrs", "Woman");
		createTitle(executionContext, "Miss", "Woman");
	}
	
	private void createTitle(final ExecutionContext executionContext, final String titleName, final String iconName){
		final Title title = new Title(titleName, iconName);
		persist(title);
		
		executionContext.add(this, title);
	}
}
