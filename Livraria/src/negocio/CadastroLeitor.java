package negocio;

import dados.RepositorioLeitorArray;
import negocio.beans.Leitor;

public class CadastroLeitor 
{
private RepositorioLeitorArray repositorio;
	
	public CadastroLeitor() 
	{
		this.repositorio = RepositorioLeitorArray.getInstance();
	}
	    
	public void cadastrar(Leitor a)
	{
		this.repositorio.cadastrarLeitor(a);
	}
	
	//Cpf
	public Leitor buscar(String a)
	{
		return this.repositorio.buscarLeitor(a);
	}
	
	public int buscarIndice(String a)
	{
		return this.repositorio.buscarIndiceLeitor(a);
	}
	
	//Login , senha
	public Leitor buscarS(String a, String b)
	{
		return this.repositorio.buscarLeitorLoginSenha(a, b);
	}
	
	public boolean alterar(Leitor a)
	{
		return this.repositorio.alterarLeitor(a);
	}
	
	public boolean excluir(String a)
	{
		return this.repositorio.excluirLeitor(a);
	}
	
	

}
