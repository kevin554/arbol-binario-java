package edd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ArbolBinario<E> {

    protected Nodo<E> raiz;

    public void insertar(E valor) {
        if (raiz != null) 
            insertar(valor, raiz);
         else 
            raiz = new Nodo<>(valor);
    }

    private void insertar(E valor, Nodo<E> actual) {
        Comparable valorA = (Comparable) valor;
        Comparable valorB = (Comparable) actual.getDato();
        
        if (valorA.compareTo(valorB) > 0) {//A es mayor a B else B mayor a A
            if (actual.getDer() == null) 
                actual.setDer(new Nodo<>(valor));
             else 
                insertar(valor, actual.getDer());
            
        } else if (valorA.compareTo(valorB) < 0) {
            if (actual.getIzq() == null) 
                actual.setIzq(new Nodo<>(valor));
             else 
                insertar(valor, actual.getIzq());
        }
    }

    @Override
    public String toString() {
        String resultado = "";
        if (raiz != null) 
            return toString(raiz, resultado);
         else 
            return "vacio";
    }

    private String toString(Nodo Actual, String resultado) {
        String aux;
        String aux2;
        if (Actual == null) {
            return "";
        } else {
            resultado += Actual + " Actual";
            resultado = resultado + "\n";
            aux = toString(Actual.getIzq(), "");
            if (!aux.trim().equals("")) 
                resultado += aux + "\n";
            
            aux2 = toString(Actual.getDer(), "");
            if (!aux2.trim().equals("")) 
                resultado += aux + "\n";
        }

        return resultado;
    }

    public void eliminar(E valor) {
        if (raiz != null) {
            if (raiz.getDato() == valor) 
                raiz = null;
             else 
                if (raiz.getIzq() != null && raiz.getDer() != null) 
                    eliminar(valor, null, raiz);
        }
    }

    private void eliminar(E valor, Nodo<E> Padre, Nodo<E> actual) {
        int comp = ((Comparable) valor).compareTo(actual.getDato());
        if (comp == 0) {
            Nodo der = actual.getDer();
            if (der != null) {
                Nodo izq;
                if (Padre == null) { // es raiz
                    izq = raiz.getIzq();
                    raiz = der;
                } else { // no es raiz
                    izq = actual.getIzq();
                    Padre.setDer(der);
                }
                Nodo aux = der;
                while (actual.getIzq() != null) 
                    aux = aux.getIzq();
                
                aux.setIzq(izq);
            } else {
                Padre.setIzq(actual.getIzq());
            }
        } else if (comp > 0) {
            eliminar(valor, actual.getDer(), actual);
        } else {
            eliminar(valor, actual.getIzq(), actual);
        }
    }

    public Nodo<E> BuscarNodo(E valor) {
        if (raiz != null) 
            return BuscarNodo(valor, raiz);
        
        return null;
    }

    private Nodo<E> BuscarNodo(E valor, Nodo<E> actual) {
        Comparable valorA = (Comparable) valor;
        Comparable valorB = (Comparable) actual.getDato();
        if (valorA.equals(valorB)) {
            return actual;
        } else if (valorA.compareTo(valorB) > 0) {
            if (actual.getDer() != null) 
                return BuscarNodo(valor, actual.getDer());
        } else if (valorA.compareTo(valorB) < 0) {
            if (actual.getIzq() != null) 
                return BuscarNodo(valor, actual.getIzq());
        }
        return null;
    }

    public int CantidadNiveles() {
        if (raiz != null) 
            return CantidadNivel(raiz);
        
        return 0;
    }

    private int CantidadNivel(Nodo<E> actual) {
        if (actual.getDer() == null && actual.getIzq() == null) {
            return 1;
        } else if (actual.getIzq() != null) {
            return CantidadNivel(actual.getIzq()) + 1;
        } else if (actual.getDer() != null) {
            return CantidadNivel(actual.getDer()) + 1;
        } else {
            int a = (CantidadNivel(actual.getDer()) + 1);
            int b = (CantidadNivel(actual.getIzq()) + 1);
            if (a > b) 
                return a;
             else 
                return b;
        }
    }

    public void DFSPreOrden() {
        if (raiz != null) 
            DFSPreOrden(raiz);
         else 
            System.out.println(raiz);
    }

    private void DFSPreOrden(Nodo<E> actual) {
        if (actual.getIzq() != null) 
            DFSPreOrden(actual.getIzq());
        
        if (actual.getDer() != null) 
            DFSPreOrden(actual.getDer());
        
        System.out.println(actual.getDato());
    }

    public void DFSInOrden() {
        if (raiz != null) 
            DFSInOrden(raiz);
         else 
            System.out.println(raiz);
    }

    private void DFSInOrden(Nodo<E> actual) {
        System.out.println(actual.getDato());
        if (actual.getIzq() != null) 
            DFSInOrden(actual.getIzq());
        
        if (actual.getDer() != null) 
            DFSInOrden(actual.getDer());
    }

    public int CantidadHojas() {
        if (raiz != null) 
            return CantidadHojas(raiz);
         else 
            return 0;
    }

    private int CantidadHojas(Nodo<E> actual) {
        int izquierda = 0;
        int derecha = 0;
        if (actual.getIzq() != null) 
            izquierda = CantidadHojas(actual.getIzq());
        
        if (actual.getDer() != null) 
            derecha = CantidadHojas(actual.getDer());
        
        if (actual.getIzq() == null && actual.getDer() == null) 
            return 1 + izquierda + derecha;
         else 
            return izquierda + derecha;
    }

    public void DFSPosOrden() {
        if (raiz != null) 
            DFSPosOrden(raiz);
         else 
            System.out.println(raiz);
    }

    private void DFSPosOrden(Nodo<E> actual) {
        System.out.println(actual.getDato());
        if (actual.getDer() != null) 
            DFSPosOrden(actual.getDer());
        
        if (actual.getIzq() != null) 
            DFSPosOrden(actual.getIzq());
    }

    public ArrayList<Nodo<E>> BFS() {
        Queue<Nodo<E>> cola = new LinkedList();
        ArrayList<Nodo<E>> resultado = new ArrayList<>();
        Nodo<E> Aux = raiz;
        cola.add(Aux);
        while (!cola.isEmpty()) {
            Nodo<E> Aux2 = cola.poll();
            resultado.add(Aux2);
            if (Aux2.getIzq() != null) 
                cola.add(Aux2.getIzq());
            
            if (Aux2.getIzq() != null) 
                cola.add(Aux2.getDer());
        }
        
        return resultado;
    }
    
    //CLASE NODO
    class Nodo<E> {

        private Nodo<E> izq;
        private Nodo<E> der;
        private E dato;

        public Nodo(E valor) {
            this.dato = valor;
        }

        public Nodo<E> getIzq() { return izq; }

        public void setIzq(Nodo<E> izq) { this.izq = izq; }

        public Nodo<E> getDer() { return der; }

        public void setDer(Nodo<E> der) { this.der = der; }

        public E getDato() { return dato; }

        public void setDato(E dato) { this.dato = dato; }

        @Override
        public String toString() {
            String resultado = "";
            if (this.getIzq() != null) 
                resultado = resultado + this.getDato() + "->I:" + this.getIzq().getDato();
             else 
                resultado = resultado + "->I:X";

            if (this.getDer() != null) 
                resultado = resultado + this.getDato() + ",D:" + this.getDer().getDato();
             else 
                resultado = resultado + ",D:X";

            return resultado;
        }
    }
}
