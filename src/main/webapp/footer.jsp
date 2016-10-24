<%-- 
    Document   : header
    Created on : 19-Oct-2016, 17:36:14
    Author     : Dan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <ul class="footer">
                <li class="footer"><a href="/Instagrim">Home</a></li>
                <li class="footer"><a href="/Instagrim/login.jsp">Login</a></li>
                <li class="footer"><a href="/Instagrim/PublicGallery">Public Gallery</a></li>
                <%
                        LoggedIn lgfoot;
                        if( request.isRequestedSessionIdValid() ) {
                        lgfoot = (LoggedIn) session.getAttribute("LoggedIn");
                        }
                        else
                        {
                        lgfoot = null;   
                        }
                        if (lgfoot != null) {;
                            if (lgfoot.getlogedin()) {
                    %>
                <li class="footer"><a href="/Instagrim/Images/<%=lgfoot.getUsername()%>">Your Gallery</a></li>
                <li class="footer"><a href="/Instagrim/Profile.jsp">User Profile</a></li>
                <li class="footer"><a href="/Instagrim/logout.jsp">Logout</a></li>
                <% }
                else
                {
                %>
                <li class="footer"><a href="/Instagrim/login.jsp">Login</a></li>
                <li class="footer"><a href="/Instagrim/register.jsp">Register</a></li>
                <%
                }
                }; %>
    </ul>
                
</html>
