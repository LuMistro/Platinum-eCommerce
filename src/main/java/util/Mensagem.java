package util;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.ResourceBundle;

@ManagedBean
public class Mensagem {

    private static ResourceBundle bundle = ResourceBundle.getBundle("messages",
            FacesContext.getCurrentInstance().getViewRoot().getLocale());

    public static void addMensagem(javax.faces.application.FacesMessage.Severity severity, String messageKey) {
        String message = bundle.getString(messageKey);
        javax.faces.application.FacesMessage facesMessage = new javax.faces.application.FacesMessage(severity, message, message);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage("messages", facesMessage);
    }

    public static void addMensagemInfo(String messageKey) {
        addMensagem(javax.faces.application.FacesMessage.SEVERITY_INFO, messageKey);
    }

    public static void addMensagemError(String messageKey) {
        addMensagem(javax.faces.application.FacesMessage.SEVERITY_ERROR, messageKey);
    }

    public static void addMensagemWarn(String messageKey) {
        addMensagem(javax.faces.application.FacesMessage.SEVERITY_WARN, messageKey);
    }
}
