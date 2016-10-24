/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.UUID;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.stores.*;
import uk.ac.dundee.computing.aec.instagrim.models.*;

/**
 * Adapted from picture servlet
 * @author Danstev
 */
@WebServlet(name = "postComment", urlPatterns = {"/Comments","/Comments/*"})
public class Comment extends HttpServlet {
    Cluster cluster = null;
    
    public Comment(){
        
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        cluster = CassandraHosts.getCluster();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{     
        String args[] = Convertors.SplitRequestPath(request);
        displayComments(args[2], request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        LoggedIn activeUser = (LoggedIn) session.getAttribute("LoggedIn");
        String args[] = Convertors.SplitRequestPath(request);
        UUID picID = UUID.fromString(args[2]); //Get pic id
        CommentModel comments = new CommentModel();
        comments.setCluster(cluster);
        String commentText = request.getParameter("commentEntry");
        System.out.println(commentText);
        String user = activeUser.getUsername();
        comments.postNewComment(picID, user, commentText); //Post comment to db
        response.sendRedirect("/Instagrim/Comments/"+args[2]); //redirect to same page (same picture), but with added comment now
    }
    
     public void displayComments(String picID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{       
        UUID uuid = UUID.fromString(picID);
        CommentModel comments = new CommentModel();
        comments.setCluster(cluster);
        LinkedList<UUID> CommentIDs;
        LinkedList<Comments> ActualComments;
        CommentIDs = comments.getCommentsFromPic(uuid); //Comment IDS
        ActualComments = comments.getCommentsFromID(CommentIDs); //Comments
        RequestDispatcher rd = request.getRequestDispatcher("/comments.jsp");
        request.setAttribute("comments", ActualComments); //Adds actual comments
        rd.forward(request, response);     
    }

}
