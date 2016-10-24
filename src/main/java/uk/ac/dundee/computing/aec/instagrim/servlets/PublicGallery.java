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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.stores.Pic;

/**
 *
 * @author Danstev
 */
@WebServlet(name = "PublicGallery", urlPatterns = {"/PublicGallery"})
public class PublicGallery extends HttpServlet {

    Cluster cluster = null;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        cluster = CassandraHosts.getCluster();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        String args[] = Convertors.SplitRequestPath(request);
        displayPictures(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
    }
    
    private void displayPictures(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //Maybe check if user is looking at own profile and just link to /profile?
        PicModel pics = new PicModel();
        pics.setCluster(cluster);
        LinkedList<Pic> allPics;
        RequestDispatcher rd = request.getRequestDispatcher("/PublicGallery.jsp");
        allPics = pics.getNewest();
        request.setAttribute("Pics", allPics);
        rd.forward(request,response);
        
    }

}
