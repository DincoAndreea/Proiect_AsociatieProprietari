/**
 * @author Andreea DINCO
 * @grupa 3133a
 * @nr 1
 */

package ro.usv.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SerializareDao<T extends Entitate, K>
        implements Dao<T, K>{
    private File fisierSerializare;

    public SerializareDao(String fisierSerializare) {
        this.fisierSerializare = new File (fisierSerializare);
    }

    @Override
    public List<T> getAll() {
        return Collections.unmodifiableList( getAllModifiable());
    }

    private  List<T> getAllModifiable() {
        List<T> lstObj = new ArrayList<>();
        if (fisierSerializare.exists()) {
            try {
                FileInputStream fisinput = new FileInputStream(fisierSerializare);
                ObjectInputStream objis = new ObjectInputStream(fisinput);
                lstObj = (List<T>) objis.readObject();
                fisinput.close();
                objis.close();
            } catch (Exception e) {
                System.err.println("Eroare deserializare lstObj: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return lstObj;
    }


    @Override
    public T get(K id) {
        if(id!=null) {
            return get(getAll(), id);
        }
        throw new IllegalArgumentException("get: parametru id=null");
    }

    private T get(List<T> lstobj, K id) {
            for (T s : lstobj)
                if (s.getId().equals(id))
                    return s;
        return null;
    }

    @Override
    public void save(T obj) throws IllegalArgumentException{
        if(obj == null)
            throw new IllegalArgumentException("save: parametru obj. null");
        List<T> lstobj = getAllModifiable();
        if(lstobj.contains(obj)){
            throw new IllegalArgumentException("save: obj. exista deja id="+ obj.getId());
        }
        lstobj.add(obj);
        saveAll(lstobj);
    }

    public void saveAll(List<T> lstlstObj) {
        if (fisierSerializare.exists()) {
            fisierSerializare.delete();
        }
        try {
            ObjectOutputStream objws = new ObjectOutputStream(
                                           new FileOutputStream(fisierSerializare, true));
            objws.writeObject(lstlstObj);
            objws.flush();
            objws.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println("Eroare in timpul serializarii: " + e.getMessage());
            System.exit(1);
        }

    }


    @Override
    public void update(T objActualizat)  throws IllegalArgumentException {
        if(objActualizat==null )
            throw new IllegalArgumentException ("update, parametru null");
        K idCautat = (K) objActualizat.getId();
        List<T> lstobj = getAllModifiable();
        T cautat = get (lstobj, idCautat );
        if (cautat == null)
            throw new IllegalArgumentException ("update, id inexistent: "+ idCautat);
        lstobj.remove(cautat);
        lstobj.add(objActualizat);
        saveAll(lstobj);
   }

    @Override
    public void delete(K id) throws IllegalArgumentException {
        throw new UnsupportedOperationException("delete neimplementat");
    }
    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("deleteAll neimplementat");
    }

}
