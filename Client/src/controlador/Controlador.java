package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.io.IOException;

import java.util.Iterator;
import java.util.Observable;

import java.util.Observer;

import javax.swing.JOptionPane;

import modelo.Conversacion;
import modelo.InformacionOjo;
import modelo.Participante;
import modelo.Robot;
import modelo.RobotExistenteException;
import modelo.RobotFactory;
import modelo.SimuladorDeChat;

import modelo.Usuario;

import modelo.UsuarioExistenteException;

import vista.IVista;

public class Controlador implements ActionListener, Observer, MouseListener, WindowListener
{
    private SimuladorDeChat modelo;
    private IVista vista;
    private Observable observado;
    private RobotFactory robotFactory = new RobotFactory();

    public Controlador(SimuladorDeChat modelo, IVista vista)
    {
        this.modelo = modelo;
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if (actionEvent.getActionCommand().equalsIgnoreCase("AGREGAR PARTICIPANTE"))
        {

            Participante aux = null;
            if (vista.ObtenerTipoParticipante().equalsIgnoreCase("Usuario"))
            {
                try
                {
                    aux = Usuario.getInstance(vista.ObtenerTipoElemento(), modelo);
                    modelo.agregarParticipante(aux);
                    vista.actualizaLista(modelo.getParticipantesIterator());
                    JOptionPane.showMessageDialog(null, "¡Usuario creado exitosamente!", "Messenger", JOptionPane.INFORMATION_MESSAGE);

                } catch (UsuarioExistenteException e)
                {
                    JOptionPane.showMessageDialog(null, "¡Usuario ya existente!", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (RobotExistenteException e)
                {
                }
            } else if (vista.ObtenerTipoParticipante().equalsIgnoreCase("Robot"))
            {
                aux = robotFactory.getRobot(vista.ObtenerTipoElemento(), vista.obtenerNombre(), modelo);
                try
                {
                    modelo.agregarParticipante(aux);
                    vista.actualizaLista(modelo.getParticipantesIterator());
                    JOptionPane.showMessageDialog(null, "¡Robot creado exitosamente!", "Messenger", JOptionPane.INFORMATION_MESSAGE);
                    new Thread((Robot) aux).start();
                    ;
                } catch (RobotExistenteException e)
                {
                    JOptionPane.showMessageDialog(null, "¡Robot ya existente!", "Error", JOptionPane.ERROR_MESSAGE);

                }
            }

        } else /* if (actionEvent.getActionCommand().equalsIgnoreCase("ELIMINAR PARTICIPANTE"))
        {
            Participante aux = vista.devolverParticipanteSeleccionado();
            aux.setActivo(false);
            modelo.borraParticipante(aux);
            vista.actualizaLista(modelo.getParticipantesIterator());
            
        } else */ if (actionEvent.getActionCommand().equalsIgnoreCase("BOTON ENVIAR GRUPAL"))
        {
            this.modelo.buscaConversacionUsuario(this.vista.obtenerMensajeGrupal(),
                                                 this.vista.obtenerParticipanteGrupal(),false);
        } else if (actionEvent.getActionCommand().equalsIgnoreCase("ENVIAR INDIVIDUAL"))
        {
            Participante aux = null;
            Iterator it = this.modelo.getParticipantesIterator();
            String nombre = vista.obtenerParticipantePestania();
            boolean encontro = false;
            while (it.hasNext() && !encontro)
            {
                aux = (Participante) it.next();
                if (aux.getNombre().equals(nombre))
                    encontro = true;
            }
            if(encontro)
              this.modelo.buscaConversacionUsuario(vista.obtenerMensajeIndividual(), aux, true);      
        }
        else
            if(actionEvent.getActionCommand().equalsIgnoreCase("CARGAR"))
            {
            try
            {
                this.modelo
                    .getBd()
                    .cargarBaseDeDatos("BaseDeDatos");
                JOptionPane.showMessageDialog(null, "¡Carga Base de Datos exitosa!", "Messenger", JOptionPane.INFORMATION_MESSAGE);
                this.vista.habilitarBotonAgregar();
                
            } catch (IOException e)
            {
            }
            }
           else
                if(actionEvent.getActionCommand().equalsIgnoreCase("GUARDAR"))
               {
            try
            {
                this.modelo
                    .getBd()
                    .guardarBaseDeDatos("BaseDeDatos");
                JOptionPane.showMessageDialog(null, "Se guardó la Base de Datos", "Messenger", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e)
            {
            }

        }
    }

    public void addObservado(Observable o)
    {
        this.observado = o;
        o.addObserver(this);
    }

    public void deleteObservado(Observable o)
    {
        if (this.observado == o)
        {
            this.observado = null;
            o.deleteObserver(this);
        }
    }

    @Override
    public void update(Observable observable, Object object)
    {
        InformacionOjo aux = (InformacionOjo) object;
        if(aux.getConversacion().isPrivada())
                vista.actualizaChatIndividual(aux.getConversacion().getIndice(), aux.getCartelito());
        else
                vista.actualizaChatGrupal(aux.getCartelito());
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (e.getClickCount() == 2)
        {
            vista.agregarPestañaChat(vista.devolverParticipanteSeleccionado());
            Iterator it = this.modelo.getParticipantesIterator();
            String nombre = vista.obtenerParticipantePestania();
            Participante aux = null;
            boolean encontro = false;
            while (it.hasNext() && !encontro)
            {
                aux = (Participante) it.next();
                if (aux.getNombre().equals(nombre))
                    encontro = true;
            }
            if(encontro)
            {
                
                Conversacion conversacionPrivada = new Conversacion(modelo.getUsuario(),aux);
                conversacionPrivada.setPrivada(true);
                conversacionPrivada.setIndice(vista.obtenerIndicePestaña());
                modelo.addConversacionPrivada(conversacionPrivada);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    @Override
    public void windowOpened(WindowEvent e)
    {
        // TODO Implement this method
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        this.modelo.finalizarHilos();
    }

    @Override
    public void windowClosed(WindowEvent e)
    {
        // TODO Implement this method
    }

    @Override
    public void windowIconified(WindowEvent e)
    {
        // TODO Implement this method
    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {
        // TODO Implement this method
    }

    @Override
    public void windowActivated(WindowEvent e)
    {
        // TODO Implement this method
    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {
        // TODO Implement this method
    }
}
