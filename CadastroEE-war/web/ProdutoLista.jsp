
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List,cadastroee.model.Produto"%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Listagem de Produtos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-4">

    <h1>Listagem de Produtos</h1>

    <a class="btn btn-primary my-2" href="ServletProdutoFC?acao=formIncluir">
        Novo Produto
    </a>

    <% if (request.getAttribute("erro") != null) { %>
        <div class="alert alert-danger">
            <%= request.getAttribute("erro") %>
        </div>
    <% } %>

    <table class="table table-striped">
        <thead class="table-dark">
            <tr>
                <th>#</th>
                <th>Nome</th>
                <th>Quantidade</th>
                <th>Preço de Venda</th>
                <th>Opções</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Produto> produtos =
                    (List<Produto>) request.getAttribute("produtos");

                if (produtos != null) {
                    for (Produto p : produtos) {
            %>
                <tr>
                    <td><%= p.getIdProduto() %></td>
                    <td><%= p.getNome()
                        .replace("&", "&amp;")
                        .replace("<", "&lt;")
                        .replace(">", "&gt;")
                        .replace("\"", "&quot;") %></td>
                    <td><%= p.getQuantidade() %></td>
                    <td><%= p.getPrecoVenda() %></td>
                    <td>
                        <a class="btn btn-primary btn-sm"
                           href="ServletProdutoFC?acao=formAlterar&amp;id=<%= p.getIdProduto() %>">
                            Alterar
                        </a>

                        <form class="d-inline" method="post"
                              action="ServletProdutoFC"
                              onsubmit="return confirm('Excluir este produto?')">
                            <input type="hidden" name="acao" value="excluir">
                            <input type="hidden" name="id" value="<%= p.getIdProduto() %>">
                            <button class="btn btn-danger btn-sm" type="submit">
                                Excluir
                            </button>
                        </form>
                    </td>
                </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
