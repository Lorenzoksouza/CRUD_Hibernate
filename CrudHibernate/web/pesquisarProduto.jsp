<%-- 
    Document   : index
    Created on : 26/08/2019, 09:28:11
    Author     : Aluno
--%>

<%@page import="br.com.hibernate.entidade.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crud Hibernate - Pesquisa Produto</title>
        <link href="resources/css/estilo.css" rel="stylesheet" />
        <link href="resources/css/mensagem.css" rel="stylesheet" />
    </head>
    <body>
        <div id="principal">
            <div id="topo"></div>
            <div id="menu">
                <%@include file="menu.jsp" %>
            </div>

            <!--inicio da parte editavel formulários-->
            <div id="contexto">
                <h1>Pesquisa Produto</h1>

                <c:if test="${msg_sucesso != null}">
                    <p class="msg_sucesso">
                        ${msg_sucesso}
                    </p>
                </c:if>
                <c:if test="${msg_erro != null}">
                    <p class="msg_erro">
                        ${msg_erro}
                    </p>
                </c:if>
                <fieldset>
                    <legend>Pesquisa Produto</legend>
                    <form id="form_pesquisa_produto" method="post"
                          action="ProdutoControle?cmd=pesquisar" >
                        <div>
                            <label>Nome</label>
                            <input name="nome" type="text" />
                            <input value="Pesquisar" type="submit" class="botao" />
                        </div>

                    </form>

                </fieldset>

                <div id="div_tabela">
                    <c:if test="${produtos != null}">
                        <table border="1">
                            <tr>
                                <th>Nome</th> 
                                <th>Preço</th> 
                                <th>Estoque</th> 
                                <th>Ação</th> 
                            </tr>
                            <c:forEach items="${produtos}" var="produto" >
                                <tr>
                                    <td>${produto.nome}</td>
                                    <td>${produto.preco}</td>
                                    <td>${produto.estoque}</td>
                                    <td>
                                        Alterar 
                                        /
                                        <a href="ProdutoControle?cmd=excluir&id=${produto.id}">Excluir</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </div>


            </div>
            <!--fim da parte editavel formulários-->

            <div id="rodape"></div>
        </div>
    </body>
</html>
