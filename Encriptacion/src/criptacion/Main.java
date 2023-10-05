package criptacion;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String frase="Si crees que la tecnología puede solventar tus problemas de seguridad, entonces no entiendes los problemas y no entiendes de tecnología.";
	//	String frase=",";
		Encriptador enc= new Encriptador();
		String encript=enc.cesar(frase, 9);
		System.out.println(encript);
		//System.out.println(enc.desencriptado(encript, 12));
	//	String clave="submarino";
	//	System.out.println(enc.cesarClave(frase, clave, 6));
		String clave="A";
		System.out.println(enc.vigenere(frase, clave));
	//	System.out.println(enc.polibio(frase));
		System.out.println(enc.confederados(frase, clave));
	//	encript=enc.trasposicion(frase, 4, "3214");
	//	System.out.println(encript);
	//	clave="Taxi";
	//	encript=enc.vigenere(encript, clave);
	//	System.out.println(encript);
	//	encript=enc.trasposicion(encript, 5, "42315");
	//	System.out.println(encript);
	//	clave="Chofer";
	//	encript=enc.vigenere(encript, clave);
	//	System.out.println(encript);
	//	encript=enc.trasposicion(frase, 10, "4208917653");
	//	System.out.println(encript);
	//	clave="Rueda";
	//	encript=enc.vigenere(encript, clave);
	//	System.out.println(encript);
	}

}
