package br.com.alura.screensound.main;



import br.com.alura.screensound.model.Artista;
import br.com.alura.screensound.model.Musica;
import br.com.alura.screensound.repository.ArtistaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

   Scanner scanner = new Scanner(System.in);

   private ArtistaRepository repository;

   public Main(ArtistaRepository repository) {
       this.repository = repository;
   }

    public void exibirMenu(){
        var opcao = 0;

        while (opcao != 9) {
            System.out.println("""
                    1- Cadastrar artistas
                    
                    2- Cadastrar músicas
                    
                    3- Listar músicas
                    
                    4- Buscar músicas por artistas
                    
                    5- Pesquisar dados sobre um artista
                    
                    9- Sair
                    """);

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1:
                    CadastrarArtista();
                    break;
                case 2:
                    CadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    BuscarMusicasPorArtistas();
                    break;
                case 5:
                    pesquisarDadosArtista();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("opção inválida!");
                    break;
            }
        }

    }

    private void CadastrarArtista() {
       List<Artista> artistasAdicionar = new ArrayList<>();

       var continuar = "S";

       while (continuar.equalsIgnoreCase("s")){
           System.out.println("Informe o nome desse artista: ");
           var nome = scanner.nextLine();

           System.out.println("Informe o tipo desse artista: (solo, dupla, banda)");
           var tipo = scanner.nextLine();

           artistasAdicionar.add(new Artista(nome, tipo));

           System.out.println("Cadastrar outro artista? (S/N)");
           continuar = scanner.nextLine();
       }

       artistasAdicionar.forEach(a -> repository.save(a));

    }

    private void CadastrarMusicas() {
        System.out.println("Informe o nome desse artista: ");
        var artistaNome = scanner.nextLine();

        Optional<Artista> artistaBuscado = repository.findByNomeContainingIgnoreCase(artistaNome);

        if (artistaBuscado.isPresent()) {

            System.out.println("Informe o titulo da música: ");
            var titulo = scanner.nextLine();

            Musica musica = new Musica(titulo);
            musica.setArtista(artistaBuscado.get());
            artistaBuscado.get().getMusicas().add(musica);

            repository.save(artistaBuscado.get());

        } else {
            System.out.println("Artista não encontrado!");
        }
    }

    private void listarMusicas() {
       List<Artista> artistas = repository.findAll();
       artistas.forEach(a -> a.getMusicas().forEach(System.out::println));

    }

    private void BuscarMusicasPorArtistas() {
       System.out.println("Informe o nome desse artista: ");
       var artistaNome = scanner.nextLine();

       Optional<Artista> artistaBuscado = repository.findByNomeContainingIgnoreCase(artistaNome);

       if (artistaBuscado.isPresent()) {
           List<Musica> musicasBuscadas = artistaBuscado.get().getMusicas();

           musicasBuscadas.forEach(System.out::println);

       } else {
           System.out.println("Artista não encontrado!");
       }
    }

    private void pesquisarDadosArtista() {

    }
}
