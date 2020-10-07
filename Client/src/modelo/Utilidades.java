package modelo;

import java.util.Random;


public class Utilidades
{
  public static Random r = new Random();

  public Utilidades()
  {
    super();
  }

  public static int getNumeroAleatorio(int valor)
  {
    return r.nextInt(valor);
  }

  public static void espera()
  {
    try
    {
      Thread.sleep(r.nextInt(15000));
    }
    catch (InterruptedException e)
    {
    }
  }
}
