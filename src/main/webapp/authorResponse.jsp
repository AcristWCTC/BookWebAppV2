<%-- 
    Document   : helloResponse
    Created on : Jan 20, 2016, 7:01:23 PM
    Author     : Adam
--%>

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
        <title>Author Info Page</title>
    </head>
    <body>
        <h1 class="col-sm-12">Author Info</h1>
        <h1 class="col-sm-4"><img src="index.png" alt=""/><h1>

                <div>
                    <input class="btn btn-primary" type="button" name="authorId" value="Add Author" onclick="location.href = 'AuthorController?task=Add'"></input>
                    <input class="btn btn-danger" type="button" name="back" value="Back" onclick="window.location.href='index.jsp'"></button>
                </div>
                <br>


                <table class="table-bordered col-sm-6 tableFormatter" style = "background-color: ${tableColor}">
                    <TH class = "col-sm-1"></th>
                    <TH class = "col-sm-2">Name</th>
                    <TH class = "col-sm-2">Date Added</th>
                        <c:forEach items="${authors}" var="current">
                        <tr>
                            <td class = "col-sm-2"><input class="btn btn-primary" type="button" name="authorId" value="Edit/Delete" onclick="location.href = 'AuthorController?task=EditAuthor&id=${current.authorId}'" ></input></td>
                            <td class = "col-sm-4"><c:out value="${current.authorName}" /></td>
                            <td class = "col-sm-6"><fmt:formatDate pattern="M/d/yyyy" 
                                            value="${current.dateAdded}" /></td>
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
