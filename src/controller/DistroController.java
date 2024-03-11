package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DistroController {

	public DistroController() {
		super();
	}

	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}

	@SuppressWarnings("deprecation")
	public void exibeDistro() {
		String os = os();
		if (os.contains("Linux")) {
			try {
				Process p = Runtime.getRuntime().exec("cat /etc/os-release");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null) {
					String[] partes = linha.split("=");
					if (partes[0].equals("NAME")) {
						String nomeDistro = partes[1];
						System.out.println("Nome da distro: " + nomeDistro);
						linha = buffer.readLine();
					} 
					
					if (partes[0].equals("VERSION")) {
						String versaoDistro = partes[1];
						System.out.println("Versão da distro: " + versaoDistro);
					}
					linha = buffer.readLine();
				}
				fluxo.close();
				leitor.close();
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Erro: Sistema Operacional não reconhecido.");
		}
	}

}
