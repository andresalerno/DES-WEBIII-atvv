package com.comunicacao.api.mock;

import com.comunicacao.api.enumeracoes.TipoCompra;
import com.comunicacao.api.enumeracoes.TipoDocumento;
import com.comunicacao.api.enumeracoes.TipoEndereco;
import com.comunicacao.api.enumeracoes.TipoTelefone;
import com.comunicacao.api.modelos.Cliente;
import com.comunicacao.api.modelos.Documento;
import com.comunicacao.api.modelos.Endereco;
import com.comunicacao.api.modelos.Empresa;
import com.comunicacao.api.modelos.Funcionario;
import com.comunicacao.api.modelos.ItemDeVenda;
import com.comunicacao.api.modelos.ServicoMercadoria;
import com.comunicacao.api.modelos.Telefone;
import com.comunicacao.api.modelos.Veiculo;
import com.comunicacao.api.modelos.Venda;
import com.comunicacao.api.repositorio.ClienteRepositorio;
import com.comunicacao.api.repositorio.DocumentoRepositorio;
import com.comunicacao.api.repositorio.EnderecoRepositorio;
import com.comunicacao.api.repositorio.EmpresaRepositorio;
import com.comunicacao.api.repositorio.FuncionarioRepositorio;
import com.comunicacao.api.repositorio.ItemDeVendaRepositorio;
import com.comunicacao.api.repositorio.ServicoMercadoriaRepositorio;
import com.comunicacao.api.repositorio.TelefoneRepositorio;
import com.comunicacao.api.repositorio.VeiculoRepositorio;
import com.comunicacao.api.repositorio.VendaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class DataMock {

    @Autowired
    private EmpresaRepositorio empresaRepository;
    @Autowired
    private ClienteRepositorio clienteRepository;
    @Autowired
    private FuncionarioRepositorio funcionarioRepository;
    @Autowired
    private DocumentoRepositorio documentoRepository;
    @Autowired
    private TelefoneRepositorio telefoneRepository;
    @Autowired
    private EnderecoRepositorio enderecoRepository;
    @Autowired
    private VeiculoRepositorio veiculoRepository;
    @Autowired
    private ServicoMercadoriaRepositorio servicoMercadoriaRepository;
    @Autowired
    private VendaRepositorio vendaRepository;
    @Autowired
    private ItemDeVendaRepositorio itemDeVendaRepository;

    private Random random = new Random();

    @PostConstruct
    public void init() {
        gerarEmpresasMockadas(5);  // Gerar 5 empresas mockadas
        gerarClientesMockados(5);  // Gerar 20 clientes mockados
        gerarFuncionariosMockados(5);  // Gerar 10 funcionários mockados
        gerarEnderecosMockados(5);  // Gerar 20 endereços mockados
        gerarVeiculosMockados(5);  // Gerar 10 veículos mockados
        gerarServicosMockados(5);  // Gerar 5 serviços/mercadorias mockados
        gerarVendasMockadas(5);  // Gerar documentos mockados para clientes
        gerarDocumentosMockados();  // Gerar documentos mockados para clientes
        gerarTelefonesMockados();  // Gerar telefones mockados
    }

    // Gerar empresas mockadas
    public void gerarEmpresasMockadas(int quantidade) {
        for (int i = 1; i <= quantidade; i++) {
            Empresa empresa = new Empresa();
            empresa.setNome("Empresa " + i);
            empresa.setCnpj("00.000.000/0001-" + i);
            empresa.setEnderecoPrincipal("Rua Fictícia " + i);

            empresaRepository.save(empresa);
        }
    }

    // Gerar clientes mockados
    public void gerarClientesMockados(int quantidade) {
        for (int i = 1; i <= quantidade; i++) {
            Cliente cliente = new Cliente();
            cliente.setNome("Cliente " + i);
            cliente.setEmail("cliente" + i + "@mail.com");

            // Associar cliente a uma empresa aleatória
            Empresa empresa = empresaRepository.findById((long) (random.nextInt(5) + 1)).orElse(null);
            if (empresa != null) {
                cliente.setEmpresa(empresa);
            }

            clienteRepository.save(cliente);
        }
    }

    // Gerar documentos mockados para clientes
    public void gerarDocumentosMockados() {
        List<Cliente> clientes = clienteRepository.findAll();
        for (Cliente cliente : clientes) {
            Documento documento = new Documento();
            documento.setTipo(TipoDocumento.CPF);
            documento.setNumero("123.456.789-0");
            documento.setCliente(cliente);
            documentoRepository.save(documento);
        }
    }

    // Gerar telefones mockados
    public void gerarTelefonesMockados() {
        List<Cliente> clientes = clienteRepository.findAll();
        for (Cliente cliente : clientes) {
            Telefone telefone = new Telefone();
            telefone.setTipo(TipoTelefone.COMERCIAL);
            telefone.setNumero("99999-9999");
            telefone.setCliente(cliente);
            telefoneRepository.save(telefone);
        }
    }

    // Gerar endereços mockados
    public void gerarEnderecosMockados(int quantidade) {
        for (int i = 1; i <= quantidade; i++) {
            Endereco endereco = new Endereco();
            endereco.setTipo(TipoEndereco.COMERCIAL);
            endereco.setLogradouro("Rua Fictícia " + i);
            endereco.setNumero("123");
            endereco.setBairro("Bairro " + i);
            endereco.setCidade("Cidade " + i);
            endereco.setEstado("Estado " + i);
            endereco.setCep("12345-678");

            // Associar endereço a um cliente
            Cliente cliente = clienteRepository.findById((long) i).orElse(null);
            if (cliente != null) {
                endereco.setCliente(cliente);
            }

            enderecoRepository.save(endereco);
        }
    }

    // Gerar veículos mockados
    public void gerarVeiculosMockados(int quantidade) {
        for (int i = 1; i <= quantidade; i++) {
            Veiculo veiculo = new Veiculo();
            veiculo.setModelo("Modelo " + i);
            veiculo.setMarca("Marca " + i);
            veiculo.setPlaca("ABC-" + i);

            // Associar veículo a uma empresa
            Empresa empresa = empresaRepository.findById((long) (i % 5 + 1)).orElse(null);
            if (empresa != null) {
                veiculo.setEmpresa(empresa);
            }

            veiculoRepository.save(veiculo);
        }
    }

    // Gerar serviços/mercadorias mockados
    public void gerarServicosMockados(int quantidade) {
        for (int i = 1; i <= quantidade; i++) {
            ServicoMercadoria servico = new ServicoMercadoria();
            servico.setNome("Servico " + i);
            servico.setDescricao("Descricao do serviço " + i);
            servico.setValor(random.nextDouble() * 1000);
            servico.setDataCadastro(new Date());
            servico.setTipo(TipoCompra.SERVICO);

            // Associar serviço a uma empresa
            Empresa empresa = empresaRepository.findById((long) (i % 5 + 1)).orElse(null);
            if (empresa != null) {
                servico.setEmpresa(empresa);
            }

            servicoMercadoriaRepository.save(servico);
        }
    }
    
 // Gerar funcionários mockados
    public void gerarFuncionariosMockados(int quantidade) {
        for (int i = 1; i <= quantidade; i++) {
            Funcionario funcionario = new Funcionario();
            funcionario.setNome("Funcionario " + i);
            funcionario.setPerfil(i % 2 == 0 ? "Gerente" : "Vendedor");  // Exemplo de perfil, alternando entre "Gerente" e "Vendedor"

            // Associar funcionário a uma empresa aleatória
            Empresa empresa = empresaRepository.findById((long) (random.nextInt(5) + 1)).orElse(null);
            if (empresa != null) {
                funcionario.setEmpresa(empresa);
            }

            // Salvar o funcionário
            funcionarioRepository.save(funcionario);

            // Gerar e associar documentos mockados ao funcionário
            gerarDocumentosParaFuncionario(funcionario);

            // Gerar e associar telefones mockados ao funcionário
            gerarTelefonesParaFuncionario(funcionario);

            // Gerar e associar endereços mockados ao funcionário
            gerarEnderecosParaFuncionario(funcionario);
        }
    }

    // Gerar documentos para um funcionário
    private void gerarDocumentosParaFuncionario(Funcionario funcionario) {
        Documento documento = new Documento();
        documento.setTipo(TipoDocumento.CPF);
        documento.setNumero("123.456.789-0");
        documento.setFuncionario(funcionario);  // Associar ao funcionário
        documentoRepository.save(documento);
    }

    // Gerar telefones para um funcionário
    private void gerarTelefonesParaFuncionario(Funcionario funcionario) {
        Telefone telefone = new Telefone();
        telefone.setTipo(TipoTelefone.COMERCIAL);
        telefone.setNumero("99999-9999");
        telefone.setFuncionario(funcionario);  // Associar ao funcionário
        telefoneRepository.save(telefone);
    }

    // Gerar endereços para um funcionário
    private void gerarEnderecosParaFuncionario(Funcionario funcionario) {
        Endereco endereco = new Endereco();
        endereco.setTipo(TipoEndereco.COMERCIAL);
        endereco.setLogradouro("Rua Fictícia Funcionario " + funcionario.getId());
        endereco.setNumero("123");
        endereco.setBairro("Bairro Funcionario " + funcionario.getId());
        endereco.setCidade("Cidade Funcionario " + funcionario.getId());
        endereco.setEstado("Estado Funcionario");
        endereco.setCep("12345-678");
        endereco.setFuncionario(funcionario);  // Associar ao funcionário
        enderecoRepository.save(endereco);
    }
    
    // Gerar vendas mockadas
 // Gerar vendas mockadas
    public void gerarVendasMockadas(int quantidadeVdas) {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ServicoMercadoria> servicos = servicoMercadoriaRepository.findAll();

        if (clientes.isEmpty() || servicos.isEmpty()) {
            System.err.println("Clientes ou serviços não disponíveis para gerar vendas.");
            return;
        }

        for (int i = 0; i < quantidadeVdas; i++) {
            Venda venda = new Venda();
            venda.setCliente(clientes.get(random.nextInt(clientes.size())));
            venda.setDataVenda(new Date());

            // Lista de itens de venda
            int quantidadeItens = 1 + random.nextInt(3); // 1 a 3 itens por venda
            double valorTotal = 0.0;

            List<ItemDeVenda> itens = new java.util.ArrayList<>();

            for (int j = 0; j < quantidadeItens; j++) {
                ServicoMercadoria servico = servicos.get(random.nextInt(servicos.size()));
                int quantidade = 1 + random.nextInt(5);
                double totalItem = servico.getValor() * quantidade;

                ItemDeVenda item = new ItemDeVenda();
                item.setServicoMercadoria(servico);
                item.setVenda(venda); // importante
                item.setQuantidade(quantidade);
                item.setValorTotal(totalItem);

                valorTotal += totalItem;
                itens.add(item);
            }

            // Define o primeiro serviço como o serviço principal da venda
            if (!itens.isEmpty()) {
                venda.setServicoMercadoria(itens.get(0).getServicoMercadoria());
            }

            venda.setItens(itens);        // Associa os itens à venda
            venda.setValor(valorTotal);   // Valor total da venda

            try {
                vendaRepository.save(venda);         // Salva a venda
                itemDeVendaRepository.saveAll(itens); // Salva os itens (com FK da venda)
            } catch (Exception e) {
                System.err.println("Erro ao salvar venda mockada: " + e.getMessage());
            }
        }
    }



}
