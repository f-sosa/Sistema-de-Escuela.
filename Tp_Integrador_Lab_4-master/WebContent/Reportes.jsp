<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="Entidad.Cursos"%>
<%@page import="Entidad.Materias"%>
<%@page import="Entidad.Alumnos"%>
<%@page import="Entidad.Notas"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#table_id1').DataTable();
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#table_id2').DataTable();
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#table_id3').DataTable();
	});
</script>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script defer src="theme.js"></script>
<link rel="stylesheet" href="style.css" />
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="SyleFormulario.css" />
</head>
<body>
	<jsp:include page="Menu.html"></jsp:include>
	<style>
	.login-box{
    height: 370px;
    top: 170px;
    width: 520px;
}
	.login-box2{
    height: 300px;
    top: 130px;
    width: 730px;
}
	.login-box3{
    height: 410px;
    top: 190px;
    width: 540px;
}
</style>
	
	<%
		ArrayList<Cursos> listacursos = null;
		if (request.getAttribute("Listanombrecursos") != null) {
			listacursos = (ArrayList<Cursos>) request.getAttribute("Listanombrecursos");
		}
	%>

	<%
		ArrayList<Materias> listaMaterias = null;
		if (request.getAttribute("Listarmaterias") != null) {
			listaMaterias = (ArrayList<Materias>) request.getAttribute("Listarmaterias");
		}
	%>

	<%
		ArrayList<Alumnos> listaAlumnos = null;
		if (request.getAttribute("listaU") != null) {
			listaAlumnos = (ArrayList<Alumnos>) request.getAttribute("listaU");
		}

		ArrayList<Alumnos> listaNotas = null;
		if (request.getAttribute("listaN") != null) {
			listaNotas = (ArrayList<Alumnos>) request.getAttribute("listaN");
		}

		ArrayList<Cursos> listaAno = null;
		if (request.getAttribute("listaAn") != null) {
			listaAno = (ArrayList<Cursos>) request.getAttribute("listaAn");
		}

		ArrayList<Notas> listaPromedio = null;
		if (request.getAttribute("listaPro") != null) {
			listaPromedio = (ArrayList<Notas>) request.getAttribute("listaPro");
		}
		int opcion = 1;
		if (request.getAttribute("Filtro") != null) {
			opcion = (int) request.getAttribute("Filtro");
		}
	%>
<style>
.submission-form2{
  max-width: 290px;
}
</style>
<form action="Reportes" method="get" class="submission-form2">
<input type=submit name="filtro1" id="sendBtn" value="Reporte 1"/>
<input type=submit name="filtro2" id="sendBtn" value="Reporte 2" />
<input type=submit name="filtro3" id="sendBtn" value="Reporte 3" />
</form>
	<!--Mostrar la cantidad de alumnos de x materia que aprobaron o desaprobaron <br> -->
	<% if(opcion ==1){%>
	<div class="login-box">
	<h1>Bienvenido:
	<%=session.getAttribute("Profesor")%></h1>
	<center>Reporte de Alumnos Aprobados/Desaprobados por Materia</center>
	<!--<form action ="CargarDropdownlist" method="GET">-->

	<form method="post" action="Reportes">
	<p>Elige un año</p><input name="Año" type="number" min="1980" max="2030">
		<p>Elija una materia</p>
		<select name="Materias" class="selected" style="width: 455px">
			<%
				if (listaMaterias != null)
					for (Materias Mat : listaMaterias) {
			%>
			<option value="<%=Mat.getCodMateria()%>"><%=Mat.getNombre()%>
			</option>
			<%
				}
			%>
		</select>

		<!--</form>-->
		<p>Elija una opcion</p>
		<select class="selected"name="Aprobaron/Desaprobaron" style="width: 455px">
			<option>Aprobaron</option>
			<option>Desaprobaron</option>
		</select><br> <br> <input type=submit name="Btnfiltro1"
			value="Mostrar Alumnos">
			</div>
	</form>


	<table id="table_id1" class="display">
		<thead>
			<tr class="w3-red">
				<th>Legajo</th>
				<th>Dni</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Email</th>
				<th>Teléfono</th>
			</tr>
		</thead>
		<tbody class="grid_Linea">
			<%
				if (listaAlumnos != null)
					for (Alumnos Alum : listaAlumnos) {
			%>
			<tr>
				<form action="Reportes" method="post">
					<td><%=Alum.getLegajoAl()%></td>
					<td><%=Alum.getDni()%></td>
					<td><%=Alum.getNombre()%></td>
					<td><%=Alum.getApellido()%></td>
					<td><%=Alum.getEmail()%></td>
					<td><%=Alum.getTelefono()%></td>
				</form>
			</tr>
			<%
				}
			%>

		</tbody>
	</table>
<%} %>
	<!--Mostrar los alumnos que tienen (Abajo arriba o igual que tal numero de X materia) <br>-->
	<%if(opcion==2){ %>
<div class="login-box2">
<h1>Bienvenido:
	<%=session.getAttribute("Profesor")%></h1>
		<b>Reporte de Alumnos por
			Año/Cuatrimestre/Materia/Parcial/Condicion/Nota</b>
	<form method="post" action="Reportes">

		<p>Elige año</p><input name="AñoIni" type="number" min="1980" max="2030">  
		<select class="selected" name="Cuatrim" style="width: 150px;">
			<option value="1">Primer Cuatrimestre</option>
			<option value="2">Segundo Cuatrimestre</option>
		</select> <select class="selected" name="Materias"style="width: 140px;">
			<%
				if (listaMaterias != null)
					for (Materias Mat : listaMaterias) {
			%>
			<option value="<%=Mat.getCodMateria()%>"><%=Mat.getNombre()%>
			</option>
			<%
				}
			%>
		</select> <select class="selected" name="Parcial" style="width: 154px;">
			<option>Nota_1</option>
			<option>Nota_2</option>
			<option>Rec_1</option>
			<option>Rec_2</option>

		</select> <select class="selected" name="Condicion" style="width: 30px;">
			<option>></option>
			<option>=</option>
			<option><</option>
		</select> <select class="selected" name="Nota" style="width: 78px;">
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
			<option>6</option>
			<option>7</option>
			<option>8</option>
			<option>9</option>
			<option>10</option>
		</select> <br>
		<br> <input type=submit name="Btnfiltro2" value="Mostrar Notas" /><br>
		</div>
	</form>



	<table id="table_id2" class="display" >
<thead>
		<tr class="w3-red">

			<th>Legajo</th>
			<th>Dni</th>
			<th>Nombre</th>
			<th>Apellido</th>
		</tr>
</thead>
<tbody class="grid_Linea">

		<%
			if (listaNotas != null)
				for (Alumnos Alum : listaNotas) {
		%>
		<tr>
			<form action="Reportes" method="post">
				<td><%=Alum.getLegajoAl()%></td>
				<td><%=Alum.getDni()%></td>
				<td><%=Alum.getNombre()%></td>
				<td><%=Alum.getApellido()%></td>
			</form>
		</tr>
		<%
			}
		%>
</tbody>
	</table>

	<br>
<%} %>
<%if(opcion==3){ %>
<div class="login-box3">
<h1>Bienvenido:
	<%=session.getAttribute("Profesor")%></h1>
	<b>Promedio de los parciales por Materia y Rango de Fechas</b>

	<form method="post" action="Reportes">


		<p>Materia </p> <select name="Materias2" class="selected" style="width: 475px">
			<%
				if (listaMaterias != null)
					for (Materias Mat : listaMaterias) {
			%>
			<option value="<%=Mat.getCodMateria()%>"><%=Mat.getNombre()%>
			</option>
			<%
				}
			%>
			</select>
		<p>Año inicio</p><input name="AñoIni" type="number" min="1980" max="2030"> 
		<p>Año fin</p><input name="AñoFin" type="number" min="1980" max="2030"> <br>
		<br> <input type=submit name="Btnfiltro3" value="Mostrar Notas" /><br>
</div>
	</form>


	<table id="table_id3" class="display" >
<thead>
		<tr class="w3-red">
			<th>Nota1</th>
			<th>Nota2</th>
			<th>Recu1</th>
			<th>Recu2</th>
</tr>
</thead>
<tbody class="grid_Linea">

			<%
				if (listaPromedio != null)
					for (Notas Notas : listaPromedio) {
			%>
		
		<tr>
			<form action="Reportes" method="post">
				<td><%=Notas.getNota1()%></td>
				<td><%=Notas.getNota2()%></td>
				<td><%=Notas.getRec1()%></td>
				<td><%=Notas.getRec2()%></td>
			</form>
		</tr>
		<%
			}
		%>
</tbody>
	</table>
<%} %>

</body>
</html>