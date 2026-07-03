<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Record" %>
<%@ page import="dao.*" %>

<%
    Record editRecord = (Record) request.getAttribute("record");
    String buttonLabel = (editRecord != null) ? "Update" : "Submit";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student register Form</title>

    
    <style>
body
{

    margin:0px;
}

input
{

     width:300px;
     height: 30px;
     /* border:rgb(106, 201, 207) 1px solid; */
}

label
{
    display:block;
}
.form_group
{
    display: block;
}

.form_group.success input
{
    border:rgb(21, 192, 15) 1px solid;
     
}

.form_group.error input
{
     border:rgb(235, 20, 31) 1px solid;
     background-color: coral;

}

.bovien
{
    border: 1px solid #1013be;
    
}

table.bovien {
    border-collapse: collapse;
}

th.bovien
{
    font-weight: bold;
}



    </style>
</head>
<body>
    

    <div id="container" style="width: 100%;  display: flex; justify-content: center;   ">

     

        <div style="width:1000px; height: 300px; background-color: #eaf5d7;">

            <h1>Student Regiter Form</h1>

            <form id="register_form" action="student" method="post">
                <input type="hidden" name="id" value="<%= (editRecord!=null)? editRecord.GetId():0 %>" />
                <input type="hidden" name="action" value="<%= (editRecord!=null)? "update" : "" %>" />

            

                    <div class="form_group">
                        <label for="sname">Student name</label>
                        <input id="sname" name="stname" value="<%= (editRecord!=null)? editRecord.GetStname():"" %>" />
                    </div>


                     <div class="form_group">
                        <label for="course">Course</label>
                        <input id="course" name="course" value="<%= (editRecord!=null)? editRecord.GetCourse():"" %>" />
                    </div>


                     <div class="form_group">
                        <label for="fee">Fee</label>
                        <input id="fee" name="fee" value="<%= (editRecord!=null)? editRecord.GetFee():"" %>" />
                    </div>


                    


                    <div>
                        <button type="submit"><%=buttonLabel%></button>
                    </div>
                </form>

        </div>


       

    </div>


<div id="container" style="width: 100%;  display: flex; justify-content: center;   ">
     <div style="width:1000px; height: 300px; background-color: #f5f1d7;">

            <table class="bovien">
                <tr class="bovien">
                    <th class="bovien" style="width:300px">Student Name</th>
                    <th class="bovien" style="width:250px">Course</th>
                    <th class="bovien" style="width:100px">Fee</th>
                    <th class="bovien" style="width:100px">Edit</th>
                    <th class="bovien" style="width:100px">Delete</th>
                </tr>
                <%
            RecordDAO dao=new RecordDAO();
            List<Record> list = dao.getAllRecords();

            if(list != null){
                for(Record p : list){
            %>

             <tr class="bovien">
                <td class="bovien" style="width:300px"><%=p.GetStname()%></td>
                    <td class="bovien" style="width:250px"><%=p.GetCourse()%></td>
                    <td class="bovien" style="width:100px"><%=p.GetFee()%></td>
                    <td class="bovien" style="width:100px"><a href="student?action=edit&id=<%=p.GetId()%>">Edit</a></td>
                    <td class="bovien" style="width:100px"><a href="student?action=delete&id=<%=p.GetId()%>" onclick="return confirm('Delete this record?');">Delete</a></td>

             </tr>



            <%} }%>


            </table>

        </div>

</div>

    

</body>
</html>
```
