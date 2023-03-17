<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Entidad.Cursos"%>
<%@ page import="Entidad.Alumnos"%>
<%@ page import="Entidad.Notas"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<script type="text/javascript">
	$(document).ready(function() {
		$('#table_id2').DataTable();
	});
</script>
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="SyleFormulario.css" />
<title>Modificar Notas</title>
</head>
<body>
	<style>
	.login-box{
    height: 140px;
    top: 80px;
}</style>
	<%
		int cursocodselec = -1;
		if (request.getAttribute("cursocodselec") != null) {
			cursocodselec = (int) request.getAttribute("cursocodselec");
		}
		ArrayList<Cursos> listaCursos = null;
		if (request.getAttribute("ListaCursos") != null) {
			listaCursos = (ArrayList<Cursos>) request.getAttribute("ListaCursos");
		}
		ArrayList<Notas> listaNotas = null;
		if (request.getAttribute("ListaNotas") != null) {
			listaNotas = (ArrayList<Notas>) request.getAttribute("ListaNotas");
		}
		int modifico = 0;
		if (request.getAttribute("Modifico") != null) {
			modifico = (int) request.getAttribute("Modifico");
		}
	%>
	<div class="bg">

	<jsp:include page="MenuDelProf.html"></jsp:include>
<div class="login-box">
	<h1>Bienvenido:
	<%=session.getAttribute("Profesor")%></h1>
	<h1>Elige un Curso</h1>
	</div>
<table id="table_id" class="display">
		<thead>
			<tr class="w3-red">
				<th>Codigo curso</th>
				<th>Materia</th>
				<th>Cuatrimestre</th>
				<th>Año</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody class="grid_Linea">
			<%
				if (listaCursos != null)
						for (Cursos Cur : listaCursos) {
			%>

			<tr>
				<td><%=Cur.getCodcurso()%></td>
				<td><%=Cur.getMat().getNombre()%></td>
				<%
					if (Cur.getCuatrimestre() == 1) {
				%>
				<td>Primer Cuatrimestre</td>
				<%
					} else {
				%>
				<td>Segundo Cuatrimestre</td>
				<%
					}
				%>

				<td><%=Cur.getAno()%></td>

				<td><input type="submit" name="ModificarNotas" value="Ver alumnos/Modificar notas"onclick="window.location.href='ModificarAlumnosXCurso?ModificarNotas=1&Curso=<%= Cur.getCodcurso() %>'"/></td>
				<td><input type="submit" name="Porcentajes" value="Ver Porcentaje"onclick="window.location.href='PorcentajeDeNotas?Porcentajes=1&Curso=<%= Cur.getCodcurso() %>'"/></td>
			</tr>
			<%
					}
				%>
		</tbody>
		</table>
		<%if(modifico == 1){ %>
		<script type="text/javascript">
		var msg = "Se modifico correctamente";
		alert(msg);
		<% }%>
	</script>
		<% if(cursocodselec!= -1) {%>
		<center>Modificando alumnos del curso: <%= cursocodselec%></center>
	<form action="ModificarAlumnosXCurso" method="post">
		<table id="table_id2" class="display">
			<thead>
				<tr class="w3-red">
					<th>Legajo</th>
					<th>Nombre</th>
					<th>Apellido</th>
					<th>Nota 1</th>
					<th>Nota 2</th>
					<th>Recuperatorio 1</th>
					<th>Recuperatorio 2</th>
					<th>Estado academico</th>
				</tr>
			</thead>
			<tbody class="grid_Linea">
				<%
					if (listaNotas != null)
						for (Notas Not : listaNotas) {
				%>
				<tr>
					<td><%=Not.getAlum().getLegajoAl()%><input type="hidden" name="Legajo" value="<%=Not.getAlum().getLegajoAl()%>"></td>
					<td><%=Not.getAlum().getNombre()%><input type="hidden" name="Cursoselec" value="<%=cursocodselec%>"></td>
					<td><%=Not.getAlum().getApellido()%><input type="hidden" name="Curso" value="<%=cursocodselec%>"></td>
										
					<td><input type="number" name="Nota1" min= "0" max= "10" style="color:black;" value=<%=Not.getNota1()%>></td>
					<td><input type="number" name="Nota2" min= "0" max= "10" style="color:black;" value=<%=Not.getNota2()%>></td>
					<td><input type="number" name="Rec1" min= "0" max= "10" style="color:black;" value=<%=Not.getRec1()%>></td>
					<td><input type="number" name="Rec2" min= "0" max= "10" style="color:black;" value=<%=Not.getRec2()%>></td>
					<td><select name="EstadoAcademico">
							<%
								if (Not.getEstadoAcademico() == 1) {
							%>
							<option Value=1 Selected>Regular*</option>
							<option Value=0>Libre</option>
							<%
								} else {
							%>
							<option Value=1>Regular</option>
							<option Value=0 Selected>Libre*</option>
							<%
								}
							%>
					</select></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<input type="submit" name="btnModificartodo" value="Modificar">
	</form>
	<%} %>
</body>
</html>