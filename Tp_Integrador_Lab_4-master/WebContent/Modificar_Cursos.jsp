<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Entidad.Cursos"%>
<%@ page import="Entidad.Materias"%>
<%@ page import="Entidad.Docentes"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

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
<meta charset="ISO-8859-1">
</head>
<body>
<style>.login-box{
    height: 425px;
    top: 220px;
}</style>
	<jsp:include page="Menu.html"></jsp:include>
	<p>
		<%
			ArrayList<Cursos> listaCursos = null;
			if (request.getAttribute("listaCur") != null) {
				listaCursos = (ArrayList<Cursos>) request.getAttribute("listaCur");
			}
			ArrayList<Materias> listaMaterias = null;
			if (request.getAttribute("ListaMaterias") != null) {
				listaMaterias = (ArrayList<Materias>) request.getAttribute("ListaMaterias");
			}
			ArrayList<Docentes> listaDocentes = null;
			if (request.getAttribute("ListaDocentes") != null) {
				listaDocentes = (ArrayList<Docentes>) request.getAttribute("ListaDocentes");
			}
			int elimino = 0;
			if (request.getAttribute("elimino") != null) {
				elimino = (int) request.getAttribute("elimino");
			}
			int curso = 0;
			if (request.getAttribute("CodCurso") != null) {
				curso = (int) request.getAttribute("CodCurso");
			}
			int cuatrimestreselec = 0;
			if (request.getAttribute("Cuatrimestre") != null) {
				cuatrimestreselec = (int) request.getAttribute("Cuatrimestre");
			}
			String codmateriaselec = "";
			if (request.getAttribute("CodMateria") != null) {
				codmateriaselec = (String) request.getAttribute("CodMateria");
			}
			System.out.print(codmateriaselec);
			int anoselec = 0;
			if (request.getAttribute("Año") != null) {
				anoselec = (int) request.getAttribute("Año");
			}
			int legajoselec = 0;
			if (request.getAttribute("Docente") != null) {
				legajoselec = (int) request.getAttribute("Docente");
			}
			int CursoEliminado = 0;
			if(request.getAttribute("CursoEliminado")!=null){
				CursoEliminado = (int) request.getAttribute("CursoEliminado");
			}	
		%>
		<%
			int filas = 0;
			if (request.getAttribute("cantFilas") != null) {
				filas = Integer.parseInt(request.getAttribute("cantFilas").toString());;
			}
		%>

		<%
			if (filas == 1) {
		%>
		Se modifico Correctamente!!
		<%
			}
		%>
		<%
			if (elimino == 1) {
		%>
		<script type="text/javascript">
			var msg = "Se ha modificado correctamente";
			alert(msg);
		</script>
		<%
			}
			if (elimino == 2) {
		%>
		<script type="text/javascript">
			var msg = "No se ha podido modificar";
			alert(msg);
		</script>
		<%
			}
		%>
	
	<form action="CursoModificar" method="post">
		<div class="login-box">
		<h1>Bienvenido:
		<%=session.getAttribute("Profesor")%></h1>
	<h1>Modificando curso: <%=curso %></h1>
		<input type="hidden" name="CodCurso" value=<%=curso%>> 
				<p>
			Ingrese un Año </p><input name="Año" type="number" min="2010" max="2030"
				value="<%=anoselec%>">
		<p>Elige
		un Cuatrimestre </p><select name="Cuatrimestre" class="selected">
			<%
				if (cuatrimestreselec == 1) {
			%>
			<option value=1 selected>Primer Cuatrimestre*</option>
			<option value=2>Segundo Cuatrimestre</option>
			<%
				} else {
			%>
			<option value=1>Primer Cuatrimestre</option>
			<option value=2 selected>Segundo Cuatrimestre*</option>
			<%
				}
			%>
		</select>
		<p>
			Elige una Materia </p><select name="Materias" class="selected">
				<%
					if (listaMaterias != null)
						for (Materias Mat : listaMaterias) {
							if (codmateriaselec.equals(Mat.getCodMateria())) {
				%>
				<option value="<%=Mat.getCodMateria()%>" selected><%=Mat.getNombre()%>*
					<%
					} else {
				%>
				
				<option value="<%=Mat.getCodMateria()%>"><%=Mat.getNombre()%>
				</option>
				<%
					}
						}
				%>
			</select>
		<p>

			Elige un Profesor </p><select name="Profesor" class="selected">
				<%
					if (listaDocentes != null)
						for (Docentes Doc : listaDocentes) {
							if (legajoselec == Doc.getLegajoDoc()) {
				%>
				<option value="<%=Doc.getLegajoDoc()%>" selected><%=Doc.getLegajoDoc()%>-<%=Doc.getNombre()%>-<%=Doc.getApellido()%>*
				</option>
				<%
					} else {
				%>
				<option value="<%=Doc.getLegajoDoc()%>"><%=Doc.getLegajoDoc()%>-<%=Doc.getNombre()%>-<%=Doc.getApellido()%></option>
				<%
					}
						}
				%>
			</select>

			<input type="submit" name="btnModificar2" value="Modificar" />
			</div>
	</form>
					<%if(CursoEliminado == 1){ %>
<script type="text/javascript"> 
    var msg = "Se ha modificado satisfactoriamente";
    alert(msg);
</script>
<%} %>
	<table id="table_id" class="display">
		<thead>
			<tr class="w3-red">
				<th>Codigo curso</th>
				<th>Materia</th>
				<th>Cuatrimestre</th>
				<th>Año</th>
				<th>Docente</th>

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

				<td><%=Cur.getDoc().getLegajoDoc()%>-<%=Cur.getDoc().getNombre()%>-<%=Cur.getDoc().getApellido()%></td>

			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<p>
	<p>
</body>
</html>