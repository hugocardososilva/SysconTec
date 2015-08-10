package model;

public enum OcorrenciaPrestadorEnum {
	CHEGAR_CEDO("CHEGOU MAIS CEDO"),SAIR_CEDO("SAIU MAIS CEDO"), CHEGAR_ATRASADO("CHEGOU MAIS TARDE"), SAIR_ATRASADO("SAIU MAIS TARDE"), 
	ESQUECER_PONTO_ENTRADA("ESQUECEU DE SE REGISTRAR NA ENTRADA"), ESQUECER_PONTO_SAIDA("ESQUECEU DE SE REGISTRAR NA SAÍDA");
	
	
	private String label;
	
	  private OcorrenciaPrestadorEnum(String label) {
	        this.label = label;
	    }
	  public String getLabel() {
	        return label;
	    }

}
