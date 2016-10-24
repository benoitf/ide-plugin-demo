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

import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.api.command.CommandImpl;
import org.eclipse.che.ide.api.command.CommandManager;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Map;

/**
 * @author Florent Benoit
 */
public class BuildAndStartAction extends Action {
    private final CommandManager commandManager;
    private final AppContext     appContext;

    @Inject
    public BuildAndStartAction(CommandManager commandManager, MyResources resources, AppContext appContext) {
        super("Build and run", "Build the application and deploy it on the jetty appserver", null, resources.buildIcon());
        this.commandManager = commandManager;
        this.appContext = appContext;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Map<String, String> attributes = Collections.emptyMap();
        CommandImpl command = new CommandImpl("buildAndDeploy", "mvn -f ${current.project.path} clean install && cp ${current.project.path}/target/*.war /home/user/jetty9/webapps/ROOT.war && /home/user/jetty9/bin/jetty.sh  run", "mvn", attributes);
        this.commandManager.executeCommand(command,  appContext.getDevMachine().getDescriptor());
    }

}
