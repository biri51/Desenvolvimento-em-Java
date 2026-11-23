package cadastroee.controller;
import cadastroee.model.Produto;
import java.util.List;
import jakarta.ejb.Local;
@Local
public interface ProdutoFacadeLocal {
 List<Produto> listar();
 Produto consultar(Integer id);
 void incluir(Produto produto);
 void alterar(Produto produto);
 void excluir(Integer id);
}
