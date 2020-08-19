package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.qoppa.pdfNotes.PDFNotesBean;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import negocio.Servidor;
import negocio.beans.Autor;
import negocio.beans.Leitor;
import negocio.beans.Livro;

public class TelaFx extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	//Inserir imagem de fundo
	public void fundoBorderPane(String a, BorderPane pane)
	{
		BackgroundImage myBI= new BackgroundImage(new Image(a,1200,650,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		pane.setBackground(new Background(myBI));
	}
	
	public void fundoScrollPane(String a, ScrollPane pane)
	{
		BackgroundImage myBI= new BackgroundImage(new Image(a,1200,650,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		pane.setBackground(new Background(myBI));
	}
	
	
	
	
	//Iserir título
	public Text ebookStore()
	{
		Text tEbookStore = new Text(" EbookStore");
		tEbookStore.setFont(Font.font("Gabriola", FontWeight.BOLD, FontPosture.ITALIC, 120));
		tEbookStore.setFill(Color.FLORALWHITE);
		DropShadow sombra1 = new DropShadow(10,10,10,Color.LIGHTSKYBLUE);
		tEbookStore.setEffect(sombra1);
		
		return tEbookStore;
	}
	
	
	
	public void start(Stage palcoInicio)
	{
		Servidor ebookStore = Servidor.getInstance();
		
		BorderPane paneInicio = new BorderPane();
		paneInicio.setPrefSize(1200, 650);
		Scene cenaInicio  = new Scene(paneInicio);
		
		
		
		//Fundo e título
		fundoBorderPane("https://i.pinimg.com/originals/4d/56/08/4d5608279a7b57df5e50fe0f2b408e3b.jpg", paneInicio);
		
		paneInicio.setBottom(ebookStore());
		
		//Cursor
		
		Image image1 = new Image("https://fenixlive.com/wp-content/uploads/2016/06/fenix_icon_RGB.png"); 
		cenaInicio.setCursor(new ImageCursor(image1));
		
		
		//Área do login
		Button entrarInicio = new Button("Entrar");
		entrarInicio.setOnAction(e -> {
			
			Stage palcoLogin = new Stage();
			BorderPane paneLogin = new BorderPane();
			Scene cenaLogin = new Scene(paneLogin);
			paneLogin.setPrefSize(250, 350);
			GridPane gridPane = new GridPane();
			
			Text loginInicio = new Text("login");
			Text senhaInicio = new Text("senha");
			
			TextField textLoginInicio = new TextField();
			PasswordField textSenhaInicio = new PasswordField();
			
			ToggleGroup group = new ToggleGroup();
			RadioButton rLeitor = new RadioButton("leitor");
			rLeitor.setToggleGroup(group);
			RadioButton rAutor = new RadioButton("autor");
			rAutor.setToggleGroup(group);
			
			final Label avisoSenha = new Label("");
			Button enterLoginInicio = new Button("Entrar");
			
			enterLoginInicio.setOnAction(t -> {
				
				//Login autor
				if(rAutor.isSelected() == true)
				{
					Autor autorLogin = new Autor();
					try
					{
						autorLogin = ebookStore.buscarAutorLoginSenha(textLoginInicio.getText(), textSenhaInicio.getText());
						
						if(autorLogin != null)
						{
							Autor autor = autorLogin;
							int numMeusLivros = 0;
							try
							{
								numMeusLivros = autor.meusLivros().size();
							}
							catch(Exception a)
							{
							}
							
							//Área de interação com o autor
							avisoSenha.setText("Senha correta");
							avisoSenha.setTextFill(Color.rgb(21, 117, 84));
							
							//Montando a tela
							Stage palcoAutor = new Stage();
							
							TabPane paneTabs = new TabPane();
							Scene cenaAutor = new Scene(paneTabs);
							paneTabs.setPrefSize(1200, 650);
							
							
							Tab cadastrarLivro = new Tab("Cadastrar");
							Tab meusLivros = new Tab("Meus livros");
							Tab autorPerfil = new Tab("Meu Perfil");
							
							Livro livro =  new Livro();
							
							BorderPane paneCadastrarLivro = new BorderPane();
							BorderPane paneMeusLivros = new BorderPane();
							BorderPane paneAutorPerfil = new BorderPane();
							
							cadastrarLivro.setContent(paneCadastrarLivro);
							meusLivros.setContent(paneMeusLivros);
							autorPerfil.setContent(paneAutorPerfil);
							
							cadastrarLivro.setClosable(false);
							meusLivros.setClosable(false);
							autorPerfil.setClosable(false);
							
							
							ScrollPane scCadastrar = new ScrollPane();
							scCadastrar.setHbarPolicy (ScrollBarPolicy.NEVER);
							scCadastrar.setVbarPolicy (ScrollBarPolicy.ALWAYS);
							ScrollPane scLivros = new ScrollPane();
							scLivros.setHbarPolicy (ScrollBarPolicy.NEVER);
							scLivros.setVbarPolicy (ScrollBarPolicy.ALWAYS);
							ScrollPane scPerfil = new ScrollPane();
							scPerfil.setHbarPolicy (ScrollBarPolicy.NEVER);
							scPerfil.setVbarPolicy (ScrollBarPolicy.ALWAYS);
							
							
							paneCadastrarLivro.setCenter(scCadastrar);
							paneMeusLivros.setCenter(scLivros);
							paneAutorPerfil.setCenter(scPerfil);
							
							
							//Montando a tela de cadastro de um novo livro
							
							
							GridPane cadLivro =  new GridPane();
							
							Text tituloCads = new Text("Título");
							Text precoCads = new Text("Preço");
							Text paginasCads = new Text("Páginas");
							Text generoCads = new Text("Gênero");
							Text idiomaCads = new Text("\nIdioma");
							Text sinopseCads = new Text("\nSinopse");
							Text capaCads = new Text("\nCapa");
							Text pdfCads = new Text("\nLivro");
							Text outroGeneroCads = new Text("outro");
							Text outroIdiomaCads = new Text("\noutro");
							
							TextField tituloTextFCads = new TextField();
							TextField precoTextFCads = new TextField();
							TextField paginasTextFCads = new TextField();
							/*
							TextField capaTextFCads = new TextField();
							capaTextFCads.setPromptText("Indique o diretório do arquivo");
							TextField pdfTextFCads = new TextField();
							pdfTextFCads.setPromptText("Indique o diretório do arquivo");
							*/
							
							
							
							TextField generoTextFCads = new TextField();
							TextField idiomaTextFCads = new TextField();
							
							TextArea sinopseText = new TextArea();
							
							Label auxView = new Label("\n");
							Label auxView2 = new Label("\n");
							Label aux = new Label("\t\t");
							
							
							cadLivro.add(aux, 0, 0);
							cadLivro.add(tituloCads, 1, 0);
							cadLivro.add(precoCads, 1, 1);
							cadLivro.add(paginasCads, 1, 2);
							cadLivro.add(generoCads, 1, 3);
							cadLivro.add(idiomaCads, 1, 22);
							cadLivro.add(capaCads, 1, 40);
							cadLivro.add(pdfCads, 1, 39);
							cadLivro.add(outroGeneroCads, 1, 21);
							cadLivro.add(outroIdiomaCads, 1, 36);
							cadLivro.add(sinopseCads, 1, 37);
							
							
							cadLivro.add(tituloTextFCads, 2, 0);
							cadLivro.add(precoTextFCads, 2, 1);
							cadLivro.add(paginasTextFCads, 2, 2);
							/*
							cadLivro.add(capaTextFCads, 2, 40);
							cadLivro.add(pdfTextFCads, 2, 39);
							*/
							
							cadLivro.add(generoTextFCads, 2, 21);
							cadLivro.add(idiomaTextFCads, 2, 36);
							cadLivro.add(sinopseText, 2, 38);
							
							

							CheckBox romance = new CheckBox("romance");
							CheckBox ficcao = new CheckBox("ficção");
							CheckBox fantasia = new CheckBox("fantasia");
							CheckBox drama = new CheckBox("drama");
							CheckBox infantuJ = new CheckBox("infanto juvenil");
							CheckBox misterio = new CheckBox("mistério");
							CheckBox policial = new CheckBox("policial");
							CheckBox comedia = new CheckBox("comédia");
							CheckBox autoAjuda = new CheckBox("auto ajuda");
							CheckBox religioso = new CheckBox("religioso");
							CheckBox poesia = new CheckBox("poesia");
							CheckBox artes = new CheckBox("artes");
							CheckBox educacao = new CheckBox("educação");
							CheckBox suspense = new CheckBox("suspense");
							CheckBox negocios = new CheckBox("negócios");
							CheckBox psicologia = new CheckBox("psicologia");
							CheckBox biografia = new CheckBox("biografia");
							
							
							CheckBox portugues = new CheckBox("Português");
							CheckBox ingles = new CheckBox("English");
							CheckBox frances = new CheckBox("Le français");
							CheckBox mandarim = new CheckBox("柑");
							CheckBox hindi = new CheckBox("हिन्दी");
							CheckBox alemao = new CheckBox("Germanisch");
							CheckBox latim = new CheckBox("Latine");
							CheckBox russo = new CheckBox("Pусский");
							CheckBox espanhol = new CheckBox("español");
							CheckBox tcheco = new CheckBox("Česky");
							CheckBox japones = new CheckBox("日本人の");
							CheckBox xhosa = new CheckBox("IsiXhosa");
							CheckBox zulu = new CheckBox("Zulu");
							
							
							Button salvarCads = new Button("Cadastrar livro");
							Button buscarCapa = new Button("Buscar");
							Button buscarPdf = new Button("Buscar");
							
							Hyperlink novoAutor = new Hyperlink("Incluir parceria");
							
							
							
							
							cadLivro.add(romance, 1, 4);
							cadLivro.add(ficcao, 1, 5);
							cadLivro.add(fantasia, 1, 6);
							cadLivro.add(drama, 1, 7);
							cadLivro.add(infantuJ, 1, 8);
							cadLivro.add(misterio, 1, 9);
							cadLivro.add(policial, 1, 10);
							cadLivro.add(comedia, 1, 11);
							cadLivro.add(autoAjuda, 1, 12);
							cadLivro.add(religioso, 1, 13);
							cadLivro.add(poesia, 1, 14);
							cadLivro.add(artes, 1, 15);
							cadLivro.add(educacao, 1, 16);
							cadLivro.add(suspense, 1, 17);
							cadLivro.add(negocios, 1, 18);
							cadLivro.add(psicologia, 1, 19);
							cadLivro.add(biografia, 1, 20);
							
							cadLivro.add(portugues, 1, 23);
							cadLivro.add(ingles, 1, 24);
							cadLivro.add(frances, 1, 25);
							cadLivro.add(mandarim, 1, 26);
							cadLivro.add(hindi, 1, 27);
							cadLivro.add(alemao, 1, 28);
							cadLivro.add(latim, 1, 29);
							cadLivro.add(russo, 1, 30);
							cadLivro.add(espanhol, 1, 31);
							cadLivro.add(japones, 1, 32);
							cadLivro.add(tcheco, 1, 33);
							cadLivro.add(xhosa, 1, 34);
							cadLivro.add(zulu, 1, 35);
							
							cadLivro.add(buscarCapa, 2, 40);
							cadLivro.add(buscarPdf, 2, 39);
							
							cadLivro.add(novoAutor, 1, 41);
							
							
							
							
							
							cadLivro.add(salvarCads, 2, 49);
							
							cadLivro.add(auxView, 2, 50);
							
							scCadastrar.setContent(cadLivro);
							
							
							
							//Capturando Gêneros escolhidos
							//______________________________________________________________________________________________________________________
							livro.setGenero("");
							romance.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Romance");
						        }
						    });
							
							ficcao.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Ficção");
						        }
						    });
							
							fantasia.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Fantasia");
						        }
						    });
							
							drama.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Drama");
						        }
						    });
							
							infantuJ.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Literatura infato-juvenil");
						        }
						    });
							
							misterio.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Mistério");
						        }
						    });
							
							policial.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Policial");
						        }
						    });
							
							comedia.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Comédia");
						        }
						    });
							
							autoAjuda.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Auto ajuda");
						        }
						    });
							
							religioso.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Religioso");
						        }
						    });
							
							poesia.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Poesia");
						        }
						    });
							
							artes.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Artes");
						        }
						    });
							
							educacao.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Educação");
						        }
						    });
							
							suspense.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Suspense");
						        }
						    });
							
							negocios.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Negócios");
						        }
						    });
							
							psicologia.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Psicologia");
						        }
						    });
							
							biografia.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addGenero("Biografia");
						        }
						    });
							
							
							
							
							//Capturando os idiomas escolhidos
							//________________________________________________________________________________________________________________
							livro.setIdioma("");
							portugues.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addIdioma("Português");
						        }
						    });
							
							ingles.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addIdioma("English");
						        }
						    });
							
							frances.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addIdioma("Le français");
						        }
						    });
							
							mandarim.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addIdioma("柑");
						        }
						    });
							
							hindi.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addIdioma("हिन्दी ");
						        }
						    });
							
							alemao.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addIdioma("Germanisch");
						        }
						    });
							
							latim.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addIdioma("Latine");
						        }
						    });
							
							russo.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addIdioma("Pусский");
						        }
						    });
							
							espanhol.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addIdioma("Espanõl");
						        }
						    });
							
							tcheco.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addIdioma("Česky");
						        }
						    });
							
							japones.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addIdioma("日本人の");
						        }
						    });
							
							xhosa.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addIdioma("IsiXhosa");
						        }
						    });
							
							zulu.selectedProperty().addListener(new ChangeListener<Boolean>() {
						        public void changed(ObservableValue<? extends Boolean> ov,
						            Boolean old_val, Boolean new_val) {
						        	livro.addIdioma("Zulu");
						        }
						    });
							livro.addIdioma(idiomaTextFCads.getText());
							
							
							
							
							//Escolhendo um arquivo pdf nos arquivos
							buscarPdf.setOnAction(r -> {
								
								try
								{
									FileChooser pdfChooser = new FileChooser();
									File pdfFile = pdfChooser.showOpenDialog (palcoAutor);
									livro.setPdf(pdfFile);
								}
								catch(Exception w)
								{
								}
							});
							
							
							/*
							//Escolhendo um arquivo pdf com o caminho
							try
							{
								livro.setPdf(new File(pdfCads.getText()));
							}
							catch(Exception w)
							{
							}
							
							*/
							
							
							
							//Escolhendo um arquivo de imagem para a capa nos arquivos
							buscarCapa.setOnAction(r -> {
								FileChooser capaChooser = new FileChooser();
								File capaFile = capaChooser.showOpenDialog (palcoAutor);
								try 
								{
									ImageView imageCapa = new ImageView(new Image(new FileInputStream(capaFile), 150, 200, false, false));
									livro.setCapa(imageCapa);
								} 
								catch (FileNotFoundException e1) 
								{
								}
								
							});
							
							/*
							//Escolhendo um arquivo de imagem para a capa com caminho
							//Escolhendo um arquivo pdf com o caminho
							try
							{
								livro.setCapa(new ImageView(new Image(new FileInputStream(capaTextFCads.getText()), 150, 200, false, false)));
							}
							catch(Exception w)
							{
							}
							*/
							

							//________________________________________________________________________
							//Tratar o caso de inclusão de novo autor
							//__________________________________________________________________________
							
							
							
							ArrayList <Autor> autoresArray = new ArrayList();
							
							autoresArray.add(ebookStore.buscarAutorLoginSenha(textLoginInicio.getText(), textSenhaInicio.getText()));
							
							novoAutor.setOnAction(r -> {
								
								Text cpfAutorParceiro = new Text("CPF/CNPJ do colaborador");
								TextField cpfParceiroTF = new TextField();
								Button cpfEntrar = new Button("Entrar");
								
								cadLivro.add(cpfAutorParceiro, 1, 42);
								cadLivro.add(cpfParceiroTF, 2, 42);
								cadLivro.add(cpfEntrar, 3, 42);
								cadLivro.add(auxView2, 0, 43);
								
								cpfEntrar.setOnAction(y -> {
									
									try
									{
										Autor parceiro = ebookStore.buscarAutor(cpfParceiroTF.getText());
										if(parceiro != null)
										{
											autoresArray.add(parceiro);
											ebookStore.buscarAutor(parceiro.getNome()).cadastrarLivro(livro);
										}
									}
									catch(Exception w)
									{
									}
								});
								
								
							});
							livro.setAutores(autoresArray);
							
							
							
							
							
							//___________________________________________________________________________________________________________________
							//Salvando Livro.......................................
							//___________________________________________________________________________________________________________________
							
							
							
							salvarCads.setOnAction(r -> {

								try
								{
									ebookStore.cadastrarLivro(livro);
									int i, y = 0;
									for(i = 0; i < autoresArray.size(); i++)
									{
										if(ebookStore.buscarAutor(autoresArray.get(i).getCpf()) != null)
										{

											//Preenchendo informações do livro
											//________________________________________________________________________________________________
											livro.setTitulo(tituloTextFCads.getText());
											livro.setPreco(precoTextFCads.getText());
											livro.setSinopse(sinopseText.getText());
											livro.setPaginas(paginasTextFCads.getText());
											livro.addGenero(generoTextFCads.getText());
											livro.addIdioma(idiomaTextFCads.getText());
											//..................................................................................................
											
											y = 1;
											
											autoresArray.get(i).cadastrarLivro(livro);
										}
									}
									
									if(y != 0)
									{
										Alert alert = new Alert (AlertType.INFORMATION);
										alert.setHeaderText("Parabéns!");
										alert.setContentText("Seu livro foi cadastrado com sucesso!");
										alert.showAndWait();
									}
								}
								
								catch(Exception x)
								{
								}
								
							});
							
							
							//meusLivros.getOnSelectionChanged();
							
							
							//_________________________________________________________________________________________
							// Segunda aba com Atualização
							//_____________________________________________________________________________________________
							//...........................................................................................
							
							
							
							 meusLivros.setOnSelectionChanged(event -> {
							        if (meusLivros.isSelected()) 
							        {
							        	BorderPane myBooks = new BorderPane();
										VBox mLivros = new VBox();
										int i;
										
										int verif = 0;
										try
										{
											verif = autor.meusLivros().size();
										}
										catch(Exception m)
										{
										}
										
										
										for(i = 0; i < verif; i++)
										{
											if(verif != 0)
											{
												ImageView capaMeuLivro = autor.meusLivros().get(i).buscarCapa();
												String auxVendas = "\nNúmero de exemplares vendidos: " 
														+ autor.meusLivros().get(i).getExemplaresV();
												Text conteudoMeuLivro = new Text(autor.meusLivros().get(i).livro() 
														+ auxVendas);
												mLivros.getChildren().add(capaMeuLivro);
												mLivros.getChildren().add(conteudoMeuLivro);
												verif = 1;
											}
										}
										
										Label semLivros = new Label();
										if(verif == 0)
										{
											semLivros.setText("\n\tVocê não possui livros");
											semLivros.setTextFill(Color.rgb(210, 39, 30));
										}
										else
										{
											semLivros.setText("");
										}
										mLivros.getChildren().add(semLivros);
										
										
										myBooks.setCenter(mLivros);
										
										scLivros.setContent(myBooks);
							        }
							    });
							 
							 
							 
							 
							 //__________________________________________________________________________________
							 // Autor Perfil com Atualização
							 //______________________________________________________________________________________
							 //......................................................................................
							 
							 
							 
							 
							 
							 autorPerfil.setOnSelectionChanged(event -> {
							        if (autorPerfil.isSelected()) 
							        {
							        	BorderPane perfilAutor = new BorderPane();
										VBox perfilBox = new VBox();
										
										Text infPerfil = new Text(autor.toString());
										
										Hyperlink fazerAlteracoes = new Hyperlink("Atualizar dados");
										Hyperlink mudarLogin = new Hyperlink("Mudar login");
										Hyperlink mudarEmail = new Hyperlink("Mudar e-mail");
										Hyperlink mudarSenha = new Hyperlink("Mudar senha");
										
										
										TextField novoLogin =  new TextField();
										novoLogin.setPromptText("Digite seu novo login");
										TextField novoEmail =  new TextField();
										novoEmail.setPromptText("Digite seu novo e-mail");
										PasswordField novaSenha =  new PasswordField();
										novaSenha.setPromptText("Digite sua nova senha");
										
										Button bLogin = new Button("Salvar");
										Button bEmail = new Button("Salvar");
										Button bSenha = new Button("Salvar");
										
										
										perfilBox.getChildren().add(infPerfil);
										perfilBox.getChildren().add(fazerAlteracoes);
										
										
										
										fazerAlteracoes.setOnAction(o -> {
											
											perfilBox.getChildren().add(mudarLogin);
											perfilBox.getChildren().add(mudarEmail);
											perfilBox.getChildren().add(mudarSenha);
											
											mudarLogin.setOnAction(w -> {
												
												perfilBox.getChildren().add(novoLogin);
												perfilBox.getChildren().add(bLogin);
												
												bLogin.setOnAction(p -> {
													ebookStore.buscarAutorNome(autor.getNome()).setLogin(novoLogin.getText());
												});
												
											});
											
											
											mudarEmail.setOnAction(c -> {
												
												perfilBox.getChildren().add(novoEmail);
												perfilBox.getChildren().add(bEmail);
												
												bEmail.setOnAction(p -> {
													ebookStore.buscarAutorNome(autor.getNome()).setEmail(novoEmail.getText());
												});
												
											});
										
											
											mudarSenha.setOnAction(z -> {
												
												perfilBox.getChildren().add(novaSenha);
												perfilBox.getChildren().add(bSenha);
												
												bSenha.setOnAction(p -> {
													ebookStore.buscarAutorNome(autor.getNome()).setSenha(novaSenha.getText());
												});
												
											});
											
										});
										
										
										
										
										perfilAutor.setCenter(perfilBox);
										scPerfil.setContent(perfilAutor);
										
										
							        }
							    });
							 
							 
							 

							 /* Procurar codigo
							 autorPerfil.setOnSelectionChanged(event -> {
							        if (autorPerfil.isSelected()) 
							        {
							            paneTabs.getTabs().add(autorPerfil);
							        }
							    });
							*/
							
							paneTabs.getTabs().add(cadastrarLivro);
							paneTabs.getTabs().add(meusLivros);
							paneTabs.getTabs().add(autorPerfil);
							palcoAutor.setTitle("Área do Autor");
							palcoAutor.setScene(cenaAutor);
							palcoAutor.show();
						}
					}
					catch (IndexOutOfBoundsException g) 
					{
						if(autorLogin == null)
						{
							avisoSenha.setText("Senha incorreta");
							avisoSenha.setTextFill(Color.rgb(210, 39, 30));
						}
					}
				}
				//_________________________________________________________________________________________________
				//Login Leitor
				//_________________________________________________________________________________________________
				if(rLeitor.isSelected() == true)
				{
					Leitor leitorLogin = new Leitor();
					try
					{
						String loginLeitor = textLoginInicio.getText();
						String senhaLeitor = textSenhaInicio.getText();
						leitorLogin = ebookStore.buscarLeitorLoginSenha(loginLeitor, senhaLeitor);
						
						if(leitorLogin != null)
						{
							Leitor leitor = leitorLogin;
							int numMeusLivros = 0;
							try
							{
								numMeusLivros = leitor.numeroMeusLivros();
							}
							catch(Exception a)
							{
							}
							 
							
							
							
							
							//>Area de interação com o usuário Leitor
							avisoSenha.setText("Senha correta");
							avisoSenha.setTextFill(Color.rgb(21, 117, 84));
							
							Stage palcoLeitor = new Stage();
							
							TabPane paneTabs = new TabPane();
							Scene cenaLeitor = new Scene(paneTabs);
							paneTabs.setPrefSize(1200, 650);
							
							
							Tab comprarLeitor = new Tab("Comprar");
							Tab livrosLeitor = new Tab("Meus livros");
							Tab perfilLeitor = new Tab("Meu Perfil");
							
							BorderPane paneLeitorCompras = new BorderPane();
							BorderPane paneLeitorLivros = new BorderPane();
							BorderPane paneLeitorPerfil = new BorderPane();
							
							
							comprarLeitor.setContent(paneLeitorCompras);
							livrosLeitor.setContent(paneLeitorLivros);
							perfilLeitor.setContent(paneLeitorPerfil);
							
							comprarLeitor.setClosable(false);
							livrosLeitor.setClosable(false);
							perfilLeitor.setClosable(false);
							
							
							
							ScrollPane scComprar = new ScrollPane();
							scComprar.setHbarPolicy (ScrollBarPolicy.NEVER);
							scComprar.setVbarPolicy (ScrollBarPolicy.ALWAYS);
							ScrollPane scLivros = new ScrollPane();
							scLivros.setHbarPolicy (ScrollBarPolicy.NEVER);
							scLivros.setVbarPolicy (ScrollBarPolicy.ALWAYS);
							ScrollPane scPerfil = new ScrollPane();
							scPerfil.setHbarPolicy (ScrollBarPolicy.NEVER);
							scPerfil.setVbarPolicy (ScrollBarPolicy.ALWAYS);
							
							
							paneLeitorCompras.setCenter(scComprar);
							paneLeitorLivros.setCenter(scLivros);
							paneLeitorPerfil.setCenter(scPerfil);
							
							
							//Lista de livros disponíveis a compra
							VBox listaLivrosCompras = new VBox();
							int i; int contadorAux = 0;
							Label labelAux = new Label("");
							int x = ebookStore.numeroListaLivro();
							if(x != 0)
							{
								TextField comprarLivro =  new TextField();
								comprarLivro.setPromptText("Digite o título do livro que deseja comprar");
								Button bComprar = new Button("Comprar");
								Label barraN = new Label("\n");
								
								listaLivrosCompras.getChildren().addAll(comprarLivro, bComprar, barraN);
								
								
								
								//_______________________________________________________________________________________
								//--------------------------------------------------------------------------------
								//Compras...................................................................
								//---------------------------------------------------------------------------------
								//________________________________________________________________________________________
								
								
								bComprar.setOnAction(k -> {
									try
									{
										String a = comprarLivro.getText();
										
										if(ebookStore.buscarLivro(a) != null)
										{
											
											ebookStore.buscarLeitor(leitor.getCpf()).comprarLivro(ebookStore.buscarLivro(a));
											
											Alert alert = new Alert (AlertType.INFORMATION);
											alert.setHeaderText("Parabéns!");
											alert.setContentText("Livro " +a+ " comprado com sucesso!");
											alert.showAndWait();
										}
										
									}
									catch(Exception s)
									{
										Alert alert = new Alert (AlertType.INFORMATION);
										alert.setHeaderText("Erro!");
										alert.setContentText("Esse livro não existe.");
										alert.showAndWait();
									}
									
								});
								
								for(i = 0; i < x; i++)
								{
									ImageView image = ebookStore.livroIndice(i).buscarCapa();
									listaLivrosCompras.getChildren().add(image);
									listaLivrosCompras.getChildren().add(new Text(ebookStore.livroIndice(i).getTitulo()));
									listaLivrosCompras.getChildren().add(new Text(ebookStore.livroIndice(i).livro()));
									
									contadorAux++;
								}
							}
							
							scComprar.setContent(listaLivrosCompras);
							
							
							//______________________________________________________________________________________
							//______________________________________________________________________________________
							//Segunda aba Livros comprados pelo leitor
							

							
							
							
							
							
							if (contadorAux == 0)
							{
								livrosLeitor.setOnSelectionChanged(event -> {
							        if (livrosLeitor.isSelected()) 
							        {
							        	labelAux.setText("\n\tNão há livros disponíveis.");
										labelAux.setTextFill(Color.rgb(210, 39, 30));
										listaLivrosCompras.getChildren().add(labelAux);
							        }
							    });
								
							}
							
							
							BorderPane meusLivros = new BorderPane();
							VBox mLivros = new VBox();
							int verif = 0;
							Button bLer = new Button("Ler");
							Text leituraLeitor = new Text("\n\tDigite o título do livro que deseja ler");
							TextField tfLeitura = new TextField();
							
							
							if(contadorAux != 0)
							{
								livrosLeitor.setOnSelectionChanged(event -> {
							        if (livrosLeitor.isSelected()) 
							        {
							        	labelAux.setText("");
							        	
							        	mLivros.getChildren().add(leituraLeitor);
										mLivros.getChildren().add(tfLeitura);
										mLivros.getChildren().add(bLer);
							        }
							    });
							}
							
							
							
							/*
							mLivros.getChildren().add(leituraLeitor);
							mLivros.getChildren().add(tfLeitura);
							mLivros.getChildren().add(bLer);
							*/
							
							
							//Leitura...................................................................................
							
							
							bLer.setOnAction(y -> {
								Livro livroAux = null;
								try
								{
									livroAux = leitor.buscarLivro(tfLeitura.getText());
								}
								catch(Exception s)
								{
								}
								
								if(livroAux != null)
								{
									try
									{
										PDFNotesBean notes = new PDFNotesBean();
										notes.loadPDF(livroAux.getPdf());
										
										notes.getAnnotToolbar().setVisible(false);
										notes.getEditToolbar().getjbOpen().setVisible(false);
										notes.getToolbar().getjbPrint().setEnabled(false);
										notes.getEditToolbar().getjbSave().setVisible(false);
										
										JFrame jframe = new JFrame();
										jframe.setContentPane(notes);
										jframe.setVisible(true);
										jframe.setSize(1200, 650);
										
									}
									catch(Exception m)
									{
									}
									
								}
								if(livroAux == null)
								{
									Alert alert = new Alert (AlertType.INFORMATION);
									alert.setHeaderText("Erro!");
									alert.setContentText("Você não comprou este livro.");
									alert.showAndWait();
								}
								
								
								
								
							});
								
							
							try
							{
								verif = leitor.meusLivros().size();
							}
							catch(Exception m)
							{
							}
							
							for(i = 0; i < numMeusLivros; i++)
							{
								if(verif != 0)
								{
									ImageView capaMeuLivro = leitor.meusLivros().get(i).buscarCapa();
									Text conteudoMeuLivro = new Text(leitor.meusLivros().get(i).livro());
									mLivros.getChildren().add(capaMeuLivro);
									mLivros.getChildren().add(conteudoMeuLivro);
									verif = 1;
								}
							}
							
							Label semLivros = new Label();
							if(verif == 0)
							{
								semLivros.setText("\n\tVocê não possui livros");
								semLivros.setTextFill(Color.rgb(210, 39, 30));
							}
							else
							{
								semLivros.setText("");
							}
							mLivros.getChildren().add(semLivros);
							
							
							meusLivros.setCenter(mLivros);
							scLivros.setContent(meusLivros);
							
							

							//___________________________________________________________________________
							//...........................................................................
							// Meu perfil Leitor
							//___________________________________________________________________________
							
							
							perfilLeitor.setOnSelectionChanged(event -> {
						        if (perfilLeitor.isSelected()) 
						        {
						        	BorderPane panePerfilLeitor = new BorderPane();
									VBox perfilBox = new VBox();
									
									Text infPerfil = new Text(leitor.toString());
									
									Hyperlink fazerAlteracoes = new Hyperlink("Atualizar dados");
									Hyperlink mudarLogin = new Hyperlink("Mudar login");
									Hyperlink mudarEmail = new Hyperlink("Mudar e-mail");
									Hyperlink mudarSenha = new Hyperlink("Mudar senha");
									
									
									TextField novoLogin =  new TextField();
									novoLogin.setPromptText("Digite seu novo login");
									TextField novoEmail =  new TextField();
									novoEmail.setPromptText("Digite seu novo e-mail");
									PasswordField novaSenha =  new PasswordField();
									novaSenha.setPromptText("Digite sua nova senha");
									
									Button bLogin = new Button("Salvar");
									Button bEmail = new Button("Salvar");
									Button bSenha = new Button("Salvar");
									
									
									perfilBox.getChildren().add(infPerfil);
									perfilBox.getChildren().add(fazerAlteracoes);
									
									
									try
									{
										
										
										fazerAlteracoes.setOnAction(o -> {
											
											perfilBox.getChildren().add(mudarLogin);
											perfilBox.getChildren().add(mudarEmail);
											perfilBox.getChildren().add(mudarSenha);
											
										
											mudarLogin.setOnAction(w -> {
												
												perfilBox.getChildren().add(novoLogin);
												perfilBox.getChildren().add(bLogin);
												
												bLogin.setOnAction(p -> {
													ebookStore.buscarLeitor(leitor.getCpf()).setLogin(novoLogin.getText());
												
												});
											});
										
										
										
											mudarEmail.setOnAction(c -> {
												
												perfilBox.getChildren().add(novoEmail);
												perfilBox.getChildren().add(bEmail);
												
												bEmail.setOnAction(p -> {
													ebookStore.buscarLeitor(leitor.getCpf()).setEmail(novoEmail.getText());
												});
												
											});
										
										
										
										
											mudarSenha.setOnAction(z -> {
												
												perfilBox.getChildren().add(novaSenha);
												perfilBox.getChildren().add(bSenha);
												
												bSenha.setOnAction(p -> {
													ebookStore.buscarLeitor(leitor.getCpf()).setSenha(novaSenha.getText());
												});
												
											});
										
											
										});
										
										
									}
									catch(Exception x1)
									{
									}
									
									
									
									
									
									panePerfilLeitor.setCenter(perfilBox);
									scPerfil.setContent(panePerfilLeitor);
									
									
					
						        	
						        }
						    });
							
							
											
							
							
							
							paneTabs.getTabs().add(comprarLeitor);
							paneTabs.getTabs().add(livrosLeitor);
							paneTabs.getTabs().add(perfilLeitor);
							
							
							palcoLeitor.setTitle("Área do leitor");
							palcoLeitor.setScene(cenaLeitor);
							palcoLeitor.show();
						}
					}
					catch (IndexOutOfBoundsException  g) 
					{
						if(leitorLogin == null)
						{
							avisoSenha.setText("Senha incorreta");
							avisoSenha.setTextFill(Color.rgb(210, 39, 30));
						}
					}
				}	
				
				
			});
			
			//Novo cadastro
			Hyperlink cadastroInicio = new Hyperlink("Cadastrar");
			
			cadastroInicio.setOnAction(w -> {
				
				Stage cadastroCliente = new Stage();
				BorderPane paneCadastroInicio = new BorderPane();
				paneCadastroInicio.setPrefSize(250, 350);
				Scene cenaCadastroInicio = new Scene(paneCadastroInicio);
				cadastroCliente.setTitle("Cadastro usuário");
				
				GridPane gridCadastroUsu = new GridPane();
				
				Text nomeCads = new Text("nome");
				Text emailCads = new Text("e-mail");
				Text cpfCads = new Text("CPF/CNPJ");
				Text loginCads = new Text("login");
				Text senhaCads = new Text("senha");
				
				TextField nomeTextFCads = new TextField();
				TextField emailTextFCads = new TextField();
				TextField cpfTextFCads = new TextField();
				TextField loginTextFCads = new TextField();
				PasswordField senhaTextFCads = new PasswordField();
				
				ToggleGroup groupCads = new ToggleGroup();
				RadioButton leitorCads = new RadioButton("leitor");
				leitorCads.setToggleGroup(groupCads);
				RadioButton autorCads = new RadioButton("autor");
				autorCads.setToggleGroup(groupCads);
				
				Label labelCads = new Label("");
				Button enterCads = new Button("Enter");
				
				gridCadastroUsu.add(nomeCads, 0, 1);
				gridCadastroUsu.add(emailCads, 0, 2);
				gridCadastroUsu.add(cpfCads, 0, 3);
				gridCadastroUsu.add(loginCads, 0, 4);
				gridCadastroUsu.add(senhaCads, 0, 5);
				gridCadastroUsu.add(nomeTextFCads, 1, 1);
				gridCadastroUsu.add(emailTextFCads, 1, 2);
				gridCadastroUsu.add(cpfTextFCads, 1, 3);
				gridCadastroUsu.add(loginTextFCads, 1, 4);
				gridCadastroUsu.add(senhaTextFCads, 1, 5);
				gridCadastroUsu.add(leitorCads, 0, 7);
				gridCadastroUsu.add(autorCads, 0, 8);
				gridCadastroUsu.add(labelCads, 0, 9, 4, 1);
				gridCadastroUsu.add(enterCads, 0, 10);
				
				paneCadastroInicio.setCenter(gridCadastroUsu);
				
				cadastroCliente.setScene(cenaCadastroInicio);
				cadastroCliente.show();
				
				enterCads.setOnAction(g -> {
					
					//Cadastro Leitor
					if(leitorCads.isSelected() == true)
					{
						try
						{
							ebookStore.cadastrarLeitor(new Leitor(nomeTextFCads.getText(), emailTextFCads.getText(),
									loginTextFCads.getText(),senhaTextFCads.getText(), cpfTextFCads.getText()));
							
							if(ebookStore.buscarLeitor(cpfTextFCads.getText()) != null)
							{
								
								labelCads.setText("Cadastro realizado com sucesso!");
								labelCads.setTextFill(Color.rgb(21, 117, 84));
							}
						
						}
						catch(Exception t)
						{
							labelCads.setText("Ocorreu um erro. Tente novamente.");
							labelCads.setTextFill(Color.rgb(210, 39, 30));
						}
						
					}
					
					//Cadastro Autor
					if(autorCads.isSelected() == true)
					{
						try
						{
								
							ebookStore.cadastrarAutor(new Autor(nomeTextFCads.getText(), emailTextFCads.getText(),
									loginTextFCads.getText(),senhaTextFCads.getText(), cpfTextFCads.getText()));
							
							Autor autorVerifica = new Autor(ebookStore.buscarAutor(cpfTextFCads.getText()));
							
							if(autorVerifica != null)
							{
								labelCads.setText("Cadastro realizado com sucesso!");
								labelCads.setTextFill(Color.rgb(21, 117, 84));
							}
						
						}
						catch(Exception t)
						{
							labelCads.setText("Ocorreu algum erro. Tente novamente.");
							labelCads.setTextFill(Color.rgb(210, 39, 30));
						}
					}
				});
				
				
			});
			
			
			gridPane.add(loginInicio,0,3);
			gridPane.add(senhaInicio,0,4);
			gridPane.add(textLoginInicio,1,3);
			gridPane.add(textSenhaInicio,1,4);
			gridPane.add(rLeitor,0,5);
			gridPane.add(rAutor,0,6);
			gridPane.add(enterLoginInicio,0,7);
			gridPane.add(avisoSenha,0,8);
			gridPane.add(cadastroInicio,0,10);
			
			Text alinhar = new Text();
			alinhar.setFont(Font.font("Gabriola", FontWeight.BOLD, FontPosture.ITALIC, 30));
			paneLogin.setTop(alinhar);
			paneLogin.setCenter(gridPane);
			
			palcoLogin.setTitle("Login");
			palcoLogin.setScene(cenaLogin);
			palcoLogin.setX(palcoInicio.getMinWidth() + palcoInicio.getWidth() - 200);
			palcoLogin.setY(palcoInicio.getMinWidth() + palcoInicio.getHeight() - 550);
			palcoLogin.show();
		});
		
		
		paneInicio.setRight(entrarInicio);
		
		//Garante que todas as guias sejam fechadas quando a tela inicial for fechada
		palcoInicio.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		        Platform.exit();
		        System.exit(0);
		    }
		});
		
		palcoInicio.setScene(cenaInicio);
		palcoInicio.setTitle("EbookStore");
		palcoInicio.show();
		
	}
	
	
}
