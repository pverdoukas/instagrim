/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;

/**
 *
 * @author Danstev
 */
@WebServlet(name = "Profile", urlPatterns = {"/Profile/*", "/UserProfile/*"})
public class Profile extends HttpServlet {
    Cluster cluster = null;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        cluster = CassandraHosts.getCluster();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        String args[] = Convertors.SplitRequestPath(request);
        displayProfile(args[2], request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //Nothing I can think of to post here, maybe edit name, address, add profile picture?
        
    }
    
    private void displayProfile(String User, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //Maybe check if user is looking at own profile and just link to /profile?
        String userName = User;
        PicModel pics = new PicModel();
        pics.setCluster(cluster);
        LinkedList<Pic> allPics;
        RequestDispatcher rd = request.getRequestDispatcher("/UserProfile.jsp");
        allPics = pics.getPicsForUser(User);
        request.setAttribute("User", userName);
        request.setAttribute("Pics", allPics);
        rd.forward(request,response);
        
    }
}
