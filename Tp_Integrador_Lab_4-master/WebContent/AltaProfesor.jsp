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
	<jsp:include page="Menu.html"></jsp:include>
	<style>
.login-box {
	height: 1110px;
	top: 570px;
}
</style>
	<%
		ArrayList<Docentes> listadocentes = null;
		if (request.getAttribute("ListaDocentes") != null) {
			listadocentes = (ArrayList<Docentes>) request.getAttribute("ListaDocentes");
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
	
	$(document).ready(function llenarLocalidades(){
	
		$('#ddlProv').on('change', function(){
			var provincia = $("#ddlProv option:selected").val();
		
			$.ajax({
				type: 'POST',
				data: {prov: provincia},
				url: 'servletLocalidades',
				success: function(result){
					$('#ddlLoc').html(result);
				}
			});
		});
	});
	
</script>
	<form action="Alta_Profesor" method="Post">
		<div class="login-box">
			<h1>
				Bienvenido:
				<%=session.getAttribute("Profesor")%></h1>
			<h1>Agregue un profesor</h1>
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
			<input type=text name="txtemail" required />
			<p>Ingrese el Telefono</p>
			<input type=text name="txtelefono" required
				placeholder="Ej: 1158867994" pattern="[0-9]{2}[0-9]{4}[0-9]{4}" onkeypress="return ((event.charCode >= 48 && event.charCode <= 57))"/>
			<p>Ingrese el Nombre de Usuario</p>
			<input type=text name="txtusuario" required />
			<p>Ingrese la Contraseña</p>
			<input type=text name="txtContraseña" required />
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
			<p>Administrador</p>
			<select name="Administrador" class="selected">
				<option value="0">No</option>
				<option value="1">Si</option>
			</select> <input type=submit name="btnAgregar" value=Agregar />
		</div>
	</form>

	<%
		int Agregado = 0;
		if (request.getAttribute("Agregado") != null) {
			Agregado = Integer.parseInt(request.getAttribute("Agregado").toString());;
		}
	%>

	<%
		int Existente = 0;
		if (request.getAttribute("Existente") != null) {
			Existente = Integer.parseInt(request.getAttribute("Existente").toString());;
		}
	%>
	<%
		int ExistenteUsuario = 0;
		if (request.getAttribute("ExistenteUsuario") != null) {
			ExistenteUsuario = Integer.parseInt(request.getAttribute("ExistenteUsuario").toString());;
		}
	%>
	<%
		if (Existente == 1) {
	%>
	<script type="text/javascript">
		var msg = "El Dni ya existe";
		alert(msg);
	</script>

	<%
		}
	%>
	<%
		if (ExistenteUsuario == 1) {
	%>
	<script type="text/javascript">
		var msg = "el Usuario <%=session.getAttribute("Usuario")%> ya Existe ";
		alert(msg);
	</script>

	<%
		}
	%>

	<%
		if (Agregado == 1) {
	%>
	<script type="text/javascript">
		var msg = "Se agrego Profesor con los datos  <%=session.getAttribute("Dni")%>-	<%=session.getAttribute("Nombre")%>-	<%=session.getAttribute("Apellido")%>
		";
		alert(msg);
	</script>
	<%
		}
	%>



</body>
</html>