package fixture.activities;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import dom.activity.ActivityCategory;
import dom.activity.ActivityType;

public class ActivityTypesAndCategoriesFixtures extends FixtureScript {

	public ActivityTypesAndCategoriesFixtures() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}
	
	@Override
	protected void execute(final ExecutionContext executionContext) {
		final ActivityCategory category = createCategory(executionContext, "Sport");
		createActivityType(executionContext, category, "Tennis");
		createActivityType(executionContext, category, "Ski");
		final ActivityCategory category2 = createCategory(executionContext, "Dancing");
		createActivityType(executionContext, category2, "Zumba");
		createActivityType(executionContext, category2, "Salsa");
	}

	private void createActivityType(final ExecutionContext executionContext, final ActivityCategory category, final String name) {
		final ActivityType type = category.createActivityType(name);
		executionContext.add(this, type);
	}

	private ActivityCategory createCategory(ExecutionContext executionContext, String name) {
		final ActivityCategory category = new ActivityCategory(name);
		persist(category);
		executionContext.add(this, category);
		
		return category;
	}

}
