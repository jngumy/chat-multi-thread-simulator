package modelo;

public class Tierra extends Participante
{
    public Tierra()
    {
        super();
    }

    @Override
    public String toString()
    {
        
        return "(Tierra)";
    }

    @Override
    public String realizaAccion(Conversacion c)
    {
        return null;
    }

    @Override
    public String esCompatible(Participante p)
    {
        return p.compatibilidadTierra();
    }

    @Override
    public String compatibilidadAgua()
    {
        return "Yo te llamo";
    }

    @Override
    public String compatibilidadFuego()
    {
        return "Más adelante";
    }

    @Override
    public String compatibilidadTierra()
    {
        return "Si";
    }

    @Override
    public String compatibilidadAire()
    {
        return "No";
    }
}
