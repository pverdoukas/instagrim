/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.models;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;
import java.util.Iterator;
import uk.ac.dundee.computing.aec.instagrim.lib.Convertors;
import uk.ac.dundee.computing.aec.instagrim.stores.Comments;

/**
 *
 * @author Danstev
 */
public class CommentModel {

    Cluster cluster;
    
    public CommentModel(){
        
    }
    
    public void setCluster(Cluster cluster){       
        this.cluster=cluster;      
    }
    
    public void postNewComment(UUID picID, String user, String comment){
        Session session = cluster.connect("instagrim");
        UUID uuid = Convertors.getTimeUUID();
        PreparedStatement ps_insertComment = session.prepare("insert into comments (commentid, user, comment, comment_added) values (?,?,?,?)"); //Add comment
        String insertCommentID = "update pics set comments = comments + {" + uuid + "} where picid = ?"; //Link comment to pic
        PreparedStatement ps_insertCommentID = session.prepare(insertCommentID);
        BoundStatement bs_insertComment = new BoundStatement(ps_insertComment);
        BoundStatement bs_insertCommentID = new BoundStatement(ps_insertCommentID);        
        Date postTime = new Date(); //New time to add to db
        session.execute(bs_insertComment.bind(uuid, user, comment, postTime));
        session.execute(bs_insertCommentID.bind(picID));
              
    }
    
    public LinkedList<UUID> getCommentsFromPic(UUID picID){
        Session session = cluster.connect("instagrim");
        LinkedList<UUID> comments = new LinkedList<>();
        PreparedStatement ps_selectComments = session.prepare("select Comments from pics where picid = ?");
        BoundStatement bs_selectComments = new BoundStatement(ps_selectComments);
        ResultSet rs_selectComments = null; //IT is used : P
        rs_selectComments = session.execute(bs_selectComments.bind(picID));

        if(rs_selectComments.isExhausted()){
            return null;
        }
        else{
            for(Row row : rs_selectComments){
                Set<UUID> newComments = row.getSet("comments", UUID.class);
                for (UUID commentID : newComments) {
                    comments.add(commentID);
                }
            } 
            return comments;   
        }
    }
    
    public LinkedList<Comments> getCommentsFromID(LinkedList<UUID> lsCommentIDs){
        Session session = cluster.connect("instagrim");
        LinkedList<Comments> comments = new LinkedList<>();
        PreparedStatement ps_selectComments = session.prepare("select user, comment, comment_added from Comments where commentid = ?");
        BoundStatement bs_selectComments = new BoundStatement(ps_selectComments);
        ResultSet rs_selectComments = null;
        
        for (UUID commentID : lsCommentIDs) {
            rs_selectComments = session.execute(bs_selectComments.bind(commentID));
            
            if(rs_selectComments.isExhausted()){
                System.out.println("Comment could not be found");
            }
            else{
                for(Row row : rs_selectComments){
                    Comments comment = new Comments();
                    String user = row.getString("user");
                    String commentText = row.getString("comment");
                    Date timestamp = row.getTimestamp("comment_added");
                    System.out.println("inserting " + commentID);
                    comment.setUUID(commentID); //Maybe easier way to do all this at once
                    comment.setUser(user);
                    comment.setComment(commentText);
                    comment.setTimestamp(timestamp);
                    comments.add(comment);      
                }
            }
        }       
        return comments;     
    }

}
