<%-- 
    Document   : UserProfile
    Created on : 23-Oct-2016, 19:15:59
    Author     : Danstev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Pages : InstaGrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
    </head>
    <body>
        <header>
        
        
            <%
            String user = (String)request.getAttribute("User"); //Does this work? FROM : https://coderanch.com/t/464300/java/Object-String-JSP 
            if (user == null) {
            %>
            No User found
            <%}
            else
            {
            %>
                <% //=user; why no work?
                    
                %>
                's profile
            <%
            }
            %>

        
        <h2>Your world in Black and White</h2>
        </header>
        
        <%@include file="nav.jsp"%>
 
        <article>
        <%
        java.util.LinkedList<Pic> allPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
        if (allPics == null) {
            %>
            <p>No Images found</p>
            <%}
        else{
            for(Pic pic : allPics)
            {
                %>
                    <figure><a href="/Instagrim/Image/<%=pic.getSUUID()%>"><img src="/Instagrim/Thumb/<%=pic.getSUUID()%>"></a>
                    <figcaption><a href="/Instagrim/Comments/<%=pic.getSUUID()%>">Picture Comments</a></figcaption></figure>
                <%
            }
        }
        %>
        </article>
        <footer>
            <%@include file="footer.jsp"%> 
        </footer>
    </body>
</html>
