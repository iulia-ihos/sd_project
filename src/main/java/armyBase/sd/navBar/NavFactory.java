package armyBase.sd.navBar;

import java.util.List;

import org.springframework.stereotype.Component;


public interface NavFactory {
	
	enum PageName {
		BASE,SOLDIER,OPERATION,TRAINING
	}
	
	
	public interface TopNavFactory extends NavFactory{
		TopNavFactory addPage(PageName page);
		
		List<NavItem> build();
	}

}
