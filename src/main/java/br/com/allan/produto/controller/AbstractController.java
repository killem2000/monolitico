package br.com.allan.produto.controller;

import br.com.allan.produto.entity.Operador;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jeanne
 */
public abstract class AbstractController implements Serializable {
    private static final long serialVersionUID = -3230015609202779140L;

    public Operador obterUsuarioLogado() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        SessionController currentUser = (SessionController) session.getAttribute("sessionController"); 
        
        return currentUser.getUsuario();
    }    

    public void adicionarMensagemInformacao(String mensagem) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void adicionarMensagemErro(String mensagem) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void adicionarMensagemAviso(String mensagem) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void cleanSubmittedValues(UIComponent component) {
        if (component instanceof EditableValueHolder) {  
            EditableValueHolder evh = (EditableValueHolder) component;  
            evh.setSubmittedValue(null);  
            evh.setValue(null);  
            evh.setLocalValueSet(false);  
            evh.setValid(true);  
        }  
        if (component.getChildCount() > 0) {  
            for (UIComponent child : component.getChildren()) {  
                cleanSubmittedValues(child);  
            }  
        }
    }

    /**
     * TODO Ver classe de util do faces para colocar esses dois metodos (ex.
     * facesutil)
     */
    //Adiciona objeto na sessao
    protected void adionarObjetoSessao(String atributo, Object objeto) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
        session.setAttribute(atributo, objeto);
    }

    //Recupera objeto da sessao
    protected Object obterObjetoSessao(String objeto) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
        return session.getAttribute(objeto);
    }
    
    public void validaNumeroMaiorQueZero(FacesContext context, UIComponent validar, Object valor){              
        try{
            double vlr = new Double(valor.toString());
            
            if (vlr == 0){
                ((UIInput) validar).setValid(false);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Valor não pode ser zero","");
                context.addMessage(validar.getClientId(context), message);
            }
            
            if (vlr < 0){
                ((UIInput) validar).setValid(false);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Valor não pode ser negativo","");
                context.addMessage(validar.getClientId(context), message);
            }
       }catch (Exception e){
          ((UIInput) validar).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Formato de número inválido", "");
            context.addMessage(validar.getClientId(context), message); 
       } 
    }
    
    public void validaNumeroMaiorOuIgualZero(FacesContext context, UIComponent validar, Object valor){              
        try{
            double vlr = new Double(valor.toString());
            
            /*if (grao == 0){
                ((UIInput) validar).setValid(false);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Área não pode ser zero","");
                context.addMessage(validar.getClientId(context), message);
            }*/
            
            if (vlr < 0){
                ((UIInput) validar).setValid(false);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Valor não pode ser negativo","");
                context.addMessage(validar.getClientId(context), message);
            }
       }catch (Exception e){
          ((UIInput) validar).setValid(false);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Formato de número inválido", "");
            context.addMessage(validar.getClientId(context), message); 
       } 
    }
}
