/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.SecureAccessException;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.project.view.ProjectGenericListPresenter;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.mvp.LoadPolicy;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class TaskDashboardPresenter extends ProjectGenericListPresenter<TaskDashboardView, TaskSearchCriteria, SimpleTask> {
    private static final long serialVersionUID = 1L;

    private ProjectTaskService taskService;

    public TaskDashboardPresenter() {
        super(TaskDashboardView.class);
        taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
    }

    @Override
    public void doSearch(TaskSearchCriteria searchCriteria) {
        view.queryTask(searchCriteria);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.TASKS)) {
            container.removeAllComponents();
            container.addComponent(view);
            String query = (data != null && data.getParams() instanceof String) ? (String) data.getParams() : "";
            view.displayView(query);

            ProjectBreadcrumb breadCrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
            breadCrumb.gotoTaskDashboard(query);
        } else {
            throw new SecureAccessException();
        }
    }

    @Override
    public ISearchableService<TaskSearchCriteria> getSearchService() {
        return taskService;
    }

    @Override
    protected void deleteSelectedItems() {

    }
}
