package bean;

import dao.ProdutoDao;
import interfaces.IBaseDao;
import model.Produto;
import org.primefaces.event.FileUploadEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ManagedBean
public class ProdutoMB implements Serializable {

    private Produto produto;
    private IBaseDao<Produto> produtoDao;
    private List<Produto> produtos;
    private List<String> fotos;

    @PostConstruct
    private void init() {
        inicializaObjetos();
        atualizar();
    }


    public void limpar() {
        produto = new Produto();
    }


    public void salvar() {
        produtoDao.salvar(produto);
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

    public List<String> getFotos() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

//        File pastaImagensTopo = new File(scontext.getRealPath("/WEB-INF/imagensTopo/"));
        File pastaImagensTopo = new File(scontext.getRealPath("/uploads/imagensTopo/"));
        if (!pastaImagensTopo.exists()) pastaImagensTopo.mkdirs();
        File[] arquivos = pastaImagensTopo.listFiles();
        ArrayList fotos = new ArrayList();
        for (File arquivo : arquivos) {
            if (arquivo.isFile()) {
                String ext = arquivo.getName().substring(arquivo.getName().lastIndexOf(".")).toLowerCase();
                if (ext.equals(".jpg") || ext.equals(".jpeg") || ext.equals(".bmp") || ext.equals(".gif") || ext.equals(".png")) {
                    fotos.add("/uploads/imagensTopo/" + arquivo.getName());
                }
            }
        }
        return fotos;
    }

    public void setFotos(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage(event.getFile().getFileName() + " foi enviado com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file
        try {
            byte[] foto = event.getFile().getContents();
            String nomeArquivo = event.getFile().getFileName();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
            String arquivo = scontext.getRealPath("/resources/imagens/" + nomeArquivo);

            File f = new File(arquivo);
            if (!f.getParentFile().exists()) f.getParentFile().mkdirs();
            if (!f.exists()) f.createNewFile();
            System.out.println(f.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(arquivo);
            fos.write(foto);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
