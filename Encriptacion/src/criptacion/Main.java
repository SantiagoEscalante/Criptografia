package criptacion;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String frase="Nos vemos el lunes 24 a las 18hs en Plaza Mitre";
		Encriptador enc= new Encriptador();
		String encript=enc.cesar(frase, 12);
		//System.out.println(encript);
		//System.out.println(enc.desencriptado(encript, 12));
	//	String clave="submarino";
	//	System.out.println(enc.cesarClave(frase, clave, 6));
		String clave="ventilado";
	//	System.out.println(enc.vigenere(frase, clave));
	//	System.out.println(enc.polibio(frase));
	//	System.out.println(enc.confederados(frase, clave));
		encript=enc.trasposicion(frase, 4, "3214");
		System.out.println(encript);
		clave="Taxi";
		encript=enc.vigenere(encript, clave);
		System.out.println(encript);
		encript=enc.trasposicion(encript, 5, "42315");
		System.out.println(encript);
		clave="Chofer";
		encript=enc.vigenere(encript, clave);
		System.out.println(encript);
		encript=enc.trasposicion(encript, 6, "623415");
		System.out.println(encript);
		clave="Rueda";
		encript=enc.vigenere(encript, clave);
		System.out.println(encript);
	}

}
