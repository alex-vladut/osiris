package fixture.titles;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Prototype;
import org.apache.isis.applib.fixturescripts.FixtureResult;
import org.apache.isis.applib.fixturescripts.FixtureScripts;

@Named("Prototyping")
@DomainService(menuOrder = "20")
public class TitlesFixturesService extends FixtureScripts {

	public TitlesFixturesService() {
		super("fixture.titles");
	}
	
	@Prototype
    @MemberOrder(sequence="20")
    public Object installFixturesAndReturnFirst() {
        final List<FixtureResult> run = findFixtureScriptFor(TitleFixtures.class).run(null);
        return run.get(0).getObject();
    }
}
