/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.domain;

import com.esofthead.mycollab.core.utils.StringUtils;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class SimpleAccount extends Account {
    private static final long serialVersionUID = 1L;

    private String createdUserAvatarId;

    private String createdUserFullName;

    private String assignUserAvatarId;

    private String assignUserFullName;

    private Integer numProjects;

    public String getCreatedUserAvatarId() {
        return createdUserAvatarId;
    }

    public void setCreatedUserAvatarId(String createdUserAvatarId) {
        this.createdUserAvatarId = createdUserAvatarId;
    }

    public String getCreatedUserFullName() {
        if (StringUtils.isBlank(createdUserFullName)) {
            return StringUtils.extractNameFromEmail(getCreateduser());
        }

        return createdUserFullName;
    }

    public void setCreatedUserFullName(String createdUserFullName) {
        this.createdUserFullName = createdUserFullName;
    }

    public String getAssignUserAvatarId() {
        return assignUserAvatarId;
    }

    public void setAssignUserAvatarId(String assignUserAvatarId) {
        this.assignUserAvatarId = assignUserAvatarId;
    }

    public String getAssignUserFullName() {
        if (StringUtils.isBlank(assignUserFullName)) {
            return StringUtils.extractNameFromEmail(getAssignuser());
        }

        return assignUserFullName;
    }

    public void setAssignUserFullName(String assignUserFullName) {
        this.assignUserFullName = assignUserFullName;
    }

    public Integer getNumProjects() {
        return numProjects;
    }

    public void setNumProjects(Integer numProjects) {
        this.numProjects = numProjects;
    }

    public enum Field {
        assignUserFullName;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }

}
