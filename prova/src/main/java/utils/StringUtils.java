package utils;

public final class StringUtils {
	
	public static String removerMascaraNumero(String valor) {
		return valor.replaceAll("[^0-9]", "");
	}
	
	public static String aplicarMascaraCPF(String cpf){
         return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
    }
	
	public static String aplicarMascaraCNPJ(String cnpj){
        return cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12);
    }
	
	public static String aplicarMascaraTelefone(String telefone){
        return "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 5) + "-" + telefone.substring(5);
    }
}
