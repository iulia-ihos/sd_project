package armyBase.sd.navBar;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import armyBase.sd.navBar.NavFactory.TopNavFactory;

@Component
public class TopNavFactoryImplementation implements TopNavFactory {

	private PageName pageName;
	
	@Override
	public TopNavFactory addPage(PageName pageName) {
		this.pageName = pageName;
		return this;	
	}

	@Override
	public List<NavItem> build() {
		List<NavItem> navItems = new ArrayList<>();
		navItems.add(ImmutableNavItem.builder()
				.title("Soldiers")
				.url("/soldier")
				.active(pageName == PageName.SOLDIER).build());
		navItems.add(ImmutableNavItem.builder()
				.title("Military Bases")
				.url("/base")
				.active(pageName == PageName.BASE).build());
		navItems.add(ImmutableNavItem.builder()
				.title("Operations")
				.url("/operation")
				.active(pageName == PageName.OPERATION).build());
		navItems.add(ImmutableNavItem.builder()
				.title("Training")
				.url("/training")
				.active(pageName == PageName.TRAINING).build());
		return navItems;
	}

}
