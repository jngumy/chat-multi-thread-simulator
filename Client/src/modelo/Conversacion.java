package modelo;

public class Conversacion
{
    private Participante participante1;
    private Participante participante2;
    private String pregunta;
    private String respuesta;
    private Participante emisor;
    private boolean privada;
    private int indice;

    public void setPrivada(boolean privada)
    {
        this.privada = privada;
    }

    public Conversacion(Participante participante1, Participante participante2)
    {
        this.participante1 = participante1;
        this.participante2 = participante2;
        this.pregunta = null;
        this.respuesta = null;
        this.emisor = null;
        this.privada = false;
        this.indice = -1;
    }

    public Participante getParticipante1()
    {
        return participante1;
    }

    public Participante getParticipante2()
    {
        return participante2;
    }


    public String getPregunta()
    {
        return pregunta;
    }

    public String getRespuesta()
    {
        return respuesta;
    }

    public void setRespuesta(String respuesta)
    {
        this.respuesta = respuesta;
    }

    public void setPregunta(String pregunta, Participante p)
    {
        this.pregunta = pregunta;
        this.emisor = p;
    }


    public Participante getEmisor()
    {
        return emisor;
    }

    public void reiniciarConversacion()
    {
        this.emisor = null;
        this.pregunta = null;
        this.respuesta = null;
    }

    public void setIndice(int indice)
    {
        this.indice = indice;
    }

    public int getIndice()
    {
        return indice;
    }

    public void setEmisor(Participante emisor)
    {
        this.emisor = emisor;
    }

    public boolean isPrivada()
    {
        return privada;
    }

}
