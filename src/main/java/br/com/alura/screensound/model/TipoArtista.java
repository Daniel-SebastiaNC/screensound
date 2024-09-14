package br.com.alura.screensound.model;

public enum TipoArtista {
    SOLO("solo"),
    DUPLA("dupla"),
    BANDA("banda");

    private String tipoArtista;

    TipoArtista(String tipoArtista) {
            this.tipoArtista = tipoArtista;
    }

    public static TipoArtista fromString(String text) {
        for (TipoArtista tipoArtista : TipoArtista.values()) {
            if (tipoArtista.tipoArtista.equalsIgnoreCase(text)) {
                return tipoArtista;
            }
        }
        throw new IllegalArgumentException("Nenhuma TipoArtista encontrada para a string fornecida: " + text);
    }

}
