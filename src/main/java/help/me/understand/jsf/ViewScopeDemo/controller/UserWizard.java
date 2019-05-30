package help.me.understand.jsf.ViewScopeDemo.controller;

import help.me.understand.jsf.ViewScopeDemo.model.User;
import org.omnifaces.util.Faces;
import org.primefaces.event.FlowEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Map;

@Component
@Scope(scopeName = "view")
public class UserWizard implements Serializable {
    private User user = new User();

    private boolean skip;

    @PostConstruct
    public void init() {
        System.out.println("Wizard bean constructed...");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void save() {
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getFirstname());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        Map<String, Object> viewmap = Faces.getViewMap();
        HttpSession session = Faces.getSession();

        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }
}
