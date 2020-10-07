package modelo;

public class Fuego extends Participante
{
  public Fuego()
  {
    super();
  }

  @Override
  public String realizaAccion(Conversacion c)
  {
    return null;
  }

    @Override
    public String toString()
    {
        
        return "(Fuego)";
    }

    @Override
    public String esCompatible(Participante p)
    {
        return p.compatibilidadFuego();
    }

    @Override
    public String compatibilidadAgua()
    {
        return "No";
    }

    @Override
    public String compatibilidadFuego()
    {
        return "Si";
    }

    @Override
    public String compatibilidadTierra()
    {
        return "Más adelante";
    }

    @Override
    public String compatibilidadAire()
    {
        return "Yo te llamo";
    }
}
