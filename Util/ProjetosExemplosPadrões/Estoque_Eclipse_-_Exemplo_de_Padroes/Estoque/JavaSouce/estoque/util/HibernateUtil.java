package estoque.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
    
    private static SessionFactory fabrica = null;
    
    private static ThreadLocal sessao = new ThreadLocal();
    private static ThreadLocal transacao = new ThreadLocal();
    
    public static Session getSessao() {
        try {
            if (fabrica == null)
            	// Trocou Configuration para AnnotationConfiguration
                fabrica = new AnnotationConfiguration().configure().buildSessionFactory();
            if (sessao.get() == null)
                iniciaTransacao();
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
        return (Session)sessao.get();
    }
    
    private static Transaction getTransacao() {
        return (Transaction)transacao.get();
    }
    
    public static void iniciaTransacao() throws Exception{
        if (sessao.get() != null)
            throw new Exception("Não sei como aninhar transações");
        sessao.set(fabrica.openSession());
        transacao.set(getSessao().beginTransaction());
    }

    public static void fechaSessao() {
        try {
            if (getSessao() != null)
                getSessao().close();
        }
        finally {
            sessao.set(null);
            transacao.set(null);
        }
    }
    
    public static void confirma() {
        try {
            if (getTransacao() != null)
                getTransacao().commit();
        }
        finally {
            fechaSessao();
        }
    }
    
    public static void aborta() {
        try {
            if (getTransacao() != null)
                getTransacao().rollback();
        }
        finally {
            fechaSessao();
        }
    }
}
