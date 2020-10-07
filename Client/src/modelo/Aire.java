package modelo;

public class Aire extends Participante
{

  public Aire()
  {
    super();
  }

    @Override
    public String toString()
    {
        
        return "(Aire)";
    }

    @Override
  public String realizaAccion(Conversacion c)
  {
    return null;
  }

    @Override
    public String esCompatible(Participante p)
    {
        return p.compatibilidadAire();
    }

    @Override
    public String compatibilidadAgua()
    {
        return "Más adelante";
    }

    @Override
    public String compatibilidadFuego()
    {
        return "Yo te llamo";
    }

    @Override
    public String compatibilidadTierra()
    {
        return "No";
    }

    @Override
    public String compatibilidadAire()
    {
        return "Si";
    }
}
