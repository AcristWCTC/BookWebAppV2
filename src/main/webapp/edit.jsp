<%-- 
    Document   : edit
    Created on : Feb 29, 2016, 4:22:05 PM
    Author     : Adam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
    <h1>Edit or Delete an Author</h1>
        <title>Edit Author</title
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link href="bookStoreAppCSS.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="edit">
        <form method="post" action="<%= response.encodeURL("AuthorController?task=Save") %>>
            <table class="table-striped table-bordered col-sm-6 tableCustom tableFormatter">
                <tr></tr>
                <th>Author Id</th>
                <th>Author Name</th>
                <th>Date Added</th>
                <tr>
                    <td><input type="text" name="authorId" value="${author.authorId}" readonly="true"/></td>
                    <td><input type="text" name="authorName" value="${author.authorName}"/></td>
                    <td><input type="text" name="dateAdded" value="${author.dateAdded}" readonly="true"/></td>
                </tr>
                <br>

            </table>
            <br>
            <br>
            <input class="btn btn-primary" type="Submit" value="Save"/>
            <input class="btn btn-danger" type="button" name="Delete" value="Delete" onclick="location.href = 'AuthorController?task=DeleteAuthor&id=${author.authorId}'" ></input>
            <input class="btn btn-primary" type="button" value="Cancel" onclick="location.href = 'AuthorController?task=Cancel'"/>

        </form>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>   
    </body>
</html>
