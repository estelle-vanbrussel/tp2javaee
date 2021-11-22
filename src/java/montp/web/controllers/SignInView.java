package montp.web.controllers;

import montp.data.model.security.Group;
import montp.data.model.security.User;
import montp.locale.Messages;
import montp.services.UserService;
import montp.web.FacesTools;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named
public class SignInView implements Serializable {
    @Inject
    private UserService userService;
    @Inject private Messages messages;
    User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void signIn(){
        Group groupUser = userService.getGroup("USER");
        List<Group> groupes = new ArrayList<>();
        groupes.add(groupUser);
        user.setGroups(groupes);
        userService.insert(user);
        FacesTools.addMessage(FacesMessage.SEVERITY_INFO, messages.get("signin.success"));
        FacesTools.redirect("login");
    }
}
