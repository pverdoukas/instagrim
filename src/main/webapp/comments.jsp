<%-- 
    Document   : comments
    Created on : 19-Oct-2016, 22:54:36
    Author     : Danstev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.Comments"%> 
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comments : InstaGrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
    </head>
    <body>
        <h2>Your world in Black and White</h2> 
        <%@include file="nav.jsp"%>
        <article>
            <h1>Comments</h1>
            
            <%
                LinkedList<Comments> commentList;
                commentList = (LinkedList<Comments>) request.getAttribute("comments");
                if(commentList == null){ 
            %>
            <h3>No comments</h3> 
            <%
                }
                else
                {
                    for(Comments com : commentList)
                    {
                    %>
                        <p>
                            <%=com.getComment()%> 
                            <a href="/Instagrim/UserProfile/<%=com.getUser()%>"><%=com.getUser()%></a>
                            <%=com.getTime()%>
                        </p>
                    <%
                    }
                }
            %>

        </article>
        <article>
            <h2>Post comment</h2>
            <form method="post">
                <textarea rows="4" cols="50" name="commentEntry" id="commentEntry" required="required">Enter comment here!</textarea>
                <input type="submit" value="post">
            </form>
        </article>
        <footer>
            <%@include file="footer.jsp"%> 
        </footer>
    </body>
</html>
