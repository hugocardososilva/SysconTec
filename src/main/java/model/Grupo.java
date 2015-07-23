package model;

public enum Grupo {
	ADMINISTRADOR("ADMINISTRADOR"), PORTEIRO("PORTEIRO"), ZELADOR("ZELADOR");
	
	private String label;

    private Grupo(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
