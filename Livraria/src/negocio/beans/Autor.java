package negocio.beans;

import java.util.ArrayList;

public class Autor extends Pessoa
{
private ArrayList <Livro> livros;
	
	private int controle = 0;
	
	//Construtor da classe 
	public Autor(String nome, String email, String login, String senha, String cpf)
	{
		super(nome, email, login, senha, cpf);
	}

	public Autor() 
	{
		super();
	}

	public Autor(Pessoa a) 
	{
		super(a);
	}

	public Autor(String login, String senha) 
	{
		super(login, senha);
	}
	

	public String ListaLivros()
	{
		return "" +livros;
	}
	
	
	public void cadastrarLivro(Livro a)
	{
		arrayLivros();
		livros.add(a);
	}
	
	//Recebe o título e devolve o livro
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
	
	//Recebe o título e devolve a posição o array ou -7
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
	
	public ArrayList<Livro> meusLivros()
	{
		return this.livros;
	}
	
	//Recebe um Livro e o modifica, devolve true se tudo der certo
	public boolean alterarLivro(Livro a)
	{
		if (buscarLivro(a.getTitulo()) != null)
		{
			buscarLivro(a.getTitulo()).setLivro(a);
			return true;
		}
		return false;
	}
	
	//Recebe o titulo e exclui, devolve true se tudo der certo
	public boolean excluirLivro(String a)
	{
		if (buscarLivro(a) != null)
		{
			livros.remove(buscarLivro(a));
			return true;
		}
		return false;
	}
	
	
	private void arrayLivros()
	{
		if(controle == 0)
		{
			this.livros = new ArrayList();
			controle++;
		}
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
	
	
	
	
	
	
	
	
	
	
	

	
	
}
