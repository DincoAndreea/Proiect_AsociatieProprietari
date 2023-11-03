/**
 * @author Andreea DINCO
 * @grupa 3133a
 * @nr 1
 */

package ro.usv;

import ro.usv.dao.Dao;
import ro.usv.dao.SerializareDaoComplet;
import ro.usv.dao.SerializareDaoCompletByte;

import java.util.ArrayList;
import java.util.List;

public class AsociatieProprietariServ implements IAsociatieProprietariServ1{

    private Dao asociatie;

    public AsociatieProprietariServ() {
        asociatie = new SerializareDaoCompletByte();
    }

    public AsociatieProprietariServ(String numeFisier) {
        asociatie = new SerializareDaoCompletByte(numeFisier);
    }

    @Override
    public void setStocare(String nume) {
        if(nume.equals(null) || nume.equals(""))
        {
            asociatie = new SerializareDaoCompletByte();
        }
        else
        {
            asociatie = new SerializareDaoCompletByte(nume);
        }

    }

    @Override
    public Apartament getApartamentById(int id) {
        return (Apartament) asociatie.get(id);
    }

    @Override
    public List<Apartament> getApartamentente() {
        return asociatie.getAll();
    }

    @Override
    public void saveApartament(Apartament ap) {
        asociatie.save(ap);
    }

    @Override
    public void deleteApartment(int id) {
        asociatie.delete(id);
    }

    @Override
    public void deleteApartmente() {
        asociatie.deleteAll();
    }

    @Override
    public int countApartamente(String tip) {
        int k1 = 0;
        int k2 = 0;
        List<Apartament> l;
        l = asociatie.getAll();
        if(tip.equals("L"))
        {
            for (Apartament apartament : l) {
                if (apartament.getClass().getName().equals("ro.usv.Locuinta")) {
                    k1++;
                }
            }
        }

        if(tip.equals("SF"))
        {
            for (Apartament apartament : l) {
                if (apartament.getClass().getName().equals("ro.usv.SediuFirma")) {
                    k2++;
                }
            }
        }
        return (tip.equals("L") ? k1 : (tip.equals("SF") ? k2 : -1));
    }

    @Override
    public List<Long> findIdsNewerThan(int nrAni) {
        ArrayList<Long> ap = new ArrayList<>();
        List<Apartament> l;
        l = asociatie.getAll();
        long id;
        for (Apartament apartament : l) {
            if (apartament.getAn() >= nrAni) {
                id = apartament.getID();
                ap.add(id);
            }
        }
        return ap;
    }

    @Override
    public List<Long> findIdsByStreet(String numeStrada) {
        ArrayList<Long> ap = new ArrayList<>();
        List<Apartament> l;
        l = asociatie.getAll();
        long id;
        for(int i = 0; i < asociatie.getAll().size(); i++)
        {
            if(l.get(i).getStrada().equals(numeStrada))
            {
                id = l.get(i).getID();
                ap.add(id);
            }
        }
        return ap;
    }

    public int getExpenses(int id)
    {
        int suma = 0;
        if(asociatie.get(id).getClass().getName().equals("ro.usv.Locuinta"))
        {
            suma = (int)(10 * getApartamentById(id).getSuprafata() + 10);
        }
        if(asociatie.get(id).getClass().getName().equals("ro.usv.SediuFirma"))
        {
            suma = (int)(10 * getApartamentById(id).getSuprafata() + 50);
        }
        return suma;
    }

    public int getCountSurf(String tip, double smin)
    {
        List<Apartament> l;
        l = asociatie.getAll();
        int count1 = 0;
        int count2 = 0;
        for(int i = 0; i < asociatie.getAll().size(); i++)
        {
            if(l.get(i).getSuprafata() >= smin && l.get(i).getClass().getName().equals("ro.usv.Locuinta"))
            {
                count1++;
            }
            if(l.get(i).getSuprafata() >= smin && l.get(i).getClass().getName().equals("ro.usv.SediuFirma"))
            {
                count2++;
            }
        }
        return (tip.equals("L") ? count1 : (tip.equals("SF") ? count2 : -1));
    }

    public List<Long> selectEtajMax(double suprafata, int etaj)
    {
        ArrayList<Long> ap = new ArrayList<>();
        List<Apartament> l;
        l = asociatie.getAll();
        long id;
        for(int i = 0; i < asociatie.getAll().size(); i++)
        {
            if(l.get(i).getSuprafata() >= suprafata && l.get(i).getEtaj() <= etaj)
            {
                id = l.get(i).getID();
                ap.add(id);
            }
        }
        return ap;
    }
}
