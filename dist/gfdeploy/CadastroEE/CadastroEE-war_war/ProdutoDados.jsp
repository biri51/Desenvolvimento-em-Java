
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cadastroee.model.Produto"%>

<%
    Produto p = (Produto) request.getAttribute("produto");
    boolean alterar = p != null;
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dados do Produto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="container py-4">

    <h1>Dados do Produto</h1>

    <form action="ServletProdutoFC" method="post">

        <input type="hidden" name="acao"
               value="<%= alterar ? "alterar" : "incluir" %>">

        <% if (alterar) { %>
            <input type="hidden" name="id"
                   value="<%= p.getIdProduto() %>">
        <% } %>

        <div class="mb-3">
            <label class="form-label" for="nome">Nome:</label>
            <input class="form-control"
                   id="nome"
                   name="nome"
                   maxlength="100"
                   required
                   value="<%= alterar
                       ? p.getNome()
                           .replace("&", "&amp;")
                           .replace("\"", "&quot;")
                           .replace("<", "&lt;")
                           .replace(">", "&gt;")
                       : "" %>">
        </div>

        <div class="mb-3">
            <label class="form-label" for="quantidade">
                Quantidade:
            </label>
            <input class="form-control"
                   id="quantidade"
                   type="number"
                   min="0"
                   name="quantidade"
                   required
                   value="<%= alterar ? p.getQuantidade() : "" %>">
        </div>

        <div class="mb-3">
            <label class="form-label" for="precoVenda">
                Preço de Venda:
            </label>
            <input class="form-control"
                   id="precoVenda"
                   type="number"
                   step="0.01"
                   min="0"
                   name="precoVenda"
                   required
                   value="<%= alterar ? p.getPrecoVenda() : "" %>">
        </div>

        <button class="btn btn-primary" type="submit">
            <%= alterar ? "Alterar Produto" : "Adicionar Produto" %>
        </button>

        <a class="btn btn-secondary"
           href="ServletProdutoFC?acao=listar">
            Voltar
        </a>

    </form>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
