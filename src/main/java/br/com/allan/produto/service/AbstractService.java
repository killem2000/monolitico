/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.allan.produto.service;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.hibernate.Session;
import org.hibernate.jpa.internal.EntityManagerImpl;

/**
 *
 * @author fernando
 */
@Named
public class AbstractService implements Serializable{
    private static final long serialVersionUID = -7768199678255869251L;
    
    @PersistenceContext(unitName = "produto")
    protected EntityManager em;
    @Resource()
    protected UserTransaction ut;     

    public Session getSession() {
        if (em.getDelegate() instanceof EntityManagerImpl) {  
            EntityManagerImpl entityManagerImpl = (EntityManagerImpl) em.getDelegate();  
            return entityManagerImpl.getSession();  
        } else {  
            return (Session) em.getDelegate();  
        }        
    } 
    
    public int proximoNumeroSequencia(String tabela, String coluna, String condicao) {
        Query query = em.createNativeQuery("SELECT MAX(" + coluna + ") MAXIMO "
                + " FROM " + tabela + condicao);

        Object obj = query.getSingleResult();

        return obj != null ? ((BigDecimal) obj).add(BigDecimal.ONE).intValue() : 1;
    }
}
