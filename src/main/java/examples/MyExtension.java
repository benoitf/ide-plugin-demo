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
import com.google.inject.Singleton;

import org.eclipse.che.ide.api.action.ActionManager;
import org.eclipse.che.ide.api.action.DefaultActionGroup;
import org.eclipse.che.ide.api.constraints.Constraints;
import org.eclipse.che.ide.api.extension.Extension;

import static org.eclipse.che.ide.api.action.IdeActions.GROUP_MAIN_MENU;
import static org.eclipse.che.ide.api.action.IdeActions.GROUP_MAIN_TOOLBAR;

@Singleton
@Extension(title = "My Extension", version = "1.0.0")
public class MyExtension {


    @Inject
    public MyExtension(MyResources resources, ActionManager actionManager, MyAction action, StartJettyAction startJettyAction,
                       StopJettyAction stopJettyAction, BuildAndStartAction buildAndStartAction) {

        // register action
        actionManager.registerAction("MyActionID", action);


        // get main menu
        DefaultActionGroup mainMenu = (DefaultActionGroup)actionManager.getAction(GROUP_MAIN_MENU);

        // create dedicated menu entry
        DefaultActionGroup myMenu = new DefaultActionGroup("Jetty", true, actionManager);

        // add the jetty menu at the end
        mainMenu.add(myMenu, Constraints.LAST);

        // register menu
        actionManager.registerAction("MyMenuID", myMenu);

        // add action in our menu
        myMenu.add(action);
        myMenu.addSeparator();
        myMenu.add(startJettyAction);
        myMenu.add(stopJettyAction);
        myMenu.add(buildAndStartAction);


        // get main toolbar
        DefaultActionGroup toolbarMenu = (DefaultActionGroup)actionManager.getAction(GROUP_MAIN_TOOLBAR);

        // create a new toolbar group
        DefaultActionGroup toolbarGroup = new DefaultActionGroup("Jetty", true, actionManager);
        toolbarGroup.getTemplatePresentation().setDescription("Jetty...");
        toolbarGroup.getTemplatePresentation().setSVGResource(resources.jettyIcon());

        // add action
        toolbarGroup.add(action);
        toolbarGroup.addSeparator();
        toolbarGroup.add(startJettyAction);
        toolbarGroup.add(stopJettyAction);
        toolbarGroup.add(buildAndStartAction);
        toolbarMenu.add(toolbarGroup);

    }
}
