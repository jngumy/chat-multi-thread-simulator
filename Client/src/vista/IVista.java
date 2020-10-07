package vista;

import controlador.Controlador;

import java.util.ArrayList;

import java.util.Iterator;

import modelo.Participante;

public interface IVista
{
    void arranca();
    void actualizaChatGrupal(String s);
    void actualizaChatIndividual(int indice, String mensaje);
    String obtenerNombre();
    int ObtenerTipoElemento();
    String ObtenerTipoParticipante();
    void setControlador(Controlador c);
    void actualizaLista(Iterator <Participante> participantes);
    Participante devolverParticipanteSeleccionado();
    String obtenerMensajeGrupal();
    String obtenerMensajeIndividual();
    void agregarPestañaChat(Participante p);
    Participante obtenerParticipanteGrupal();
    String obtenerParticipantePestania();
    int obtenerIndicePestaña();
    void habilitarBotonAgregar();
}
