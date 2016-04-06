<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link href="bookStoreAppCSS.css" rel="stylesheet" type="text/css"/>
        <title>Book Info Page</title>
    </head>
    <body>
        <h1 class="col-sm-12">Book Info</h1>
        <h1 class="col-sm-4"><img src="index.png" alt=""/><h1>
                <div>
                    <input class="btn btn-primary" type="button" name="bookId" value="Add Book" onclick="location.href = 'BookController?task=Add'"></input>
                    <input class="btn btn-danger" type="button" name="back" value="Back" onclick="window.location.href='index.jsp'"></button>
                </div>
                <br>


                <table class="table-bordered col-sm-6 tableFormatter" style = "background-color: ${tableColor}">
                    <TH class = "col-sm-1"></th>
                    <TH class = "col-sm-2">Name</th>
                    <TH class = "col-sm-2">ISBN</th>
                    <TH class = "col-sm-2">Author</th>
                        <c:forEach items="${books}" var="current">
                        <tr>
                            <td class = "col-sm-2"><input class="btn btn-primary" type="button" name="bookId" value="Edit/Delete" onclick="location.href = 'BookController?task=EditBook&id=${current.bookId}'" ></input></td>
                            <td class = "col-sm-4"><c:out value="${current.title}" /></td>
                            <td class = "col-sm-4"><c:out value="${current.isbn}" /></td>
                            <td class = "col-sm-4"><c:out value="${current.authorId.authorName}" /></td>

                            </c:forEach>
                </table>
                <div class ="col-sm-6"></div>





                <style>
                    th,td{
                        color: ${textColor}
                    }
                </style>
                
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>   
                </body>
                </html>
