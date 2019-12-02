package bean;

import dao.ProdutoDao;
import interfaces.IBaseDao;
import model.Arquivo;
import model.Produto;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import service.ArquivoService;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class ProdutoMB implements Serializable {

    private Produto produto;
    private IBaseDao<Produto> produtoDao;
    private List<Produto> produtos;
    private String arquivoSelecionado;
    private ArquivoService arquivoService;
    private ProdutoMB produtoMB;
    private String caminhoAbsoluto = System.getProperty("user.home") + "/" + "ecommerce/produtos";

    @PostConstruct
    private void init() {
        inicializaObjetos();
        atualizar();
    }



    public void limpar() {
        produto = new Produto();
        arquivoSelecionado = null;
    }


    public void salvar() {

        if (produto.getId() == null) {
            produto.setArquivo(ArquivoMB.arquivo);
            Arquivo arquivoSalvo = arquivoService.inserirArquivoNoSistema(produto.getArquivo() , criarDiretorioArquivo(produto));
            produto.setArquivo(arquivoSalvo);
            produtoDao.salvar(produto);
        } else {
            produtoDao.alterar(produto);
        }
        atualizar();
        limpar();
    }

    private String criarDiretorioArquivo(Produto produto) {
        return "produtos/";
    }


    public StreamedContent baixarArquivo(Produto produto) {
      File file =  arquivoService.obterArquivo(produto.getArquivo().getNome() ,produto.getArquivo().getCaminho());
        String contentType = new MimetypesFileTypeMap().getContentType(produto.getArquivo().getNome());
        try {
            FileInputStream stream = new FileInputStream(file);
            return new DefaultStreamedContent(stream , contentType , produto.getArquivo().getNome());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void atualizar() {
        produtos = produtoDao.buscarTodos();
    }

    private void inicializaObjetos() {
        produto = new Produto();
        produtoDao = new ProdutoDao();
        produtos = new ArrayList<>();
        arquivoService = new ArquivoService();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getArquivoSelecionado() {
        return arquivoSelecionado;
    }

    public void setArquivoSelecionado(String arquivoSelecionado) {
        this.arquivoSelecionado = arquivoSelecionado;
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        Arquivo arquivo = new Arquivo();
        arquivo.setNome(event.getFile().getFileName());
        arquivo.setStream(event.getFile().getInputstream());
        produto.setArquivo(arquivo);
        arquivoSelecionado = arquivo.getNome();
    }

    public String getCaminhoAbsoluto() {
        return caminhoAbsoluto;
    }

    public void setCaminhoAbsoluto(String caminhoAbsoluto) {
        this.caminhoAbsoluto = caminhoAbsoluto;
    }
}
