package mercadolibre.mutant.enums;

public enum MutantGeneEnum {
	MUTAN_GEN_A("AAAA"), 
	MUTAN_GEN_T("TTTT"), 
	MUTAN_GEN_C("CCCC"), 
	MUTAN_GEN_G("GGGG");
	
	private String value;

	private MutantGeneEnum(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
