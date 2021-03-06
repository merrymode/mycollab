/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.module.project.view;

import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.events.ProjectEvent;
import com.esofthead.mycollab.mobile.module.project.ui.AbstractListPageView;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent;
import com.esofthead.mycollab.mobile.ui.AbstractPagedBeanList;
import com.esofthead.mycollab.mobile.ui.SearchInputField;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.i18n.ProjectI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.vaadin.navigationbarquickmenu.NavigationBarQuickMenu;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
@ViewComponent
public class UserProjectListViewImpl extends AbstractListPageView<ProjectSearchCriteria, SimpleProject> implements UserProjectListView {
    private static final long serialVersionUID = 664947871255886622L;

    public UserProjectListViewImpl() {
        this.setCaption(AppContext.getMessage(ProjectCommonI18nEnum.M_VIEW_PROJECT_LIST));
    }

    @Override
    protected AbstractPagedBeanList<ProjectSearchCriteria, SimpleProject> createBeanList() {
        return new ProjectListDisplay();
    }

    @Override
    protected SearchInputField<ProjectSearchCriteria> createSearchField() {
        return null;
    }

    @Override
    protected void doSearch() {
        if (getPagedBeanTable().getSearchRequest() == null) {
            ProjectSearchCriteria criteria = new ProjectSearchCriteria();
            criteria.setInvolvedMember(StringSearchField.and(AppContext.getUsername()));
            criteria.setProjectStatuses(new SetSearchField(OptionI18nEnum.StatusI18nEnum.Open.name()));
            getPagedBeanTable().setSearchCriteria(criteria);
        }
        getPagedBeanTable().refresh();
    }

    @Override
    protected void buildNavigateMenu() {
        addSection("Views:");

        // Buttons with styling (slightly smaller with left-aligned text)
        Button activityBtn = new Button("Activities", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                closeMenu();
                EventBusFactory.getInstance().post(new ProjectEvent.AllActivities(this));
            }
        });
        activityBtn.setIcon(FontAwesome.INBOX);
        addMenuItem(activityBtn);

        Button prjBtn = new Button("Projects", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                closeMenu();
                EventBusFactory.getInstance().post(new ProjectEvent.GotoProjectList(this, null));
            }
        });
        prjBtn.setIcon(FontAwesome.BUILDING);
        addMenuItem(prjBtn);

        addSection("Settings:");

        Button logoutBtn = new Button("Logout", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                closeMenu();
                EventBusFactory.getInstance().post(new ShellEvent.LogOut(this));
            }
        });
        logoutBtn.setIcon(FontAwesome.SIGN_OUT);
        addMenuItem(logoutBtn);
    }

    @Override
    protected Component buildRightComponent() {
        NavigationBarQuickMenu menu = new NavigationBarQuickMenu();
        menu.setButtonCaption("...");
        MVerticalLayout content = new MVerticalLayout();
        content.with(new Button(AppContext.getMessage(ProjectI18nEnum.NEW), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                EventBusFactory.getInstance().post(new ProjectEvent.GotoAdd(this, null));
            }
        }));
        menu.setContent(content);
        return menu;
    }


}
