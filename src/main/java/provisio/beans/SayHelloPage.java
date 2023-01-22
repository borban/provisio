package provisio.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class SayHelloPage {
	String name = "Default Name";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
