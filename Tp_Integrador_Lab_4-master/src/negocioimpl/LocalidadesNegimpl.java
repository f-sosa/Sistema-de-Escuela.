package negocioimpl;
import java.util.ArrayList;
import Entidad.Localidad;
import daoimpl.LocalidadesDaoimpl;
import negocio.LocalidadesNeg;
public class LocalidadesNegimpl implements LocalidadesNeg{

	public ArrayList<Localidad> CargarLocalidades(String idprov) {				
		 LocalidadesDaoimpl LocDao = new LocalidadesDaoimpl();		 
		 ArrayList<Localidad> ListanombreLocalidad= LocDao.CargarLocalidades(idprov);				
		return ListanombreLocalidad; 		
	 }
	
	
}
