package modelo;

import controlador.Controlador;

import java.io.IOException;

import java.util.ArrayList;
import java.util.TreeSet;

import vista.IVista;
import vista.VentanaPrincipal;

public class Main
{
    public Main()
    {
        super();
    }

    public static void main(String[] args)
    {
        MotorBusqueda motorBusqueda = new MotorBusqueda();
        BaseDeDatos bd = new BaseDeDatos(motorBusqueda);
        SimuladorDeChat sc = new SimuladorDeChat(bd);
        IVista ventana = new VentanaPrincipal();
        Controlador controlador = new Controlador(sc, ventana);
        controlador.addObservado(sc);
        ventana.setControlador(controlador);

        /*  ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
        Pregunta p1 = new Pregunta("¿Que hora es  en tu lugar de origen?");
        Pregunta p2 = new Pregunta("¿Como estas querido amigo?");
        Pregunta p3 = new Pregunta("¿De donde sos?");
        Pregunta p4 = new Pregunta("¿Tenes algun perrito en tu casa?");
        Pregunta p5 = new Pregunta("¿Que me estas queriendo decir?");
        Pregunta p6 = new Pregunta("¿Tomamos cafe?");
        Pregunta p7 = new Pregunta("¿Como le esta yendo a Argentina?");
        Pregunta p8 = new Pregunta("¿Que estas haciendo en este momento?");
        Pregunta p9 = new Pregunta("¿Que vas a hacer el dia de mañana?");
        Pregunta p10 = new Pregunta("¿Como estuvo tu dia?");


        p1.agregarRespuesta("4:30");
        p1.agregarRespuesta("6:30");
        p1.agregarRespuesta("10:30");

        p2.agregarRespuesta("No se");
        p2.agregarRespuesta("Mmm no muy bien");
        p2.agregarRespuesta("Excelente !");
        p3.agregarRespuesta("Soy de Jujuy");
        p3.agregarRespuesta("Soy de Bs As");
        p3.agregarRespuesta("Soy de Mar del Plata");

        p4.agregarRespuesta("Sii tengo, me encantan");
        p4.agregarRespuesta("No, tengo gatos");
        p4.agregarRespuesta("No, no tengo ningun perrito");

        p5.agregarRespuesta("Nada nada");
        p5.agregarRespuesta("Que sos una gran persona");
        p5.agregarRespuesta("Que este trabajo nos obliga a interactuar :)");

        p7.agregarRespuesta("Muy mal");
        p7.agregarRespuesta("No muy bien, yo sacaria a Sampaoli y a Caballero");
        p7.agregarRespuesta("Bien, aguante Messi");

        p8.agregarRespuesta("En este momento, soñando con un 10 en Progra 3");
        p8.agregarRespuesta("Estoy pensando que Lionel, Guille e Ivonne son los mejores profesores");
        p8.agregarRespuesta("Hablando con vos");

        p9.agregarRespuesta("Mañana voy a descansar y dormir mucho");
        p9.agregarRespuesta("Mañana voy a ver el Mundial");
        p9.agregarRespuesta("Mañana voy a pasear al perro");
        p9.agregarRespuesta("No lo se aún");

        p10.agregarRespuesta("Estuvo genial");
        p10.agregarRespuesta("Mejor no preguntes");


        preguntas.add(p1);
        preguntas.add(p2);
        preguntas.add(p3);
        preguntas.add(p4);
        preguntas.add(p5);
        preguntas.add(p6);
        preguntas.add(p7);
        preguntas.add(p8);
        preguntas.add(p9);
        preguntas.add(p10);


        bd.setPreguntas(preguntas); */

        /*   try
        {
            bd.guardarBaseDeDatos("BasedeDatos");
        } catch (IOException e)
        {
        } */

        ventana.arranca();

    }
}
