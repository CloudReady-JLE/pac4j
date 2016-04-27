package org.pac4j.core.engine;

import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Callback logic with web session renewal for J2E.
 *
 * @author Jerome Leleu
 * @since 1.9.0
 */
public class J2ERenewSessionCallbackLogic extends DefaultCallbackLogic<Object> {

    protected void renewSession(final WebContext context) {
        logger.debug("Discard old session and replace by a new one...");
        final HttpServletRequest request = ((J2EContext) context).getRequest();
        final HttpSession session = request.getSession();
        final Map<String, java.lang.Object> attributes = new HashMap<>();
        Collections.list(session.getAttributeNames()).forEach(k -> attributes.put(k, session.getAttribute(k)));
        session.invalidate();
        final HttpSession newSession = request.getSession(true);
        attributes.forEach((k, v) -> newSession.setAttribute(k, v));
    }
}
