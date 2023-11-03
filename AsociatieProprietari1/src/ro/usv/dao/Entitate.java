/**
 * @author Andreea DINCO
 * @grupa 3133a
 * @nr 1
 */

package ro.usv.dao;

import java.io.Serializable;

public abstract class Entitate<K> implements Serializable {

    public abstract K getId();

    @Override
    final public boolean equals(Object oid){
        if (this == oid) return true;
        if (oid == null || !(oid instanceof Entitate) )
                return false;
        K cheie = ((Entitate<K>)oid).getId();
        return cheie != null ? cheie.equals(this.getId()) : getId() == null;
    }

    @Override
     final public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

}
