/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package examples;

import com.google.inject.Inject;

import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.api.command.CommandImpl;
import org.eclipse.che.ide.api.command.CommandManager;

import java.util.Collections;
import java.util.Map;

/**
 * @author Florent Benoit
 */
public class StopJettyAction extends Action {
    private final CommandManager commandManager;
    private final AppContext     appContext;

    @Inject
    public StopJettyAction(CommandManager commandManager, MyResources resources, AppContext appContext) {
        super("Stop Jetty", "Stop the jetty appserver", null, resources.stopIcon());
        this.commandManager = commandManager;
        this.appContext = appContext;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Map<String, String> attributes = Collections.emptyMap();
        CommandImpl command = new CommandImpl("stop-jetty", "/home/user/jetty9/bin/jetty.sh stop", "custom", attributes);
        this.commandManager.executeCommand(command,  appContext.getDevMachine().getDescriptor());
    }

}
