package modelo;

public class Agua extends Participante
{

  public Agua()
  {
    super();
  }

    @Override
    public String toString()
    {
        return "(Agua)";
    }

    @Override
  public String realizaAccion(Conversacion c)
  {
    return null;
  }

    @Override
    public String esCompatible(Participante p)
    {
       return p.compatibilidadAgua();
    }

    @Override
    public String compatibilidadAgua()
    {
        return "Si";
    }

    @Override
    public String compatibilidadFuego()
    {
        return "No";
    }

    @Override
    public String compatibilidadTierra()
    {
       return "Yo te llamo";
    }

    @Override
    public String compatibilidadAire()
    {
        return "Más adelante";
    }
}
