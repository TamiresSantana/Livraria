package dados;

import java.util.ArrayList;

import negocio.beans.Autor;

public class RepositorioAutorArray 
{
ArrayList <Autor> autores;
	
	private static RepositorioAutorArray instance;
	
	
	public static RepositorioAutorArray getInstance() 
	{
		if (instance == null) 
	    {
	       instance = new RepositorioAutorArray();
	    }
	      return instance;
	 }

	private RepositorioAutorArray()
	{
		this.autores = new ArrayList();
	}
	
	
	
	//M�todo recebe um Autor'a' e a cadastra em 
	// um ArrayList de Autores
	public void cadastrarAutor(Autor a)
	{
		autores.add(a);
	}
	
	
	//M�todo recebe uma String 'a' com o cpf/cnpj 
	// e devolve o Autor procurado 
	// ou devolve null se o autor n�o tiver cadastro
	public Autor buscarAutor(String a) 
	{
		int x = autores.size();
		int i = 0;
		
		for(i = 0; i <= x; i++)
		{
			if(a.equals(autores.get(i).getCpf()))
			{
				return autores.get(i);
			}
		}
		return null;
	}
	
	
	//Busca Autor semelhante "Email"
	//Retorna true se ja existe um Autor com esse email
	// retorna false se n�o tem Autor com o mesmo email
	public boolean buscarEmailAutor(String a)
	{
		for(int i = 0; i < autores.size(); i++)
		{
			if(autores.get(i).getEmail().equals(a))
			{
				return true;
			}
		}
		return false;
	}
	
	
	//M�todo recebe uma String 'a' com o cpf/cnpj
	// e devolve o indice do autor no Array de Autores
	// ou, devolve -7 se o autor n�o for encontrado
	public int buscarIndiceAutor(String a) 
	{
		int x = autores.size();
		int i = 0;
		
		for(i = 0; i <= x; i++)
		{
			if(a.equals(autores.get(i).getCpf()))
			{
				return i;
			}
		}
		return -7;
	}
	
	//M�todo recebe uma String 'a' com o nome
		// e devolve o Autor procurado 
		// ou devolve null se o autor n�o tiver cadastro
		public Autor buscarAutorNome(String a) 
		{
			int x = autores.size();
			int i = 0;
			
			for(i = 0; i <= x; i++)
			{
				if(a.equals(autores.get(i).getNome()))
				{
					return autores.get(i);
				}
			}
			return null;
		}
		
	
	//Devolve o autor apartir do login e senha
	public Autor buscarAutorLoginSenha(String a, String b)
	{
		int x = autores.size();
		int i = 0;
		
		for(i = 0; i <= x; i++)
		{
			if(a.equals(autores.get(i).getLogin()))
			{
				if(b.equals(autores.get(i).getSenha()))
				{
					return autores.get(i);
				}
			}
		}
		return null;
	}
	
	//M�todo recebe um Autor 'a' e busca a mesma no Array de Autores.
	//Se existe, altera as informa��es originais pelas fornecidas e devolve 'true',
	// se n�o, devolve false
	public boolean alterarAutor(Autor a)
	{
		if (buscarAutor(a.getCpf()) != null)
		{
			buscarAutor(a.getCpf()).setPessoa(a);
			return true;
		}
		return false;
	}
	
	
	//M�todo recebe uma String 'a' == cpf/cnpj
	// e se encontrado, o autor � excluido do Array de Autores
	// e devolve true
	//Se n�o, devolve false
	public boolean excluirAutor(String a)
	{
		if (buscarAutor(a) != null)
		{
			autores.remove(buscarAutor(a));
			return true;
		}
		return false;
		
		
	}
	

	
}
