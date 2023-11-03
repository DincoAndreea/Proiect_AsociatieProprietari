/**
 * @author Andreea DINCO
 * @grupa 3133a
 * @nr 1
 */

package ro.usv;

public class SediuFirma extends Apartament{

    private String denumire;
    private long CUI;

    public SediuFirma(int id, double suprafata, int an, String strada, String numarCasa, String scara, int etaj, String numarApartament, String denumire, long CUI) {
        super(id, suprafata, an, strada, numarCasa, scara, etaj, numarApartament);
        this.denumire = denumire;
        try{
            if(CUI < 0)
            {
                throw new IllegalArgumentException("Valori incorecte pentru crearea unui sediu de firma");
            }
            this.CUI = CUI;
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String toString() {
        return "\n{Tip=SF" + super.toString() +", denumire='" + denumire + "', CUI=" + CUI + "}";
    }

    @Override
    public Integer getId() {
        return this.getID();
    }
}
