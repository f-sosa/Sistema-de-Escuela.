<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@page import="Entidad.Notas"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
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
		$('#table_id').DataTable();
	});
</script>
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="SyleFormulario.css" />
</head>
<body>
	<style>
	.login-box{
    height: 160px;
    top: 110px;
}</style>
<jsp:include page="Menu.html"></jsp:include>
<%
		ArrayList<Notas> listaAlumnos = null;
		if (request.getAttribute("ListaAlumnos") != null) {
			listaAlumnos = (ArrayList<Notas>) request.getAttribute("ListaAlumnos");
		}
	%>
	<%
		int filas = 0;
		if (request.getAttribute("Agregado") != null) {
			filas = Integer.parseInt(request.getAttribute("Agregado").toString());
			;
		}
	%>
	<%
		if (filas == 1) {
	%>
	Se agrego el Alumno 
	<%
		}
	%>
	<div class="login-box">
<h1>Bienvenido:
	<%=session.getAttribute("Profesor")%></h1>
	<h1>Viendo alumnos del curso: <%=session.getAttribute("CodCurso")%></h1>
	</div>
	<table id="table_id" class="display">
<thead>
		<tr class="w3-red">
			<th>Legajo</th>		
			<th>Nombre</th>
			<th>Apellido</th>
				<th></th>
		
			</tr>
			</thead>
			<tbody class="grid_Linea">
			<%
				if (listaAlumnos != null)
					for (Notas Not : listaAlumnos) {
			%>
		
		<tr>

			<td><%= Not.getAlum().getLegajoAl()%></td>
			<td><%= Not.getAlum().getNombre()%></td>
			<td><%=Not.getAlum().getApellido()%></td>
			<<td> <input type="submit" name="btnAgregar" value="Agregar Alumno"  onclick="window.location.href='AgregarAlumnosACursos?btnAgregar=1&legAlum=<%= Not.getAlum().getLegajoAl() %>&CodCurso=<%=session.getAttribute("CodCurso")%>'"/></td>

		</tr>

		<%
			}
		%>
		</tbody>
	</table>
  

</body>
</html>