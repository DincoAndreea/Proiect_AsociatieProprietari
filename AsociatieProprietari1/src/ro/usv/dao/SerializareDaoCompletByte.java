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

public class SerializareDaoCompletByte<T extends Entitate, K> implements Dao<T, K> {
    private File fisierSerializare = null;
    private ByteArrayOutputStream byteArrayOutputStream = null;
    private byte[] byteMemory = null;

    public SerializareDaoCompletByte() {
    }

    public SerializareDaoCompletByte(String fisierSerializare) {
        this.fisierSerializare = new File(fisierSerializare);
    }

    private InputStream getInputStream() {
        InputStream fisinput = null;
        if (fisierSerializare != null) {
            try {
                fisinput = new FileInputStream(fisierSerializare);
            } catch (FileNotFoundException e) {
                //System.out.println("Eroare getInputStream():" + e.getMessage());
            }
        } else {
            if (byteMemory == null) {
                //System.out.println("Eroare  getInputStream(): byteMemory=null");
                fisinput = null;
            } else {
                //System.out.println("getInputStream - byteMemory=" + byteMemory.length);
                fisinput = new ByteArrayInputStream(byteMemory);
            }
        }
        return fisinput;
    }

    private OutputStream getOutputStream() {
        OutputStream fisoutput = null;
        if (fisierSerializare != null) {
            try {
                fisoutput = new FileOutputStream(fisierSerializare);
            } catch (FileNotFoundException e) {
                System.out.println("Eroare fisier serializare:" + e.getMessage());
            }
        } else {
            byteArrayOutputStream = new ByteArrayOutputStream();
            fisoutput = (OutputStream) byteArrayOutputStream;
        }
        return fisoutput;
    }

    @Override
    public List<T> getAll() {
        return Collections.unmodifiableList(getAllModifiable());
    }

    private List<T> getAllModifiable() {
        List<T> lstObj = new ArrayList<>();
        InputStream fisinput = getInputStream();
        if(fisinput != null) {
            try {
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
        if (id != null) {
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
    public void save(T obj) throws IllegalArgumentException {
        if (obj == null)
            throw new IllegalArgumentException("save: parametru obj. null");
        List<T> lstobj = getAllModifiable();
        if (lstobj.contains(obj)) {
            throw new IllegalArgumentException("save: obj. exista deja id=" + obj.getId());
        }
        lstobj.add(obj);
        saveAll(lstobj);
    }

    public void saveAll(List<T> lstlstObj) {
        if (fisierSerializare != null && fisierSerializare.exists()) {
            fisierSerializare.delete();
        }
        try {
            OutputStream fisoutput = getOutputStream();
            ObjectOutputStream objws = new ObjectOutputStream(fisoutput);
            objws.writeObject(lstlstObj);
            if (fisierSerializare == null) {
                byteMemory = byteArrayOutputStream.toByteArray();
                //System.out.println("saveAll() - byteMemory=" + byteMemory.length);
            } else {
                objws.flush();
                objws.close();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println("Eroare in timpul serilalizarii: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void deleteAll() {
        saveAll(new ArrayList<T>());
    }

    @Override
    public void update(T objActualizat) throws IllegalArgumentException {
        if (objActualizat == null)
            throw new IllegalArgumentException("update, parametru null");
        K idCautat = (K) objActualizat.getId();
        List<T> lstobj = getAllModifiable();
        T cautat = get(lstobj, idCautat);
        if (cautat == null)
            throw new IllegalArgumentException("update, id inexistent: " + idCautat);
        lstobj.remove(cautat);
        lstobj.add(objActualizat);
        saveAll(lstobj);
    }

    @Override
    public void delete(K id) throws IllegalArgumentException {
        List<T> lstobj = getAllModifiable();
        T objId = get(lstobj, id);
        if (objId != null) {
            lstobj.remove(objId);
            saveAll(lstobj);
        } else {
            throw new IllegalArgumentException("delete, id inexistent: " + id);
        }
    }

}
