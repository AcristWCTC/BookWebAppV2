<%-- 
    Document   : edit
    Created on : Feb 29, 2016, 4:22:05 PM
    Author     : Adam
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Add Author</title
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link href="bookStoreAppCSS.css" rel="stylesheet" type="text/css"/>
    </head>

    <body class="add">
        <h1>Add a New Author</h1>
        <%
            Date date = new Date();
            SimpleDateFormat ft
                    = new SimpleDateFormat("dd/MM/yyyy");
            String CurrentDate = ft.format(date);

        %>

        <form method="post" action="<%= response.encodeURL("AuthorController?task=AddNewAuthor")%>">
            <table class="table-striped table-bordered col-sm-4 tableCustom tableFormatter">
                <tr>
                    <th>Author Name</th>
                    <th>Date Added</th>
                <tr>
                    <td><input type="text" name="authorName" value="${author.authorName}"/></td>
                    <td><input type="text" name="dateAdded" value="<%out.print(CurrentDate);%>" readonly="true"/></td>
                </tr>
                <br>

            </table>
            <br>
            <br>
            <input class="btn btn-primary" type="submit" value="save"/>
            <input class="btn btn-primary" type="button" value="cancel" onclick="location.href = 'AuthorController?task=Cancel'"/>
        </form>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>   
    </body>
</html>
