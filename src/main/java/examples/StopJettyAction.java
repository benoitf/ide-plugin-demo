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
import org.eclipse.che.ide.extension.machine.client.actions.SelectCommandComboBoxReady;
import org.eclipse.che.ide.extension.machine.client.command.CommandConfiguration;
import org.eclipse.che.ide.extension.machine.client.command.CommandManager;
import org.eclipse.che.ide.util.loging.Log;

/**
 * @author Florent Benoit
 */
public class StopJettyAction extends Action {
    private final SelectCommandComboBoxReady  selectCommandAction;
    private final CommandManager              commandManager;

    @Inject
    public StopJettyAction(SelectCommandComboBoxReady selectCommandAction, CommandManager commandManager, MyResources resources) {
        super("Stop Jetty", "Stop the jetty appserver", null, resources.stopIcon());
        this.selectCommandAction = selectCommandAction;
        this.commandManager = commandManager;
    }

    public void actionPerformed(ActionEvent event) {
        String name = "jetty-project: stop";
        CommandConfiguration command = this.selectCommandAction.getCommandByName(name);
        if(command != null) {
            this.commandManager.execute(command);
        } else {
           Log.error(getClass(), "unable to stop jetty as command jetty:stop is not there");
        }

    }
}
