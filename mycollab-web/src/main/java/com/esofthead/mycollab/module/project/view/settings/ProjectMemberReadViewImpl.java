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
package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.common.GenericLinkUtils;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.core.arguments.*;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.NumberUtils;
import com.esofthead.mycollab.module.project.*;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.ProjectMemberI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.view.AbstractProjectPageView;
import com.esofthead.mycollab.module.project.view.user.ProjectActivityStreamPagedList;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.TooltipHelper;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.*;
import com.esofthead.mycollab.vaadin.web.ui.*;
import com.esofthead.mycollab.vaadin.web.ui.field.DefaultViewField;
import com.esofthead.mycollab.vaadin.web.ui.field.LinkViewField;
import com.esofthead.mycollab.vaadin.web.ui.field.UserLinkViewField;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Span;
import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import static com.esofthead.mycollab.vaadin.TooltipHelper.TOOLTIP_ID;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class ProjectMemberReadViewImpl extends AbstractProjectPageView implements ProjectMemberReadView {
    private static final long serialVersionUID = 1L;

    private SimpleProjectMember beanItem;
    private AdvancedPreviewBeanForm<SimpleProjectMember> previewForm;

    private MHorizontalLayout bottomLayout;

    public ProjectMemberReadViewImpl() {
        super(AppContext.getMessage(ProjectMemberI18nEnum.DETAIL), FontAwesome.USER);

        previewForm = initPreviewForm();
        previewForm.setWidth("100%");

        bottomLayout = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false)).withFullWidth();
        this.addHeaderRightContent(createButtonControls());
        this.with(previewForm, bottomLayout);
    }

    @Override
    public SimpleProjectMember getItem() {
        return beanItem;
    }

    @Override
    public HasPreviewFormHandlers<SimpleProjectMember> getPreviewFormHandlers() {
        return previewForm;
    }

    public void previewItem(final SimpleProjectMember item) {
        this.beanItem = item;
        previewForm.setFormLayoutFactory(initFormLayoutFactory());
        previewForm.setBeanFormFieldFactory(initBeanFormFieldFactory());
        previewForm.setBean(item);
        createBottomPanel();
    }

    public SimpleProjectMember getBeanItem() {
        return beanItem;
    }

    public AdvancedPreviewBeanForm<SimpleProjectMember> getPreviewForm() {
        return previewForm;
    }

    protected AdvancedPreviewBeanForm<SimpleProjectMember> initPreviewForm() {
        return new AdvancedPreviewBeanForm<>();
    }

    private ComponentContainer createButtonControls() {
        return new ProjectPreviewFormControlsGenerator<>(previewForm).createButtonControls(ProjectPreviewFormControlsGenerator.DELETE_BTN_PRESENTED
                | ProjectPreviewFormControlsGenerator.EDIT_BTN_PRESENTED, ProjectRolePermissionCollections.USERS);
    }

    private void createBottomPanel() {
        bottomLayout.removeAllComponents();

        MVerticalLayout leftColumn = new MVerticalLayout().withMargin(new MarginInfo(false, true, false, false));
        ProjectActivityStreamPagedList activityStreamList = new ProjectActivityStreamPagedList();
        leftColumn.with(activityStreamList);

        UserAssignmentWidget userAssignmentWidget = new UserAssignmentWidget();
        userAssignmentWidget.showOpenAssignments();
        bottomLayout.with(leftColumn, userAssignmentWidget).expand(leftColumn);

        ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
        searchCriteria.setModuleSet(new SetSearchField<>(ModuleNameConstants.PRJ));
        searchCriteria.setCreatedUser(StringSearchField.and(previewForm.getBean().getUsername()));
        searchCriteria.setExtraTypeIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        searchCriteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        activityStreamList.setSearchCriteria(searchCriteria);
    }

    protected String initFormTitle() {
        return beanItem.getMemberFullName();
    }

    protected IFormLayoutFactory initFormLayoutFactory() {
        return new ProjectMemberReadLayoutFactory();
    }

    protected AbstractBeanFieldGroupViewFieldFactory<SimpleProjectMember> initBeanFormFieldFactory() {
        return new ProjectMemberFormFieldFactory(previewForm);
    }

    protected class ProjectMemberReadLayoutFactory extends AbstractFormLayoutFactory {
        private static final long serialVersionUID = 8833593761607165873L;

        @Override
        public ComponentContainer getLayout() {
            HorizontalLayout blockContent = new HorizontalLayout();
            blockContent.addStyleName("member-block");
            Image memberAvatar = UserAvatarControlFactory.createUserAvatarEmbeddedComponent(beanItem.getMemberAvatarId(), 100);
            memberAvatar.addStyleName(UIConstants.CIRCLE_BOX);
            blockContent.addComponent(memberAvatar);

            MVerticalLayout memberInfo = new MVerticalLayout().withMargin(new MarginInfo(false, false, false, true));

            ELabel memberLink = ELabel.h3(beanItem.getMemberFullName()).withFullWidth();
            memberInfo.addComponent(memberLink);

            String memberRoleLinkPrefix = String.format("<a href=\"%s%s%s\"", AppContext.getSiteUrl(), GenericLinkUtils.URL_PREFIX_PARAM,
                    ProjectLinkGenerator.generateRolePreviewLink(beanItem.getProjectid(), beanItem.getProjectroleid()));
            ELabel memberRole = new ELabel(ContentMode.HTML).withStyleName(UIConstants.META_INFO);
            if (Boolean.TRUE.equals(beanItem.getIsadmin()) || beanItem.getProjectroleid() == null) {
                memberRole.setValue(memberRoleLinkPrefix + "style=\"color: #B00000;\">" + "Project Owner" + "</a>");
            } else {
                memberRole.setValue(memberRoleLinkPrefix + "style=\"color:gray;font-size:12px;\">" + beanItem.getRoleName() + "</a>");
            }
            memberRole.setSizeUndefined();
            memberInfo.addComponent(memberRole);

            if (Boolean.TRUE.equals(AppContext.showEmailPublicly())) {
                Label memberEmailLabel = new Label(String.format("<a href='mailto:%s'>%s</a>", beanItem.getUsername(),
                        beanItem.getUsername()), ContentMode.HTML);
                memberEmailLabel.addStyleName(UIConstants.META_INFO);
                memberEmailLabel.setWidth("100%");
                memberInfo.addComponent(memberEmailLabel);
            }

            ELabel memberSinceLabel = new ELabel(String.format("Member since: %s", AppContext.formatPrettyTime(beanItem.getJoindate())))
                    .withDescription(AppContext.formatDateTime(beanItem.getJoindate())).withStyleName(UIConstants
                            .META_INFO).withFullWidth();
            memberInfo.addComponent(memberSinceLabel);

            if (ProjectMemberStatusConstants.ACTIVE.equals(beanItem.getStatus())) {
                Label lastAccessTimeLbl = new ELabel(String.format("Logged in %s", AppContext.formatPrettyTime(beanItem.getLastAccessTime())))
                        .withDescription(AppContext.formatDateTime(beanItem.getLastAccessTime()));
                lastAccessTimeLbl.addStyleName(UIConstants.META_INFO);
                memberInfo.addComponent(lastAccessTimeLbl);
            }

            String memberWorksInfo = ProjectAssetsManager.getAsset(ProjectTypeConstants.TASK).getHtml() + " " + new Span
                    ().appendText("" + beanItem.getNumOpenTasks()).setTitle("Open tasks") + "  " + ProjectAssetsManager
                    .getAsset(ProjectTypeConstants.BUG).getHtml() + " " + new Span().appendText("" + beanItem
                    .getNumOpenBugs()).setTitle("Open bugs") + " " +
                    " " + FontAwesome.MONEY.getHtml() + " " + new Span().appendText("" + NumberUtils.roundDouble(2,
                    beanItem.getTotalBillableLogTime())).setTitle("Billable hours") + "  " + FontAwesome.GIFT.getHtml() +
                    " " + new Span().appendText("" + NumberUtils.roundDouble(2, beanItem.getTotalNonBillableLogTime())).setTitle("Non billable hours");

            Label memberWorkStatus = new Label(memberWorksInfo, ContentMode.HTML);
            memberWorkStatus.addStyleName(UIConstants.META_INFO);
            memberInfo.addComponent(memberWorkStatus);
            memberInfo.setWidth("100%");

            blockContent.addComponent(memberInfo);
            blockContent.setExpandRatio(memberInfo, 1.0f);
            blockContent.setWidth("100%");

            return blockContent;
        }

        @Override
        protected Component onAttachField(Object propertyId, Field<?> field) {
            return null;
        }

    }

    private static class ProjectMemberFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<SimpleProjectMember> {
        private static final long serialVersionUID = 1L;

        ProjectMemberFormFieldFactory(GenericBeanForm<SimpleProjectMember> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(final Object propertyId) {
            if (propertyId.equals("projectroleid")) {
                if (Boolean.FALSE.equals(attachForm.getBean().getIsadmin())) {
                    return new LinkViewField(attachForm.getBean().getRoleName(), ProjectLinkBuilder.generateRolePreviewFullLink(
                            attachForm.getBean().getProjectid(), attachForm.getBean().getProjectroleid()), null);
                } else {
                    return new DefaultViewField("Project Admin");
                }
            } else if (propertyId.equals("username")) {
                return new UserLinkViewField(attachForm.getBean().getUsername(),
                        attachForm.getBean().getMemberAvatarId(), attachForm.getBean().getMemberFullName());
            }
            return null;
        }
    }

    private class UserAssignmentWidget extends Depot {
        private static final long serialVersionUID = 1L;

        private ProjectGenericTaskSearchCriteria searchCriteria;
        private final DefaultBeanPagedList<ProjectGenericTaskService, ProjectGenericTaskSearchCriteria, ProjectGenericTask> taskList;

        public UserAssignmentWidget() {
            super(String.format("Assignments: %d", 0), new CssLayout());
            this.setWidth("400px");

            final CheckBox overdueSelection = new CheckBox("Overdue");
            overdueSelection.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                    boolean isOverdueOption = overdueSelection.getValue();
                    if (isOverdueOption) {
                        searchCriteria.setDueDate(new DateSearchField(DateTimeUtils.getCurrentDateWithoutMS()));
                    } else {
                        searchCriteria.setDueDate(null);
                    }
                    updateSearchResult();
                }
            });

            final CheckBox isOpenSelection = new CheckBox("Open", true);
            isOpenSelection.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                    boolean isOpenOption = isOpenSelection.getValue();
                    if (isOpenOption) {
                        searchCriteria.setIsOpenned(new SearchField());
                    } else {
                        searchCriteria.setIsOpenned(null);
                    }
                    updateSearchResult();
                }
            });

            addHeaderElement(overdueSelection);
            addHeaderElement(isOpenSelection);

            taskList = new DefaultBeanPagedList<>(AppContextUtil.getSpringBean(ProjectGenericTaskService.class),
                    new TaskRowDisplayHandler(), 10);
            bodyContent.addComponent(taskList);
        }

        private void showOpenAssignments() {
            searchCriteria = new ProjectGenericTaskSearchCriteria();
            searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
            searchCriteria.setAssignUser(StringSearchField.and(beanItem.getUsername()));
            searchCriteria.setIsOpenned(new SearchField());
            updateSearchResult();
        }

        private void updateSearchResult() {
            taskList.setSearchCriteria(searchCriteria);
            setTitle(String.format("Assignments: %d", taskList.getTotalCount()));
        }
    }

    public static class TaskRowDisplayHandler implements DefaultBeanPagedList.RowDisplayHandler<ProjectGenericTask> {

        @Override
        public Component generateRow(AbstractBeanPagedList host, ProjectGenericTask genericTask, int rowIndex) {
            MHorizontalLayout rowComp = new MHorizontalLayout().withStyleName("list-row").withFullWidth();
            rowComp.setDefaultComponentAlignment(Alignment.TOP_LEFT);

            A taskLink = new A().setId("tag" + TOOLTIP_ID);
            taskLink.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(genericTask.getType(), genericTask.getTypeId() + ""));
            taskLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());
            if (ProjectTypeConstants.BUG.equals(genericTask.getType()) || ProjectTypeConstants.TASK.equals(genericTask.getType())) {
                taskLink.appendText(String.format("[#%d] - %s", genericTask.getExtraTypeId(), genericTask.getName()));
                taskLink.setHref(ProjectLinkBuilder.generateProjectItemLink(genericTask.getProjectShortName(),
                        genericTask.getProjectId(), genericTask.getType(), genericTask.getExtraTypeId() + ""));
            } else {
                taskLink.appendText(genericTask.getName());
                taskLink.setHref(ProjectLinkBuilder.generateProjectItemLink(genericTask.getProjectShortName(),
                        genericTask.getProjectId(), genericTask.getType(), genericTask.getTypeId() + ""));
            }
            Label issueLbl = new Label(taskLink.write(), ContentMode.HTML);
            if (genericTask.isClosed()) {
                issueLbl.addStyleName("completed");
            } else if (genericTask.isOverdue()) {
                issueLbl.addStyleName("overdue");
            }

            String avatarLink = StorageFactory.getInstance().getAvatarPath(genericTask.getAssignUserAvatarId(), 16);
            Img img = new Img(genericTask.getAssignUserFullName(), avatarLink).setTitle(genericTask
                    .getAssignUserFullName());

            MHorizontalLayout iconsLayout = new MHorizontalLayout().with(new ELabel(ProjectAssetsManager.getAsset
                    (genericTask.getType()).getHtml(), ContentMode.HTML), new ELabel(img.write(), ContentMode.HTML));
            MCssLayout issueWrapper = new MCssLayout(issueLbl);
            rowComp.with(iconsLayout, issueWrapper).expand(issueWrapper);
            return rowComp;
        }
    }
}
