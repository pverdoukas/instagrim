<%-- 
    Document   : nav
    Created on : 23-Oct-2016, 22:00:08
    Author     : PVerdoukas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <nav>
        <ul>
                <li class="nav"><a href="/Instagrim">Home</a></li>
                <li class="nav"><a href="/Instagrim/login.jsp">Login</a></li>
                <li class="nav"><a href="/Instagrim/PublicGallery">Public Gallery</a></li>
                <%
                        LoggedIn lgNav;
                        if( request.isRequestedSessionIdValid() ) {
                        lgNav = (LoggedIn) session.getAttribute("LoggedIn");
                        }
                        else
                        {
                        lgNav = null;   
                        }
                        if (lgNav != null) {
                            if (lgNav.getlogedin()) {
                    %>
                <li class="nav"><a href="/Instagrim/Images/<%=lgNav.getUsername()%>">Your Gallery</a></li>
                <li class="nav"><a href="/Instagrim/Profile.jsp">User Profile</a></li>
                <li class="nav"><a href="/Instagrim/logout.jsp">Logout</a></li>
                <% }
                else
                {
                %>
                <li class="nav"><a href="/Instagrim/login.jsp">Login</a></li>
                <li class="nav"><a href="/Instagrim/register.jsp">Register</a></li>
                <%
                }
                }; %>
        </ul>
    </nav>
                
</html>
