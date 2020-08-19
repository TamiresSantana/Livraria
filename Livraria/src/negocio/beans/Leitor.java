package negocio.beans;

import java.util.ArrayList;

public class Leitor extends Pessoa
{
	private ArrayList <Livro> livros;
	private int controle = 0;
	
	
	//Construtor da classe 
	public Leitor(String nome, String email, String login, String senha, String cpf)
	{
		super(nome, email, login, senha, cpf);
	}

	public Leitor() 
	{
		super();
	}

	public Leitor(Pessoa a) 
	{
		super(a);
	}

	public Leitor(String login, String senha) 
	{
		super(login, senha);
	}
	
	
	public ArrayList<Livro> meusLivros()
	{
		return this.livros;
	}
	
	public int numeroMeusLivros()
	{
		return this.livros.size();
	}
	
	
	public String ListaLivros()
	{
		int i;
		String a = "";
		for(i = 0; i < livros.size(); i++)
		{
			a += livros.get(i).livro() + "\n\n";
		}
		return a;
	}
	
	
	private void arrayLivros()
	{
		if(controle == 0)
		{
			this.livros = new ArrayList();
			controle++;
		}
	}
	
	
	//Recebe o livro
	public void comprarLivro(Livro a)
	{
		arrayLivros();
		livros.add(a);
		a.setExemplaresV();
	}
	
	//Recebe título devolve livro
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
	
	//Recebe o título
	public boolean excluirLivro(String a)
	{
		if (buscarLivro(a) != null)
		{
			livros.remove(buscarLivro(a));
			return true;
		}
		return false;
	}
	
	
	
}
