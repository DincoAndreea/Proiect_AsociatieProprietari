/**
 * @author Andreea DINCO
 * @grupa 3133a
 * @nr 1
 */

package ro.usv;

public class Locuinta extends Apartament{

    private int nrPersoane;

    public Locuinta(int id, double suprafata, int an, String strada, String numarCasa, String scara, int etaj, String numarApartament, int nrPersoane) {
        super(id, suprafata, an, strada, numarCasa, scara, etaj, numarApartament);
        try{
            if(nrPersoane < 1)
            {
                throw new IllegalArgumentException("Valori incorecte pentru crearea unei locuinte");
            }
            this.nrPersoane = nrPersoane;
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "\n{Tip=L" + super.toString() + ", nrPersoane=" + nrPersoane + "}";
    }

    @Override
    public Integer getId() {
        return this.getID();
    }
}
