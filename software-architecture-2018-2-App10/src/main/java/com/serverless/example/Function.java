package com.serverless.example;

import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Data;

public class Function {

    List<Cidade> cidades;
    List<Estado> estados;

    @FunctionName ("criarCidade")
    public Cidade criar (
            @HttpTrigger (name = "restcriarcidade"
                    , methods = {HttpMethod.POST},
                    route = "cidade") Cidade c)
    {
        return c;
    }

    @FunctionName ("lerCidades")
    public List<Cidade> ler (
            @HttpTrigger (name = "restlercidades",
                    methods = {HttpMethod.GET},
                    route = "cidade") String x)
    {

        return Stream.of(
            new Cidade( 1, "São Paulo", new Estado(1, "São Paulo")),
                new Cidade(2, "João Pessoa", new Estado(2, "Paraíba")),
                new Cidade(3, "Curtiba", new Estado(3, "Paraná")),
                new Cidade(4, "Cuiabá", new Estado(4, "Mato Grosso"))
        ).collect(Collectors.toList());
    }

    @FunctionName ("alterarCidade")
    public Cidade alterar (
        @HttpTrigger (
            name = "restalterarcidade",
            methods = {HttpMethod.PUT},
            route = "cidade") Cidade c) {

        return c;
    }

    @FunctionName ("apagarcidade")
    public int apagar (
        @HttpTrigger (
            name = "restapagarcidade",
            methods = {HttpMethod.DELETE},
            route = "cidade/{id}"
        )
        @BindingName ("id") Long id) {

        return 200;
   }
}

@Data
class Estado {

    private long id;
    private String nome;

    public Estado(long id, String nome){
        this.id = id;
        this.nome = nome;
    }
}

@Data
class Cidade {
    private long id;
    private String nome;
    private Estado estado;

    public Cidade(long id, String nome, Estado estado){
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }
}