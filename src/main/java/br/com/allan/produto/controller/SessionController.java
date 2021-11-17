/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.allan.produto.controller;

import br.com.allan.produto.entity.Operador;
import br.com.allan.produto.service.OperadorService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Administrador
 */
@ManagedBean
@SessionScoped
public class SessionController extends AbstractController {
    private static final long serialVersionUID = 5581037080408358418L;
        
    private Operador usuario;
    private String login;
    private String senha;

    @Inject
    private OperadorService operadorService;
            
    public String logar(){
        setUsuario(operadorService.getOperador(login, senha));
        
        if (getUsuario() == null){
            adicionarMensagemErro("Usuário e senha inválidos");
            return "login";
        } else {
            return "menu?faces-redirect=true";
        }        
    }
    
    public String logout(){        
        setUsuario(null);
        setLogin(null);
        setSenha(null);
        FacesContext context = FacesContext.getCurrentInstance();                 
        context.getExternalContext().getSessionMap().remove("sessionController");             
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();                            
        
        return "login.xhtml?faces-redirect=true";
    }
    
    public void carregaBean(ComponentSystemEvent e) {                       
       HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
       //System.out.println(request.getParameter("login")); 
       //System.out.println(request.getParameter("senha"));
             
       this.setLogin(request.getParameter("login"));
       this.setSenha(request.getParameter("senha"));
       
       /*if (this.getLogin() != null){
          if (logar().equals("admPainelAdm?faces-redirect=true")){
              Funcoes.redirect("/faces/admPainelAdm.xhtml");
          } 
       } */
    }
    
    public Operador getUsuario() {
        return usuario;
    }

    public void setUsuario(Operador usuario) {
        this.usuario = usuario;
    }            

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
