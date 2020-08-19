package dados;

import java.util.ArrayList;

import negocio.beans.Livro;

public class RepositorioLivroArray 
{
	ArrayList <Livro> livros;
	private static RepositorioLivroArray instance;
	
	public static RepositorioLivroArray getInstance() 
	{
		if (instance == null) 
	    {
	       instance = new RepositorioLivroArray();
	    }
	      return instance;
	 }
	
	private RepositorioLivroArray()
	{
		this.livros = new ArrayList();
	}
	
	public void cadastrarLivro(Livro a)
	{
		livros.add(a);
	}
	
	//Recebe titulo
	public Livro buscarLivro(String a)
	{
		int x = livros.size();
		int i = 0;
		
		for(i = 0; i <= x; i++)
		{
			if(a.equals(livros.get(i).getTitulo()))
			{
				return livros.get(i);
			}
		}
		return null;
	}
	
	public int buscarIndiceLivro(String a)
	{
		int x = livros.size();
		int i = 0;
		
		for(i = 0; i <= x; i++)
		{
			if(a.equals(livros.get(i).getTitulo()))
			{
				return i;
			}
		}
		return -7;
	}
	
	
	//Devolve true se tudo der certo
	public boolean excluirLivro(String a)
	{
		Livro t = buscarLivro(a);
		if(t != null)
		{
			livros.remove(t);
			return true;
		}
		return false;
	}
	
	public String listaLivro()
	{
		int i;
		String a = "";
		for(i = 0; i < livros.size(); i++)
		{
			a += livros.get(i).livro() + "\n\n";
		}
		return a;
	}
	
	public int numeroListaLivro() 
	{
		return livros.size();
	}
	
	//Recebe o indice do livro no array e devolve o livro
	public Livro livroIndice(int a)
	{
		int x = livros.size();
		int i = 0;
		
		for(i = 0; i <= x; i++)
		{
			if(a == i)
			{
				return livros.get(i);
			}
		}
		return null;
	}
	

	
	
	
}
