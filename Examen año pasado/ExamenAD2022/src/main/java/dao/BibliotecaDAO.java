package dao;

import java.util.HashSet;
import models.Libro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author FranciscoRomeroGuill
 */
public class BibliotecaDAO {
    
    private static SessionFactory sessionFactory;
    
    static{
        try{
            Configuration cfg = new Configuration();
            cfg.configure();//Comprobamos que el archivo de configuracion no tiene ningun problema
            sessionFactory = cfg.buildSessionFactory();

            System.out.println("SessionFactory creada con exito!");

        }catch(Exception ex){
            System.out.println("Error iniciando Hibernate");
            System.out.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void saveLibro(Libro libro) {
        Session session = sessionFactory.openSession();
        /* Guarda un libro con todos sus ejemplares en la base de datos */
        try {

            // Inicia la transacción
            session.beginTransaction();

            // Guarda el libro y sus ejemplares
            session.save(libro);

            // Confirma la transacción
            session.getTransaction().commit();

            System.out.println("Libro guardado correctamente con sus ejemplares.");
        } catch (Exception e) {
            // Si hay un error, realiza un rollback de la transacción
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println("Error al guardar el libro con sus ejemplares.");
            e.printStackTrace();
        }
    }

    public HashSet<Libro> findByEstado(String estado){
        Session session = sessionFactory.openSession();

 /*
         Devuelve el conjunto de libros que tenga el estado indicado
        */
        HashSet<Libro> librosConEstado = new HashSet<>();

        try {
            // Realiza la consulta para obtener los libros con el estado dado
            String hql = "SELECT DISTINCT e.libro FROM Ejemplar e WHERE e.estado = :estado";
            librosConEstado.addAll(session.createQuery(hql, Libro.class)
                    .setParameter("estado", estado)
                    .getResultList());

            System.out.println("Libros con estado '" + estado + "' encontrados.");
        } catch (Exception e) {
            System.out.println("Error al buscar libros por estado.");
            e.printStackTrace();
        }

        return librosConEstado;
    }


    public void printInfo(){

        /*
          Muestra por consola todos los libros de la biblioteca y el número
          de ejemplares disponibles de cada uno.

          Debe imprimirlo de esta manera:

          Biblioteca
          ----------
          Como aprender java en 24h. (3)
          Como ser buena persona (1)
          Aprobando exámenes fácilmente (5)
          ...

        */
        System.out.println("Método printInfo no implementado");

    }

}
