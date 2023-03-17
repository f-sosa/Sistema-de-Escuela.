<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="Entidad.Docentes"%>
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
	<style>
.login-box {
	height: 1130px;
	top: 570px;
}
</style>
	<jsp:include page="Menu.html"></jsp:include>
	<%
		ArrayList<Docentes> listadocentes = null;
		if (request.getAttribute("ListaDocentes") != null) {
			listadocentes = (ArrayList<Docentes>) request.getAttribute("ListaDocentes");
		}
		ArrayList<Provincias> ListaProv = null;
		if (request.getAttribute("listaprov") != null) {
			ListaProv = (ArrayList<Provincias>) request.getAttribute("listaprov");
		}
		Docentes Doc = new Docentes();
		if (request.getAttribute("Docentes") != null) {
			Doc = (Docentes) request.getAttribute("Docentes");
		}
		int ProfEliminado = 0;
		if (request.getAttribute("ProfEliminado") != null) {
			ProfEliminado = (int) request.getAttribute("ProfEliminado");
		}
	%>
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
	<form action="ProfesorModificar" method="post">
		<div class="login-box">
			<h1>
				Bienvenido:
				<%=session.getAttribute("Profesor")%></h1>
			<h1>
				Modificando profesor:
				<%=Doc.getLegajoDoc()%></h1>
			<input type="hidden" name="LegajoDoc" value="<%=Doc.getLegajoDoc()%>">
			<p>Ingrese el dni</p>
			<input type=text name="txtdni" value="<%=Doc.getDni()%>" required
				placeholder="Ej:42469737" pattern="[0-9]{2}[0-9]{3}[0-9]{3}" onkeypress="return ((event.charCode >= 48 && event.charCode <= 57))"/>
			<p>Ingrese el Nombre</p>
			<input type=text name="txtNombre" value="<%=Doc.getNombre()%>"
				required
				onkeypress="return (event.charCode ==32 || (event.charCode >= 97 && event.charCode <= 122) || (event.charCode >= 65 && event.charCode <= 90))" />
			<p>Ingrese el Apellido</p>
			<input type=text name="txtApellido" value="<%=Doc.getApellido()%>"
				required
				onkeypress="return (event.charCode ==32 || (event.charCode >= 97 && event.charCode <= 122) || (event.charCode >= 65 && event.charCode <= 90))" />
			<p>Ingrese la Fecha nacimiento</p>
			<input type="date" name="txtnacimiento"
				value="<%=Doc.getNacimiento()%>" min="1920-01-01" max="2018-12-31"
				required />
			<p>Ingrese la Dirección</p>
			<input type=text name="txtdireccion" value="<%=Doc.getDireccion()%>"
				required /> </select> Ingrese el Email
			</p>
			<input type=text name="txtemail" value="<%=Doc.getEmail()%>" required />
			<p>Ingrese el Telefono</p>
			<input type=text name="txtelefono" value="<%=Doc.getTelefono()%>"
				required placeholder="Ej: 1158867994"
				pattern="[0-9]{2}[0-9]{4}[0-9]{4}" onkeypress="return ((event.charCode >= 48 && event.charCode <= 57))"/>
			<p>Ingrese el Nombre de Usuario</p>
			<input type=text name="txtusuario"
				value="<%=Doc.getNombreusuario()%>" required />
			<p>Ingrese la Contraseña</p>
			<input type=text name="txtContraseña"
				value="<%=Doc.getContrasena()%>" required />
			<p>Seleccione la Provincia</p>
			<select class="selected" name="Provincia" id="ddlProv" required
				onchange="llenarLocalidades()">
				<option value=<%=Doc.getProv().getIdProvincia()%> selected><%=Doc.getProv().getNombre()%></option>

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
				<option value=<%=Doc.getLoc().getIdLocalidad()%> selected><%=Doc.getLoc().getNombre()%></option>
			</select>
			<p>
			<p>Administrador</p>
			<select class="selected" name="Administrador">
				<%
					if (Doc.getAdministrador().equals("1")) {
				%>
				<option value="1" selected>Si*</option>
				<option value="0">No</option>
				<%
					} else {
				%>
				<option value="1">Si</option>
				<option value="0" selected>No*</option>
				<%
					}
				%>
			</select> <input type=submit name="btnModificar2" value=Modificar>
		</div>
	</form>
	<%
		if (ProfEliminado == 1) {
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
				<th>Nacimiento</th>
				<th>Dirección</th>
				<th>Localidad</th>
				<th>Provincia</th>
				<th>Email</th>
				<th>Teléfono</th>
				<th>Usuario</th>
			</tr>
		</thead>
		<tbody class="grid_Linea">
			<%
				if (listadocentes != null)
					for (Docentes Doc2 : listadocentes) {
			%>

			<tr>
				<td><%=Doc2.getLegajoDoc()%></td>
				<td><%=Doc2.getDni()%></td>
				<td><%=Doc2.getNombre()%></td>
				<td><%=Doc2.getApellido()%></td>
				<td><%=Doc2.getNacimiento()%></td>
				<td><%=Doc2.getDireccion()%></td>
				<td><%=Doc2.getLoc().getNombre()%></td>
				<td><%=Doc2.getProv().getNombre()%></td>
				<td><%=Doc2.getEmail()%></td>
				<td><%=Doc2.getTelefono()%></td>
				<td><%=Doc2.getNombreusuario()%></td>

				<%
					}
				%>
			</tr>
		</tbody>
	</table>

</body>
</html>