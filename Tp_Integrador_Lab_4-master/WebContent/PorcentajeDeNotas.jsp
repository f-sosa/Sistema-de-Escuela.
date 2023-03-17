<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<%@page import="Entidad.*"%>	
<head><link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="SyleFormulario.css" />
</head>
 	<jsp:include page="MenuDelProf.html"></jsp:include>
<%
int apNota1 = 0;
int apNota2 = 0;
int apNota3 = 0;
int apNota4 = 0;
int reNota1 = 0;
int reNota2 = 0;
int reNota3 = 0;
int reNota4 = 0;
int regulares = 0;
int libres = 0;

Notas not = new Notas();


int asd = (Integer)request.getAttribute("AprobadoNota1");

if(request.getAttribute("AprobadoNota1") != null) 
{ 
	apNota1 = (Integer)request.getAttribute("AprobadoNota1");
}

if(request.getAttribute("AprobadoNota2") != null) 
{ 
	apNota2 = (Integer)request.getAttribute("AprobadoNota2");
}

if(request.getAttribute("AprobadoNota3") != null) 
{
	apNota3 = (Integer)request.getAttribute("AprobadoNota3");
}

if(request.getAttribute("AprobadoNota4") != null) 
{ 
	apNota4 = (Integer)request.getAttribute("AprobadoNota4");
}

if(request.getAttribute("DesaprobadoNota1") != null) 
{
	reNota1 = (Integer)request.getAttribute("DesaprobadoNota1");
}

if(request.getAttribute("DesaprobadoNota2") != null) 
{
	reNota2 = (Integer)request.getAttribute("DesaprobadoNota2");
}

if(request.getAttribute("DesaprobadoNota3") != null) 
{ 
	reNota3 = (Integer)request.getAttribute("DesaprobadoNota3");
}

if(request.getAttribute("DesaprobadoNota4") != null) 
{ 
	reNota4 = (Integer)request.getAttribute("DesaprobadoNota4");
}

if(request.getAttribute("contRegular") != null) 
{ 
	regulares = (Integer)request.getAttribute("contRegular");
}

if(request.getAttribute("contLibre") != null) 
{ 
	libres = (Integer)request.getAttribute("contLibre");
}

if(request.getAttribute("Notas") != null) 
{ 
	not = (Notas)request.getAttribute("Notas");
}

%>
<% 
int Porcentaje=0;
if(request.getAttribute("ca") != null) 
{ 
	Porcentaje = (int)request.getAttribute("ca");
}
%>
<% 
if(Porcentaje==1) 
{ 
	
%>

<!DOCTYPE html>
<html>
<head>
<body>
     <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Parcial 1 Aprobados',     <%=apNota1%>],     
          ['Parcial l Desaprobados', <%=reNota1%>], 
          ['Parcial 2 Aprobados',     <%=apNota2%>],
          ['Parcial 2 Desaprobados', <%=reNota2%>], 
          ['Sleep',    0]
        ]);
     
        var options = {
          title: 'Porcentaje',
        	  backgroundColor: { fill:'transparent' }
        };
        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
     

<%} %>

<% 
if(Porcentaje==2) 
{ 
	
%>

<!DOCTYPE html>
<html>
<head>
     <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Parcial 1 Aprobados',     <%=apNota1%>],     
          ['Parcial l Desaprobados', <%=reNota1%>],      
          ['Sleep',    0]
        ]);
     

        var options = {
          title: 'Porcentaje',
          backgroundColor: { fill:'transparent' }
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
     

<%} %>

<%
if(Porcentaje==3) 
{ 
%>

 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Parcial 2 Aprobados',     <%=apNota2%>],
          ['Parcial 2 Desaprobados', <%=reNota2%>],       
          ['Sleep',    0]
        ]);
     

        var options = {
          title: 'Porcentaje',
          backgroundColor: { fill:'transparent' }
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>




<%} %>
<%

if(Porcentaje==4) 
{ 
%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Recuperatorio 1 Aprobados',  <%=apNota3%>],
          ['Recuperatorio 1 Desaprobados', <%=reNota3%>],      
          ['Sleep',    0]
        ]);
     

        var options = {
          title: 'Porcentaje',
          backgroundColor: { fill:'transparent' }
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>

<%} %>
<%
if(Porcentaje==5) 
{ 
%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Recuperatorio 2 Aprobados', <%=apNota4%>],
          ['Recuperatorio 2 Desaprobados', <%=reNota4%>],        
          ['Sleep',    0]
        ]);
     

        var options = {
          title: 'Porcentaje',
          backgroundColor: { fill:'transparent' }
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>

<%} %>
<%
if(Porcentaje==6) 
{ 
%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Alumnos Libres ', <%=libres%>],
          ['Alumnos Regulares', <%=regulares%>],        
          ['Sleep',    0]
        ]);
     

        var options = {
          title: 'Porcentaje',
          backgroundColor: { fill:'transparent' }
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>

<%} %>

<title></title>
</head>

<form action="PorcentajeDeNotas" method="GET" class="submission-form">
<input type=submit name="btnParcial1" id="sendBtn"value=" Mostrar Parcial 1 " />
<input type=submit name="btnParcial2" id="sendBtn" value="Mostrar Parcial 2 " />
<input type=submit name="btnrec1" id="sendBtn" value=" Mostrar Recuperatorio 1 " />
<input type=submit name="btnrec2"  id="sendBtn"value="Mostrar Recuperatorio 2 " />
<input type=submit name="btnestado" id="sendBtn" value="Mostrar Estados" />
</form>
 <div id="piechart" style="width: 900px; height: 500px; margin:2rem auto"></div>
</body>
</html>