package br.com.alura.livraria.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Created by Robson Martinhão on 02/01/2020
 **/

public class Autorizador implements PhaseListener {
    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
        String nomePagina = context.getViewRoot().getViewId();

        System.out.println(nomePagina);

        if ("/login.xhtml".equals(nomePagina)) {
            return;
        }

        Object usuarioLogado = context.getExternalContext().getSessionMap().get("usuarioLogado");
        if (usuarioLogado != null) {
            return;
        }

        NavigationHandler handler = context.getApplication().getNavigationHandler();
        handler.handleNavigation(context, null, "/login?faces-redirect=true");
        context.renderResponse();
    }

    @Override
    public void beforePhase(PhaseEvent phaseEvent) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
