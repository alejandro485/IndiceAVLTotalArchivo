package Logica;

import java.io.RandomAccessFile;
import java.util.Stack;

public class ArbolAVL {
	String arbol = "arbol.dat";
	String estudiantes = "estudiantes.dat";

	public RandomAccessFile a;
	public boolean exists;

	public ArbolAVL() {
		try {
			a = new RandomAccessFile(arbol, "rw");
			if (a.length() > 0) {
				int b = a.readInt();
				if (b > 0)
					exists = true;
				else
					exists = false;
			} else {
				for (int i = 1; i < 3; i++) {
					a.writeInt(0);
					a.writeInt(0);
					a.writeInt(0);
					a.writeInt(0);
					a.writeInt(i);
				}
				a.writeInt(0);
				a.writeInt(0);
				a.writeInt(0);
				a.writeInt(0);
				a.writeInt(0);
				exists = false;
			}
			a.close();
			if (!exists) {
				a = new RandomAccessFile(estudiantes, "rw");
				for (int i = 1; i < 5; i++) {
					a.writeBytes("          ");
					a.writeInt(0);
					a.writeInt(0);
					a.writeInt(i);
				}
				a.writeBytes("          ");
				a.writeInt(0);
				a.writeInt(0);
				a.writeInt(0);
				a.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void rotarDerecha(int p, int q) {
		try {
			// balance de q = 0
			a.seek((q * 20) + 4);
			a.writeInt(0);
			// captura de la derecha de q en b
			a.seek((q * 20) + 12);
			int b = a.readInt();
			// balanceo de p = 0
			a.seek((p * 20) + 4);
			a.writeInt(0);
			// izquierda de p = derecha de q (b)
			a.writeInt(b);
			// derecha de q le asigno p
			a.seek((q * 20) + 12);
			a.writeInt(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void rotarIzquierda(int p, int q) {
		try {
			// balanceo de p = 0
			a.seek((20 * p) + 4);
			a.writeInt(0);
			// balanceo de q = 0
			a.seek((20 * q) + 4);
			a.writeInt(0);
			// a la derecha de p le asigno la izquerda de q
			int b = a.readInt();
			a.seek((20 * p) + 12);
			a.writeInt(b);
			// izquierda de q le asigno p
			a.seek((20 * q) + 8);
			a.writeInt(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int dobleRotacionDerecha(int p, int q) {
		int r = 0, b = 0;
		try {
			// r = derecha de q
			a.seek((q * 20) + 12);
			r = a.readInt();
			// derecha de q = izquierda de r
			a.seek((20 * r) + 8);
			b = a.readInt();
			a.seek((q * 20) + 12);
			a.writeInt(b);
			// izquierda de r = q
			a.seek((20 * r) + 8);
			a.writeInt(q);
			// izquierda de p = derecha de r
			a.seek((r * 20) + 12);
			b = a.readInt();
			a.seek((p * 20) + 8);
			a.writeInt(b);
			// derecha de r = p
			a.seek((r * 20) + 12);
			a.writeInt(p);
			// balanceo de r
			a.seek((r * 20) + 4);
			b = a.readInt();
			switch (b) {
			case -1:
				// balanceo de q = 1
				a.seek((q * 20) + 4);
				a.writeInt(1);
				// balanceo de p = 0
				a.seek((p * 20) + 4);
				a.writeInt(0);
				break;
			case 0:
				// balanceo de q = 0
				a.seek((q * 20) + 4);
				a.writeInt(0);
				// balanceo de p = 0
				a.seek((p * 20) + 4);
				a.writeInt(0);
				break;
			case 1:
				// balanceo de q = 0
				a.seek((q * 20) + 4);
				a.writeInt(0);
				// balanceo de p = -1
				a.seek((p * 20) + 4);
				a.writeInt(-1);
				break;
			}
			// balanceo de r = 0
			a.seek((r * 20) + 4);
			a.writeInt(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	private int dobleRotacionIzquierda(int p, int q) {
		int r = 0, b = 0;
		try {
			// r = izquierda de q
			a.seek((q * 20) + 8);
			r = a.readInt();
			// izquierda de q = derecha de r
			a.seek((20 * r) + 12);
			b = a.readInt();
			a.seek((q * 20) + 8);
			a.writeInt(b);
			// derecha de r = q
			a.seek((20 * r) + 12);
			a.writeInt(q);
			// derecha de p = izquierda de r
			a.seek((r * 20) + 8);
			b = a.readInt();
			a.seek((p * 20) + 12);
			a.writeInt(b);
			// izquierda de r = p
			a.seek((r * 20) + 8);
			a.writeInt(p);
			// balanceo de r
			a.seek((r * 20) + 4);
			b = a.readInt();
			switch (b) {
			case -1:
				// balanceo de q = 0
				a.seek((q * 20) + 4);
				a.writeInt(0);
				// balanceo de p = 1
				a.seek((p * 20) + 4);
				a.writeInt(1);
				break;
			case 0:
				// balanceo de q = 0
				a.seek((q * 20) + 4);
				a.writeInt(0);
				// balanceo de p = 0
				a.seek((p * 20) + 4);
				a.writeInt(0);
				break;
			case 1:
				// balanceo de q = -1
				a.seek((q * 20) + 4);
				a.writeInt(-1);
				// balanceo de p = 0
				a.seek((p * 20) + 4);
				a.writeInt(0);
				break;
			}
			// balanceo de r = 0
			a.seek((r * 20) + 4);
			a.writeInt(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	private void agregarLlave(int ll, int st) {
		int p, q, s, pivote, pp, n;// emulan los nodos
		int llave, altura, c = 0, l; // ayudas
		try {
			a = new RandomAccessFile(arbol, "rw");
			c = a.readInt();// c equivale a la raiz del arbol
			if (c == 0) {// para cuando la raiz es nula
				l=siguienteLibre();
				a.seek(0);
				a.writeInt(l);
				modificarNodo(l, ll, 0, 0, 0, st);
				a.close();
				return;
			}
			pp = q = 0;
			pivote = p = c;
			llave = ll;
			while (p != 0) {
				// balanceo
				a.seek((20 * p) + 4);
				l = a.readInt();
				if (l != 0) {
					pp = q;
					pivote = p;
				}
				// informacion
				a.seek(20 * p);
				l = a.readInt();
				if (l == llave) {
					a.close();
					return;
				} else {
					q = p;
					a.seek(p * 20);
					l = a.readInt();// informacion de p
					if (llave < l) {
						a.seek((20 * p) + 8);
						p = a.readInt();
					} else {
						a.seek((20 * p) + 12);
						p = a.readInt();
					}
				}
			}
			a.seek(20 * q);
			l = a.readInt();
			if (llave < l) {
				l = siguienteLibre();
				a.seek((20 * q) + 8);
			} else {
				l = siguienteLibre();
				a.seek((20 * q) + 12);
			}
			a.writeInt(l);
			modificarNodo(l, llave, 0, 0, 0, st);// guardar informacion
															// del arbol
			n = l;
			// informacion del pivote
			a.seek(20 * pivote);
			l = a.readInt();
			if (llave < l) {
				// s = izquierda de pivote
				a.seek((20 * pivote) + 8);
				altura = 1;
			} else {
				// s = derecha de privote
				a.seek((20 * pivote) + 12);
				altura = -1;
			}
			s = a.readInt();
			p = s;
			while (p != n) {
				a.seek(20 * p);
				l = a.readInt();
				a.seek((20 * p) + 4);
				if (llave < l) {
					a.writeInt(1);
					p = a.readInt();
				} else {
					a.writeInt(-1);
					a.seek((20 * p) + 12);
					p = a.readInt();
				}
			}
			// balanceo del privote
			a.seek((20 * pivote) + 4);
			l = a.readInt();
			if (l == 0) {
				a.seek((20 * pivote) + 4);
				a.writeInt(altura);
			} else if (l + altura == 0) {
				a.seek((20 * pivote) + 4);
				a.writeInt(0);
			} else {
				a.seek((20 * s) + 4);
				l = a.readInt();
				if (altura == 1) {
					if (l == 1) {
						rotarDerecha(pivote, s);
					} else {
						s = dobleRotacionDerecha(pivote, s);
					}
				} else {
					if (l == -1) {
						rotarIzquierda(pivote, s);
					} else {
						s = dobleRotacionIzquierda(pivote, s);
					}
				}
				if (pp == 0) {
					a.seek(0);
					a.writeInt(s);
				} else {
					a.seek((20 * pp) + 8);
					l = a.readInt();
					if (l == pivote) {
						a.seek((20 * pp) + 8);
					} else {
						a.seek((20 * pp) + 12);
					}
					a.writeInt(s);
				}
			}
			a.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int balanceoDerecha(int q, int[] terminar){
		int t=0,b;
		try{
			a.seek((20*q)+4);
			b=a.readInt();
			switch(b){
				case 1:
					a.seek((20*q)+4);
					a.writeInt(0);
				break;
				case -1:
					a.seek((20*q)+12);
					t=a.readInt();// t = derecha de q
					a.seek((20*t)+4);
					b=a.readInt();//balance de t
					switch(b){
						case 1:
							t=dobleRotacionIzquierda(q,t);
						break;
						case -1:
							rotarIzquierda(q, t);
						break;
						case 0:
							// derecha de q = izquierda de t
							a.seek((20*t)+8);
							b=a.readInt();
							a.seek((20*q)+12);
							a.writeInt(b);
							// izquierda de t = q
							a.seek((20*t)+8);
							a.writeInt(q);
							// balanceo de t = 1
							a.seek((20*t)+4);
							a.writeInt(1);
							terminar[0]=1;
						break;
					}
				break;
				case 0:
					a.seek((20*q)+4);
					a.writeInt(-1);
					terminar[0]=1;
				break;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return t;
	}
	
	private int balanceoIzquierda(int q, int[] terminar){
		int t=0,b;
		try{
			// balanceo d q
			a.seek((20*q)+4);
			b=a.readInt();
			switch(b){
				case -1:
					a.seek((20*q)+4);
					a.writeInt(0);
				break;
				case 1:// t = izquierda de q
					a.seek((20*q)+8);
					t=a.readInt();
					// b = balanceo de t
					a.seek((20*t)+4);
					b=a.readInt();
					switch(b){
						case 1:
							rotarDerecha(q,t);
						break;
						case -1:
							t=dobleRotacionDerecha(q,t);
						break;
						case 0:
							// izquierda de q = derecha de t
							a.seek((20*t)+12);
							b=a.readInt();
							a.seek((20*q)+8);
							a.writeInt(b);
							// derecha de t = q
							a.seek((20*t)+12);
							a.writeInt(q);
							// balanceo de t = -1
							a.seek((20*t)+4);
							a.writeInt(-1);
							terminar[0]=1;
						break;
					}
				break;
				case 0:
					// balanceo de q = 1
					a.seek((20*q)+4);
					a.writeInt(1);
					terminar[0]=1;
				break;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return t;
	}
	
	public int retirarLlave(int n){
		Stack<Integer> pila = new Stack<Integer>();
		int p,q,t,r;
		int llave, accion,ay,ay2;
		int[] terminar = new int[1];
		boolean encontro = false;
		try{
			a = new RandomAccessFile(arbol, "rw");
			a.seek(0);
			ay=a.readInt();
			if(ay==0){
				a.close();
				return 1;
			}
			terminar[0]=0;
			p=ay;
			while(!encontro && p!=0){
				pila.push(p);
				a.seek(p*20);
				ay=a.readInt();
				if(n<ay){
					// p = izquierda de p
					a.seek((20*p)+8);
					p=a.readInt();
				}
				else if(n>ay){
					// p = derecha de p
					a.seek((20*p)+12);
					p=a.readInt();
				}
				else{
					encontro=true;
				}
			}
			if(!encontro){
				a.close();
				return 2;
			}
			t=0;
			p=(Integer) pila.pop();
			a.seek(20*p);
			llave=a.readInt();
			// izquierda y derecha de p
			a.seek((20*p)+8);
			ay=a.readInt();
			ay2=a.readInt();
			if(ay==0 && ay2==0){
				accion=0;
			}
			else if(ay2==0){
				accion=1;
			}
			else if(ay==0){
				accion=2;
			}
			else{
				accion=3;
			}
			if (accion == 0 || accion == 1 || accion == 2) {
				if (!pila.empty()){
					q = (Integer) pila.pop();
					a.seek(20*q);
					ay=a.readInt();
					if(llave<ay){
						switch(accion){
						case 0:
						case 1:
							// izquierda de q = izquierda de p
							a.seek((20*p)+8);
							ay=a.readInt();
							a.seek((20*q)+8);
							a.writeInt(ay);
							t=balanceoDerecha(q, terminar);
							break;
						case 2:
							// izquierda de q = derecha de p
							a.seek((20*p)+12);
							ay=a.readInt();
							a.seek((20*q)+8);
							a.writeInt(ay);
							t=balanceoDerecha(q, terminar);	
							break;
						}
					}
					else{
						switch (accion) {
						case 0:
						case 2:
							// derecha de q = derecha de p
							a.seek((20*p)+12);
							ay=a.readInt();
							a.seek((20*q)+12);
							a.writeInt(ay);
							t = balanceoIzquierda(q, terminar);
							break;
						case 1:
							// derecha de q = izquierda de p
							a.seek((20*p)+8);
							ay=a.readInt();
							a.seek((20*q)+12);
							a.writeInt(ay);
							t = balanceoIzquierda(q, terminar);
							break;
						}
					}
				}
				else{
					switch(accion){
					case 0:
						//nulificar la raiz
						a.seek(0);
						a.writeInt(0);
						terminar[0]=1;
						break;
					case 1:
						// raiz = izquierda de p
						a.seek((20*p)+8);
						ay=a.readInt();
						a.seek(0);
						a.writeInt(ay);
						break;
					case 2:
						// raiz = derecha de p
						a.seek((20*p)+12);
						ay=a.readInt();
						a.seek(0);
						a.writeInt(ay);
						break;
					}
				}
				a.seek((20*p)+16);
				ay=a.readInt();
				removerEstudiante(ay);
				eliminarNodo(p);
			}
			else{
				pila.push(p);
				r=p;
				// p = derecha de r
				a.seek((20*r)+12);
				p=a.readInt();
				q=0;
				// izquierda de p
				a.seek((20*p)+8);
				ay=a.readInt();
				while(ay!=0){
					pila.push(p);
					q = p;
					p = ay;
					a.seek((20*p)+8);
					ay=a.readInt();
				}
				// ay = info de p
				a.seek(20*p);
				ay=a.readInt();
				llave=ay;
				a.seek(20*r);
				a.writeInt(ay);
				// ay2= referencia de r
				a.seek((20*r)+16);
				ay2=a.readInt();
				// referencia de r = referencia de p
				a.seek((20*p)+16);
				ay=a.readInt();
				a.seek((20*r)+16);
				a.writeInt(ay);
				removerEstudiante(ay2);
				// ay2 = derecha de p
				a.seek((20*p)+12);
				ay2=a.readInt();
				if(q!=0){
					// izquierda de q = derecha de p
					a.seek((20*q)+8);
					a.writeInt(ay2);
					t = balanceoDerecha(q, terminar);
				}
				else{
					// derecha de r = derecha de p
					a.seek((20*r)+12);
					a.writeInt(ay2);
					t = balanceoIzquierda(r, terminar);
				}
				eliminarNodo(p);
				q=(Integer)pila.pop();
			}
			while(!pila.empty() && terminar[0]==0){
				q=(Integer)pila.pop();
				a.seek(20*q);
				ay=a.readInt();
				if(llave<ay){
					if(t!=0){
						// izquierda de q = t
						a.seek((20*q)+8);
						a.writeInt(t);
						t=0;
					}
					t = balanceoDerecha(q, terminar);
				}
				else{
					if(t!=0){
						// derecha de q = t
						a.seek((20*q)+12);
						a.writeInt(t);
						t=0;
					}
					t=balanceoIzquierda(q, terminar);
				}
			}
			if(t!=0){
				if(pila.empty()){
					a.seek(0);
					a.writeInt(t);
				}
				else{
					q=(Integer)pila.pop();
					a.seek(20*q);
					ay=a.readInt();
					if(llave<ay){
						// izquierda de q = t
						a.seek((20*q)+8);
					}
					else{
						// derecha de q = t
						a.seek((20*q)+12);
					}
					a.writeInt(t);
				}
			}
			a.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return 0;
	}

	private int buscarLlave(int ll) {
		int b = 0, l;
		try {
			a = new RandomAccessFile(arbol, "rw");
			b = a.readInt();
			while (b != 0) {
				a.seek(20 * b);
				l = a.readInt();
				if (l == ll)
					return b;
				else {
					if (l > ll) {
						a.seek((20 * b) + 12);
						b = a.readInt();
					} else {
						a.seek((20 * b) + 8);
						b = a.readInt();
					}
				}
			}
			a.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public void agregarEstudiante(int cod, String nom, int n1, int n2, int n3) {
		if (buscarLlave(cod) == 0) {
			while (nom.length() < 10) {
				nom += " ";
			}
			int b = siguienteLibreEstudiante();
			insertarEstudiante(b, nom, n1, n2, n3);
			agregarLlave(cod, b);
		}
	}

//-----------------------------------------Trabajo con el arbol-----------------------------------------------
	
	private int aumentarArchivo() {
		int b = 0;
		try {
			b = (int) (a.length() / 20);
			a.seek(b * 20);
			for (int i = 1; i < 5; i++) {
				a.writeInt(0);
				a.writeInt(0);
				a.writeInt(0);
				a.writeInt(0);
				a.writeInt(b + i);
			}
			a.writeInt(0);
			a.writeInt(0);
			a.writeInt(0);
			a.writeInt(0);
			a.writeInt(0);
			a.seek(16);
			a.writeInt(b);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	private int siguienteLibre() {
		int i, j;
		try {
			a.seek(16);
			i = a.readInt();
			if (i == 0) {
				i = aumentarArchivo();
			}
			a.seek((i * 20) + 16);
			j = a.readInt();
			a.seek(16);
			a.writeInt(j);
		} catch (Exception e) {
			e.printStackTrace();
			i = 0;
		}
		return i;
	}

	private void modificarNodo(int num_p, int info, int bal, int der, int izq,
			int ref) {
		try {
			a.seek(num_p * 20);
			a.writeInt(info);
			a.writeInt(bal);
			a.writeInt(izq);
			a.writeInt(der);
			a.writeInt(ref);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void eliminarNodo(int n) {
		int i;
		try {
			a.seek(16);
			i = a.readInt();
			a.seek(20 * n);
			a.writeInt(0);
			a.writeInt(0);
			a.writeInt(0);
			a.writeInt(0);
			a.writeInt(i);
			a.seek(16);
			a.writeInt(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//-------------------------------------------------------Trabajo con el estudiante------------------------------------
	
	private int aumentarArchivoEstudiante() {
		int b = 0;
		try {
			b = (int) (a.length() / 22);
			a.seek(b * 22);
			for (int i = 1; i < 5; i++) {
				a.writeBytes("          ");
				a.writeInt(0);
				a.writeInt(0);
				a.writeInt(b + i);
			}
			a.writeBytes("          ");
			a.writeInt(0);
			a.writeInt(0);
			a.writeInt(0);
			a.seek(18);
			a.writeInt(b);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	private int siguienteLibreEstudiante() {
		int i, j;
		try {
			a = new RandomAccessFile(estudiantes, "rw");
			a.seek(18);
			i = a.readInt();
			if (i == 0) {
				i = aumentarArchivoEstudiante();
			}
			a.seek((i * 22) + 18);
			j = a.readInt();
			a.seek(18);
			a.writeInt(j);
			a.close();
		} catch (Exception e) {
			e.printStackTrace();
			i = 0;
		}
		return i;
	}

	private void insertarEstudiante(int num_p, String nombre, int n1, int n2,
			int n3) {
		try {
			a = new RandomAccessFile(estudiantes, "rw");
			a.seek(num_p * 22);
			a.writeBytes(nombre);
			a.writeInt(n1);
			a.writeInt(n2);
			a.writeInt(n3);
			a.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void removerEstudiante(int r) {
		int i = 0;
		try {
			RandomAccessFile b = new RandomAccessFile(estudiantes, "rw");
			b.seek(18);
			i = b.readInt();
			b.seek(22 * r);
			b.writeBytes("          ");
			b.writeInt(0);
			b.writeInt(0);
			b.writeInt(i);
			b.seek(18);
			b.writeInt(r);
			b.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}