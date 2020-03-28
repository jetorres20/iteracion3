package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Reserva;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Reserva de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Equipo A-09
 */
class SQLReserva {
	/* ****************************************************************
	 * 			Constantes 
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAlohandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaAlohandes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLReserva(PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una Reserva a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param id
	 * @param recintoId
	 * @param personaId
	 * @param fechaReserva
	 * @param fechaInicio
	 * @param fechaFin
	 * @param personas
	 * @param subTotal
	 * @param fechaCancelacion
	 * @param cobroAdicional
	 * @param activa
	 * @return el numero de tuplas insertadas
	 */
	public long adicionarReserva(PersistenceManager pm, long id, long recintoId, long personaId, Timestamp fechaReserva, Timestamp fechaInicio, Timestamp fechaFin, int personas, double subTotal, Timestamp fechaCancelacion, double cobroAdicional, int activa){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaReservas () + "(id, recintoId, personaId, fechaReserva, fechaInicio, fechaFin, personas, subTotal, fechaCancelacion, cobroAdicional, activa) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters( id,  recintoId,  personaId,  fechaReserva,  fechaInicio,  fechaFin,  personas,  subTotal,  fechaCancelacion,  cobroAdicional,  activa);
		return (long)q.executeUnique();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar una reserva de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - el id de la reserva
	 * @return numero de tuplas eliminadas
	 */
	public long eliminarReservaPorId(PersistenceManager pm, long id){
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReservas () + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	
	
	/**
	 *  Crea y ejecuta la sentencia SQL para encontrar la información de Una Reserva alohandes por su id
	 * @param pm - El manejador de persistencia
	 * @param id - id de la reserva
	 * @return objeto reserva con el id dado
	 */
	public Reserva darReservaPorId(PersistenceManager pm, long id){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReservas () + " WHERE id = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(id);
		return (Reserva) q.executeUnique();
	}
	
	
	/**
	 *  Crea y ejecuta la sentencia SQL para encontrar la información de las reservas de una persona especifica
	 * @param pm - El manejador de persistencia
	 * @param personaId
	 * @return lista de objetos reserva de la persona con el id dado
	 */
	public List<Reserva> darReservasPersona(PersistenceManager pm, long personaId){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReservas () + "WHERE personaId = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(personaId);
		return (List<Reserva>) q.executeList();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de las reservas de una recnto especifico
	 * @param pm
	 * @param recintoId
	 * @return lista de objetos reserva del recinto con el id dado
	 */
	public List<Reserva> darReservasRecinto(PersistenceManager pm, long recintoId){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReservas () + "WHERE recintoId = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(recintoId);
		return (List<Reserva>) q.executeList();
	}
	
	
	/**
	 *  Crea y ejecuta la sentencia SQL para encontrar la información de todos Las Reservas de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return todas las reservas de la base de datos
	 */
	public List<Reserva> darReservas(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReservas ());
		q.setResultClass(Reserva.class);
		return (List<Reserva>) q.executeList();
	}	
	
	

	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS OPERARIOS Y SUS GANANCIAS de la 
	 * base de datos de Alohandes. Incluye, con 0, los operarios que no han vendido nada.
	 * @param pm - El manejador de persistencia
	 * @return Una lista de arreglos de objetos, de tamaño 5. Los elementos del arreglo corresponden a los datos del bebedor,
	 * y del número de visitas realizadas:
	 * 		(id, nombre, ciudad, presupuesto) del bebedor y numVisitas
	 */
	public List<Object> darOperariosYGanancias (PersistenceManager pm)
	{
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) -1);
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		String formatted = format1.format(cal.getTime());
		System.out.println(formatted);
		
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_YEAR, 1);
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
		String formatted1 = format2.format(cal1.getTime());
		System.out.println(formatted);
		
	    String sql = "SELECT id, (SELECT sum (subTotal + coalesce(cobroAdicional,0)) as GananciasAnio FROM ";
	    sql += pp.darTablaReservas() + " r1 WHERE r1.ID = " + pp.darTablaReservas() + ".ID AND FECHAFIN > " + formatted1;
	    sql += " ), (SELECT sum (subTotal + coalesce(cobroAdicional,0)) as GananciasAnioCorrido FROM ";
	    sql += pp.darTablaReservas() + " r2 WHERE r2.ID = " + pp.darTablaReservas() + ".ID AND FECHAFIN > " + formatted;
	    sql += ") FROM " + pp.darTablaReservas();
	    sql	+= " GROUP BY id";
		
	    Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS OFERTAS MAS FAMOSAS de la 
	 * base de datos de Alohandes. Incluye, con 0, los operarios que no han vendido nada.
	 * @param pm - El manejador de persistencia
	 * @return Una lista de arreglos de objetos, de tamaño 5. Los elementos del arreglo corresponden a los datos del bebedor,
	 * y del número de visitas realizadas:
	 * 		(id, nombre, ciudad, presupuesto) del bebedor y numVisitas
	 */
	public List<Object> darMejoresRecintos (PersistenceManager pm)
	{
		
		
		
	    String sql = "recintoId, cnt as reservas FROM   (SELECT   recintoid , COUNT(recintoId) AS cnt FROM ";
	    sql += pp.darTablaReservas() + " GROUP BY recintoid";
	    sql += " )  WHERE rownum <=20";
		
	    Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
	
	
}
