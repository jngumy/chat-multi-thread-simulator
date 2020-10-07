package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class Usuario
  extends DecoratorParticipante
{

  private static Usuario usuario_instancia = null;
  private Participante p;


  private Usuario(SimuladorDeChat sc, Participante p)
  {
    super("Yo", sc);
    this.p = p;
    sc.setUsuario(this);
  }

  public static Participante getInstance(int tipo, SimuladorDeChat sc)
    throws UsuarioExistenteException
  {
    if (usuario_instancia == null)
    {
      Participante aux = null;
      switch (tipo)
      {
        case 0:
          aux = new Tierra();
          break;
        case 1:
          aux = new Fuego();
          break;
        case 2:
          aux = new Agua();
          break;
        case 3:
          aux = new Aire();
          break;
      }
      usuario_instancia = new Usuario(sc, aux);
    }
    else
      throw new UsuarioExistenteException("El usuario ya existe");

    return usuario_instancia;

  }

  @Override
  public String realizaAccion(Conversacion c)
  {
    String cartelito = null;
    
    if (c.getEmisor() != null && !(c.getEmisor().equals(this)))
    {
      cartelito = ("Usuario le dice a " + c.getEmisor().toString() + ": " + c.getRespuesta()+"\n");
      c.reiniciarConversacion();
    }
    else
    {
      if (c.getParticipante1() == this)
        cartelito =("Usuario le dice a " + c.getParticipante2() + ": " + c.getPregunta()+"\n");
      
      else
        cartelito =("Usuario le dice a " + c.getParticipante1() + ": " + c.getPregunta()+"\n");
    
    }
    return cartelito;
  }


  @Override
  public String toString()
  {
    return "Usuario" + p;
  }


    @Override
    public String esCompatible(Participante p)
    {
        return p.esCompatible(this.p);
    }

    @Override
    public String compatibilidadAgua()
    {
       return this.p.compatibilidadAgua();
    }

    @Override
    public String compatibilidadFuego()
    {
        return this.p.compatibilidadFuego();
    }

    @Override
    public String compatibilidadTierra()
    {
        return this.p.compatibilidadTierra();
    }

    @Override
    public String compatibilidadAire()
    {
       return this.p.compatibilidadAire();
    }
}
