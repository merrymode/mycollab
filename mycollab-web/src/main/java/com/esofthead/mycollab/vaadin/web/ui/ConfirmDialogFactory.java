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
package com.esofthead.mycollab.vaadin.web.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.dialogs.DefaultConfirmDialogFactory;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class ConfirmDialogFactory extends DefaultConfirmDialogFactory {
    private static final long serialVersionUID = 1L;

    @Override
    public ConfirmDialog create(String caption, String message, String okCaption, String cancelCaption, String notOkCaption) {
        ConfirmDialog d = super.create(caption, message, okCaption, cancelCaption, notOkCaption);

        d.getContent().setStyleName("custom-dialog");
        d.getContent().setHeightUndefined();
        d.setHeightUndefined();

        Button ok = d.getOkButton();
        ok.setStyleName(UIConstants.BUTTON_ACTION);

        HorizontalLayout buttons = (HorizontalLayout) ok.getParent();
        buttons.setHeightUndefined();

        Button cancelBtn = d.getCancelButton();
        cancelBtn.setStyleName(UIConstants.BUTTON_OPTION);
        cancelBtn.focus();

        return d;
    }

}
