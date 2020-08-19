package negocio;

import dados.RepositorioAutorArray;
import negocio.beans.Autor;

public class CadastroAutor 
{
private RepositorioAutorArray repositorio;
	
	
	public CadastroAutor()
	{
		this.repositorio = RepositorioAutorArray.getInstance();
	}
	
	
	
	public void cadastrar(Autor a)
	{
		this.repositorio.cadastrarAutor(a);
	}
	
	public Autor buscar(String a)
	{
		return this.repositorio.buscarAutor(a);
	}
	
	public Autor buscarLoginSenha(String a, String b)
	{
		return this.repositorio.buscarAutorLoginSenha(a, b);
	}
	
	public int buscarIndice(String a)
	{
		return this.repositorio.buscarIndiceAutor(a);
	}
	
	public Autor buscarAutorNome(String a)
	{
		return this.repositorio.buscarAutorNome(a);
	}
	
	public boolean alterar(Autor a)
	{
		return this.repositorio.alterarAutor(a);
	}
	
	public boolean excluir(String a)
	{
		return this.repositorio.excluirAutor(a);
	}
	

	
	
}
