package interfaces;

import java.util.List;

public interface IBaseDao<T extends IBaseModel> {

    public abstract void salvar(T entity);

    public abstract void alterar(T entity);

    public abstract void excluir(T entity);

    public abstract T buscarPorid(Integer id);

    public abstract List<T> buscarTodos();

}
