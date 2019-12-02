package bean;

import model.Arquivo;
import org.primefaces.event.FileUploadEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;

@ManagedBean
@SessionScoped
public class ArquivoMB {

    public static Arquivo arquivo;
    public static String arquivoSelecionado;


    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public String getArquivoSelecionado() {
        return arquivoSelecionado;
    }

    public void setArquivoSelecionado(String arquivoSelecionado) {
        this.arquivoSelecionado = arquivoSelecionado;
    }

    public void reiniciar() {
        arquivo = null;
        arquivoSelecionado = null;
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        reiniciar();
        arquivo = new Arquivo();
        arquivo.setNome(event.getFile().getFileName());
        arquivo.setStream(event.getFile().getInputstream());
        arquivoSelecionado = arquivo.getNome();
    }


}
