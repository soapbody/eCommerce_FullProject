package com.Mateus_Ulrich.eCommerce_FullProject;

import com.Mateus_Ulrich.eCommerce_FullProject.util.ValidaCNPJ;
import com.Mateus_Ulrich.eCommerce_FullProject.util.ValidaCPF;

public class TesteCpfCnjpj {
    public static void main(String[] args) {
        boolean isCnpj = ValidaCNPJ.isCNPJ("02.708.052/0001-42");
        System.out.println("Cnpj valido: " + isCnpj);

        boolean isCpf = ValidaCPF.isCPF("092.819.829-43");
        System.out.println("Cpf valido: " + isCpf);
    }
}
