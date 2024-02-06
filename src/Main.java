public class Main {
    public static void main(String[] args) {
        SpravceDeskovek spravceDeskovek = new SpravceDeskovek();
        spravceDeskovek.pridejDeskovku(new Deskovka("Monopoly", false, 1));
        SouhrnaUloha s = new SouhrnaUloha(spravceDeskovek);
        s.setVisible(true);

    }
}