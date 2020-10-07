package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Robot extends DecoratorParticipante implements Runnable
{
    private Participante p;


    public Robot(String nombre, SimuladorDeChat sc, Participante p)
    {
        super(nombre, sc);
        this.p = p;
    }

    /**Metodo invocado por conversaRobot(), que pregunta o contesta según la conversacion que eligió
     * @param c Conversación utilizada
     * @return retorna el mensaje que se mostrará por la ventana (string)
     */
    @Override
    public String realizaAccion(Conversacion c)
    {
        String cartelito = null;

        if (c.getEmisor() != null && !(c.getEmisor().equals(this)))
        {
            if(c.getPregunta().compareTo("¿Tomamos cafe?")==0)
                c.setRespuesta(this.esCompatible(c.getEmisor()));
            else
            {
                c.setRespuesta(this.getSc()
                                   .getBd()
                                   .buscaPregunta(c.getPregunta()));
            }
            cartelito = (this + " le dice a " + c.getEmisor().toString() + ": " + c.getRespuesta() + "\n");
            c.reiniciarConversacion();
        } else
        {
            c.setPregunta(this.getSc()
                              .getBd()
                              .eligePreguntaAlAzar()
                              .getPregunta(), this);
            if (c.getParticipante1() == this)
                cartelito = (this + " le dice a " + c.getParticipante2() + ": " + c.getPregunta() + "\n");

            else
                cartelito = (this + " le dice a " + c.getParticipante1() + ": " + c.getPregunta() + "\n");

        }
        return cartelito;
    }


    @Override
    public String toString()
    {
        return this.getNombre() + p;
    }

    @Override
    public void run()
    {
        while (this.activo)
        {
            Utilidades.espera();
            this.getSc().conversaRobot(this);
            Utilidades.espera();
        }
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
