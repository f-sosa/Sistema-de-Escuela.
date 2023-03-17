<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="Entidad.Alumnos"%>
<%@page import="Entidad.Docentes"%>
<%@page import="Entidad.Cursos"%>
<%@page import="Entidad.Materias"%>
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
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <script defer src="theme.js"></script>
  <link rel="stylesheet" href="style.css" />
  <link
    href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700&display=swap"
    rel="stylesheet"
  />
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="SyleFormulario.css" />
</head>

<body>
	<style>
	.login-box{
    height: 210px;
    top: 110px;
}</style>
	<jsp:include page="Menu.html"></jsp:include>

		<%
			ArrayList<Docentes> listaDocentes = null;
			if (request.getAttribute("listaProf") != null) {
				listaDocentes = (ArrayList<Docentes>) request.getAttribute("listaProf");
			}
		%>
		<%
			ArrayList<Cursos> Busquedacursos = null;
			if (request.getAttribute("listaCur") != null) {
				Busquedacursos = (ArrayList<Cursos>) request.getAttribute("listaCur");
			}

			ArrayList<Materias> ListaNombreMaterias = null;
			if (request.getAttribute("ListaMaterias") != null) {
				ListaNombreMaterias = (ArrayList<Materias>) request.getAttribute("ListaMaterias");
			}

			int tabla = 0;
			if (request.getAttribute("Tablaop") != null) {
				tabla = (int) request.getAttribute("Tablaop");
			}
		%>
		<%
			if (tabla == 1) {
				int eliminadoC = 0;
				if (request.getAttribute("Eliminado") != null) {
					eliminadoC = (int) request.getAttribute("Eliminado");
				}
				String cursoel = "";
				if (request.getAttribute("cursoel") != null) {
					cursoel = (String) request.getAttribute("cursoel");
				}
		%>
	<div class="login-box">
	<h1>
				Bienvenido:
				<%=session.getAttribute("Profesor")%></h1>
	<h1>Cursos</h1>
	<input type=submit name="Alta_Curso" value="Añadir curso"
		onclick="window.location.href='Alta_Curso?Alta_Curso=1'" />
	<p></p>
	</div>
	<%
		if (eliminadoC == 1) {
	%>
	<script type="text/javascript"> 
    var msg = "El curso: <%=cursoel%> ha sido eliminado";
    alert(msg);
</script>
	<%
		}
	%>
	<table id="table_id" class="display" >
		<thead>
			<tr class="w3-red">
				<th>Codigo curso</th>
				<th>Materia</th>
				<th>Cuatrimestre</th>
				<th>Año</th>
				<th>Docente</th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody class="grid_Linea">
			<%
				if (Busquedacursos != null)
						for (Cursos Cur : Busquedacursos) {
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

				<td><%=Cur.getDoc().getLegajoDoc()%>-<%=Cur.getDoc().getNombre()%>-<%=Cur.getDoc().getApellido()%></td>

				<td><input type="submit" name="btnAlumnos" value="Ver Alumnos"
					onclick="window.location.href='VerAlumnoDeCurso?btnAlumnos=1&CodCurso=<%=Cur.getCodcurso()%>'"></td>

				<td><input type="submit" name="btnAgregarAlumnos"
					value="Añadir Alumnos"
					onclick="window.location.href='AgregarAlumnosACursos?btnAgregarAlumnos=1&CodCurso=<%=Cur.getCodcurso()%>'"></td>

				<td><input type="submit" name="btnModificar"
					value="Modificar curso"
					onclick="window.location.href='CursoModificar?btnModificar=1&CodCurso=<%=Cur.getCodcurso()%>'" /></td>

				<td><input type="submit" name="btnEliminar"
					value="Eliminar curso"
					onclick="window.location.href='Eliminar_Curso?btnEliminar=1&CodCurso=<%=Cur.getCodcurso()%>'" /></td>

			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<%
		}
	%>
	<%
		if (tabla == 2) {
			int eliminadoD = 0;
			if (request.getAttribute("Eliminado") != null) {
				eliminadoD = (int) request.getAttribute("Eliminado");
			}
			int legeliminado = 0;
			if (request.getAttribute("LegEliminado") != null) {
				legeliminado = (int) request.getAttribute("LegEliminado");
			}
			String nombre = "";
			if (request.getAttribute("nombre") != null) {
				nombre = (String) request.getAttribute("nombre");
			}
			String apellido = "";
			if (request.getAttribute("apellido") != null) {
				apellido = (String) request.getAttribute("apellido");
			}
	%>
	<div class="login-box">
	<h1>
				Bienvenido:
				<%=session.getAttribute("Profesor")%></h1>
	<h1>Profesores</h1>
	<input type=submit name="Alta_Profesor" value="Añadir profesor"
		onclick="window.location.href='Alta_Profesor?Alta_Profesor=1'" />
	<p></p>
	</div>
	<%
		if (eliminadoD == 1) {
	%>
	<script type="text/javascript">
    var msg = "El profesor: <%=legeliminado%>-<%=nombre%>-<%=apellido%> ha sido eliminado";
    alert(msg);
</script>
	<%
		}
	%>
	<table id="table_id" class="display">
		<thead>
			<tr class="w3-red">
				<th>Legajo</th>
				<th>Dni</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Fecha nacimiento</th>
				<th>Dirección</th>
				<th>Provincia</th>
				<th>Localidad</th>
				<th>Email</th>
				<th>Teléfono</th>
				<th>Usuario</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody class="grid_Linea">
			<%
				if (listaDocentes != null)
						for (Docentes Doc : listaDocentes) {
			%>
			<tr>
				<td><%=Doc.getLegajoDoc()%></td>
				<td><%=Doc.getDni()%></td>
				<td><%=Doc.getNombre()%></td>
				<td><%=Doc.getApellido()%></td>
				<td><%=Doc.getNacimiento()%></td>
				<td><%=Doc.getDireccion()%></td>
				<td><%=Doc.getProv().getNombre()%></td>
				<td><%=Doc.getLoc().getNombre()%></td>
				<td><%=Doc.getEmail()%></td>
				<td><%=Doc.getTelefono()%></td>
				<td><%=Doc.getNombreusuario()%></td>
				<td><input type="submit" name="btnModificar"
					value="Modificar Profesor"
					onclick="window.location.href='ProfesorModificar?btnModificar=1&LegajoDoc=<%=Doc.getLegajoDoc()%>'" /></td>
				<td><input type="submit" name="btnEliminar"
					value="Eliminar profesor"
					onclick="window.location.href='ProfesorEliminar?btnEliminar=1&legajoprof=<%=Doc.getLegajoDoc()%>&nombre=<%=Doc.getNombre()%>&apellido=<%=Doc.getApellido()%>'" /></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<%
		}
	%>
	<%
		ArrayList<Alumnos> listaAlumnos = null;
		if (request.getAttribute("listaAlum") != null) {
			listaAlumnos = (ArrayList<Alumnos>) request.getAttribute("listaAlum");
		}
	%>
	<%
		if (tabla == 3) {
			String nombre = "";
			if (request.getAttribute("nombre") != null) {
				nombre = (String) request.getAttribute("nombre");
			}
			String apellido = "";
			if (request.getAttribute("apellido") != null) {
				apellido = (String) request.getAttribute("apellido");
			}
			String legajoal = "";
			if (request.getAttribute("legajoal") != null) {
				legajoal = (String) request.getAttribute("legajoal");
			}
			int eliminadoA = 0;
			if (request.getAttribute("Eliminado") != null) {
				eliminadoA = (int) request.getAttribute("Eliminado");
			}
	%>
		<div class="login-box">
	<h1>
				Bienvenido:
				<%=session.getAttribute("Profesor")%></h1>
	<h1>Alumnos</h1>
		<input type=submit name="Alta_Alumno" value="Añadir alumno"
			onclick="window.location.href='Alta_Alumno?Alta_Alumno=1'" />
	<p></p>
	</div>
	<%
		if (eliminadoA == 1) {
	%>
	<script type="text/javascript">
    var msg = "El alumno: <%=legajoal%>-<%=nombre%>-<%=apellido%> ha sido eliminado";
		alert(msg);
	</script>
	<%
		}
	%>
	<table id="table_id" class="display">
		<thead>
			<tr class="w3-red">
				<th>Legajo</th>
				<th>Dni</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Fecha Nacimiento</th>
				<th>Dirección</th>
				<th>Provincia</th>
				<th>Localidad</th>
				<th>Email</th>
				<th>Teléfono</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody class="grid_Linea">
			<%
				if (listaAlumnos != null)
						for (Alumnos Alum : listaAlumnos) {
			%>
			<tr>

				<td><%=Alum.getLegajoAl()%></td>
				<td><%=Alum.getDni()%></td>
				<td><%=Alum.getNombre()%></td>
				<td><%=Alum.getApellido()%></td>
				<td><%=Alum.getNacimiento()%></td>
				<td><%=Alum.getDireccion()%></td>
				<td><%=Alum.getProv().getNombre()%></td>
				<td><%=Alum.getLoc().getNombre()%></td>
				<td><%=Alum.getEmail()%></td>
				<td><%=Alum.getTelefono()%></td>

				<td><input type="submit" name="btnModificar" value="Modificar Alumno" onclick="window.location.href='AlumnoModificar?btnModificar=1&LegajoAl=<%=Alum.getLegajoAl()%>'" /></td>

				<td><input type="submit" name="btnEliminar"
					value="Eliminar alumno"
					onclick="window.location.href='AlumnoEliminar?btnEliminar=1&LegajoAl=<%=Alum.getLegajoAl()%>&Nombre=<%=Alum.getNombre()%>&Apellido=<%=Alum.getApellido()%>'" /></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>

	<%
		}
	%>
</body>
</html>