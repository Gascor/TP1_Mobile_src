package TP2_Cemaphore_src;

import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

public class Main {

	public static void main(String[] args) {
		CemaphoreBinaire cb = new CemaphoreBinaire(1);
		Affichage TA = new Affichage("AAA",cb);
		Affichage TC = new Affichage("CC",cb);
		Affichage TB = new Affichage("BB",cb);
		TB.start();
		TA.start();
		TC.start();

	}

}
