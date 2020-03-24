package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Menaje;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Menaje de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Equipo A-09
 */
class SQLMenaje {
	/* ****************************************************************
	 * 			Constantes // TODO cambiar PersistenciaParranderos
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaParranderos.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaParranderos pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLMenaje(PersistenciaParranderos pp)
	{
		this.pp = pp;
	}

	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Menaje a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param id - el identificador del menaje
	 * @param nombre - nombre del menaje
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarMenaje(PersistenceManager pm, long id, String nombre){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSirven () + "(id, nombre) values (?, ?)");
		// TODO cambiar dar tabla por Menaje 
		q.setParameters(id, nombre);
		return (long)q.executeUnique();   
	}	
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar Menaje de la base de datos de Alohandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombre - El nombre del menaje
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarMenajePorNombre (PersistenceManager pm, String nombre)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBar () + " WHERE nombre = ?");
     // TODO cambiar dar tabla por Menaje 
        q.setParameters(nombre);
        return (long) q.executeUnique();
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar Menaje de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El id del menaje
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarMenajePorId (PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBar () + " WHERE id = ?");
		// TODO cambiar dar tabla por Menaje 
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Menajes de la 
	 * base de datos de alohandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombre - El nombre del menaje
	 * @return una lista de menajes que tienen el nombre dado
	 */
	public List<Menaje> darMenajesPorNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + " WHERE nombre = ?");
		// TODO cambiar dar tabla por Menaje 
		q.setResultClass(Menaje.class);
		q.setParameters(nombre);
		return (List<Menaje>) q.executeList();		
	}
	
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Menaje de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El id del menaje
	 * @return el objeto Menaje que tiene el identificador dado
	 */
	public Menaje darMenajePorId(PersistenceManager pm, long id){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + " WHERE id = ?");
		// TODO cambiar dar tabla por Menaje 
		q.setResultClass(Menaje.class);
		q.setParameters(id);
		return (Menaje) q.executeUnique();
	}
	
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS MENAJES de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return todos los menajes en la tabla Menajes
	 */
	public List<Menaje> darMenajes(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar ());
		// TODO cambiar dar tabla por Menaje 
		q.setResultClass(Menaje.class);
		return (List<Menaje>) q.executeList();
	}
}