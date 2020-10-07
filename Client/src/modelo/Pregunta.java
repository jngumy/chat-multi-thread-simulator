package modelo;


import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class Pregunta implements Serializable
{
    private String pregunta;
    private ArrayList <String> respuestas = new ArrayList <String>();


    public Pregunta()
    {
        
    }
    public Pregunta(String pregunta)
    {
        this.pregunta = pregunta;
    }
    
    public void agregarRespuesta(String respuesta)
    {
        this.respuestas.add(respuesta);
    }
    
    public void eliminarRespuesta(String respuesta)
    {
        this.respuestas.remove(respuesta);
    }


    public String getPregunta()
    {
        return pregunta;
    }

    public String getRespuesta(int i)
    {
        return respuestas.get(i);
    }
    
    public int getTamano()
    {
        return respuestas.size();
    }


    public void setPregunta(String pregunta)
    {
        this.pregunta = pregunta;
    }

    public void setRespuestas(ArrayList<String> respuestas)
    {
        this.respuestas = respuestas;
    }

    public ArrayList<String> getRespuestas()
    {
        return respuestas;
    }
}


