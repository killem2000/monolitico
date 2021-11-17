/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.allan.produto.filter;

import br.com.allan.produto.controller.SessionController;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;
 
public class LoginListener implements PhaseListener {
    
    private static final long serialVersionUID = 4818954213499653084L;
 
   @Override
    public void afterPhase(PhaseEvent event) {
 
        FacesContext facesContext = event.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();       
        
        //HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();                 
        
        boolean noLogin = ((currentPage.lastIndexOf("login.xhtml") > -1) ||                           
                           (currentPage.lastIndexOf("sisEsqueciSenha.xhtml") > -1));
        
                
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        SessionController currentUser = (SessionController) session.getAttribute("sessionController");                       
                         
        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();                                
        
        if ((noLogin) && ((currentUser != null) && (currentUser.getLogin() != null))){
            nh.handleNavigation(facesContext, null, "menu?faces-redirect=true");                         
            return;
        }
        
        if (!noLogin && ((currentUser == null) || (currentUser.getUsuario() == null))) {            
            nh.handleNavigation(facesContext, null, "login");           
        }
    }
 
    @Override
    public void beforePhase(PhaseEvent event) {         
    }
 
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}