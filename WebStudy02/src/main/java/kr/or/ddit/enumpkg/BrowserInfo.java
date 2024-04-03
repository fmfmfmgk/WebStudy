package kr.or.ddit.enumpkg;

public enum BrowserInfo {
	EDG("엣지"), WHALE("웨일"), CHROME("크롬"), SAFARI("사파리"), OTHERS("기타");
	
	
	private BrowserInfo(String browserName) {
		this.browserName = browserName;
	}

	private String browserName;
	
	public String getBrowserName() {
		return browserName;
	}
	
	public static BrowserInfo findBrowser(String userAgent) {
		BrowserInfo finded = OTHERS;
		for (BrowserInfo single : values()) {
			if(userAgent.toUpperCase().contains(single.name())){
				finded = single;
				break;
			}
		}
		return finded;
	}
	
	public static String findedBrowserName(String userAgent) {
		return findBrowser(userAgent).getBrowserName();
	}
}
