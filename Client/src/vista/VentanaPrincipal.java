package vista;

import controlador.Controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import java.util.ArrayList;

import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import modelo.Participante;

public class VentanaPrincipal extends JFrame implements IVista
{
    private JTextField escribeGrupal;
    private JTextField escribeIndividual;
    private JTextArea chatGrupalTextArea;
    private JButton agregarParticipanteButton;
    //private JButton eliminarParticipanteButton;
    private DefaultListModel<Participante> modeloLista = new DefaultListModel<Participante>();
    private JList<Participante> listaParticipantes = new JList<Participante>();

    private String[] tipos =
    {
     "Agua", "Aire", "Fuego", "Tierra"
    };
    private String[] tipoParticipante =
    {
         "Usuario", "Robot"
    };
    private JComboBox menuElemento;
    private JComboBox menuParticipante;
    private JComboBox<Participante> comboBoxParticipantes = new JComboBox<Participante>();
    private JScrollPane scrollTextAreaGrupal;
    private JTextField nombreTextField;
    private JTabbedPane panelPestanias;
    private JButton botonEnviarGrupal;
    private JButton botonEnviarIndividual;
    private JButton botonGuardar;
    private JButton botonCargar;

    public VentanaPrincipal()
    {
        super("Messenger");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(0, 3));

        JPanel panelCentral = new JPanel();
        TitledBorder borde = (new TitledBorder("Chat grupal"));
        panelCentral.setBorder(borde);
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setSize(300, 600);

        this.chatGrupalTextArea = new JTextArea();
        this.escribeGrupal = new JTextField(20);
        this.chatGrupalTextArea.setEditable(false);
        this.scrollTextAreaGrupal = new JScrollPane(this.chatGrupalTextArea);
        scrollTextAreaGrupal.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panelCentral.add(new JPanel(), BorderLayout.NORTH);
        JPanel panelTextoGrupal = new JPanel();
        panelTextoGrupal.setLayout(new BorderLayout());
        panelTextoGrupal.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        panelTextoGrupal.add(this.scrollTextAreaGrupal, BorderLayout.CENTER);
        panelCentral.add(panelTextoGrupal, BorderLayout.CENTER);
        JPanel panelBarraGrupal = new JPanel();
        panelBarraGrupal.setLayout(new FlowLayout());
        panelBarraGrupal.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

        panelBarraGrupal.add(this.escribeGrupal);
        panelBarraGrupal.add(comboBoxParticipantes);
        this.botonEnviarGrupal = new JButton("Send");
        this.botonEnviarGrupal.setActionCommand("BOTON ENVIAR GRUPAL");
        panelBarraGrupal.add(this.botonEnviarGrupal);
        panelCentral.add(panelBarraGrupal, BorderLayout.SOUTH);

        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBorder(new LineBorder(Color.LIGHT_GRAY));
        panelPestanias = new JTabbedPane();
        panelPestanias.setBackground(new Color(176, 196, 222));
        panelDerecho.add(panelPestanias, BorderLayout.CENTER);
        JPanel panelBarraIndividual = new JPanel();
        panelBarraIndividual.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        panelBarraIndividual.setLayout(new FlowLayout());
        this.escribeIndividual = new JTextField(26);
        this.botonEnviarIndividual = new JButton("Send");
        this.botonEnviarIndividual.setActionCommand("ENVIAR INDIVIDUAL");
        panelBarraIndividual.add(this.escribeIndividual);
        panelBarraIndividual.add(this.botonEnviarIndividual);
        panelDerecho.add(panelBarraIndividual, BorderLayout.SOUTH);

        this.listaParticipantes.setModel(modeloLista);
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setBorder(new TitledBorder("Participantes"));
        panelIzquierdo.setBackground(new Color(176, 196, 222));
        panelIzquierdo.setLayout(new BorderLayout());
        panelIzquierdo.add(listaParticipantes, BorderLayout.CENTER);
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 0));
        panelBotones.setBorder(new TitledBorder("Opciones"));
        JPanel panelOpcionesSuperior = new JPanel();
        JPanel panelOpcionesMedio = new JPanel();
        JPanel panelOpcionesInferior = new JPanel();

        this.menuElemento = new JComboBox(tipos);
        this.menuElemento.setActionCommand("MENU ELEMENTO");
        this.menuParticipante = new JComboBox(tipoParticipante);
        this.menuParticipante.setActionCommand("MENU PARTICIPANTE");
        panelOpcionesSuperior.setBorder(new TitledBorder("Atributos de Creación"));
        panelOpcionesSuperior.setLayout(new GridLayout(2, 0));
        JPanel panelOpcionesSuperiorAuxiliar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelOpcionesSuperiorAuxiliarDos = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelOpcionesSuperiorAuxiliar.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        panelOpcionesSuperiorAuxiliarDos.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        panelOpcionesSuperiorAuxiliar.add(new JLabel("Tipo Elemento:"));
        panelOpcionesSuperiorAuxiliar.add(menuElemento);
        panelOpcionesSuperiorAuxiliarDos.add(new JLabel("Tipo Participante:"));
        panelOpcionesSuperiorAuxiliarDos.add(menuParticipante);
        panelOpcionesSuperior.add(panelOpcionesSuperiorAuxiliarDos);
        panelOpcionesSuperior.add(panelOpcionesSuperiorAuxiliar);
        this.nombreTextField = new JTextField(8);
        panelOpcionesMedio.add(nombreTextField);
        this.agregarParticipanteButton = new JButton("Agregar");
        this.agregarParticipanteButton.setEnabled(false);
        //this.eliminarParticipanteButton = new JButton("Eliminar");
      //  this.eliminarParticipanteButton.setEnabled(false);
       // this.eliminarParticipanteButton.setActionCommand("ELIMINAR PARTICIPANTE");
        this.agregarParticipanteButton.setActionCommand("AGREGAR PARTICIPANTE");
        panelOpcionesMedio.setBorder(new TitledBorder("Nombre"));
        panelOpcionesMedio.add(this.agregarParticipanteButton);
       // panelOpcionesMedio.add(this.eliminarParticipanteButton);

        panelOpcionesInferior.setBorder(new TitledBorder("Persistencia"));
        this.botonCargar = new JButton("Cargar BD");
        this.botonGuardar = new JButton("Guardar BD");
        this.botonCargar.setActionCommand("CARGAR");
        this.botonGuardar.setActionCommand("GUARDAR");
        panelOpcionesInferior.add(this.botonCargar);
        panelOpcionesInferior.add(this.botonGuardar);
        panelBotones.add(panelOpcionesSuperior);
        panelBotones.add(panelOpcionesMedio);
        panelBotones.add(panelOpcionesInferior);


        panelIzquierdo.add(panelBotones, BorderLayout.EAST);

        panelPrincipal.add(panelIzquierdo);
        panelPrincipal.add(panelCentral);
        panelPrincipal.add(panelDerecho);


        getContentPane().add(panelPrincipal);
    }

    @Override
    public void arranca()
    {
        pack();
        this.setSize(1200, 600);
        setVisible(true);

    }

    @Override
    public void actualizaChatGrupal(String s)
    {
        this.chatGrupalTextArea.append(s);
    }

    @Override
    public String obtenerNombre()
    {

        return this.nombreTextField.getText();
    }

    @Override
    public int ObtenerTipoElemento()
    {
        int aux = 0;
        switch (((String) this.menuElemento.getSelectedItem()))
        {
        case "Tierra":
            aux = 0;
            break;
        case "Fuego":
            aux = 1;
            break;
        case "Agua":
            aux = 2;
            break;
        case "Aire":
            aux = 3;
            break;
        }
        return aux;
    }

    @Override
    public String ObtenerTipoParticipante()
    {
        return (String) this.menuParticipante.getSelectedItem();
    }

    @Override
    public void setControlador(Controlador c)
    {
        this.agregarParticipanteButton.addActionListener(c);
        //this.eliminarParticipanteButton.addActionListener(c);
        this.botonEnviarGrupal.addActionListener(c);
        this.listaParticipantes.addMouseListener(c);
        this.botonEnviarIndividual.addActionListener(c);
        this.botonCargar.addActionListener(c);
        this.botonGuardar.addActionListener(c);
        this.addWindowListener(c);
    }

    @Override
    public void actualizaLista(Iterator<Participante> participantes)
    {
        this.modeloLista.clear();
        this.comboBoxParticipantes.removeAllItems();
        Iterator<Participante> it = participantes;
        Participante aux = null;
       // if (it.hasNext() == false)
           // this.eliminarParticipanteButton.setEnabled(false);
       // else
        //{
          //  this.eliminarParticipanteButton.setEnabled(true);
            while (it.hasNext())
            {
                aux = it.next();
                this.modeloLista.addElement(aux);
                if (!(aux.getNombre().equals("Yo")))
                    this.comboBoxParticipantes.addItem(aux);
            }
       // }

    }

    @Override
    public Participante devolverParticipanteSeleccionado()
    {
        return this.listaParticipantes.getSelectedValue();
    }

    @Override
    public String obtenerMensajeGrupal()
    {

        return this.escribeGrupal.getText();
    }

    @Override
    public String obtenerMensajeIndividual()
    {
        return this.escribeIndividual.getText();
    }

    @Override
    public void agregarPestañaChat(Participante p)
    {
        PanelChat panelChat = new PanelChat();
        this.panelPestanias.addTab(p.getNombre(), panelChat);
        this.panelPestanias.setSelectedComponent(panelChat);
    }

    @Override
    public Participante obtenerParticipanteGrupal()
    {

        return (Participante) this.comboBoxParticipantes.getSelectedItem();
    }


    @Override
    public String obtenerParticipantePestania()
    {
        int indice = obtenerIndicePestaña();
        return  this.panelPestanias.getTitleAt(indice);
    
    }
    @Override
    public int obtenerIndicePestaña()
    {
        return this.panelPestanias.getSelectedIndex();
    }

    @Override
    public void actualizaChatIndividual(int indice, String mensaje)
    {
        PanelChat panel = (PanelChat)this.panelPestanias.getComponentAt(indice);
        
        panel.getChatTextArea().append(mensaje);
        
    }

    @Override
    public void habilitarBotonAgregar()
    {
        this.agregarParticipanteButton.setEnabled(true);
    }
}
