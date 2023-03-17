<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="Entidad.Notas"%>
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
		$('#table_id').DataTable();
	});
</script>
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="SyleFormulario.css" />
</head>
	<style>
	.login-box{
    height: 160px;
    top: 110px;
}</style>
<body>
	<jsp:include page="Menu.html"></jsp:include>
 
	<br>
<%
		ArrayList<Notas> listaAlumnos = null;
		if (request.getAttribute("ListaAlumnos") != null) {
			listaAlumnos = (ArrayList<Notas>) request.getAttribute("ListaAlumnos");
		}
	%>
	<%
		int Existente = 0;
		if (request.getAttribute("VerificarAlumnos") != null) {
			Existente = Integer.parseInt(request.getAttribute("VerificarAlumnos").toString());
			;
		}
	%>
	<%
		if (Existente == 2) {
	%>
	No hay Alumnos en este curso
	<%
		}
	%>
	<%
		int Eliminado = 0;
		if (request.getAttribute("Eliminado") != null) {
			Eliminado = Integer.parseInt(request.getAttribute("Eliminado").toString());
			;
		}
	%>
	<%
	int Legajo = 0;
			if(request.getAttribute("Legajo")!=null){
				Legajo = (int) request.getAttribute("Legajo");
			}
			String nombre = "";
			if(request.getAttribute("Nombre")!=null){
				nombre = (String) request.getAttribute("Nombre");
			}
			String apellido = "";
			if(request.getAttribute("Apellido")!=null){
				apellido = (String) request.getAttribute("Apellido");
			}
			%>
	<%
		if (Eliminado == 1) {
	%>
	<script type="text/javascript">
    var msg = "El alumno <%=Legajo%>-<%=nombre%>-<%=apellido%> ha sido eliminado";
    alert(msg);
</script>
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
			<td> <input type="submit" name="btnEliminar" value="Eliminar Alumno"  onclick="window.location.href='EliminarAlumnoDeCurso?btnEliminar=1&legAlum=<%= Not.getAlum().getLegajoAl() %>&Nombre=<%= Not.getAlum().getNombre()%>&Apellido=<%=Not.getAlum().getApellido()%>&CodCurso=<%=session.getAttribute("CodCurso")%>'"/></td>
		</tr>

		<%
			}
		%>
		</tbody>
	</table>
	


</body>
</html>