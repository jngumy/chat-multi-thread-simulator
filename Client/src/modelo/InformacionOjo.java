package modelo;

public class InformacionOjo
{
    private String cartelito;
    private Conversacion conversacion ;

    public InformacionOjo(String cartelito, Conversacion conversacion)
    {
        this.cartelito = cartelito;
        this.conversacion = conversacion;
    }

    public void setCartelito(String cartelito)
    {
        this.cartelito = cartelito;
    }

    public String getCartelito()
    {
        return cartelito;
    }

    public void setConversacion(Conversacion conversacion)
    {
        this.conversacion = conversacion;
    }

    public Conversacion getConversacion()
    {
        return conversacion;
    }
}
