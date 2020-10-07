package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;

public class SimuladorDeChat
  extends Observable
{
  private ArrayList<Conversacion> conversaciones = new ArrayList<Conversacion>();
  private ArrayList<Participante> participantes = new ArrayList<Participante>();
  private BaseDeDatos bd = null;
  private Usuario usuario = null;

  public SimuladorDeChat(BaseDeDatos bd)
  {
    this.bd = bd;
  }

    /**Este método synchronized sirve para que el robot converse con otro participante. Adquiere el bloqueo y si no tiene con quien conversar, espera
     *busca una conversacion disponible, de todas las que haya posible en el chat. 
     *Notifica al controlador (observador), la conversacion que mantiene
     * @param p Participante (Robot) en cuestion
     */
    public synchronized void conversaRobot(Participante p)
  {
    Conversacion aux = this.getConversacionDisponible(p);
    String cartelito;
    while (aux == null)
    {
      try
      {
        System.out.println(p + " Espera, no tiene con quien conversar");
        wait();
        aux = this.getConversacionDisponible(p);

      }
      catch (InterruptedException e)
      {
      }
    }
    cartelito = p.realizaAccion(aux);
    setChanged();
    InformacionOjo informacion = new InformacionOjo(cartelito, aux);
    notifyObservers(informacion);
    notifyAll();

  }

  public void setUsuario(Usuario usuario)
  {
    this.usuario = usuario;
  }

  public Usuario getUsuario()
  {
    return usuario;
  }

    /**Este método lo utiliza el usuario, capaz de hablar con otros participantes a traves de la ventana. Busca la conversacion disponible entre
     * el participante elegido en la ventana y el usuario, para transmitirle el mensaje (pregunta o respuesta)
     * @param mensaje String recibido del controlador proveniente del textField de la ventana.
     * @param p participante elegido desde la ventana
     * @param privado parámetro para saber si se trata de una conversacion pública o privada.
     */
    public synchronized void buscaConversacionUsuario(String mensaje,Participante p, boolean privado)
  {
    Iterator it = this.getIteratorConversaciones();
    Conversacion aux = null;
    boolean encontro = false;
    while (it.hasNext() && !encontro)
    {
      aux = (Conversacion) it.next();
      if (((aux.getParticipante1().equals(p) || aux.getParticipante2().equals(p)) &&
          (aux.getParticipante1().equals(usuario) || aux.getParticipante2().equals(usuario))) && aux.isPrivada()==privado)
        encontro = true;
    }
    conversaUsuario(aux,encontro,mensaje);
    
  }
  
  private void conversaUsuario(Conversacion aux, boolean encontro, String mensaje)
  {
      if (!encontro)
        aux = null;
      else
      {
        if (aux.getPregunta() == null)
        {
          aux.setPregunta(mensaje, usuario);
          aux.setEmisor(usuario);
        }
        else if (aux.getEmisor() != usuario)
        {
          aux.setRespuesta(mensaje);
          this.bd.intentarAgregarRespuesta(aux.getPregunta(), aux.getRespuesta());
        }
      }
      String cartelito= usuario.realizaAccion(aux);
      if(aux.getRespuesta()!=null)
         aux.reiniciarConversacion();
      setChanged();
      InformacionOjo  informacion = new InformacionOjo(cartelito, aux);
      notifyObservers(informacion);
      notifyAll();
  }

    /**Este método, invocado por conversaRobot(), elige al azar una conversacion disponible en la que 
     * pueda participar el robot p. (preguntar o contestar)
     * @param p Participante (robot) que quiere conversar
     * @return retorna una Conversación disponible. Puede ser null (no encontró alguna disponible)
     */
    private synchronized Conversacion getConversacionDisponible(Participante p)
  {
    ArrayList<Conversacion> array = this.getConversaciones(p);
    Iterator it = array.iterator();
    Conversacion aux = null;
    ArrayList<Conversacion> disponible = new ArrayList<Conversacion>();
    
    while (it.hasNext())
    {
      aux = (Conversacion) it.next();
      if (this.disponible(aux, p))
         disponible.add(aux);
    }
    
    if(!disponible.isEmpty())
    {
        Collections.shuffle(disponible);
        aux = disponible.get(0);
    }
    else
         aux = null;
    
    return aux;
  }

    /**Este método agrega un participante a la sala de chat. Ademas de ello, agrega al arrayList de Conversaciones del simulador de chat, las 
     * posibles conversaciones entre el participante recientemente agregado y todos los existentes en la sala de chat.
     * @param p Es un participante a agregar.
     * @throws RobotExistenteException Excepcion lanzada si un nombre de Participante - Robot - se repite
     */
    public synchronized void agregarParticipante(Participante p) throws RobotExistenteException
  {
    
    if (buscaRobot(p))
        throw new RobotExistenteException();
    else
    {
    int aux = (this.participantes.size() - 1);

    for (int i = 0; i <= aux; i++)
    {
      Conversacion conversacion = new Conversacion(p, this.participantes.get(i));
      this.conversaciones.add(conversacion);
    }
    this.participantes.add(p);
    notifyAll();
    }

  }

  /* public synchronized void borraParticipante(Participante p)
  {
    Iterator it = this.getIteratorConversaciones();
    Conversacion aux = null;

    while (it.hasNext())
    {
      aux = (Conversacion) it.next();
      if (aux.getParticipante1().equals(p) || aux.getParticipante2().equals(p))
        this.conversaciones.remove(aux);
    }
    this.participantes.remove(p);
  } */

    /**Este método devuelve un ArrayList  de conversaciones en las que 
     * puede participar p
     * @param p Participante 
     * @return ArrayList no null de conversaciones
     */
    private ArrayList<Conversacion> getConversaciones(Participante p)
  {
    Iterator it = this.getIteratorConversaciones();
    ArrayList<Conversacion> array = new ArrayList<Conversacion>();
    Conversacion aux;
    while (it.hasNext())
    {
      aux = (Conversacion) it.next();
      if (aux.getParticipante1().equals(p) || aux.getParticipante2().equals(p))
      {
        array.add(aux);
      }
    }
    return array;
  }

  private boolean disponible(Conversacion c, Participante p)
  {
    return (c.getEmisor() == null || c.getEmisor() != p);
  }

  public Iterator getIteratorConversaciones()
  {
    return this.conversaciones.iterator();
  }

  public BaseDeDatos getBd()
  {
    return bd;
  }
  
  public boolean buscaRobot(Participante p)
  {
      Participante aux= null;
      boolean encontro = false;
      Iterator it = this.participantes.iterator();
      while (it.hasNext() && !encontro)
      {
         aux = (Participante)it.next();
         if (aux.getNombre().equals(p.getNombre()))
              encontro=true;
      }
      return encontro;
  }
  
  
  public Iterator getParticipantesIterator()
  {
    return this.participantes.iterator();
  }
  
  public synchronized void addConversacionPrivada(Conversacion c)
  {
      this.conversaciones.add(c);
      notifyAll();
  }
  
  public void finalizarHilos()
  {
      Iterator it = this.getParticipantesIterator();
      Participante aux= null;
      while (it.hasNext())
      {
          aux= (Participante) it.next();
          if(!(aux.getNombre().equals("Usuario")))
                aux.setActivo(false);

      }
  }
  
}
