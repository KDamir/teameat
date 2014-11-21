/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.utils;

import java.util.Map;
import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.render.RenderKitFactory;

/**
 *
 * @author Дамир
 */
public class CustomViewHandler extends ViewHandlerWrapper{
    private ViewHandler handler;
    
    public CustomViewHandler(ViewHandler _handler) {
        handler = _handler;
    }

    @Override
    public String calculateRenderKitId(FacesContext fc) {
        ExternalContext extc = fc.getExternalContext();
        Map<String,String> map = extc.getRequestHeaderMap();
        String userAgent = map.get("user-agent");
        if(userAgent.contains("mobile"))
            return "PRIMEFACES_MOBILE";
        else
            return RenderKitFactory.HTML_BASIC_RENDER_KIT;
    }

    @Override
    public ViewHandler getWrapped() {
        return handler;
    }
    
}
