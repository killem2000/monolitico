/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.allan.produto.controller.AbstractController;
import br.com.allan.produto.entity.Operador;
import br.com.allan.produto.service.OperadorService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Administrador
 */

@ManagedBean
@ViewScoped
public class OperadorController extends AbstractController{
    private static final long serialVersionUID = -4077541884240834242L;
   
    @Inject
    private OperadorService operadorService;
    private List<Operador> operadores;
    private Operador operador;
    private transient UIForm form;

    @PostConstruct
    public void init(){
        atualizarOperadores();
    }
    
    private void atualizarOperadores(){
        setOperadores(operadorService.getOperadores());
    }
    
    public void prepararAdicionar(ActionEvent actionEvent){
        if (getForm() != null){
            cleanSubmittedValues(getForm().findComponent("cod"));
            cleanSubmittedValues(getForm().findComponent("codint"));
            cleanSubmittedValues(getForm().findComponent("nome"));
            cleanSubmittedValues(getForm().findComponent("senha"));
            //cleanSubmittedValues(getForm().findComponent("emausu"));
            //cleanSubmittedValues(getForm().findComponent("telusu"));
            //cleanSubmittedValues(getForm().findComponent("celusu"));            
        }
        
        setOperador(new Operador()); 
    }
    
    public void prepararAlterar(Operador u){
        cleanSubmittedValues(getForm().findComponent("cod"));
        cleanSubmittedValues(getForm().findComponent("codint"));
        cleanSubmittedValues(getForm().findComponent("nome"));
        cleanSubmittedValues(getForm().findComponent("senha"));
        
        setOperador(u);
    }
    
    public void excluir(ActionEvent actionEvent){       
        operadorService.excluirOperador(getOperador());        
        atualizarOperadores();
        
        adicionarMensagemInformacao("Registro excluído com sucesso");                     
    }
    
    public void salvar(ActionEvent actionEvent){                                             
        
        if ((operadorService.nomeJaExiste(this.operador.getNome()) != null) &&
            !(operadorService.nomeJaExiste(this.operador.getNome()).equals(this.operador))){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome já cadastrado para outro usuário","");                  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
            return;
        }   
        
        if ((operadorService.codigoJaExiste(this.operador.getCodInterno()) != null) &&
            !(operadorService.codigoJaExiste(this.operador.getCodInterno()).equals(this.operador))){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Código acesso frente já cadastrado para outro usuário","");                  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
            return;
        }                           
        
        operadorService.salvarOperador(getOperador());
        atualizarOperadores();                        
        adicionarMensagemInformacao("Registro salvo com sucesso");                
        RequestContext context = RequestContext.getCurrentInstance();  
        context.addCallbackParam("valok", true);                  
    }

    public void validaTresDigitos(FacesContext context, UIComponent validar, Object valor){
        String codigo = (String) valor;
        
        if (codigo.length() != 3){
            ((UIInput) validar).setValid(false);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "O código deve ter 3 dígitos", null);
                context.addMessage(validar.getClientId(context), message);
        }
    }
    
    public List<Operador> getOperadores() {
        return operadores;
    }
    
    public void setOperadores(List<Operador> operadores) {
        this.operadores = operadores;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }        

    public UIForm getForm() {
        return form;
    }

    public void setForm(UIForm form) {
        this.form = form;
    }

}
