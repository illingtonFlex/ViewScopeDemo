package help.me.understand.jsf.ViewScopeDemo.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LifeCycleListener implements PhaseListener {

    private static final Logger log = LoggerFactory.getLogger(LifeCycleListener.class);

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    /**
     * Uncomment log statement when you want JSF LifeCycle changes to be announced in the logs.
     * This is useful during debugging
     *
     */
    public void beforePhase(PhaseEvent event) {
        log.info("START PHASE " + event.getPhaseId());
        HttpServletRequest request = ((HttpServletRequest) event.getFacesContext().getExternalContext().getRequest());
        HttpSession session = request.getSession();
        log.debug("Here...");
    }

    /**
     * Uncomment log statement when you want JSF LifeCycle changes to be announced in the logs.
     * This is useful during debugging
     *
     */
    public void afterPhase(PhaseEvent event) {
        log.info("END PHASE " + event.getPhaseId());
    }

}