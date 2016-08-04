package com.cwjcsu.projecteuler.util;

import java.io.Serializable;

/*
*
* @author atlas
*
* @param <V1>
* @param <V2>
*/
public class Tuple<V1, V2> implements Serializable{
   private static final long serialVersionUID = 6848965487886590984L;
   private V1 v1;
   private V2 v2;

   public Tuple(V1 val1, V2 val2) {
       this.v1 = val1;
       this.v2 = val2;
   }

   public V1 getV1() {
       return v1;
   }

   public void setV1(V1 val1) {
       this.v1 = val1;
   }

   public V2 getV2() {
       return v2;
   }

   public void setV2(V2 val2) {
       this.v2 = val2;
   }

   public String toString() {
       return v1 + ":" + v2;
   }

   @Override
   public int hashCode() {
       final int prime = 31;
       int result = 1;
       result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
       result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
       return result;
   }

   @Override
   public boolean equals(Object obj) {
       if (this == obj)
           return true;
       if (obj == null)
           return false;
       if (getClass() != obj.getClass())
           return false;
       Tuple other = (Tuple) obj;
       if (v1 == null) {
           if (other.v1 != null)
               return false;
       } else if (!v1.equals(other.v1))
           return false;
       if (v2 == null) {
           if (other.v2 != null)
               return false;
       } else if (!v2.equals(other.v2))
           return false;
       return true;
   }

}
