<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="Entidad.Materias"%>
<%@page import="Entidad.Cursos"%>
<%@page import="Entidad.Docentes"%>
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
		$('#table_id').DataTable();
	});
</script>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="SyleFormulario.css" />
<body>
	<jsp:include page="Menu.html"></jsp:include>
		<style>
.login-box {
	height: 430px;
	top: 210px;
}
</style>
	<p>
		<%
			ArrayList<Materias> listaMaterias = null;
			if (request.getAttribute("ListarmateriasDropDown") != null) {
				listaMaterias = (ArrayList<Materias>) request.getAttribute("ListarmateriasDropDown");
			}
		%>
		<%
			ArrayList<Docentes> listadocentes = null;
			if (request.getAttribute("listanombreProfDropdown") != null) {
				listadocentes = (ArrayList<Docentes>) request.getAttribute("listanombreProfDropdown");
			}
		%>

	<p></p>
	<form action="Alta_Curso" method="Post">
	<div class="login-box">
	<h1>Bienvenido:
		<%=session.getAttribute("Profesor")%></h1>
	<h1>Agregue un curso</h1>
		<p>Elige un año</p><input name="Año" type="number" min="2010" max="2030">
		 <p>  Elige un Cuatrimestre </p>
		<select class="selected" name="Cuatrimestre">
			<option value=1>Primer Cuatrimestre</option>
			<option value=2>Segundo Cuatrimestre</option>
		</select>

		<p>	Elige una Materia </p> <select class="selected" name="Materias">
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
		<p>

			Elige un Profesor  </p><select name="Profesor" class="selected">
				<%
					if (listadocentes != null)
						for (Docentes Doc : listadocentes) {
				%>
				<option value="<%=Doc.getLegajoDoc()%>"><%=Doc.getLegajoDoc()%>-<%=Doc.getNombre()%>-<%=Doc.getApellido()%>
				</option>

				<%
					}
				%>
			</select>
			<br>
		<p>
			<input type=submit name="btnAgregar" value=Agregar />
			</p></div>
	</form>
	<p>
		<%
			int filas = 0;
			if (request.getAttribute("Funciono") != null) {
				filas = Integer.parseInt(request.getAttribute("Funciono").toString());
				;
			}
		%>

		<%
			if (filas == 1) {
		%>
		<script type="text/javascript">
		var msg = "Se agrego el Curso";
		alert(msg);
	</script>		
		<%
			}
		%>

</body>
</html>