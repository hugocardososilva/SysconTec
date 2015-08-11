package model;

public enum OcorrenciaPrestadorEnum {
	CHEGAR_CEDO_1("CHEGOU MAIS CEDO"),SAIR_CEDO_2("SAIU MAIS CEDO"), CHEGAR_ATRASADO_3("CHEGOU MAIS TARDE"), SAIR_ATRASADO_4("SAIU MAIS TARDE"), 
	ESQUECER_PONTO_ENTRADA_5("ESQUECEU DE SE REGISTRAR NA ENTRADA"), ESQUECER_PONTO_SAIDA_6("ESQUECEU DE SE REGISTRAR NA SAÍDA");
	
	
	private String label;
	
	  private OcorrenciaPrestadorEnum(String label) {
	        this.label = label;
	    }
	  public String getLabel() {
	        return label;
	    }

}
