/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myjaphoo.gui.action;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.xml.bind.JAXBException;
import org.myjaphoo.MyjaphooController;
import org.myjaphoo.gui.icons.Icons;

/**
 * Recreates the tumbs for  one or more files.
 * @author lang
 */
public class VLCPlayAction extends AbstractWankmanAction implements DisplayAsLastUsedActions {

    private final static ResourceBundle localeBundle = ResourceBundle.getBundle("org/myjaphoo/gui/action/resources/VLCPlayAction");

    public VLCPlayAction(MyjaphooController controller, ViewContext context) {
        super(controller, localeBundle.getString("PLAY (VLC)"), Icons.IR_MEDIA_PLAYBACK.icon, context);
    }

    @Override
    public void run(final MyjaphooController controller, ActionEvent e, ViewContext context) throws IOException, JAXBException {
        controller.playMovies(context.getSelNodes());
    }
}
