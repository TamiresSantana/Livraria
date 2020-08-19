package negocio;

import negocio.beans.Autor;
import negocio.beans.Leitor;
import negocio.beans.Livro;

public class Servidor 
{
	private CadastroAutor autores;
	private CadastroLeitor leitores;
	private CadastroLivro livros;
	
	private static Servidor instance;
	
	private Servidor()
	{
		this.autores = new CadastroAutor();
		this.leitores = new CadastroLeitor();
		this.livros = new CadastroLivro();
	}
	
	
	public static Servidor getInstance()
	{
		 if (instance == null) 
		 {
			 instance = new Servidor();
	     }
		 return instance;
	}
	
	public void cadastrarAutor(Autor a)
	{
		 autores.cadastrar(a);
	}
	
	public Autor buscarAutor(String a)
	{
		return autores.buscar(a);
	}
	
	
	public Autor buscarAutorLoginSenha(String a, String b)
	{
		return autores.buscarLoginSenha(a, b);
	}
	
	public int buscarIndiceAutor(String a)
	{
		return autores.buscarIndice(a);
	}
	
	public Autor buscarAutorNome(String a)
	{
		return autores.buscarAutorNome(a);
	}
	
	public boolean alterarAutor(Autor a)
	{
		return autores.alterar(a);
	}
	
	public boolean excluirAutor(String a)
	{
		return autores.excluir(a);
	}
	
	
	public void cadastrarLeitor(Leitor a)
	{
		 leitores.cadastrar(a);
	}
	
	public Leitor buscarLeitor(String a)
	{
		return leitores.buscar(a);
	}
	
	public Leitor buscarLeitorLoginSenha(String a, String b)
	{
		return leitores.buscarS(a, b);
	}
	
	public int buscarIndiceLeitor(String a)
	{
		return leitores.buscarIndice(a);
	}
	
	public boolean alterarLeitor(Leitor a)
	{
		return leitores.alterar(a);
	}
	
	public boolean excluirLeitor(String a)
	{
		return leitores.excluir(a);
	}
	
	
	public void cadastrarLivro(Livro a)
	{
		 livros.cadastrar(a);
	}
	
	//Recebe titulo
	public Livro buscarLivro(String a)
	{
		return livros.buscar(a);
	}
	
	public int buscarIndiceLivro(String a)
	{
		return livros.buscarIndice(a);
	}
	
	public boolean excluirLivro(String a)
	{
		return livros.excluir(a);
	}
	
	public String listaLivros()
	{
		return livros.lista();
	}
	
	public int numeroListaLivro()
	{
		return livros.numeroLista();
	}
	
	public Livro livroIndice(int a)
	{
		return livros.livroIndice(a);
	}
	

}
