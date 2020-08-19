package dados;

import java.util.ArrayList;

import negocio.beans.Leitor;

public class RepositorioLeitorArray 
{

ArrayList <Leitor> leitores;
	
	private static RepositorioLeitorArray instance;
	
	public static RepositorioLeitorArray getInstance() 
	{
		if (instance == null) 
	    {
	       instance = new RepositorioLeitorArray();
	    }
	      return instance;
	 }
	
	private RepositorioLeitorArray()
	{
		this.leitores = new ArrayList();
	}
	
	
	//M�todo recebe uma Pessoa 'a' e a cadastra em 
	// um ArrayList de Leitores
	public void cadastrarLeitor(Leitor a)
	{
		leitores.add(a);
	}
	
	

	//Busca Leitor semelhante "Email"
	//Retorna true se ja existe um Leitor com esse email
	// retorna false se n�o tem Leitor com o mesmo email
	public boolean buscarEmailLeitor(String a)
	{
		for(int i = 0; i < leitores.size(); i++)
		{
			if(leitores.get(i).getEmail().equals(a))
			{
				return true;
			}
		}
		return false;
	}
	
	
	
	//M�todo recebe uma String 'a' com o cpf/cnpj 
	// e devolve o Leitor procurado 
	// ou devolve null se o leitor n�o tiver cadastro
	public Leitor buscarLeitor(String a) 
	{
		int x = leitores.size();
		int i = 0;
		
		for(i = 0; i <= x; i++)
		{
			if(a.equals(leitores.get(i).getCpf()))
			{
				return leitores.get(i);
			}
		}
		return null;
	}
	
	//Devolve o Leitor a partir de Login e Senha
	public Leitor buscarLeitorLoginSenha(String a, String b)
	{
		int x = leitores.size();
		int i = 0;
		
		for(i = 0; i <= x; i++)
		{
			if(a.equals(leitores.get(i).getLogin()))
			{
				if(b.equals(leitores.get(i).getSenha()))
				{
					return leitores.get(i);
				}
			}
		}
		return null;
	}
	
	//M�todo recebe uma String 'a' com o cpf/cnpj
	// e devolve o indice do leitor no Array de Leitores
	// ou, devolve -7 se o leitor n�o for encontrado
	public int buscarIndiceLeitor(String a) 
	{
		int x = leitores.size();
		int i = 0;
		
		for(i = 0; i <= x; i++)
		{
			if(a.equals(leitores.get(i).getCpf()))
			{
				return i;
			}
		}
		return -7;
	}
	
	//M�todo recebe uma Pessoa 'a' e busca a mesma no Array de Leitores.
	//Se existe, altera as informa��es originais pelas fornecidas e devolve 'true',
	// se n�o, devolve false
	public boolean alterarLeitor(Leitor a)
	{
		if (buscarLeitor(a.getCpf()) != null)
		{
			buscarLeitor(a.getCpf()).setPessoa(a);
			return true;
		}
		return false;
	}
	
	
	//M�todo recebe uma String 'a' == cpf/cnpj
	// e se encontrado, o leitor � excluido do Array de Leitores
	// e devolve true
	//Se n�o, devolve false
	public boolean excluirLeitor(String a)
	{
		if (buscarLeitor(a) != null)
		{
			leitores.remove(buscarLeitor(a));
			return true;
		}
		return false;
	}


}
