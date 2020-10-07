package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

public class PanelChat extends JPanel
{
    private JTextArea chatTextArea;
    private JScrollPane scrollTextArea;
    public PanelChat()
    {
        super();
        this.setLayout(new BorderLayout());
        this.chatTextArea =  new JTextArea();
        this.chatTextArea.setEditable(false);
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setSize(300, 600);
        //panelCentral.add(new JPanel(), BorderLayout.NORTH);
        JPanel panelTexto = new JPanel(new BorderLayout());
        panelTexto.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        this.scrollTextArea = new JScrollPane(this.chatTextArea);
        scrollTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panelTexto.add(scrollTextArea, BorderLayout.CENTER);
       
        panelCentral.add(panelTexto, BorderLayout.CENTER);
    
        this.add(panelCentral, BorderLayout.CENTER);
        this.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
    }


    public JTextArea getChatTextArea()
    {
        return chatTextArea;
    }
}
