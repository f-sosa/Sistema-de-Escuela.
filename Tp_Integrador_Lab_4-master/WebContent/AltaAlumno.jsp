<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="Entidad.Alumnos"%>
<%@page import="Entidad.Provincias"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>

<script src="http://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<script
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="SyleFormulario.css" />
<meta charset="ISO-8859-1">
</head>

<body>
	<jsp:include page="Menu.html"></jsp:include>
	<style>
.login-box {
	height: 870px;
	top: 450px;
}
</style>
	<script>
		$(document).ready(function() {
			$('#myTable').DataTable();
		});
	</script>

	<%
		ArrayList<Alumnos> listaAlumnos = null;
		if (request.getAttribute("ListaAlumnos") != null) {
			listaAlumnos = (ArrayList<Alumnos>) request.getAttribute("ListaAlumnos");
		}
	%>
	<%
		ArrayList<Provincias> ListaProv = null;
		if (request.getAttribute("listaprov") != null) {
			ListaProv = (ArrayList<Provincias>) request.getAttribute("listaprov");
		}
	%>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function llenarLocalidades() {

			$('#ddlProv').on('change', function() {
				var provincia = $("#ddlProv option:selected").val();

				$.ajax({
					type : 'POST',
					data : {
						prov : provincia
					},
					url : 'servletLocalidades',
					success : function(result) {
						$('#ddlLoc').html(result);
					}
				});
			});
		});
	</script>
	<form action="Alta_Alumno" method="Post">
		<div class="login-box">
			<h1>
				Bienvenido:
				<%=session.getAttribute("Profesor")%></h1>
			<h1>Agregue un alumno</h1>
			<p>Ingrese el dni</p>
			<input type=text name="txtdni" required placeholder="Ej:42469737"
				pattern="[0-9]{2}[0-9]{3}[0-9]{3}" onkeypress="return ((event.charCode >= 48 && event.charCode <= 57))"/>
			<p>Ingrese el Nombre</p>
			<input type=text name="txtNombre" required
				onkeypress="return (event.charCode ==32 || (event.charCode >= 97 && event.charCode <= 122) || (event.charCode >= 65 && event.charCode <= 90))" />
			<p>Ingrese el Apellido</p>
			<input type=text name="txtApellido" required
				onkeypress="return (event.charCode ==32 || (event.charCode >= 97 && event.charCode <= 122) || (event.charCode >= 65 && event.charCode <= 90))" />
			<p>Ingrese la Fecha nacimiento</p>
			<input type="date" name="txtnacimiento" min="1920-01-01"
				max="2018-12-31" required />
			<p>Ingrese la Dirección</p>
			<input type=text name="txtdireccion" required /> Ingrese el Email
			</p>
			<input type=email name="txtemail" required />
			<p>Ingrese el Telefono</p>
			<input type=text name="txtelefono" required
				placeholder="Ej: 1158867994" pattern="[0-9]{2}[0-9]{4}[0-9]{4}" onkeypress="return ((event.charCode >= 48 && event.charCode <= 57))"/>
			<p></p>
			<p>Seleccione la Provincia</p>
			<select class="selected" name="Provincia" id="ddlProv" required
				onchange="llenarLocalidades()">
				<option value="" selected>Seleccione provincia</option>

				<%
					if (ListaProv != null) {
						for (Provincias prov : ListaProv) {
				%>

				<option value=<%=prov.getIdProvincia()%>><%=prov.getNombre()%>
				</option>

				<%
					}
					}
				%>

			</select>
			<p>Seleccione la Localidad</p>
			<select class="selected" name='sLctLoc' id='ddlLoc' style='' required>
				<option value="" selected>Seleccione Localidad</option>


			</select>
			<p>
				<input type=submit name="btnAgregar" value=Agregar />
		</div>
	</form>
	<%
		int filas = 0;
		if (request.getAttribute("Agregado") != null) {
			filas = Integer.parseInt(request.getAttribute("Agregado").toString());;
		}
	%>

	<%
		int Existente = 0;
		if (request.getAttribute("Existente") != null) {
			Existente = Integer.parseInt(request.getAttribute("Existente").toString());;
		}
	%>
	<%
		if (Existente == 1) {
	%>
	<script type="text/javascript">
		var msg = "El Dni <%=session.getAttribute("Dni")%> Ya existe";
		alert(msg);
	</script>
	<%
		}
	%>

	<%
		if (filas == 1) {
	%>
	<script type="text/javascript">
		var msg = "Se agrego Alumno con los datos <%=session.getAttribute("Dni")%>-<%=session.getAttribute("Nombre")%>-
		<%=session.getAttribute("Apellido")%>";
		alert(msg);
	</script>
	<%
		}
	%>



</body>
</html>