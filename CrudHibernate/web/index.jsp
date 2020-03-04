<%-- 
    Document   : index
    Created on : 26/08/2019, 09:28:11
    Author     : Aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crud Hibernate - Principal</title>
        <link href="resources/css/estilo.css" rel="stylesheet" />
    </head>
    <body>
        <div id="principal">
            <div id="topo"></div>
            <div id="menu">
                <%@include file="menu.jsp" %>
            </div>
            
            <!--inicio da parte editavel formulários-->
            <div id="contexto">
                <img id="img_contexto" 
                     src="resources/imagem/java-contexto.jpg"
                     alt="" />
            </div>
            <!--fim da parte editavel formulários-->
            
            <div id="rodape"></div>
        </div>
    </body>
</html>
