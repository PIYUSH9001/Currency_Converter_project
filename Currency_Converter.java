package currency_converter_project;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.HashMap;


public class currency_converter extends JFrame implements ActionListener {
    public ImageIcon change_img(ImageIcon icon) {
        Image i = icon.getImage();
        Image i2 = i.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon = new ImageIcon(i2);
        return icon;
    }

    ImageIcon arrow_icon = new ImageIcon("C:\\Users\\Dell\\IdeaProjects\\pk_projects\\src\\currency_converter_project\\arrow.png");
    ImageIcon trash_icon = new ImageIcon("C:\\Users\\Dell\\IdeaProjects\\pk_projects\\src\\currency_converter_project\\trash_can.png");
    ImageIcon cur_icon = new ImageIcon("C:\\Users\\Dell\\IdeaProjects\\pk_projects\\src\\currency_converter_project\\currency_icon.png");
    ImageIcon rupees = new ImageIcon("C:\\Users\\Dell\\IdeaProjects\\pk_projects\\src\\currency_converter_project\\rupees.png");
    ImageIcon dollar = new ImageIcon("C:\\Users\\Dell\\IdeaProjects\\pk_projects\\src\\currency_converter_project\\dollar.png");
    ImageIcon euro = new ImageIcon("C:\\Users\\Dell\\IdeaProjects\\pk_projects\\src\\currency_converter_project\\euro.png");
    ImageIcon yen = new ImageIcon("C:\\Users\\Dell\\IdeaProjects\\pk_projects\\src\\currency_converter_project\\yen.png");
    ImageIcon ruble = new ImageIcon("C:\\Users\\Dell\\IdeaProjects\\pk_projects\\src\\currency_converter_project\\ruble.png");
    ImageIcon pound = new ImageIcon("C:\\Users\\Dell\\IdeaProjects\\pk_projects\\src\\currency_converter_project\\pound.png");
    JPanel panel = new JPanel();
    JButton button = new JButton("Convert");
    JTextField jtf = new JTextField(), jtf2 = new JTextField();
    JLabel label = new JLabel("CURRENCY CONVERTER");
    JButton arrow = new JButton(change_img(arrow_icon));
    JButton delete_text1 = new JButton(change_img(trash_icon));
    JButton delete_text2 = new JButton(change_img(trash_icon));
    JComboBox jcb = new JComboBox<>(), jcb2 = new JComboBox<>();
    public String text1 = null, text2 = null, combo_text1 = "Dollar ($)", combo_text2 = "Dollar ($)";

    private DefaultComboBoxModel populate() {
        DefaultComboBoxModel dm = new DefaultComboBoxModel<>();
        dm.addElement(new img_text(change_img(dollar), "Dollar ($)"));
        dm.addElement(new img_text(change_img(pound), "Pound (£)"));
        dm.addElement(new img_text(change_img(yen), "Yen (¥)"));
        dm.addElement(new img_text(change_img(euro), "Euro (€)"));
        dm.addElement(new img_text(change_img(ruble), "Ruble (₽)"));
        dm.addElement(new img_text(change_img(rupees), "Rupees (₹)"));
        return dm;
    }

    currency_converter() {
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(0));
        this.setResizable(false);
        this.setTitle("Currency Converter");
        panel.setPreferredSize(new Dimension(450, 450));
        panel.setLayout(null);
        label.setBounds(75, 50, 400, 50);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("HELVETICA NEUE", Font.BOLD, 25));
        panel.add(label);
        panel.setBackground(new Color(0, 0, 0));
        this.add(panel);
        jtf.setBounds(165, 125, 200, 30);
        jtf.setFocusable(true);
        jtf.setFont(new Font("Nimbus", Font.PLAIN, 25));
        jtf2.setBounds(165, 200, 200, 30);
        jtf2.setFocusable(true);
        jtf2.setFont(new Font("Nimbus", Font.PLAIN, 25));
        panel.add(jtf);
        panel.add(jtf2);
        button.setBounds(169, 300, 130, 50);
        button.setFocusable(false);
        button.setFont(new Font("Nimbus", Font.BOLD, 20));
        button.addActionListener(this);
        button.setBackground(Color.white);
        button.setForeground(Color.black);
        panel.add(button);
        jcb.setModel(populate());
        jcb2.setModel(populate());
        jcb.setRenderer(new Combobox_renderer());
        jcb2.setRenderer(new Combobox_renderer());
        jcb2.setBounds(20, 125, 130, 30);
        jcb.setBounds(20, 200, 130, 30);
        jcb.setBackground(Color.white);
        jcb.setForeground(Color.black);
        jcb2.setForeground(Color.black);
        jcb2.setBackground(Color.white);
        jcb.setFocusable(false);
        jcb2.setFocusable(false);
        panel.add(jcb);
        panel.add(jcb2);
        arrow.setOpaque(true);
        arrow.setBounds(250, 162, 30, 30);
        arrow.setBackground(Color.white);
        arrow.setFocusable(false);
        arrow.addActionListener(this);
        panel.add(arrow);
        delete_text1.setOpaque(true);
        delete_text1.setBounds(380,125,30,30);
        delete_text1.setBackground(Color.white);
        delete_text1.setFocusable(false);
        delete_text1.addActionListener(this);
        panel.add(delete_text1);
        delete_text2.setOpaque(true);
        delete_text2.setBounds(380,200,30,30);
        delete_text2.setBackground(Color.white);
        delete_text2.setFocusable(false);
        delete_text2.addActionListener(this);
        panel.add(delete_text2);
        jtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                text1 = jtf.getText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                text1 = jtf.getText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                text1 = jtf.getText();
            }
        });
        jtf2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                text2 = jtf2.getText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                text2 = jtf2.getText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                text2 = jtf2.getText();
            }
        });
        jcb2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    img_text temp = (img_text) jcb2.getSelectedItem();
                    switch (temp.get_text()) {
                        case "Dollar ($)" -> combo_text1 = "Dollar ($)";
                        case "Pound (£)" -> combo_text1 = "Pound (£)";
                        case "Yen (¥)" -> combo_text1 = "Yen (¥)";
                        case "Euro (€)" -> combo_text1 = "Euro (€)";
                        case "Ruble (₽)" -> combo_text1 = "Ruble (₽)";
                        case "Rupees (₹)" -> combo_text1 = "Rupees (₹)";
                    }
                }
            }
        });
        jcb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    img_text temp = (img_text) jcb.getSelectedItem();
                    switch (temp.get_text()) {
                        case "Dollar ($)" -> combo_text2 = "Dollar ($)";
                        case "Pound (£)" -> combo_text2 = "Pound (£)";
                        case "Yen (¥)" -> combo_text2 = "Yen (¥)";
                        case "Euro (€)" -> combo_text2 = "Euro (€)";
                        case "Ruble (₽)" -> combo_text2 = "Ruble (₽)";
                        case "Rupees (₹)" -> combo_text2 = "Rupees (₹)";
                    }
                }
            }
        });
        this.setIconImage(cur_icon.getImage());
        this.setLocation(250, 120);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        60cd338cd8cdcfa6eba85a79   -------------->      API KEY
//        String url_str = "https://v6.exchangerate-api.com/v6/60cd338cd8cdcfa6eba85a79/latest/USD";
//        System.out.println(getexc("USD","INR"));
        new currency_converter();
    }

    public class Combobox_renderer extends JLabel implements ListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            img_text it = (img_text) value;//Get values
//            setting values
            setText(it.get_text());
            setIcon(it.get_img());
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(Color.lightGray);
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            setFont(list.getFont());
            setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
            return this;
        }
    }

    public class img_text {
        String text;
        ImageIcon img;

        public img_text(ImageIcon img, String text) {
            this.text = text;
            this.img = img;
        }

        public void set_text(String text) {
            this.text = text;
        }

        public void set_img(ImageIcon icon) {
            this.img = icon;
        }

        public String get_text() {
            return this.text;
        }

        public ImageIcon get_img() {
            return this.img;
        }
    }

    public static float getexc(String base_currency, String counter_currency) throws IOException, InterruptedException {
        String url_str = "https://v6.exchangerate-api.com/v6/60cd338cd8cdcfa6eba85a79/pair/" + base_currency + "/" + counter_currency;
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url_str)).build();
        var client = HttpClient.newBuilder().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String str = response.body();
        String[] parts = str.split(",");
        HashMap<String, String> m = new HashMap<>();
        for (String part : parts) {

            //split the employee data by : to get id and name
            String[] empdata = part.split(":");

            String strId = empdata[0].trim();
            String strName = empdata[1].trim();

            //add to map
            m.put(strId, strName);
        }
        String a = m.get("\"conversion_rate\"").replace("}", "");
        float result = Float.parseFloat(a);
        return result;
    }

    public void show_result(String base_c, String counter_c, boolean downup) {
        double input;
        if (downup) {
            input = Double.parseDouble(text1);
            try {
                input *= getexc(base_c, counter_c);
            } catch (IOException io) {
            } catch (InterruptedException ie) {
            }
            DecimalFormat df = new DecimalFormat("#.000");
            jtf2.setText(df.format(input));
        } else {
            input = Double.parseDouble(text2);
            try {
                input *= getexc(base_c, counter_c);
            } catch (IOException io) {
                System.out.println("error");
            } catch (InterruptedException ie) {
                System.out.println("error2");
            }
            DecimalFormat df = new DecimalFormat("#.000");
            jtf.setText(df.format(input));
        }
    }

    public void up_down() {
        switch (combo_text1) {
            case "Dollar ($)" -> change_curr("USD", true);
            case "Pound (£)" -> change_curr("GBP", true);
            case "Yen (¥)" -> change_curr("JPY", true);
            case "Euro (€)" -> change_curr("EUR", true);
            case "Ruble (₽)" -> change_curr("RUB", true);
            case "Rupees (₹)" -> change_curr("INR", true);
        }
    }

    public void down_up() {
        switch (combo_text2){
            case "Dollar ($)":
                change_curr("USD",false);
                break;
            case "Pound (£)":
                change_curr("GBP",false);
                break;
            case "Yen (¥)":
                change_curr("JPY",false);
                break;
            case "Euro (€)":
                change_curr("EUR",false);
                break;
            case "Ruble (₽)":
                change_curr("RUB",false);
                break;
            case "Rupees (₹)":
                change_curr("INR",false);
                break;
        }
    }

    public void change_curr(String base_c, boolean b) {
        if (b) {
            switch (((img_text) jcb.getSelectedItem()).get_text()){
                case "Dollar ($)":
                    show_result(base_c,"USD",true);
                    break;
                case "Pound (£)":
                    show_result(base_c,"GBP",true);
                    break;
                case "Yen (¥)":
                    show_result(base_c,"JPY",true);
                    break;
                case "Euro (€)":
                    show_result(base_c,"EUR",true);
                    break;
                case "Ruble (₽)":
                    show_result(base_c,"RUB",true);
                    break;
                case "Rupees (₹)":
                    show_result(base_c,"INR",true);
                    break;
            }
        }
         else {
            switch (((img_text) jcb2.getSelectedItem()).get_text()){
                case "Dollar ($)":
                    show_result(base_c,"USD",false);
                    break;
                case "Pound (£)":
                    show_result(base_c,"GBP",false);
                    break;
                case "Yen (¥)":
                    show_result(base_c,"JPY",false);
                    break;
                case "Euro (€)":
                    show_result(base_c,"EUR",false);
                    break;
                case "Ruble (₽)":
                    show_result(base_c,"RUB",false);
                    break;
                case "Rupees (₹)":
                    show_result(base_c,"INR",false);
                    break;
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            if (jtf.hasFocus() || jtf2.getText().isEmpty()) {
                up_down();
            } else if (jtf2.hasFocus()||jtf.getText().isEmpty()) {
                down_up();
            }
        } else if (e.getSource() == arrow) {
            String temp = jtf.getText();
            jtf.setText(jtf2.getText());
            jtf2.setText(temp);
            if(jtf.hasFocus()){
                jtf2.grabFocus();
            }
            else{
                jtf.grabFocus();
            }
        }
        else if(e.getSource() == delete_text1){
            text1 = null;
            jtf.setText("");
            jtf.grabFocus();
        }
        else if(e.getSource() == delete_text2){
            text2 = null;
            jtf2.setText("");
            jtf2.grabFocus();
        }
    }
}
