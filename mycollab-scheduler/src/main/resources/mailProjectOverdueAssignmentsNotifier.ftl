<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<#import "macroTextBlock.ftl" as lib>
<#include "mailHeader.ftl">
<body>
    <table width="800" cellpadding="0" cellspacing="0" border="0" style="font-size:12px; margin: 20px 0px;">
        <#include "mailLogo.ftl">
        <tr>
            <td style="padding: 10px 30px">
                <p><b>Overdue assignments</b></p>
                <#if assignments?has_content>
                    <table width="100%" cellpadding="0" cellspacing="0" border="0" style="font-size: 12px; margin: 20px 0px; border-collapse: collapse;">
                        <tr style="border-bottom: 1px solid $styles.border_color">
                            <td style="font-weight: bold; ${styles.cell('80px')}">Due Date</td>
                            <td style="font-weight: bold; ${styles.cell('300px')}">Assignment</td>
                            <td style="font-weight: bold; ${styles.cell('300px')}">Assignee</td>
                        </tr>
                        <#list assignments as assignment>
                            <tr style="border-bottom: 1px solid ${styles.border_color}">
                                <td style="${styles.cell('80px')} color: ${styles.meta_color}">${formatter.formatDate(assignment.dueDate)}</td>
                                <td style="${styles.cell('300px')}">${formatter.formatLink(subdomain, assignment)}</td>
                                <td style="${styles.cell('300px')}">${formatter.formatAssignUser(subdomain, assignment)}</td>
                            </tr>
                        </#list>
                    </table>
                </#if>
            </td>
        </tr>
        <#include "mailFooter.ftl">
    </table>
</body>