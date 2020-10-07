package modelo;


import java.util.Iterator;

public abstract class Participante
{
    private String nombre;
    private SimuladorDeChat sc;
    protected boolean activo;

    public Participante(String nombre, SimuladorDeChat sc)
    {
        this.nombre = nombre;
        this.sc = sc;
        this.activo = true;
    }

    public void setActivo(boolean activo)
    {
        this.activo = activo;
    }

    public Participante()
    {
        super();
    }


    public abstract String realizaAccion(Conversacion c);


    public String getNombre()
    {
        return nombre;
    }

    public SimuladorDeChat getSc()
    {
        return sc;
    }

    public abstract String esCompatible(Participante p);

    public abstract String compatibilidadAgua();

    public abstract String compatibilidadFuego();

    public abstract String compatibilidadTierra();

    public abstract String compatibilidadAire();
}
