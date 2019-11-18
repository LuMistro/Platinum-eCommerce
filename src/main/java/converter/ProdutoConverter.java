package converter;

import dao.ProdutoDao;
import interfaces.IBaseDao;
import model.Produto;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

    private IBaseDao<Produto> produtoDao;

    public ProdutoConverter() {
        produtoDao = new ProdutoDao();
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        if (produtoDao.buscarPorid(Integer.valueOf(s)) == null) {
            return null;
        } else {
            Produto produto = (Produto) produtoDao.buscarPorid(Integer.valueOf(s));
            return produto;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object p) {
        Produto produto = (Produto) p;
        produto = produtoDao.buscarPorid(produto.getId());

        if (produto == null) {
            return null;
        } else {
            return produto.getId().toString();
        }
    }
}
