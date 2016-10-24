<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload : InstaGrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body>
        <h1>Upload your pictures</h1>
        <h2>Your world in Black and White</h2>
        <%@include file="nav.jsp"%>
 
        <article>
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image">
                File to upload: <input type="file" name="upfile"><br/>

                <br/>
                <input type="submit" value="Press"> to upload the file!
            </form>

        </article>
        <footer>
            <%@include file="footer.jsp"%> 
        </footer>
    </body>
</html>
