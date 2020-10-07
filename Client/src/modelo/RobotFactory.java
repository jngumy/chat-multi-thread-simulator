package modelo;

public class RobotFactory //Implementa Patron factory
{
    public static final int ROBOTTIERRA = 0;
    public static final int ROBOTFUEGO = 1;
    public static final int ROBOTAGUA = 2;
    public static final int ROBOTAIRE = 3;


    public RobotFactory()
    {
        super();
    }

    public Participante getRobot(int tipo, String nombre, SimuladorDeChat sc)
    {
        Participante aux = null;

        switch (tipo)
        {
        case 0:
            aux = new Robot(nombre,sc,new Tierra());
            break;
        case 1:
            aux = new Robot(nombre,sc,new Fuego());
            break;
        case 2:
            aux = new Robot(nombre,sc,new Agua());
            break;
        case 3:
            aux = new Robot(nombre,sc,new Aire());
            break;        
        }
        return aux;
    }
}
