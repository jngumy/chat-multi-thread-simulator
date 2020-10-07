package modelo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class BaseDeDatos implements Serializable
{
    private ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
    private MotorBusqueda buscador;

    public BaseDeDatos()
    {
        super();
    }

    public BaseDeDatos(MotorBusqueda buscador)
    {
        this.buscador = buscador;
    }

    /** Este método delega al motor de busqueda la búsqueda de una pregunta en la base de datos.
     * @param pregunta no nula (string)
     * @return retorna una respuesta a la pregunta recibida - al azar en caso de encontrarla - (string)
     */
    public synchronized String buscaPregunta(String pregunta)
    {
        return buscador.buscaPregunta(pregunta, this.preguntas);
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas)
    {
        this.preguntas = preguntas;
    }

    public ArrayList<Pregunta> getPreguntas()
    {
        return preguntas;
    }

    public Pregunta eligePreguntaAlAzar()
    {
        return this.preguntas.get((int) (Math.random() * (this.preguntas.size())));
    }


    public void guardarBaseDeDatos(String nombreArchivo) throws IOException
    {
        XMLEncoder encoder = null;
        encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(nombreArchivo)));
        encoder.writeObject(preguntas);
        encoder.close();
    }

    public void cargarBaseDeDatos(String nombreArchivo) throws IOException
    {
        XMLDecoder decoder = null;
        decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nombreArchivo)));
        this.preguntas = (ArrayList<Pregunta>) decoder.readObject();
        decoder.close();
    }


    /**En este método ,cada respuesta original del usuario, se agregará a la base de datos como respuesta a
la pregunta formulada por el robot. O sea en caso de no figurar entre las respuestas registradas.
     * @param pregunta string no nulo
     * @param respuesta string no nulo
     */
    void intentarAgregarRespuesta(String pregunta, String respuesta)
    {
        Iterator it = this.preguntas.iterator();
        Pregunta aux = null;
        boolean encontro = false;
        while (it.hasNext() && !encontro)
        {
            aux =(Pregunta) it.next();
            if( aux.getPregunta().equals(pregunta))
               encontro = true;
        }
        if (encontro)
        {
          it = aux.getRespuestas().iterator();
          String string = null;
          encontro= false;
          while(it.hasNext() && !encontro)
          {
              string = (String) it.next();
              if(string.compareTo(respuesta)==0)
                 encontro = true;
          }
          if(!encontro)
             aux.agregarRespuesta(respuesta);
        }
    }
}
