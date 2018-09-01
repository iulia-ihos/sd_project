package armyBase.sd.navBar;

import org.immutables.value.Value;

@Value.Immutable
public interface NavItem {
	String getUrl();
	boolean getActive();
	String getTitle();
}
