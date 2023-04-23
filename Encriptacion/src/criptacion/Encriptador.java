package criptacion;

public class Encriptador {
	char[] diccionario = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A', 'B', 'C', 'D', 'E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
	
	public Encriptador() {
		
	}

	private int posicion(char x) {       //busco en el abecedario la palabra, sino se encuentra es un caracter especial
		int i=0;
		while(i<this.diccionario.length && x!=this.diccionario[i]) {
			i++;
		}
		if(i==this.diccionario.length) {
			return -1;
		}
		else
			return i;
	}
	
	//CESAR 
	
	public String cesar(String texto,int salto) {  //el encriptado con desplazamiento a derecha sirve como desencriptado de cesar con desplazamiento a izquierda
		String encriptado="";
		for (int i=0 ; i<texto.length(); i++) {
			int posicion= this.posicion(texto.charAt(i));
			if(posicion == -1)
				encriptado+=texto.charAt(i);
			else
				if(posicion+salto>this.diccionario.length) {
					encriptado+=this.diccionario[posicion+salto-this.diccionario.length];
				}
				else
					encriptado+=this.diccionario[posicion+salto];
		}
		return encriptado;
	}
	
	public String desencriptado(String texto, int salto) {   //el desencriptado puede servir como forma de encriptar cesar con desplazamiento a izquierda
		String desencriptado="";
		for (int i=0 ; i<texto.length(); i++) {
			int posicion= this.posicion(texto.charAt(i));
			if(posicion == -1)
				desencriptado+=texto.charAt(i);
			else
				if(posicion-salto<0) {
					desencriptado+=this.diccionario[posicion-salto+this.diccionario.length];
				}
				else
					desencriptado+=this.diccionario[posicion-salto];
		}
		return desencriptado;
	}
	
	//CESAR CON CLAVE
	
	public String cesarClave(String texto, String clave, int salto) {
		String encriptado="";
		String transformacion="";
		transformacion+=this.transforIzq(clave,salto);
		transformacion+=clave;
		transformacion+=this.transforDer(clave,salto);
		int pos=-1;
		for(int i=0; i<texto.length(); i++) {
			pos=this.posicion(texto.charAt(i));
			if(pos>=0) {
				encriptado+=transformacion.charAt(pos);
			}
			else
				encriptado+=texto.charAt(i);
		}
		return encriptado;
	}
	//ARMO EL ABECEDARIO TRANSFORMADO, LA PARTE A IZQUIERDA A LA CLAVE
	private String transforIzq(String clave, int salto) {
		int i=0;
		String transform="";
		int saltoExtra= this.simbolosEnClave(clave,salto);
		for(i=salto+saltoExtra ; i>0 ; i--) {
			if(!this.buscaEnClave(clave, this.diccionario[this.diccionario.length-i])) {
				transform+=this.diccionario[this.diccionario.length-i];
			}
		}
		return transform;
	}
	//LO UTILIZO PARA SABER SI HAY SIMBOLOS QUE POSIBLEMENTE SE DESPLACEN A IZQUIEDA DE LA CLAVE PERO SI SE ENCUENTRAN EN LA MISMA TENGO QUE LLENAR CON OTROS UN POCO MAS A IZQUIERDA
	private int simbolosEnClave(String clave,int salto) {
		int cont=0;
		for(int i=salto; i>0 ; i--) {
			if(this.buscaEnClave(clave, this.diccionario[this.diccionario.length-i])) {
				cont++;
			}
		}
		return cont;
	}
	//SE FIJA SI LOS SIMBOLOS SE ENCUENTRAN EN LA CLAVE
	private boolean buscaEnClave(String clave, char simbolo) {
		int i=0;
		while(i<clave.length() && clave.charAt(i)!= simbolo) {
			i++;
		}
		if(i<clave.length())
			return true;
		else
			return false;
	}
	
	//ARMA LA PARTE DERECHA DEL ABECEDARIO TRANSFORMADO
	private String transforDer(String clave, int salto) {
		String transform="";
		int saltoExtra= this.simbolosEnClave(clave,salto);
		for(int i=0 ; i<this.diccionario.length-(salto+saltoExtra); i++) {
			if(!this.buscaEnClave(clave, this.diccionario[i])) {
				transform+=this.diccionario[i];
			}
		}
		return transform;
	}
	
	//VIGENERE
	public String vigenere(String texto,String clave) {    
		String encriptado="";
		int indicePalClave=0;
		int posFrase=0;
		int[] posClaves= new int[clave.length()];
		posClaves= this.posicionesClaves(clave);
		for (int i=0 ; i<texto.length() ; i++) {
			posFrase=this.posicion(texto.charAt(i));
			if(posFrase>=0 && posFrase+posClaves[indicePalClave]>=this.diccionario.length) {
				encriptado+=this.diccionario[posFrase+posClaves[indicePalClave]-this.diccionario.length];
				indicePalClave++;
				if(indicePalClave==posClaves.length)
					indicePalClave=0;
			}
			else if(posFrase>=0) {
					encriptado+=this.diccionario[posFrase+posClaves[indicePalClave]];
					indicePalClave++;
					if(indicePalClave==posClaves.length)
						indicePalClave=0;
				}
				else
					encriptado+=texto.charAt(i);
		}
		return encriptado;
	}
	//ESTA FUNCION NOS PERMITE CALCULAR LOS DESPLAZAMIENTOS DE CADA LETRA DE LA CLAVE PARA NO REPETIR Y BUSCAR CADA LETRA EN EL ABECEDARIO CADA VEZ QUE HAY UNA LETRA EN LA FRASE
	private int[] posicionesClaves(String clave) {
		int[] posiciones=new int[clave.length()];
		for(int i=0; i<clave.length(); i++) {
			posiciones[i]=this.posicion(clave.charAt(i));
		}
		return posiciones;
	}
	
	//CONFEDERADOS
	
	public String confederados(String texto, String clave) {   //similar a vigenere pero aca la A=1 el abecedario tiene valores del 1 al 62
		String encriptado="";
		int indicePalClave=0;
		int posFrase=0;
		int[] posClaves= new int[clave.length()];
		posClaves= this.posicionesClaves(clave);
		for (int i=0 ; i<texto.length() ; i++) {
			posFrase=this.posicion(texto.charAt(i));
			System.out.println(posFrase);
			System.out.println(posClaves[indicePalClave]);
			if(posFrase>=0 && posFrase+posClaves[indicePalClave]+2>this.diccionario.length) {
				System.out.println(posFrase+posClaves[indicePalClave]+2-this.diccionario.length);
				encriptado+=this.diccionario[posFrase+posClaves[indicePalClave]+1-this.diccionario.length];
				indicePalClave++;
				if(indicePalClave==posClaves.length)
					indicePalClave=0;
			}
			else if(posFrase>=0) {
					encriptado+=this.diccionario[posFrase+posClaves[indicePalClave]+1];
					indicePalClave++;
					if(indicePalClave==posClaves.length)
						indicePalClave=0;
				}
				else
					encriptado+=texto.charAt(i); 
		}
		return encriptado;
	}
	
	//POLIBIO
	String polibio(String texto) {
		String encriptado="";
		int dimension=this.calculoDim();
		char [][] matriz = new char[dimension][dimension];
		matriz=this.generaMatriz(dimension);
		for(int i=0; i<texto.length() ; i++) {
			String pos=this.busquedaMatricial(matriz,texto.charAt(i),dimension);
			if(pos.equals("nada"))
				encriptado+=texto.charAt(i);
			else
				encriptado+=pos;
		}
		return encriptado;
	}
	
	//BUSCAMOS LA LETRA EN LA MATRIZ ABECEDARIO SE DEVUELVE LA FILA Y COLUMNA DONDE SE ENCUENTRA
	private String busquedaMatricial(char [][] matriz,char simbolo,int dimension ) {
		int i=0, j=0;
		while(i<dimension && matriz[i][j]!=simbolo) {
			while(j<dimension && matriz[i][j]!=simbolo) {
				j++;
			}
			if(j==dimension) {
				i++;
				j=0;
			}
		}
		if(i==dimension)
			return "nada";
		else
			return String.valueOf(i+1)+String.valueOf(j+1);
		
	}
	
	private char [][] generaMatriz(int dimension){
		int contLetras=0;
		char [][] matriz = new char[dimension][dimension];
		for(int i=0; i<dimension ; i++) {
			for(int j=0 ; j<dimension ; j++) {
				if(contLetras<this.diccionario.length) {
					matriz[i][j]=this.diccionario[contLetras];
					contLetras++;
				}
				else
					matriz[i][j]='/';    //con este caracter delimitamos que no hay cifrado para esa posicion , un desperdicio pero no queda otra
			}
		}
		return matriz;
	}
	//CALCULO LAS DIMENSIONES DE LA MATRIZ EN BASE A LOS SIMBOLOS DEL ABECEDARIO
	private int calculoDim() {
		int raiztruncada=(int)Math.sqrt(this.diccionario.length);
		if(raiztruncada*raiztruncada<this.diccionario.length)
			raiztruncada++;
		return raiztruncada;
		
	}
	
	public String trasposicion(String texto, int columnas , String rotacion) {
		int filas;
		String encriptado="";
		if(texto.length()%columnas==0)
			filas=texto.length()/columnas;
		else
			filas=((int)texto.length()/columnas)+1;
		char [][] matriz= new char [filas][columnas];
		matriz= generaMatrizFrase(texto, filas, columnas);
		matriz= this.rotacion(matriz,filas,columnas,rotacion);
		for (int i=0 ; i<filas ; i++ ) {
			for(int j=0 ; j<columnas ; j++) {
				encriptado+=matriz[i][j];
			}
		}
		return encriptado;
	}
	
	private char [][] rotacion(char [][] matriz , int filas , int columnas , String rotacion){
		char [][] matRot= new char [filas][columnas];
		for(int i=0 ; i<rotacion.length() ; i++) {
			int col=Integer.parseInt(String.valueOf(rotacion.charAt(i)))-1;
			for(int j=0 ; j<filas ; j++) {
				matRot[j][i]=matriz[j][col];
			}
		}
		return matRot;
	}
	
	private char [][] generaMatrizFrase(String texto, int filas, int columnas){
		int indexFrase=0;
		char [][] mat = new char [filas][columnas];
		for(int i=0; i<filas ; i++) {
			for(int j=0 ; j<columnas ; j++) {
				if(indexFrase==texto.length())
					mat[i][j]=' ';
				else {
					mat[i][j]= texto.charAt(indexFrase);
					indexFrase++;
				}
			}
		}
		return mat;
	}
}
