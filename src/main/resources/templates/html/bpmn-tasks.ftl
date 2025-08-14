<#import "../bpmn-template-util.ftl" as util/>
<#import "bpmn-extensions.ftl" as extensions>

<#macro listTasks processElements>

<#assign taskTypes = [
{ "serviceTask": "Service Tasks" },
{ "userTask": "User Tasks" },
{ "sendTask": "Send Tasks" },
{ "receiveTask": "Receive Tasks" },
{ "businessRuleTask": "Business Rule Tasks" },
{ "scriptTask": "Script Tasks" },
{ "anonymousTask": "Anonymous Tasks" }
]/>

<#assign noTasks = processElements?filter(element -> element.flowType?ends_with("Task"))/>

<#if (noTasks?size = 0) && !skipEmptySections>
    <details ${openSections}>
    <summary><h4>Tasks</h4></summary>
    <#if (noTasks?size > 0)>
        <#list taskTypes as type>
            <#list type as taskType, title>
                <#assign tasks = processElements?filter(element -> element.flowType == taskType)/>
                <#if (tasks?size > 0)>
                    <h5>${title}</h5>
                    <ul>
                        <#list tasks as task>
                            <li><@util.emptyOrNull task.name "task" /> (${task.id})</li>
                        </#list>
                    </ul>
                </#if>
            </#list>
        </#list>
    <#else>
        <@util.emptySection skip=skipEmptySections section="tasks" quote=false markdown=false />
    </#if>
</details>
</#if>

<#if (noTasks?size > 0)>
    <#list taskTypes as type>
        <#list type as taskType, title>
            <#assign tasks = processElements?filter(element -> element.flowType == taskType)/>
            <#if (tasks?size > 0)>
                <details ${openSections}>
                    <summary><h4>${title}</h4></summary>
                        <#list tasks as task>
                            <p>
                                <h5><@util.emptyOrNull task.name "task" /></h5>
                                <table>
                                    <tr>
                                        <th>Property</th>
                                        <th>Value</th>
                                        <th>Version</th>
                                    </tr>
                                    <tr>
                                        <td>ID</td>
                                        <td>${task.id}</td>
                                        <td>n/a</td>
                                    </tr>
                                    <tr>
                                        <td>Template</td>
                                        <td><@util.emptyOrNull task.template.name "template" /></td>
                                        <td><@util.emptyOrNull task.template.version "template version" /></td>
                                    </tr>
                                </table>
                                <#if task.documentation?has_content>
                                    <blockquote>${task.documentation}</blockquote>
                                <#else>
                                    <@util.emptySection skip=skipEmptySections section="documentation" quote=true markdown=false />
                                </#if>
                            </p>
                            <p>
                                <#if task.extensions?has_content>
                                    <@extensions.listExtensions task.extensions />
                                </#if>
                            </p>
                        </#list>
                </details>
            </#if>
        </#list>
    </#list>
</#if>

</#macro>