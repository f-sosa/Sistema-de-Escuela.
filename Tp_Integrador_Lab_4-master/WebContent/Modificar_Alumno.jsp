<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="Entidad.Alumnos"%>
<%@page import="Entidad.Provincias"%>
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
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="SyleFormulario.css" />
</head>
<body>
	<jsp:include page="Menu.html"></jsp:include>
	<style>
.login-box {
	height: 910px;
	top: 455px;
}
</style>
	<%
		ArrayList<Alumnos> listaalumnos = null;
		if (request.getAttribute("ListaAlumnos") != null) {
			listaalumnos = (ArrayList<Alumnos>) request.getAttribute("ListaAlumnos");
		}
		ArrayList<Provincias> ListaProv = null;
		if (request.getAttribute("listaprov") != null) {
			ListaProv = (ArrayList<Provincias>) request.getAttribute("listaprov");
		}
		Alumnos Alum = new Alumnos();
		if (request.getAttribute("Alumnos") != null) {
			Alum = (Alumnos) request.getAttribute("Alumnos");
		}
		int AlumEliminado = 0;
		if (request.getAttribute("AlumEliminado") != null) {
			AlumEliminado = (int) request.getAttribute("AlumEliminado");
		}
	%>

	<p>
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
	<form action="AlumnoModificar" method="post">
		<div class="login-box">
			<h1>
				Bienvenido:
				<%=session.getAttribute("Profesor")%></h1>
			<h1>
				Modificando alumno:
				<%=Alum.getLegajoAl()%></h1>
			<input type="hidden" name="LegajoAl" value="<%=Alum.getLegajoAl()%>">
			<p>Ingrese el dni</p>
			<input type=text name="txtdni" value="<%=Alum.getDni()%>" required
				placeholder="Ej:42469737" pattern="[0-9]{2}[0-9]{3}[0-9]{3}" onkeypress="return ((event.charCode >= 48 && event.charCode <= 57))"/>
			<p>Ingrese el Nombre</p>
			<input type=text name="txtNombre" value="<%=Alum.getNombre()%>"
				required
				onkeypress="return (event.charCode ==32 || (event.charCode >= 97 && event.charCode <= 122) || (event.charCode >= 65 && event.charCode <= 90))" />
			<p>Ingrese el Apellido</p>
			<input type=text name="txtApellido" value="<%=Alum.getApellido()%>"
				required
				onkeypress="return (event.charCode ==32 || (event.charCode >= 97 && event.charCode <= 122) || (event.charCode >= 65 && event.charCode <= 90))" />
			<p>Ingrese la Fecha nacimiento</p>
			<input type="date" name="txtnacimiento"
				value="<%=Alum.getNacimiento()%>" min="1920-01-01" max="2018-12-31"
				required />
			<p>Ingrese la Dirección</p>
			<input type=text name="txtdireccion" value="<%=Alum.getDireccion()%>"
				required /> </select> Ingrese el Email
			</p>
			<input type=text name="txtemail" value="<%=Alum.getEmail()%>"
				required />
			<p>Ingrese el Telefono</p>
			<input type=text name="txtelefono" value="<%=Alum.getTelefono()%>"
				required placeholder="Ej: 1158867994"
				pattern="[0-9]{2}[0-9]{4}[0-9]{4}" onkeypress="return ((event.charCode >= 48 && event.charCode <= 57))"/>
			<p>Seleccione la Provincia</p>
			<select class="selected" name="Provincia" id="ddlProv" required
				onchange="llenarLocalidades()">
				<option value=<%=Alum.getProv().getIdProvincia()%> selected><%=Alum.getProv().getNombre()%></option>
				<option value="">Seleccione provincia</option>

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
				<option value=<%=Alum.getLoc().getIdLocalidad()%> selected><%=Alum.getLoc().getNombre()%></option>



			</select>
			<p>
				<input type=submit name="btnModificar2" value=Modificar>
		</div>
	</form>
	<%
		if (AlumEliminado == 1) {
	%>
	<script type="text/javascript">
		var msg = "Se ha modificado satisfactoriamente";
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
			</tr>
		</thead>
		<tbody class="grid_Linea">
			<%
				if (listaalumnos != null)
					for (Alumnos Alum2 : listaalumnos) {
			%>
			<tr>

				<td><%=Alum2.getLegajoAl()%></td>
				<td><%=Alum2.getDni()%></td>
				<td><%=Alum2.getNombre()%></td>
				<td><%=Alum2.getApellido()%></td>
				<td><%=Alum2.getNacimiento()%></td>
				<td><%=Alum2.getDireccion()%></td>
				<td><%=Alum2.getProv().getNombre()%></td>
				<td><%=Alum2.getLoc().getNombre()%></td>
				<td><%=Alum2.getEmail()%></td>
				<td><%=Alum2.getTelefono()%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>