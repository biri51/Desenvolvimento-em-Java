package cadastroee.controller;
import cadastroee.model.Produto;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import java.util.List;
@Stateless
public class ProdutoFacade implements ProdutoFacadeLocal {
 @PersistenceContext(unitName="CadastroEE-ejbPU") private EntityManager em;
 @Override public List<Produto> listar(){return em.createQuery("SELECT p FROM Produto p ORDER BY p.idProduto",Produto.class).getResultList();}
 @Override public Produto consultar(Integer id){return em.find(Produto.class,id);}
 @Override public void incluir(Produto p){em.persist(p);}
 @Override public void alterar(Produto p){em.merge(p);}
 @Override public void excluir(Integer id){Produto p=em.find(Produto.class,id);if(p!=null)em.remove(p);}
}
