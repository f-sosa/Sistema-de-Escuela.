package negocioimpl;

import java.util.ArrayList;

import Entidad.Materias;
import daoimpl.MateriasDaoimpl;
import negocio.MateriasNeg;

public class MateriasNegimpl implements MateriasNeg{
	
	public ArrayList<Materias> listarmaterias() {
		ArrayList<Materias> lista = new ArrayList<Materias>();
		MateriasDaoimpl MatDao = new MateriasDaoimpl();			 
		 lista = MatDao.listarmaterias();		 		 		
		return lista; 		
	 }

}
