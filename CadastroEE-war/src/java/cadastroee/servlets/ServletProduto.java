package cadastroee.servlets;
import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/ServletProduto")
public class ServletProduto extends HttpServlet {
 @EJB private ProdutoFacadeLocal facade;
 @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws IOException{
  resp.setContentType("text/html;charset=UTF-8");
  try(PrintWriter out=resp.getWriter()){
   out.println("<!doctype html><html><meta charset='UTF-8'><title>Teste EJB</title><body><h1>Servlet ServletProduto at /CadastroEE-war</h1>");
   for(Produto p:facade.listar())out.println("<p>"+p.getIdProduto()+" - "+p.getNome().replace("&","&amp;").replace("<","&lt;")+"</p>");
   out.println("<a href='ServletProdutoFC?acao=listar'>Abrir cadastro</a></body></html>");
  }
 }
}
