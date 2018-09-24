package fixture.activities;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Prototype;
import org.apache.isis.applib.fixturescripts.FixtureResult;
import org.apache.isis.applib.fixturescripts.FixtureScripts;

@DomainService(menuOrder = "20")
public class ActivityTypesAndCategoriesFixturesService extends FixtureScripts {

	public ActivityTypesAndCategoriesFixturesService() {
		super("fixture.activities");
	}
	
	@Prototype
    @MemberOrder(sequence="20")
    public Object installFixturesAndReturnFirst() {
        final List<FixtureResult> run = findFixtureScriptFor(ActivityTypesAndCategoriesFixtures.class).run(null);
        
        return run.get(0).getObject();
    }
}
