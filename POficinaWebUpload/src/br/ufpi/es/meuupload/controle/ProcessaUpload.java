package br.ufpi.es.meuupload.controle;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.ufpi.es.meuupload.utilidades.Constantes;

/**
 * Faz o controle do processamento do arquivo durante o Upload
 * @author armandosoaressousa
 *
 */
public class ProcessaUpload {

	/**
	 * Faz o envio do arquivo
	 * @param request requisição do servlet
	 * @param nomeArquivo nome do Arquivo
	 * @param factory armazena um item de arquivo
	 * @param uploadPach diretório onde o arquivo será armazenado
	 * @return nome do arquivo que foi enviado
	 * @throws FileUploadException gera exceção caso dê erro no upload
	 * @throws Exception gera exceção caso dê algum erro
	 */
	public String executa(HttpServletRequest request, String nomeArquivo, DiskFileItemFactory factory, String uploadPath)
			throws FileUploadException, Exception {
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// Tamanho maximo do arquivo que pode ser armazenado
		upload.setSizeMax(Constantes.MAX_REQUEST_SIZE);
		
		System.out.println("Iniciando upload...");
		
		// Parse the request
		List items = upload.parseRequest(request);
		Iterator interator = items.iterator();
		while (interator.hasNext()) {
			FileItem item = (FileItem) interator.next();
			if (!item.isFormField()) {
				String fileName = new File(item.getName()).getName();
				nomeArquivo = fileName; 
				String filePath = uploadPath + System.getProperty(Constantes.FILE_SEPARATOR) + fileName;
				File uploadedFile = new File(filePath);
				// salva o arquivo no diretorio
				item.write(uploadedFile);
			}
		}
		return nomeArquivo;
	}	
}

