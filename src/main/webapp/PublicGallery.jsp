<%-- 
    Document   : PublicGallery
    Created on : 23-Oct-2016, 20:21:04
    Author     : Danstev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Public Gallery : InstaGrim</title>
        <link rel="stylesheet" type="text/css" href="/Instagrim/Styles.css" />
    </head>
    <body>
        <header>
        
        <h1>
            
        </h1>
        </header>
        
        <%@include file="nav.jsp"%>
 
        <article>
        <%
        java.util.LinkedList<Pic> allPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
        if (allPics == null) {
            %>
            <p>No Images found</p>
            <%}
        else{ %> <ul><%
            for(Pic pic : allPics)
            {
                %>
                <li> <figure><a href="/Instagrim/Image/<%=pic.getSUUID()%>"><img src="/Instagrim/Thumb/<%=pic.getSUUID()%>"></a>
                </li>        
                <%
            }
            %></ul> <%
        }
        %>
        </article>
        <footer>
            <%@include file="footer.jsp"%> 
        </footer>
    </body>
</html>
