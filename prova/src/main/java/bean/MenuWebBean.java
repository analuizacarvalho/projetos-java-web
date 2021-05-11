package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "menuWebBean")
public class MenuWebBean {
		
	public String redirecionaCliente() {
		return "cliente.xhtml?faces-redirect=true";
	}
	
}
