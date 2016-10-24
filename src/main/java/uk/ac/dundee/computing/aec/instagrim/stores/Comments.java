/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.stores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Danstev
 */
public class Comments {
    
    private UUID uuid = null;
    private String user = null;
    private String comment = null;
    private Date time = null;
    
    public UUID getUuid() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public Date getTime() {
        return time;
    }

    public void setTimestamp(Date timeNew) {
        this.time = timeNew;
    }
   
    public String fixTime(){
        String dateString = this.time.toString();
        dateString = dateString.substring(4); 
        return dateString;
    }

}
