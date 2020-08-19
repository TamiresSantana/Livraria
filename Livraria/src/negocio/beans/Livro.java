package negocio.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Livro 
{
	private String titulo;
	private String preco;
	private String paginas;
	private ArrayList <Autor> autores;
	private String genero;
	private String sinopse;
	private int exemplaresV;
	private String idioma;
	InputStream pdf;
	ImageView capa;
	
	
	//Construtor da classe 
	public Livro(String titulo, String preco, String paginas, ArrayList <Autor> autor, String genero, 
			String sinopse, String idioma)
	{
		super();
		
		this.titulo = titulo;
		this.preco = preco;
		this.paginas = paginas;
		this.autores.addAll(autor);
		this.genero = genero;
		this.sinopse = sinopse;
		this.idioma = idioma;
	}
	
	public void setAutor(Autor a)
	{
		this.autores.add(a);
	}
	
	public void setAutores(ArrayList <Autor> a)
	{
		this.autores = a;
	}
	
	public Livro()
	{
		this.exemplaresV = 0;
	}
	
	
	//Métodos Getters
	
	public String getTitulo()
	{
		return this.titulo;
	}
	
	public String getPreco()
	{
		return this.preco;
	}
	
	public String getPaginas()
	{
		return this.paginas;
	}
	
	
	public String getAutorNome()
	{
		int x = this.autores.size();
		int i; String a = "";
		for(i = 0; i < x; i++)
		{
			a += this.autores.get(i).getNome();
		}
		return a;
	}
	
	public String getGenero()
	{
		return this.genero;
	}
	
	
	public String getSinopse()
	{
		return this.sinopse;
	}
	
	public int getExemplaresV()
	{
		return this.exemplaresV;
	}
	
	public String getIdioma()
	{
		return this.idioma;
	}
	
	
	//Métodos Setters
	
	public void setTitulo(String a)
	{
		this.titulo = a;
	}
	
	public void setPreco(String a)
	{
		this.preco = a;
	}
	
	public void setPaginas(String a)
	{
		this.paginas = a;
	}
	
	public void setGenero(String a)
	{
		this.genero = a;
	}
	
	public void addGenero(String a)
	{
		String aux = a + ". ";
		this.genero += aux;
	}
	
	public void setSinopse(String a)
	{
		this.sinopse = a;
	}
	
	public void setIdioma (String a)
	{
		this.idioma = a;
	}
	
	public void addIdioma(String a)
	{
		String aux = a + ". ";
		this.idioma += aux;
	}
	
	public void setExemplaresV ()
	{
		this.exemplaresV += 1;
	}
	
	
	//ToString com todas as informações do livro exceto o enredo
	
	
	public String livro()
	{
		int i;
		String d = "";
		for(i = 0; i < autores.size(); i++)
		{
			d += autores.get(i).getNome() + ". ";
		}
		
		return "" +"\nTítulo "+ this.titulo+ "\nPreço: " +this.preco+ "\nPáginas: " 
			+this.paginas+ "\nAutores: " +d+ "\nGênero: " +this.genero+ "\nIdioma: " +this.idioma+ 
			"\nSinopse: " +this.sinopse;
	}
	
	
	public ArrayList<Autor> autoresArray()
	{
		return this.autores;
	}
	
	public int getNumeroAutores()
	{
		return this.autores.size();
	}
	
	public InputStream getPdf()
	{
		return this.pdf;
	}
	
	
	public Livro getLivro()
	{
		return this;
	}
	
	public void setLivro(Livro a)
	{
		this.titulo = a.titulo;
		this.preco = a.preco;
		this.paginas = a.paginas;
		this.genero = a.genero;
		this.sinopse = a.sinopse;
	}
	
	//Recebe uma String com o caminho da imagem e insere no livro
	public void insereCapa(String a)
	{
		Image n = new Image(a,150,200,false,false);
		this.capa = new ImageView(n);
	}
	
	public ImageView buscarCapa()
	{
		return this.capa;
	}
	
	public void setPdf(File a)
	{
		try
		{
			InputStream aux = new FileInputStream(a);
			this.pdf = aux;
		}
		catch(Exception e)
		{
		}
	}
	
	public void setCapa(ImageView a)
	{
		this.capa = a;
	}
	
}
