package entity.types;

public enum TipoClienteEnum {
	
	PESSOA_FISICA("Pessoa Física"),
	PESSOA_JURIDICA("Pessoa Jurídica");
	
	private String descricao;
	
	private TipoClienteEnum(final String descricao) {
		this.descricao = descricao;
	}
	
	public static TipoClienteEnum getTipoClienteEnumByDescricao(final String descricao) {
		for (final TipoClienteEnum tipoClienteEnum : values()) {
			if(tipoClienteEnum.descricao.equals(descricao)) {
				return tipoClienteEnum;
			}
		}
		return null;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
