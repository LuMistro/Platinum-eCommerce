package bean;

import dao.ProdutoDao;
import interfaces.IBaseDao;
import model.Produto;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ManagedBean
@ViewScoped
public class ProdutoMB implements Serializable {

    private Produto produto;
    private IBaseDao<Produto> produtoDao;
    private List<Produto> produtos;
    private String caminhoFoto = "C:/Imagens/";
    private String nomeFoto = new String();


    @PostConstruct
    private void init() {
        inicializaObjetos();
        atualizar();
    }


    public void limpar() {
        produto = new Produto();
    }


    public void salvar() {
        if (produto.getId() == null) {
            produtoDao.salvar(produto);
        } else {
            produtoDao.alterar(produto);
        }
        atualizar();
        limpar();
    }

    public void atualizar() {
        produtos = produtoDao.buscarTodos();
    }

    private void inicializaObjetos() {
        produto = new Produto();
        produtoDao = new ProdutoDao();
        produtos = new ArrayList<>();
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

//    public void upload() {
//        if (file != null) {
//            produto.setFoto(file.getFileName());
//            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }
//    }

    public void handleFileUpload(FileUploadEvent event) {

        try {
            Path destino = Paths.get("C:/Imagens", event.getFile().getFileName());
            Files.write(destino, event.getFile().getContents());
            nomeFoto = destino.getFileName().toString();
            produto.setFoto(caminhoFoto + nomeFoto);

        } catch (IOException e) {
            e.printStackTrace();
        }
        FacesMessage msg = new FacesMessage("Upload de foto feito com sucesso", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
