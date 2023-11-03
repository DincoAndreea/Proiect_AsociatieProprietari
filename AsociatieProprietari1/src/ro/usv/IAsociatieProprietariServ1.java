/**
 * @author Andreea DINCO
 * @grupa 3133a
 * @nr 1
 */

package ro.usv;

import java.util.List;

public interface IAsociatieProprietariServ1 {

        void setStocare(String nume);
        Apartament getApartamentById(int id);
        List<Apartament> getApartamentente();
        void saveApartament(Apartament ap);
        void deleteApartment(int id);
        void deleteApartmente();
        int countApartamente(String tip);
        List<Long> findIdsNewerThan(int nrAni);
        List<Long> findIdsByStreet(String numeStrada);
        int getExpenses(int id);
        int getCountSurf(String tip, double smin);
        List<Long> selectEtajMax(double suprafata, int etaj);
}
