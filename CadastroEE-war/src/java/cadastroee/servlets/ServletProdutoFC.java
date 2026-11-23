package cadastroee.servlets;
import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet("/ServletProdutoFC")
public class ServletProdutoFC extends HttpServlet {
 @EJB private ProdutoFacadeLocal facade;
 @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{processRequest(req,resp);}
 @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{processRequest(req,resp);}
 private void processRequest(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  req.setCharacterEncoding("UTF-8");
  String acao=req.getParameter("acao");if(acao==null)acao="listar";
  String destino="/ProdutoLista.jsp";
  try{
   switch(acao){
    case "formIncluir": destino="/ProdutoDados.jsp";break;
    case "formAlterar":{
     Produto p=facade.consultar(Integer.valueOf(req.getParameter("id")));
     if(p==null)throw new IllegalArgumentException("Produto não encontrado");
     req.setAttribute("produto",p);destino="/ProdutoDados.jsp";break;
    }
    case "incluir":{
     Produto p=lerProduto(req,false);facade.incluir(p);break;
    }
    case "alterar":{
     Produto p=lerProduto(req,true);facade.alterar(p);break;
    }
    case "excluir": facade.excluir(Integer.valueOf(req.getParameter("id")));break;
    case "listar":break;
    default:throw new IllegalArgumentException("Ação desconhecida");
   }
   if(destino.equals("/ProdutoLista.jsp"))req.setAttribute("produtos",facade.listar());
  }catch(Exception e){
   req.setAttribute("erro",e.getMessage());
   req.setAttribute("produtos",facade.listar());destino="/ProdutoLista.jsp";
  }
  req.getRequestDispatcher(destino).forward(req,resp);
 }
 private Produto lerProduto(HttpServletRequest req,boolean alterar){
  Produto p=new Produto();
  if(alterar)p.setIdProduto(Integer.valueOf(req.getParameter("id")));
  String nome=req.getParameter("nome");
  if(nome==null||nome.trim().isEmpty())throw new IllegalArgumentException("Nome obrigatório");
  p.setNome(nome.trim());
  int qtd=Integer.parseInt(req.getParameter("quantidade"));
  float preco=Float.parseFloat(req.getParameter("precoVenda").replace(',','.'));
  if(qtd<0||!Float.isFinite(preco)||preco<0)throw new IllegalArgumentException("Quantidade e preço devem ser válidos");
  p.setQuantidade(qtd);p.setPrecoVenda(preco);return p;
 }
}
