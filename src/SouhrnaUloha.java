import javax.swing.*;

public class SouhrnaUloha extends JFrame {
    private JCheckBox cb;
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JRadioButton rb3;
    private JTextField txt;
    private JPanel mainPanel;
    private JButton pbtn;
    private JButton ubtn;
    private JButton dbtn;

    private int indexAktualniDeskovky = 0;
    private final SpravceDeskovek spravceDeskovek;

    public SouhrnaUloha(SpravceDeskovek spravceDeskovek) {
        this.spravceDeskovek = spravceDeskovek;
        initComponents();
        updateGUI();
        dbtn.addActionListener(e -> dalsiDeskovka());
        pbtn.addActionListener(e -> predchoziDeskovka());
        ubtn.addActionListener(e -> ulozDeskovku());
    }

    private void initComponents() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Čtení ze souboru");
        pack();
    }

    private void ulozDeskovku() {
        String nazevHry = txt.getText();
        boolean zakoupeno = cb.isSelected();
        int oblibenost = 1;
        if (rb2.isSelected()) {
            oblibenost = 2;
        } else if (rb3.isSelected()) {
            oblibenost = 3;
        }
        Deskovka aktualniDeskovka = spravceDeskovek.getDeskovka(indexAktualniDeskovky);
        aktualniDeskovka.setNazev(nazevHry);
        aktualniDeskovka.setZakoupeno(zakoupeno);
        aktualniDeskovka.setOblibenost(oblibenost);
    }

    private void predchoziDeskovka() {
        dbtn.setEnabled(true);
        if (indexAktualniDeskovky > 0) {
            indexAktualniDeskovky--;
            updateGUI();
        }else{
            pbtn.setEnabled(false);
        }
    }

    private void dalsiDeskovka() {
        pbtn.setEnabled(true);
        if (indexAktualniDeskovky < spravceDeskovek.getPocetDeskovek() - 1) {
            indexAktualniDeskovky++;
            updateGUI();
        }else{
            dbtn.setEnabled(false);
        }
    }

    private void updateGUI() {
        if (spravceDeskovek.getPocetDeskovek() == 0) {
            txt.setText("");
            cb.setSelected(false);
            rb1.setSelected(true);
        }else {
            Deskovka aktualniDeskovka = spravceDeskovek.getDeskovka(indexAktualniDeskovky);
            txt.setText(aktualniDeskovka.getNazev());
            cb.setSelected(aktualniDeskovka.isZakoupeno());
            switch (aktualniDeskovka.getOblibenost()) {
                case 1:
                    rb1.setSelected(true);
                    break;
                case 2:
                    rb2.setSelected(true);
                    break;
                case 3:
                    rb3.setSelected(true);
                    break;
            }
        }
    }
}