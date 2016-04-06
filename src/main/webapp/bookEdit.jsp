
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
    <h1>Edit or Delete an Book</h1>
        <title>Edit Book</title
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link href="bookStoreAppCSS.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="edit">
        <form method="post" action="<%= response.encodeURL("BookController?task=Save") %>">
            <table class = "table-striped table-bordered col-sm-6 tableCustom tableFormatter">
                <tr></tr>
                <th>Book Id</th>
                <th>Title</th>
                <th>ISBN</th>
                <th>Author</th>
                <tr>
                    <td><input type="text" name="bookId" value="${book.bookId}" readonly="true"/></td>
                    <td><input type="text" name="title" value="${book.title}"/></td>
                    <td><input type="text" name="isbn" value="${book.isbn}"/></td>
                                        <td>
                        <select name="authorId">
                            <c:forEach items="${authorsList}" var="author">
                                <option value="${author.authorId}">${author.authorName}</option>
                            </c:forEach>

                        </select>
                    </td>
                </tr>
                <br>

            </table>
            <br>
            <br>
            <input class="btn btn-primary" type="Submit" value="Save"/>
            <input class="btn btn-danger" type="button" name="Delete" value="Delete" onclick="location.href = 'BookController?task=DeleteBook&id=${book.bookId}'" ></input>
            <input class="btn btn-primary" type="button" value="Cancel" onclick="location.href = 'BookController?task=Cancel'"/>

        </form>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>   
    </body>
</html>
