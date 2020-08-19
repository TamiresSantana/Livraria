package negocio;

import dados.RepositorioLivroArray;
import negocio.beans.Livro;

public class CadastroLivro 
{
private RepositorioLivroArray repositorio;
	
	public CadastroLivro()
	{
		this.repositorio = RepositorioLivroArray.getInstance();
	}
	
	public void cadastrar(Livro a)
	{
		this.repositorio.cadastrarLivro(a);
	}
	
	//Recebe titulo
	public Livro buscar(String a)
	{
		return this.repositorio.buscarLivro(a);
	}
	
	public int buscarIndice(String a)
	{
		return this.repositorio.buscarIndiceLivro(a);
	}
	
	public boolean excluir(String a)
	{
		return this.repositorio.excluirLivro(a);
	}
	
	public String lista()
	{
		return this.repositorio.listaLivro();
	}
	
	
	public int numeroLista()
	{
		return this.repositorio.numeroListaLivro();
	}
	
	public Livro livroIndice(int a)
	{
		return this.repositorio.livroIndice(a);
	}
	
	
	

	
}
