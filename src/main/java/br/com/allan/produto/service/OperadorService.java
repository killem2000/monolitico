/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.allan.produto.service;
import br.com.allan.produto.entity.Operador;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.persistence.Query;
import javax.transaction.SystemException;

/**
 *
 * @author Administrador
 */
@Named
public class OperadorService extends AbstractService{
    private static final long serialVersionUID = 332636458602642749L;
    
    public Operador getOperador(String login, String senha){
        Query query = em.createQuery("SELECT o FROM Operador o where nome = :nome AND senha = :senha");
        query.setParameter("nome", login);
        query.setParameter("senha", senha);
        
        if (query.getResultList().isEmpty()){
            return null;
        } else {
            return (Operador) query.getResultList().get(0);
        }
    }

    public Operador getOperadorPorCodigo(String codOperador) {
        Query query = em.createNamedQuery("Operador.findByCodInterno");
        query.setParameter("codInterno", codOperador);
        
        if (query.getResultList().isEmpty()){
            return null;
        } else {
            return (Operador) query.getResultList().get(0);
        }
    }

    public List<Operador> getOperadores() {
        Query query = em.createNamedQuery("Operador.findAll");
        return query.getResultList();
    }
    
    public void excluirOperador(Operador operador) {
        try {
            ut.begin();
            em.remove(em.merge(operador));
            ut.commit();                                   
        } catch(Exception ex){
            ex.printStackTrace();
            try {
                ut.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(OperadorService.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(OperadorService.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(OperadorService.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }            
    }

    public void salvarOperador(Operador operador) {
        try{                    
            ut.begin();

//            if (operador.getId() == null){
//                em.persist(operador);
//                em.flush();
//            } else {
//                em.merge(operador);
//            }
        ut.commit();
        } catch(Exception ex){
            try {
                ut.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(OperadorService.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(OperadorService.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(OperadorService.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }     
    }

    public Operador nomeJaExiste(String nome) {
        Query query = em.createNamedQuery("Operador.findByNome");
        query.setParameter("nome", nome);
        
        if (query.getResultList().isEmpty()){
            return null;
        } else {
            return (Operador) query.getResultList().get(0);
        }
    }

    public Operador codigoJaExiste(String codInterno) {
        Query query = em.createNamedQuery("Operador.findByCodInterno");
        query.setParameter("codInterno", codInterno);
        
        if (query.getResultList().isEmpty()){
            return null;
        } else {
            return (Operador) query.getResultList().get(0);
        }
    }
}
