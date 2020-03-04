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
        <title>Crud Hibernate - Novo Produto</title>
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
                <h1>Produto</h1>
                
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
                    <legend>Novo Produto</legend>
                    <form id="form_novo_produto" method="post"
                          action="ProdutoControle?cmd=salvar" >
                        <div>
                            <label>Nome</label>
                            <input name="nome" type="text" />
                        </div>

                        <div>
                            <label>Preço</label>
                            <input name="preco" type="text" />
                        </div>

                        <div>
                            <label>Estoque</label>
                            <input name="estoque" type="text" />
                        </div>

                        <div id="descricao">
                            <label>Descrição</label>
                            <textarea name="descricao" ></textarea>
                        </div>

                        <div id="botao">
                            <input value="Salvar" type="submit" class="botao" />
                            <input value="Limpar" type="reset" class="botao" />
                        </div>                            

                    </form>

                </fieldset>



            </div>
            <!--fim da parte editavel formulários-->

            <div id="rodape"></div>
        </div>
    </body>
</html>
