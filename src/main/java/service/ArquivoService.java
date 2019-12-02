package service;

import dao.util.JpaUtil;
import model.Arquivo;

import javax.persistence.EntityManager;
import java.io.*;

public class ArquivoService {

    private EntityManager entityManager;

    public ArquivoService() {
        entityManager = JpaUtil.getEntityManager();
    }

    private String folderPath = System.getProperty("user.home") + "/" + "ecommerce";

    public Arquivo inserirArquivoNoSistema(Arquivo arquivo, String diretorio) {
        if (arquivo == null) {
            return null;
        }

        if (arquivo.getStream() == null && arquivo.getFile() != null) {
            try {
                arquivo.setStream(new FileInputStream(arquivo.getFile()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            if (arquivo.getStream() != null
                    && arquivo.getStream().available() > 0) {
                montarArquivo(arquivo, diretorio);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        arquivo.setCaminho(diretorio);
        Arquivo arquivoSalvo = entityManager.merge(arquivo);
        arquivoSalvo.setFile(arquivo.getFile());
        return arquivoSalvo;
    }

    private void montarArquivo(Arquivo arquivo, String diretorio) throws IOException {
        File file = obterArquivo(arquivo.getNome(), diretorio);
        salvarStreamNoArquivo(arquivo.getStream(), file);
        arquivo.setFile(file);
    }

    public File obterArquivo(String nomeArquivo, String nomeDiretorio) {
        File diretorioRaiz = new File(folderPath);
        File diretorio = new File(diretorioRaiz, nomeDiretorio);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        File file = new File(diretorio, nomeArquivo);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private void salvarStreamNoArquivo(InputStream stream, File file) throws IOException {
        if (stream.available() <= 0) {
            return;
        }
        OutputStream out = new FileOutputStream(file);
        byte buf[] = new byte[1024];
        int len;
        while ((len = stream.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.close();
        stream.close();
    }

}
