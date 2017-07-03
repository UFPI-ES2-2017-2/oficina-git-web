package br.ufpi.es.meuupload.controle;

import java.io.IOException;
import java.io.PrintWriter;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.ufpi.es.meuupload.utilidades.Constantes;
import br.ufpi.es.meuupload.fronteira.*;

/**
 * Servlet principal que vai controlar o(s) Upload(s) do(s) arquivo(s) passados pelo formulário de Upload
 * @author armandossrecife
 *
 */
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Formulario form = new Formulario();
	private String uploadPath;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
    }

	/**
	 * Inicializa o servlet
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException { 
        uploadPath = System.getProperty(Constantes.USER_DIRECTORY);
		
		System.out.println("Servlet inicializado...");
		System.out.println("Path do upload: " + uploadPath);
	}

	/**
	 * Get do usuario
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter saida = response.getWriter();
		
		form.limpaPagina();
		form.montaPagina("Bem vindo ao fileupload");
		saida.print(form.pagina());
	}

	/**
	 * Envia resposta para o usuario
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter saida = response.getWriter();
		String processamento="erro";
		String nomeArquivo="";

		checaMultipart(request);
		DiskFileItemFactory factory = new ArmazenaItemArquivo().executa();
		try {
			nomeArquivo = new ProcessaUpload().executa(request, nomeArquivo, factory, this.uploadPath);
			processamento = "sucesso";
		} catch (FileUploadException ex) {
			throw new ServletException(ex);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		finally{
			System.out.println("Upload do arquivo " + nomeArquivo + " concluido com " + processamento + "!");
			form.limpaPagina();
			form.montaPagina("Upload do arquivo " + nomeArquivo + " concluido com " + processamento + "!");
			saida.print(form.pagina());
		}		
	}

	/**
	 * Check o upload request do tipo multipart
	 * @param request requisição do Servlet
	 */
	private void checaMultipart(HttpServletRequest request) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipart) {
			return;
		}
	}

}
