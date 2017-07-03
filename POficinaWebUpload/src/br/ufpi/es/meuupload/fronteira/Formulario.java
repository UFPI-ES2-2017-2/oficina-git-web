package br.ufpi.es.meuupload.fronteira;

/**
 * Gerencia a criação do formulário 
 * @author Armando
 *
 */
public class Formulario {
	public String conteudo="";
	public String resposta="";
	
	/**
	 * Monta a pagina principal
	 * @param mensagem de boas vindas
	 */
	public void montaPagina(String mensagem){
		conteudo = conteudo + "<html>";
		conteudo = conteudo + "<head>";
		conteudo = conteudo + "<title>File Upload</title>";
		conteudo = conteudo + "</head>";
		conteudo = conteudo + "<body>";
		conteudo = conteudo + "<h1>" + mensagem + "</h1><br>";
		conteudo = conteudo + "<form method=POST action='upload' enctype='multipart/form-data'>";
		conteudo = conteudo + "<br>Arquivo: <input type=file name=dataFile id='fileChooser'/>";
		conteudo = conteudo + "<br><input type='submit' value='Upload' />";
		conteudo = conteudo + "</form>";
		conteudo = conteudo + "</body>";
		conteudo = conteudo + "</html>";			
	}
	
	/**
	 * Exibe a pagina principal completa
	 * @return o  conteudo da pagina principal
	 */
	public String pagina(){
		return this.conteudo;
	}
	
	/**
	 * Limpa o conteudo da pagina principal
	 */
	public void limpaPagina(){
		this.conteudo = "";
	}
}