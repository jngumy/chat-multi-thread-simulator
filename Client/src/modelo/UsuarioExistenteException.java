package modelo;

public class UsuarioExistenteException extends Exception
{


    public UsuarioExistenteException(String string)
    {
        super(string);
    }

    public UsuarioExistenteException()
    {
        super();
    }
}
