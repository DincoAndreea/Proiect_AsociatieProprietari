/**
 * @author Andreea DINCO
 * @grupa 3133a
 * @nr 1
 */

package ro.usv;

import ro.usv.dao.Entitate;

import java.io.Serializable;
import java.time.Year;

public abstract class Apartament extends Entitate<Integer> implements Serializable{

    private int ID;
    private double suprafata;
    private int an;
    private String strada;
    private String numarCasa;
    private String scara;
    private int etaj;
    private String numarApartament;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        try{
            if(ID < 0)
            {
                throw new IllegalArgumentException("ID-ul nu poate fi negativ sau mai mare ca valoarea maxima acceptata" + Integer.MAX_VALUE + "!");
            }
            this.ID = ID;
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public double getSuprafata() {
        return suprafata;
    }

    public void setSuprafata(double suprafata) {
        try{
            if(suprafata < 1)
            {
                throw new IllegalArgumentException("Suprafata nu poate fi mai mica ca 1!");
            }
            this.suprafata = suprafata;
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        try{
            if(an > Year.now().getValue())
            {
                throw new IllegalArgumentException("An in viitor!");
            }
            this.an = an;
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getNumarCasa() {
        return numarCasa;
    }

    public void setNumarCasa(String numarCasa) {
        this.numarCasa = numarCasa;
    }

    public String getScara() {
        return scara;
    }

    public void setScara(String scara) {
        this.scara = scara;
    }

    public int getEtaj() {
        return etaj;
    }

    public void setEtaj(int etaj) {
        try{
            if(etaj < 0)
            {
                throw new IllegalArgumentException("Etajul nu poate avea o valoare negativa!");
            }
            this.etaj = etaj;
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public String getNumarApartament() {
        return numarApartament;
    }

    public void setNumarApartament(String numarApartament) {
        this.numarApartament = numarApartament;
    }

    public Apartament(int id, double suprafata, int an, String strada, String numarCasa, String scara, int etaj, String numarApartament) {
        try{
            if(id < 0)
            {
                throw new IllegalArgumentException("Valori incorecte pentru crearea unui apartament");
            }
            this.ID = id;
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        try{
            if(suprafata < 1)
            {
                throw new IllegalArgumentException("Valori incorecte pentru crearea unui apartament");
            }
            this.suprafata = suprafata;
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        try{
            if(an > Year.now().getValue())
            {
                throw new IllegalArgumentException("Valori incorecte pentru crearea unui apartament");
            }
            this.an = an;
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        this.strada = strada;
        this.numarCasa = numarCasa;
        this.scara = scara;
        try{
            if(etaj < 0)
            {
                throw new IllegalArgumentException("Valori incorecte pentru crearea unui apartament");
            }
            this.etaj = etaj;
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        this.numarApartament = numarApartament;
    }

    @Override
    public String toString() {
        return ", id=" + this.getID() + ", suprafata=" + this.getSuprafata() + ", anConstructie=" + this.getAn() + ", strada='" + this.getStrada() + "', nr=" + this.getNumarCasa() + ", scara=" + this.getScara() + ", etaj=" + this.getEtaj() + ", nrApt=" + this.getNumarApartament();
    }


}
