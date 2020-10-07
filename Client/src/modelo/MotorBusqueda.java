package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class MotorBusqueda
{
    private Pregunta preguntaMax;
    private int cantMaxIguales;

    private Pregunta preguntaMaxSimilar;
    private int cantMaxSimilares;

    private Pregunta preguntaElegida;

    public MotorBusqueda()
    {
        this.preguntaMax = null;
        this.preguntaMaxSimilar = null;
        this.cantMaxIguales = 0;
        this.cantMaxSimilares = 0;
    }

    /** Este método recibe una pregunta de cualquier participante y la busca en el Arraylist de preguntas, bajo 3 criterios : exactamente igual, palabras iguales y palabras coincidentes
     * @param pregunta Es un string no null que recibe de cualquier participante
     * @param preguntasBD Arraylist de las preguntas cargadas en la base de datos (no null)
     * @return Retorna una respuesta (string) a la pregunta solicitada
     */
    public String buscaPregunta(String pregunta, ArrayList<Pregunta> preguntasBD)
    {
      this.preguntaMax = null;
      this.preguntaMaxSimilar = null;
      this.cantMaxIguales = 0;
      this.cantMaxSimilares = 0;
      
        boolean encontro = false;
        pregunta = pregunta.toUpperCase();
        
        if(pregunta.startsWith(Character.toString('¿')))
          pregunta = pregunta.substring(1, pregunta.length() - 1);

        String preguntaAux = null;
        Pregunta objetoPregunta = null;

        Iterator it = preguntasBD.iterator();

        while (it.hasNext() && !encontro)
        {
            objetoPregunta = (Pregunta) it.next();
            preguntaAux = objetoPregunta.getPregunta();
            preguntaAux = preguntaAux.toUpperCase();
            preguntaAux = preguntaAux.substring(1, preguntaAux.length() - 1);

            if (pregunta.compareTo(preguntaAux) == 0)
            {
                preguntaMax = objetoPregunta;
                encontro = true; 
            } else
            {
                calculaPalabrasIguales(pregunta, preguntaAux, objetoPregunta);
                calculaPalabrasSimilares(pregunta, preguntaAux, objetoPregunta);
            }
        }

        return this.darRespuesta(eligePregunta());

    }

    /**Este método retorna una respuesta al azar del ArrayList de respuesta de la pregunta.
     * @param pregunta puede ser nula
     * @return Si la pregunta se encuentra en la base de datos, retornara una respuesta al azar. En caso contrario, retornará "No entiendo tu pregunta"
     */
    public String darRespuesta(Pregunta pregunta)
  {
    String respuesta = "No entiendo tu pregunta";
    
    if (pregunta != null)
    { 
      respuesta = pregunta.getRespuesta(Utilidades.getNumeroAleatorio(pregunta.getTamano()));
    }
    return respuesta;
  }

    /**Este método calcula las palabras iguales entre la pregunta recibida y la pregunta original tomada del ArrayList
     * @param preguntaRecibida pregunta recibida de algun Participante (string)
     * @param preguntaOriginal pregunta para comparar
     * @param objetoPregunta objeto pregunta actual para la comparacion maxima 
     */
    private void calculaPalabrasIguales(String preguntaRecibida, String preguntaOriginal, Pregunta objetoPregunta)
    {

        String[] partes = preguntaRecibida.split(" ");
        String[] partesOriginales = preguntaOriginal.split(" ");
        int longitudOriginal = partesOriginales.length;
        int cantIguales = 0;

        for (int i = 0; i < partes.length; i++)
        {
            if (partes[i].length() > 3)
                for (int j = 0; j < longitudOriginal; j++)
                {
                    if (partesOriginales[j].length() > 3)
                    {
                        if (partes[i].equals(partesOriginales[j]))
                            cantIguales++;
                    }
                }
        }


        if ((cantIguales >= 3) && (cantIguales > cantMaxIguales))
        {
            this.setCantMaxIguales(cantIguales);
            this.setPreguntaMax(objetoPregunta);
        }

    }

    /**Este método calcula las palabras similares entre la pregunta recibida y la pregunta original tomada del ArrayList
     * @param preguntaRecibida pregunta recibida de algun Participante (string)
     * @param preguntaOriginal pregunta para comparar
     * @param objetoPregunta objeto pregunta actual para la comparacion maxima 
     */
    private void calculaPalabrasSimilares(String preguntaRecibida, String preguntaOriginal, Pregunta objetoPregunta)
    {

        String[] partes = preguntaRecibida.split(" ");
        String[] partesOriginales = preguntaOriginal.split(" ");

        int longitudOriginal = partesOriginales.length;
        int cantSimilares = 0;

        for (int i = 0; i < partes.length; i++)
        {
            if (partes[i].length() > 3)
                for (int j = 0; j < longitudOriginal; j++)
                {
                    if (partesOriginales[j].length() > 3)
                    {
                        if (partesOriginales[j].indexOf(partes[i]) > -1 || partes[i].indexOf(partesOriginales[j]) > -1)
                            cantSimilares++;
                    }
                }


        }

        if ((cantSimilares >= 3) && (cantSimilares > cantMaxSimilares))
        {
            this.setCantMaxSimilares(cantSimilares);
            this.setPreguntaMaxSimilar(objetoPregunta);
        }

    }

    /**Método que elige la pregunta bajo un criterio de coincidencia
     * @return Retorna el objeto Pregunta seleccionado
     */
    private Pregunta eligePregunta()
    {
        Pregunta aux = null;

        if (this.getPreguntaMax() == null && this.getPreguntaMaxSimilar() != null)
            aux = this.getPreguntaMaxSimilar();
        else if (this.getPreguntaMax() != null && this.getPreguntaMaxSimilar() == null)
            aux = this.getPreguntaMax();
        else if (this.cantMaxIguales < this.cantMaxSimilares)
            aux = this.getPreguntaMaxSimilar();
        else
            aux = this.getPreguntaMax();

        return aux;
    }

    public void setPreguntaMax(Pregunta preguntaMax)
    {
        this.preguntaMax = preguntaMax;
    }

    public void setCantMaxIguales(int cantMaxIguales)
    {
        this.cantMaxIguales = cantMaxIguales;
    }

    public void setPreguntaMaxSimilar(Pregunta preguntaMaxSimilar)
    {
        this.preguntaMaxSimilar = preguntaMaxSimilar;
    }

    public void setCantMaxSimilares(int cantMaxSimilares)
    {
        this.cantMaxSimilares = cantMaxSimilares;
    }

    public void setPreguntaElegida(Pregunta preguntaElegida)
    {
        this.preguntaElegida = preguntaElegida;
    }

    public Pregunta getPreguntaMax()
    {
        return preguntaMax;
    }

    public int getCantMaxIguales()
    {
        return cantMaxIguales;
    }

    public Pregunta getPreguntaMaxSimilar()
    {
        return preguntaMaxSimilar;
    }

    public int getCantMaxSimilares()
    {
        return cantMaxSimilares;
    }

    public Pregunta getPreguntaElegida()
    {
        return preguntaElegida;
    }
}
