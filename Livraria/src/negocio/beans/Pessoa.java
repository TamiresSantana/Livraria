package negocio.beans;

public abstract class Pessoa 
{
	private String nome;
	private String email;
	private String login;
	private String senha;
	private String cpf;
	
	
	//Construtor da classe
	public Pessoa(String nome, String email, String login, String senha, String cpf)
	{
		super();
		
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.cpf = cpf;
	}
	
	public Pessoa(String login, String senha)
	{
		super();
		this.login = login;
		this.senha = senha;
	}
	
	public Pessoa()
	{
	}

	public Pessoa(Pessoa a) 
	{}

	//Métodos Getters
	public String getNome() 
	{
		return nome;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getLogin()
	{
		return login;
	}
	
	public String getSenha()
	{
		return senha;
	}
	
	public String getCpf()
	{
		return cpf;
	}
	
	public Pessoa getPessoa()
	{
		return this;
	}
	
	//Métodos Setters
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	
	public void setEmail(String a)
	{
		this.email = a;
	}

	public void setLogin(String a)
	{
		this.login = a;
	}
	
	public void setSenha(String a)
	{
		this.senha = a;
	}
	
	
	public void setPessoa(Pessoa a)
	{
		this.nome = a.getNome();
		this.email = a.getEmail();
		this.login = a.getLogin();
		this.senha = a.getSenha();
	}
	
	//ToString com nome, email, cpf, login
	public String toString()
	{
		String a = this.nome;
		String b = this.email;
		String c = this.cpf;
		String d = this.login;
		
		return "Nome: " +a+ "\nEmail: " +b+ "\nCPF/CNPJ: " +c+ "\nLogin: " +d;
	}
	

	
	
}
