package negocioimpl;

import java.util.ArrayList
;

import Entidad.Provincias;
import daoimpl.ProvinciaDaoimpl;
import negocio.ProvinciaNeg;

public class ProvinciaNegimpl implements ProvinciaNeg{

	
	
	 public ArrayList<Provincias> ListarProvincias() {				
		 ProvinciaDaoimpl ProvDao = new ProvinciaDaoimpl();		 
		 ArrayList<Provincias> ListarProvincias= ProvDao.ListarProvincias();	
		return ListarProvincias; 		
	 }
	
	
	
}
