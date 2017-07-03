package br.ufpi.es.meuupload.controle;

import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import br.ufpi.es.meuupload.utilidades.Constantes;

/**
 * Cria uma estrutura para armazenar o arquivo para ser manipulado durante o Upload
 * @author armandosoaressousa
 *
 */
public class ArmazenaItemArquivo {

	/*
	 * 	Cria um factory para armazenar um item de arquivo baseado no DiskFile
	*/
	public DiskFileItemFactory executa() {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// Tamanho max do arquivo que pode ser armazenado em memoria
		factory.setSizeThreshold(Constantes.MAX_MEMORY_SIZE);
		
		// Cria um diretorio temporario para manipular o arquivo
		String temp = System.getProperty(Constantes.USER_DIRECTORY) + System.getProperty(Constantes.FILE_SEPARATOR) + 
				Constantes.DATA_DIRECTORY + System.getProperty(Constantes.FILE_SEPARATOR) + Constantes.TEMP_DIRECTORY;
		factory.setRepository(new File(temp));
		return factory;
	}	
	
}

