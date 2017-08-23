package com.primefaces.hibernate.bean;

import com.primefaces.hibernate.criteria.dao.EmployeesDAO;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.primefaces.hibernate.criteria.dao.UsersDAO;
import com.primefaces.hibernate.model.Employees;
import com.primefaces.hibernate.model.Roles;
import com.primefaces.hibernate.model.Users;
import com.primefaces.hibernate.util.HttpSessionUtil;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.faces.bean.ManagedProperty;
import org.apache.log4j.Logger;


 

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(LoginBean.class);

    private static final long serialVersionUID = 1L;

    private String message;
    private Users user = new Users();
    private Employees employee = new Employees();

    private Roles roleAdmin = Roles.Administrator;
    private Roles roleUser = Roles.User;

    private boolean loggedIn;

    @ManagedProperty(value = "#{resourceBundleBean}")
    private ResourceBundleBean resourceBundleBean;

    public LoginBean() {
        reset();
    }

    private void reset() {
        loggedIn = false;
        LOG.info("LoginBean");
    }

    public String login() throws IOException, NoSuchAlgorithmException {
        LOG.info("ENTRATOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO LOGGGGINFO ");
        UsersDAO usersDAO = new UsersDAO();
        user = usersDAO.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (null != user) {
            LOG.info("utente esiste ");
            EmployeesDAO employeesDAO = new EmployeesDAO();
            employee = employeesDAO.findByUser(user);

            HttpSession session = HttpSessionUtil.getSession(true);

            session.setAttribute("user", user);

            loggedIn = true;
            return "success";

        } else {
            user = new Users();
            LOG.info("errore nuovo utente");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    ResourceBundleBean.getResourceBundleString("LoginBean.error.login.1"),
                    ResourceBundleBean.getResourceBundleString("LoginBean.error.login.2"));
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }

    public void signOut() throws IOException {
        HttpSession session = HttpSessionUtil.getSession(false);
        session.removeAttribute("user");
        session.invalidate();
        user = new Users();

        HttpSessionUtil.redirect(HttpSessionUtil.getRequest().getContextPath() + "/ui/home.jsf");
    }
     
    
    public Roles getRoleAdmin() {
        return roleAdmin;
    }

    public void setRoleAdmin(Roles roleAdmin) {
        this.roleAdmin = roleAdmin;
    }

    public Roles getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(Roles roleUser) {
        this.roleUser = roleUser;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResourceBundleBean getResourceBundleBean() {
        return resourceBundleBean;
    }

    public void setResourceBundleBean(ResourceBundleBean resourceBundleBean) {
        this.resourceBundleBean = resourceBundleBean;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
